package me.matt.file;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.UIManager;

import me.matt.file.gui.SecureFileFilter;
import me.matt.file.secure.SecureFileLoader;

public class FileEncryptor {

    private static void decrypt() throws Exception {
        final JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new SecureFileFilter());
        final int opt = jfc.showOpenDialog(null);
        if (opt == JFileChooser.APPROVE_OPTION) {
            final JPasswordField pf = new JPasswordField();
            final int confim = JOptionPane.showConfirmDialog(null,
                    new Object[] { "Enter a password: ", pf },
                    "Enter Password", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
            if (confim == JOptionPane.OK_OPTION) {
                final int success = SecureFileLoader.load(
                        jfc.getSelectedFile(), new String(pf.getPassword()));
                if (success == SecureFileLoader.DECRYPTION_SUCCESSFUL) {
                    JOptionPane.showMessageDialog(null,
                            "File decrypted successfully!");
                } else if (success == SecureFileLoader.DECRYPTION_FAILED) {
                    JOptionPane.showMessageDialog(null,
                            "File decryption failed!");
                }
            }
        }
    }

    private static void encrypt() throws Exception {
        final JFileChooser jfc = new JFileChooser();
        final int opt = jfc.showOpenDialog(null);
        if (opt == JFileChooser.APPROVE_OPTION) {
            final JPasswordField pf = new JPasswordField();
            final int confim = JOptionPane.showConfirmDialog(null,
                    new Object[] { "Enter a password: ", pf },
                    "Enter Password", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
            if (confim == JOptionPane.OK_OPTION) {
                SecureFileLoader.save(jfc.getSelectedFile(),
                        new String(pf.getPassword()));
                JOptionPane.showMessageDialog(null,
                        "File encrypted successfully!");
            }
        }
    }

    public static void main(final String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        final int opt = JOptionPane.showOptionDialog(null,
                "What would you like to do?", "Method",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new String[] { "Encrypt", "Decrypt" }, "Decrypt");
        if (opt == 0) {
            FileEncryptor.encrypt();
        } else if (opt == 1) {
            FileEncryptor.decrypt();
        }
    }
}
