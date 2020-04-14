package gui_swing.ui.model;


import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class ConfigManager {

    private  static String jwtToken;
    private  static String loggedUser;
    private static String apiURI;

    public ConfigManager() {
        setApiURI();
    }

    public static String getApiURI() {
        return apiURI;
    }

    public static void setApiURI() {
        final Properties resources = new Properties();
       /* ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try {
            resources.load(classLoader.getResourceAsStream( "/src/main/resources/config/inputs.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        URL url = ClassLoader.getSystemResource("config/inputs.properties");
        if (url != null) {
            try {
                resources.load(url.openStream());
                System.out.println("Properties File Loaded");
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

        ConfigManager.apiURI = resources.getProperty("apiUri");
    }

    public static String getJwtToken() {
        return jwtToken;
    }

    public static void setJwtToken(String jwtToken) {
        ConfigManager.jwtToken = jwtToken;
    }

    public static String getLoggedUser() {
        return loggedUser;
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
