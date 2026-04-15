package API;

public class Question
{
    public static int questionNumber;
    private static int current = 0;
    public static Question[] questions;

    public String question;
    public String answer;

    public Question()
    {

    }

    public Question(String question, String answer)
    {
        this.question = question;
        this.answer = answer;
        questions[current] = this;
        current++;
    }
}