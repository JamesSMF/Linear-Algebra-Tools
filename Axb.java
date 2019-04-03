import java.util.Scanner;

class Axb{
   public static void main(String[] args){
      Scanner sc = new Scanner(System.in);
      System.out.print("How many rows does matrix A have: ");
      int r = sc.nextInt();
      System.out.println("Please enter " + r*r + " entries of matrix A:");
      double[][] A = new double[r][r];
      for (int a=0; a<r; a++){
         for (int d=0; d<r; d++){
            A[a][d] = sc.nextDouble();
         }
      }
      System.out.println("Please enter " + r + " entries of vector b");
      double[] b = new double[r];
      for (int c=0; c<r; c++){
         b[c] = sc.nextDouble();
      }

      double[][] cp = new double[r][r];
      for (int i=0; i<r; i++){
         for (int j=0; j<r; j++){
            cp[i][j] = A[i][j];
         }
      }

      if (Det(cp) != 0){
         double[][] Aug = new double[r][r+1];
         for (int f=0; f<r; f++){
            for (int e=0; e<r; e++){
               Aug[f][e] = A[f][e];
            }
         }
         for (int i=0; i<r; i++){
            Aug[i][r] = b[i];
         }

         for (int l=0; l<r-1; l++){
            for (int u=l+1; u<r; u++){
               if (equal(Aug,u,l)){ // If row u and l have the same number of zeros.
                  Boom(Aug,u,l); // Perform elimination on row u and l.
               }                 // Otherwise just ignore this row and continue checking
            }                    // the next row.
         }

         double[] sol = new double[r];
         sol = solve(Aug);
         printSolution(sol);
      }else{
         System.out.println("Matrix A is singular. This program only solves for invertible matrices.");
      }
   }

   static double[] solve(double[][] A){
      double[] sol = new double[A.length];
      double temp = 0;
      sol[A.length-1] = A[A.length-1][A[A.length-1].length-1] / A[A.length-1][A[A.length-1].length-2];
      for (int i=A.length-2; i>=0; i--){
         for (int au=A.length-1; au>i; au--){
            temp += sol[au] * A[i][au];
         }
         sol[i] = (A[i][A[i].length-1] - temp) / A[i][i];
         temp=0;
      }

      return sol;
   }

   static int CountZero(double[][] P, int i){
      int count = 0;
      for (int n=0; n<P[i].length; n++){
         if (P[i][n] == 0){
            count++;
         }else break;
      }
      return count;
   }

   static boolean equal(double[][] cp, int i, int j){
      boolean Hey = false;
      Hey = CountZero(cp, i) == CountZero(cp, j);
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

   static double Det(double[][] Q){
      for (int l=0; l<Q.length-1; l++){
         for (int u=l+1; u<Q.length; u++){
            if (equal(Q,u,l)){
               Boom(Q,u,l);
            }
         }
      }

      double det = 1;
      for (int k=0; k<Q.length; k++){
         det *= Q[k][k];
      }
      return det;
   }

   static void printSolution(double[] sol){
      System.out.println();
      System.out.print("x = (");
      for (int i=0; i<sol.length-1; i++){
         System.out.printf("%.4f",sol[i]);
         System.out.print(" ");
      }
      System.out.printf("%.4f",sol[sol.length-1]);
      System.out.println(")");
   }
}
