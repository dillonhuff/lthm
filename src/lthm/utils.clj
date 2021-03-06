(ns lthm.utils
  (:require [lthm.core :refer :all])
  (:gen-class))

(defn seq-thm-builder [thm-builders form]
  (if (= 0 (count thm-builders))
    (failed-thm 'no-builders-succeeded)
    (let [res (apply (peek thm-builders) (list form))]
      (if (thm? res)
        res
        (recur (pop thm-builders) form)))))
