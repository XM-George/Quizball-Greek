package API;

public class RngQuestionGenerator
{
    public int generateQuestion()
    {
        return (int) (Math.random() * Question.questions.size());
    }
}