package shu.silence.work1;

import javax.swing.*;
import java.awt.event.*;

public class MousePolice implements MouseListener, MouseMotionListener, FocusListener {
    JTextArea area;

    public void setJTextArea(JTextArea area) {
        this.area = area;
    }

    public void mousePressed(MouseEvent e) {
        area.append("\n鼠标按下,位置:" + "(" + e.getX() + "," + e.getY() + ")");
    }

    public void mouseReleased(MouseEvent e) {
        area.append("\n鼠标释放,位置:" + "(" + e.getX() + "," + e.getY() + ")");
    }

    public void mouseEntered(MouseEvent e) {
        if (e.getSource() instanceof JButton)
            area.append("\n鼠标进入按纽,位置:" + "(" + e.getX() + "," + e.getY() + ")");
        if (e.getSource() instanceof JTextField)
            area.append("\n鼠标进入文本框,位置:" + "(" + e.getX() + "," + e.getY() + ")");
        if (e.getSource() instanceof JFrame)
            area.append("\n鼠标进入窗口,位置:" + "(" + e.getX() + "," + e.getY() + ")");
    }

    public void mouseExited(MouseEvent e) {
        area.append("\n鼠标退出,位置:" + "(" + e.getX() + "," + e.getY() + ")");
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() >= 2) {
            area.setText("\n鼠标" + "点击了" + e.getClickCount() + "次，位置:" + "(" + e.getX() + "," + e.getY() + ")");

        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        if (e.getSource() instanceof JButton)
            area.append("\n鼠标在按钮内拖动,位置:" + "(" + e.getX() + "," + e.getY() + ")");
        else if (e.getSource() instanceof JTextField)
            area.append("\n鼠标在文本框中拖动,位置:" + "(" + e.getX() + "," + e.getY() + ")");
        else if (e.getSource() instanceof JFrame)
            area.append("\n鼠标在窗口中拖动,位置:" + "(" + e.getX() + "," + e.getY() + ")");
        else // TODO Auto-generated method stub
            area.append("\n鼠标拖动,位置:" + "(" + e.getX() + "," + e.getY() + ")");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() instanceof JButton)
            area.append("\n鼠标在按钮内移动,位置:" + "(" + e.getX() + "," + e.getY() + ")");
        if (e.getSource() instanceof JTextField)
            area.append("\n鼠标在文本框中移动,位置:" + "(" + e.getX() + "," + e.getY() + ")");
        if (e.getSource() instanceof JFrame)
            area.append("\n鼠标在窗口中移动,位置:" + "(" + e.getX() + "," + e.getY() + ")");
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() instanceof JTextField)
            area.append("\n鼠标在文本框中获得了焦点");
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() instanceof JTextField)
            area.append("\n鼠标在文本框中失去获得了焦点");
    }
}