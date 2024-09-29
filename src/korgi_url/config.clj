(ns korgi-url.config
  (:require [environ.core :refer [env]]))

(def config (atom {}))

(defn load-env []
  (reset! config
    {:base "https://korgi.link/"
     :db {:host (env :database-host)
          :port (Integer/parseInt (env :database-port))
          :user (env :database-user)
          :password (env :database-password)
          :database (env :database-name)
          :use-ssl? (Boolean/parseBoolean (env :database-ssl))}}))