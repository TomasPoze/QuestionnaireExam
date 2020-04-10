package lt.codeacademy.dbmanager;

import lt.codeacademy.questionnaire.Answer;
import lt.codeacademy.questionnaire.Question;
import lt.codeacademy.questionnaire.Questionnaire;
import lt.codeacademy.questionnaire.Results;
import lt.codeacademy.user.Admin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbManager {

    public void getQuestionnaires() {
        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        SessionFactory factory = new Configuration().configure().buildSessionFactory();

        Session session = factory.openSession();

        try{
            Query<Questionnaire> list = session.createQuery("From Questionnaire", Questionnaire.class);
            List<Questionnaire> questionnaires = list.list();
            questionnaires.forEach(System.out::println);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }


    }

    public List<Question> getAllQueryQuestions(int questionnaireChoice) {
        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        Query<Question> list = session.createQuery("FROM Question WHERE questionnaireId = '" + questionnaireChoice + "'", Question.class);
        //questions.forEach(System.out::println);

        return list.list();
    }

    public List<Answer> getAnswers(int questionId) {
        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        Query<Answer> list = session.createQuery("FROM Answer WHERE question_id = " + questionId + " GROUP BY answer_options , answer_text,id", Answer.class);


        return list.list();
    }

    public void createQuestion(int adminQuestionnaireChoice, String adminQuestionText) {
        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Question question = new Question();
            question.setQuestionnaireId(adminQuestionnaireChoice);
            question.setQuestionText(adminQuestionText);

            session.save(question);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void createAnswers(String answerOption, int questionId, String answerText, boolean trueFalse) {
        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Answer answer = new Answer();
            answer.setAnswerOption(answerOption);
            answer.setQuestionId(questionId);
            answer.setAnswerText(answerText);
            answer.setTrueFalse(trueFalse);

            session.save(answer);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateQuestions(String pakeitimas, int whichId) {
        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
//              Update
            Query<Question> query = session.createQuery("UPDATE Question SET question_text = :question_text WHERE id = :id");
            query.setParameter("question_text", pakeitimas);
            query.setParameter("id", whichId);
            query.executeUpdate();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateAnswers(String pakeitimas, int whichId, String option, boolean trueOrFalse) {
        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
//              Update
            Query<Question> query = session.createQuery("UPDATE Answer SET answer_text = :answer_text , true_false = :true_false WHERE question_id = :question_id AND answer_options = :answer_options");
            query.setParameter("answer_text", pakeitimas);
            query.setParameter("question_id", whichId);
            query.setParameter("answer_options", option);
            query.setParameter("true_false", trueOrFalse);
            query.executeUpdate();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public int getQuestion(int questionnaireChoice, int questionId) {
        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        Query<Question> list = session.createQuery("FROM Question WHERE questionnaireId = '" + questionnaireChoice + "' AND id = '" + questionId + "'", Question.class);
        List<Question> questions = list.list();
        //questions.forEach(System.out::println);
        return questions.get(0).getId();
    }

    public void deleteQuestion(int questionId) {
        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
//              Update
            Query<Question> query = session.createQuery("DELETE FROM Question WHERE id = :id");
            query.setParameter("id", questionId);

            query.executeUpdate();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteAnswers(int questionId) {
        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
//              Update
            Query<Question> query = session.createQuery("DELETE FROM Answer WHERE question_id = :question_id");
            query.setParameter("question_id", questionId);

            query.executeUpdate();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateResults(String total, int whichId) {
        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        Transaction tx = null;
        try {

            tx = session.beginTransaction();
//              Update
            Query<Results> query = session.createQuery("UPDATE Results SET " + total + " = " + total + "+:" + total + " WHERE id = :id");

            query.setParameter(total, +1);

            query.setParameter("id", whichId);
            query.executeUpdate();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<Results> getResults() {
        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        SessionFactory factory = new Configuration().configure().buildSessionFactory();

        Session session = factory.openSession();

        Query<Results> list = session.createQuery("From Results order by id", Results.class);

        return list.list();
    }

    public List<Admin> getAdmin(){
        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        SessionFactory factory = new Configuration().configure().buildSessionFactory();

        Session session = factory.openSession();

        Query<Admin> adminQuery = session.createQuery("From Admin", Admin.class);
        return adminQuery.list();
    }
}
