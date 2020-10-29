import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public abstract class filter extends pipe {

    public filter (pipe sink) throws IOException 
    { 
        super(sink); 
    }

    public filter() {}

	public void run() {
        try { filtering(in, out); } 
        catch (IOException e) {}
        finally { try { in.close(); out.close(); } catch (IOException e) {} }
      }
  

    abstract public void filtering (Reader in, Writer out) throws IOException;
    
}
