(ns glp.core
  (:require [clojure.core.logic :refer :all]
            [clojure.core.logic.fd :as fd]
            [clojure.core.logic.pldb :as db])
  (:refer-clojure :exclude [==]))

(def x 1)
(def l [1 2 3 1])

(run* [q]
  (conde
    [(firsto l x) (resto l q)]
    [(fresh [y] (firsto l y) (!= x y) (== l q))]))

(run* [q]
  (!= q 1) (!= q 2))

(defn orderedo [l]
  (matche [l]
    ([[f . r]]
     (fresh [s]
       (firsto r s)
       (fd/<= f s)
       (orderedo r)))
    ([[_]] succeed)
    ([[]] succeed)))

(let [l (repeatedly 7 #(rand-int 100))]
  (println l)
  (time (first (run 1 [q]
                 (permuteo l q)
                 (orderedo q)))))
