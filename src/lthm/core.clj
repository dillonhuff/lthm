(ns lthm.core
  (:gen-class))

;; Term creation
(defstruct variable :type :name)

(defstruct function :type :name :args)

(defstruct predicate :type :name :args)

(defstruct binop :type :left :right)

(defstruct unop :type :arg)

(defstruct quantifier :type :variable :arg)

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

;; Type testing

(defn- type-is? [t obj]
  (= (datatype obj) t))

(defn v? [obj]
  (type-is? 'variable obj))

(defn func? [obj]
  (type-is? 'function obj))

(defn pred? [obj]
  (type-is? 'predicate obj))

(defn term? [obj]
  (or (v? obj)
      (func? obj)))
