package decoder.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import decoder.core.StageManager;
import javax.swing.JOptionPane;

public class DecoderFrame extends JFrame {

    private final Color PURPLE = new Color(98, 24, 238);
    private final Color TEXT = new Color(25, 25, 30);
    private final Color MUTED = new Color(125, 125, 135);
    private final Color ERROR = new Color(255, 55, 65);

    private JTextArea txtInput;
    private JTextArea txtResult;
    private JLabel lblError;

    private JButton btnExecute;
    private JButton btnClear;

    private String selectedStage = "Binary to Decimal";
    private final StageManager stageManager = new StageManager();

    public DecoderFrame() {
        setTitle("Java Decoder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(850, 600));
        setSize(1200, 800);
        setLocationRelativeTo(null);

        setContentPane(createMainLayout());

    }

    private JPanel createMainLayout() {
        JPanel root = new JPanel(new BorderLayout(24, 0));
        root.setBackground(new Color(248, 248, 250));
        root.setBorder(new EmptyBorder(24, 24, 24, 24));

        root.add(createSidebar(), BorderLayout.WEST);
        root.add(createWorkspace(), BorderLayout.CENTER);

        return root;
    }

    // LEFT SIDEBAR
    private JPanel createSidebar() {
        RoundedPanel sidebar = new RoundedPanel(
                Color.WHITE, 32
        );

        sidebar.setLayout(new BoxLayout(
                sidebar, BoxLayout.Y_AXIS
        ));

        sidebar.setPreferredSize(new Dimension(290, 0));
        sidebar.setBorder(new EmptyBorder(30, 24, 30, 24));

        JLabel title = new JLabel("Code");
        title.setFont(new Font("SansSerif", Font.PLAIN, 36));
        title.setForeground(TEXT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        sidebar.add(title);
        sidebar.add(Box.createVerticalStrut(50));

        addStageButton(sidebar, "Binary to Decimal");
        sidebar.add(Box.createVerticalStrut(28));

        addStageButton(sidebar, "Hexcimal to Decimal");
        sidebar.add(Box.createVerticalStrut(28));

        addStageButton(sidebar, "Morse Code");
        sidebar.add(Box.createVerticalStrut(28));

        addStageButton(sidebar, "Soon");

        sidebar.add(Box.createVerticalGlue());

        return sidebar;
    }

    private void addStageButton(JPanel sidebar, String stageName) {
        RoundedButton button = new RoundedButton(stageName, 16);

        button.setFont(new Font(
                "SansSerif", Font.PLAIN, 19
        ));

        button.setForeground(TEXT);
        button.setBackground(Color.WHITE);
        button.setPreferredSize(new Dimension(242, 64));
        button.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 64)
        );
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.addActionListener((ActionEvent e) -> {
            selectedStage = stageName;
            lblError.setText(" ");

            // Highlight the selected operation.
            for (Component c : sidebar.getComponents()) {
                if (c instanceof RoundedButton) {
                    RoundedButton b = (RoundedButton) c;
                    b.setSelected(stageName.equals(b.getText()));
                }
            }
        });

        sidebar.add(button);
    }

    // RIGHT WORKSPACE
    private JPanel createWorkspace() {
        JPanel workspace = new JPanel(new BorderLayout(0, 28));
        workspace.setOpaque(false);

        JLabel title = new JLabel("Java Decoder");
        title.setFont(new Font("SansSerif", Font.PLAIN, 38));
        title.setForeground(TEXT);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        workspace.add(title, BorderLayout.NORTH);
        workspace.add(createEditorPanel(), BorderLayout.CENTER);

        return workspace;
    }

    private JPanel createEditorPanel() {
        RoundedPanel editor = new RoundedPanel(
                Color.WHITE, 42
        );

        editor.setLayout(new GridBagLayout());
        editor.setBorder(new EmptyBorder(30, 36, 20, 36));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 10, 0);

        // INPUT LABEL
        gbc.gridy = 0;
        gbc.weighty = 0;
        editor.add(createLabel("Input"), gbc);

        // INPUT AREA
        txtInput = new JTextArea();
        txtInput.setFont(new Font("Monospaced", Font.PLAIN, 18));
        txtInput.setLineWrap(true);
        txtInput.setWrapStyleWord(true);
        txtInput.setOpaque(false);
        txtInput.setBorder(new EmptyBorder(15, 18, 15, 18));

