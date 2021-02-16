package gui_swing.ui.model;


//import com.google.common.io.Resources;
import gui_swing.ui.model.pojo.UserRole;

import javax.swing.*;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigManager {

    private  static String jwtToken;
    private  static String loggedUser;
    private static String apiURI;
    private static String tagsFolder;
    private static String tempFolder;
    private static String thunderbirdFolder;
    private static String scriptsFolder;
    private static URL url = ClassLoader.getSystemResource("config/inputs.properties");
    private static UserRole loggedUserRole;

    public ConfigManager() {
        setApiURI();
        setTranslations();
    }

    private static void setTranslations() {
        UIManager.put("FileChooser.newFolderToolTipText","Nowy folder");
        UIManager.put("FileChooser.homeFolderToolTipText","Folder startowy");
        UIManager.put("FileChooser.filesOfTypeLabelText","Typ plików:");
        UIManager.put("FileChooser.lookInLabelText","Szukaj w:");

    }

    public static String getApiURI() {
        return apiURI;
    }

    public static void setCompareURI(String oldPath, String newPath){
       /*
        URL url = ClassLoader.getSystemResource("config/inputs.properties");

        if (url != null) {
           *//* try{
               // BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(Resources.getResource("config/inputs.properties").toURI()));


                resources.setProperty("oldPath", oldPath);
                resources.setProperty("newPath", newPath);

                //FileWriter writer = new FileWriter(new File(url.getPath()));
                //resources.store(bufferedWriter,"RAZDWA");
                //writer.close();
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }*//*
        }*/
      /*  final Properties resources = new Properties();
        if (url != null){
            try {


                resources.setProperty("oldPath", oldPath);
                resources.setProperty("newPath", newPath);
                FileWriter writer = new FileWriter(new File(url.toURI().getPath()));
                resources.store(writer, "");
                writer.close();
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }

        }*/

    }

    public static void setApiURI() {
        final Properties resources = new Properties();
       /* ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try {
            resources.load(classLoader.getResourceAsStream( "/src/main/resources/config/inputs.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        if (url != null) {
            try {
                resources.load(url.openStream());
                System.out.println("Properties File Loaded");
                System.out.println(resources.getProperty("apiUri"));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        /*try {
            FileInputStream in = new FileInputStream(
                    "src/main/resources/config/inputs.properties");
            try {
                resources.load(in);
                System.out.println("Properties File Loaded");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        ConfigManager.setApiURI (resources.getProperty("apiUri"));
        ConfigManager.setTagsFolder( resources.getProperty("tagsFolder"));
        ConfigManager.setTempFolder( resources.getProperty("tempFolder"));
        ConfigManager.setThunderbirdFolder( resources.getProperty("thunderbirdFolder"));
        ConfigManager.setScriptsFolder( resources.getProperty("scriptsFolder"));
        //System.out.println(resources.getProperty("tagsFolder"));
    }

    public static String getThunderbirdFolder() {
        return thunderbirdFolder;
    }

    public static void setThunderbirdFolder(String thunderbirdFolder) {
        ConfigManager.thunderbirdFolder = thunderbirdFolder;
    }

    public static String getJwtToken() {
        return jwtToken;
    }

    public static void setJwtToken(String jwtToken) {
        ConfigManager.jwtToken = jwtToken;
    }

    public static UserRole getLoggedUserRole() {
        return loggedUserRole;
    }

    public static void setLoggedUserRole(UserRole loggedUserRole) {
        ConfigManager.loggedUserRole = loggedUserRole;
    }

    public static String getLoggedUser() {
        return loggedUser;
    }

    public static void setApiURI(String apiURI) {
        ConfigManager.apiURI = apiURI;
    }

    public static String getTagsFolder() {
        return tagsFolder;
    }

    public static String getTempFolder() {
        return tempFolder;
    }

    public static void setTempFolder(String tempFolder) {
        ConfigManager.tempFolder = tempFolder;
    }

    public static void setTagsFolder(String tagsFolder) {
        ConfigManager.tagsFolder = tagsFolder;
    }

    public static String getScriptsFolder() {
        return scriptsFolder;
    }

    public static void setScriptsFolder(String scriptsFolder) {
        ConfigManager.scriptsFolder = scriptsFolder;
    }

    public static void setLoggedUser(String loggedUser) {
        ConfigManager.loggedUser = loggedUser;
    }
/*public  String getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(String loggedUser) {
        ConfigManager.loggedUser = loggedUser;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }*/


}
