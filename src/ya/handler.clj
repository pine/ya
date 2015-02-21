(ns ya.handler
  (:require
    [compojure.core :refer :all]
    [compojure.route :as route]
    [compojure.handler :as handler]
    [ring.middleware.json :as json]
    [ring.middleware.session :as session]

    [ya.mongo :as db]
    [ya.routes :refer [app-routes]]))

(db/init!)

(def app
  (-> (handler/api app-routes)
      (session/wrap-session)
      (json/wrap-json-body)
      (json/wrap-json-params)
      (json/wrap-json-response)))