        gbc.gridy = 1;
        gbc.weighty = 0.35;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 26, 0);
        editor.add(createTextBox(txtInput, 30), gbc);

        // EXECUTE BUTTON
        btnExecute = new RoundedButton("Execute", 30);
        btnExecute.setFont(new Font(
                "SansSerif", Font.BOLD, 24
        ));
        btnExecute.setForeground(Color.WHITE);
        btnExecute.setBackground(PURPLE);
        btnExecute.setBorderPainted(false);
        btnExecute.addActionListener(e -> executeDecoder());

        // CLEAR BUTTON
        btnClear = new RoundedButton("Clear", 30);
        btnClear.setFont(new Font(
                "SansSerif", Font.PLAIN, 24
        ));
        btnClear.setForeground(TEXT);
        btnClear.setBackground(Color.WHITE);

        JPanel buttons = new JPanel(new GridLayout(1, 2, 20, 0));
        buttons.setOpaque(false);
        buttons.add(btnExecute);
        buttons.add(btnClear);

        gbc.gridy = 2;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 28, 0);
        editor.add(buttons, gbc);

        // RESULT LABEL
        gbc.gridy = 3;
        gbc.weighty = 0;
        gbc.insets = new Insets(0, 8, 10, 0);
        editor.add(createLabel("Result"), gbc);

        // RESULT AREA
        txtResult = new JTextArea();
        txtResult.setEditable(false);
        txtResult.setFont(new Font(
                "Monospaced", Font.PLAIN, 18
        ));
        txtResult.setLineWrap(true);
        txtResult.setWrapStyleWord(true);
        txtResult.setOpaque(false);
        txtResult.setBorder(new EmptyBorder(15, 18, 15, 18));

        gbc.gridy = 4;
        gbc.weighty = 0.35;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 18, 0);
        editor.add(createTextBox(txtResult, 30), gbc);

        // ERROR MESSAGE
        lblError = new JLabel(" ");
        lblError.setFont(new Font(
                "SansSerif", Font.PLAIN, 20
        ));
        lblError.setForeground(ERROR);
        lblError.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        gbc.gridy = 5;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 0, 0);
        editor.add(lblError, gbc);

        btnClear.addActionListener(e -> {
            txtInput.setText("");
            txtResult.setText("");
            lblError.setText(" ");

        });
        return editor;
    }

    private void executeDecoder() {
        String input = txtInput.getText().trim();

        if (input.isEmpty()) {
            lblError.setText("Please enter an input value.");
            txtResult.setText("");
            return;
        }

        try {
            String result = stageManager.execute(
                    selectedStage,
                    input
            );

            txtResult.setText(result);
            lblError.setText(" ");

        } catch (NumberFormatException ex) {
            txtResult.setText("");
            lblError.setText("Invalid input for " + selectedStage);

        } catch (IllegalArgumentException ex) {
            txtResult.setText("");
            lblError.setText(ex.getMessage());
        }
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.PLAIN, 27));
        label.setForeground(TEXT);
        return label;
    }

    private JPanel createTextBox(
            JTextArea area, int radius
    ) {
        RoundedPanel box = new RoundedPanel(
                Color.WHITE, radius
        );

        box.setLayout(new BorderLayout());
        box.setBorder(BorderFactory.createLineBorder(
                new Color(25, 25, 25), 2, true
        ));

        box.add(area, BorderLayout.CENTER);
        return box;
    }

    // ROUNDED PANEL
    private static class RoundedPanel extends JPanel {

        private final Color backgroundColor;
        private final int radius;

        RoundedPanel(Color color, int radius) {
            this.backgroundColor = color;
            this.radius = radius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(backgroundColor);
            g2.fillRoundRect(
                    0, 0, getWidth(), getHeight(),
                    radius, radius
            );

            g2.dispose();
            super.paintComponent(g);
        }
    }

    // ROUNDED BUTTON
    private static class RoundedButton extends JButton {

        private final int radius;
        private boolean selected;

        RoundedButton(String text, int radius) {
            super(text);
            this.radius = radius;

            setFocusPainted(false);
            setContentAreaFilled(false);
            setOpaque(false);
            setBorderPainted(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setBorder(new EmptyBorder(10, 12, 10, 12));
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            Color fill = getBackground();

            if (getModel().isPressed()) {
                fill = fill.darker();
            } else if (selected) {
                fill = new Color(239, 230, 255);
            } else if (getModel().isRollover()) {
                fill = new Color(246, 242, 255);
            }

            g2.setColor(fill);
            g2.fillRoundRect(
                    0, 0, getWidth() - 1, getHeight() - 1,
                    radius, radius
            );

            g2.setColor(new Color(30, 30, 30));
            g2.setStroke(new BasicStroke(1.2f));
            g2.drawRoundRect(
                    0, 0, getWidth() - 1, getHeight() - 1,
                    radius, radius
            );

            g2.dispose();
            super.paintComponent(g);
        }
    }
}
