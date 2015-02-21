(ns ya.handlers.auth.twitter
  (:require
    [clojure.core.cache :as cache]
    [ring.util.response :refer [resource-response response redirect]]
    [somnium.congomongo :as m]
    [oauth.client :as oauth]
    [slingshot.slingshot :refer [try+]]
    [environ.core :refer [env]]
    
    [ya.models.users :as users]
    [ya.server :as server]
    [ya.path :as path]
    [ya.handlers.auth.oauth :as y-oauth]))


(def ^:private consumer-token (env :twitter-consumer-token))
(def ^:private consumer-token-secret (env :twitter-consumer-token-secret))
(def ^:private callback-uri (str server/host path/twitter-callback))

(if-not consumer-token
  (.println *err* "環境変数 twitter-consumer-token が設定されていません。"))
(if-not consumer-token-secret
  (.println *err* "環境変数 twitter-consumer-token-secret が設定されていません。"))


(def ^:private consumer
  (oauth/make-consumer
    consumer-token
    consumer-token-secret
    "https://api.twitter.com/oauth/request_token"
    "https://api.twitter.com/oauth/access_token"
    "https://api.twitter.com/oauth/authorize"
    :hmac-sha1))


; アクセストークンを一定時間保管する
(def ^:private request-tokens (y-oauth/make-request-token-store))

(defn- make-request-token []
  (let [token (oauth/request-token consumer callback-uri)]
    (swap! request-tokens assoc (:oauth_token token) token)
    token))

(defn- restore-request-token [token]
  (cache/lookup @request-tokens token))

; GET /oauth/twitter/login
(defn login [session]
  (if (:user session)
    (redirect path/loggedin)
    
    (let [request-token (make-request-token)
          oauth-token (:oauth_token request-token)
          approval-uri (oauth/user-approval-uri consumer oauth-token)]
      (redirect approval-uri))))

; GET /oauth/twitter/callback?oauth_token=TOKEN&oauth_verifier=VERIFIER
(defn callback [token verifier]
  (let [request-token (restore-request-token token)]
    (if-not request-token
      (response {:err "処理がタイムアウトしました。"})
      (try+
        (let [access-token (oauth/access-token consumer request-token verifier)]
          (println access-token)
          (let [id (:user_id access-token)
                name (:screen_name access-token)
                user (users/find-or-create-by-twitter id name)]
            (-> (response {:ok "認証に成功しました。", :user user})
                (assoc :session { :user user }))))
        (catch [:status 401] {:keys [request-time headers body]}
          (response { :err "認証に失敗しました。" }))))))