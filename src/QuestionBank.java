import java.io.*;
import java.util.*;

/**
 * Manages a collection of questions with file import/export capabilities
 */
public class QuestionBank {
    private List<Question> questions;
    private Map<String, List<Question>> questionsByTopic;

    private static final String PERSISTENCE_FILE = "questions_data.csv";

    public QuestionBank() {
        questions = new ArrayList<>();
        questionsByTopic = new HashMap<>();
        loadFromPersistence();
    }

    /**
     * Load questions from persistence file on startup
     */
    private void loadFromPersistence() {
        File file = new File(PERSISTENCE_FILE);
        if (file.exists()) {
            try {
                importFromCSV(PERSISTENCE_FILE);
            } catch (IOException e) {
                System.err.println("Could not load persistence file: " + e.getMessage());
            }
        }
    }

    /**
     * Save all questions to persistence file
     */
    public void saveToPersistence() {
        try {
            exportToCSV(PERSISTENCE_FILE);
        } catch (IOException e) {
            System.err.println("Could not save to persistence file: " + e.getMessage());
        }
    }

    public void addQuestion(Question question) {
        questions.add(question);
        questionsByTopic.computeIfAbsent(question.getTopic(), k -> new ArrayList<>())
                       .add(question);
    }

    public List<Question> getAllQuestions() {
        return new ArrayList<>(questions);
    }

    public List<Question> getQuestionsByTopic(String topic) {
        return questionsByTopic.getOrDefault(topic, new ArrayList<>());
    }

    public List<String> getAvailableTopics() {
        return new ArrayList<>(questionsByTopic.keySet());
    }

    public int getTotalQuestions() {
        return questions.size();
    }

    /**
     * Import questions from CSV file
     * Format: topic,question,optionA,optionB,optionC,optionD,correctIndex,explanation,difficulty
     */
    public void importFromCSV(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        StringBuilder content = new StringBuilder();
        String line;

        // Read entire file content
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        reader.close();

        String fullContent = content.toString();

        // Check if file has newlines - if not, it's space-separated and needs special handling
        String[] lines;
        if (fullContent.split("\n").length <= 2) {
            // File is on one or two lines - it's space-separated
            fullContent = fullContent.replace("\n", " ");
            // Insert newline after difficulty field when followed by a topic name
            // Pattern: ",difficulty TOPIC" or ",difficultyTOPIC" (sometimes no space)
            // The structure is: ...,"explanation",(easy|medium|hard)[ ]?Topic...
            fullContent = fullContent.replaceAll(",(easy|medium|hard)\\s*([A-Za-zÄÖÜäöü])", ",$1\n$2");
            lines = fullContent.split("\n");
        } else {
            lines = fullContent.split("\n");
        }
        int lineNumber = 0;
        int imported = 0;

        for (String record : lines) {
            lineNumber++;
            record = record.trim();

            if (record.isEmpty() || record.startsWith("topic,question")) {
                continue; // Skip header and empty lines
            }

            try {
                String[] parts = parseCSVLine(record);
                if (parts.length < 8) {
                    continue;
                }

                String topic = parts[0].trim();
                String questionText = parts[1].trim();
                String[] options = new String[]{parts[2].trim(), parts[3].trim(), parts[4].trim(), parts[5].trim()};
                int correctIndex = Integer.parseInt(parts[6].trim());
                String explanation = parts[7].trim();
                String difficulty = parts.length > 8 ? parts[8].trim() : "medium";

                addQuestion(new Question(topic, questionText, options, correctIndex,
                                       explanation, difficulty));
                imported++;
            } catch (Exception e) {
                // Silently skip malformed lines
            }
        }
        System.out.println("Imported " + imported + " questions from " + filename);

        // Save to persistence if not already loading from persistence
        if (!filename.equals(PERSISTENCE_FILE)) {
            saveToPersistence();
        }
    }

