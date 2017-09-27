import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.*;
import java.io.File;
import java.lang.String;
import java.lang.Integer;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Puzzle implements ActionListener {
//GUI 
	private static int dim;
	private static int iter;
	private static int hillwalk;
	private static double pro;
     private static int myMatrix [][];
     private static int puzMatrix [][];
     private static int tempMatrix [][]; 
     private static JTextField inputField [][];
     private static int result;
     private static JButton task1, task2, task3, task4, task5, task6, task7;
     private static JPanel choosePanel [] = new JPanel[8];
     private static int lastdim;
     private static int lastit;
     private static int lasthill;
     private static double lastpro;
      
     
     Puzzle (){
         dim = 0;
         myMatrix = new int [0][0];
         ChooseOperation();
     }
     
     
     //prompting for matrix's dimensions
     private static void getDimension(){
      JTextField field = new JTextField(5); 
      
      //design input line
	JPanel choosePanel [] = new JPanel [2];
	choosePanel [0] = new JPanel();
	choosePanel [1] = new JPanel();
	choosePanel[0].add(new JLabel("Enter Dimensions") );
	choosePanel[1].add(field);
	
        
      result = JOptionPane.showConfirmDialog(null, choosePanel, 
               null,JOptionPane.OK_CANCEL_OPTION, 
               JOptionPane.PLAIN_MESSAGE);
        
      //save last dimensions
      lastdim = dim;
      
      //ok option
       if(result == 0)
       {
         
         if(field.getText().equals(""))
             dim = 0;
         else
         {
             if(isInt(field.getText()))
             {
                 dim = Integer.parseInt(field.getText());
             }
             else
             {
                 JOptionPane.showMessageDialog(null, "Wrong Dimensions");
                 dim = lastdim;
                 return;
             }
          
         }
       if(dim < 3 || dim == 4 || dim == 6 || dim == 8 || dim == 10)
       {
           JOptionPane.showConfirmDialog(null, "You entered wrong dimensions", 
                   "Error",JOptionPane.PLAIN_MESSAGE);
           dim = lastdim;
          
       }
       else
       {
           tempMatrix = myMatrix;
           myMatrix = new int [dim][dim];
            matrixGenerator(myMatrix); //filling the new matrix
        
       }
       }
       else if(result == 1)
       {
           dim = lastdim;
       }
     }

      private static void getIteration(){
      JTextField lField = new JTextField(5); 
      JTextField rField = new JTextField(5); 
      
      //design input line
      JPanel choosePanel [] = new JPanel [2];
       choosePanel [0] = new JPanel();
       choosePanel [1] = new JPanel();
      choosePanel[0].add(new JLabel("Dimensions"));
      choosePanel[1].add(lField);
      choosePanel[0].add(Box.createHorizontalStrut(15)); // a spacer
      choosePanel[0].add(new JLabel("Iteration:"));
      choosePanel[1].add(Box.createHorizontalStrut(15)); 
      choosePanel[1].add(rField);
        
      result = JOptionPane.showConfirmDialog(null, choosePanel, 
               null,JOptionPane.OK_CANCEL_OPTION, 
               JOptionPane.PLAIN_MESSAGE);
        
      //save last dimensions
      lastdim = dim;
      lastit = iter;
      
      //ok option
       if(result == 0)
       {
         
         if(rField.getText().equals(""))
             iter = 0;
         else
         {
             if(isInt(rField.getText()))
             {
                 iter= Integer.parseInt(rField.getText());
             }
             else
             {
                 JOptionPane.showMessageDialog(null, "Wrong Dimensions");
                 dim = lastdim;
                 iter = lastit;
                 return;
             }
            
             if(isInt(lField.getText()))
             {
                 dim = Integer.parseInt(lField.getText());
             }
             else
             {
                 JOptionPane.showMessageDialog(null, "Wrong Dimensions");
                 dim = lastdim;
                 iter = lastit;
                 return;
             }
          
         }
       if(dim < 3 || dim == 4 || dim == 6 || dim == 8 || dim == 10 || iter < 1)
       {
           JOptionPane.showConfirmDialog(null, "You entered wrong dimensions", 
                   "Error",JOptionPane.PLAIN_MESSAGE);
           dim = lastdim;
            iter = lastit;
          
       }
       else
       {
            tempMatrix = myMatrix;
           myMatrix = new int [dim][dim];
            matrixGenerator(myMatrix); //filling the new matrix
       }
       }
       else if(result == 1)
       {
           dim = lastdim;
           iter = lastit;
       }
     }

     private static void getRestart(){
      JTextField lField = new JTextField(5); 
      JTextField rField = new JTextField(5); 
      JTextField hField = new JTextField(5); 
      JPanel choosePanel [] = new JPanel [2];
      choosePanel [0] = new JPanel();
      choosePanel [1] = new JPanel();
      choosePanel[0].add(new JLabel("Dimensions"));
      choosePanel[1].add(lField);
      choosePanel[0].add(Box.createHorizontalStrut(15)); 
      choosePanel[0].add(new JLabel("Iteration:"));
      choosePanel[1].add(Box.createHorizontalStrut(15)); 
      choosePanel[1].add(rField);
      choosePanel[0].add(Box.createHorizontalStrut(15)); // a spacer
      choosePanel[0].add(new JLabel("Hillclimb:"));
      choosePanel[1].add(hField);
        
      result = JOptionPane.showConfirmDialog(null, choosePanel, 
               null,JOptionPane.OK_CANCEL_OPTION, 
               JOptionPane.PLAIN_MESSAGE);
        
      //save last dimensions
      lastdim = dim;
      lastit = iter;
      lasthill = hillwalk;
      
      //ok option
       if(result == 0)
       {
         
         if(rField.getText().equals(""))
             iter = 0;
         else
         {
             if(isInt(rField.getText()))
             {
                 iter= Integer.parseInt(rField.getText());
             }
             else
             {
                 JOptionPane.showMessageDialog(null, "Wrong Dimensions");
                 dim = lastdim;
                 iter = lastit;
                 hillwalk = lasthill;
                 return;
             }
            
             if(isInt(lField.getText()))
             {
                 dim = Integer.parseInt(lField.getText());
             }
             else
             {
                 JOptionPane.showMessageDialog(null, "Wrong Dimensions");
                 dim = lastdim;
                 iter = lastit;
                 hillwalk = lasthill;
                 return;
             }
             if(isDouble(rField.getText()))
             {
                 pro = Double.parseDouble(hField.getText());
             }
             else
             {
                 JOptionPane.showMessageDialog(null, "Wrong Dimensions");
                 dim = lastdim;
                 iter = lastit;
                 hillwalk = lasthill;
                 return;
             }
          
         }
       if(dim < 3 || dim == 4 || dim == 6 || dim == 8 || dim == 10 || iter < 1)
       {
           JOptionPane.showConfirmDialog(null, "You entered wrong dimensions", 
                   "Error",JOptionPane.PLAIN_MESSAGE);
           dim = lastdim;
                 iter = lastit;
                 hillwalk = lasthill;
          
       }
       else
       {
            tempMatrix = myMatrix;
           myMatrix = new int [dim][dim];
            matrixGenerator(myMatrix); //filling the new matrix
       }
       }
       else if(result == 1)
       {
           dim = lastdim;
	       iter = lastit;
	       hillwalk = lasthill;
       }
     }
    
     private static void getProbability(){
      JTextField lField = new JTextField(5); 
      JTextField rField = new JTextField(5); 
      JTextField hField = new JTextField(5); 
      JPanel choosePanel [] = new JPanel [2];
      choosePanel [0] = new JPanel();
      choosePanel [1] = new JPanel();
      choosePanel[0].add(new JLabel("Dimensions"));
      choosePanel[1].add(lField);
      choosePanel[0].add(Box.createHorizontalStrut(15)); 
      choosePanel[0].add(new JLabel("Iteration:"));
      choosePanel[1].add(Box.createHorizontalStrut(15)); 
      choosePanel[1].add(rField);
      choosePanel[0].add(Box.createHorizontalStrut(15)); // a spacer
      choosePanel[0].add(new JLabel("Probability:"));
      choosePanel[1].add(hField);
        
      result = JOptionPane.showConfirmDialog(null, choosePanel, 
               null,JOptionPane.OK_CANCEL_OPTION, 
               JOptionPane.PLAIN_MESSAGE);
        
      //save last dimensions
      lastdim = dim;
      lastit = iter;
      lastpro = pro;
      
      //ok option
       if(result == 0)
       {
         
         if(rField.getText().equals(""))
             iter = 0;
         else
         {
             if(isInt(rField.getText()))
             {
                 iter= Integer.parseInt(rField.getText());
             }
             else
             {
                 JOptionPane.showMessageDialog(null, "Wrong Dimensions");
                 dim = lastdim;
                 iter = lastit;
                 pro = lastpro;
                 return;
             }
            
             if(isInt(lField.getText()))
             {
                 dim = Integer.parseInt(lField.getText());
             }
             else
             {
                 JOptionPane.showMessageDialog(null, "Wrong Dimensions");
                 dim = lastdim;
                 iter = lastit;
                 pro = lastpro;
                 return;
             }
             if(isDouble(hField.getText()))
             {
                 pro = Double.parseDouble(hField.getText());
             }
             else
             {
                 JOptionPane.showMessageDialog(null, "Wrong Dimensions");
                 dim = lastdim;
                 iter = lastit;
                 pro = lastpro;
                 return;
             }
          
         }
       if(dim < 3 || dim == 4 || dim == 6 || dim == 8 || dim == 10 || iter < 1)
       {
           JOptionPane.showConfirmDialog(null, "You entered wrong dimensions", 
                   "Error",JOptionPane.PLAIN_MESSAGE);
           dim = lastdim;
            iter = lastit;
          
       }
       else
       {
            tempMatrix = myMatrix;
           myMatrix = new int [dim][dim];
            matrixGenerator(myMatrix); //filling the new matrix
       }
       }
       else if(result == 1)
       {
           dim = lastdim;
           iter = lastit;
       }
     }
    
   
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
    //for setting spaced fields as zeros
     private static void checkTextField (JTextField field [][] )
     {
         for(int temp = 0; temp < field.length; temp++)
         {
             for(int temp1 = 0; temp1 < field[0].length; temp1++)
             {
                 if(field[temp][temp1].getText().equals(""))
                 field[temp][temp1].setText("0");
             }
         }
     }//end reset
     
    private void ChooseOperation ()
    {
        int temp;
        
        
        for(temp = 0; temp < choosePanel.length; temp++)
        {
            choosePanel [temp] = new JPanel ();
        }        
        choosePanel[1].add(Box.createHorizontalStrut(15)); // a spacer
        
        choosePanel[6].add(Box.createHorizontalStrut(15)); // a spacer
        
        
        
        task1 = new JButton ("Puzzle Representation");
        task1.setPreferredSize(new Dimension(175,35));
        task1.addActionListener(this);
        choosePanel[2].add(task1);
        
        task2 = new JButton ("Puzzle Evaluation");
        task2.setPreferredSize(new Dimension(175,35));
        task2.addActionListener(this);
        choosePanel[2].add(task2);
        
        task3 = new JButton ("Basic Hill Climbing");
        task3.setPreferredSize(new Dimension(175,35));
        task3.addActionListener(this);
        choosePanel[2].add(task3);
        
        task4 = new JButton ("Random Restarts");
        task4.setPreferredSize(new Dimension(175,35));
        task4.addActionListener(this);
        choosePanel[3].add(task4);
        
        task5 = new JButton ("Random Walk");
        task5.setPreferredSize(new Dimension(175,35));
        task5.addActionListener(this);
        choosePanel[3].add(task5);
        
        task6 = new JButton ("Simulated Annealing");
        task6.setPreferredSize(new Dimension(175,35));
        task6.addActionListener(this);
        choosePanel[3].add(task6);
        
        
        task7 = new JButton ("Population Based");
        task7.setPreferredSize(new Dimension(175,35));
        task7.addActionListener(this);
        choosePanel[4].add(task7);
        
        
        JOptionPane.showConfirmDialog(null, choosePanel, null,
               JOptionPane.CLOSED_OPTION , JOptionPane.PLAIN_MESSAGE);
         
    }
   
   
    @Override
    public  void actionPerformed(ActionEvent e) 
    {
        
        if(e.getSource() == task1)
        {
            getDimension();
            showMatrix(myMatrix);
        }
        if(e.getSource() == task2)
        {
            showMatrices(puzMatrix);
        }
        
        else if(e.getSource() == task3)
        {
            getIteration();
            int [][] dummy = new int[myMatrix.length][myMatrix.length];
            fillMatrix(dummy, myMatrix);
            System.out.println("dummy:");
            System.out.println();
            printMatrix(dummy);
            int task3 = new Puzzle().hillClimbing(dummy, iter, hillClimbing_array);
			System.out.println("task3: ");
			int getIndex = (dummy.length-3)/3;
			System.out.println(dummy.length + " Index: " + getIndex);
			hillClimbing_array[getIndex].print();
			int result3 = new Puzzle().eval2(getMatrix(hillClimbing_array, dummy.length));
            // printMatrix(getMatrix(hillClimbing_array, dim));
            showBest(hillClimbing_array[getIndex]);
        }
         /*
        else if(e.getSource() == task4)
        {
            getRestart();
            int [][] dummy = new int[myMatrix.length][myMatrix.length];
            fillMatrix(dummy, myMatrix);
            System.out.println("dummy:");
            System.out.println();
            printMatrix(dummy);
            int task4 = new Puzzle().randomRestarts(dummy, iter, hillwalk, randomRestarts_array);
			System.out.println("task3: ");
			int getIndex = (dummy.length-3)/3;
			System.out.println(dummy.length + " Index: " + getIndex);
			randomRestarts_array[getIndex].print();
			int result4 = new Puzzle().eval2(getMatrix(randomRestarts_array, dummy.length));
            // printMatrix(getMatrix(hillClimbing_array, dim));
            showBest(randomRestarts_array[getIndex]);
            
        }
        else if(e.getSource() == task5)
        {
            getProbability();
            int [][] dummy = new int[myMatrix.length][myMatrix.length];
            fillMatrix(dummy, myMatrix);
            System.out.println("dummy:");
            System.out.println();
            printMatrix(dummy);
            int task5 = new Puzzle().randomWalk(dummy, iter, pro, randomWalk_array);
			System.out.println("task3: ");
			int getIndex = (dummy.length-3)/3;
			System.out.println(dummy.length + " Index: " + getIndex);
			randomWalk_array[getIndex].print();
			int result5 = new Puzzle().eval2(getMatrix(randomWalk_array, dummy.length));
            showBest(randomWalk_array[getIndex]);
        }
        
        else if(e.getSource() ==  task6)
        {
            getDimension();
            //showBest(myMatrix, 1, 1);
        }
         else if(e.getSource() == task7)
        {
            getDimension();
            showPop(myMatrix, 1, 1);
        }
        */
    }//end action performed

    
    private static void showMatrix(int [][] matrix)
    {
       
       JPanel choosePanel [] = new JPanel [matrix.length+1];
       choosePanel[0] = new JPanel ();
       choosePanel[0].add( new JLabel ("Input") );
   
       for(int i = 1; i <= matrix.length; i++)
       {
           choosePanel[i] = new JPanel();
           
           
           for(int j = 0; j < matrix[0].length; j++)
           {
              if(i == matrix.length && j == matrix.length - 1){
                choosePanel[i].add(new JLabel("G"));
              }else{
                choosePanel[i].add(new JLabel(String.format("%d", matrix[i-1][j])));
               
                 if(j < matrix[0].length -1){
                    choosePanel[i].add(Box.createHorizontalStrut(15)); // a spacer
                 }
              }
               
           }
           
       }
       
    if(dim == 0)
    {
        JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
    }
    else
    {
    
    JOptionPane.showMessageDialog(null, choosePanel, null, 
            JOptionPane.PLAIN_MESSAGE, null);
    }  
    }//end show Matrix

  private static void showMatrices(int [][] matrix){
       JPanel choosePanel [] = new JPanel [(matrix.length+1)*3];
       choosePanel[0] = new JPanel ();
       choosePanel[0].add( new JLabel ("Input") );
        int i = 1;
       for(; i <= matrix.length; i++)
       {
           choosePanel[i] = new JPanel();
           
           
           for(int j = 0; j < matrix[0].length; j++)
           {
              if(i == matrix.length && j == matrix.length - 1){
                choosePanel[i].add(new JLabel("G"));
              }else{
                choosePanel[i].add(new JLabel(String.format("%d", matrix[i-1][j])));
               
                 if(j < matrix[0].length -1){
                    choosePanel[i].add(Box.createHorizontalStrut(15)); // a spacer
                 }
              }
               
           }
           
       }

       int t2 = new Puzzle().eval2(matrix);
       if(t2 < 0){
       	t2 = t2/2;
       }
       int line = i + 1;
       choosePanel[line] = new JPanel ();
       choosePanel[line].add( new JLabel ("Output") );
       line++;
       for(int k = 0; k < matrix.length; k++)
       {
           choosePanel[line] = new JPanel();          
           for(int j = 0; j < matrix.length; j++)
           {
           		
              if(jumpMatrix2[k][j] == 0){
              	if(k == 0 && j == 0){
              		choosePanel[line].add(new JLabel(String.format("%d", 0)));
              	}
                else{
                	choosePanel[line].add(new JLabel(String.format("X")));
                }
              }else{
                choosePanel[line].add(new JLabel(String.format("%d", jumpMatrix2[k][j])));
               
              }
             if(j < matrix[0].length -1){
                    choosePanel[line].add(Box.createHorizontalStrut(15)); // a spacer
             }
             
               
           }//end col loop
           line++;
       }//
       System.out.println(t2);
       choosePanel[line] = new JPanel ();
       choosePanel[line].add( new JLabel ("Value: " + String.format("%d", t2)));
       
   
    
   	
    JOptionPane.showMessageDialog(null, choosePanel, null, 
            JOptionPane.PLAIN_MESSAGE, null);

    }


   private static void showBest(Result mbest){
   		int [][] matrix = mbest.bestMatrix;
   		int val = mbest.evaluation;
   		long time = mbest.time;
       JPanel choosePanel [] = new JPanel [(matrix.length+1)*3];
       choosePanel[0] = new JPanel ();
       choosePanel[0].add( new JLabel ("Input") );
        int i = 1;
       for(; i <= matrix.length; i++)
       {
           choosePanel[i] = new JPanel();
           
           
           for(int j = 0; j < matrix[0].length; j++)
           {
              if(i == matrix.length && j == matrix.length - 1){
                choosePanel[i].add(new JLabel("G"));
              }else{
                choosePanel[i].add(new JLabel(String.format("%d", matrix[i-1][j])));
               
                 
              }

              if(j < matrix[0].length -1){
                    choosePanel[i].add(Box.createHorizontalStrut(15)); // a spacer
                 }
               
           }
           
       }
       int line = i + 1;
       choosePanel[line] = new JPanel ();
       choosePanel[line].add( new JLabel ("Output") );
       line++;
       for(int k = 0; k < matrix.length; k++)
       {
           choosePanel[line] = new JPanel();
           
           for(int j = 0; j < matrix.length; j++)
           {
              if(jumpMatrix2[k][j] == 0){
              	if(k == 0 && j == 0){
              		choosePanel[line].add(new JLabel(String.format("%d", 0)));
              	}
                else{
                	choosePanel[line].add(new JLabel(String.format("X")));
                }
              }else{
                choosePanel[line].add(new JLabel(String.format("%d", jumpMatrix2[k][j])));
               
             
              }
               
               if(j < matrix[0].length -1){
                    choosePanel[line].add(Box.createHorizontalStrut(15)); // a spacer
                 }
           }
           line++;
       }
       choosePanel[line] = new JPanel ();
       choosePanel[line].add( new JLabel ("Output") );
       line++;
       choosePanel[line] = new JPanel ();
       choosePanel[line].add( new JLabel ("Value: " + String.format("%d", val)));
       line++;
       choosePanel[line] = new JPanel ();
       choosePanel[line].add( new JLabel ("Time: " + String.format("%d", time)));
       
   
    JOptionPane.showMessageDialog(null, choosePanel, null, 
            JOptionPane.PLAIN_MESSAGE, null);
 
    }

    private static void showPop(int [][] matrix, int val, int iteration){
       
       JPanel choosePanel [] = new JPanel [(matrix.length+1)*3];
       choosePanel[0] = new JPanel ();
       choosePanel[0].add( new JLabel ("Input") );
        int i = 1;
       for(; i <= matrix.length; i++)
       {
           choosePanel[i] = new JPanel();
           
           
           for(int j = 0; j < matrix[0].length; j++)
           {
              if(i == matrix.length && j == matrix.length - 1){
                choosePanel[i].add(new JLabel("G"));
              }else{
                choosePanel[i].add(new JLabel(String.format("%d", matrix[i-1][j])));
               
                 if(j < matrix[0].length -1){
                    choosePanel[i].add(Box.createHorizontalStrut(15)); // a spacer
                 }
              }
               
           }//end col loop
           
       }//end row loop
       int line = i + 1;
       choosePanel[line] = new JPanel ();
       choosePanel[line].add( new JLabel ("Output") );

       line++;
       for(int k = 0; k < matrix.length; k++)
       {
           choosePanel[line] = new JPanel();
           
           for(int j = 0; j < matrix.length; j++)
           {
              if(matrix[k][j] == 0){
                choosePanel[line].add(new JLabel("X"));
                if(j < matrix[0].length -1){
                    choosePanel[line].add(Box.createHorizontalStrut(15)); // a spacer
                }
              }else{
                choosePanel[line].add(new JLabel(String.format("%d", matrix[k][j])));
  
              }
              if(j < matrix[0].length -1){
                    choosePanel[line].add(Box.createHorizontalStrut(15)); // a spacer
              }
               
           }//end col loop
           line++;
       }//
       choosePanel[line] = new JPanel ();
       choosePanel[line].add( new JLabel ("Output") );
       line++;
       choosePanel[line] = new JPanel ();
       choosePanel[line].add( new JLabel ("Value: " + String.format("%d", val)));
       line++;
       choosePanel[line] = new JPanel ();
       choosePanel[line].add( new JLabel ("# of iteration: " + String.format("%d", iteration)));
       
    if(dim == 0)
    {
        JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
    }
    else
    {
    
    JOptionPane.showMessageDialog(null, choosePanel, null, 
            JOptionPane.PLAIN_MESSAGE, null);
    }  
    }

    
    private static boolean isInt(String str)
   {
       int temp;
       if (str.length() == '0')
           return false;
       
       for(temp = 0; temp < str.length();temp++)
       {
           if(str.charAt(temp) != '+' && str.charAt(temp) != '-'
                   && !Character.isDigit(str.charAt(temp)))
           {
               return false;
           }
       }
       return true;
   }

    private static boolean isDouble (String str)
   {
       int temp;
       if (str.length() == '0')
           return false;
       
       for(temp = 0; temp < str.length();temp++)
       {
           if(str.charAt(temp) != '+' && str.charAt(temp) != '-'
                   && str.charAt(temp) != '.'
                   && !Character.isDigit(str.charAt(temp))
                   )
           {
               return false;
           }
       }
       return true;
   }

//####################################################################################################################################
//Backend   
   	static int [][] jumpMatrix2;
	static Result [] hillClimbing_array = new Result[4];
	static Result [] randomRestarts_array = new Result[4];
	static Result [] randomWalk_array = new Result[4];
	static Result [] simulatedAnnealing_array = new Result[4];
	static Result [] population_array = new Result[4];

	ArrayList<Integer> hill_plot = new ArrayList<>();
	ArrayList<Integer> restart_plot = new ArrayList<>();
	ArrayList<Integer> walk_plot = new ArrayList<>();
	ArrayList<Integer> anneal_plot = new ArrayList<>();
	ArrayList<Integer> pop_plot = new ArrayList<>();


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
		ArrayList<Node> children = new ArrayList<>();
		
		
		Node(int rowIndex, int colIndex, int jump, int level, ArrayList<Node> children){
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

		static int[][] getMatrix(Result[] result, int dimension){
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

		long getTime(Result[] result, int dimension){
			switch(dimension){
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
		int eval2(int[][] matrix){

		long startTime = System.currentTimeMillis();

		int n = matrix.length;
		int[][] visitedMatrix = new int[n][n];
		int[][] jumpMatrix = new int[n][n];
		// System.out.println("input");
		//printMatrix(matrix);
		// System.out.println();
		// printMatrix(visitedMatrix);
		// System.out.println();
		ArrayList<Node> children = new ArrayList<>();
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
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		
		System.out.println("Jump ");
		printMatrix(jumpMatrix);
		System.out.println("Min Distance to Goal: " + tree.path(jumpMatrix));
		//System.out.println("start: " + startTime + "end: " + endTime + "totalTime: " + totalTime);
		jumpMatrix2 = jumpMatrix;
		return tree.path(jumpMatrix);
	}
	int eval(int[][] matrix){

		long startTime = System.currentTimeMillis();

		int n = matrix.length;
		int[][] visitedMatrix = new int[n][n];
		int[][] jumpMatrix = new int[n][n];
		// System.out.println("input");
		//printMatrix(matrix);
		// System.out.println();
		// printMatrix(visitedMatrix);
		// System.out.println();
		ArrayList<Node> children = new ArrayList<>();
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
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		jumpMatrix2 = jumpMatrix;
		return tree.path(jumpMatrix);

	}
	
	//task 3
	int hillClimbing(int [][] matrix, int iteration, Result [] array){
		System.out.print(iteration);
		long startTime = System.currentTimeMillis();
		int n = matrix.length;
		printMatrix(matrix);
		int [][] dummy = new int[n][n];
		printMatrix(dummy);
		fillMatrix(dummy, matrix);
		int before = new Puzzle().eval(dummy);
		System.out.print(949);
		int after = 0;
		int listCount = 0;
		int xplot = iteration + 1;
		while(iteration > 0){
			//System.out.print(952);
			int [][] temp = new int[n][n];
			fillMatrix(temp, dummy);
			int[][] visitedMatrix = new int[n][n];
			int[][] jumpMatrix = new int[n][n];
			Random r = new Random();
			int randRow = r.nextInt(matrix.length - 1) + 1;
			int randCol = r.nextInt(matrix.length - 1) + 1;
			//prevents the goal from being replaced
			while(randRow == matrix.length - 1 && randCol == matrix.length - 1){
				randRow = r.nextInt(matrix.length - 1) + 1;
				randCol = r.nextInt(matrix.length - 1) + 1;
				//System.out.println("randRow: " + randRow + " randCol: " + randCol);
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
				//System.out.println("randRow: " + randRow + "randCol: " + randCol + "random: " + random);
			}
			temp[randRow][randCol] = random;
			//System.out.println("randRow: " + randRow + "randCol: " + randCol + "random: " + random);

			before = new Puzzle().eval(dummy);
			after = new Puzzle().eval(temp);

			System.out.println("999 bfore "+ before);
			System.out.println("999 after "+ after);
			if(before > after){
				storeEvaluation(array, dummy, before);
				storeMatrix(array, dummy);
			}else{
				storeEvaluation(array, temp, before);
				storeMatrix(array, temp);
			}
			dummy = getMatrix(array, matrix.length);
			hill_plot.add(Math.max(before, after));
			int printX = (int) Math.abs(iteration - xplot);
			//System.out.print("(" + printX + "," + hill_plot.get(listCount) + ")");
			listCount++;
			iteration--;
		}
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		storeTime(array, getMatrix(array, matrix.length), totalTime);
		System.out.print("endwork");
		return Math.max(before, after);

	}
	
	//task 4
	int randomRestarts(int[][] matrix, int iteration, int startHill, Result [] array){
		long startTime = System.currentTimeMillis(); 
		int n = matrix.length;
		int [][] temp = new int[n][n];
		int [][] dummy = new int[n][n];
		fillMatrix(dummy, matrix);
		fillMatrix(temp, matrix);
		int best = new Puzzle().eval(temp);
		while(startHill > 0){
			int hill = new Puzzle().hillClimbing(dummy, iteration, array);
			if(hill >= best){
				fillMatrix(temp, getMatrix(array, n));
				best = hill;
			}
			matrixGenerator(dummy);
			startHill--;
		}

		storeMatrix(array, temp);
		storeEvaluation(array, temp, best);
		long endTime  = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		storeTime(array, getMatrix(array, n), totalTime);
		return best; 
	}
	
	//task 5
	int randomWalk(int [][] matrix, int iteration, double probability, Result [] array){
		long startTime = System.currentTimeMillis();
		int walk = 0;
		if(probability == 0){
			walk = new Puzzle().hillClimbing(matrix, iteration, array);
			long endTime = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			storeTime(array, getMatrix(array, matrix.length), totalTime);
			return walk;
		}

		int n = matrix.length;
		int [][] dummy = new int[n][n];
		fillMatrix(dummy, matrix);
		int before = new Puzzle().eval(matrix);
		int after = 0;
		while(iteration > 0){
			int [][] temp = new int[n][n];
			fillMatrix(temp, dummy);
			int[][] visitedMatrix = new int[n][n];
			int[][] jumpMatrix = new int[n][n];
			Random r = new Random();
			int randRow = r.nextInt(matrix.length - 1) + 1;
			int randCol = r.nextInt(matrix.length - 1) + 1;
			//prevents the goal from being replaced
			while(randRow == matrix.length - 1 && randCol == matrix.length - 1){
				randRow = r.nextInt(matrix.length - 1) + 1;
				randCol = r.nextInt(matrix.length - 1) + 1;
				//System.out.println("randRow: " + randRow + " randCol: " + randCol);
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
				//System.out.println("randRow: " + randRow + "randCol: " + randCol + "random: " + random);
			}
			temp[randRow][randCol] = random;
			//System.out.println("randRow: " + randRow + "randCol: " + randCol + "random: " + random);

			before = new Puzzle().eval(dummy);
			after = new Puzzle().eval(temp);
			if(before > after){
				if(r.nextDouble() > probability){
					storeEvaluation(array, dummy, before);
					storeMatrix(array, dummy);
					walk = before;
				}else{
					storeEvaluation(array, temp, before);
					storeMatrix(array, temp);
					walk = after;
					//System.out.println("random walk");
				}
			}
			else{
				storeEvaluation(array, temp, before);
				storeMatrix(array, temp);
				walk = after;
			}
			dummy = getMatrix(array, matrix.length);
			//System.out.println(iteration);
			iteration--;
		}
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		storeTime(array, getMatrix(array, matrix.length), totalTime);
		return walk;
	}
	
	//task 6 
	int simulatedAnnealing(int[][] matrix, int iteration, double tempurature, double decay, Result [] array){
		long startTime = System.currentTimeMillis();
		int anneal = 0;
		int n = matrix.length;
		int [][] dummy = new int[n][n];
		fillMatrix(dummy, matrix);
		int before = new Puzzle().eval(matrix);
		int after = 0;
		while(iteration > 0){
			int [][] temp = new int[n][n];
			fillMatrix(temp, dummy);
			int[][] visitedMatrix = new int[n][n];
			int[][] jumpMatrix = new int[n][n];
			Random r = new Random();
			int randRow = r.nextInt(matrix.length - 1) + 1;
			int randCol = r.nextInt(matrix.length - 1) + 1;
			//prevents the goal from being replaced
			while(randRow == matrix.length - 1 && randCol == matrix.length - 1){
				randRow = r.nextInt(matrix.length - 1) + 1;
				randCol = r.nextInt(matrix.length - 1) + 1;
				//System.out.println("randRow: " + randRow + " randCol: " + randCol);
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
				//System.out.println("randRow: " + randRow + "randCol: " + randCol + "random: " + random);
			}
			temp[randRow][randCol] = random;
			//System.out.println("randRow: " + randRow + "randCol: " + randCol + "random: " + random);

			before = new Puzzle().eval(dummy);
			after = new Puzzle().eval(temp);
			if(before > after){
				double probability = Math.exp((after - before)/tempurature);
				if(r.nextDouble() > probability){
					storeEvaluation(array, dummy, before);
					storeMatrix(array, dummy);
					anneal = before;
				}
				else{
					storeEvaluation(array, temp, before);
					storeMatrix(array, temp);
					anneal = after;
				}		
			}else{
				storeEvaluation(array, temp, before);
				storeMatrix(array, temp);
				anneal = after;
			}
			dummy = getMatrix(array, matrix.length);
			//System.out.println(iteration);
			tempurature = tempurature*decay;
			iteration--;
		}
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		storeTime(array, getMatrix(array, matrix.length), totalTime);
		return anneal;
	}
	//task 7 
	int population(int [][] matrix, ArrayList<int [][]> randomMatrices, Result [] array){
		long startTime = System.currentTimeMillis();
		int n = matrix.length;
		int [][] dummy = new int [n][n];
		fillMatrix(dummy, matrix);
		int [][] temp = new int [n][n];
		int count = 0;
		while(false||(System.currentTimeMillis()-startTime)< getTime(array, n)){
			temp = randomMatrices.get(count);
		    Random r = new Random();
			int randRow = r.nextInt(matrix.length - 1) + 1;
			int randCol = r.nextInt(matrix.length - 1) + 1;
			//prevents the goal from being replaced
			while(randRow == matrix.length - 1 && randCol == matrix.length - 1){
				randRow = r.nextInt(matrix.length - 1) + 1;
				randCol = r.nextInt(matrix.length - 1) + 1;
			}
			int limit = (int) Math.max(Math.max(n - 1 - randRow, randRow - n - 1) , 
					Math.max(n - 1 - randCol, randCol - n - 1));
			//swap
			if(dummy[randRow][randCol] < limit && temp[randRow][randRow] < limit){
				int swap = dummy[randRow][randCol];
				dummy[randRow][randCol] = temp[randRow][randRow];
				temp[randRow][randRow] = swap;
			}
			// System.out.println(count);
			// System.out.println(System.currentTimeMillis()-startTime);
			count++;
		}
		printMatrix(dummy);
		int result = new Puzzle().eval2(getMatrix(array, matrix.length));
		return count;
	}


	
	
public static int[][] matrixfromtext(String filename) throws Exception{int[][] matrix2 = null;
	BufferedReader temp = new BufferedReader(new FileReader(filename));
		String line;
		int row = 0;
		int n = 0;
		line = temp.readLine();
		n = Integer.parseInt(line);
		System.out.print(n + " x " + n + " input matrix \n");
		if(n!=5){
			if(n!=7){
				if(n!=9){
					if(n!=11){
						System.out.print(n + " is an invalid dimension");
					}
				}
			}
		}
		while((line = temp.readLine())!= null){
			String[] vals = line.trim().split("\\s+");
				if(matrix2 == null){
				
					matrix2 = new int[n][n];
					}
				for (int col = 0; col < n; col++){
					matrix2[row][col] = Integer.parseInt(vals[col]);
					}
				row++;
				}
			return matrix2;
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
		//int n = 5;
		// int [][] fail = {{3,3,2,4,3},
		// 				{2,2,2,1,1},
		// 				{4,3,1,3,4},
		// 				{2,3,1,1,3},
		// 				{1,1,3,2,0}
		// 				};
	try{
		puzMatrix = matrixfromtext(args[0]);
		} catch (Exception e){
			e.printStackTrace();
			}
			printMatrix(puzMatrix);
			
		fillDummyResult(hillClimbing_array);
		fillDummyResult(randomRestarts_array); 
		fillDummyResult(randomWalk_array);
		fillDummyResult(simulatedAnnealing_array);
		fillDummyResult(population_array);

		// long startTime = System.currentTimeMillis();
		// int[][] matrix2 = new int[11][11];
		// matrixGenerator(matrix2);
		// printMatrix(matrix2);
		// long endTime = System.currentTimeMillis();
		// System.out.println(endTime - startTime);
		Puzzle p1 = new Puzzle();
		//System.out.println("task2: ");
		// //eval_array[0].print();

		// int task3 = new Puzzle().hillClimbing(matrix, 100, hillClimbing_array);
		// System.out.println("task3: ");
		// hillClimbing_array[3].print();
		// int result3 = new Puzzle().eval2(getMatrix(hillClimbing_array, matrix.length));

		// System.out.println("task4: ");
		// int task4 = new Puzzle().randomRestarts(matrix, 10, 10, randomRestarts_array);
		// randomRestarts_array[3].print();
		// int result4 = new Puzzle().eval2(getMatrix(randomRestarts_array, matrix.length));
		
		// System.out.println("task5: ");
		// int task5 = new Puzzle().randomWalk(matrix, 100, .70, randomWalk_array);
		// randomWalk_array[3].print();
		// int result5 = new Puzzle().eval2(getMatrix(randomWalk_array, matrix.length));

		// System.out.println("task6: ");
		// int task6 = new Puzzle().simulatedAnnealing(matrix, 100, 100.00, .85, simulatedAnnealing_array);
		// simulatedAnnealing_array[3].print();
		// int result6 = new Puzzle().eval2(getMatrix(simulatedAnnealing_array, matrix.length));

		// System.out.println("task7: ");
		// ArrayList<int [][]> randomMatrice = new ArrayList<int [][]>(0);
		// for(int i = 0; i < 1000000; i++){
		// 	int[][] newMatrix = new int[matrix.length][matrix.length];
		// 	matrixGenerator(newMatrix);
		// 	randomMatrice.add(newMatrix);
		// }


		// int task7_hill = new Puzzle().population(matrix, randomMatrice, hillClimbing_array);
		// int task7_restart = new Puzzle().population(matrix, randomMatrice, randomRestarts_array);
		// int task7_walk = new Puzzle().population(matrix, randomMatrice, randomWalk_array);
		// int task7_anneal = new Puzzle().population(matrix, randomMatrice, simulatedAnnealing_array);
		// System.out.println("Iteration comparing hillClimbing: " + task7_hill);
		// System.out.println("Iteration comparing randomRestarts: " + task7_restart);
		// System.out.println("Iteration comparing randomWalk: " + task7_walk);
		// System.out.println("Iteration comparing simulatedAnnealing: " + task7_anneal);

	}
}
