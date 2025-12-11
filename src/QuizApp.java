import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Main Quiz Application with GUI using CardLayout for single-window experience
 */
public class QuizApp extends JFrame {
    private QuestionBank questionBank;
    private List<Question> currentQuiz;
    private int currentQuestionIndex;
    private int score;
    private Map<Integer, java.util.Set<Integer>> userAnswers;

    // UI Components
    private JLabel topicLabel;
    private JLabel questionLabel;
    private JCheckBox[] optionButtons;
    private JButton nextButton;
    private JButton previousButton;
    private JButton submitButton;
    private JLabel progressLabel;
    private JTextArea explanationArea;
    private JPanel questionPanel;

    // CardLayout for switching between views
    private CardLayout cardLayout;
    private JPanel mainContainer;
    private JPanel startMenuPanel;
    private JPanel topicSelectionPanel;
    private JPanel resultsPanel;
    private static final String START_MENU = "start";
    private static final String TOPIC_SELECTION = "topics";
    private static final String QUIZ_VIEW = "quiz";
    private static final String RESULTS_VIEW = "results";

    public QuizApp() {
        setTitle("Mathematik1 Quiz - Practice Application");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize question bank
        questionBank = QuestionGenerator.generateMathematik1Questions();
        userAnswers = new HashMap<>();

        initializeUI();
        showView(START_MENU);
    }

    private void initializeUI() {
        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        // Create all views
        mainContainer.add(createStartMenuPanel(), START_MENU);
        mainContainer.add(createTopicSelectionPanel(), TOPIC_SELECTION);
        mainContainer.add(createQuizPanel(), QUIZ_VIEW);
        mainContainer.add(createResultsPanel(), RESULTS_VIEW);

        add(mainContainer);
    }

    private void showView(String viewName) {
        cardLayout.show(mainContainer, viewName);
    }

    private JPanel createStartMenuPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        // Title
        JLabel titleLabel = new JLabel("Mathematik1 Practice Quiz", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Menu panel
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Stats label (will be updated)
        JLabel statsLabel = new JLabel();
        updateStatsLabel(statsLabel);
        menuPanel.add(statsLabel);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Buttons
        JButton startFullQuizBtn = createMenuButton("Start Full Quiz (All Questions)");
        startFullQuizBtn.addActionListener(e -> {
            startQuiz(questionBank.getAllQuestions());
            showView(QUIZ_VIEW);
        });

        JButton startRandomBtn = createMenuButton("Random Quiz (6 Questions)");
        startRandomBtn.addActionListener(e -> {
            startQuiz(questionBank.getRandomQuestions(6));
            showView(QUIZ_VIEW);
        });

        JButton selectTopicBtn = createMenuButton("Select by Topic");
        selectTopicBtn.addActionListener(e -> {
            refreshTopicSelection();
            showView(TOPIC_SELECTION);
        });

        JButton importBtn = createMenuButton("Import Questions from CSV");
        importBtn.addActionListener(e -> {
            importQuestionsFromFile();
            updateStatsLabel(statsLabel);
        });

        JButton exportBtn = createMenuButton("Export Questions");
        exportBtn.addActionListener(e -> exportQuestions());

        menuPanel.add(startFullQuizBtn);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(startRandomBtn);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(selectTopicBtn);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        menuPanel.add(importBtn);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(exportBtn);

        panel.add(menuPanel, BorderLayout.CENTER);
        return panel;
    }

    private void updateStatsLabel(JLabel statsLabel) {
        statsLabel.setText("<html><body>" +
            "<h3>Available Questions:</h3>" +
            "<p>Total Questions: " + questionBank.getTotalQuestions() + "</p>" +
            "<p>Topics: " + questionBank.getAvailableTopics().size() + "</p>" +
            "</body></html>");
    }

    private JPanel createTopicSelectionPanel() {
        topicSelectionPanel = new JPanel(new BorderLayout());
        refreshTopicSelection();
        return topicSelectionPanel;
    }

