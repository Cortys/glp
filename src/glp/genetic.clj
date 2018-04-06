(ns glp.genetic
  (:require [push.core :as push]
            [push.interpreter.core :as interpreter]))

(defn winner
  [field]
  (let [c (count field)
        size (int (Math/sqrt c))
        rows (partition size field)
        columns (map (fn [i]
                       (map (partial get field)
                            (range i c size)))
                     (range size))
        diagonals [(map #(get field (+ % (* % size)))
                        (range size))
                   (map #(get field (+ (- size 1 %) (* % size)))
                        (range size))]
        checks (concat rows columns diagonals)]
    (some #(when (= (count (set %)) 1) (first %))
          checks)))

(defn empty-field
  [size]
  (vec (repeat nil (* size size))))

(defn make-move
  [field player position]
  (when (nil? (get field position))
    (assoc field position player)))

(def runner (push/interpreter))

(def prog [1 2 :scalar-add])

(def res (push/run runner prog 1000))
