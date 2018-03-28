(ns glp.core
  (:require [clojure.core.logic :refer :all]
            [clojure.core.logic.fd :as fd]
            [clojure.core.logic.pldb :as db])
  (:refer-clojure :exclude [==]))

; Functional quicksort:

(defn quicksort
  [l]
  (if (empty? l)
    '()
    (let [[h & t] l
          {:keys [s e]} (reduce (fn [m e]
                                  (update m (if (< e h) :s :e) conj e))
                                {:s '(), :e '()}
                                t)]
      (concat (sort s) (cons h (sort e))))))

; Relational quicksort:

(defn partitiono
  [l p o1 o2]
  (conde
    [(fresh [first rest
             rest-o1 rest-o2]
       (firsto l first)
       (resto l rest)
       (conde [(fd/< first p)
               (firsto o1 first)
               (resto o1 rest-o1)
               (== o2 rest-o2)]
              [(fd/>= first p)
               (== o1 rest-o1)
               (firsto o2 first)
               (resto o2 rest-o2)])
       (partitiono rest p rest-o1 rest-o2))]
    [(emptyo l) (emptyo o1) (emptyo o2)]))

(defn quicksorto
  [list sorted]
  (conde
    [(fresh [pivot rest left right
             sorted-left sorted-right
             pivot-sorted-right]
       (firsto list pivot)
       (resto list rest)
       (partitiono rest pivot left right)
       (quicksorto left sorted-left)
       (quicksorto right sorted-right)
       (conso pivot sorted-right pivot-sorted-right)
       (appendo sorted-left pivot-sorted-right sorted))
     [(emptyo list) (emptyo sorted)]]))

(let [l (repeatedly 100 #(rand-int 1000))]
  (time (quicksort l))
  (time (first (run 1 [q] (quicksorto l q))))
  nil)
