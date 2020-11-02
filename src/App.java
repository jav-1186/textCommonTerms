import java.io.Reader;
import java.io.Writer;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.OutputStreamWriter;

/**
 * Main method where the pipe and filter program is run from. Some credit for the pipe and filter
 * structure belongs to "https://resources.oreilly.com/examples/9781565923713/blob/master/Pipes.java".
 * I used that as a base for this assignment. 
 */
public class App {
    public static void main(String[] args) throws Exception {
        
        long startTime = System.nanoTime();
        File f = new File(".");
        String path = f.getCanonicalPath();
        /**
         * Scanner object to accept input from the user so they can select what file to run
         */
        int choice = 99;
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the number corresponding to the input file you want to run: ");
        System.out.println("1 - alice30");
        System.out.println("2 - usdeclar");
        System.out.println("3 - kjbible");
        System.out.print("Selection: ");

        choice = input.nextInt();

        /**
         * This block executes if the user selcets the "alice30" text file
         */
        if (choice == 1)
        {
            /**
             * Reader object which accepts the text file as input. I created a writer object
             * that can write to a file for extensibility, but it is basically unused.
             */
            Reader in = new BufferedReader(new FileReader("alice30.txt"));
            Writer out = new BufferedWriter(new OutputStreamWriter(System.out));

            /**
             * Creating the pipe and filter objects. Each filter is instantiated with a sink.
             * That connects the reader and writer objects together.
             */
            pipeOut last = new pipeOut(out);
            filter three = new porterFilter(last);
            filter two = new stopWFilter(three);
            filter one = new charFilter(two);
            pipeIn source = new pipeIn(one, in);

            /**
             * Starting the threads, going from the last sink to the source pipe.
             */
            last.start();
            three.start();
            two.start();
            one.start();
            source.start();
    
        }

         /**
         * This block executes if the user selcets the "usdeclar" text file
         */
        else if (choice == 2)
        {
            /**
             * Reader object which accepts the text file as input. I created a writer object
             * that can write to a file for extensibility, but it is basically unused.
             */
            Reader in = new BufferedReader(new FileReader("usdeclar.txt"));
            Writer out = new BufferedWriter(new OutputStreamWriter(System.out));

            /* 
             * Creating the pipe and filter objects. Each filter is instantiated with a sink.
             * That connects the reader and writer objects together.
             */
            pipeOut last = new pipeOut(out);
            filter three = new porterFilter(last);
            filter two = new stopWFilter(three);
            filter one = new charFilter(two);
            pipeIn source = new pipeIn(one, in);

            /**
             * Starting the threads, going from the last sink to the source pipe.
             */
            last.start();
            three.start();
            two.start();
            one.start();
            source.start();
        }

        /**
         * This block executes if the user selcets the "kjbible" text file
         */
        else if (choice == 3)
        {
            /**
             * Reader object which accepts the text file as input. I created a writer object
             * that can write to a file for extensibility, but it is basically unused.
             */
            Reader in = new BufferedReader(new FileReader("kjbible.txt"));
            Writer out = new BufferedWriter(new OutputStreamWriter(System.out));

            /* 
             * Creating the pipe and filter objects. Each filter is instantiated with a sink.
             * That connects the reader and writer objects together.
             */
            pipeOut last = new pipeOut(out);
            filter three = new porterFilter(last);
            filter two = new stopWFilter(three);
            filter one = new charFilter(two);
            pipeIn source = new pipeIn(one, in);

            /**
             * Starting the threads, going from the last sink to the source pipe.
             */
            last.start();
            three.start();
            two.start();
            one.start();
            source.start();
        }

        long stopTime = System.nanoTime();
        long time = (stopTime - startTime);
        //System.out.println("App run time = " + time + " miliseconds");
    }
}
