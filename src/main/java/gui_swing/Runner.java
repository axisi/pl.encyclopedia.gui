package gui_swing;
import gui_swing.ui.controller.MainController;
import org.apache.log4j.BasicConfigurator;
import org.docx4j.org.xhtmlrenderer.util.XRLog;
import org.w3c.dom.ls.LSOutput;

public class Runner {
    public static void main(String[] args) {

        BasicConfigurator.configure();

        MainController mainController = new MainController();




    }
}
