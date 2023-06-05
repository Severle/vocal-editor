package org.severle.system.impl;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class P7FileChooseFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        return f.getName().toLowerCase().endsWith(".p7");
    }

    @Override
    public String getDescription() {
        return "Vocal Editor文件(*.p7)";
    }
}
