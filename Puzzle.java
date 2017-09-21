import java.util.Random;
import java.util.Scanner;
public class Puzzle{

	int [] eval_array = new int [4];
	int [] hillClimbing_array = new int [4];
	int [] randomRestarts_array = new int[4];
	int [] randomWalk_array = new int[4];
	int [] simulatedAnnealing_array = new int[4];
	int [] population_array = new int[4];
	/*
	//task 2
	int eval(int[][] matrix, int[][] visited){
	
	}

	//task 3
	void hillClimbing(int[][] matrix){

	}
	//task 4
	void randomRestarts(int[][] matrix){

	}
	//task 5
	void randomWalk(int[][] matrix){

	}
	//task 6 
	void simulatedAnnealing(int[][] matrix){

	}
	//task 7 
	void population(int[][] matrix){

	}

*/
	

	public static int getNum(int[][] matrix, int rowCord, int colCord){
		rowCord--;
		colCord--;
		return matrix[rowCord][colCord];
	}

	public static void fillMatrix(int[][] matrix){
		
		for(int row = 0; row < matrix.length; row++){
			for(int col = 0; col < matrix.length; col++){
				if(row == matrix.length - 1 && col == matrix.length - 1){
					break;
				}
				//creates a random class
				Random rand = new Random();
				//max number of jump for the index 
				int limit = (int) Math.max(Math.max(matrix.length - 1 - row, row - matrix.length - 1) , 
					Math.max(matrix.length - 1 - col, col - matrix.length - 1));
				//generates a random number from 1 to n - 1 
				int n = rand.nextInt((limit - 1) + 1) + 1;
				//fills in the indices 
				matrix[row][col] = n;
				//if the middle index is n - 1 then we randomly reassign it until it is not n - 1

			}
			System.out.println();
		}
	}

	public static void printMatrix(int[][] matrix){
		for(int row = 0; row < matrix.length; row++){
			for(int col = 0; col < matrix.length; col++){
				System.out.print(matrix[row][col] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args){

		System.out.println("Enter size of n");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		int[][] test = new int[n][n];
		int[][] visited = new int[n][n];
		fillMatrix(test);
		printMatrix(test);
		System.out.println();
		printMatrix(visited);

	}
}