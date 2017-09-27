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

public class Matrix implements ActionListener{
     private static int dim;
     private static int myMatrix [][];
     private static int puzMatrix [][];
     private static int tempMatrix [][]; 
     private static JTextField inputField [][];
     private static int result;
     private static JButton task1, task2, task3, task4, task5, task6, task7;
     private static JPanel choosePanel [] = new JPanel[8];
     private static int lastdim;
      
     
     Matrix ()
     {
         dim = 0;
         myMatrix = new int [0][0];
         ChooseOperation();
     }
     
     
     //prompting for matrix's dimensions
     private static void getDimension() 
    {
      JTextField field = new JTextField(5); 
      
      //design input line
      JPanel choosePanel [] = new JPanel [2];
       choosePanel [0] = new JPanel();
       choosePanel [1] = new JPanel();
      choosePanel[0].add(new JLabel("Enter Dimensions") );
      choosePanel[1].add(field);
      //choosePanel[1].add(Box.createHorizontalStrut(15)); // a spacer
        
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
            setElements(myMatrix); //filling the new matrix
        
       }
       }
       else if(result == 1)
       {
           dim = lastdim;
       }
     }
    
     //setting a matrix's elementis
    private static void setElements(int matrix [][])
    {
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
    }//end get Inputs
    
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
            getDimension();
            showBest(myMatrix, 1, 1);
        }
         
        else  if(e.getSource() == task4)
        {
            getDimension();
            showBest(myMatrix, 1, 1);
        }
        else if(e.getSource() == task5)
        {
            getDimension();
            showBest(myMatrix, 1, 1);
        }
        
        else if(e.getSource() ==  task6)
        {
            getDimension();
            showBest(myMatrix, 1, 1);
        }
         else if(e.getSource() == task7)
        {
            getDimension();
            showPop(myMatrix, 1, 1);
        }
        
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
               
           }//end col loop
           
       }//end row loop
       
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
              }else{
                choosePanel[line].add(new JLabel(String.format("%d", matrix[k][j])));
               
                 if(j < matrix[0].length -1){
                    choosePanel[line].add(Box.createHorizontalStrut(15)); // a spacer
                 }
              }
               
           }//end col loop
           line++;
       }//

       choosePanel[line] = new JPanel ();
       choosePanel[line].add( new JLabel ("Value: " + String.format("%d", matrix[matrix.length - 1][matrix.length - 1])));
       
   
    JOptionPane.showMessageDialog(null, choosePanel, null, 
            JOptionPane.PLAIN_MESSAGE, null);
    
    }


   private static void showBest(int [][] matrix, int val, long time){
       
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
              }else{
                choosePanel[line].add(new JLabel(String.format("%d", matrix[k][j])));
               
                 if(j < matrix[0].length -1){
                    choosePanel[line].add(Box.createHorizontalStrut(15)); // a spacer
                 }
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
       choosePanel[line].add( new JLabel ("Time: " + String.format("%d", time)));
       
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
              }else{
                choosePanel[line].add(new JLabel(String.format("%d", matrix[k][j])));
               
                 if(j < matrix[0].length -1){
                    choosePanel[line].add(Box.createHorizontalStrut(15)); // a spacer
                 }
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

    private static boolean isInt (String str)
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
     public static void main (String [] args)
    {
            try{
              puzMatrix = matrixfromtext(args[0]);
              } catch (Exception error){
                error.printStackTrace();
            }
        Matrix m1 = new Matrix ();
        
    }
}//end class

