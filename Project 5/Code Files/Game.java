import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Game contains the main method used to run and test the application.
 * This solves the task of finding the best route from one endline to the other.
 * @author Sivalee Intachit
 *
 */
public class Game {
	/**
    static nested class serving as a vehicle to return two values from a method
    */
    public static class TwoValues {
        public int startingColumn;
        public int totalPoints;
    }
    
    /**
     * Method which finds the best starting point in a given field.
     * Uses bestRoute to recursively determine the best routes for each starting point.
     * Uses calcSum to recursively determine the number of each points that each route totals.
     * @param board represents the given field
     * @return twoValues which contain the starting column and total points of the best starting point
     */
    public static TwoValues bestStartingPoint(Field<Block> board) {
    	TwoValues twoValues = new TwoValues();
    	for (int i = 0; i < board.getWidth(); i++) {
    		if (calcSum(bestRoute(board, i), 0, 0) > twoValues.totalPoints) {
    			twoValues.startingColumn = i;
    			twoValues.totalPoints = calcSum(bestRoute(board, i), 0, 0);;
    		}
    	}
    	
    	return twoValues;
    }
    
    /**
     * Method which forms the best route from one endline to another with a given starting point.
     * Uses a helper method to recursively determine the best route.
     * @param board represents the given field
     * @param col represents the starting point
     * @return ArrayList which represents the most desirable route by storing all blocks in consecutive order
     */
    public static ArrayList<Block> bestRoute(Field<Block> board, int col) {
    	return nextBlock(board, col, 0, new ArrayList<Block>());
    }
    
    /**
     * Helper recursive method which finds the best path based on a given column. 
     * @param board represents the given field
     * @param currCol represents the current column
     * @param currRow represents the current row
     * @param route is an ArrayList that represents the most desirable route by storing each block
     * @return route
     */
    private static ArrayList<Block> nextBlock(Field<Block> board, int currCol, int currRow, ArrayList<Block> route) {
    	if (route.isEmpty()) {
    		// If the starting point is an obstacle, then it has no route
    		if (board.getElement(currRow, currCol) instanceof Obstacle) {
    			return route;
    		}
    		route.add(board.getElement(currRow, currCol));
    	}
    	if (currRow == board.getHeight() - 1) {
    		return route;
    	}
    	int maxNum = -9999999;
    	int maxIndex = 0;
    	
    	ArrayList<Block> consecBlocks = new ArrayList<Block>();
    	
    	consecBlocks.add(board.getElement(currRow + 1, currCol));
    	// If column is on the further-most left side, retrieve the bottom and bottom-right indexes
    	if (currCol == 0) {
    		consecBlocks.add(board.getElement(currRow + 1, currCol + 1));
    	}
    	// If the column is on the further-most right side, retrieve the bottom and bottom-left indexes
    	else if (currCol == board.getWidth() - 1) {
    		consecBlocks.add(0, board.getElement(currRow + 1, currCol - 1));
    	}
    	// Otherwise, retrieve the three consecutive indexes below the current column
    	else {
    		consecBlocks.add(0, board.getElement(currRow + 1, currCol - 1));
    		consecBlocks.add(board.getElement(currRow + 1, currCol + 1));
    	}
    	
    	// Find the consecutive block with the largest value of points and add its value to the sum
    	boolean hasConsec = false;
    	for (int i = 0; i < consecBlocks.size(); i++) {
    		if (consecBlocks.get(i) instanceof Passage && consecBlocks.get(i).getValue() > maxNum) {
    			maxNum = consecBlocks.get(i).getValue();
    			maxIndex = i;
    			hasConsec = true;
    		}
    	}
    	// If there are no consecutive blocks that are passages, the player has reached the end of its path
    	if (hasConsec == false) {
    		return route;
    	}
    	
    	// Iterate to the next row
    	currRow++;
    	
    	// Retrieve the actual index of the next item in order to retrieve the following blocks
    	int currIndex = 0;
    	if (currCol == 0 && consecBlocks.size() == 2) { // Left-most side
        	switch (maxIndex) {
        		case 0: currIndex = currCol; break;
        		case 1: currIndex = currCol + 1; break;
        	}
        }
        else if (currCol == board.getWidth() - 1 && consecBlocks.size() == 2) { // Right-most side
        	switch(maxIndex) {
        		case 0: currIndex = currCol - 1; break;
        		case 1: currIndex = currCol; break;
        	}
        }
        else {
	      	switch (maxIndex) { // Middle
	      		case 0: currIndex = currCol - 1; break;
				case 1: currIndex = currCol; break;
				case 2: currIndex = currCol + 1; break;
	        }
        }
        route.add(consecBlocks.get(maxIndex));
    	return nextBlock(board, currIndex, currRow, route);
    }
    /**
     * Recursive helper method which determines the total number of points of a given route.
     * @param route represents the given route
     * @param row represents the current row of each iteration
     * @param sum represents the current sum of each iteration
     * @return integer value representing the number of points total
     */
    private static int calcSum(ArrayList<Block> route, int row, int sum) {
    	int currSum = sum;
    	
    	if (row == route.size()) {
    		return currSum;
    	}
    	currSum += route.get(row).getValue();
    	
    	return calcSum(route, row + 1, currSum);
    }
    
    /**
     * Main method which is used to test and run the application.
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String args[]) throws FileNotFoundException
    {
        /**
        command line arguments validation
        */
        if (args.length != 1)
        {
            System.err.println("Usage: java " + Game.class.getName() + " <filename>");
//            return;
        }

        /**
        example of loading data from a file
        */
        Field<Block> field = FieldGenerator.loadDataFromFile(args[0]);

        /**
        example of generating a random Field
        */
//        Field<Block> field = FieldGenerator.randomIntegers(8,11,0,9,10);
        
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
//        Iterator<Block> it = field.iterator();
//        Iterator<Block> it = field.iterator("Passage");
        Iterator<Block> it = field.iterator("Obstacle"); // same thing for Obstacle objects
        while(it.hasNext())
            System.out.print(it.next() + " ");

        /**
        find the best starting point and print the results
        */
        TwoValues br = bestStartingPoint(field);
        System.out.println("Best starting point is in column " + br.startingColumn + " and the total points collected from this route is " + br.totalPoints);

        /**
        find the best route and print it
        */
//        for (int i = 0; i < field.getWidth(); i++) {
//        	ArrayList<Block> route = bestRoute(field, i);
//        	for (Block b : route) {
//        		System.out.print(b + " ");
//        	}
//        	System.out.println();
//        }
//        ArrayList<Block> al = bestRoute(field, br.startingColumn);
//        for (Block b : al)
//            System.out.print(b + " ");
    }
}
