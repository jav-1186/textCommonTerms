import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public abstract class pipe extends Thread{

    protected pipe sink = null;
    protected PipedWriter out = null;
    protected PipedReader in = null;

    public pipe(pipe sink) throws IOException { 
        this.sink = sink; 
        out = new PipedWriter();
        out.connect(sink.getReader());
      }

    public pipe() 
    { 
        super(); 
    }

    public PipedReader getReader() {
        if (in == null) in = new PipedReader();
        return in;
      }

    
  
  
    
}
