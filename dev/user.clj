(ns user
  (:require [clojure.tools.namespace.repl :as ctnr]
            [clojure.stacktrace :refer :all]
            [proto-repl.saved-values]))

(defn start
  []
  (require '[glp.core])
  (println "REPL started."))

(defn refresh
  []
  (ctnr/refresh :after 'user/start))

(defn refresh-all
  []
  (ctnr/refresh-all :after 'user/start))
