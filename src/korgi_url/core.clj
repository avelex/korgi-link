(ns korgi-url.core
  (:gen-class)
  (:require [org.httpkit.server :as http]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.logger :as logger]
            [korgi-url.handler :as handler]
            [korgi-url.repo :as repo]
            [korgi-url.config :as config]))

(defroutes app-routes
           (GET "/:short" [short] (handler/get-original-url short))
           (POST "/create" req (handler/create-short-url req))
           (route/not-found "<h1>Page not found</h1>"))

(def app
  (-> app-routes
      (logger/wrap-with-logger)))

(defn start-server [port]
  (http/run-server app {:port port})
  (println (str "Start listening server on http://localhost:" port)))

(defn -main []
  (repo/connect-to-db (config/config :db))
  (start-server 8090))