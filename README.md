ya
------

**CAUTION!! EXPERIMENTAL REPOSITORY**

ya is yurie's server-side application written in Clojure.

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
$ export TWITTER_CONSUMER_TOKEN=[Twitter Consumer Token]
$ export TWITTER_CONSUMER_TOKEN_SECRET=[Twitter Consumer Token Secret]
$ lein ring server-headless
```


## License
MIT License<br />
Copyright (c) 2015 Pine Mizune