/*
Title: Recursive Vs Iterative
Purpose: Compare runtime of recursive and iterative implementations of the power function
Author: Sam McFarland
Date: 1/16/2023
*/
import java.io.*;

public class RecursiveVsIterative {
   public static void main(String[] args) {
      FileWriter output;
      try {
         output = new FileWriter("output.txt");
         output.write("n,time,type\n");
      } catch (IOException e) {
         System.err.println("IOException: " + e); 
         return;
      }
      double base = 3.14159265359;
      long previous;
      long current;
      long gap1;
      long gap2 = 0;
      double result;
      String line;
      for (int i = 1; i <= Integer.MAX_VALUE; i++) {
         previous = System.nanoTime();
         iterativePower(base, i);
         current = System.nanoTime();
         gap1 = current - previous;
         previous = System.nanoTime();
         try {
            recursivePower(base, i);
         } catch (StackOverflowError e) {
            break;
         }
         current = System.nanoTime();
         gap2 = current - previous;
         line = i + "," + gap1 + ",iterative\n" + i + "," + gap2 + "," + "recursive\n";
         try {
            output.write(line, 0, line.length());
         } catch (IOException e) {
            System.err.println("IOException: " + e); 
            return;
         }
      }
      try {
         output.flush();
      } catch (IOException e) {
         System.err.println("IOException: " + e);
      }
   }
   
   public static double iterativePower(double base, int exponent) {
      double retVal = 1.0;
      if (exponent < 0) {
         return 1.0 / iterativePower(base, -exponent);
      } else {
         for (int i = 0; i < exponent; i++)
         retVal *= base;
      }
      return retVal;
   }
   
   public static double recursivePower(double base, int exponent) {
      if (exponent < 0) {
         return 1.0 / recursivePower(base, -exponent);
      } else if (exponent == 0) {
         return 1.0;
      } else {
         return base * recursivePower(base, exponent - 1);
      }
   }
}