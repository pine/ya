(ns ya.models.users
  (:require
    [somnium.congomongo :as m]
    
    [ya.mongo :as db]))

(defrecord User [_id name twitterId])

(defn create-by-twitter [id name]
  (let [user (m/insert! :users {:twitterId id, :name name})]
    (when user
      (-> user db/str-id-seq map->User))))

(defn find-by-twitter [id]
  (let [user (m/fetch-one :users :where {:twitterId id})]
    (when user
      (-> user db/str-id-seq map->User))))

(defn find-or-create-by-twitter [id name]
  (or (find-by-twitter id)
      (create-by-twitter id name)))