package lt.codeacademy.questionnaire;

import javax.persistence.*;

@Entity
@Table(name = "results")
public class Results {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String questionnaireName;

    @Column(name = "total_finished_exams_count")
    private int totalExams;

    @Column(name = "total_correct_answers")
    private int totalCorrectAns;

    @Column(name = "total_a")
    private int totalA;

    @Column(name = "total_b")
    private int totalB;

    @Column(name = "total_C")
    private int totalC;

    public Results() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionnaireName() {
        return questionnaireName;
    }

    public void setQuestionnaireName(String questionnaireName) {
        this.questionnaireName = questionnaireName;
    }

    public int getTotalExams() {
        return totalExams;
    }

    public void setTotalExams(int totalExams) {
        this.totalExams = totalExams;
    }

    public int getTotalCorrectAns() {
        return totalCorrectAns;
    }

    public void setTotalCorrectAns(int totalCorrectAns) {
        this.totalCorrectAns = totalCorrectAns;
    }

    public int getTotalA() {
        return totalA;
    }

    public void setTotalA(int totalA) {
        this.totalA = totalA;
    }

    public int getTotalB() {
        return totalB;
    }

    public void setTotalB(int totalB) {
        this.totalB = totalB;
    }

    public int getTotalC() {
        return totalC;
    }

    public void setTotalC(int totalC) {
        this.totalC = totalC;
    }

    @Override
    public String toString() {
        return "Results{" +
                "id=" + id +
                ", questionnaireName='" + questionnaireName + '\'' +
                ", totalExams=" + totalExams +
                ", totalCorrectAns=" + totalCorrectAns +
                ", totalA=" + totalA +
                ", totalB=" + totalB +
                ", totalC=" + totalC +
                '}';
    }
}
