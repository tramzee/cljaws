(ns cljaws.util
  (:require [cognitect.aws.client.api :as aws]))

(defn ops
  [client]
  (-> (aws/ops client)
      keys
      sort))

(defn doc
  [client op]
  (aws/doc client (keyword op)))
