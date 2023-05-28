import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClockGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private JLabel clockLabel = new JLabel();
    private JLabel dateLabel = new JLabel();
    private JLabel dayNightLabel = new JLabel();
    private JPanel panel = new JPanel();
    private JButton modeButton = new JButton("Switch Mode");
    private boolean isDarkMode = true;

    ClockGUI() {
        clockLabel.setHorizontalAlignment(JLabel.CENTER);
        clockLabel.setFont(new Font("Verdana", Font.PLAIN, 35));
        clockLabel.setOpaque(true);

        dateLabel.setHorizontalAlignment(JLabel.CENTER);
        dateLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        dateLabel.setOpaque(true);

        dayNightLabel.setHorizontalAlignment(JLabel.CENTER);
        dayNightLabel.setFont(new Font("Verdana", Font.PLAIN, 25));
        dayNightLabel.setOpaque(true);

        panel.setLayout(new BorderLayout());

        modeButton.addActionListener(e -> switchMode());

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Digital Clock");
        this.setLayout(new BorderLayout());
        this.setSize(300,300);

        this.add(dateLabel, BorderLayout.NORTH);
        panel.add(clockLabel, BorderLayout.CENTER);
        panel.add(dayNightLabel, BorderLayout.SOUTH);

        this.add(panel, BorderLayout.CENTER);
        this.add(modeButton, BorderLayout.SOUTH);

        this.setVisible(true);

        Timer timer = new Timer(1000, e -> {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            clockLabel.setText(now.format(timeFormatter));
            dateLabel.setText(now.format(dateFormatter));
            if (now.getHour() >= 6 && now.getHour() < 18) {
                dayNightLabel.setText("It's Day");
                dayNightLabel.setBackground(new Color(135, 206, 235));
            } else {
                dayNightLabel.setText("It's Night");
                dayNightLabel.setBackground(new Color(75, 0, 130));
            }
        });
        timer.start();

        switchMode();
    }

    private void switchMode() {
        if (isDarkMode) {
            clockLabel.setBackground(Color.BLACK);
            clockLabel.setForeground(Color.WHITE);
            dateLabel.setBackground(Color.BLACK);
            dateLabel.setForeground(Color.WHITE);
            panel.setBackground(Color.BLACK);
        } else {
            clockLabel.setBackground(Color.WHITE);
            clockLabel.setForeground(Color.BLACK);
            dateLabel.setBackground(Color.WHITE);
            dateLabel.setForeground(Color.BLACK);
            panel.setBackground(Color.WHITE);
        }
        isDarkMode = !isDarkMode;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClockGUI::new);
    }
}
