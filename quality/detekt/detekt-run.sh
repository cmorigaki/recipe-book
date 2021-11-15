#!/bin/bash

while getopts i:e: flag
do
    case "${flag}" in
        i) INCLUDE_PATHS=${OPTARG};;
        e) EXCLUDE_PATHS=${OPTARG};;
    esac
done

# Install detekt cli if necessary
DETEKT_VERSION=$(.tools/detekt-cli/bin/detekt-cli --version)
if [[ $DETEKT_VERSION != "1.19.0-RC1" ]]; then
  ./quality/detekt/detekt-install.sh
fi

CMD=".tools/detekt-cli/bin/detekt-cli --all-rules \
--excludes \"**/build/**,$EXCLUDE_PATHS\" "

if [[ ! -z "$INCLUDE_PATHS" ]]; then
  CMD+=" --input $INCLUDE_PATHS"
fi

eval $CMD