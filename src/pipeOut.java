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
import java.util.Queue;
import java.util.TreeMap;

/**
 * This class is the last sink, and it provides the output to the user by determining
 * what terms have the highest values.
 */
public class pipeOut extends pipe{
    
    protected Writer out;
    HashMap<String, Double> counter = new HashMap<String, Double>();
    TreeMap<String, Double> alphabetical = new TreeMap<String, Double>();
    ArrayList<String> keys = new ArrayList<String>();

    /**
     * Constructor that takes in a Writer object to write to. Unused in this instance, but provides
     * extensibility for other applications.
     */
    public pipeOut(Writer out) throws IOException 
    { 
        super();  // Create a terminal Pipe with no sink attached.
        this.out = out; 
    }
  
    public void run()
    {
        long startTime = System.nanoTime();
        System.out.println("The out-pipe is running!");

        BufferedReader br = new BufferedReader(in);

        /**
         * Calculates totals for each term and puts them into a hashmap
         */
        try { 
            
            String line = br.readLine();
        
            while (line != null) 
            {
                
                line = br.readLine();

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

            

            /**
             * Sorting the hashmap by using a comparator. It is sorted by values so that the highest
             * values are printed first per the homework.
             */
            LinkedList<Map.Entry<String, Double>> list = new LinkedList<>(counter.entrySet());
            Comparator<Map.Entry<String, Double>> comparator = Comparator.comparing(Map.Entry::getValue);
            
            Collections.sort(list, comparator.reversed());
            
            
            int p = 0;
            Double alpha = 0.0;
            System.out.println("Top ten appearing terms:");
            PriorityQueue<String> pq = new PriorityQueue<>();
            Double value = 0.0;
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

                String key = word.getKey().toString();
                value = (Double) word.getValue();
                alphabetical.put(key, value);

                System.out.println(word.getKey().toString() + " = " + word.getValue());
                p++;
            }

            long stopTime = System.nanoTime();
          long time = (stopTime - startTime);
          //System.out.println("pipeOut runtime = " + time + " nanoseconds");

            } 
            catch (IOException e) {}
            // When done with the data, close the Reader and the pipe
            finally { try { in.close(); out.close(); } catch (IOException e) {} 

           
        
    }

    
    

}


}
