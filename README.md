# Mathematik1 Quiz Application

A comprehensive Java application for practicing Multiple Choice questions based on Mathematik1 course content with persistent storage and single-window interface.

## Features

✅ **Persistent Storage**: Automatically saves and loads all imported questions

✅ **Single Window Interface**: All menus and dialogs in one window - no popup windows

✅ **Large Question Bank**: Supports hundreds of questions across multiple topics including:
- Zahlenlehre (Number Theory)
- Algebra
- Gleichungen (Equations)
- Folgen und Reihen (Sequences and Series)
- Funktionen (Functions)
- Geometrie (Geometry)
- Analysis (Calculus)

✅ **Import/Export**: Load questions from CSV files or export for printing/backup

✅ **Multiple Quiz Modes**:
- Full quiz (all questions)
- Random quiz (6 questions)
- Topic-specific quizzes

✅ **Interactive GUI**: User-friendly interface with progress tracking

✅ **Answer Review**: Review all answers with explanations after completing the quiz

✅ **Export Options**:
- CSV format for data
- Formatted text file for printing/studying

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Terminal or Command Prompt

### Quick Start

**Linux/Mac:**
```bash
chmod +x run.sh
./run.sh
```

**Windows:**
```cmd
run.bat
```

### Manual Compilation

Navigate to the project directory and compile:

```bash
# Create bin directory
mkdir -p bin

# Compile all Java files
javac -d bin src/*.java

# Run the application
java -cp bin QuizApp
```

## Usage Guide

### Starting a Quiz

1. Launch the application using `run.sh` (Linux/Mac) or `run.bat` (Windows)
2. Choose from the start menu:
   - **Start Full Quiz**: Practice with all available questions
   - **Random Quiz (6 Questions)**: Get 6 random questions for quick practice
   - **Select by Topic**: Choose a specific topic to practice
   - **Import Questions from CSV**: Load additional questions from a file
   - **Export Questions**: Save questions to a file

### During the Quiz

- Use **Next** and **Previous** buttons to navigate between questions
- Your answers are automatically saved as you navigate
- Click **Submit Quiz** when finished
- You can submit even if you haven't answered all questions (confirmation dialog will appear)

### After the Quiz

- View your score and percentage
- See grade feedback (Excellent, Good, Pass, or Needs Improvement)
- **Review Answers**: Go through all questions with:
  - Correct answers highlighted in **green** and bold
  - Your incorrect answers in **red**
  - Detailed explanations for each question
- **Start New Quiz**: Return to main menu to start another quiz

## Importing Questions

### Step-by-Step Import Guide

1. **Prepare Your CSV File**
   - Create or obtain a CSV file with questions
   - Ensure it follows the correct format (see below)

2. **Import the File**
   - Click **"Import Questions from CSV"** from the main menu
   - Browse to your CSV file
   - Select the file and click "Open"
   - Wait for the import confirmation message

3. **Automatic Persistence**
   - All imported questions are automatically saved to `questions_data.csv`
   - Next time you start the app, all questions will be loaded automatically
   - No need to import again!

### CSV Format

The CSV file should have the following columns (in this exact order):
```
topic,question,optionA,optionB,optionC,optionD,correctIndex,explanation,difficulty
```

**Important Format Notes:**
- **correctIndex**: 0=A, 1=B, 2=C, 3=D (zero-indexed!)
- **difficulty**: must be `easy`, `medium`, or `hard`
- **Quotes**: Use double quotes for fields containing commas or special characters
- **Encoding**: UTF-8 encoding recommended for German characters (ä, ö, ü, ß)

**Example:**
```csv
topic,question,optionA,optionB,optionC,optionD,correctIndex,explanation,difficulty
Algebra,Was ist 2 + 2?,3,4,5,6,1,"Grundlegende Addition: 2 + 2 = 4",easy
Geometrie,Was ist der Satz des Pythagoras?,"a² + b² = c²","a + b = c","a² = b² + c²","a = b + c",0,"In einem rechtwinkligen Dreieck gilt: a² + b² = c²",medium
```

### Supported CSV Formats

The application can handle two CSV formats:

#### Standard Format (Recommended)
Each question on a separate line:
```csv
topic,question,optionA,optionB,optionC,optionD,correctIndex,explanation,difficulty
Topic1,Question1,A,B,C,D,0,Explanation1,easy
Topic2,Question2,A,B,C,D,1,Explanation2,medium
```

#### Space-Separated Format
All questions on one line separated by difficulty keywords. The application will automatically parse this format:
```csv
topic,question,optionA,optionB,optionC,optionD,correctIndex,explanation,easy Topic2,question,...
```

### Import Tips

✅ **Do:**
- Test with a small file first (5-10 questions)
- Use UTF-8 encoding for special characters
- Enclose fields with commas in double quotes
- Verify correctIndex values (0-3)
- Use meaningful topic names

❌ **Don't:**
- Use newlines within question text
- Mix up the column order
- Use correctIndex values outside 0-3
- Forget quotes around text containing commas

### Troubleshooting Import Issues

**Problem: "Only X questions imported" (fewer than expected)**
- Check that each question has all 9 fields
- Verify quotes are balanced (opening quote has closing quote)
- Look for embedded commas without proper quoting
- Check for special characters that might break parsing

**Problem: "Error importing questions"**
- Verify the file is a valid CSV file
- Check file permissions (must be readable)
- Ensure file is not open in another program
- Try a smaller test file first

