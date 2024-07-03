package com.example.texteditor.gui;

import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@Component
public class TextEditorGUI {

    private JFrame frame;
    private JTextArea textArea;
    private JMenuBar menuBar;
    private TextEditorActions actions;

    public TextEditorGUI(TextEditorActions actions) {
        this.actions = actions;
    }

    public void createAndShowGUI() {
        frame = new JFrame("Spring Boot Text Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        textArea = new JTextArea();
        actions.setTextArea(textArea); // Set the text area in TextEditorActions
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        createMenuBar();

        frame.setVisible(true);
    }

    private void createMenuBar() {
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu formatMenu = new JMenu("Format");

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(formatMenu);

        // File menu items
        addMenuItem(fileMenu, "New", e -> actions.newFile());
        addMenuItem(fileMenu, "Open", e -> actions.openFile());
        addMenuItem(fileMenu, "Save", e -> actions.saveFile());
        addMenuItem(fileMenu, "Exit", e -> System.exit(0));

        // Edit menu items
        addMenuItem(editMenu, "Undo", e -> actions.undo());
        addMenuItem(editMenu, "Redo", e -> actions.redo());
        addMenuItem(editMenu, "Cut", e -> textArea.cut());
        addMenuItem(editMenu, "Copy", e -> textArea.copy());
        addMenuItem(editMenu, "Paste", e -> textArea.paste());

        // Format menu items
        addMenuItem(formatMenu, "Font", e -> actions.changeFont());
        addMenuItem(formatMenu, "Color", e -> actions.changeColor());
    }

    private void addMenuItem(JMenu menu, String label, ActionListener listener) {
        JMenuItem menuItem = new JMenuItem(label);
        menuItem.addActionListener(listener);
        menu.add(menuItem);
    }

    public JTextArea getTextArea() {
        return textArea;
    }
}