package monografia;

import java.text.ParseException;
import javax.swing.UIManager;
import principal.form_principal;


public class Main {

    public static void main(String[] args) throws ParseException {
        try {
           //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
           UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	//System.getProperties().put("proxySet", "true");
        //System.getProperties().put("http.proxyHost", "192.168.100.1");
        //System.getProperties().put("http.proxyPort", "8080");
        //System.getProperties().put("http.proxyUser", "someUserName");
        //System.getProperties().put("http.proxyPassword", "somePassword");

        } catch (Exception e) {
            e.printStackTrace();
        }
        form_principal principal = new form_principal();
    }
}
