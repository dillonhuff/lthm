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
    (is (not (= (func "g" [v "b"]) (func "k" [v "b"]))))
    (is (=
         (func "f" [v "John", v "Kate"])
         (func "f" [v "John", v "Kate"]))))

  (testing "variable type"
    (is (= (v? (v "a")) true))
    (is (= (v? (func "f" [])) false)))

  (testing "function type"
    (is (= (func? (func "f" [v "b"])) true))
    (is (= (func? [v "asdf"]) false)))

  (testing "atom creation"
    (is (= (pred "nope" [v "k"]) (pred "nope" [v "k"])))
    (is (not (= (pred "nope" [v "l", v "k"]) (pred "nope" [v "l", v "k", v "s"]))))))
