import java.util.Random;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
import java.util.*;
public class Puzzle{

	
	// static int [][] matrix_five = new int[5][5];
	// static int [][] matrix_seven = new int[7][7];
	// static int [][] matrix_nine = new int[9][9];
	// static int [][] matrix_eleven = new int[11][11];

	static Result [] eval_array = new Result[4];
	static Result [] hillClimbing_array = new Result[4];
	static Result [] randomRestarts_array = new Result[4];
	static Result [] randomWalk_array = new Result[4];
	static Result [] simulatedAnnealing_array = new Result[4];
	static Result [] population_array = new Result[4];

	static public class Result{
		int evaluation; 
		int [][] bestMatrix;
		long time; 

		Result(int evaluation,  int [][] bestMatrix, long time){
			this.evaluation = evaluation;
			this.bestMatrix = bestMatrix;
			this.time = time;
		}

		void print(){
			System.out.println("evaluation: " + evaluation);
			printMatrix(bestMatrix);
			System.out.println("time: " + time);
		}

		

	
	}

	static void fillDummyResult(Result[] result){
		for(int i = 0; i < result.length; i++){
			Result dummy = new Result(0, new int[5 + 2*i][5 + 2*i], 0);
			result[i] = dummy;
		}
	}

	public class Node{
		int rowIndex;
		int colIndex;
		int jump; 
		int level;
		List<Node> children = new ArrayList<>();
		
		
		Node(int rowIndex, int colIndex, int jump, int level, List<Node> children){
			this.rowIndex = rowIndex;
			this.colIndex = colIndex;
			this.jump = jump; 
			this.level = level;
			this.children = children; 
			
		}
	}

	public class Tree{
		Node root; 
		int posGoal;
		int negGoal;

		Tree(Node root){
			this.root = root;
		}

		void addChild(Node parent, int[][] matrix, int [][] jumpMatrix, int[][] visitedMatrix, Queue<Node> q){

			// //base case
			// if(parent.children == null){
			// 	return parent;
			// }
			//adding children to the queue 
			for(int i = 0; i < parent.children.size(); i++){
				q.add(parent.children.get(i));
			}
			if(q.isEmpty()){
				return;
			}
			//remove the head 
			Node child = q.remove();
			if(child.jump == 0){
				posGoal = child.level;
			}
			int level = child.level + 1;
			child.children = new ArrayList<>();
			//up
			if(child.rowIndex - child.jump > -1 && visitedMatrix[child.rowIndex - child.jump][child.colIndex] == 0){
				Node newNode = new Node(child.rowIndex - child.jump, child.colIndex, matrix[child.rowIndex - child.jump][child.colIndex], level, null);
				child.children.add(newNode);
				visitedMatrix[child.rowIndex - child.jump][child.colIndex] = 1;
				jumpMatrix[child.rowIndex - child.jump][child.colIndex] = level;
				//System.out.println(jumpMatrix[child.rowIndex - child.jump][child.colIndex]);
				//q.add(newNode);
			}
			//down
			if(child.rowIndex + child.jump < matrix.length && visitedMatrix[child.rowIndex + child.jump][child.colIndex] == 0){
				Node newNode = new Node(child.rowIndex + child.jump, child.colIndex, matrix[child.rowIndex + child.jump][child.colIndex], level, null);
				child.children.add(newNode);
				visitedMatrix[child.rowIndex + child.jump][child.colIndex] = 1;
				jumpMatrix[child.rowIndex + child.jump][child.colIndex] = level;

				//System.out.println(jumpMatrix[child.rowIndex + child.jump][child.colIndex]);
				//q.add(newNode);
			}
			//left
			if(child.colIndex - child.jump > -1 && visitedMatrix[child.rowIndex][child.colIndex - child.jump] == 0){
				Node newNode = new Node(child.rowIndex, child.colIndex - child.jump, matrix[child.rowIndex][child.colIndex - child.jump], level, null);
				child.children.add(newNode);
				visitedMatrix[child.rowIndex][child.colIndex - child.jump] = 1;
				jumpMatrix[child.rowIndex][child.colIndex - child.jump] = level;

				//System.out.println(jumpMatrix[child.rowIndex][child.colIndex - child.jump]);
				//q.add(newNode);
			}
			//down
			if(child.colIndex + child.jump < matrix.length && visitedMatrix[child.rowIndex][child.colIndex + child.jump] == 0){
				Node newNode = new Node(child.rowIndex, child.colIndex + child.jump, matrix[child.rowIndex][child.colIndex + child.jump], level, null);
				child.children.add(newNode);
				visitedMatrix[child.rowIndex][child.colIndex + child.jump] = 1;
				jumpMatrix[child.rowIndex][child.colIndex + child.jump] = level;

				//System.out.println(jumpMatrix[child.rowIndex][child.colIndex + child.jump]);
				//q.add(newNode);
			}
			//visitedMatrix[child.rowIndex][child.colIndex] = 1;
			// System.out.println("row: " + child.rowIndex + " col: " + child.colIndex + " level: " + child.level + " visited: " + visitedMatrix[child.rowIndex][child.colIndex]);
			// for(int i =0; i < child.children.size(); i++){
			// 	System.out.println(child.children.get(i).rowIndex + " " + child.children.get(i).colIndex );
			// }
			addChild(child, matrix, jumpMatrix, visitedMatrix, q);

		}

