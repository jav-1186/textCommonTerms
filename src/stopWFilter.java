import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

/**
 * This class reads in the stop words and removes them from the text.
 */
public class stopWFilter extends filter {
    
    ArrayList<String> stopWords = new ArrayList<String>();
    ArrayList<String> toSend = new ArrayList<String>();

    /**
     * Constructor that takes in a sink object and connectes the writer to the reader.
     */
    public stopWFilter(pipe sink) throws IOException 
    { 
        super(sink); 
    }

    /**
     * This method provides stop word filtering and sends output to the porterFilter
     */
    public void filtering(Reader in, Writer out) throws IOException
    {

        System.out.println("Stop words filtering running!");
        System.out.println("Reading in stop words...");

        BufferedReader stopW = new BufferedReader(new FileReader("stopwords.txt"));
        String stop = stopW.readLine();

        /**
         * Reading in stop words and adding to the Array List
         */
        while (stop != null)
        {
            stopWords.add(stop);
            stop = stopW.readLine();
        }

        long startTime = System.nanoTime();

        BufferedReader br = new BufferedReader(in);
        
        /**
         * Removes stop words from text by not adding them to the array list
         */
        try { 
        
                String line = br.readLine();
            
                while (line != null) 
            {
               
                line = br.readLine();
                
                if(stopWords.contains(line))
                {
                    continue;
                }

                else
                {
                    if(line != null)
                    {
                        toSend.add(line);
                    }
                    
                }
                
            }
            
            System.out.println("Stop words have been filtered!");

            /**
             * Sending to the porter filter
             */
            for(String word : toSend)
             {
                
                word = word + "\n";
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
    //System.out.println("stopW runtime = " + time + " nanoseconds");

}
}

