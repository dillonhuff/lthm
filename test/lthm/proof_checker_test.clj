(ns lthm.proof-checker-test
  (:require [clojure.test :refer :all]
            [lthm.core :refer :all]
            [lthm.proof-checker :refer :all]))

(deftest proof-checker-tests
  (testing "axiom recognition"
    (is (=
         (build-ax-eql (eql (v "a") (v "a")))
         (ax-eql (v "a"))))
    (is (=
         (build-ax-pqp (imp (pred "f" []) (imp (pred "g" []) (pred "f" []))))
         (ax-pqp (pred "f" []) (pred "g" [])))))

  (testing "axiom recognition in simple proofs"
    (is (=
         (proof? (claimed-proof (list (eql (v "a") (v "a")))))
         true))

    (is (=
         (proof? (claimed-proof (list (imp (pred "p" []) (imp (pred "q" []) (pred "p" []))))))
         true))
    (is (=
         (proof? (claimed-proof (list (pred "f" []))))
         false))

    (is (=
         (proof? (claimed-proof '()))
         true))))
