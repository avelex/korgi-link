(ns korgi-url.config
  (:require [environ.core :refer [env]]))

(def config (atom {}))

(defn load-env []
  (reset! config
    {:base "https://korgi.link/"
     :db {:url (env :database-url)}}))