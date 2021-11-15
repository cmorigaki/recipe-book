#!/bin/bash

mkdir -p .tools

echo "Downloading detekt cli"
curl -sSL https://github.com/detekt/detekt/releases/download/v1.19.0-RC1/detekt-cli-1.19.0-RC1.zip -o .tools/detekt-cli.zip

echo "Extracting..."
unzip -q .tools/detekt-cli.zip -d .tools/

mv .tools/detekt-cli-1.19.0-RC1 .tools/detekt-cli
rm .tools/detekt-cli.zip
