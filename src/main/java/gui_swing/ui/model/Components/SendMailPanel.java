package gui_swing.ui.model.Components;

import gui_swing.ui.model.ApiConnector;
import gui_swing.ui.model.ConfigManager;
import gui_swing.ui.model.tableModels.GradientButton;
import net.atlanticbb.tantlinger.shef.HTMLEditorPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;

public class SendMailPanel extends JFrame {
    private ApiConnector apiConnector = new ApiConnector();
    private JPanel mainJPanel;
    private JPanel topPanel;
    private JPanel topLeftPanel;
    private JPanel topCenterPanel;
    private JPanel topRightPanel;
    private JPanel bottomPanel;
    private String filePath;
    private ArrayList <Long> sendTermsIdList;
    private ArrayList <Long> sendTermsVersionList;
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    private JButton sendButton;
    private JLabel toLabel;
    private JLabel ccLabel;
    private JLabel subjectLabel;
    private JLabel statusChangeCBLabel;
    private JLabel attachmentLabel;
    private JComboBox statusChangeCB;
    private JTextField toText;
    private JTextField attachmentText;
    private JTextField ccText;
    private JTextField subjectText;
    private JTextArea bodyText;
    private JScrollPane scrollPane;
    private HTMLEditorPane htmlEditorPane;

    private JButton toButton;
    private JButton ccButton;

    private SendMailPanel sendMailPanel;
    private AddressBookPanel addressBookPanel;


    public SendMailPanel(String filePath, ArrayList<Long> sendTermsIdList,ArrayList<Long> sendTermsVersionList) {
        this.filePath = filePath;
        this.sendTermsIdList = sendTermsIdList;
        this.sendTermsVersionList = sendTermsVersionList;
        this.sendMailPanel = this;
        prepareForm();
        fillData();
        showForm();
    }

    private void fillData() {
        ArrayList<String> strings = apiConnector.getAllStatusesString();
        statusChangeCB.addItem("Nie zmieniaj statusów");
        for (String s :strings
                ) {
            statusChangeCB.addItem(s);
        }
        statusChangeCB.setSelectedIndex(0);
        attachmentText.setText(filePath);

    }

    private void showForm() {
        this.setMinimumSize(new Dimension((int) (dim.width*0.7), (int) (dim.height * 0.6)));
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setVisible(true);
        this.setTitle("Generowanie wiadomości mailowej");
    }

