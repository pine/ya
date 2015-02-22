#!/bin/bash

curl -O https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein
chmod +x lein
sudo mv lein /usr/local/bin

echo "> lein version"
lein version

echo "> lein deps"
lein deps
