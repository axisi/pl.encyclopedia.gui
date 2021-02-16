package gui_swing.ui.model.Components;

import gui_swing.ui.model.ApiConnector;
import gui_swing.ui.model.pojo.Author;
import gui_swing.ui.model.tableModels.EditorsCategoriesTableModel;
import gui_swing.ui.model.tableModels.GradientButton;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CategoryEditorsPanel extends JFrame {
    private ApiConnector apiConnector = new ApiConnector();
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    private JPanel mainJPanel;
    private JPanel topJPanel;
    private JPanel bottomJPanel;


    private JPanel tRightJPanel;
    private String chosenCategory;

    private JScrollPane leftScrollPane;
    private JScrollPane rightScrollPane;

    private JTable leftJTable;
    private JTable rightJTable;
    private JButton saveJButton;



    public CategoryEditorsPanel()  {
        prepareForm();

        fillData();
        addListeners();
        showForm();
    }

    private void addListeners() {
        moveToSecondTable(leftJTable, rightJTable);
        moveToSecondTable(rightJTable, leftJTable);
        saveJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Integer> chosenAuthorsIdList = new ArrayList<>();
                for (int i = rightJTable.getRowCount()-1; i >-1 ; i--) {
                    chosenAuthorsIdList.add((Integer) rightJTable.getValueAt(i,0));
                }
                apiConnector.updateEditorsForCategory(chosenCategory,chosenAuthorsIdList);
            }
        });
    }

    private void moveToSecondTable(JTable mainTable, JTable secondaryTable) {
        mainTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2 && mainTable.getSelectedRow()>-1){
                    DefaultTableModel leftTableModel = (DefaultTableModel) mainTable.getModel();
                    DefaultTableModel rightTableModel = (DefaultTableModel) secondaryTable.getModel();
                    Integer i = mainTable.getSelectedRow();
                    rightTableModel.addRow(new Object[]{leftTableModel.getValueAt(i,0),
                            leftTableModel.getValueAt(i,1),
                            leftTableModel.getValueAt(i,2),
                            leftTableModel.getValueAt(i,3)
                    });
                    leftTableModel.removeRow(i);
                }
            }
        });
    }

    private void showForm() {
        this.setMinimumSize(new Dimension((int) (dim.width*0.8), (int) (dim.height * 0.95)));
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setVisible(true);

        this.setTitle("Redaktorzy działów, redaktor naukowy" );
    }

    private void fillData() {
        ArrayList<String> categoriesList = new ArrayList<>( Arrays.asList(
                "Życie polityczne",
                "Życie gospodarcze",
                "Mniejszości i stowarzyszenia",
                "Sztuka",
                "Literatura, teatr, muzyka",
                "Nauka i biografistyka",
                "Historia i kalendarium",
                "Cracovia sacra",
                "Geografia i środowisko przyrodnicze",
                "Architektura i urbanizacja",
                "Odsyłaczowe",
                "Redaktor naukowy"
        ));


        for (String s: categoriesList
        ) {
            JButton newButton = new JButton(s);
            newButton.setBackground(Color.gray);
            newButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    loadEditorsPanel(s);
                    for (Component button:tRightJPanel.getComponents()
                         ) {
                        button.setBackground(Color.gray);
                    }
                    newButton.setBackground(Color.green);
                }
            });
            tRightJPanel.add(newButton);
        }
    }

    private void prepareForm() {
        mainJPanel = new JPanel( new BorderLayout());
        getContentPane().add(mainJPanel);
        topJPanel = new JPanel(new BorderLayout());
        mainJPanel.add(topJPanel,BorderLayout.NORTH);

        tRightJPanel = new JPanel(new GridLayout(0,1));
        topJPanel.add(tRightJPanel,BorderLayout.CENTER);
        bottomJPanel = new JPanel(new BorderLayout());
        leftJTable = new JTable(new EditorsCategoriesTableModel());
        rightJTable = new JTable(new EditorsCategoriesTableModel());
        leftScrollPane = new JScrollPane(leftJTable);
        rightScrollPane = new JScrollPane(rightJTable);
        bottomJPanel.add(leftScrollPane,BorderLayout.WEST);
        bottomJPanel.add(rightScrollPane,BorderLayout.EAST);
        leftScrollPane.setPreferredSize(new Dimension((int) (dim.getWidth()*0.4), (int) (dim.height*0.4)));
        rightScrollPane.setPreferredSize(new Dimension((int) (dim.getWidth()*0.4), (int) (dim.height*0.4)));
        saveJButton = new GradientButton("Zapisz",Color.GREEN);
        bottomJPanel.add(saveJButton,BorderLayout.SOUTH);
        mainJPanel.add(new JLabel("Przesuń podwójnym kliknięciem autora na prawo, następnie wciśnij zapisz",JLabel.CENTER),BorderLayout.CENTER);
        mainJPanel.add(bottomJPanel,BorderLayout.SOUTH);
        leftJTable.getColumnModel().getColumn(0).setPreferredWidth(30);
        leftJTable.getColumnModel().getColumn(3).setPreferredWidth(300);
        rightJTable.getColumnModel().getColumn(0).setPreferredWidth(30);
        rightJTable.getColumnModel().getColumn(3).setPreferredWidth(300);
        //leftJTable.setAutoCreateRowSorter(true);
        //rightJTable.setAutoCreateRowSorter(true);
        leftJTable.getTableHeader().setReorderingAllowed(false);
        rightJTable.getTableHeader().setReorderingAllowed(false);
        leftScrollPane.setBorder(BorderFactory.createEmptyBorder(0,0,0,15));
    }

    private void loadEditorsPanel(String s) {
        chosenCategory = s;
        ArrayList<Author> authorList = apiConnector.getAllAuthorsObject();
        DefaultTableModel leftTableModel = (DefaultTableModel) leftJTable.getModel();
        leftTableModel.setNumRows(0);
        DefaultTableModel rightTableModel= (DefaultTableModel) rightJTable.getModel();
        rightTableModel.setNumRows(0);
        for (Author a : authorList
        ){
            Float f = a.getId();
            ((DefaultTableModel) leftJTable.getModel()).addRow(new Object[]{f.intValue(),a.getName(),a.getSurname(),a.getEmail()});
        }
        ArrayList<Long> chosenAuthorsIds = apiConnector.getEditorAuthorsId(chosenCategory);
        for (Long id: chosenAuthorsIds
             ) {
            for (int i = leftTableModel.getRowCount()-1; i >-1 ; i--) {
                if(leftTableModel.getValueAt(i,0).equals(id.intValue())){
                    rightTableModel.addRow(new Object[]{leftTableModel.getValueAt(i,0),
                            leftTableModel.getValueAt(i,1),
                            leftTableModel.getValueAt(i,2),
                            leftTableModel.getValueAt(i,3)
                    });
                    leftTableModel.removeRow(i);
                }
            }
        }


    }
}
