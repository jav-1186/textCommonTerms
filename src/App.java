import java.io.Reader;
import java.io.Writer;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.OutputStreamWriter;

public class App {
    public static void main(String[] args) throws Exception {
        
        int choice = 99;
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the number corresponding to the input file you want to run: ");
        System.out.println("1 - alice30");
        System.out.println("2 - usdeclar");
        System.out.println("3 - kjbible");
        System.out.print("Selection: ");

        choice = input.nextInt();

        if (choice == 1)
        {
            Reader in = new BufferedReader(new FileReader("src/alice30.txt"));
            Writer out = new BufferedWriter(new OutputStreamWriter(System.out));

            /**
             * Starting the pipes and filters
             */
            pipeOut last = new pipeOut(out);
            filter two = new stopWFilter(last);
            filter one = new charFilter(two);
            pipeIn source = new pipeIn(one, in);

            last.start();
            two.start();
            one.start();
            source.start();
    
        }

        else if (choice == 2)
        {
            Reader in = new BufferedReader(new FileReader("src/usdeclar.txt"));
            Writer out = new BufferedWriter(new OutputStreamWriter(System.out));

            /**
             * Starting the pipes and filters
             */
            pipeOut last = new pipeOut(out);
            filter two = new stopWFilter(last);
            filter one = new charFilter(two);
            pipeIn source = new pipeIn(one, in);

            last.start();
            two.start();
            one.start();
            source.start();
        }

        else if (choice == 3)
        {
            Reader in = new BufferedReader(new FileReader("src/kjbible.txt"));
            Writer out = new BufferedWriter(new OutputStreamWriter(System.out));

            /**
             * Starting the pipes and filters
             */
            pipeOut last = new pipeOut(out);
            filter two = new stopWFilter(last);
            filter one = new charFilter(two);
            pipeIn source = new pipeIn(one, in);

            last.start();
            two.start();
            one.start();
            source.start();
        }


    }
}
