(ns ya.routes
  (:require
    [compojure.core :refer [defroutes GET POST DELETE]]
    [compojure.route :as route]
            
    [ya.handlers.auth.twitter :as twitter]
    [ya.handlers.auth.session :as session]
    
    [ya.handlers.storage :as storage]))

(defroutes app-routes
  (GET "/" [] "ya works")
  
  ; 認証関係
  (GET "/auth/logout"
       []
       (session/logout))
  
  (GET "/auth/status"
       {session :session}
       (session/status session))
  
  (GET "/auth/twitter/login"
       {session :session}
       (twitter/login session))
  
  (GET "/auth/twitter/callback"
       [oauth_token oauth_verifier]
       (twitter/callback oauth_token oauth_verifier))
  
  ; クラウドストレージ関係
  (GET "/storages"
       {{user :user} :session}
       (storage/fetch user))
  
  (POST "/storages"
        {{user :user} :session
         {provider :provider, credential :credential} :params}
        (storage/insert user provider credential))
  
  (DELETE "/storages/:provider"
        {{user :user} :session
         {provider :provider} :params}
        (storage/destroy user provider))
  
  (route/not-found "Not Found"))