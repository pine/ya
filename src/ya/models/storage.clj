(ns ya.models.storage
  (:require
    [somnium.congomongo :as m]
    
    [ya.mongo :as db]))

(defrecord Storage [_id userId provider credential])

(defn fetch [id]
  (let [storages (m/fetch :storages
                          :where {:userId (db/obj-id id)})]
    (->> storages db/str-id-seq (map map->Storage))))

(defn insert-or-update [exec user provider credential]
  (let [data {:userId (db/obj-id user)
              :provider provider,
              :credential credential}]
    (->> (exec :storages data)
         db/str-id-seq map->Storage)))

(def insert (partial insert-or-update m/insert!))
(def update (partial insert-or-update m/update!))

;(defn replace [user provider credential])

(defn destroy [userId provider]
  (m/destroy! :storages {:userId (db/obj-id userId)
                         :provider provider}))