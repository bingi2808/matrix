# Matrix Operations API

## Description
This project provides an API for performing various matrix operations, including:
- **Echo** (Print the matrix as CSV)
- **Invert** (Transpose the matrix)
- **Flatten** (Convert to a single line CSV)
- **Sum** (Calculate sum of all elements)
- **Multiply** (Calculate product of all elements, dynamically handling large values)

## Prerequisites
- **Java 21**

## Installation
### Clone the Repository
```
git clone https://github.com/bingi2808/matrix
git clone matrix
```
### Build the Project
To build the project using Gradle:
```
./gradlew clean build
```
This generates a JAR file in the build/libs/ directory.

## Usage
### Run the Application
You can run the application using the generated JAR file:
```
java -jar build/libs/matrix-1.0.0.jar
```
The application will start on http://localhost:8080.

## Running Tests
### To run the unit tests:

```
./gradlew test
```
## Running the Script for API Requests

A script (matrix_operations.sh) is available to automate the API calls. It allows selecting operations dynamically and reusing or changing the input file.

To make the script executable and run it:
```
chmod +x matrix_operations.sh
./matrix_operations.sh
```
The script prompts for the CSV file and lets you choose an operation interactively.

## Notes
* The application handles integer overflow dynamically, switching to BigInteger when needed. 
* Ensure your CSV files contain only integers and have consistent row lengths (must be a square matrix).
