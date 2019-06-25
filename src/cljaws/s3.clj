(ns cljaws.s3
  (:require
   [cognitect.aws.client.api :as aws]
   [cljaws.util :as u]
   [clojure.string :as str]))

(defonce ^:private s3-api- (atom nil))

(defn s3-api
  ([] (s3-api nil))
  ([reset]
   (or (and (not reset) @s3-api-)
       (doto (aws/client {:api :s3})
         (->> (reset! s3-api-))))))

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
