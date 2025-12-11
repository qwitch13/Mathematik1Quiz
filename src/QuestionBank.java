import java.io.*;
import java.util.*;

/**
 * Manages a collection of questions with file import/export capabilities
 */
public class QuestionBank {
    private List<Question> questions;
    private Map<String, List<Question>> questionsByTopic;

    public QuestionBank() {
        questions = new ArrayList<>();
        questionsByTopic = new HashMap<>();
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
        String line;
        int lineNumber = 0;
        
        while ((line = reader.readLine()) != null) {
            lineNumber++;
            if (lineNumber == 1 && line.startsWith("topic,")) {
                continue; // Skip header
            }
            
            try {
                String[] parts = parseCSVLine(line);
                if (parts.length < 8) {
                    System.err.println("Warning: Line " + lineNumber + " has insufficient fields");
                    continue;
                }
                
                String topic = parts[0];
                String questionText = parts[1];
                String[] options = new String[]{parts[2], parts[3], parts[4], parts[5]};
                int correctIndex = Integer.parseInt(parts[6]);
                String explanation = parts[7];
                String difficulty = parts.length > 8 ? parts[8] : "medium";
                
                addQuestion(new Question(topic, questionText, options, correctIndex, 
                                       explanation, difficulty));
            } catch (Exception e) {
                System.err.println("Error parsing line " + lineNumber + ": " + e.getMessage());
            }
        }
        reader.close();
        System.out.println("Imported " + questions.size() + " questions from " + filename);
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