    private void prepareForm() {
        mainJPanel = new JPanel(new BorderLayout());
        getContentPane().add(mainJPanel);
        topPanel= new JPanel(new FlowLayout());
        mainJPanel.add(topPanel,BorderLayout.NORTH);
        topLeftPanel = new JPanel(new GridLayout(0,1));
        topCenterPanel = new JPanel(new GridLayout(0,1));
        JPanel topRightLeftPanel = new JPanel(new GridLayout(0,1));

        JPanel topRightRightPanel = new JPanel(new GridLayout(0,1));
        topRightPanel = new JPanel(new BorderLayout());
        topRightPanel.add(topRightLeftPanel,BorderLayout.WEST);
        topRightPanel.add(topRightRightPanel,BorderLayout.EAST);
        topPanel.add(topLeftPanel);
        topPanel.add(topCenterPanel);
        topPanel.add(topRightPanel);
        sendButton = new GradientButton("Wyślij",Color.GREEN.darker());
        sendButton.setPreferredSize(new Dimension(100,120));
        topLeftPanel.add(sendButton);
        toLabel = new JLabel("Do: ",SwingConstants.RIGHT);
        toLabel.setPreferredSize(new Dimension(150,30));
        ccLabel = new JLabel("DW: ",SwingConstants.RIGHT);
        ccLabel.setPreferredSize(new Dimension(150,30));
        subjectLabel = new JLabel("Temat: " ,SwingConstants.RIGHT);
        subjectLabel.setPreferredSize(new Dimension(150,30));
        statusChangeCBLabel= new JLabel("Zmiana statusu haseł: ", SwingConstants.RIGHT);
        statusChangeCBLabel.setPreferredSize(new Dimension(150,30));
        attachmentLabel = new JLabel("Załączniki: ",SwingConstants.RIGHT);
        attachmentLabel.setPreferredSize(new Dimension(150,30));
        toButton = new GradientButton("Do",Color.blue.brighter());
        ccButton = new GradientButton("DW",Color.CYAN.brighter());
        toButton.setPreferredSize(new Dimension(60,30));
        ccButton.setPreferredSize(new Dimension(60,30));

        topCenterPanel.add(toLabel);
        topCenterPanel.add(ccLabel);
        topCenterPanel.add(subjectLabel);
        topCenterPanel.add(statusChangeCBLabel);
        topCenterPanel.add(attachmentLabel);
        toText = new JTextField();
        toText.setPreferredSize(new Dimension((int) (dim.width*0.6),30));
        ccText = new JTextField();
        ccText.setPreferredSize(new Dimension((int) (dim.width*0.6),30));
        subjectText = new JTextField();
        subjectText.setPreferredSize(new Dimension((int) (dim.width*0.6),30));
        attachmentText = new JTextField();
        attachmentText.setEnabled(false);
        attachmentText.setPreferredSize(new Dimension((int) (dim.width*0.6),30));
        statusChangeCB = new JComboBox();

        topRightLeftPanel.add(toButton);
        topRightRightPanel.add(toText);
        topRightLeftPanel.add(ccButton);

        topRightRightPanel.add(ccText);
        topRightLeftPanel.add(new JLabel());
        topRightRightPanel.add(subjectText);
        topRightLeftPanel.add(new JLabel());
        topRightRightPanel.add(statusChangeCB);
        topRightLeftPanel.add(new JLabel());
        topRightRightPanel.add(attachmentText);

        bottomPanel= new JPanel();
        mainJPanel.add(bottomPanel,BorderLayout.CENTER);
        //bodyText = new JTextArea();
        //scrollPane = new JScrollPane(bodyText);
        htmlEditorPane = new HTMLEditorPane();
        htmlEditorPane.setPreferredSize(new Dimension((int) (dim.width*0.7),(int) (dim.height*0.4)));
        bottomPanel.add(htmlEditorPane);
        //scrollPane.setPreferredSize(new Dimension((int) (dim.width*0.7),(int) (dim.height*0.4)));
        //bottomPanel.add(scrollPane);

        this.pack();

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMail();
            }
        });

        toButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addressBookPanel != null) {
                    addressBookPanel.dispose();
                }
                addressBookPanel = new AddressBookPanel(sendMailPanel,true);
            }
        });
        ccButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addressBookPanel != null) {
                    addressBookPanel.dispose();
                }
                addressBookPanel = new AddressBookPanel(sendMailPanel,false);
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (addressBookPanel != null) {
                    addressBookPanel.dispose();
                }
            }
        });
    }

    private void sendMail() {
        char quotes ='"';
        String strCommand;
        strCommand = " -compose "+quotes+"to='"+toText.getText()+"',";
        strCommand += "cc='"+ccText.getText()+"',";
        strCommand += "body='<html>"+htmlEditorPane.getText().replaceAll("\n", "")+"</html>',";
        strCommand += "subject='"+subjectText.getText()+"',";
        strCommand += "format='1',";
        strCommand += "attachment='"+attachmentText.getText()+"'\"";



        /*String command = "C:/Program Files (x86)/Google/Chrome/Application&chrome.exe";
        ProcessBuilder pb = new ProcessBuilder();
        pb.command("cmd.exe", "/c", "dir");
        pb.directory(new File(System.getProperty("user.home")));

        try {
            Process p = pb.start();


        } catch (IOException e) {
            e.printStackTrace();
        }*/
        String str2 = " -compose \"subject='test',to='me'\"";
        String command = "\""+ConfigManager.getThunderbirdFolder()+"\"thunderbird";
        command += strCommand;
        /*try {
            Runtime.getRuntime().exec("cmd.exe "+command);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        String filePath = ConfigManager.getScriptsFolder()+File.separator+"sendMail.bat";
        try {
            PrintWriter writer = new PrintWriter(filePath, "UTF-8");
            writer.println(command);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            Runtime.
                    getRuntime().
                    exec("\""+filePath+"\"" );
        } catch (IOException e) {
            e.printStackTrace();
        }

        dispose();
        /*ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/C","/U","\"C:/Program Files (x86)/Mozilla Thunderbird/\"thunderbird" , command  );
        try {
            Process p = pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //C:\Program Files (x86)\Mozilla Thunderbird>thunderbird -compose subject='test'
        //ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/C", "start", "\"C:/Program Files (x86)/Mozilla Thunderbird/thunderbird\"",);

       /* StringBuilder stringBuilder= new StringBuilder();
        stringBuilder.append("cmd.exe start \"C:/Program Files (x86)/Mozilla Thunderbird\"/thunderbird");
        stringBuilder.append(strCommand);
        try {
            Runtime.getRuntime().exec(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //ProcessBuilder builder = new ProcessBuilder("cmd.exe /c \"C:/Program Files (x86)/Mozilla Thunderbird\"/thunderbird", strCommand);
        /*try {
            Process p  = pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }
    public void addAddressesTo (String addresses){
        toText.setText(toText.getText()+addresses);
    }
    public void addAddressesCC (String addresses){
        ccText.setText(ccText.getText()+addresses);
    }
}
