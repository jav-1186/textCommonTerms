import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 * This class reads in the text file and is the first pipe in the chain
 */
public class pipeIn extends pipe{
    
    protected Reader in;

    /**
     * Constructor that takes in a pipe object that it can sink to and the reader object from the App class
     */
    public pipeIn(pipe sink, Reader in) throws IOException 
    { 
      super(sink);
      this.in = in; 
    }

    /**
     * This method runs once the thread is started.
     */
    public void run()
    {
        long startTime = System.nanoTime();
        System.out.println("The in-pipe is running!");
        BufferedReader br = new BufferedReader(in);
      
        /**
         * Reads in the text file line by line and places them into a string builder so I could
         * fix the line endings. I had an issue where the last word of a line would keep concatenating
         * with the first word of the next line. Using string builder and appending fixed it.
         */
        try { 
          
          String line = br.readLine();
          StringBuilder sb = new StringBuilder();
      
          while (line != null) 
          {
            
            /**
             * Appending new line character
             */
              sb.append(line);
              sb.append("\n");
              line = br.readLine();
              
              /**
               * Sending the text line to sink, which in this case is the charFilter
               */
              if(line != null)
              {
                out.write(sb.toString(), 0, sb.length());
              }

              /**
               * Clearing the stringbuilder every iteration
               */
              sb.delete(0, sb.length());
              
          }
          
        

          } 
          catch (IOException e) {}
          // When done with the data, close the Reader and the pipe
          finally { try { in.close(); out.close(); } catch (IOException e) {} 
        }
    
        long stopTime = System.nanoTime();
        long time = (stopTime - startTime);
        //System.out.println("pipeIn runtime = " + time + " miliseconds");
    }

  

}
