/**
 * Represents a multiple-choice question with topic, question text, options, and correct answer
 */
public class Question {
    private String topic;
    private String questionText;
    private String[] options;
    private int correctAnswerIndex;
    private String explanation;
    private String difficulty; // "easy", "medium", "hard"

    public Question(String topic, String questionText, String[] options, 
                   int correctAnswerIndex, String explanation, String difficulty) {
        this.topic = topic;
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
        this.explanation = explanation;
        this.difficulty = difficulty;
    }

    public String getTopic() {
        return topic;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public boolean isCorrect(int answerIndex) {
        return answerIndex == correctAnswerIndex;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Topic: ").append(topic).append("\n");
        sb.append("Difficulty: ").append(difficulty).append("\n");
        sb.append("Question: ").append(questionText).append("\n");
        for (int i = 0; i < options.length; i++) {
            sb.append((char)('A' + i)).append(") ").append(options[i]).append("\n");
        }
        return sb.toString();
    }

    // Convert question to CSV format
    public String toCSV() {
        StringBuilder sb = new StringBuilder();
        sb.append(escapeCsv(topic)).append(",");
        sb.append(escapeCsv(questionText)).append(",");
        for (String option : options) {
            sb.append(escapeCsv(option)).append(",");
        }
        sb.append(correctAnswerIndex).append(",");
        sb.append(escapeCsv(explanation)).append(",");
        sb.append(escapeCsv(difficulty));
        return sb.toString();
    }

    private String escapeCsv(String value) {
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }
}
