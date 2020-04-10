package lt.codeacademy.questionnaireservice;

import lt.codeacademy.dbmanager.DbManager;
import lt.codeacademy.inputs.Inputs;
import lt.codeacademy.menu.Menu;
import lt.codeacademy.questionnaire.Question;
import lt.codeacademy.questionnaire.Results;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuestionnaireService {
    private Menu menu;
    private DbManager dbManager = new DbManager();
    private Inputs inputs = new Inputs();

    public QuestionnaireService(Menu menu) {
        this.menu = menu;
    }

    public void oneAtTheTimeQuestion(int userChoice) {
        List<Question> questions = dbManager.getAllQueryQuestions(userChoice);
        String choice;
        System.out.println("Pasirinkite viena A , B arba C");
        for (Question question : questions) {
            System.out.println(question.getQuestionText());
            for (int i = 0; i < dbManager.getAnswers(question.getId()).size(); i++) {
                System.out.print(dbManager.getAnswers(question.getId()).get(i).getAnswerOption() + ".");
                System.out.print(dbManager.getAnswers(question.getId()).get(i).getAnswerText() + "\n");
            }
            do {
                choice = inputs.stringInput().toUpperCase();
                if (choice.equals("A")) {
                    if (dbManager.getAnswers(question.getId()).get(0).isTrueFalse()) {
                        dbManager.updateResults("total_correct_answers", userChoice);
                    }
                    dbManager.updateResults("total_a", userChoice);
                    break;
                } else if (choice.equals("B")) {
                    if (dbManager.getAnswers(question.getId()).get(1).isTrueFalse()) {
                        dbManager.updateResults("total_correct_answers", userChoice);
                    }
                    dbManager.updateResults("total_b", userChoice);
                    break;
                } else if (choice.equals("C")) {
                    if (dbManager.getAnswers(question.getId()).get(2).isTrueFalse()) {
                        dbManager.updateResults("total_correct_answers", userChoice);
                    }
                    dbManager.updateResults("total_c", userChoice);
                    break;
                } else {
                    System.out.println("Ivedete bloga raide bandykit dar!");
                }
            } while (true);
        }
        dbManager.updateResults("total_finished_exams_count", userChoice);
    }

    public void adminQuestionCreation() {
        System.out.println("Pasirinkite klausimyna:");
        dbManager.getQuestionnaires();
        int questionnaire = inputs.intInput();
        System.out.println("Iveskite klausima:");
        inputs.stringNextLine();
        String question = inputs.stringNextLine();
        dbManager.createQuestion(questionnaire, question);
        List<Question> questions = dbManager.getAllQueryQuestions(questionnaire);
        adminAnswersCreation(questions.get(questions.size() - 1).getId());
    }

    public void adminAnswersCreation(int questionId) {
        System.out.println("Iveskite atsakymo variantus:");
        System.out.print("A:");
        String answerA = inputs.stringNextLine();
        System.out.print("B:");
        String answerB = inputs.stringNextLine();
        System.out.print("C:");
        String answerC = inputs.stringNextLine();
        System.out.println("Iveskite raide, kuri is triju varinatu yra teisinga");
        do {
            String trueAnswer = inputs.stringInput().toUpperCase();
            if (trueAnswer.equals("A")) {
                dbManager.createAnswers("A", questionId, answerA, true);
                dbManager.createAnswers("B", questionId, answerB, false);
                dbManager.createAnswers("C", questionId, answerC, false);
                break;
            } else if (trueAnswer.equals("B")) {
                dbManager.createAnswers("A", questionId, answerA, false);
                dbManager.createAnswers("B", questionId, answerB, true);
                dbManager.createAnswers("C", questionId, answerC, false);
                break;
            } else if (trueAnswer.equals("C")) {
                dbManager.createAnswers("A", questionId, answerA, false);
                dbManager.createAnswers("B", questionId, answerB, false);
                dbManager.createAnswers("C", questionId, answerC, true);
                break;
            } else {
                System.out.println("Ivesta bloga raide, bandykite dar karta!");
            }
        } while (true);


    }

    public void questionnaireUpdate(int questionnaire) {
        List<Question> questions = dbManager.getAllQueryQuestions(questionnaire);
        for (Question question : questions) {
            System.out.print(question.getId() + ".");
            System.out.print(question.getQuestionText() + "\n");
        }
        System.out.println("Iveskite klausimo ID, kuri norite redaguoti:");
        do {
            try {
                int questionId = inputs.intInput();
                if (questionId == dbManager.getQuestion(questionnaire, questionId)) {
                    System.out.println("Iveskite nauja klausima:");
                    inputs.stringNextLine();
                    String newQuestion = inputs.stringNextLine();
                    dbManager.updateQuestions(newQuestion, questionId);
                    System.out.println("Iveskite T raide jeigu norite atnaujinti atsakymus.");
                    System.out.println("Iveskite N raide jeigu nenorite atnaujinti atsakymus.");
                    String yesOrNo = inputs.stringNextLine();
                    if (yesOrNo.toUpperCase().equals("T")) {
                        answersUpdate(questionId);
                    } else if (yesOrNo.toUpperCase().equals("N")) {
                        menu.admin();
                    }
                    break;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Tokio ID nera");
            }
        } while (true);
    }

    public void answersUpdate(int questionId) {
        System.out.println("Iveskite naujus atsakymo variantus:");
        System.out.print("A:");
        String answerA = inputs.stringNextLine();
        System.out.print("B:");
        String answerB = inputs.stringNextLine();
        System.out.print("C:");
        String answerC = inputs.stringNextLine();
        System.out.println("Iveskite raide, kuri is triju varinatu yra teisinga");
        do {
            String trueAnswer = inputs.stringInput().toUpperCase();
            if (trueAnswer.equals("A")) {
                dbManager.updateAnswers(answerA, questionId, "A", true);
                dbManager.updateAnswers(answerB, questionId, "B", false);
                dbManager.updateAnswers(answerC, questionId, "C", false);
                System.out.println("Atnaujinimas ivykditas.");
                break;
            } else if (trueAnswer.equals("B")) {
                dbManager.updateAnswers(answerA, questionId, "A", false);
                dbManager.updateAnswers(answerB, questionId, "B", true);
                dbManager.updateAnswers(answerC, questionId, "C", false);
                System.out.println("Atnaujinimas ivykditas.");
                break;
            } else if (trueAnswer.equals("C")) {
                dbManager.updateAnswers(answerA, questionId, "A", false);
                dbManager.updateAnswers(answerB, questionId, "B", false);
                dbManager.updateAnswers(answerC, questionId, "C", true);
                System.out.println("Atnaujinimas ivykditas.");
                break;
            } else {
                System.out.println("Ivesta bloga raide, bandykite dar karta!");
            }
        } while (true);
    }

    public void questionnairePrinting() {
        int choice;
        do {
            Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
            System.out.println("Pasirinkite klausimyna:");
            dbManager.getQuestionnaires();
            choice = inputs.intInput();
            userQuestionnaireChoice(choice);
        } while (choice != 4);
    }

    public void userQuestionnaireChoice(int userChoice) {
        if (userChoice == 1) {
            questionnaireUpdate(userChoice);
        } else if (userChoice == 2) {
            questionnaireUpdate(userChoice);
        } else if (userChoice == 3) {
            questionnaireUpdate(userChoice);
        }
    }

    public void questionDelete() {
        System.out.println("Pasirinkite klausimyna:");
        dbManager.getQuestionnaires();
        int questionnaire = inputs.intInput();
        List<Question> questions = dbManager.getAllQueryQuestions(questionnaire);
        for (Question question : questions) {
            System.out.print(question.getId() + ".");
            System.out.print(question.getQuestionText() + "\n");
        }
        System.out.print("Iveskite klausimo id kuri norite istrinti:");
        int questionId = inputs.intInput();
        dbManager.deleteQuestion(questionId);
        dbManager.deleteAnswers(questionId);
        System.out.println();
        System.out.println("Klausimas istrintas");
    }

    public void results() {
        List<Results> results = dbManager.getResults();

        int totalEx = 0;
        int totalAns = 0;
        int totalA = 0;
        int totalB = 0;
        int totalC = 0;
        System.out.printf("%5s | %10s| %5s | %9s| %9s | %9s | %9s |", "Nr.", "Pavadinimas", "Spresta", "Teisingu atsakymu sk.", "Viso pasirinkta A", "Viso pasirinkta B", "Viso pasirinkta C");
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < results.size(); i++) {
            totalEx += results.get(i).getTotalExams();
            totalAns += results.get(i).getTotalCorrectAns();
            totalA += results.get(i).getTotalA();
            totalB += results.get(i).getTotalB();
            totalC += results.get(i).getTotalC();
            System.out.printf("%5s | %10s | %7s | %20s | %17s | %17s | %17s |", i + 1, results.get(i).getQuestionnaireName(), results.get(i).getTotalExams(), results.get(i).getTotalCorrectAns(), results.get(i).getTotalA(), results.get(i).getTotalB(), results.get(i).getTotalC());
            System.out.println();
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%5s | %10s| %7s | %20s | %17s | %17s | %17s |", "   ", "           ", "Viso", "Viso", "Viso", "Viso", "Viso");
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%5s | %10s| %7s | %20s | %17s | %17s | %17s |", "   ", "           ", totalEx, totalAns, totalA, totalB, totalC);
        System.out.println();
        System.out.println("Teisingu atskaymu vidurkis: " + ((double)totalAns / ((double)totalA+(double)totalB+(double)totalC)));
        System.out.println();
    }
}
