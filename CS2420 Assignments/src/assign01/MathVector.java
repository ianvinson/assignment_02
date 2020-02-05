package assign01;

import java.util.Arrays;

/**
 * This class represents a simple row or column vector of numbers.
 * In a row vector, the numbers are written horizontally (i.e., along the columns).
 * In a column vector, the numbers are written vertically (i.e., along the rows).
 * 
 * @author Erin Parker & Ian Vinson
 * @version January 9, 2020
 */
public class MathVector {

	// 2D array to hold the numbers of the vector, either along the columns or rows
	private double[][] data;      
	// set to true for a row vector and false for a column vector
	private boolean isRowVector;
	// count of elements in the vector
	private int vectorSize;
	
	/**
	 * Creates a new row or column vector.
	 * For a row vector, the input array is expected to have 1 row and a positive number of columns,
	 * and this number of columns represents the vector's length.
	 * For a column vector, the input array is expected to have 1 column and a positive number of rows,
	 * and this number of rows represents the vector's length.
	 * 
	 * @param data - a 2D array to hold the numbers of the vector
	 * @throws IllegalArgumentException if the numbers of rows and columns in the input 2D array is not 
	 *         compatible with a row or column vector
	 */
	public MathVector(double[][] data) {
		if(data.length == 0)
			throw new IllegalArgumentException("Number of rows must be positive.");
		if(data[0].length == 0)
			throw new IllegalArgumentException("Number of columns must be positive.");
		
		if(data.length == 1) {
			// This is a row vector with length = number of columns.
			this.isRowVector = true;
			this.vectorSize = data[0].length;
		}
		else if(data[0].length == 1) {
			// This is a column vector with length = number of rows.
			this.isRowVector = false;
			this.vectorSize = data.length;
		}
		else
			throw new IllegalArgumentException("Either the number of rows or the number of columns must be 1.");
		
		// Create the array and copy data over.
		if(this.isRowVector)
			this.data = new double[1][vectorSize];
		else
			this.data = new double[vectorSize][1];
		for(int i=0; i < this.data.length; i++) { 
			for(int j=0; j < this.data[0].length; j++) {
				this.data[i][j] = data[i][j];
			}
		}
	}
	
	/**
	 * Determines whether this vector is "equal to" another vector, where equality is
	 * defined as both vectors being row (or both being column), having the same 
	 * vector length, and containing the same numbers in the same positions.
	 * 
	 * @param other - another vector to compare
	 */
	public boolean equals(Object other) {
		if(!(other instanceof MathVector))
			return false;
		
		MathVector otherVec = (MathVector)other;
		// for a vector to be equal they must have the same number of columns and rows and have the same value in each location.
		// A conditional can check horizontal size
		if (data.length != otherVec.data.length)
			return false;
		// Checking each corresponding inner array for equality can do both height and value at once
		for(int i=0; i < data.length; i++) {
			if (!(Arrays.equals(data[i], otherVec.data[i]))) 
				return false;
		}
		return true;
	}
	
	/**
	 * Generates and returns a new vector that is the transposed version of this vector.
	 */
	public MathVector transpose() {
		// A new array is built and the number of columns becomes the number of rows or vice versa.
		// The values from the first array are then assigned to their corresponding locations. There is a slight difference in process depending on type
		double[][] newData;
		if (isRowVector) {
			newData = new double[vectorSize][1];
			for(int i=0; i < data[0].length; i++) {
				newData[i][0] = data[0][i];
			}
		}
		else {
			newData = new double[1][vectorSize];
			for(int i=0; i < data.length; i++) {
				newData[0][i] = data[i][0];
			}
		}
		return new MathVector(newData);
	}
	
	/**
	 * Generates and returns a new vector representing the sum of this vector and another vector.
	 * 
	 * @param other - another vector to be added to this vector
	 * @throws IllegalArgumentException if the other vector and this vector are not both row vectors of
	 *         the same length or column vectors of the same length
	 */
	public MathVector add(MathVector other) {
		// Checks to see if the two vectors are of the same type and have the same number of dimensions
		if (!((isRowVector == other.isRowVector) && (vectorSize == other.vectorSize)))
			throw new IllegalArgumentException();
		// Adding a vector is simply adding the values of corresponding components. The values of the two corresponding components simply need to 
		// be added and placed into  the corresponding location in a new array
		double[][] newData = data.clone();
		for(int i=0; i < data.length; i++) { 
			for(int j=0; j < data[0].length; j++) {
				newData[i][j] = data[i][j] + other.data[i][j];
			}
		}
		return new MathVector(newData);
	}
	
	/**
	 * Computes and returns the dot product of this vector and another vector.
	 * 
	 * @param other - another vector to be combined with this vector to produce the dot product
	 * @throws IllegalArgumentException if the other vector and this vector are not both row vectors of
	 *         the same length or column vectors of the same length
	 */	
	public double dotProduct(MathVector other) {
		// Checks to see if the two vectors are of the same type and have the same dimensions
		if (!((isRowVector == other.isRowVector) && (vectorSize == other.vectorSize)))
			throw new IllegalArgumentException();
		// To find the dot product you multiply together corresponding components then sum all of the products up (a1*b1 + a2*b2 + ...).
		double dotProduct = 0.0;
		for(int i=0; i < data.length; i++) { 
			for(int j=0; j < data[0].length; j++) {
				// Once multiplied the components are added to a total 
				dotProduct += data[i][j] * other.data[i][j];
			}
		}
		return dotProduct;
	}
	
	/**
	 * Computes and returns this vector's magnitude (also known as a vector's length) .
	 */
	public double magnitude() {
		// the magnitude of a vector is the square root of the sum of the square of each component/dimension.
		double magnitude = 0;
		for(int i=0; i < data.length; i++) {
			for(int j=0; j < data[0].length; j++) {
				// Every component is squared then added to a total which has the root taken after the loop is finished
				magnitude += Math.pow(data[i][j], 2.0);
			}
		}
		return Math.sqrt(magnitude);
	}
	
	/** 
	 * Generates and returns a normalized version of this vector.
	 */
	public MathVector normalize() {
		// A normalized vector is one whose magnitude is 1. To normalize a vector you must divide each of its components by its original magnitude.
		double[][] newData = data.clone();
		double magnitude = this.magnitude();
		for(int i=0; i < data.length; i++) { 
			for(int j=0; j < data[0].length; j++) {
				newData[i][j] = (data[i][j]/magnitude);
			}
		}
		return new MathVector(newData);
	}
	
	/**
	 * Generates and returns a textual representation of this vector.
	 * For example, "1.0 2.0 3.0 4.0 5.0" for a sample row vector of length 5 and 
	 * "1.0
	 *  2.0
	 *  3.0
	 *  4.0
	 *  5.0" for a sample column vector of length 5. 
	 *  In both cases, notice the lack of a newline or space after the last number.
	 */
	public String toString() {
		// To convert the vector to a string representation, every number is added to the string by looping 
		// and either a new line character or a space is added inbetween each iteration minus the last iteration depending on the type of the vector
		String vectorString = "";
		for(int i=0; i < data.length; i++) {
			for(int j=0; j < data[0].length; j++) {
				vectorString += ("" + data[i][j]);
				if (i < data.length - 1 )
					vectorString += '\n';
				else if (j < data[0].length - 1)
					vectorString += ' ';
			}
		}
		return vectorString;
	}
}