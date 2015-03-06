package me.matt.file;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import me.matt.file.gui.FileEncryptor;

public class Application {
    
    public static void main(final String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        final int opt = JOptionPane.showOptionDialog(null,
                "What would you like to do?", "Method",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new String[] { "Encrypt", "Decrypt" }, "Decrypt");
        if (opt == 0) {
            FileEncryptor.encryptFile();
        } else if (opt == 1) {
            FileEncryptor.decryptFile();
        }
    }

}
