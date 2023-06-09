package org.severle.ui;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import lombok.extern.log4j.Log4j2;
import org.severle.core.Project7;
import org.severle.core.factory.ProjectFactory;
import org.severle.core.io.reader.Project7Reader;
import org.severle.core.io.writer.Project7Writer;
import org.severle.system.Initializer;
import org.severle.system.ProjectOpenList;
import org.severle.system.impl.P7FileChooseFilter;
import org.severle.text.Text;
import org.severle.ui.dialog.NotSaveConfirmDialog;
import org.severle.ui.dialog.SettingDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Log4j2
public class MainForm {
    private final ExecutorService executor;

    static {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
    }

    {
        log.info("Init window.");
        this.executor = Executors.newFixedThreadPool(8);
        this.frame = new JFrame(Text.translate("main.title"));
        this.menuBar = new JMenuBar();
        this.frame.setJMenuBar(this.menuBar);
        this.fileChooser = new JFileChooser(new File("./projects"));
        this.fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        this.fileChooser.setMultiSelectionEnabled(false);
        this.fileChooser.addChoosableFileFilter(new P7FileChooseFilter());

        this.notSaveConfirmDialog = new NotSaveConfirmDialog();
        this.settingDialog = new SettingDialog();
    }

    private final JFrame frame;
    private JPanel mainPanel;
    private JToolBar operationAreaToolBar;
    private final JMenuBar menuBar;
    private JMenuItem save;
    private JMenuItem saveAs;
    private final JFileChooser fileChooser;
    private File openFile;
    private Project7 project7;
    private final Project7Reader reader = new Project7Reader();
    private final Project7Writer writer = new Project7Writer();
    private boolean isModify = false;

    private final NotSaveConfirmDialog notSaveConfirmDialog;
    private final SettingDialog settingDialog;

    private void setOpenFile(File file) {
        this.openFile = file;
        this.save.setEnabled(true);
        this.saveAs.setEnabled(true);
        this.frame.setTitle(Text.translate("main.title") + " - " + this.openFile.getName());
    }

    public MainForm() {
        initMenu();
        this.notSaveConfirmDialog.setLocationRelativeTo(this.mainPanel);
    }