    /**
     * Export questions to CSV file
     */
    public void exportToCSV(String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        
        // Write header
        writer.write("topic,question,optionA,optionB,optionC,optionD,correctIndex,explanation,difficulty\n");
        
        // Write questions
        for (Question q : questions) {
            writer.write(q.toCSV() + "\n");
        }
        
        writer.close();
        System.out.println("Exported " + questions.size() + " questions to " + filename);
    }

    /**
     * Export questions to a formatted text file for printing/studying
     */
    public void exportToTextFile(String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        
        writer.write("===================================================\n");
        writer.write("       MATHEMATIK 1 - PRACTICE QUESTIONS\n");
        writer.write("===================================================\n\n");
        
        Map<String, List<Question>> byTopic = new HashMap<>();
        for (Question q : questions) {
            byTopic.computeIfAbsent(q.getTopic(), k -> new ArrayList<>()).add(q);
        }
        
        int questionNum = 1;
        for (String topic : byTopic.keySet()) {
            writer.write("\n" + "=".repeat(50) + "\n");
            writer.write("TOPIC: " + topic.toUpperCase() + "\n");
            writer.write("=".repeat(50) + "\n\n");
            
            for (Question q : byTopic.get(topic)) {
                writer.write("Question " + questionNum + " [" + q.getDifficulty() + "]:\n");
                writer.write(q.getQuestionText() + "\n\n");
                
                String[] options = q.getOptions();
                for (int i = 0; i < options.length; i++) {
                    writer.write("   " + (char)('A' + i) + ") " + options[i] + "\n");
                }
                writer.write("\n");
                questionNum++;
            }
        }
        
        // Answer key at the end
        writer.write("\n\n" + "=".repeat(50) + "\n");
        writer.write("ANSWER KEY\n");
        writer.write("=".repeat(50) + "\n\n");
        
        questionNum = 1;
        for (String topic : byTopic.keySet()) {
            writer.write("\n" + topic + ":\n");
            for (Question q : byTopic.get(topic)) {
                java.util.Set<Integer> answers = q.getCorrectAnswerIndices();
                if (answers.isEmpty()) {
                    writer.write("  Q" + questionNum + ": (no correct options)\n");
                } else {
                    StringBuilder letters = new StringBuilder();
                    boolean first = true;
                    for (Integer idx : new java.util.TreeSet<>(answers)) {
                        if (!first) letters.append(", ");
                        letters.append((char)('A' + idx));
                        first = false;
                    }
                    writer.write("  Q" + questionNum + ": " + letters + "\n");
                }
                writer.write("     Explanation: " + q.getExplanation() + "\n\n");
                questionNum++;
            }
        }
        
        writer.close();
        System.out.println("Exported formatted questions to " + filename);
    }

    /**
     * Parse a CSV line handling quoted fields
     */
    private String[] parseCSVLine(String line) {
        List<String> result = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder current = new StringBuilder();
        
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            
            if (c == '"') {
                if (inQuotes && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    current.append('"');
                    i++;
                } else {
                    inQuotes = !inQuotes;
                }
            } else if (c == ',' && !inQuotes) {
                result.add(current.toString());
                current = new StringBuilder();
            } else {
                current.append(c);
            }
        }
        result.add(current.toString());
        
        return result.toArray(new String[0]);
    }

    /**
     * Get random questions for a quiz
     */
    public List<Question> getRandomQuestions(int count) {
        List<Question> shuffled = new ArrayList<>(questions);
        Collections.shuffle(shuffled);
        return shuffled.subList(0, Math.min(count, shuffled.size()));
    }

    /**
     * Get random questions from specific topic
     */
    public List<Question> getRandomQuestionsByTopic(String topic, int count) {
        List<Question> topicQuestions = getQuestionsByTopic(topic);
        Collections.shuffle(topicQuestions);
        return topicQuestions.subList(0, Math.min(count, topicQuestions.size()));
    }
}
