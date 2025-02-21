#!/bin/bash

BASE_URL="http://localhost:8080/matrix"
FILE_PATH=""

while true; do
    if [ -z "$FILE_PATH" ]; then
        echo "Enter the path to the CSV file:"
        read FILE_PATH
    fi

    if [ ! -f "$FILE_PATH" ]; then
        echo "Error: File does not exist! Please enter a valid file."
        FILE_PATH=""
        continue
    fi

    echo "Choose an operation:"
    echo "1 - Echo | 2 - Invert | 3 - Flatten  | 4 - Sum | 5 - Multiply | 6 - Change CSV File | 7 - Exit"
    read -p "Enter your choice (1-7): " CHOICE

    case $CHOICE in
        1) ENDPOINT="echo";;
        2) ENDPOINT="invert";;
        3) ENDPOINT="flatten";;
        4) ENDPOINT="sum";;
        5) ENDPOINT="multiply";;
        6) FILE_PATH=""; continue;;
        7) echo "Exiting..."; exit 0;;
        *) echo "Invalid choice! Please enter a valid option."; continue;;
    esac

    echo "Running $ENDPOINT operation..."
    curl -X POST -F "file=@$FILE_PATH" "$BASE_URL/$ENDPOINT" -w "\n"
    echo

done
