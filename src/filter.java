import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * Abstract class that extends pipe. It provides the basic outline for constructors, the run method,
 * and filtering. 
 */
public abstract class filter extends pipe {

    /**
     * Constructor that takes in a pipe object and calls the super or pipe constructor. 
     */
    public filter (pipe sink) throws IOException 
    { 
        super(sink); 
    }

    public filter() {}

    /**
     * Run method when the thread is started. Automatically calls the filtering method.
     */
	public void run() {
        try { filtering(in, out); } 
        catch (IOException e) {}
        finally { try { in.close(); out.close(); } catch (IOException e) {} }
      }
  

    abstract public void filtering (Reader in, Writer out) throws IOException;
    
}