**Problem: Questions appear garbled**
- File encoding might be wrong - save as UTF-8
- Special characters (ä, ö, ü) need UTF-8 encoding

## Exporting Questions

### CSV Export
- Exports all questions in machine-readable format
- Can be imported later or edited in a spreadsheet program
- Perfect for backup or sharing question banks

### Text Export
- Creates a formatted, printable document
- Questions organized by topic
- Includes answer key with explanations at the end
- Perfect for printing study materials or offline review

## Persistent Storage

The application automatically manages persistence:

- **Automatic Save**: When you import questions, they're saved to `questions_data.csv`
- **Automatic Load**: On startup, questions are loaded from `questions_data.csv`
- **Location**: The persistence file is in the project root directory
- **No Manual Action Needed**: Everything happens automatically!

**Note**: If you want to reset the question bank, simply delete `questions_data.csv` and restart the app.

## Project Structure

```
Mathematik1Quiz/
├── src/
│   ├── Question.java          # Question data model
│   ├── QuestionBank.java      # Question management and I/O with persistence
│   ├── QuestionGenerator.java # Pre-loaded sample questions
│   └── QuizApp.java           # Main GUI application with CardLayout
├── bin/                       # Compiled class files (auto-generated)
├── questions_data.csv         # Persistent storage (auto-generated)
├── 300q-from-math1-source.csv # Example question bank
├── run.sh                     # Linux/Mac launch script
├── run.bat                    # Windows launch script
└── README.md                  # This file
```

## Topics Covered

The application supports questions across all Mathematik1 topics:

### Zahlenlehre (Number Theory)
- Ganze Zahlen und Brüche (Integers and Fractions)
- Potenzen und Wurzeln (Powers and Roots)
- Komplexe Zahlen (Complex Numbers)

### Algebra
- Grundlagen (Fundamentals)
- Binomische Formeln (Binomial Formulas)
- Bruchrechnung (Fractions)

### Gleichungen (Equations)
- Lineare Gleichungen (Linear Equations)
- Quadratische Gleichungen (Quadratic Equations)
- Lineare Gleichungssysteme (Systems of Linear Equations)

### Folgen und Reihen (Sequences and Series)
- Grundlagen (Fundamentals)
- Binomialkoeffizienten (Binomial Coefficients)

### Funktionen (Functions)
- Graphen und Eigenschaften (Graphs and Properties)
- Exponential und Logarithmus (Exponential and Logarithmic)

### Geometrie (Geometry)
- Ebene (Plane Geometry)
- Vektoren (Vectors)

### Analysis
- Differenzialrechnung (Differential Calculus)
- Integralrechnung (Integral Calculus)

## Adding More Questions

### Method 1: Import CSV File (Recommended)

1. Create a CSV file with your questions
2. Import through the application
3. Questions are automatically saved and persist

### Method 2: Edit QuestionGenerator.java

Add questions directly in the code:

```java
bank.addQuestion(new Question(
    "Topic Name",
    "Your question text?",
    new String[]{"Option A", "Option B", "Option C", "Option D"},
    1, // correct answer index (0-3)
    "Explanation text",
    "difficulty" // easy, medium, or hard
));
```

Then recompile:
```bash
javac -d bin src/*.java
```

## Troubleshooting

### Compilation Errors
- Ensure you're in the correct directory
- Check that JDK is properly installed: `java -version`
- Try using the provided run scripts instead

### Cannot Import CSV
- Verify CSV format matches the template exactly
- Check for special characters (save as UTF-8)
- Ensure correctIndex is 0-3
- Test with a simple 2-3 question file first

### Questions Not Persisting
- Check if `questions_data.csv` is created in project root
- Verify you have write permissions in the project directory
- Look for error messages in the console

### Application Won't Start
- Check Java version: `java -version` (need JDK 8+)
- Verify all class files were compiled
- Try using the run scripts (`run.sh` or `run.bat`)

### GUI Issues
- All windows should appear in the main frame (no popups)
- If menus don't respond, check console for errors
- Try resizing the window if content appears cut off

## Tips for Studying

1. **Import a Large Question Bank**: Start by importing a comprehensive CSV file
2. **Start with Topic Quizzes**: Focus on one topic at a time
3. **Review Explanations**: Always read explanations, even for correct answers
4. **Use Random Quizzes**: Regular practice with random questions helps retention
5. **Export and Print**: Create study sheets using the text export feature
6. **Track Your Progress**: Note which topics need more practice

## Command Reference

### Linux/Mac
```bash
# Run the application
./run.sh

# Manual compilation
mkdir -p bin
javac -d bin src/*.java
java -cp bin QuizApp

# Check Java version
java -version
```

### Windows
```cmd
REM Run the application
run.bat

REM Manual compilation
mkdir bin
javac -d bin src\*.java
java -cp bin QuizApp

REM Check Java version
java -version
```

## Example: Importing the Included Question Bank

The project includes `300q-from-math1-source.csv` with 200+ questions:

1. Start the application
2. Click **"Import Questions from CSV"**
3. Navigate to `300q-from-math1-source.csv`
4. Click "Open"
5. Wait for confirmation ("Imported X questions")
6. Questions are now available and will persist!

## License

This application is created for educational purposes for Mathematik1 course preparation.

## Contact & Support

For questions or issues:
- Check the troubleshooting section above
- Review the CSV format requirements
- Ensure all files are in the correct locations
- Check the console output for error messages

---

**Good luck with your Mathematik1 studies! 📚✨**
