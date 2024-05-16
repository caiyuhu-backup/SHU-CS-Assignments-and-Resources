package shu.silence.work1;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class WindowMenu extends JFrame {
    JMenuBar menubar;
    JMenu docMenu, editMenu, viewMenu, helpMenu, subMenu;
    JMenuItem itemNewFile, itemOpenFile, itemCloseFile, itemSaveFile, itemFileSaveAs, itemExit;

    public WindowMenu() {
    }

    public WindowMenu(String s, int x, int y, int w, int h) {
        init(s);
        setLocation(x, y);
        setSize(w, h);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    void init(String s) {
        setTitle(s);             //设置窗口的标题
        menubar = new JMenuBar();
        docMenu = new JMenu("文件");
        editMenu = new JMenu("编辑");
        viewMenu = new JMenu("视图");
        helpMenu = new JMenu("帮助");
        itemNewFile = new JMenuItem("新建文件");
        itemOpenFile = new JMenuItem("打开文件");
        itemCloseFile = new JMenuItem("关闭文件");
        itemSaveFile = new JMenuItem("保存文件");
        itemFileSaveAs = new JMenuItem("文件另存为");
        itemExit = new JMenuItem("退出");
        subMenu = new JMenu("文件格式");
        itemNewFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        itemOpenFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        itemCloseFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        itemSaveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        itemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));


        docMenu.add(itemNewFile);
        docMenu.add(itemOpenFile);
        docMenu.add(itemCloseFile);
        docMenu.addSeparator();   //在菜单之间增加分隔线
        docMenu.add(subMenu);
        docMenu.add(itemSaveFile);
        docMenu.add(itemFileSaveAs);
        docMenu.addSeparator();   //在菜单之间增加分隔线
        docMenu.add(itemExit);


        subMenu.add(new JCheckBox("Java文件"));
        subMenu.add(new JCheckBox("Txt文件"));

        subMenu.addSeparator();   //在菜单之间增加分隔线

        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton button1 = new JRadioButton("只读");
        JRadioButton button2 = new JRadioButton("可写");
        buttonGroup.add(button1);
        buttonGroup.add(button2);
        subMenu.add(button1);
        subMenu.add(button2);

        menubar.add(docMenu);
        menubar.add(editMenu);
        menubar.add(viewMenu);
        menubar.add(helpMenu);
        setJMenuBar(menubar);
    }
}