    private void refreshTopicSelection() {
        topicSelectionPanel.removeAll();

        JLabel titleLabel = new JLabel("Choose a topic:", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        topicSelectionPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel topicPanel = new JPanel();
        topicPanel.setLayout(new BoxLayout(topicPanel, BoxLayout.Y_AXIS));
        topicPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        for (String topic : questionBank.getAvailableTopics()) {
            int count = questionBank.getQuestionsByTopic(topic).size();
            JButton topicBtn = new JButton(topic + " (" + count + " questions)");
            topicBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            topicBtn.setMaximumSize(new Dimension(700, 35));
            topicBtn.addActionListener(e -> {
                startQuiz(questionBank.getQuestionsByTopic(topic));
                showView(QUIZ_VIEW);
            });
            topicPanel.add(topicBtn);
            topicPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        JScrollPane scrollPane = new JScrollPane(topicPanel);
        topicSelectionPanel.add(scrollPane, BorderLayout.CENTER);

        JButton backBtn = new JButton("← Back");
        backBtn.addActionListener(e -> showView(START_MENU));
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(backBtn);
        topicSelectionPanel.add(bottomPanel, BorderLayout.SOUTH);

        topicSelectionPanel.revalidate();
        topicSelectionPanel.repaint();
    }

    private JPanel createQuizPanel() {
        JPanel container = new JPanel(new BorderLayout(10, 10));

        // Top panel with progress
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        progressLabel = new JLabel("Question 1 of 30");
        progressLabel.setFont(new Font("Arial", Font.BOLD, 14));
        topPanel.add(progressLabel, BorderLayout.WEST);
        container.add(topPanel, BorderLayout.NORTH);

        // Center panel for questions
        questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        questionPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        topicLabel = new JLabel();
        topicLabel.setFont(new Font("Arial", Font.BOLD, 16));
        topicLabel.setForeground(new Color(0, 102, 204));

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        optionButtons = new JCheckBox[4];

        questionPanel.add(topicLabel);
        questionPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        questionPanel.add(questionLabel);
        questionPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JCheckBox();
            optionButtons[i].setFont(new Font("Arial", Font.PLAIN, 13));
            questionPanel.add(optionButtons[i]);
            questionPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        explanationArea = new JTextArea(4, 40);
        explanationArea.setWrapStyleWord(true);
        explanationArea.setLineWrap(true);
        explanationArea.setEditable(false);
        explanationArea.setFont(new Font("Arial", Font.ITALIC, 12));
        explanationArea.setBorder(BorderFactory.createTitledBorder("Explanation"));
        explanationArea.setVisible(false);
        questionPanel.add(explanationArea);

        JScrollPane scrollPane = new JScrollPane(questionPanel);
        container.add(scrollPane, BorderLayout.CENTER);

        // Bottom panel with navigation buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        previousButton = new JButton("← Previous");
        previousButton.addActionListener(e -> previousQuestion());

        nextButton = new JButton("Next →");
        nextButton.addActionListener(e -> nextQuestion());

        submitButton = new JButton("Submit Quiz");
        submitButton.addActionListener(e -> submitQuiz());
        submitButton.setBackground(new Color(0, 153, 76));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));

        bottomPanel.add(previousButton);
        bottomPanel.add(nextButton);
        bottomPanel.add(submitButton);

        container.add(bottomPanel, BorderLayout.SOUTH);
        return container;
    }

    private JPanel createResultsPanel() {
        resultsPanel = new JPanel(new BorderLayout(10, 10));
        return resultsPanel;
    }

