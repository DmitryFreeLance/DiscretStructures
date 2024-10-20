import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TGUser {
    private Long userId;
    private List<Question> questions = new ArrayList<>();
    private Integer EnglishLevelPoints = 0;
    private Integer EnglishLevelWrongPoints = 0;
    private boolean isNewUser = true;
    private boolean inTestSession;

    public TGUser(Long userId) {
        this.userId = userId;
        fillQuestions();
    }

    private void fillQuestions(){
        questions.add(new Question(Text.Q1, Arrays.asList(
                new Answer(EnglishLevel.WrongPoints, Text.Q1_A1),
                new Answer(EnglishLevel.Points, Text.Q1_A2),
                new Answer(EnglishLevel.WrongPoints, Text.Q1_A3),
                new Answer(EnglishLevel.WrongPoints, Text.Q1_A4)
        )));

        questions.add(new Question(Text.Q2, Arrays.asList(
                new Answer(EnglishLevel.WrongPoints, Text.Q2_A1),
                new Answer(EnglishLevel.WrongPoints, Text.Q2_A2),
                new Answer(EnglishLevel.Points, Text.Q2_A3),
                new Answer(EnglishLevel.WrongPoints, Text.Q2_A4)
        )));

        questions.add(new Question(Text.Q3, Arrays.asList(
                new Answer(EnglishLevel.Points, Text.Q3_A1),
                new Answer(EnglishLevel.WrongPoints, Text.Q3_A2),
                new Answer(EnglishLevel.WrongPoints, Text.Q3_A3),
                new Answer(EnglishLevel.WrongPoints, Text.Q3_A4)
        )));

        questions.add(new Question(Text.Q4, Arrays.asList(
                new Answer(EnglishLevel.WrongPoints, Text.Q4_A1),
                new Answer(EnglishLevel.WrongPoints, Text.Q4_A2),
                new Answer(EnglishLevel.Points, Text.Q4_A3),
                new Answer(EnglishLevel.WrongPoints, Text.Q4_A4)
        )));

        questions.add(new Question(Text.Q5, Arrays.asList(
                new Answer(EnglishLevel.Points, Text.Q5_A1),
                new Answer(EnglishLevel.WrongPoints, Text.Q5_A2),
                new Answer(EnglishLevel.WrongPoints, Text.Q5_A3),
                new Answer(EnglishLevel.WrongPoints, Text.Q5_A4)
        )));

        questions.add(new Question(Text.Q6, Arrays.asList(
                new Answer(EnglishLevel.WrongPoints, Text.Q6_A1),
                new Answer(EnglishLevel.Points, Text.Q6_A2),
                new Answer(EnglishLevel.WrongPoints, Text.Q6_A3),
                new Answer(EnglishLevel.WrongPoints, Text.Q6_A4)
        )));

        questions.add(new Question(Text.Q7, Arrays.asList(
                new Answer(EnglishLevel.WrongPoints, Text.Q7_A1),
                new Answer(EnglishLevel.Points, Text.Q7_A2),
                new Answer(EnglishLevel.WrongPoints, Text.Q7_A3),
                new Answer(EnglishLevel.WrongPoints, Text.Q7_A4)
        )));

        questions.add(new Question(Text.Q8, Arrays.asList(
                new Answer(EnglishLevel.Points, Text.Q8_A1),
                new Answer(EnglishLevel.WrongPoints, Text.Q8_A2),
                new Answer(EnglishLevel.WrongPoints, Text.Q8_A3),
                new Answer(EnglishLevel.WrongPoints, Text.Q8_A4)
        )));

        questions.add(new Question(Text.Q9, Arrays.asList(
                new Answer(EnglishLevel.WrongPoints, Text.Q9_A1),
                new Answer(EnglishLevel.WrongPoints, Text.Q9_A2),
                new Answer(EnglishLevel.WrongPoints, Text.Q9_A3),
                new Answer(EnglishLevel.Points, Text.Q9_A4)
        )));

        questions.add(new Question(Text.Q10, Arrays.asList(
                new Answer(EnglishLevel.WrongPoints, Text.Q10_A1),
                new Answer(EnglishLevel.WrongPoints, Text.Q10_A2),
                new Answer(EnglishLevel.Points, Text.Q10_A3),
                new Answer(EnglishLevel.WrongPoints, Text.Q10_A4)
        )));

        questions.add(new Question(Text.Q11, Arrays.asList(
                new Answer(EnglishLevel.WrongPoints, Text.Q11_A1),
                new Answer(EnglishLevel.Points, Text.Q11_A2),
                new Answer(EnglishLevel.WrongPoints, Text.Q11_A3),
                new Answer(EnglishLevel.WrongPoints, Text.Q11_A4)
        )));

        questions.add(new Question(Text.Q12, Arrays.asList(
                new Answer(EnglishLevel.WrongPoints, Text.Q12_A1),
                new Answer(EnglishLevel.WrongPoints, Text.Q12_A2),
                new Answer(EnglishLevel.WrongPoints, Text.Q12_A3),
                new Answer(EnglishLevel.Points, Text.Q12_A4)
        )));

        questions.add(new Question(Text.Q13, Arrays.asList(
                new Answer(EnglishLevel.WrongPoints, Text.Q13_A1),
                new Answer(EnglishLevel.WrongPoints, Text.Q13_A2),
                new Answer(EnglishLevel.Points, Text.Q13_A3),
                new Answer(EnglishLevel.WrongPoints, Text.Q13_A4)
        )));

        questions.add(new Question(Text.Q14, Arrays.asList(
                new Answer(EnglishLevel.Points, Text.Q14_A1),
                new Answer(EnglishLevel.WrongPoints, Text.Q14_A2),
                new Answer(EnglishLevel.WrongPoints, Text.Q14_A3),
                new Answer(EnglishLevel.WrongPoints, Text.Q14_A4)
        )));

        questions.add(new Question(Text.Q15, Arrays.asList(
                new Answer(EnglishLevel.WrongPoints, Text.Q15_A1),
                new Answer(EnglishLevel.WrongPoints, Text.Q15_A2),
                new Answer(EnglishLevel.Points, Text.Q15_A3),
                new Answer(EnglishLevel.WrongPoints, Text.Q15_A4)
        )));

        questions.add(new Question(Text.Q16, Arrays.asList(
                new Answer(EnglishLevel.WrongPoints, Text.Q16_A1),
                new Answer(EnglishLevel.Points, Text.Q16_A2),
                new Answer(EnglishLevel.WrongPoints, Text.Q16_A3),
                new Answer(EnglishLevel.WrongPoints, Text.Q16_A4)
        )));

        questions.add(new Question(Text.Q17, Arrays.asList(
                new Answer(EnglishLevel.Points, Text.Q17_A1),
                new Answer(EnglishLevel.WrongPoints, Text.Q17_A2),
                new Answer(EnglishLevel.WrongPoints, Text.Q17_A3),
                new Answer(EnglishLevel.WrongPoints, Text.Q17_A4)
        )));

        questions.add(new Question(Text.Q18, Arrays.asList(
                new Answer(EnglishLevel.WrongPoints, Text.Q18_A1),
                new Answer(EnglishLevel.WrongPoints, Text.Q18_A2),
                new Answer(EnglishLevel.Points, Text.Q18_A3),
                new Answer(EnglishLevel.WrongPoints, Text.Q18_A4)
        )));

        questions.add(new Question(Text.Q19, Arrays.asList(
                new Answer(EnglishLevel.WrongPoints, Text.Q19_A1),
                new Answer(EnglishLevel.WrongPoints, Text.Q19_A2),
                new Answer(EnglishLevel.WrongPoints, Text.Q19_A3),
                new Answer(EnglishLevel.Points, Text.Q19_A4)
        )));
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Integer getEnglishLevelPoints() {
        return EnglishLevelPoints;
    }

    public void setEnglishLevelPoints(Integer englishLevelPoints) {
        EnglishLevelPoints = englishLevelPoints;
    }

    public Integer getEnglishLevelWrongPoints() {
        return EnglishLevelWrongPoints;
    }

    public void setEnglishLevelWrongPoints(Integer englishLevelWrongPoints) {
        EnglishLevelWrongPoints = englishLevelWrongPoints;
    }

    public boolean isNewUser() {
        return isNewUser;
    }

    public void setNewUser(boolean newUser) {
        isNewUser = newUser;
    }

    public boolean isInTestSession() {
        return inTestSession;
    }

    public void setInTestSession(boolean inTestSession) {
        this.inTestSession = inTestSession;
    }
}
