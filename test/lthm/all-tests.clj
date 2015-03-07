(ns lthm.all-tests
  (:require [clojure.test :refer :all]
            [lthm.core :refer :all]
            [lthm.core-test :refer :all]))

(defn all-tests []
  (run-tests 'lthm.core-test))