		int path(int[][] jumpMatrix){
			if(posGoal == 0){
				for(int row = 0; row < jumpMatrix.length; row++){
					for(int col = 0; col < jumpMatrix.length; col++){
						if(row == 0 && col == 0){
							continue;
						}
						if(jumpMatrix[row][col] == 0){
							//Show X in the GUI
							negGoal--;
						}
					}
				}
				return negGoal;
			}
			else{

				return posGoal;
			}
		}
	
	}

		void storeEvaluation(Result[] result, int [][] matrix, int evaluation){
			switch(matrix.length){
				case 5:
					result[0].evaluation = evaluation;
				break;
				case 7:
					result[1].evaluation = evaluation;
				break;
				case 9:
					result[2].evaluation = evaluation;
				break;
				case 11:
					result[3].evaluation = evaluation;
				break;
			}
		}

		int getEvaluation(Result[] result, int dimension){
			switch(dimension){
				case 5:
					return result[0].evaluation;
				case 7:
					return result[1].evaluation;
				case 9:
					return result[2].evaluation;
				case 11:
					return result[3].evaluation;
			}
			return 0;
		}

		void storeMatrix(Result[] result, int [][] matrix){
			switch(matrix.length){
				case 5:
					result[0].bestMatrix = matrix;
				break;
				case 7:
					result[1].bestMatrix = matrix;
				break;
				case 9:
					result[2].bestMatrix = matrix;
				break;
				case 11:
					result[3].bestMatrix = matrix;
				break;
			}
		}

		int[][] getMatrix(Result[] result, int dimension){
			switch(dimension){
				case 5:
					return result[0].bestMatrix;
				case 7:
					return result[1].bestMatrix;
				case 9:
					return result[2].bestMatrix;
				case 11:
					return result[3].bestMatrix;
			}
			return null;
		}

		void storeTime(Result[] result, int [][] matrix, long time){
			switch(matrix.length){
				case 5:
					result[0].time = time;
				break;
				case 7:
					result[1].time = time;
				break;
				case 9:
					result[2].time = time;
				break;
				case 11:
					result[3].time = time;
				break;
			}
		}

		long getTime(Result[] result, int [][] matrix){
			switch(matrix.length){
				case 5:
					return result[0].time;
				case 7:
					return result[1].time;
				case 9:
					return result[2].time;
				case 11:
					return result[3].time;
			}
			return 0;
		}

	void fillMatrix(int[][] copy, int [][] original){
		for(int i = 0; i < copy.length; i++){
			for(int j = 0; j < copy.length; j++){
				copy[i][j] = original[i][j];
			}
		}
	}

	//task 2
	int eval(int[][] matrix){

		long startTime = System.currentTimeMillis();

		int n = matrix.length;
		int[][] visitedMatrix = new int[n][n];
		int[][] jumpMatrix = new int[n][n];
		// System.out.println("input");
		// printMatrix(matrix);
		// System.out.println();
		// printMatrix(visitedMatrix);
		// System.out.println();
		List<Node> children = new ArrayList<>();
		int jump = matrix[0][0];
		Node child1 = new Node(0, jump, matrix[0][jump], 1, children);
		Node child2 = new Node(jump, 0, matrix[jump][0], 1, children);
		children.add(child1);
		children.add(child2);
		Node root = new Node(0, 0, jump, 0, children);
		visitedMatrix[0][0] = 1;
		visitedMatrix[0][jump] = 1;
		visitedMatrix[jump][0] = 1;
		jumpMatrix[0][jump] = 1;
		jumpMatrix[jump][0] = 1;
		Tree tree = new Tree(root);
		Queue<Node> q = new LinkedList<>();
		tree.addChild(root, matrix, jumpMatrix, visitedMatrix, q);
		// System.out.println("visited");
		// printMatrix(visitedMatrix);
		// System.out.println();
		// System.out.println("jump");
		// printMatrix(jumpMatrix);
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("start: " + startTime + "end: " + endTime + "totalTime: " + totalTime);
		storeEvaluation(eval_array, matrix, tree.path(jumpMatrix));
		storeMatrix(eval_array, matrix);
		storeTime(eval_array, matrix, totalTime);

		return tree.path(jumpMatrix);

	}
	
