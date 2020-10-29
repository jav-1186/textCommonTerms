import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;
import java.io.Reader;

public class pipeIn extends pipe{
    
    protected Reader in;

    public pipeIn(pipe sink, Reader in) throws IOException 
    { 
      super(sink);
      this.in = in; 
    }

    public void run()
    {
        System.out.println("The in-pipe is running!");
        BufferedReader br = new BufferedReader(in);

        try { 
          StringBuilder sb = new StringBuilder();
          String line = br.readLine();
      
          while (line != null) 
          {
              
              sb.append(line);
              sb.append(System.lineSeparator());
              line = br.readLine();
              
              if(line != null)
              {
                int length = line.length();
                out.write(line, 0, length);
              }
              
          }
          //String everything = sb.toString();
          //System.out.println(everything);
          } 
          catch (IOException e) {}
          // When done with the data, close the Reader and the pipe
          finally { try { in.close(); out.close(); } catch (IOException e) {} 
        }
    
    }

    public PipedReader getReader() {
      throw new Error("Can't connect to a ReaderPipeSource!");
    }


}
