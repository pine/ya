(ns ya.mongo
  (:require
    [clojure.walk :as w]
    [somnium.congomongo :as m])
  (:import [org.bson.types ObjectId]))

(def db-name "ya")
(def host "localhost")
(def port 27017)

(def conn
  (m/make-connection db-name :host host :port port))

(defn init! []
  (m/set-connection! conn))

(defn obj-id [id]
  (if id (ObjectId. id)))

(defn obj-id-to-str [id]
  (if (instance? ObjectId id) (str id) id))

(defn str-id-seq [coll]
  (when coll
    (w/postwalk obj-id-to-str coll)))