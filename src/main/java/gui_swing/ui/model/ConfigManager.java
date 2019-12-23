package gui_swing.ui.model;


public class ConfigManager {

    private  static String jwtToken;
    private  static String loggedUser;

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
