import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame {
    private static final long serialVersionUID = 1L;
    private char currentSymbol;
    private JButton[] buttons;
    private boolean gameEnded;

    TicTacToe() {
        currentSymbol = 'X';
        buttons = new JButton[9];
        gameEnded = false;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 300);
        this.setLayout(new GridLayout(3, 3));

        initializeButtons();

        this.setVisible(true);
    }

    private void initializeButtons() {
        for(int i=0; i<=8; i++) {
            buttons[i] = new JButton();
            buttons[i].setText("");
            buttons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton buttonClicked = (JButton)e.getSource();
                    buttonClicked.setText(String.valueOf(currentSymbol));
                    buttonClicked.setEnabled(false);
                    checkForWin();
                    if(!gameEnded) {
                        checkForDraw();
                    }
                    if(!gameEnded) {
                        switchSymbol();
                    }
                }
            });

            this.add(buttons[i]);
        }
    }

    private void switchSymbol() {
        currentSymbol = (currentSymbol == 'X') ? 'O' : 'X';
    }

    private void checkForWin() {
        //check horizontal lines
        for (int i = 0; i < 9; i += 3)
            if (checkLine(i, i+1, i+2))
                endGame(buttons[i].getText());

        //check vertical lines
        for (int i = 0; i < 3; ++i)
            if (checkLine(i, i+3, i+6))
                endGame(buttons[i].getText());

        //check the diagonals
        if (checkLine(0, 4, 8))
            endGame(buttons[0].getText());

        if (checkLine(2, 4, 6))
            endGame(buttons[2].getText());
    }

    private boolean checkLine(int a, int b, int c) {
        return buttons[a].getText().equals(buttons[b].getText()) &&
               buttons[b].getText().equals(buttons[c].getText()) &&
               !buttons[a].getText().equals("");
    }

    private void checkForDraw() {
        for (JButton button : buttons) {
            if(button.isEnabled()) {
                return;
            }
        }
        endGame("Draw");
    }

    private void endGame(String result) {
        gameEnded = true;
        if(result.equals("Draw")) {
            JOptionPane.showConfirmDialog(null, "The game was a draw!", "Game Over", JOptionPane.DEFAULT_OPTION);
        } else {
            JOptionPane.showConfirmDialog(null, String.format("%s wins!", result), "Game Over", JOptionPane.DEFAULT_OPTION);
        }
        this.dispose();
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
