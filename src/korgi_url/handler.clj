(ns korgi-url.handler
  (:require [jsonista.core :as json]
            [ring.util.response :refer [redirect]]
            [korgi-url.config :as config]
            [korgi-url.http-util :as http-util]
            [korgi-url.config :as config]
            [korgi-url.repo :as repo])
  (:import (java.security MessageDigest)))

(defn sha256 [string]
  (let [digest (.digest (MessageDigest/getInstance "SHA-256") (.getBytes string "UTF-8"))]
    (apply str (map (partial format "%02x") digest))))

(defn get-or-create-short-url [url]
  (let [hash (subs (sha256 url) 0 8)]
    (repo/save-url url hash)
    (str (@config/config :base) hash)))

(defn render-json [short-url]
  (json/write-value-as-string {"url" short-url}))

(defn fetch-long-url [body]
  (let [parsed (json/read-value body)]
    (if-let [url (get parsed "url")]
      url
      (throw (ex-info "url not found in request" {:body body})))))

(defn create-short-url [req]
  (-> (fetch-long-url (get req :body))
      (get-or-create-short-url)
      (render-json)
      (http-util/render-ok)))

(defn get-original-url [short-url]
  (-> (repo/get-url-by-hash short-url)
      (redirect)))