    private void showResults() {
        resultsPanel.removeAll();

        double percentage = (score * 100.0) / currentQuiz.size();
        String grade;
        Color gradeColor;

        if (percentage >= 90) {
            grade = "Excellent!";
            gradeColor = new Color(0, 153, 0);
        } else if (percentage >= 75) {
            grade = "Good!";
            gradeColor = new Color(0, 102, 204);
        } else if (percentage >= 60) {
            grade = "Pass";
            gradeColor = new Color(204, 102, 0);
        } else {
            grade = "Needs Improvement";
            gradeColor = new Color(204, 0, 0);
        }

        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        innerPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel scoreLabel = new JLabel(String.format("Score: %d / %d", score, currentQuiz.size()));
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel percentLabel = new JLabel(String.format("%.1f%%", percentage));
        percentLabel.setFont(new Font("Arial", Font.BOLD, 32));
        percentLabel.setForeground(gradeColor);
        percentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel gradeLabel = new JLabel(grade);
        gradeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gradeLabel.setForeground(gradeColor);
        gradeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        innerPanel.add(scoreLabel);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        innerPanel.add(percentLabel);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        innerPanel.add(gradeLabel);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JButton reviewBtn = new JButton("Review Answers");
        reviewBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        reviewBtn.addActionListener(e -> {
            reviewAnswers();
            showView(QUIZ_VIEW);
        });

        JButton newQuizBtn = new JButton("Start New Quiz");
        newQuizBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        newQuizBtn.addActionListener(e -> showView(START_MENU));

        innerPanel.add(reviewBtn);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        innerPanel.add(newQuizBtn);

        resultsPanel.add(innerPanel, BorderLayout.CENTER);
        resultsPanel.revalidate();
        resultsPanel.repaint();

        showView(RESULTS_VIEW);
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(400, 40));
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        return button;
    }

    private void startQuiz(List<Question> questions) {
        currentQuiz = new ArrayList<>(questions);
        Collections.shuffle(currentQuiz);
        currentQuestionIndex = 0;
        score = 0;
        userAnswers.clear();
        submitButton.setEnabled(true);
        displayQuestion();
    }

    private void displayQuestion() {
        if (currentQuestionIndex >= currentQuiz.size()) {
            return;
        }

        Question q = currentQuiz.get(currentQuestionIndex);

        topicLabel.setText("<html><b>Topic:</b> " + q.getTopic() +
                          " <i>[" + q.getDifficulty() + "]</i></html>");
        questionLabel.setText("<html><body style='width: 600px'>" +
                             q.getQuestionText() + "</body></html>");

        String[] options = q.getOptions();
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText((char)('A' + i) + ") " + options[i]);
            optionButtons[i].setEnabled(true);
            optionButtons[i].setForeground(Color.BLACK);
            optionButtons[i].setFont(new Font("Arial", Font.PLAIN, 13));
        }

        // Restore previous answer if exists
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setSelected(false);
        }
        if (userAnswers.containsKey(currentQuestionIndex)) {
            for (Integer idx : userAnswers.get(currentQuestionIndex)) {
                if (idx >= 0 && idx < 4) optionButtons[idx].setSelected(true);
            }
        }

        progressLabel.setText("Question " + (currentQuestionIndex + 1) +
                             " of " + currentQuiz.size());

        previousButton.setEnabled(currentQuestionIndex > 0);
        explanationArea.setVisible(false);
    }

    private void nextQuestion() {
        saveCurrentAnswer();

        if (currentQuestionIndex < currentQuiz.size() - 1) {
            currentQuestionIndex++;
            displayQuestion();
        }
    }

    private void previousQuestion() {
        saveCurrentAnswer();

        if (currentQuestionIndex > 0) {
            currentQuestionIndex--;
            displayQuestion();
        }
    }

    private void saveCurrentAnswer() {
        java.util.Set<Integer> selected = new java.util.HashSet<>();
        for (int i = 0; i < 4; i++) {
            if (optionButtons[i].isSelected()) {
                selected.add(i);
            }
        }
        if (!selected.isEmpty()) {
            userAnswers.put(currentQuestionIndex, selected);
        } else {
            userAnswers.put(currentQuestionIndex, new java.util.HashSet<>());
        }
    }

    private void submitQuiz() {
        saveCurrentAnswer();

        if (userAnswers.size() < currentQuiz.size()) {
            int response = JOptionPane.showConfirmDialog(this,
                "You haven't answered all questions. Submit anyway?",
                "Confirm Submit",
                JOptionPane.YES_NO_OPTION);
            if (response != JOptionPane.YES_OPTION) {
                return;
            }
        }

        calculateScore();
        showResults();
    }

    private void calculateScore() {
        score = 0;
        for (int i = 0; i < currentQuiz.size(); i++) {
            java.util.Set<Integer> ans = userAnswers.get(i);
            if (currentQuiz.get(i).isCorrect(ans)) {
                score++;
            }
        }
    }

    private void reviewAnswers() {
        currentQuestionIndex = 0;
        displayQuestionWithReview();
    }

    private void displayQuestionWithReview() {
        Question q = currentQuiz.get(currentQuestionIndex);

        topicLabel.setText("<html><b>Topic:</b> " + q.getTopic() +
                          " <i>[" + q.getDifficulty() + "]</i></html>");
        questionLabel.setText("<html><body style='width: 600px'>" +
                             q.getQuestionText() + "</body></html>");

        String[] options = q.getOptions();
        java.util.Set<Integer> correct = q.getCorrectAnswerIndices();
        java.util.Set<Integer> selected = userAnswers.getOrDefault(currentQuestionIndex, new java.util.HashSet<>());
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText((char)('A' + i) + ") " + options[i]);
            optionButtons[i].setEnabled(false);
            optionButtons[i].setSelected(selected.contains(i));
            optionButtons[i].setForeground(Color.BLACK);
            optionButtons[i].setFont(new Font("Arial", Font.PLAIN, 13));
            if (correct.contains(i)) {
                optionButtons[i].setForeground(new Color(0, 153, 0));
                optionButtons[i].setFont(new Font("Arial", Font.BOLD, 13));
            }
            if (selected.contains(i) && !correct.contains(i)) {
                optionButtons[i].setForeground(Color.RED);
            }
        }

        explanationArea.setText(q.getExplanation());
        explanationArea.setVisible(true);

        progressLabel.setText("Review: Question " + (currentQuestionIndex + 1) +
                             " of " + currentQuiz.size());
    }

    private void importQuestionsFromFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
            "CSV Files", "csv"));

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                questionBank.importFromCSV(fileChooser.getSelectedFile().getAbsolutePath());
                JOptionPane.showMessageDialog(this,
                    "Questions imported successfully!\nTotal questions: " +
                    questionBank.getTotalQuestions(),
                    "Import Successful",
                    JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                    "Error importing questions: " + ex.getMessage(),
                    "Import Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void exportQuestions() {
        String[] options = {"CSV Format", "Text Format (Printable)", "Cancel"};
        int choice = JOptionPane.showOptionDialog(this,
            "Select export format:",
            "Export Questions",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);

        if (choice == 2) return;

        JFileChooser fileChooser = new JFileChooser();
        if (choice == 0) {
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "CSV Files", "csv"));
            fileChooser.setSelectedFile(new java.io.File("mathematik1_questions.csv"));
        } else {
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Text Files", "txt"));
            fileChooser.setSelectedFile(new java.io.File("mathematik1_questions.txt"));
        }

        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                String filename = fileChooser.getSelectedFile().getAbsolutePath();
                if (choice == 0) {
                    questionBank.exportToCSV(filename);
                } else {
                    questionBank.exportToTextFile(filename);
                }
                JOptionPane.showMessageDialog(this,
                    "Questions exported successfully to:\n" + filename,
                    "Export Successful",
                    JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                    "Error exporting questions: " + ex.getMessage(),
                    "Export Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new QuizApp().setVisible(true);
        });
    }
}
