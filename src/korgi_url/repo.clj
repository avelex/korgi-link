(ns korgi-url.repo
  (:require [pg.core :as pg]
            [pg.pool :as pool]))

(def db-pool (atom nil))

(defn connect-to-db [config]
  (reset! db-pool (pool/pool config)))

(defn save-url [url hash]
  (let [conn (pool/borrow-connection db-pool)]
    (pg/execute conn
                "INSERT INTO url (original_url, hash) VALUES ($1, $2)
                ON CONFLICT (original_url) DO NOTHING
                RETURNING hash"
                {:params [url hash]})))

(defn get-url-by-hash [hash]
  (let [conn (pool/borrow-connection db-pool)]
    (-> (pg/execute conn
                    "SELECT original_url FROM url WHERE hash = $1"
                    {:params [hash]})
        (first)
        (:original_url))))