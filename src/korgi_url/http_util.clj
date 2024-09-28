(ns korgi-url.http-util
  (:require [ring.util.response :as response]))

(defn render-ok [body]
  (-> (response/response body)
      (response/content-type "application/json")))
