(ns lthm.all-tests
  (:require [clojure.test :refer :all]
            [lthm.core :refer :all]
            [lthm.core-test :refer :all]
            [lthm.proof-checker-test :refer :all]))

(run-tests 'lthm.core-test 'lthm.proof-checker-test)
