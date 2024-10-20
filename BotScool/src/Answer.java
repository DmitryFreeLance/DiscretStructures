public class Answer {
    private String text;
    private EnglishLevel userLevel;

    public Answer(EnglishLevel userLevel, String text){
        this.text = text;
        this.userLevel = userLevel;
    }

    public EnglishLevel getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(EnglishLevel userLevel) {
        this.userLevel = userLevel;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
