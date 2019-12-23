package gui_swing.ui.controller;

import gui_swing.ui.model.ConfigManager;

public class MainController {
    private LoginFrameController loginFrameController = new LoginFrameController(this);
    private ConfigManager jwtToken = new ConfigManager();
    private ApplicationFrameController applicationFrameController = new ApplicationFrameController(this );

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
        initComponents(jwtToken);
    }

    private void initComponents(ConfigManager jwtToken) {
        this.loginFrameController.showMainFrameWindow();
    }

}

