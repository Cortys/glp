(ns glp.core
  (:require [clojure.core.logic :refer :all])
  (:refer-clojure :exclude [==]))

(run* [q]
  (== q true))
