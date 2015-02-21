(defproject ya "0.1.0-SNAPSHOT"
  :description "ya is yurie's server-side application written in Clojure."
  :url "https://github.com/pine613/yurie"
  :min-lein-version "2.0.0"
  
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/core.cache "0.6.4"]
                 [compojure "1.3.1"]
                 [ring/ring-core "1.3.2"]
                 [ring/ring-json "0.3.1"]
                 [congomongo "0.4.4"]
                 [clj-oauth "1.5.2"]
                 [slingshot "0.12.2"]
                 [cheshire "5.4.0"]
                 [environ "1.0.0"]]
  :plugins [[lein-ring "0.8.13"]]
  
  ; Ring
  :ring {:handler ya.handler/app}
  
  ; Profiles
  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                  [ring-mock "0.1.5"]]}
             :uberjar {:aot :all}}
  
  ; Jar
  :jar-name "ya.jar"
  :uberjar-name "ya-standalone.jar"
  
  ; JVM
  :jvm-opts ["-Dfile.encoding=UTF8"])
