package API;

import java.util.ArrayList;

public class Question
{
    public static ArrayList<Question> questions = new ArrayList<Question>();

    public String question;
    public String answer;

    public Question()
    {

    }

    public Question(String question, String answer)
    {
        this.question = question;
        this.answer = answer;
        questions.add(this);
    }
}