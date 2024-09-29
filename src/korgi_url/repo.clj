(ns korgi-url.repo
  (:require [next.jdbc :as jdbc]))

(def db-pool (atom nil))

(defn connect-to-db [spec]
  (reset! db-pool (jdbc/get-datasource spec)))

(defn save-url [url hash]
  (jdbc/execute! @db-pool
                 ["INSERT INTO url (original_url, hash) VALUES (?, ?)
                   ON CONFLICT (original_url) DO NOTHING
                   RETURNING hash" url hash]))

(defn get-url-by-hash [hash]
  (let [result (jdbc/execute! @db-pool
                               ["SELECT original_url FROM url WHERE hash = ?" hash])]
    (when (seq result)
      (:original_url (first result)))))