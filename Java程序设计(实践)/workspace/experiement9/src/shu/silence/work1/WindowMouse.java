package shu.silence.work1;

import javax.swing.*;
import java.awt.*;

public class WindowMouse extends JFrame {
    JTextField text;
    JButton button;
    JTextArea textArea;
    MousePolice police;

    WindowMouse() {
        init();
        setBounds(100, 100, 420, 220);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void init() {
        setLayout(new FlowLayout());
        text = new JTextField(8);
        textArea = new JTextArea(15, 30);

        police = new MousePolice();
        police.setJTextArea(textArea);

        text.addMouseListener(police);
        text.addMouseMotionListener(police);
        text.addFocusListener(police);

        button = new JButton("按钮");
        button.addMouseListener(police);
        button.addMouseMotionListener(police);
        button.addFocusListener(police);

        addMouseListener(police);
        addMouseMotionListener(police);
        addFocusListener(police);

        add(button);
        add(text);
        add(new JScrollPane(textArea));
    }
}