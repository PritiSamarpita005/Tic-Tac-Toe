import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tictactoe implements ActionListener {

    private JFrame frame;
    private JPanel panel;
    private JButton[][] buttons;
    private char currentPlayer;
    private int playerXScore;
    private int playerOScore;
    private JLabel scoreLabel;
    private final Color initialColor = Color.LIGHT_GRAY;
    private final Color clickedColor = Color.GREEN;
    
    public Tictactoe() {
        frame = new JFrame("Tic-Tac-Toe");
        frame.setSize(400,400);
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(3,3));
        panel.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
        frame.add(panel, BorderLayout.CENTER);

        buttons = new JButton[3][3];
        currentPlayer = 'X';
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].setBackground(initialColor);
                buttons[i][j].addActionListener(this);
                panel.add(buttons[i][j]);
            }
        }

        scoreLabel = new JLabel("Player X: 0 - Player O: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        frame.add(scoreLabel, BorderLayout.NORTH);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (clickedButton == buttons[i][j] && buttons[i][j].getText().equals("")) {
                    buttons[i][j].setText(String.valueOf(currentPlayer));
                    buttons[i][j].setEnabled(false);
                    buttons[i][j].setBackground(clickedColor);
                    
                    if (checkWin()) {
                        JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " wins!");
                        updateScore();
                        resetBoard();
                    } else if (isBoardFull()) {
                        JOptionPane.showMessageDialog(null, "The game is a tie!");
                        resetBoard();
                    } else {
                        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                    }
                    return;
                }
            }
        }
    }

    private boolean checkWin() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((buttons[i][0].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[i][1].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[i][2].getText().equals(String.valueOf(currentPlayer))) ||
                    (buttons[0][i].getText().equals(String.valueOf(currentPlayer)) &&
                            buttons[1][i].getText().equals(String.valueOf(currentPlayer)) &&
                            buttons[2][i].getText().equals(String.valueOf(currentPlayer)))) {
                return true;
            }
        }
        // Check diagonals
        if ((buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][2].getText().equals(String.valueOf(currentPlayer))) ||
                (buttons[0][2].getText().equals(String.valueOf(currentPlayer)) &&
                        buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                        buttons[2][0].getText().equals(String.valueOf(currentPlayer)))) {
            return true;
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void updateScore() {
        if (currentPlayer == 'X') {
            playerXScore++;
        } else {
            playerOScore++;
        }
        scoreLabel.setText("Player X: " + playerXScore + " - Player O: " + playerOScore);
    }

    private void resetBoard() {
        currentPlayer = 'X';
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
                buttons[i][j].setBackground(initialColor);
            }
        }
    }

    public static void main(String[] args) {
        Tictactoe t3 = new Tictactoe();
    }
}