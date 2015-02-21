(ns ya.server
  (:require
    [environ.core :refer [env]]))

(def scheme (env :server-scheme))
(def hostname (env :server-hostname))

; 末尾に / を付けないこと
(def uri (str scheme "://" hostname))
