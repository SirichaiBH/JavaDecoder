package decoder.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import decoder.core.StageManager;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

//GUI thanks so much Chat gpt 
public class DecoderFrame extends JFrame {
//gui 1 ui color
    private final Color PURPLE = new Color(98, 24, 238);
    private final Color TEXT = new Color(25, 25, 30);
    private final Color MUTED = new Color(125, 125, 135);
    private final Color ERROR = new Color(255, 55, 65);
//gui 2 gui matiral
    private JTextArea txtInput;
    private JTextArea txtResult;
    private JLabel lblError;

    private JButton btnExecute;
    private JButton btnClear;
    private JButton btnCopy;
// get state and state manager
    private String selectedStage = "Binary to Decimal";
    private final StageManager stageManager = new StageManager();
//gui name size and position
    public DecoderFrame() {
        setTitle("Java Decoder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(850, 600));
        setSize(1200, 800);
        setLocationRelativeTo(null);

        setContentPane(createMainLayout());

    }
// main layout
    private JPanel createMainLayout() {
        JPanel root = new JPanel(new BorderLayout(24, 0));
        root.setBackground(new Color(248, 248, 250));
        root.setBorder(new EmptyBorder(24, 24, 24, 24));

        root.add(createSidebar(), BorderLayout.WEST);
        root.add(createWorkspace(), BorderLayout.CENTER);

        return root;
    }

// slidebar for select state
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

        addStageButton(sidebar, "Hexadecimal to Decimal");
        sidebar.add(Box.createVerticalStrut(28));

        addStageButton(sidebar, "Morse Code");
        sidebar.add(Box.createVerticalStrut(28));

        addStageButton(sidebar, "Soon");

        sidebar.add(Box.createVerticalGlue());

        return sidebar;
    }
// creat button 
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
//select mode of state button 
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
        }); //----

        sidebar.add(button);
    }

//decode window
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
//creat input resutl button and label
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
        txtInput.setLineWrap(true);
        txtInput.setWrapStyleWord(true);
        txtInput.setOpaque(false);
        txtInput.setBorder(new EmptyBorder(15, 18, 15, 18));
        JScrollPane inputScroll = new JScrollPane(txtInput);
        inputScroll.setBorder(null);
        inputScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        gbc.gridy = 1;
        gbc.weighty = 0.35;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 26, 0);
        editor.add(createTextBox(inputScroll, 30), gbc);

        // EXECUTE BUTTON
        btnExecute = new RoundedButton("Execute", 30);
        btnExecute.setFont(new Font(
                "SansSerif", Font.BOLD, 24
        ));
        btnExecute.setForeground(Color.WHITE);
        btnExecute.setBackground(PURPLE);
        btnExecute.setBorderPainted(false);
        btnExecute.addActionListener(e -> executeDecoder()); //run when click execute button

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

// RESULT HEADER
        JPanel resultPanel = new JPanel(new BorderLayout(0, 8));
        resultPanel.setOpaque(false);

// ชื่อ Result ด้านซ้าย
        JLabel lblResultTitle = createLabel("Result");

// ปุ่ม Copy ด้านขวา
        btnCopy = new JButton("Copy");
        btnCopy.setFocusPainted(false);
        btnCopy.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCopy.setPreferredSize(new Dimension(70, 30));
        btnCopy.addActionListener(e -> copyResult());

        JPanel resultHeader = new JPanel(new BorderLayout());
        resultHeader.setOpaque(false);
        resultHeader.add(lblResultTitle, BorderLayout.WEST);
        resultHeader.add(btnCopy, BorderLayout.EAST);

// สร้างพื้นที่แสดงผลลัพธ์
        txtResult = new JTextArea();
        txtResult.setFont(new Font("Monospaced", Font.PLAIN, 18));
        txtResult.setLineWrap(true);
        txtResult.setWrapStyleWord(true);
        txtResult.setEditable(false);
        txtResult.setBorder(new EmptyBorder(15, 18, 15, 18));
        txtResult.setBackground(Color.WHITE);

        JScrollPane resultScroll = new JScrollPane(txtResult);
        resultScroll.setBorder(null);
        resultScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

// กล่องผลลัพธ์พร้อมเส้นขอบ
        JPanel resultBox = createTextBox(resultScroll, 30);

// จัดวาง Header และ Result Box
        resultPanel.add(resultHeader, BorderLayout.NORTH);
        resultPanel.add(resultBox, BorderLayout.CENTER);

// เพิ่ม Result Panel ลงใน Editor
        gbc.gridy = 3;
        gbc.weighty = 0.65;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 20, 0);
        editor.add(resultPanel, gbc);

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
//clear
        btnClear.addActionListener(e -> {
        txtInput.setText("");
        txtResult.setText("");
        showStatus(" ", "normal");
        });
        return editor;
    }
//check input execute decoder and exceptioin
    private void executeDecoder() {
        String input = txtInput.getText().trim();

        if (input.isEmpty()) {
            txtResult.setText("");
            showStatus("Please enter an input value.", "error");
            return;
        }

        try {
            String result = stageManager.execute(
                    selectedStage,
                    input
            );

            txtResult.setText(result);
            showStatus(" ", "normal");

        } catch (NumberFormatException ex) {
            txtResult.setText("");
            showStatus("Invalid input for " + selectedStage, "error");

        } catch (IllegalArgumentException ex) {
            txtResult.setText("");
            showStatus(ex.getMessage(), "error");
        }
    }
//label
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.PLAIN, 27));
        label.setForeground(TEXT);
        return label;
    }
//some gui
    private JPanel createTextBox(JComponent component, int radius) {
        RoundedPanel box = new RoundedPanel(Color.WHITE, radius);

        box.setLayout(new BorderLayout());
        box.setBorder(BorderFactory.createLineBorder(
                new Color(25, 25, 25), 2, true
        ));

        box.add(component, BorderLayout.CENTER);
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
//copy button
    private void copyResult() {
        String result = txtResult.getText();

        if (result == null || result.trim().isEmpty()) {
            showStatus("No result to copy.", "error");
            return;
        }

        try {
            java.awt.datatransfer.StringSelection selection
                    = new java.awt.datatransfer.StringSelection(result);

            java.awt.datatransfer.Clipboard clipboard
                    = java.awt.Toolkit.getDefaultToolkit()
                            .getSystemClipboard();

            clipboard.setContents(selection, null);

            showStatus("Copied to clipboard!", "success");

        } catch (Exception ex) {
            showStatus("Unable to copy result.", "error");
        }
    }
//status
    private void showStatus(String message, String type) {
        lblError.setText(message);

        switch (type) {
            case "success" ->
                lblError.setForeground(new Color(22, 163, 74));

            case "error" ->
                lblError.setForeground(new Color(220, 38, 38));

            default ->
                lblError.setForeground(MUTED);
        }
    }
}