    private void initMenu() {
        log.info("Init menu.");
        JMenu menu;
        menu = new JMenu(Text.translate("menu.file.menu.title"));
        menu.setMnemonic(KeyEvent.VK_F);
        initMenuItem(menu, "menu.file.menu.item.new_file.text", this::fileMenuNewItemOnClick, KeyEvent.VK_N);
        initMenuItem(menu, "menu.file.menu.item.open.text", this::fileMenuOpenItemOnClick, KeyEvent.VK_O);
        JMenu m = new JMenu(Text.translate("menu.file.menu.item.open_recent.text"));
        JMenuItem item;
        for (String s : ProjectOpenList.getList()) {
            item = new JMenuItem(s);
            item.addActionListener(e -> {
                executor.submit(() -> {
                    // open file openProjectFile(new File(absolutePath))
                });
            });
            m.add(item);
        }
        menu.add(m);
        menu.addSeparator();
        JMenuItem settingItem = initMenuItem(menu, "menu.file.menu.item.setting.text", this::openSettingForm, KeyEvent.VK_S);
        settingItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.ALT_DOWN_MASK));
        menu.addSeparator();
        this.save = initMenuItem(menu, "menu.file.menu.item.save.text", this::fileMenuSaveItemOnClick, KeyEvent.VK_S);
        this.saveAs = initMenuItem(menu, "menu.file.menu.item.save_as.text", this::fileMenuSaveAsItemOnClick, KeyEvent.VK_A);
        menu.addSeparator();
        // Add 'import' and 'export' item.
        menu.addSeparator();
        initMenuItem(menu, "menu.file.menu.item.exit.text", this::closingWindow, KeyEvent.VK_X);
        save.setEnabled(false);
        saveAs.setEnabled(false);
        this.menuBar.add(menu);


        menu = new JMenu(Text.translate("menu.edit.menu.title"));
        menu.setMnemonic(KeyEvent.VK_E);
        // Add 'Edit' menu component...
        initMenuItem(menu, "menu.edit.menu.item.undo.text", this::undo, KeyEvent.VK_U);
        initMenuItem(menu, "menu.edit.menu.item.redo.text", this::redo, KeyEvent.VK_R);
        menu.addSeparator();
        initMenuItem(menu, "menu.edit.menu.item.copy.text", this::copy, KeyEvent.VK_C);
        initMenuItem(menu, "menu.edit.menu.item.cut.text", this::cut, KeyEvent.VK_T);
        initMenuItem(menu, "menu.edit.menu.item.paste.text", this::paste, KeyEvent.VK_V);
        initMenuItem(menu, "menu.edit.menu.item.delete.text", this::delete, KeyEvent.VK_D);
        menu.addSeparator();
        initMenuItem(menu, "menu.edit.menu.item.select_all.text", this::selectAll, KeyEvent.VK_A);
        this.menuBar.add(menu);

        menu = new JMenu(Text.translate("menu.window.menu.title"));
        menu.setMnemonic(KeyEvent.VK_W);
        // Add 'Window' menu component...

        this.menuBar.add(menu);


        menu = new JMenu(Text.translate("menu.help.menu.title"));
        menu.setMnemonic(KeyEvent.VK_H);
        // Add 'Help' menu component...

        this.menuBar.add(menu);
    }

    private JMenuItem initMenuItem(JMenu menu, String key, Runnable task, int keyEvent) {
        JMenuItem item = new JMenuItem(Text.translate(key), keyEvent);
        item.addActionListener(e -> {
            executor.submit(task);
        });
        menu.add(item);
        return item;
    }

    private void fileMenuNewItemOnClick() {
        // if not save, show not save dialog.
        createNewProjectFile();
    }

    private void createNewProjectFile() {
        log.info("New a project.");
        String repository = Initializer.getProjectRepository();
        String fileName = repository + Initializer.DEFAULT_NEW_FILE_NAME + Initializer.SUFFIX;
        File file = new File(fileName);
        int i = 1;
        while (file.exists()) {
            fileName = repository + Initializer.DEFAULT_NEW_FILE_NAME + " (" + i + ")" + Initializer.SUFFIX;
            file = new File(fileName);
            i++;
        }
        this.setOpenFile(file);
        log.debug(this.openFile);
        this.project7 = ProjectFactory.createBlankProject();
    }

    private void fileMenuOpenItemOnClick() {
        log.info("Open a project.");
        int result = this.fileChooser.showOpenDialog(this.mainPanel);
        if (result == JFileChooser.APPROVE_OPTION) {
            openProjectFile(this.fileChooser.getSelectedFile());
        }
    }

    private void openProjectFile(File file) {
        this.setOpenFile(file);
        log.debug(openFile);
        this.project7 = this.reader.read(this.openFile);
        ProjectOpenList.add(this.openFile.getAbsolutePath());
    }

    private void fileMenuSaveItemOnClick() {
        saveProject();
    }

    private void saveProject() {
        log.info("Save a project.");

        this.writer.write(this.project7, this.openFile);
        this.isModify = false;
    }

    private void fileMenuSaveAsItemOnClick() {
        log.info("Save as a project.");
        int result = this.fileChooser.showSaveDialog(this.mainPanel);
        this.fileChooser.setCurrentDirectory(this.openFile);
        if (result == JFileChooser.APPROVE_OPTION) {
            log.debug("Save.");
            File file = this.fileChooser.getSelectedFile();
            this.writer.write(this.project7, file);
            log.debug("Write OK.");
        }
    }

    private void undo() {
        log.debug("Undo");
    }

    private void redo() {
        log.debug("Redo");
    }

    private void copy() {
        log.debug("Copy");
    }

    private void cut() {
        log.debug("Cut");
    }

    private void paste() {
        log.debug("Paste");
    }

    private void delete() {
        log.debug("Delete");
    }

    private void selectAll() {
        log.debug("Select all");
    }

    private void openSettingForm() {
        log.debug("Open setting form");
        this.settingDialog.setVisible(true);
    }

    private void closingWindow() {
        log.info("Closing window.");
        if (this.isModify) {
            // Show Not Save Dialog.
            this.notSaveConfirmDialog.setVisible(true);
            int status = this.notSaveConfirmDialog.getStatus();
            if (status == 0) {
                // Save and exit
                log.debug("Saving...");
                saveProject();
                log.debug("Save Success.");
                exit();
            } else if (status == 1) {
                // Exit with no save
                exit();
            }
        } else {
            exit();
        }
    }

    private void exit() {
        log.debug("Exiting...");
        this.executor.shutdownNow();
        System.exit(0);
    }

    public void start() {
        log.info("Main form start.");
        this.frame.setContentPane(mainPanel);
        this.frame.setSize(new Dimension(800, 600));
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closingWindow();
            }
        });
        this.frame.setVisible(true);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        final Spacer spacer1 = new Spacer();
        mainPanel.add(spacer1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        operationAreaToolBar = new JToolBar();
        mainPanel.add(operationAreaToolBar, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 20), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
