(ns korgi-url.config
  (:require [environ.core :refer [env]]))

(def db-host (env :database-host))
(def db-port (env :database-port))
(def db-user (env :database-user))
(def db-password (env :database-password))
(def db-name (env :database-name))


(def config
  {:base "https://korgi.link/"
   :db {:host db-host
        :port db-port
        :user db-user
        :password db-password
        :database db-name}})