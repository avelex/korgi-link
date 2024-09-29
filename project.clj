(defproject korgi-url "0.1.0"
  :description "URL Shortener Service"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [http-kit "2.8.0"]
                 [compojure "1.7.1"]
                 [metosin/jsonista "0.3.11"]
                 [com.github.igrishaev/pg2-core "0.1.17"]
                 [ring-logger "1.1.1"]
                 [ring/ring-core "1.12.2"]
                 [environ "1.2.0"]]
  :main ^:skip-aot korgi-url.core
  :aot [korgi-url.core])
