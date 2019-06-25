(ns cljaws.s3
  (:require [cljaws.util :as u]
            [clojure.string :as str]
            [cognitect.aws.client.api :as aws]))

(defonce ^:private s3-api- (atom nil))

(defn s3-api
  ([] (s3-api nil))
  ([profile]
   (or (and (nil? profile) @s3-api-)
       (reset! s3-api- (aws/client {:api :s3
                                    :credentials-provider (u/credentials profile)})))))

(defn- bucket-prefix
  [path]
  (and path (str/split path #"/" 2)))

(defn ops
  []
  (u/ops (s3-api)))

(defn doc
  [op]
  (u/doc (s3-api) op))

(defn ls
  "List contents of a bucket"
  ([] (ls nil))
  ([path]
   (let [[bucket prefix] (bucket-prefix path)]
     (if (empty? bucket)
      (aws/invoke (s3-api) {:op :ListBuckets})
      (aws/invoke (s3-api) {:op :ListObjectsV2
                            :request {:Bucket bucket
                                      :Prefix prefix}})))))

(defn get-object
  [path]
  (let [[b p] (bucket-prefix path)]
    (-> (s3-api)
        (aws/invoke {:op :GetObject
                     :request {:Bucket b
                               :Key p}})
        :Body)))
