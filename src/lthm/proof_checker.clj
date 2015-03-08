(ns lthm.proof-checker
  (:require [lthm.core :refer :all]
            [lthm.utils :refer :all])
  (:gen-class))

(defstruct proof-outline :already-proved :statements)

(defn statements [out]
  (:statements out))

(defn next-statements [out]
  (pop (statements out)))

(defn next-line [out]
  (peek (statements out)))

(defn top-formula-to-thm [thm outline]
  (struct proof-outline (conj (statements outline) thm) (next-statements outline)))

(defstruct proof-check-result :result :theorems)

(defn failed-proof [outline]
  (struct proof-check-result 'failed (:already-proved outline)))

(defn empty-proof []
  (struct proof-check-result 'succeeded nil))

(defn proof [outline]
  (if (= 0 (count (:already-proved outline)))
    (empty-proof)
    (struct proof-check-result 'succeeded (reverse (:already-proved outline)))))

(defn proof-construction-failed? [res]
  (= (:result res) 'failed))

(defn claimed-proof [statements]
  (struct proof-outline '() statements))

(defn num-lines [pf]
  (count (:statements pf)))

(defn tap [form]
  (if (implication? form)
    true
    false))

(defn build-ax-pqp [form]
  (if (implication? form)
    (let [p (left form)
          qImpP (right form)]
      (if (implication? qImpP)
        (let [q (left qImpP)
              other-p (right qImpP)]
          (if (= other-p p)
            (ax1 p q)
            (failed-thm form)))
        (failed-thm form)))
    (failed-thm form)))

(defn build-ax-eql [form]
  (if (eql? form)
    (if (= (pred-arg form 0) (pred-arg form 1))
      (ax-eql (pred-arg form 0))
      (failed-thm form))
    (failed-thm form)))

;; Axiom construction
(def fol-axiom-builders
  (list
   build-ax-eql
   build-ax-pqp))

(defn make-fol-axiom [formula]
  (seq-thm-builder fol-axiom-builders formula))

;; Proof construction
(defn construct-proof [outline]
  (if (= 0 (num-lines outline))
    (proof outline)
    (let [ax-res (make-fol-axiom (next-line outline))]
      (if (thm? ax-res)
        (construct-proof (top-formula-to-thm ax-res outline))
        (failed-proof outline)))))

(defn proof? [outline]
  (let [res (construct-proof outline)]
    (if (proof-construction-failed? res)
      false
      true)))

    
