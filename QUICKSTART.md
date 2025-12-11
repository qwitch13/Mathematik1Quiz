# QUICK START GUIDE
## Mathematik1 Quiz Application

### Fastest Way to Start

**Option 1: Using the Run Script (Recommended)**
```bash
cd /Users/qwitch13/IdeaProjects/Mathematik1Quiz
./run.sh
```

**Option 2: Manual Start**
```bash
cd /Users/qwitch13/IdeaProjects/Mathematik1Quiz/src
javac *.java
java QuizApp
```

---

## What You Get

### 35+ Pre-loaded Questions on:
✓ Algebra & Exponent Rules (6 questions)
✓ Pythagorean Theorem (1 question)  
✓ Trigonometry & Unit Circle (8 questions)
✓ Complex Numbers (7 questions)
✓ Vectors (6 questions)
✓ Mathematical Reasoning (3 questions)
✓ Advanced Topics (4 questions)

### Key Features:
📊 Multiple quiz modes (full, random, by topic)
📝 Answer review with detailed explanations
💾 Import questions from CSV files
📤 Export to CSV or printable text format
🎯 Progress tracking during quiz
🎨 Color-coded answer review (green=correct, red=incorrect)

---

## First Time Usage

1. **Run the application**
   ```bash
   ./run.sh
   ```

2. **Try a Random Quiz**
   - Click "Random Quiz (20 Questions)"
   - Answer questions at your pace
   - Submit when done

3. **Review Your Answers**
   - See your score and grade
   - Click "Review Answers" to see:
     - Correct answers in green
     - Your mistakes in red
     - Detailed explanations

4. **Practice by Topic**
   - Click "Select by Topic"
   - Choose topics you need to practice
   - Focus on weak areas

---

## Adding Your Own Questions

### Quick Method: Import CSV

1. Create a file `my_questions.csv`:
```csv
topic,question,optionA,optionB,optionC,optionD,correctIndex,explanation,difficulty
"Algebra","What is x²?","x","2x","x squared","x+x",2,"x² means x multiplied by itself",easy
```

2. In the app, click "Import Questions from CSV"
3. Select your file
4. Questions are added immediately!

### See `sample_questions.csv` for more examples

---

## Export Your Questions

**For Printing:**
- Click "Export Questions"
- Choose "Text Format (Printable)"
- Get a formatted document with answer key

**For Backup/Sharing:**
- Choose "CSV Format"  
- Share with classmates or save as backup

---

## Pro Tips

💡 **Study Strategy:**
1. Start with topic-specific quizzes to learn
2. Use random quizzes to test mixed knowledge
3. Review explanations even for correct answers
4. Export and print questions for offline study

💡 **Custom Practice:**
- Add your lecture notes as questions
- Create questions from homework problems
- Import questions from study groups

💡 **Before Exam:**
- Take multiple random quizzes
- Focus on topics with low scores
- Print a text export for last-minute review

---

## File Locations

📁 Application: `/Users/qwitch13/IdeaProjects/Mathematik1Quiz/`
📄 Sample CSV: `sample_questions.csv`
📖 Full Manual: `README.md`

---

## Need Help?

- Check README.md for detailed instructions
- CSV format examples in sample_questions.csv
- All questions have built-in explanations

---

**Ready to practice? Run `./run.sh` and start learning! 🚀**
