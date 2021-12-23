(ns product-recommendation.db
  (:require [neo4j-clj.core :as neo4j])
  (:import java.net.URI)
  (:gen-class))

(def db (neo4j/connect (URI. "db-uri") "user" "password]"))

(neo4j/defquery reset-db!
  "MATCH (r) DETACH DELETE r")

(neo4j/defquery add-brand
  "CREATE (c:brand $brand) RETURN c as brand")

(neo4j/defquery add-client
  "CREATE (c:client $client) RETURN c as client")

(neo4j/defquery add-product
  "CREATE (p:product $product) RETURN p as product")

(neo4j/defquery get-products
  "MATCH (p:product) RETURN p as product")

(neo4j/defquery add-view
  "MATCH (c:client {id: $client_id}), (p:product {id: $product_id})
   CREATE (c)-[r:VISUALIZES]->(p)
   RETURN type(r)")

(neo4j/defquery add-search
  "MATCH (c:client {id: $client_id}), (p:product {id: $product_id})
   CREATE (c)-[r:SEARCHES]->(p)
   RETURN type(r)")

(neo4j/defquery add-to-wishlist
  "MATCH (c:client {id: $client_id}), (p:product {id: $product_id})
   CREATE (c)-[r:WISHES]->(p)
   RETURN type(r)")

(neo4j/defquery add-rating
  "MATCH (c:client {id: $client_id}), (p:product {id: $product_id})
   CREATE (c)-[r:RATES {rating: $rating}]->(p)
   RETURN type(r)")

(neo4j/defquery link-to-brand
  "MATCH (b:brand {id: $brand_id}), (p:product {id: $product_id})
   CREATE (p)-[r:BELONGS_TO]->(b)
   RETURN type(r)")

(neo4j/defquery connect-clients
  "MATCH (c:client {id: $client_a_id}), (p:client {id: $client_b_id})
   CREATE (c)-[r:RELATES_TO]->(p)
   RETURN type(r)")

(neo4j/defquery views-by-client
  "MATCH (c:client)-[VISUALIZES]->(p)
   WHERE c.id = $client_id
   RETURN p as product")

(defn transact
  ([db-fn]
   (transact db-fn {}))
  ([db-fn data]
   (with-open [session (neo4j/get-session db)]
     (let [results (db-fn session data)]
       (println "***" results)
       (doall results)))))
