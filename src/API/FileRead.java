package API;

import java.io.*;

public class FileRead
{
    public void readCSV()
    {
        String line;
        String filename = "resources/questions.csv" ;

        String category = new File(filename).getName().replaceFirst("[.][^.]+$", "");

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
                try
                {
                    new Question(values[0], values[1], Integer.parseInt(values[2]), category);
                }
                catch (NumberFormatException e)
                {
                    new Question(values[0], values[1], category);
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


    }
}