#!/bin/bash

# Update detekt-run.sh on version update

mkdir -p .tools

echo "Downloading detekt cli"
curl -sSL https://github.com/detekt/detekt/releases/download/v1.19.0/detekt-cli-1.19.0.zip -o .tools/detekt-cli.zip

echo "Extracting detekt cli..."
unzip -q .tools/detekt-cli.zip -d .tools/

rm -rf .tools/detekt-cli
mv .tools/detekt-cli-1.19.0 .tools/detekt-cli
rm .tools/detekt-cli.zip

echo "Downloading detekt ktlint formatting"
rm .tools/detekt-formatting.jar
curl -sSL https://repo1.maven.org/maven2/io/gitlab/arturbosch/detekt/detekt-formatting/1.19.0/detekt-formatting-1.19.0.jar -o .tools/detekt-formatting.jar

echo "Installation finished"