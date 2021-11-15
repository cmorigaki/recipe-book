#!/bin/bash

while getopts i:e: flag
do
    case "${flag}" in
        i) INCLUDE_PATHS=${OPTARG};;
        e) EXCLUDE_PATHS=${OPTARG};;
    esac
done

CMD=".tools/detekt-cli/bin/detekt-cli --all-rules \
--excludes \"**/build/**,$EXCLUDE_PATHS\" "

if [[ ! -z "$INCLUDE_PATHS" ]]; then
  CMD+=" --input $INCLUDE_PATHS"
fi

eval $CMD