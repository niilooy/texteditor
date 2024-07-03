package com.example.texteditor.gui;

import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.io.*;
import java.nio.file.*;

@Component
public class TextEditorActions {

    private JTextArea textArea;
    private UndoManager undoManager;

    public TextEditorActions() {
        this.undoManager = new UndoManager();
    }

    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
        this.textArea.getDocument().addUndoableEditListener(undoManager);
    }

    public void newFile() {
        textArea.setText("");
        undoManager.discardAllEdits();
    }

    public void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            Path file = fileChooser.getSelectedFile().toPath();
            try {
                String content = Files.readString(file);
                textArea.setText(content);
                undoManager.discardAllEdits();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            Path file = fileChooser.getSelectedFile().toPath();
            try {
                Files.writeString(file, textArea.getText());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void undo() {
        if (undoManager.canUndo()) {
            undoManager.undo();
        }
    }

    public void redo() {
        if (undoManager.canRedo()) {
            undoManager.redo();
        }
    }

    public void changeFont() {
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        String selectedFont = (String) JOptionPane.showInputDialog(null, "Select a font:", "Font Selection",
                JOptionPane.QUESTION_MESSAGE, null, fonts, textArea.getFont().getFamily());
        if (selectedFont != null) {
            textArea.setFont(new Font(selectedFont, Font.PLAIN, textArea.getFont().getSize()));
        }
    }

    public void changeColor() {
        Color color = JColorChooser.showDialog(null, "Choose Text Color", textArea.getForeground());
        if (color != null) {
            textArea.setForeground(color);
        }
    }
}