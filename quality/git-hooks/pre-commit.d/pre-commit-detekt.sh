#!/bin/bash

echo "Running detekt pre-commit"

eval .tools/detekt-cli/bin/detekt-cli --all-rules

#./gradlew ktlint

#RESULT=$?

# return 1 exit code if running checks fails
#[ $RESULT -ne 0 ] && exit 1
