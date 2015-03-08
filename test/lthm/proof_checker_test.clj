(ns lthm.proof-checker-test
  (:require [clojure.test :refer :all]
            [lthm.core :refer :all]
            [lthm.proof-checker :refer :all]))

(deftest proof-checker-tests
  (testing "axiom recognition"
    (is (=
         (proof? (claimed-proof (list (imp (pred "p" []) (imp (pred "q" []) (pred "p" []))))))
         true))
    (is (=
         (proof? (claimed-proof (list (pred "f" []))))
         false))

    (is (=
         (proof? (claimed-proof '()))
         true))))
