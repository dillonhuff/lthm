(ns lthm.proof-checker-test
  (:require [clojure.test :refer :all]
            [lthm.core :refer :all]
            [lthm.proof-checker :refer :all]))

(deftest proof-checker-tests
  (testing "axiom recognition"
    (is (=
         true
         false))))
;;         (is-proof-correct? (proof (list (imp (v "p") (imp (v "q") (v "p"))))))))))