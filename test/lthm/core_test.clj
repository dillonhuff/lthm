(ns lthm.core-test
  (:require [clojure.test :refer :all]
            [lthm.core :refer :all]))

(deftest core-tests
  (testing "variable creation"
    (is (= (v "a") (v "a")))
    (is (not (= (v "a") (v "B")))))

  (testing "function creation"
    (is (= (func "f" []) (func "f" [])))
    (is (not (= (func "f" [v "a"]) (func "f" [v "b"]))))
    (is (not (= (func "g" [v "b"]) (func "k" [v "b"]))))))
