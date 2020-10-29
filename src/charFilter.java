import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class charFilter extends filter{

    ArrayList<String> charWords = new ArrayList<String>();

    public charFilter(pipe sink) throws IOException 
    { 
        super(sink); 
    }

    public void filtering (Reader in, Writer out) throws IOException
    {
        System.out.println("Character filtering running!");

        BufferedReader br = new BufferedReader(in);
        
        try { 
            
            String line = br.readLine();
            Pattern pattern = Pattern.compile("\\s+");
        
            while (line != null) 
            {
                
                line = line.toLowerCase();
                String [] words = pattern.split(line);
                for (int i = 0; i < words.length; i++)
                {
                
                    words[i] = words[i].replaceAll("[^a-zA-Z]", "");
                    words[i] = words[i].trim();

                    String toSend = words[i]+ "\n";
                    //System.out.println(words[i]);

                    charWords.add(toSend);
                    
                }
                
                line =  br.readLine();
                
            }

            System.out.println("char array size: " + charWords.size());

            for(String word : charWords)
             {
                int length = word.length();
                out.write(word, 0, length);
             }

            }
            catch (IOException e) {}
          // When done with the data, close the Reader and the pipe
          finally { try { in.close(); out.close(); } catch (IOException e) {} 
          }
      
      }
  
      
  
  
  }
  