	//task 3
	int hillClimbing(int dimension, int iteration){
		int [][] matrix = getMatrix(eval_array, dimension);
		int before = getEvaluation(eval_array, dimension);
		int after = 0;
		while(iteration > 0){
			long startTime = System.currentTimeMillis();
			int n = matrix.length;
			int [][] temp = new int[n][n];
			fillMatrix(temp, matrix);
			int[][] visitedMatrix = new int[n][n];
			int[][] jumpMatrix = new int[n][n];
		
			Random r = new Random();
			int randRow = r.nextInt(matrix.length - 1) + 1;
			int randCol = r.nextInt(matrix.length - 1) + 1;
			//prevents the goal from being replaced
			while(randRow == matrix.length - 1 && randCol == matrix.length - 1){
				randRow = r.nextInt(matrix.length - 1) + 1;
				randCol = r.nextInt(matrix.length - 1) + 1;
				System.out.println("randRow: " + randRow + " randCol: " + randCol);
			}

			//max number of jump for the index 
			int limit = (int) Math.max(Math.max(matrix.length - 1 - randRow, randRow - matrix.length - 1) , 
				Math.max(matrix.length - 1 - randCol, randCol - matrix.length - 1));
			//generates a random number from 1 to n - 1 
			int random = r.nextInt((limit - 1) + 1) + 1;
			//fills in the indices 

			// System.out.println(temp[randRow][randCol]);
			// System.out.println(random);
			while(random == temp[randRow][randCol]){
				random = r.nextInt((limit - 1) + 1) + 1;
				if(random == 1){
					random = r.nextInt((limit - 1) + 1) + 2;
				}
				//System.out.println(random);
				System.out.println("randRow: " + randRow + "randCol: " + randCol + "random: " + random);
			}
			temp[randRow][randCol] = random;
			//System.out.println("randRow: " + randRow + "randCol: " + randCol + "random: " + random);

			before = new Puzzle().eval(matrix);
			after = new Puzzle().eval(temp);
			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			if(before > after){
				storeEvaluation(hillClimbing_array, matrix, before);
				storeMatrix(hillClimbing_array, matrix);
				storeTime(hillClimbing_array, matrix, totalTime);
			}else{
				storeEvaluation(hillClimbing_array, temp, before);
				storeMatrix(hillClimbing_array, temp);
				storeTime(hillClimbing_array, temp, totalTime);
			}
			matrix = getMatrix(hillClimbing_array, dimension);
			System.out.println(iteration);
			iteration--;
		}
		return Math.max(before, after);

	}
	
	/*//task 4
	int randomRestarts(int dimension, int iteration, int startHill){
		int hill = 0;
		int randomResult = 0;
		int[][] matrix = getMatrix(dimension);
		int[][] randomMatrix = new int [dimension][dimension];
		matrixGenerator(randomMatrix);
		while(startHill > 0){
			hill = new Puzzle().hillClimbing(matrix, iteration);
			matrix = getMatrix(dimension);
			while(iteration > 0){
				randomResult = new Puzzle().eval(randomMatrix);
				if(hill > randomResult){
					storage(matrix);
				}else{
					storage(randomMatrix);
				}
				iteration--;
			}
			startHill--;
		}
		return Math.max(hill, randomResult); 
	}
	
	//task 5
	void randomWalk(int iteration, int probability){

	}
	/*
	//task 6 
	void simulatedAnnealing(int[][] matrix){

	}
	//task 7 
	void population(int[][] matrix){

	}

*/
	
	public static void matrixGenerator(int[][] matrix){
		
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

		// System.out.println("Enter size of n");
		// Scanner sc = new Scanner(System.in);
		// int n = sc.nextInt();
		int n = 5;
		// int [][] fail = {{3,3,2,4,3},
		// 				{2,2,2,1,1},
		// 				{4,3,1,3,4},
		// 				{2,3,1,1,3},
		// 				{1,1,3,2,0}
		// 				};
		
		fillDummyResult(eval_array);
		fillDummyResult(hillClimbing_array);
		fillDummyResult(randomRestarts_array); 
		fillDummyResult(randomWalk_array);
		fillDummyResult(simulatedAnnealing_array);
		fillDummyResult(population_array);

		int[][] matrix = new int[n][n];
		matrixGenerator(matrix);
		int task2 = new Puzzle().eval(matrix);
		System.out.println("task2: " + task2);
		eval_array[0].print();

		int task3 = new Puzzle().hillClimbing(n, 50);
		System.out.println("task3: " + task3);

		// int task4 = new Puzzle().randomRestarts(n, 1000, 1000);
		// System.out.println("task4: " + task4);

	}
}
