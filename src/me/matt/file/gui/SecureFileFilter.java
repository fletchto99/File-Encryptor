package me.matt.file.gui;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class SecureFileFilter extends FileFilter {

    @Override
    public boolean accept(final File f) {
        if (f.isDirectory()) {
            return true;
        }
        return f.getName().toLowerCase().endsWith(".jcrypt");
    }

    @Override
    public String getDescription() {
        return "Encrypted files";
    }

}
