(ns lthm.proof-checker-test
  (:require [clojure.test :refer :all]))

(deftest proof-checker-tests
  (testing "axiom recognition"
    (is (= false true))))
