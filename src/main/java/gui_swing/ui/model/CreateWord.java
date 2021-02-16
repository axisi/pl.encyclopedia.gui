package gui_swing.ui.model;


import gui_swing.ui.model.Components.WordGeneratePanel;
import gui_swing.ui.model.pojo.Author;
import gui_swing.ui.model.pojo.Term;
import gui_swing.ui.model.pojo.TermHistory;
import gui_swing.ui.model.pojo.TermHistoryComment;
import net.atlanticbb.tantlinger.shef.HTMLEditorPane;
import org.apache.poi.xwpf.usermodel.*;
import org.docx4j.XmlUtils;
import org.docx4j.convert.in.xhtml.XHTMLImporterImpl;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.CommentsPart;
import org.docx4j.wml.Comments;
import org.graalvm.compiler.word.Word;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class CreateWord {
    private JTable jTable;
    private ApiConnector apiConnector;
    private StringBuilder xhtml;
    private String fontColor;
    private String backGroundColor;
    private String path;
    private String fileName;
    private Boolean isSelected;
    private WordGeneratePanel parentPanel;
    private Boolean printIt;
    private String fullPath;
    private Boolean attachComments = true;


    public CreateWord(JTable jTable, Color foreground, Color background, String path, String fileName , WordGeneratePanel wordGeneratePanel,Boolean isSelected) {
        //String hex = String.format("#%02x%02x%02x", r, g, b);
        printIt = false;
        this.jTable = jTable;
        staticConstructorPart(foreground, background, path, fileName);
        parentPanel =wordGeneratePanel;
        this.isSelected = isSelected;
        //createEmptyFile();
        createEmptyFile1();
    }

    private void staticConstructorPart(Color foreground, Color background, String path, String fileName) {
        apiConnector= new ApiConnector();
        fontColor = String.format("#%02x%02x%02x", foreground.getRed(), foreground.getGreen(), foreground.getBlue());
        backGroundColor = String.format("#%02x%02x%02x", background.getRed(), background.getGreen(), background.getBlue());
        this.path = path;
        this.fileName = fileName;
    }

    public CreateWord(){

    }

    public CreateWord(Integer termId, Long versionNumber, Color black, Color white, String tempFolder, String date,Boolean isSelected , Boolean printIt) {
        staticConstructorPart(black,white,tempFolder,date);
        this.isSelected= isSelected;
        this.printIt = printIt;
        createEmptyFile2(termId,versionNumber);
    }

    public CreateWord (Term term , Color black , Color white , String tempFolder , String date){
        staticConstructorPart(black,white,tempFolder,date+"new");
        isSelected = false;
        printIt = false;
        createEmptyFile2(term);

    }

    private void createEmptyFile2(Term term) {
        xhtml = new StringBuilder();
        addHeader();
        addBody2(term);
        addFooter();
        convertAndGenerateFile();
    }

    private void addBody2(Term term) {
        fillTermEntity(term.getId(),-1l,term.getTitle(),term);
    }



    private void createEmptyFile2(Integer termId, Long versionNumber) {
        xhtml = new StringBuilder();
        addHeader();
        addBody2(termId,versionNumber);
        addFooter();
        convertAndGenerateFile();
    }

    private void addBody2(Integer termId, Long versionNumber) {
        Term term = apiConnector.getTerm(termId);
        String termTitle = term.getTitle();
        fillTermEntity(termId.longValue(),versionNumber.longValue(),termTitle);

    }


    private void createEmptyFile1() {
        xhtml = new StringBuilder();
        addHeader();
        addBody();
        addFooter();
        convertAndGenerateFile();




    }



    private void convertAndGenerateFile()  {
        try {
            //xhtml = xhtml.replaceAll("(\\r|\\n)", "");
            //xhtml = xhtml.replaceAll("     ", " ");
           // xhtml = xhtml.trim().replaceAll(" +"," ");


        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();

            /*CommentsPart commentsPart = new CommentsPart();
            wordMLPackage.addTargetPart(commentsPart);
            Comments comments = new Comments();
            commentsPart.setJaxbElement(comments);
            Comments.Comment comment = new Comments.Comment();
            comment.setInitials("aaa");
            comment.setAuthor("Grześ");
            comments.getComment().add(comment);*/




            XHTMLImporterImpl XHTMLImporter = new XHTMLImporterImpl(wordMLPackage);

        try{

            wordMLPackage.getMainDocumentPart().getContent().addAll(
                    XHTMLImporter.convert(String.valueOf(xhtml), null) );
        }catch (Exception e1){
            e1.printStackTrace();
            System.out.println("AA");
        }

        xhtml.setLength(0);
        fullPath = path +File.separator+ fileName+".docx";
        File file = new File(fullPath);


            if(attachComments){
                if(jTable!=null)
                    generateComments();
            }

            wordMLPackage.save(new FileOutputStream(file));
            wordMLPackage.reset();

            if(attachComments){
                if(jTable!=null)
                    insertCommentsIntoWord();
            }


            if(isSelected){
                Desktop desktop = Desktop.getDesktop();

                if(printIt) {
                    desktop.print(file);
                }else{
                    desktop.open(file);

                }
            }
            if(!printIt&&parentPanel!=null){
                parentPanel.dispose();
            }
        } catch (Docx4JException  e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            byte[] bytes = e.getMessage().getBytes(StandardCharsets.UTF_8);
            String s1 = null;
            s1 = new String(bytes, Charset.forName("UTF-8"));


            JOptionPane.showMessageDialog(parentPanel, s1 );
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void insertCommentsIntoWord() {
        File file = new File(ConfigManager.getScriptsFolder()+"file.txt");
        try {
            FileWriter myWriter = new FileWriter(file);
            myWriter.write(fullPath);
            myWriter.close();
        }catch (Exception e1){

        }
        file = new File(ConfigManager.getScriptsFolder()+"script2.docm");
        Desktop desktop = Desktop.getDesktop();
        if(file.exists()) {
            try {
                desktop.open(file);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        boolean isFileLocked = true;
        File thisFile = new File(fullPath);
        while (isFileLocked) {
            try {
                org.apache.commons.io.FileUtils.touch(thisFile);
                isFileLocked = false;
            } catch (IOException e) {
                isFileLocked = true;
            }
        }

    }

    private void generateComments() throws IOException {
        FileWriter fileJSON = new FileWriter(ConfigManager.getScriptsFolder()+"comments.json");
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        for (int i = 0; i < jTable.getRowCount(); i++) {
            if ((Boolean) jTable.getValueAt(i, 0)) {
                JSONObject term = new JSONObject();
               JSONArray array1 = new JSONArray();
                ArrayList<TermHistoryComment> termHistoryComments = apiConnector.getAllCommentsOfTerm((Long) jTable.getValueAt(i,1));
                for (TermHistoryComment t:termHistoryComments
                     ) {
                    JSONObject comment = new JSONObject();
                    comment.put("Lp",t.getLp());
                    comment.put("Deadline",t.getDeadlineDate());
                    comment.put("ModifiedBy",apiConnector.getLoginOfCommentModifier(t.getId()));
                    comment.put("Content",t.getContent());
                    array1.put(comment);
                }
                term.put("Id",jTable.getValueAt(i, 1).toString());
                term.put("comments",array1);
                array.put(term);
            }

        }
        json.put("data",array);
        try{
            fileJSON.write(json.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileJSON.flush();
            fileJSON.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addFooter() {
        xhtml.append("</html>");

    }

    private void addBody() {
        for (int i = 0; i < jTable.getRowCount(); i++) {
            if((Boolean) jTable.getValueAt(i,0)) {
                Long termId = Long.valueOf(jTable.getValueAt(i, 1).toString());
                Long version = Long.valueOf(jTable.getValueAt(i, 4).toString());
                String termTitle = jTable.getValueAt(i, 2).toString();
                fillTermEntity(termId, version, termTitle);
            }

        }
    }

    private void fillTermEntity(Long termId, Long version, String termTitle,Term ... term) {
        TermHistory termHistory;
        String authorString;
        if(version!=-1l){
             termHistory = apiConnector.getTermHistoryToTermWidthVersion(termId.intValue(), version.intValue());
             authorString = "";

            for (Author a: apiConnector.getAuthorsOfTerm(termId)
            ) {
                authorString+= a.getName() +" "+ a.getSurname();
                Integer verses =  apiConnector.getVerses((long) a.getId(),termId);
                if (verses >-1 ){
                    authorString+= " - "+verses;
                }
                authorString+="; ";
            }
        }else {
            termHistory = term[0].getTermHistories().get(0);
            authorString = "";

        }
        HTMLEditorPane htmlEditorPane = new HTMLEditorPane();
        htmlEditorPane.setText(termHistory.getContent());
        String content = htmlEditorPane.getText() ;
        content = content.replaceAll("(\n)","");
        content= content.trim().replaceAll(" +", " ");
        content= content.trim().replaceAll("<br>", "<br></br>");
        xhtml.append("<table style=\"width:100%;border: 1px solid black;border-collapse: collapse;page-break-inside:auto;\">");
        xhtml.append("<tr>");
        xhtml.append("<td style=\"border: 1px solid black;color:"+fontColor+";background-color:"+backGroundColor+";\">Id:</td>");
        xhtml.append("<td style=\"border: 1px solid black;color:"+fontColor+";background-color:"+backGroundColor+";\">"+termId.toString()+"</td>");
        System.out.println(termId.toString());
        xhtml.append("<td style=\"border: 1px solid black;color:"+fontColor+";background-color:"+backGroundColor+";\">Wersja:</td>");
        xhtml.append("<td style=\"border: 1px solid black;color:"+fontColor+";background-color:"+backGroundColor+";\">"+version.toString()+"</td>");
        xhtml.append("<td style=\"border: 1px solid black;color:"+fontColor+";background-color:"+backGroundColor+";\">Tytuł:</td>");
        xhtml.append("<td style=\"border: 1px solid black;color:"+fontColor+";background-color:"+backGroundColor+";\">"+termTitle+"</td>");
        xhtml.append("</tr>");
        xhtml.append("<tr>");
        xhtml.append("<td style=\"border: 1px solid black;color:"+fontColor+";background-color:"+backGroundColor+";\">Autorzy:</td>");
        xhtml.append("<td colspan=\"5\" style=\"border: 1px solid black;color:"+fontColor+";background-color:"+backGroundColor+";\">"+authorString +"</td>");
        xhtml.append("</tr>");
        xhtml.append("<tr>");
        xhtml.append("<td colspan=\"6\" style=\"border: 1px solid black;\">"+content+"</td>");
        xhtml.append("</tr>");
        xhtml.append("</table>");
        xhtml.append("<br></br>");
    }

    private void addHeader() {
        xhtml.append("<html>");
        /*xhtml.append("<style type=\"text/css\">\n" +
                "table { page-break-inside:auto }" +
                "table {border-style: solid;}" +
                "</style>";*/
    }

    public String getFullPath() {
        return fullPath;
    }

    /*private void createEmptyFile() {
        XWPFDocument document= new XWPFDocument();
        try {

            String path = ConfigManager.getTempFolder();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
            LocalDateTime now = LocalDateTime.now();
            String date =(dtf.format(now));
            FileOutputStream out = new FileOutputStream(new File(path + date+".docx"));

            for (int i = 0; i < jTable.getRowCount(); i++) {

                if((jTable.getValueAt(i, 0).toString())== "true") {


                    //create table
                    XWPFTable table = document.createTable();

                    CTTblWidth width = table.getCTTbl().addNewTblPr().addNewTblW();
                    width.setType(STTblWidth.DXA);
                    width.setW(BigInteger.valueOf(9072));
                    Long termId = Long.valueOf(jTable.getValueAt(i, 1).toString());
                    Long version = Long.valueOf(jTable.getValueAt(i, 4).toString());
                    //create first row
                    XWPFTableRow tableRowOne = table.getRow(0);
                    tableRowOne.getCell(0).setText("Id:");
                    tableRowOne.addNewTableCell().setText(termId.toString());
                    tableRowOne.addNewTableCell().setText("Wersja:");
                    tableRowOne.addNewTableCell().setText(version.toString());
                    tableRowOne.addNewTableCell().setText("Tytuł:");
                    tableRowOne.addNewTableCell().setText(jTable.getValueAt(i, 2).toString());
                    //create second row
                    XWPFTableRow tableRowTwo = table.createRow();
                    mergeCellHorizontally(table, 1, 0, 5);
                    TermHistory termHistory = apiConnector.getTermHistoryToTermWidthVersion(termId.intValue(), version.intValue());


                    String xhtml = termHistory.getContent();
                    WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
                    XHTMLImporterImpl XHTMLImporter = new XHTMLImporterImpl(wordMLPackage);
                    wordMLPackage.getMainDocumentPart().getContent().addAll(
                            XHTMLImporter.convert( xhtml, null) );
                    ;

                    tableRowTwo.getCell(0).setText((XmlUtils.marshaltoString(wordMLPackage
                            .getMainDocumentPart().getJaxbElement(), false, false)));
                    //table.setWidthType();
                    XWPFParagraph paragraph = document.createParagraph();
                    paragraph.setPageBreak(true);
                }
            }

            document.write(out);
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (Docx4JException e) {
            e.printStackTrace();
        }

    }
    static void mergeCellHorizontally(XWPFTable table, int row, int fromCol, int toCol) {
        for(int colIndex = fromCol; colIndex <= toCol; colIndex++){
            XWPFTableCell cell = table.getRow(row).getCell(colIndex);
            CTHMerge hmerge = CTHMerge.Factory.newInstance();
            if(colIndex == fromCol){
                // The first merged cell is set with RESTART merge value
                hmerge.setVal(STMerge.RESTART);
            } else {
                // Cells which join (merge) the first one, are set with CONTINUE
                hmerge.setVal(STMerge.CONTINUE);
                // and the content should be removed
                for (int i = cell.getParagraphs().size(); i > 0; i--) {
                    cell.removeParagraph(0);
                }
                cell.addParagraph();
            }
            // Try getting the TcPr. Not simply setting an new one every time.
            CTTcPr tcPr = cell.getCTTc().getTcPr();
            if (tcPr != null) {
                tcPr.setHMerge(hmerge);
            } else {
                // only set an new TcPr if there is not one already
                tcPr = CTTcPr.Factory.newInstance();
                tcPr.setHMerge(hmerge);
                cell.getCTTc().setTcPr(tcPr);
            }
        }
    }*/

}
