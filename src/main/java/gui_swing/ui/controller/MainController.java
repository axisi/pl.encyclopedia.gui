package gui_swing.ui.controller;

import gui_swing.ui.model.ConfigManager;

public class MainController {
    private LoginFrameController loginFrameController ;
    private ConfigManager jwtToken ;
    private ApplicationFrameController applicationFrameController ;

    public ConfigManager getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(ConfigManager jwtToken) {
        this.jwtToken = jwtToken;
    }


    public LoginFrameController getLoginFrameController() {
        return loginFrameController;
    }

    public void setLoginFrameController(LoginFrameController loginFrameController) {
        this.loginFrameController = loginFrameController;
    }


    public ApplicationFrameController getApplicationFrameController() {
        return applicationFrameController;
    }

    public void setApplicationFrameController(ApplicationFrameController applicationFrameController) {
        this.applicationFrameController = applicationFrameController;
    }


    public MainController(){
        loginFrameController = new LoginFrameController(this);
        jwtToken = new ConfigManager();
        applicationFrameController = new ApplicationFrameController(this);
        initComponents(jwtToken);
    }

    private void initComponents(ConfigManager jwtToken) {
        this.loginFrameController.showMainFrameWindow();
    }

}

