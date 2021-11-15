#!/bin/bash

echo "[Pre-commit] Detekt"

MODIFIED_DETEKT_FILES=$(git diff --diff-filter=ACMRd --name-only --cached --relative | grep 'detekt')

if [[ $MODIFIED_DETEKT_FILES ]]; then
    echo "Configuration files have changed. All files need to be analyzed."
    quality/detekt/detekt-run.sh
else
  FILES_TO_ANALYZE=$(git diff --diff-filter=ACMRd --name-only --cached --relative | grep '\.kt$')
  if [[ -z "$FILES_TO_ANALYZE" ]]; then
    echo "No files to analyze!"
  else
    quality/detekt/detekt-run.sh -i $FILES_TO_ANALYZE
  fi
fi
