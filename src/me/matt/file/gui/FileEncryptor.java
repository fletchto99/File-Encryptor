package me.matt.file.gui;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import me.matt.file.secure.SecureFileLoader;

public class FileEncryptor {

    public static void decryptFile() throws Exception {
        final JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new SecureFileFilter());
        if (jfc.showDialog(null, "Decrypt") == JFileChooser.APPROVE_OPTION) {
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
                            "File decryption failed! Invalid password?");
                }
            }
        }
    }

    public static void encryptFile() throws Exception {
        final JFileChooser jfc = new JFileChooser();
        if (jfc.showDialog(null, "Encrypt") == JFileChooser.APPROVE_OPTION) {
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
}
