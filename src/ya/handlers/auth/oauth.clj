(ns ya.handlers.auth.oauth
  (:require
    [clojure.core.cache :as cache]))

; OAuth の RequestToken の有効期限
(def request-token-timeout (* 1000 60))

(defn make-request-token-store []
  (atom (cache/ttl-cache-factory {} :ttl request-token-timeout)))
