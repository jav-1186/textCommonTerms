import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Map.Entry;

public class pipeOut extends pipe{
    
    protected Writer out;
    HashMap<String, Double> counter = new HashMap<String, Double>();
    ArrayList<String> keys = new ArrayList<String>();

    public pipeOut(Writer out) throws IOException 
    { 
        super();  // Create a terminal Pipe with no sink attached.
        this.out = out; 
    }
  
    public void run()
    {
        System.out.println("The out-pipe is running!");

        BufferedReader br = new BufferedReader(in);

        try { 
            
            String line = br.readLine();
        
            while (line != null) 
            {
                
                line = br.readLine();
                //System.out.println(line);

                if(counter.containsKey(line))
                {
                    Double temp = counter.get(line);
                    counter.remove(line);
                    counter.put(line, temp + 1);
                }

                else
                {
                    Double initial = 1.0;
                    counter.put(line, initial);
                    keys.add(line);
                }
                
            }

            
            int q = 0;
           
            List<String> keyH = new ArrayList<>();
            Iterator<Map.Entry<String,Double>> iter = counter.entrySet().iterator();

            LinkedList<Map.Entry<String, Double>> list = new LinkedList<>(counter.entrySet());
            Comparator<Map.Entry<String, Double>> comparator = Comparator.comparing(Map.Entry::getValue);
            Collections.sort(list, comparator.reversed());

            //System.out.println(list.listIterator(10));
            
            int p = 0;
            System.out.println("Top ten appearing terms:");
            for (Map.Entry word : list)
            {
                if (p >= 10)
                {
                    break;
                }

                if (word.getKey().toString().equalsIgnoreCase(""))
                {
                    continue;
                }

                
                System.out.println(word.getKey().toString() + " = " + word.getValue());
                p++;
            }
            
            



            
            
            

            } 
            catch (IOException e) {}
            // When done with the data, close the Reader and the pipe
            finally { try { in.close(); out.close(); } catch (IOException e) {} 

        
    }

}
}
