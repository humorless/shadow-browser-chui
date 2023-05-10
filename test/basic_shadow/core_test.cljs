(ns basic-shadow.core-test
  (:require
   [cljs.core.async :refer [go <!]]
   [cljs.core.async.interop :refer [<p!]]
   [kitchen-async.promise :as p]
   [lambdaisland.fetch :as fetch]
   [clojure.test :refer [deftest testing is async]]))

(deftest passing-tests
  (is (= 1 (inc 0)))

  (is (pos-int? 5))

  (is (thrown? cljs.core/ExceptionInfo (throw (ex-info "Oh no!" {:pos :thrown})))))

(deftest failing-tests
  (testing "These tests are regular assertion failures"
    (is (= 1 2))

    (is (= [{:x 1} {:y 2} {:z 3}]
           [{:x 1} {:y 0} {:z 3}]))

    (is (throw (ex-info "Oh no!" {:pos :is})))))

(deftest forgot-is
  (testing "Kaocha catches this as a deftest without an assertion, it's a common mistake"
    (= 5 4)))

(deftest exception-outside-assertion
  (testing "Exceptions are reported as :error results"
    (throw (ex-info "Oh no!" {:pos :outside}))))

(defn return-promise []
  (try
    (throw (ex-info "Woring!" {:pos :promise}))))

(deftest some-async-test
  (testing "Some async test should fail"
    (async done
           (go
             (<p! (fetch/get "http://localhost:8001")) ; error occurs here
             (is (= false true)) ; never executed
             (done)))))
