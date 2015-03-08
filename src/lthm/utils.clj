(ns lthm.utils
  (:require [lthm.core :refer :all])
  (:gen-class))

(defn seq-thm-builder [thm-builders form]
  (if (= 0 (count thm-builders))
    (failed-thm form)
    (let [res (apply (peek thm-builders) form)]
      (if (thm? res)
        res
        (recur (pop thm-builders) form)))))
