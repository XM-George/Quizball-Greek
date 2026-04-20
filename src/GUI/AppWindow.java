package GUI;

import API.Question;
import API.QuizLogic;
import API.RngQuestionGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.Objects;

import static API.QuizLogic.currentQuestion;

public class AppWindow {

    Font f = new Font(null, Font.PLAIN, 20);
    RngQuestionGenerator rng = new RngQuestionGenerator();

    ImageIcon footballImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/ICONS/football.png")));

    JFrame main;

    public void start() {
        main = new JFrame();
        main.setPreferredSize(new Dimension(800, 800));
        main.pack();
        main.setLocationRelativeTo(null);
        main.setResizable(false);
        main.setLayout(null);
        main.setIconImage(footballImage.getImage());
        main.setTitle("Quizball");

        setMenuBar();

        setNamesToFrame();

        setScoreForPlayers();

        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Question.removeQuestion(currentQuestion);
        currentQuestion = rng.generateQuestion();

        JButton showQ = new JButton();
        showQ.setBounds(300, 650, 200, 50);
        showQ.setSize(200, 50);
        if(QuizLogic.categories.isEmpty()) {
            showQ.setText("Select categories");
        }
        else
        {
            showQ.setText("Show question");
        }
        showQ.setFocusable(false);
        main.getRootPane().setDefaultButton(showQ);
        showQ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.dispose();
                if(QuizLogic.categories.isEmpty()) {
                    categorySelect();
                    start();
                }
                else
                {
                    showQuestionAnswerDialog("Q");
                }
            }
        });

        if(Question.checkIfEmpty()) {
            showQ.setText("No questions to show");
            showQ.setEnabled(false);
        }

        main.add(showQ);

        main.setVisible(true);
    }

    public void setMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Set names");
        JMenuItem setNames = new JMenuItem("Set names");
        setNames.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getNames();
                main.dispose();
                start();
            }
        });

        menu.add(setNames);

        menuBar.add(menu);

        main.setJMenuBar(menuBar);
    }

    public void categorySelect()
    {
        JDialog categoryDialog = new JDialog();
        categoryDialog.getContentPane().setPreferredSize(new Dimension(800, 500));
        categoryDialog.pack();
        categoryDialog.setTitle("Choose categories");
        ImageIcon questionImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/ICONS/questions.png")));
        categoryDialog.setIconImage(questionImage.getImage());
        categoryDialog.setLocationRelativeTo(null);
        categoryDialog.setResizable(false);
        categoryDialog.setModal(true);
        categoryDialog.setLayout(null);

        int width = 20;
        int height = 20;
        int categorySum = 0;

        JButton[] buttons = new JButton[Question.categories.size()];

        for (String c : Question.categoryNames)
        {
            ImageIcon rawCategoryIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/ICONS/" + c + ".png")));
            Image categoryImage = rawCategoryIcon.getImage().getScaledInstance(42, 42, Image.SCALE_SMOOTH);

            JLabel label = new JLabel(c.toUpperCase());
            label.setBounds(width, height, 250,30);
            label.setFont(f);

            JButton button = new JButton();
            button.setIcon(new ImageIcon(categoryImage));
            button.setBounds(width, height + 30, 100, 50);
            button.setFocusable(false);
            buttons[categorySum] = button;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String category = new File(rawCategoryIcon.getDescription()).getName().replaceFirst("[.][^.]+$", "");
                    QuizLogic.categories.add(category);
                    button.setEnabled(false);
                }
            });

            width += 250;
            categorySum += 1;
            if(categorySum % 3 == 0)
            {
                width = 20;
                height += 90;
            }

            categoryDialog.add(label);
            categoryDialog.add(button);
        }

        JButton confirmButton = new JButton("Confirm");
        confirmButton.setBounds(100, 420, 200, 50);
        confirmButton.setFocusable(false);
        confirmButton.setFont(f);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                categoryDialog.dispose();
            }
        });

        JButton resetButton = new JButton("Reset");
        resetButton.setBounds(500, 420, 200, 50);
        resetButton.setFocusable(false);
        resetButton.setFont(f);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                QuizLogic.categories.clear();
                for (JButton b : buttons)
                {
                    b.setEnabled(true);
                }
            }
        });

        categoryDialog.add(confirmButton);
        categoryDialog.add(resetButton);

        categoryDialog.setVisible(true);

        if (QuizLogic.categories.isEmpty()) {
            QuizLogic.categories.addAll(Arrays.asList(Question.categoryNames));
        }
    }

    public void getNames()
    {
        JDialog nameDialog = new JDialog();
        nameDialog.setSize(800, 300);
        nameDialog.setTitle("Set names");
        nameDialog.setLocationRelativeTo(null);
        nameDialog.setResizable(false);
        nameDialog.setModal(true);
        nameDialog.setLayout(null);

        JLabel nameLabel1 = new JLabel("Player 1:");
        nameLabel1.setFont(f);
        JLabel nameLabel2 = new JLabel("Player 2:");
        nameLabel2.setFont(f);

        JTextField nameField1 = new JTextField();
        nameField1.setFont(f);
        JTextField nameField2 = new JTextField();
        nameField2.setFont(f);

        JButton setNamesButton = new JButton("Set names");
        setNamesButton.setFont(f);
        setNamesButton.setFocusable(false);
        nameDialog.getRootPane().setDefaultButton(setNamesButton);
        setNamesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                QuizLogic.playerNames[0] = nameField1.getText();
                QuizLogic.playerNames[1] = nameField2.getText();
                nameDialog.dispose();
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(f);
        cancelButton.setFocusable(false);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameDialog.dispose();
            }
        });

        nameLabel1.setBounds(50, 20, 325, 30);
        nameLabel2.setBounds(425, 20, 325, 30);

        nameField1.setBounds(50, 60, 325, 40);
        nameField2.setBounds(425, 60, 325, 40);

        setNamesButton.setBounds(50, 200, 325, 40);
        cancelButton.setBounds(425, 200, 325, 40);

        nameDialog.add(nameLabel1);
        nameDialog.add(nameLabel2);
        nameDialog.add(nameField1);
        nameDialog.add(nameField2);
        nameDialog.add(setNamesButton);
        nameDialog.add(cancelButton);

        nameDialog.setVisible(true);
    }

    public void setNamesToFrame()
    {
        if(QuizLogic.playerNames == null)
        {
            return;
        }
        JLabel nameLabel1 = new JLabel(QuizLogic.playerNames[0]);
        nameLabel1.setFont(f);
        JLabel nameLabel2 = new JLabel(QuizLogic.playerNames[1]);
        nameLabel2.setFont(f);

        nameLabel1.setBounds(50, 20, 300, 30);
        nameLabel2.setBounds(450, 20, 300, 30);

        main.add(nameLabel1);
        main.add(nameLabel2);
    }


    JLabel scoreLabel1;
    JLabel scoreLabel2;
    public void setScoreForPlayers()
    {
        scoreLabel1 = new JLabel("Score: " + QuizLogic.scores[0]);
        scoreLabel1.setFont(f);

        scoreLabel2 = new JLabel("Score: " + QuizLogic.scores[1]);
        scoreLabel2.setFont(f);

        JButton addScoreButton1 = new JButton("+1");
        addScoreButton1.setFont(f);
        addScoreButton1.setFocusable(false);
        addScoreButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (QuizLogic.scores[0] + 1 <=100)
                {
                    QuizLogic.scores[0]++;
                }
                updateScores();
            }
        });

        JButton subtractScoreButton1 = new JButton("-1");
        subtractScoreButton1.setFont(f);
        subtractScoreButton1.setFocusable(false);
        subtractScoreButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(QuizLogic.scores[0] - 1 >= 0)
               {
                   QuizLogic.scores[0]--;
               }
               updateScores();
            }
        });

        JButton addScoreButton2 = new JButton("+1");
        addScoreButton2.setFont(f);
        addScoreButton2.setFocusable(false);
        addScoreButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (QuizLogic.scores[1] + 1 <=100)
                {
                    QuizLogic.scores[1]++;
                }
                updateScores();
            }
        });

        JButton subtractScoreButton2 = new JButton("-1");
        subtractScoreButton2.setFont(f);
        subtractScoreButton2.setFocusable(false);
        subtractScoreButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(QuizLogic.scores[1] - 1 >= 0)
                {
                    QuizLogic.scores[1]--;
                }
                updateScores();
            }
        });

        scoreLabel1.setBounds(50, 60, 300, 30);
        scoreLabel2.setBounds(450, 60, 300, 30);

        addScoreButton1.setBounds(50, 100, 100, 40);
        subtractScoreButton1.setBounds(200, 100, 100, 40);

        addScoreButton2.setBounds(450, 100, 100, 40);
        subtractScoreButton2.setBounds(600, 100, 100, 40);

        main.add(scoreLabel1);
        main.add(scoreLabel2);
        main.add(addScoreButton1);
        main.add(subtractScoreButton1);
        main.add(addScoreButton2);
        main.add(subtractScoreButton2);
    }

    public void updateScores() {
        scoreLabel1.setText("Score: " + QuizLogic.scores[0]);
        scoreLabel2.setText("Score: " + QuizLogic.scores[1]);

        main.repaint();
    }

    public void showQuestionAnswerDialog(String use) {
        JFrame questionAnswerFrame = new JFrame();
        questionAnswerFrame.setSize(600, 400);
        questionAnswerFrame.setLayout(null);
        questionAnswerFrame.setLocationRelativeTo(null);
        questionAnswerFrame.setResizable(false);
        questionAnswerFrame.setIconImage(footballImage.getImage());

        questionAnswerFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JTextArea field = new JTextArea();
        field.setBounds(50, 50, 500, 200);
        field.setEditable(false);
        field.setFocusable(false);
        field.setLineWrap(true);
        field.setWrapStyleWord(true);
        field.setFont(f);

        if (use.equals("Q")) {
            field.setText(Question.questions.get(currentQuestion).question);
            questionAnswerFrame.setTitle("Question");
        } else if (use.equals("A")) {
            field.setText(Question.questions.get(currentQuestion).question + "\n\n" + Question.questions.get(currentQuestion).answer);
            questionAnswerFrame.setTitle("Answer");
        }

        JButton showNext = getShowNextButton(use, questionAnswerFrame);

        questionAnswerFrame.add(field);
        questionAnswerFrame.add(showNext);

        questionAnswerFrame.setVisible(true);
    }

    private JButton getShowNextButton(String use, JFrame frame) {
        JButton showNext = new JButton();
        showNext.setBounds(200, 300, 200, 50);
        if (use.equals("Q")) {
            showNext.setText("Show Answer");
        } else if (use.equals("A")) {
            showNext.setText("Next Question");
        }
        showNext.setFocusable(false);
        frame.getRootPane().setDefaultButton(showNext);
        showNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                if (use.equals("Q")) {
                    showQuestionAnswerDialog("A");
                } else if (use.equals("A")) {
                    start();
                }
            }
        });
        return showNext;
    }
}