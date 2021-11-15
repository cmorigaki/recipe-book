#!/bin/bash

while getopts t:s: flag
do
    case "${flag}" in
        t) TARGET_BRANCH=${OPTARG};;
        s) SOURCE_BRANCH=${OPTARG};;
    esac
done

echo $TARGET_BRANCH
echo $SOURCE_BRANCH
MODIFIED_DETEKT_FILES=$(git diff --diff-filter=ACMRd --name-only $TARGET_BRANCH...$SOURCE_BRANCH | grep 'detekt')

if [[ $MODIFIED_DETEKT_FILES ]]; then
    echo "Configuration files have changed. All files need to be analyzed."
    quality/detekt/detekt-run.sh
else
  FILES_TO_ANALYZE=$(git diff --diff-filter=ACMRd --name-only $TARGET_BRANCH...$SOURCE_BRANCH | grep '\.kt$')
  if [[ -z "$FILES_TO_ANALYZE" ]]; then
    echo "No files to analyze!"
  else
    quality/detekt/detekt-run.sh -i $FILES_TO_ANALYZE
  fi
fi
