import java.util.Scanner;

class Det{
   public static void main(String[] args){
      Scanner sc = new Scanner(System.in);
      System.out.println("How many rows do you need?");
      int row = sc.nextInt();
      double[][] matrix = new double[row][row];
      System.out.println("Now, please enter "+ row*row + " entries:");
      for (int i=0; i<row; i++){
         for (int j=0; j<row; j++){
            matrix[i][j] = sc.nextDouble();
         }
      }

      for (int l=0; l<row-1; l++){
         for (int u=l+1; u<row; u++){
            if (equal(matrix,u,l)){ 
               Boom(matrix,u,l); 
            } 
         }                    
      }

      double det = 1;
      for (int k=0; k<row; k++){
         det *= matrix[k][k];
      }

      System.out.println("The determinant for this matrix is: "+det);
   }


   static int CountZero(double[][] P, int i){
      int count = 0;
      for (int n=0; n<P[i].length; n++){
         if (P[i][n] == 0){
            count++;
         }else if (P[i][n] != 0) break;
      }
      return count;
   }

   static boolean equal(double[][] matrix, int i, int j){
      boolean Hey = false;
      Hey = CountZero(matrix, i) == CountZero(matrix, j);
      return Hey;
   }

   static void Boom(double[][] Q, int current, int n){
      double pivot=0;
      for (int i=0; i<Q[current].length; i++){
         if (Q[current][i] != 0 && i<Q[current].length-1){
            pivot = Q[current][i];
            double ratio = pivot / Q[n][i];
            for (int o=i; o<Q[current].length; o++){
               Q[current][o]= Q[current][o]- Q[n][o]*ratio;
            }
            break;
         }
      }
   }
}
