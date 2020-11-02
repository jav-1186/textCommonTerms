import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Filter class that takes in data from the source pipe and removes all non characters
 * from the text. This took a lot of tooling, trimming, and troubleshooting to get everything.
 */
public class charFilter extends filter{

    ArrayList<String> charWords = new ArrayList<String>();

    /**
     * Constructor that takes in a pipe object as a sink so the reader and writer can connect.
     */
    public charFilter(pipe sink) throws IOException 
    { 
        super(sink); 
    }

    /**
     * Performs the character filtering on the incoming text
     */
    public void filtering (Reader in, Writer out) throws IOException
    {
        long startTime = System.nanoTime();
        System.out.println("Character filtering running!");

        BufferedReader br = new BufferedReader(in);
        
        /**
         * Using regex and pattern matching to split up the line of text into individual 
         * strings. All words are sent to lower case so they can be matched easier. Trimming
         * is done to delete all whitespace at the head or tail of the string.
         */
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
                    charWords.add(toSend);
                    
                }
                
                line =  br.readLine();
                
            }

            /**
             * For each loop into the words array and sending the output to the next sink which is stop words.
             */
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
      
          long stopTime = System.nanoTime();
          long time = (stopTime - startTime);
          //System.out.println("charIn runtime = " + time + " nanoseconds");
      }
  
        
  }
  