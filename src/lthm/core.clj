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

(defn pred-name [pred]
  (:name pred))

(defn pred-arguments [pred]
  (:args pred))

(defn pred-arg [term n]
  (if (pred? term)
    (nth (pred-arguments term) n)
    "Error: Input to arg is not a predicate"))

(defn imp [left right]
  (struct binop 'implication left right))

(defn eql [left right]
  (pred "eql" [left, right]))

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

(defn eql? [obj]
  (and (pred? obj) (= (pred-name obj) "eql")))

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
  (= (:res obj) 'succeeded))

;; Theorem creation
(defn ax-pqp [p q]
  (thm (imp p (imp p q))))

(defn ax-eql [t]
  (if (term? t)
    (thm (eql t t))
    (failed-thm 'failed t)))
