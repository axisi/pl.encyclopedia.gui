package gui_swing.ui.model.Components;

import gui_swing.ui.model.ApiConnector;
import gui_swing.ui.model.Term;
import gui_swing.ui.model.TermHistory;
import gui_swing.ui.model.tableModels.TermVersionTableModel;

import javax.swing.*;
import java.awt.*;

public class TermVersionPanel extends JFrame {

   private JPanel mainPanel;
   private JScrollPane scrollPane;
   private JTable jTable;
   private Long termId;
   ApiConnector apiConnector;

   public TermVersionPanel(Long termId){
       super();
       this.termId = termId;
       apiConnector = new ApiConnector();

       mainPanel = new JPanel();
       jTable = new JTable(new TermVersionTableModel());
       scrollPane = new JScrollPane(jTable);
       getContentPane().add(mainPanel);
       mainPanel.add(scrollPane);
       Term term = apiConnector.getTerm(termId.intValue());
       setTitle("Wersje hasła: "+term.getTitle());
       TermVersionTableModel termVersionTableModel= (TermVersionTableModel) jTable.getModel();
      for (TermHistory t: term.getTermHistories()
           ) {
         termVersionTableModel.addRow(new Object[]{t.getVersion(),t.getCreationDate(),apiConnector.getCreatedByOfTermHistory(t.getId())});
      }
       jTable.getTableHeader().setReorderingAllowed(false);
       this.setMinimumSize(new Dimension(600,550));
       Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
       this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
       this.setVisible(true);



   }

}
