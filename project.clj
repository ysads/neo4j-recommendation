(defproject product-recommendation "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[faker "0.3.2"]
                 [gorillalabs/neo4j-clj "4.1.2"]
                 [org.clojure/clojure "1.10.3"]]
  :repl-options {:init-ns product-recommendation.core})
