import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

public class stopWFilter extends filter {
    
    ArrayList<String> stopWords = new ArrayList<String>();
    ArrayList<String> toSend = new ArrayList<String>();

    public stopWFilter(pipe sink) throws IOException 
    { 
        super(sink); 
    }

    public void filtering(Reader in, Writer out) throws IOException
    {

        System.out.println("Stop words filtering running!");
        System.out.println("Reading in stop words...");

        BufferedReader stopW = new BufferedReader(new FileReader("src/stopwords.txt"));
        String stop = stopW.readLine();

        while (stop != null)
        {
            stopWords.add(stop);
            //System.out.println(stop);
            stop = stopW.readLine();
        }

        BufferedReader br = new BufferedReader(in);
        
        try { 
        
                String line = br.readLine();
            
                while (line != null) 
            {
                //System.out.println(line);
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
            System.out.println("stop array size: " + toSend.size());
            System.out.println("Stop words have been filtered!");

            for(String word : toSend)
             {
                //System.out.println(word);
                word = word + "\n";
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

