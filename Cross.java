import java.util.Scanner;

class Cross{
   public static void main(String[] args){
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter the first 3-dimensional vector: ");
      double[] v1 = new double[3];
      for (int i=0; i<3; i++){
         v1[i] = sc.nextDouble();
      }
      System.out.println("Enter the second 3-dimensional vector: ");
      double[] v2 = new double[3];
      for (int j=0; j<3; j++){
         v2[j] = sc.nextDouble();
      }
      System.out.println();
      double[] C = new double[3];
      C[0] = v1[1] * v2[2] - v1[2] * v2[1];
      C[1] = v1[2] * v2[0] - v1[0] * v2[2];
      C[2] = v1[0] * v2[1] - v1[1] * v2[0];

      System.out.print("The cross product of ");
      printArray(v1);
      System.out.print(" and ");
      printArray(v2);
      System.out.println(" is:");
      printArray(C);
      System.out.println();
   }

   static void printArray(double[] A){
          System.out.print("(");
      for (int i=0; i<A.length; i++){
         System.out.print(A[i]);
         if (i < A.length-1) System.out.print(", ");
      }
      System.out.print(")");
   }

}
