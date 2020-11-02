import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * Abstract class that extends Thread. It has a pipedreader and pipedwriter objects that are used to communicate
 * between pipes and filters. 
 */
public abstract class pipe extends Thread{

    protected pipe sink = null;
    protected PipedWriter out = null;
    protected PipedReader in = null;

    /**
     * Constructor that takes in a pipe object and connects the writer to the reader.
     */
    public pipe(pipe sink) throws IOException { 
        this.sink = sink; 
        out = new PipedWriter();
        out.connect(sink.getReader());
      }

    public pipe() 
    { 
        super(); 
    }

    /**
     * Creates a new PipedReader object if none exists.
     */
    public PipedReader getReader() {
        if (in == null) in = new PipedReader();
        return in;
      }

    
  
  
    
}
