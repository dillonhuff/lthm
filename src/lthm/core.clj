(ns lthm.core
  (:gen-class))

(defstruct variable :type :name)

(defstruct function :type :name :args)

(defn- datatype [core-struct]
  (:type core-struct))

(defn v [name]
  (struct variable 'variable name))

(defn func [name args]
  (struct function 'function name args))

;; Type testing
(defn v? [obj]
  (= (datatype obj) 'variable))

(defn func? [obj]
  (= (datatype obj) 'function))
