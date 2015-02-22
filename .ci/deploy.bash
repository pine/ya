#!/bin/bash

echo "> lein ring uberjar"
lein ring uberjar

git clone git@bitbucket.org:pine613/yurie-private.git
