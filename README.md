# Mathematik1 Quiz Application

A comprehensive Java application for practicing Multiple Choice questions based on Mathematik1 course content.

## Features

✅ **Pre-loaded Questions**: 35+ questions covering:
- Algebra and Exponent Rules
- Pythagorean Theorem
- Trigonometry and Unit Circle
- Complex Numbers
- Vector Algebra

✅ **Import/Export**: Load additional questions from CSV files or export questions for printing

✅ **Multiple Quiz Modes**:
- Full quiz (all questions)
- Random quiz (20 questions)
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

### Compilation

Navigate to the project directory and compile all Java files:

```bash
cd /Users/qwitch13/IdeaProjects/Mathematik1Quiz/src
javac *.java
```

### Running the Application

After compilation, run the main application:

```bash
java QuizApp
```

## Usage Guide

### Starting a Quiz

1. Launch the application
2. Choose from the start menu:
   - **Start Full Quiz**: Practice with all available questions
   - **Random Quiz**: Get 20 random questions
   - **Select by Topic**: Choose a specific topic to practice
   - **Import Questions**: Load additional questions from a CSV file
   - **Export Questions**: Save questions to a file

### During the Quiz

- Use **Next** and **Previous** buttons to navigate between questions
- Your answers are automatically saved
- Click **Submit Quiz** when finished
- You can submit even if you haven't answered all questions

### After the Quiz

- View your score and percentage
- See grade feedback
- **Review Answers**: Go through all questions with:
  - Correct answers highlighted in green
  - Your incorrect answers in red
  - Detailed explanations for each question

## File Formats

### CSV Format for Questions

The CSV file should have the following columns:
```
topic,question,optionA,optionB,optionC,optionD,correctIndex,explanation,difficulty
```

**Example:**
```csv
"Algebra","What is 2 + 2?","3","4","5","6",1,"Basic addition",easy
```

- **correctIndex**: 0=A, 1=B, 2=C, 3=D
- **difficulty**: easy, medium, or hard

### Sample Questions File

A sample CSV file (`sample_questions.csv`) is included with 10 example questions.

## Importing Questions

1. Prepare a CSV file with your questions (use the format above)
2. Click "Import Questions from CSV" in the start menu
3. Select your CSV file
4. Questions will be added to the question bank

## Exporting Questions

### CSV Export
- Exports all questions in machine-readable format
- Can be imported later or edited in a spreadsheet program

### Text Export
- Creates a formatted, printable document
- Questions organized by topic
- Includes answer key with explanations at the end
- Perfect for printing study materials

## Project Structure

```
Mathematik1Quiz/
├── src/
│   ├── Question.java          # Question data model
│   ├── QuestionBank.java      # Question management and I/O
│   ├── QuestionGenerator.java # Pre-loaded questions
│   └── QuizApp.java           # Main GUI application
└── sample_questions.csv       # Sample questions file
```

## Topics Covered

### 1. Algebra - Exponent Rules
- Basic exponent operations
- Fractional and negative exponents
- Common mistakes and fallacies

### 2. Geometry - Pythagorean Theorem
- Right triangle calculations
- Geometric proofs

### 3. Trigonometry
- Unit circle
- Sine, cosine, and tangent functions
- Radian measure
- Special angles and values

### 4. Complex Numbers
- Complex plane representation
- Arithmetic operations
- Polar form and De Moivre's Theorem
- Roots of complex numbers

### 5. Vectors
- Vector operations (addition, dot product, cross product)
- Magnitude and direction
- Orthogonality
- Projections

### 6. Mathematical Reasoning
- Identifying fallacies
- Proof techniques

## Adding More Questions

### Method 1: Edit QuestionGenerator.java

Add questions directly in the `generateMathematik1Questions()` method:

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
javac src/*.java
```

### Method 2: Create a CSV File

Create a new CSV file with your questions and import it through the application.

## Troubleshooting

### Compilation Errors
- Ensure you're in the correct directory
- Check that JDK is properly installed: `java -version`
- Make sure all `.java` files are in the same directory

### Cannot Import CSV
- Verify CSV format matches the template
- Check for special characters in questions (use quotes)
- Ensure correctIndex is 0-3

### Application Won't Start
- Check Java version (need JDK 8+)
- Verify all class files were compiled
- Run from the correct directory

## Future Enhancements

Potential features for future versions:
- Timed quizzes
- Question difficulty adaptation
- Progress tracking over time
- LaTeX support for mathematical formulas
- More question types (fill-in-the-blank, matching, etc.)

## Tips for Studying

1. **Start with Topic Quizzes**: Focus on one topic at a time
2. **Review Explanations**: Always read the explanations, even for correct answers
3. **Export and Print**: Create study sheets using the text export feature
4. **Create Custom Questions**: Add your own questions to focus on weak areas
5. **Regular Practice**: Take random quizzes regularly to maintain knowledge

## License

This application is created for educational purposes for Mathematik1 course preparation.

## Contact & Support

For questions or issues:
- Check the troubleshooting section above
- Review the CSV format requirements
- Ensure all files are in the correct locations

---

**Good luck with your Mathematik1 studies! 📚✨**
