package gui_swing.ui.model.Components;

import gui_swing.ui.controller.ApplicationFrameController;
import gui_swing.ui.model.ApiConnector;

import gui_swing.ui.model.pojo.Term;
import gui_swing.ui.model.pojo.TermHistory;
import gui_swing.ui.model.tableModels.GradientButton;
import gui_swing.ui.model.tableModels.TermVersionTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;

public class TermVersionPanel extends JFrame {

   private JPanel mainPanel;
   private JScrollPane scrollPane;
   private JTable jTable;
   private Integer termId;
   ApiConnector apiConnector;

   public TermVersionPanel(Integer termId){
       super();
       this.termId = termId;
       apiConnector = new ApiConnector();

       mainPanel = new JPanel(new BorderLayout());
       jTable = new JTable(new TermVersionTableModel());
       scrollPane = new JScrollPane(jTable);
       getContentPane().add(mainPanel);
       mainPanel.add(scrollPane,BorderLayout.CENTER);
       JButton compareVersionsJButton = new GradientButton("Porównaj wersje", Color.GRAY);
       mainPanel.add(compareVersionsJButton,BorderLayout.NORTH);

       Term term = apiConnector.getTerm(termId.intValue());
       setTitle("Wersje hasła: "+term.getTitle());
       TermVersionTableModel termVersionTableModel= (TermVersionTableModel) jTable.getModel();
       DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
       //jTable.setPreferredSize(new Dimension(600,550));
      for (TermHistory t: term.getTermHistories()
           ) {

         termVersionTableModel.addRow(new Object[]{
                 t.getVersion()
                 ,t.getCreationDate().toLocalDateTime().format(myFormatObj)
                 , term.getActualVersion() == t.getVersion()
                 ,apiConnector.getStatusOfTermHistory(t.getId())
                 ,apiConnector.getCreatedByOfTermHistory(t.getId())});
      }
       jTable.getTableHeader().setReorderingAllowed(false);
      jTable.getColumnModel().getColumn(0).setPreferredWidth(30);
      jTable.getColumnModel().getColumn(1).setPreferredWidth(150);
      //jTable.getTableHeader().setBackground(Color.BLUE);
       scrollPane.setPreferredSize(new Dimension(700,650));
       this.setMinimumSize(new Dimension(800,750));
       Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
       this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
       this.pack();
       this.setVisible(true);
       jTable.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e) {
               if(e.getClickCount()==2&&jTable.getSelectedRow()!=-1){
                   ApplicationFrameController.termWindows.add( new TermWindow(termId,
                           apiConnector.getTermHistoryToTermWidthVersion(termId
                                   ,Integer.valueOf(jTable.getValueAt(jTable.getSelectedRow(),0).toString())).getId().intValue()));
                   TermWindow termFrame =ApplicationFrameController.termWindows.get(ApplicationFrameController.termWindows.size()-1);
                   termFrame.getFrame().setTitle(apiConnector.getTerm(termId).getTitle());
               }
           }
       });

       compareVersionsJButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               new CompareTwoVersionsPanel(termId.longValue());
           }
       });


   }

}
