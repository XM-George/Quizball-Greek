package API;

import java.io.*;

public class FileRead
{
    public void readCSV()
    {
        String line;
        String filename = "resources/questions.csv" ;

        InputStream is = FileRead.class.getClassLoader().getResourceAsStream(filename);

        if (is == null)
        {
            throw new IllegalArgumentException("File not found: " + filename);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is)))
        {
            while ((line = br.readLine()) != null)
            {
                //χωρίζονται απο κόμμα
                String[] values = line.split(",");
                new Question(values[0], values[1]);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


    }
}