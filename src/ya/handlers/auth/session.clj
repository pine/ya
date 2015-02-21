(ns ya.handlers.auth.session
  (:require
    [ring.util.response :refer [response redirect]]
    [ya.path :as path]))

(defn logout []
  (-> (response { :ok "ログアウトに成功しました。" })
      (assoc :session {})))

(defn status [session]
  (response (select-keys session [:user])))

(defn require-login []
  (response {:err "ログインが必要です"}))