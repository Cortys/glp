(defproject glp "0.1.0-SNAPSHOT"
  :description "Experiments with genetic logic programming."

  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/core.logic "0.8.11"]]

  :plugins [[lein-codox "0.10.3"]]
  :codox {:output-path "docs"}

  :main glp.core

  :profiles {:dev {:source-paths ["dev" "src"]
                   :dependencies [[org.clojure/tools.namespace "0.2.11"]
                                  [clj-stacktrace "0.2.8"]
                                  [proto-repl "0.3.1"]
                                  [proto-repl-charts "0.3.1"]
                                  [proto-repl-sayid "0.1.3"]
                                  [com.cemerick/pomegranate "0.3.1"]]
                   :repl-options {:init-ns user
                                  :init (start)
                                  :caught clj-stacktrace.repl/pst+}
                   :eastwood {:exclude-linters [:constant-test :def-in-def]}}
             :uberjar {:aot :all}})
