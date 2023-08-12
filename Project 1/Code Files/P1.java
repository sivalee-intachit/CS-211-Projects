package Project_1;
public class P1 {
	public static void main(String[] args) {
		
	}
	public static int stringValue(String word) {
		int solution = 0;
		char[] charList = new char[word.length()];
		// Place each char within string into temporary list
		for (int i = 0; i < word.length(); i++) {
			charList[i] = word.charAt(i);
		}
		// Check & remove double alphabetical chars for proper evaluation
		for (int i = 0; i < charList.length; i++) {
			if (i != (charList.length - 1) && charList[i] == charList[i+1] && (charList[i] >= 65 && charList[i] <= 90 || charList[i] >= 97 && charList[i] <= 122)) {
				charList[i+1] = 0;
			}
		}
		// Check for max char incase of numerical characters
		int maxChar = 0;
		for (int i = 0; i < charList.length; i++) {
			if (charList[i] > maxChar) {
				maxChar = charList[i];
			}
		}
		// Evaluate solution
		for (int i = 0; i < charList.length; i++) {
			if (charList[i] == ' ') {
				continue;
			}
			else if ((charList[i] >= 65 && charList[i] <= 122) || (word.charAt(i) >= 40 && word.charAt(i) <= 47) || (word.charAt(i) >= 58 && word.charAt(i) <= 64) || word.charAt(i) == 123) { // alphabetical chars
				solution += word.charAt(i);
			}
			else if ((charList[i] >= 48 && charList[i] <= 57)) { // numerical chars
				solution += word.charAt(i) * maxChar;
			}
		}
		return solution;
	}
	public static double expValue(int x, double precision) {
		double sum = 1.0;
		double improvement = 1.0;
		int denominator = 1;
		for (int i = 1; i >= 0; i++) {
			improvement += Math.pow(x, i) / denominator;
			if ((improvement - sum) < precision) {
				break;
			}
			else {
				sum += Math.pow(x, i) / denominator;
				denominator *= i + 1;
			}
		}
		return sum;
	}
	public static int mirrorNum(int num) {
		int mirroredNum = 0;
		while (num > 0 || num < 0) {
			if (num % 10 == 0 && num % 100 >= 1) { // Special case for 0s in middle of number
				num /= 10;
				mirroredNum = mirroredNum * 100 + (num % 10);
				num /= 10;
			}
			else {
				mirroredNum = mirroredNum * 10 + (num % 10);
				num /= 10;
			}
		}
		return mirroredNum;
	}
	public static boolean raisedNum(long num) {
		for (int x = 2; x < num; x++) {
			for (int y = 2; y < num; y++) {
				if (Math.pow(x, y) + Math.pow(y, x) == num) {
					return true;
				}
			}
		}
		return false;
	}
	public static int[][] smallestSubarray(int[][] array, int sum) {
		int subDimensions = 2;
		int maxSum = 0;
		int[][] subArray = new int[subDimensions][subDimensions];
		while (subDimensions < array.length) {
			for (int i = 0; i < array.length - subDimensions + 1; i++) {
				for (int j = 0; j < array[i].length - subDimensions + 1; j++) {
					int tempSum = 0;
					int[][] tempArray = new int[subDimensions][subDimensions];
					for (int k = 0; k < subDimensions; k++) {
						for (int l = 0; l < subDimensions; l++) {
							tempArray[k][l] = array[i+k][j+l];
							tempSum += array[i+k][j+l];
						}
					}
					if (tempSum >= sum && tempSum > maxSum) {
						maxSum = tempSum;
						subArray = tempArray;
					}
				}
			}
			if (maxSum != 0) {
				break;
			}
			subDimensions++;
		}
		if (maxSum == 0) {
			return array;
		}
		return subArray;
	}
	public static void replaceElement(int[][] array, int elem, int[] newElem) {
		if (newElem.length > 1) { // Create new & extended sub-array if num of new elems > 1
			int shift = 0;
			for (int i = 0; i < array.length; i++) {
				int elemCount = 0;
				for (int j = 0; j < array[i].length; j++) {
					if (array[i][j] == elem) {
						elemCount += 1;
					}
				}
				int[] subArray = new int[array[i].length + (newElem.length - 1) * elemCount];
				int counter = 0;
				for (int j = 0; j < subArray.length; j++) {
					if (array[i][j-shift] == elem) {
						for (int k = 0; k < newElem.length; k++) {
							subArray[j+k] = newElem[counter];
							counter++;
						}
						shift += newElem.length - 1;
						j += newElem.length -1;
					}
					else {
						subArray[j] = array[i][j-shift];
					}
				}
				// Reassign subarray to original array
				array[i] = subArray;
				shift = 0;
			}
		}
		else { // If num newElems = 1, simply reassign value
			for (int i = 0; i < array.length; i++) {
				for (int j = 0; j < array[i].length; j++) {
					if (array[i][j] == elem) {
						array[i][j] = newElem[0];
					}
				}
			}
		}
	}
	public static int[][] removeDuplicates(int[][] array) {
		int newArray[][] = new int[array.length][];
		int previousIndex = -98452311;
		int emptyCount = 0;
		for (int i = 0; i < array.length; i++) {
			int uniqueCounter = 0;
			int duplicateCount = 0;
			int[] tempArray = new int[array[i].length];
			for (int j = 0; j < array[i].length; j++) {
				if (array[i][j] == previousIndex) { // Count num of duplicates if found
					duplicateCount++;
					continue;
				}
				else { // If unique elem found, add to tempArray
					tempArray[uniqueCounter] = array[i][j];
					previousIndex = array[i][j];
					uniqueCounter++;
				}
			}
			int subArray[] = new int[tempArray.length - duplicateCount];
			for (int j = 0; j < subArray.length; j++) { // Create new subarray without extra spacing
				subArray[j] = tempArray[j];

			}
			if (subArray.length == 0) { // Checks for empty arrays after removing duplicates, doesn't add to newArray if found
				emptyCount++;
				continue;
			}
			else {
				newArray[i-emptyCount] = subArray;
			}
		}
		if (emptyCount > 0) { // If empty arrays founds, create new return array which doesn't include empty arrays
			int result[][] = new int[newArray.length-emptyCount][];
			for (int i = 0; i < result.length; i++) {
				result[i] = newArray[i];
			}
			return result;
		}
		return newArray;
	}
	public static int[] vortex(int[][] array) {
		int[] clockwiseArray = new int[array.length * array[0].length];
		int index = 0;
		int rowMax = array.length; int rowMin = 0;
		int colMax = array[0].length; int colMin = 0;
		// Loop for traversing clockwise
		while (index < clockwiseArray.length) {
			int currRow = rowMin;
			int currCol = colMin;
			if (rowMin == rowMax) { // Middle (if given vortex with row length of an odd integer)
				clockwiseArray[index] = array[currRow][currCol];
				break;
			} 
			while (currCol < colMax) { // Right
				clockwiseArray[index] = array[currRow][currCol];
				currCol++; index++;
			}
			currRow += 1;
			if (currCol == colMax) { // Down
				currCol--;
				while (currRow < rowMax) {
					clockwiseArray[index] = array[currRow][currCol];
					currRow++; index++;
				}
			}
			if (currRow == rowMax) { // Left
				currRow--; currCol--;
				while (currCol >= colMin) {
					clockwiseArray[index] = array[currRow][currCol];
					currCol--; index++;
				}
			}
			if (currCol == colMin - 1) { // Up
				currCol++; currRow--;
				while (currRow > colMin) {
					clockwiseArray[index] = array[currRow][currCol];
					currRow--; index++;
				}
			}
			rowMin++; rowMax--; colMin++; colMax--;
		}
		return clockwiseArray;
	}
}