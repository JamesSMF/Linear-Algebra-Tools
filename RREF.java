import java.util.Scanner;

class RREF{
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
      }           // End of inputing a matrix.

      for (int l=0; l<row-1; l++){
         for (int u=l+1; u<row; u++){
            if (Inverse.equal(matrix,u,l)){ // If row u and l have the same number of zeros.
               Inverse.Boom(matrix,u,l); // Perform elimination on row u and l.
            }                    // Otherwise just ignore this row and continue checking
         }                       // the next row.
      }

      double det = 1;
      for (int k=0; k<row; k++){
         det *= matrix[k][k];
      }

      if (det==0){
         System.out.println("This is a singular matrix.");
         System.exit(0);
      }

      for (int q=row-1; q>0; q--){
         for (int t=q-1; t>=0; t--){
            if (InverseEqual(matrix,t,q)){
               InverseBoom(matrix,t,q);
            }
         }
      }

      double pivot = 0;

      for (int w=0; w<row; w++){
         for (int v=0; v<row; v++){
            if (matrix[w][v]!=0){
               pivot = matrix[w][v];
               for (int b=0; b<matrix[w].length; b++){
                  matrix[w][b] /= pivot;
               }
            }
         }
      }   //End of rref.
      System.out.println("The reduce row echelon form is: ");
      printArray(matrix);
   }

   static void printArray(double[][] matrix){
      for (int i=0; i<matrix.length; i++){
         for (int j=0; j<matrix[i].length; j++){
            System.out.printf("%.4f",matrix[i][j]);
            System.out.print(" ");
         }
         System.out.println();
      }
   }

   static int InverseCount(double[][] P, int i){
      int count = 0;
      for (int n=P[i].length-1; n>-1; n--){
         if (P[i][n] == 0){
            count++;
         }else break;
      }
      return count;
   }

   static boolean InverseEqual(double[][] matrix, int i, int j){
      boolean Hey = false;
      Hey = InverseCount(matrix, i) == InverseCount(matrix, j);
      return Hey;
   }

   static void InverseBoom(double[][] Q, int current, int n){
      double pivot=0;
      for (int i=Q[current].length/2-1; i>0; i--){
         if (Q[current][i] != 0){
            pivot = Q[current][i];
            double ratio = pivot / Q[n][i];
            for (int o=Q[current].length-1; o>=0; o--){
               Q[current][o] = Q[current][o]- Q[n][o]*ratio;
            }
            break;
         }
      }
   }
}

