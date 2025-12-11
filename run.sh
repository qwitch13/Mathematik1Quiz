#!/bin/bash

# Mathematik1 Quiz - Compile and Run Script

echo "======================================"
echo "  Mathematik1 Quiz Application"
echo "======================================"
echo ""

# Navigate to src directory
cd "$(dirname "$0")/src"

# Compile all Java files
echo "Compiling Java files..."
javac *.java

# Check if compilation was successful
if [ $? -eq 0 ]; then
    echo "✓ Compilation successful!"
    echo ""
    echo "Starting Quiz Application..."
    echo ""
    
    # Run the application
    java QuizApp
else
    echo "✗ Compilation failed. Please check for errors."
    exit 1
fi
