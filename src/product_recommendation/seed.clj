(ns product-recommendation.seed
  (:require [product-recommendation.db :as db])
  (:gen-class))

(def products [{:id 0 :name "TV" :year 2019 :price 399.90 :color "black"}
               {:id 1 :name "PC" :year 2021 :price 559.90 :color "silver"}
               {:id 2 :name "Smartphone" :year 2020 :price 199.90 :color "black"}
               {:id 3 :name "Mug" :year 2016 :price 2.99 :color "white"}
               {:id 4 :name "Chair" :year 2017 :price 25.99 :color "wood"}
               {:id 5 :name "Plate" :year 2020 :price 3.99 :color "pink"}])

(def clients [{:id 0 :name "Zoe" :gender "F" :birthyear 1995}
              {:id 1 :name "Greta" :gender "NB" :birthyear 1992}
              {:id 2 :name "Sundar" :gender "M" :birthyear 1997}
              {:id 3 :name "Otto" :gender "NB" :birthyear 2000}
              {:id 4 :name "Niamh" :gender "F" :birthyear 1998}])

(def brands [{:id 0 :name "Huwaei"}
             {:id 1 :name "IKEA"}
             {:id 2 :name "Xiaomi"}])

(def product-brands [{:product_id 0 :brand_id 0}
                     {:product_id 1 :brand_id 2}
                     {:product_id 2 :brand_id 2}
                     {:product_id 3 :brand_id 1}
                     {:product_id 4 :brand_id 1}
                     {:product_id 5 :brand_id 1}])

(def views [{:client_id 0 :product_id 0}
            {:client_id 0 :product_id 3}
            {:client_id 0 :product_id 5}
            {:client_id 1 :product_id 0}
            {:client_id 1 :product_id 2}
            {:client_id 1 :product_id 3}
            {:client_id 2 :product_id 3}
            {:client_id 2 :product_id 4}
            {:client_id 3 :product_id 3}
            {:client_id 3 :product_id 0}
            {:client_id 4 :product_id 3}])

(def searches [{:client_id 0 :product_id 0}
               {:client_id 0 :product_id 2}
               {:client_id 0 :product_id 5}
               {:client_id 1 :product_id 5}
               {:client_id 1 :product_id 4}
               {:client_id 1 :product_id 5}
               {:client_id 2 :product_id 1}
               {:client_id 2 :product_id 5}])

(def wishlists [{:client_id 0 :product_id 0}
                {:client_id 0 :product_id 3}
                {:client_id 1 :product_id 0}
                {:client_id 2 :product_id 4}
                {:client_id 2 :product_id 5}
                {:client_id 5 :product_id 4}])

(def ratings [{:client_id 0 :product_id 1 :rating 3.5}
              {:client_id 0 :product_id 4 :rating 1.0}
              {:client_id 1 :product_id 1 :rating 5.0}
              {:client_id 2 :product_id 2 :rating 4.5}
              {:client_id 3 :product_id 2 :rating 3.0}])

(defn seed! []
  (db/transact db/reset-db!)
  (map #(db/transact db/add-brand {:brand %}) brands)
  (map #(db/transact db/add-client {:client %}) clients)
  (map #(db/transact db/add-product {:product %}) products)
  (map #(db/transact db/link-to-brand %) product-brands)
  (map #(db/transact db/add-view %) views)
  (map #(db/transact db/add-search %) searches)
  (map #(db/transact db/add-to-wishlist %) wishlists)
  (map #(db/transact db/add-rating %) ratings))
