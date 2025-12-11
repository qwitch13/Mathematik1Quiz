/**
 * Represents a multiple-choice question with topic, question text, options, and correct answer(s).
 * Supports multiple correct answers (including none or all).
 */
public class Question {
    private String topic;
    private String questionText;
    private String[] options;
    // Multiple correct answers supported
    private java.util.Set<Integer> correctAnswerIndices;
    private String explanation;
    private String difficulty; // "easy", "medium", "hard"

    // Backward-compatible constructor: single correct index
    public Question(String topic, String questionText, String[] options,
                    int correctAnswerIndex, String explanation, String difficulty) {
        this.topic = topic;
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndices = new java.util.HashSet<>();
        if (correctAnswerIndex >= 0) {
            this.correctAnswerIndices.add(correctAnswerIndex);
        }
        this.explanation = explanation;
        this.difficulty = difficulty;
    }

    // New constructor: multiple correct indices
    public Question(String topic, String questionText, String[] options,
                    java.util.Set<Integer> correctAnswerIndices, String explanation, String difficulty) {
        this.topic = topic;
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndices = new java.util.HashSet<>();
        if (correctAnswerIndices != null) {
            this.correctAnswerIndices.addAll(correctAnswerIndices);
        }
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

    // For legacy callers expecting a single correct index.
    // Returns -1 when there is not exactly one correct answer.
    public int getCorrectAnswerIndex() {
        return correctAnswerIndices.size() == 1 ? correctAnswerIndices.iterator().next() : -1;
    }

    public java.util.Set<Integer> getCorrectAnswerIndices() {
        return new java.util.HashSet<>(correctAnswerIndices);
    }

    public String getExplanation() {
        return explanation;
    }

    public String getDifficulty() {
        return difficulty;
    }

    // Check correctness for multiple selections: exact match required
    public boolean isCorrect(java.util.Set<Integer> selectedIndices) {
        if (selectedIndices == null) {
            return false;
        }
        return new java.util.HashSet<>(selectedIndices).equals(correctAnswerIndices);
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

    // Convert question to CSV format (legacy: writes a single correct index; -1 if multiple/none)
    public String toCSV() {
        StringBuilder sb = new StringBuilder();
        sb.append(escapeCsv(topic)).append(",");
        sb.append(escapeCsv(questionText)).append(",");
        for (String option : options) {
            sb.append(escapeCsv(option)).append(",");
        }
        sb.append(getCorrectAnswerIndex()).append(",");
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
