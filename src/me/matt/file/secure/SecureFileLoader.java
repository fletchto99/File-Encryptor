package me.matt.file.secure;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SecureFileLoader {

    public static int load(final File file, final String password)
            throws Exception {
        try {
            final byte[] data = SecurityUtility.decrypt(
                    Files.readAllBytes(Paths.get(file.getAbsolutePath())),
                    password);
            if (data == null) {
                return SecureFileLoader.DECRYPTION_FAILED;
            }
            final File decrypted = new File(file.getAbsolutePath().replace(
                    ".jcrypt", ""));
            if (!decrypted.exists()) {
                decrypted.createNewFile();
            }
            final FileOutputStream fos = new FileOutputStream(decrypted);
            try {
                fos.write(data);
            } finally {
                fos.flush();
                fos.close();
            }
            return SecureFileLoader.DECRYPTION_SUCCESSFUL;
        } catch (final Exception e) {
            return SecureFileLoader.DECRYPTION_FAILED;
        }
    }

    public static void save(final File file, final String password)
            throws Exception {
        final File encrypted = new File(file.getAbsolutePath() + ".jcrypt");
        if (!encrypted.exists()) {
            encrypted.createNewFile();
        }
        final FileOutputStream fos = new FileOutputStream(encrypted);
        try {
            fos.write(SecurityUtility.encrypt(
                    Files.readAllBytes(Paths.get(file.getAbsolutePath())),
                    password));
        } finally {
            fos.flush();
            fos.close();
        }
    }

    public static int DECRYPTION_SUCCESSFUL = 1;

    public static int DECRYPTION_FAILED = 2;
}
