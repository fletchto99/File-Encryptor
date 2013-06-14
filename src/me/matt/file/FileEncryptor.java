package me.matt.file;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.UIManager;

import me.matt.file.gui.SecureFileFilter;
import me.matt.file.secure.SecureFileLoader;

public class FileEncryptor {

	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		int opt = JOptionPane.showOptionDialog(null,
				"What would you like to do?", "Method",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				new String[] { "Encrypt", "Decrypt" }, "Decrypt");
		if (opt == 0) {
			encrypt();
		} else if (opt == 1) {
			decrypt();
		}
	}

	private static void decrypt() throws Exception {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileFilter(new SecureFileFilter());
		int opt = jfc.showOpenDialog(null);
		if (opt == JFileChooser.APPROVE_OPTION) {
			JPasswordField pf = new JPasswordField();
			int confim = JOptionPane.showConfirmDialog(null, new Object[] {
					"Enter a password: ", pf }, "Enter Password",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			if (confim == JOptionPane.OK_OPTION) {
				int success = SecureFileLoader.load(jfc.getSelectedFile(),
						new String(pf.getPassword()));
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
		JFileChooser jfc = new JFileChooser();
		int opt = jfc.showOpenDialog(null);
		if (opt == JFileChooser.APPROVE_OPTION) {
			JPasswordField pf = new JPasswordField();
			int confim = JOptionPane.showConfirmDialog(null, new Object[] {
					"Enter a password: ", pf }, "Enter Password",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			if (confim == JOptionPane.OK_OPTION) {
				SecureFileLoader.save(jfc.getSelectedFile(),
						new String(pf.getPassword()));
				JOptionPane.showMessageDialog(null,
						"File encrypted successfully!");
			}
		}
	}
}
