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
		int visited;
		int level;
		List<Node> children = new ArrayList<>();
		
		Node(int rowIndex, int colIndex, int jump, int visited, List<Node> children){
			this.rowIndex = rowIndex;
			this.colIndex = colIndex;
			this.jump = jump; 
			this.visited = visited;
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

		void addChild(Node parent, Node child){

			int rowDistance = (int) Math.abs(parent.rowIndex - child.rowIndex);
			int colDistance = (int) Math.abs(parent.colIndex - child.colIndex);

			if(rowDistance == parent.jump || colDistance == parent.jump){
				parent.children.add(child);
				child.level = parent.level + 1;
			}
		}

		void fillMatrix(Node cur, int [][] matrix){
			if(cur != null){
				int count = cur.children.size();
				for (int i = 0; i < count; i++) {
					Node child = cur.children.get(i);
					matrix[child.rowIndex][child.colIndex] = child.level;
					System.out.println("row: " + cur.rowIndex + " col: " + cur.colIndex + " jump: " 
						+ cur.jump + " level: " + cur.level);
					if(child.jump == 0){
						posGoal = child.level;
					}
					fillMatrix(child, matrix);
				}
			}
			for(int row = 0; row < matrix.length; row++){
				for(int col = 0; col < matrix.length; col++){
					if(row == matrix.length - 1 && col == matrix.length - 1){
						break;
					}
					if(matrix[row][col] == 0){
						//Show X in the GUI
						negGoal--;
					}
				}
			}
		}
		
		int path(){
			if(posGoal == 0){
				return negGoal;
			}
			else{
				return posGoal;
			}
		}

	}
	
	//task 2
	int eval(int[][] matrix, int[][] jumpMatrix){
		Queue<Node> q = new LinkedList<>();
		for(int row = 0; row < matrix.length; row++){
			for(int col = 0; col < matrix.length; col++){
				List<Node> children = new ArrayList<>();
				Node cell = new Node(row, col, matrix[row][col], 0, children);
				q.add(cell);
			}
		}
		Tree tree = new Tree(q.remove());
		Node parent = tree.root;
		int escape = 0;
		while(q != null && escape < 20){
			Node child = q.peek();
			tree.addChild(parent, child);
			if(child.visited == 0){
				q.add(q.remove()); 
				escape++;
			}
			else{
				q.remove();
				parent = child;
			}
		}
		parent = tree.root;
		tree.fillMatrix(parent, jumpMatrix);
		return tree.path();
	}
	/*
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

		System.out.println("Enter size of n");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		int[][] test = new int[n][n];
		int[][] visited = new int[n][n];
		int[][] jumpMatrix = new int[n][n];
		matrixGenerator(test);
		printMatrix(test);
		System.out.println();
		printMatrix(visited);
		int result = new Puzzle().eval(test, jumpMatrix);
		printMatrix(jumpMatrix);
		System.out.println(result);

	}
}