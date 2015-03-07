(ns lthm.core
  (:gen-class))

(defstruct variable :name)

(defstruct function :name :args)

(defn v [name]
  (struct variable name))

(defn func [name args]
  (struct function name args))
