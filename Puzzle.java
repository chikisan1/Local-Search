import java.util.Random;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
import java.util.*;
public class Puzzle{

	int [] eval_array = new int [4];
	int [] hillClimbing_array = new int [4];
	int [] randomRestarts_array = new int[4];
	int [] randomWalk_array = new int[4];
	int [] simulatedAnnealing_array = new int[4];
	int [] population_array = new int[4];

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
	//task 2
	int eval(int[][] matrix){
		int n = matrix.length;
		int[][] visitedMatrix = new int[n][n];
		int[][] jumpMatrix = new int[n][n];
		System.out.println("input");
		printMatrix(matrix);
		System.out.println();
		//printMatrix(visitedMatrix);
		System.out.println();
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
		System.out.println("visited");
		printMatrix(visitedMatrix);
		System.out.println();
		System.out.println("jump");
		printMatrix(jumpMatrix);
		return tree.path(jumpMatrix);

	}
	
	//task 3
	int hillClimbing(int[][] matrix){
		int [][] temp = matrix;
		int n = matrix.length;
		int[][] visitedMatrix = new int[n][n];
		int[][] jumpMatrix = new int[n][n];

		Random r = new Random();
		int randRow = r.nextInt(matrix.length - 1) + 1;
		int randCol = r.nextInt(matrix.length - 1) + 1;
		while(randRow == matrix.length - 1 && randCol == matrix.length - 1){
			randRow = r.nextInt(matrix.length - 1) + 1;
			randCol = r.nextInt(matrix.length - 1) + 1;
		}

		//max number of jump for the index 
		int limit = (int) Math.max(Math.max(matrix.length - 1 - randRow, randRow - matrix.length - 1) , 
			Math.max(matrix.length - 1 - randCol, randCol - matrix.length - 1));
		//generates a random number from 1 to n - 1 
		int random = r.nextInt((limit - 1) + 1) + 1;
		//fills in the indices 
		temp[randRow][randCol] = random;

		//int before = new Puzzle().eval(matrix);
		System.out.println("randRow: " + randRow + "randCol: " + randCol + "random: " + random);
		int after = new Puzzle().eval(temp);
		//return Math.max(before, after);
		return after;


	}
	/*
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
		int n = 7;
		// int [][] fail = {{3,3,2,4,3},
		// 				{2,2,2,1,1},
		// 				{4,3,1,3,4},
		// 				{2,3,1,1,3},
		// 				{1,1,3,2,0}
		// 				};
		int[][] matrix = new int[n][n];
		matrixGenerator(matrix);
		int task2 = new Puzzle().eval(matrix);
		System.out.println("task2: " + task2);

		int task3 = new Puzzle().hillClimbing(matrix);
		System.out.println("task3: " + task3);

	}
}
