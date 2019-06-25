(ns cljaws.ddb
  (:require
   [cognitect.aws.client.api :as aws]
   [cljaws.util :as u]
   [clojure.string :as str]))


(defonce ^:private ddb-api- (atom nil))

(defn ddb-api
  ([] (ddb-api nil))
  ([profile]
   (or (and (nil? profile) @ddb-api-)
       (reset! ddb-api- (aws/client {:api :dynamodb
                                    :credentials-provider (u/credentials profile)})))))

(defn- to-ddb
  [o]
  (let [t (type o)]
    (cond (or (= t java.lang.String)
              (= t  java.lang.Boolean))
          {:S o}


          (number? o) {:N (str o)}
         )))


(defn ops
  []
  (u/ops (ddb-api)))

(defn doc
  [op]
  (u/doc (ddb-api) op))


(defn get-item
  [table k]
  (aws/invoke (ddb-api) {:op :GetItem
                         :request {:Key k
                                   :TableName table}}))
