package lt.codeacademy.menu;

import lt.codeacademy.dbmanager.DbManager;
import lt.codeacademy.inputs.Inputs;
import lt.codeacademy.password.PasswordHash;
import lt.codeacademy.questionnaireservice.QuestionnaireService;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Menu {

    private DbManager dbManager = new DbManager();
    private Inputs inputs = new Inputs();
    private PasswordHash passwordHash = new PasswordHash();
    private QuestionnaireService qS;

    public Menu() {
        qS = new QuestionnaireService(this);
    }

    public void userInit() throws NoSuchAlgorithmException {
        System.out.println("Iveskite varda:");
        String name = inputs.stringInput();
        if (name.toLowerCase().equals(dbManager.getAdmin().get(0).getFirstName())) {
            System.out.println("Iveskite slaptazodi:");
            String password = inputs.stringInput();
            if (passwordHash.encription(password).equals(dbManager.getAdmin().get(0).getPassword())) {
                admin();
            } else {
                System.out.println("Slaptazodis neteisingas!");
                userInit();
            }
        } else {
            questionnaireStart();
        }
    }

    public void admin() {
        int choice;
        do {
            Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
            System.out.println("Pasirinkite veiksma:");
            adminMenuPrint();
            choice = inputs.intInput();
            adminInputChoice(choice);
        } while (choice != 6);
    }

    public void questionnaireStart() {
        int choice;
        do {
            Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
            System.out.println("Pasirinkite klausimyna:");
            dbManager.getQuestionnaires();
            System.out.println("4. Isjungti programa");
            choice = inputs.intInput();
            userQuestionnaireChoice(choice);
        } while (choice != 4);
    }

    public void userQuestionnaireChoice(int userChoice) {
        if (userChoice == 1) {
            qS.oneAtTheTimeQuestion(userChoice);
        } else if (userChoice == 2) {
            qS.oneAtTheTimeQuestion(userChoice);
        } else if (userChoice == 3) {
            qS.oneAtTheTimeQuestion(userChoice);
        }
    }

    public void adminInputChoice(int adminChoice) {
        if (adminChoice == 1) {
            questionnaireStart();
        } else if (adminChoice == 2) {
            qS.adminQuestionCreation();
        } else if (adminChoice == 3) {
            qS.questionnairePrinting();
        } else if (adminChoice == 4) {
            qS.questionDelete();
        } else if (adminChoice == 5) {
            qS.results();
        } else if (adminChoice == 6) {
            System.out.println("Viso gero!");
        }
    }

    public void adminMenuPrint() {
        System.out.println("1.Atlikti klausimyna");
        System.out.println("2.Sukurti klausima klausimynui");
        System.out.println("3.Redaguoti klausima");
        System.out.println("4.Istrinti klausima is klausimyno");
        System.out.println("5.Perziureti statistika");
        System.out.println("6.Isjungti programa.");
    }
}
