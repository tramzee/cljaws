(ns cljaws.util
  (:require [cognitect.aws.client.api :as aws]
            [cognitect.aws.credentials :as cred]))

(defn ops
  [client]
  (-> (aws/ops client)
      keys
      sort))

(defn doc
  [client op]
  (aws/doc client (keyword op)))

(def ^:private _creds (atom (cred/profile-credentials-provider)))

(defn credentials
  ([] (credentials nil))
  ([profile]
   (or (and (nil? profile) @_creds)
       (reset! _creds (cred/chain-credentials-provider
                       [(cred/environment-credentials-provider)
                        (cred/system-property-credentials-provider)
                        (cred/profile-credentials-provider (or profile "default"))
                        (cred/container-credentials-provider)
                        (cred/instance-profile-credentials-provider)])))))
