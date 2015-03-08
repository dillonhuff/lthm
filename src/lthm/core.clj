(ns lthm.core
  (:gen-class))

;; Term creation
(defstruct variable :type :name)
(defstruct function :type :name :args)

;; Formula creation
(defstruct predicate :type :name :args)
(defstruct binop :type :left :right)
(defstruct unop :type :arg)
(defstruct quantifier :type :variable :arg)

(defn left [bop]
  (:left bop))

(defn right [bop]
  (:right bop))

;; Theorem creation
(defstruct theorem :res :formula)
(defstruct failed-theorem :res :fail-condition)

(defn failed-thm [form]
  (struct failed-theorem 'failed form))

(defn- thm [form]
  (struct theorem 'succeeded form))

(defn- datatype [core-struct]
  (:type core-struct))

(defn v [name]
  (struct variable 'variable name))

(defn func [name args]
  (struct function 'function name args))

;; Formula creation

(defn pred [name term-list]
  (struct predicate 'predicate name term-list))

(defn imp [left right]
  (struct binop 'implication left right))

;; Theorem creation
(defn ax1 [p q]
  (thm (imp p (imp p q))))


;; Type testing

(defn- type-is? [t obj]
  (= (datatype obj) t))

(defn v? [obj]
  (type-is? 'variable obj))

(defn func? [obj]
  (type-is? 'function obj))

(defn pred? [obj]
  (type-is? 'predicate obj))

(defn implication? [obj]
  (type-is? 'implication obj))

(defn- binop? [obj]
  (or (implication? obj)))

(defn- unop? [obj]
  (type-is? 'unop obj))

(defn quantifier? [obj]
  (type-is? 'quantifier obj))

(defn formula? [obj]
  (or (pred? obj)
      (binop? obj)
      (unop? obj)
      (quantifier? obj)))

(defn term? [obj]
  (or (v? obj)
      (func? obj)))

(defn thm? [obj]
  (= (:res obj) 'thm))
