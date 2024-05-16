package shu.silence.work2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowInput extends JFrame implements ActionListener {
    JTextArea showResult;
    JButton openInput;

    WindowInput() {
        openInput = new JButton("弹出输入对话框");
        showResult = new JTextArea();
        add(openInput, BorderLayout.NORTH);
        add(new JScrollPane(showResult), BorderLayout.CENTER);

        openInput.addActionListener(this);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        String str = JOptionPane.showInputDialog(this, "输入相关信息至主窗口", "输入对话框",
                JOptionPane.INFORMATION_MESSAGE);
        if (str != null) {
            showResult.append(str);
        }
    }
}