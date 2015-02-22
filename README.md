ya
------
[![Build Status](https://travis-ci.org/pine613/ya.svg?branch=master)](https://travis-ci.org/pine613/ya)
[![Build Status](https://drone.io/github.com/pine613/ya/status.png)](https://drone.io/github.com/pine613/ya/latest)
[![Dependencies Status](http://jarkeeper.com/pine613/ya/status.svg)](http://jarkeeper.com/pine613/ya)

**CAUTION!! EXPERIMENTAL REPOSITORY**

ya is [yurie](https://github.com/pine613/yurie)'s server-side application written in Clojure.

## Prerequisites

- Java (1.7 or above)
- [Leiningen](https://github.com/technomancy/leiningen) (2.0.0 or above)
- MongoDB

## Used libraries

- Ring
- Compojure

## Running

To start a web server for the application, run:

```sh
$ export SERVER_HOST_NAME=[Hostname]
$ export SERVER_SCHEME=http
$ export TWITTER_CONSUMER_TOKEN=[Twitter Consumer Token]
$ export TWITTER_CONSUMER_TOKEN_SECRET=[Twitter Consumer Token Secret]
$ lein ring server-headless
```

## Building

```sh
$ lein ring uberjar
$ java -jar target/ya-standalone.jar
```

## License
MIT License<br />
Copyright (c) 2015 Pine Mizune