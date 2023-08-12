/**
 * Game contains the main method used to run and test the application.
 * @author Sivalee Intachit
 *
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.ArrayList;

public class Test
{
    /**
    static nested class serving as a vehicle to return two values from a method
    */
    public static class TwoValues
    {
        public int startingColumn;
        public int totalPoints;
    }

    public static void main(String args[]) throws FileNotFoundException
    {
        /**
        command line arguments validation
        */
        if (args.length != 1)
        {
            System.err.println("Usage: java " + Test.class.getName() + " <filename>");
//            return;
        }

        /**
        example of loading data from a file
        */
//        Field<Block> field = FieldGenerator.loadDataFromFile(args[0]);
//        Field<Block> field = FieldGenerator.loadDataFromFile(args[0]);

        /**
        example of generating a random Field
        */
        Field<Block> field = FieldGenerator.randomIntegers(8,11,0,9,10);
        
        /**
        print the whole field
        */
        System.out.println(field);

        /**
        Example of running a foreach loop
        This will invoke the default iterator (the one using the anonymous inner class)
        */
//        for(Block b:field)
//            System.out.println(b);

        /**
        Example of running a while loop
        This will invoke the overloaded iterator (the one using the FieldIterator class)
        */
        Iterator<Block> it = field.iterator();
//        Iterator<Block> it = field.iterator("Passage");
//        Iterator<Block> it = field.iterator("Obstacle"); // same thing for Obstacle objects
        while(it.hasNext())
            System.out.print(it.next() + " ");

        /**
        find the best starting point and print the results
        */
//        TwoValues br = bestStartingPoint(field);
//        System.out.println("Best starting point is in column " + br.startingColumn + " and the total points collected from this route is " + br.totalPoints);

        /**
        find the best route and print it
        */
//        ArrayList<Block> al = bestRoute(field, br.startingColumn);
//        for (Block b : al)
//            System.out.println(b);
    }

}
