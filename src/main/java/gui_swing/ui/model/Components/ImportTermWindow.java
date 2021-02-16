package gui_swing.ui.model.Components;

import gui_swing.ui.model.ApiConnector;
import gui_swing.ui.model.ConfigManager;
import gui_swing.ui.model.tableModels.GradientButton;
import org.docx4j.Docx4J;
import org.docx4j.Docx4jProperties;
import org.docx4j.convert.out.HTMLSettings;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ImportTermWindow extends JFrame {

    private ApiConnector apiConnector;
    private Dimension dim;
    private Dimension widthDim;
    private JPanel mainJPanel;
    private JPanel topJPanel;
    private JPanel centerJPanel;
    private JPanel leftJPanel;
    private JPanel rightJPanel;
    private JPanel bottomJPanel;
    private JFileChooser fileChooser;
    private JTextField textField;
    private DropTarget dropTarget;
    private JCheckBox topJCheckBox;
    private JCheckBox bottomJCheckBox;
    private  JButton importButton;
    private File importedFile;
    private String importedFileHtml;
    private ImportTermWindow importTermWindow;

    public ImportTermWindow()  {
        super();
        initComponents();
        showForm();

    }
    private void showForm() {
        this.setMinimumSize(widthDim);

        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.pack();
        this.setVisible(true);
        this.setTitle("Import plików Word");
    }
    private void initComponents() {
        importTermWindow = this;
        apiConnector = new ApiConnector();
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        widthDim = new Dimension((int) (dim.width*0.8), (int) (dim.height * 0.8));
        mainJPanel=new JPanel(new BorderLayout());
        getContentPane().add(mainJPanel);
        centerJPanel = new JPanel(new GridLayout(0,1));
        mainJPanel.add(centerJPanel,BorderLayout.CENTER);
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(widthDim.width, 40));
        centerJPanel.add(textField);
        bottomJPanel = new JPanel();
        mainJPanel.add(bottomJPanel,BorderLayout.SOUTH);
        fileChooser = new JFileChooser();
        fileChooser.setControlButtonsAreShown(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Pliki Word","doc","docx");
        fileChooser.setFileFilter(filter);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setCurrentDirectory(new File(ConfigManager.getTempFolder()));
        centerJPanel.add(fileChooser);

        enableDragAndDrop();
        leftJPanel = new JPanel(new GridLayout(0,1));
        mainJPanel.add(leftJPanel,BorderLayout.WEST);
        //disableDragAndDrop();
        topJCheckBox = new JCheckBox("Drag & Drop");
        bottomJCheckBox = new JCheckBox("Znajdź na dysku");
        leftJPanel.add(topJCheckBox);

        leftJPanel.add(bottomJCheckBox);
        bottomJCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(bottomJCheckBox.isSelected()){
                    fileChooser.setVisible(true);
                    topJCheckBox.setSelected(false);
                    textField.setVisible(false);
                }else
                {
                    fileChooser.setVisible(false);
                    topJCheckBox.setSelected(true);
                    textField.setVisible(true);
                }
            }
        });
        topJCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(topJCheckBox.isSelected()){
                    textField.setVisible(true);
                    bottomJCheckBox.setSelected(false);
                    fileChooser.setVisible(false);
                }else{
                    textField.setVisible(false);
                    bottomJCheckBox.setSelected(true);
                    fileChooser.setVisible(true);
                }
            }
        });

        bottomJCheckBox.setSelected(true);
        textField.setVisible(false);
        topJCheckBox.setSelected(false);

        importButton = new GradientButton("Importuj",Color.GREEN.brighter());
        bottomJPanel.add(importButton);
        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(topJCheckBox.isSelected()){
                    importedFile =new File(textField.getText());
                }else{
                    if(fileChooser.getSelectedFile()==null){
                        JOptionPane.showMessageDialog(importTermWindow,"Nie wybrano pliku.","Błąd odczytu pliku",JOptionPane.ERROR_MESSAGE);
                    }else{
                        importedFile = fileChooser.getSelectedFile().getAbsoluteFile();
                    }
                }
                if(importedFile.isFile()){
                    try {
                        importAndConvertWordFile();
                    }catch (OutOfMemoryError outOfMemoryError){
                        outOfMemoryError.printStackTrace();

                    }
                    new ImportHTMLFile(importedFileHtml);
                    dispose();
                }else{
                    if(importedFile.isDirectory()){
                        JOptionPane.showMessageDialog(importTermWindow,"Wybrano folder, zamiast pliku.","Błąd odczytu pliku",JOptionPane.ERROR_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(importTermWindow,"Plik nie istnieje.","Błąd pliku",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    private void importAndConvertWordFile() {
        WordprocessingMLPackage wordMLPackage;
        try {
            wordMLPackage = Docx4J.load(importedFile);
            HTMLSettings htmlSettings = Docx4J.createHTMLSettings();

            htmlSettings.setImageDirPath(ConfigManager.getTempFolder() );
            //htmlSettings.setImageTargetUri(importedFile.getAbsolutePath().substring(importedFile.getAbsolutePath().lastIndexOf("/")+1)
                    //+ "_files");
            htmlSettings.setWmlPackage(wordMLPackage);
            OutputStream os;
            importedFileHtml = ConfigManager.getTempFolder() +importedFile.getName() + ".html";
            os = new FileOutputStream(ConfigManager.getTempFolder() +importedFile.getName() + ".html");
            //Docx4J.toHTML(htmlSettings, os, Docx4J.FLAG_EXPORT_PREFER_XSL);
            Docx4J.toHTML(htmlSettings, os, Docx4J.FLAG_EXPORT_PREFER_NONXSL);

            Docx4jProperties.setProperty("docx4j.Convert.Out.HTML.OutputMethodXML", true);

        } catch (Docx4JException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void enableDragAndDrop(){
            dropTarget  = new DropTarget(textField, new DropTargetListener() {
            @Override
            public void dragEnter(DropTargetDragEvent dtde) {

            }

            @Override
            public void dragOver(DropTargetDragEvent dtde) {

            }

            @Override
            public void dropActionChanged(DropTargetDragEvent dtde) {

            }

            @Override
            public void dragExit(DropTargetEvent dte) {

            }

            @Override
            public void drop(DropTargetDropEvent dtde) {
                try
                {

                    // Accept the drop first, important!
                    dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);

                    // Get the files that are dropped as java.util.List
                    java.util.List list=(java.util.List) dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);

                    // Now get the first file from the list,
                    File file=(File)list.get(0);
                    if(getFileExtension(file).equals("docx")||getFileExtension(file).equals("doc")){
                        textField.setText(file.getAbsolutePath());
                    }


                }catch(Exception ex){}
            }
        });
    }
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
}
