(ns ya.handlers.storage
  (:require
    [ring.util.response :refer [response redirect]]
    [cheshire.core :refer [parse-string]]
    
    [ya.handlers.auth.session :as session]
    [ya.models.storage :as storage]))

(defn fetch [user]
  (cond
    user (response {:storages (storage/fetch (:_id user))})
    :else (session/require-login)))

(defn insert [user provider credential]
  (println user provider credential)
  (cond
    (empty? user) (session/require-login)
    (empty? provider) (response {:err "クラウドプロバイダー名が必要です。"})
    (empty? credential) (response {:err "認証情報が必要です。"})
    :else (response {:ok "ストレージの作成に成功しました。",
                     :storage (storage/insert (:_id user) provider credential)})))

(defn destroy [user provider]
  (cond
    (empty? user) (session/require-login)
    (empty? provider) (response {:err "クラウドプロバイダー名が必要です。"})
    :else (response {:ok "削除に成功しました"})))