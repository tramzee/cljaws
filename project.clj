(defproject cljaws "0.3.2-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[clojure-term-colors         "0.1.0"]
                 [com.cognitect.aws/api       "0.8.305"]
                 [com.cognitect.aws/autoscaling "712.2.426.0"]
                 [com.cognitect.aws/cloudformation "717.2.442.0"]
                 [com.cognitect.aws/dynamodb  "722.2.470.0"]
                 [com.cognitect.aws/ec2       "723.2.476.0"]
                 [com.cognitect.aws/endpoints "1.1.11.568"]
                 [com.cognitect.aws/iam       "722.2.468.0"]
                 [com.cognitect.aws/s3        "722.2.468.0"]
                 [com.cognitect.aws/sqs       "697.2.391.0"]
                 [com.cognitect.aws/sts       "722.2.464.0"]
                 [com.cognitect.aws/sns       "718.2.444.0"]
                 [org.clojure/clojure         "1.10.1"]
                 [org.clojure/java.jdbc       "0.7.9"]
                 [org.ini4j/ini4j             "0.5.4"]
                 [org.xerial/sqlite-jdbc      "3.27.2.1"]]

  :profiles
  {:repl {:dependencies [[nrepl "0.6.0"]
                         [cider/piggieback "0.4.1"]]
          :plugins [[cider/cider-nrepl "0.21.1"]
                    [refactor-nrepl "2.4.0"]]}}
  :repl-options {:init-ns cljaws.core})
