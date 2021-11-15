#!/bin/bash

curl -sSLO https://github.com/detekt/detekt/releases/download/v1.19.0-RC1/detekt-cli-1.19.0-RC1.zip
unzip detekt-cli-1.19.0-RC1.zip .tools/
./detekt-cli-1.19.0-RC1/bin/detekt-cli --help