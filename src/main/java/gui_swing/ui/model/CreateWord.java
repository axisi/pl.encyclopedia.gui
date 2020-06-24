package gui_swing.ui.model;


import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreateWord {
    private JTable jTable;
    private ApiConnector apiConnector;
    public CreateWord(JTable jTable) {
        this.jTable = jTable;
        apiConnector= new ApiConnector();
        
        createEmptyFile();
    }

    private void createEmptyFile() {
        XWPFDocument document= new XWPFDocument();
        try {

            String path = ConfigManager.getTempFolder();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmmss");
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
                    tableRowTwo.getCell(0).setText(termHistory.getContent());
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
    }

}
