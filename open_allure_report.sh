#!/bin/bash

# Find the Google Chrome path on macOS
CHROME_PATH=$(which google-chrome || which google-chrome-stable || which /Applications/Google\ Chrome.app/Contents/MacOS/Google\ Chrome)

# Check if Chrome is installed
if [[ ! -x "$CHROME_PATH" ]]; then
    echo "Google Chrome executable not found."
    exit 1
fi

# Set the path to the Allure report
ALLURE_REPORT_PATH="$(pwd)/target/site/allure-maven-plugin/index.html"

# Check if the Allure report exists
if [[ ! -f "$ALLURE_REPORT_PATH" ]]; then
    echo "Allure report not found. Please ensure it has been generated."
    exit 1
fi

# Open Chrome with the local file access flag
"$CHROME_PATH" --allow-file-access-from-files "file://$ALLURE_REPORT_PATH"