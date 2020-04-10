package lt.codeacademy;

import lt.codeacademy.dbmanager.DbManager;
import lt.codeacademy.menu.Menu;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppStart {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        DbManager dbManager = new DbManager();


        Menu menu = new Menu();
        menu.userInit();
    }
}
