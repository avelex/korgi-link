(ns korgi-url.core
  (:require [org.httpkit.server :as http]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.logger :as logger]
            [korgi-url.handler :as handler]))

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
  (start-server 8090))