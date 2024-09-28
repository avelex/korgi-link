(ns korgi-url.repo
  (:require [pg.core :as pg]
            [korgi-url.config :refer [config]]))

(def db-conn
  (pg/connect (config :db)))


(defn save-url [url hash]
  (pg/execute db-conn
              "INSERT INTO url (original_url, hash) VALUES ($1, $2)
              ON CONFLICT (original_url) DO NOTHING
              RETURNING hash"
              {:params [url hash]}))

(defn get-url-by-hash [hash]
  (-> (pg/execute db-conn
              "SELECT original_url FROM url WHERE hash = $1"
              {:params [hash]})
      (first)
      (:original_url)))