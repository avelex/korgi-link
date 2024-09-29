(ns korgi-url.repo
  (:require [next.jdbc :as jdbc]
            [next.jdbc.plan :as plan]))

(def db-pool (atom nil))

(defn connect-to-db [spec]
  (reset! db-pool (jdbc/get-datasource spec)))

(defn save-url [url hash]
  (jdbc/execute! @db-pool
                 ["INSERT INTO url (original_url, hash) VALUES (?, ?)
                   ON CONFLICT (original_url) DO NOTHING
                   RETURNING hash" url hash]))

(defn get-url-by-hash [hash]
  (plan/select-one! @db-pool :original_url ["SELECT original_url FROM url WHERE hash = ?" hash]))