import java.util.Scanner;

class Eigen{
   public static void main(String[] args){
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter 9 entries:");
      double[] A = new double [9];
      for (int i = 0; i < 9; i++){
         A [i] = sc.nextDouble();
      }

      System.out.println("The determinant of the matrix is: "+det(A));

      if ((det(A)) == 0) System.out.println("Singular Matrix");
      else System.out.println("Invertible Matrix");

      if ((A[1]==A[3]) && (A[2]==A[6]) && (A[5]==A[7])){
         System.out.println("This is a symmetric matrix.");
      }else{
         System.out.println("This is not a symmetric matrix.");
      }

      double tolerance = 0.00000000001, threshold = 0.001;
      int asshole = 4;
      double [] C = new double[asshole];
      C[0] = det(A);
      C[1] = (A[1]*A[3]+A[2]*A[6]+A[5]*A[7])-(A[0]*A[4]+A[4]*A[8]+A[8]*A[0]);
      C[2] = A[0]+A[4]+A[8];
      C[3] = -1;

      double[] Lambda = new double[9];
      System.arraycopy(A, 0, Lambda, 0, 9);

      double o, p;
      for (double shit = -30.999; shit <= 29.999; shit++){
            o = shit;
            p = shit+1;
            if (poly(C, o) * poly(C, p) <= 0){
               double mid= findRoot(C, o, p, tolerance);
               if ((poly(C, mid) < 0.001) && (poly(C, mid) > -0.001)){
                  System.out.print("   lambda = ");
                  System.out.printf("%.5f%n", mid);
                  Lambda[0] = A[0] - mid;
                  Lambda[4] = A[4] - mid;
                  Lambda[8] = A[8] - mid;
                  double[] B = Gauss(Lambda);
                  double[] Evector = {-(B[2]*B[5]-B[1]*B[4])/B[0],-B[4],B[5]};
                  System.out.print("The corresponding eigenvector is: ");
                  printArray(Evector);
                  System.out.println();
               }
            }else if (diff(C, o) * diff(C, p) <= 0) {
               double midterm= findRootDiff(C, o, p, threshold);
               if ((poly(C, midterm) < 0.001) && (poly(C, midterm) > -0.001)){
                  System.out.print("   lambda = ");
                  System.out.printf("%.5f%n", midterm);
                  Lambda[0] = A[0] - midterm;
                  Lambda[4] = A[4] - midterm;
                  Lambda[8] = A[8] - midterm;
                  double[] B = Gauss(Lambda);
                  double[] Evector = {-(B[2]*B[5]-B[1]*B[4])/B[0],-B[4],B[5]};
                  System.out.print("The corresponding eigenvector is: ");
                  printArray(Evector);
                  System.out.println();
               }
            }
      }
   }

   static double poly(double[] C, double x){
      double sum=0;
      for (int i = 0; i < C.length; i++){
         sum += C[i] * Math.pow(x,i);
      }
      return sum;
   }

   static double diff(double[] C, double x){
      double sum = 0;
      for (int i=1; i < C.length; i++){
         sum += i * C[i] * Math.pow(x,i-1);
      }
      return sum;
   }

   static void printArray(double[] X){
      System.out.print("( ");
      for(int i=0; i<X.length; i++){
         System.out.printf("%.5f",X[i]);
         System.out.print(" ");
      }
      System.out.print(")");
   }

   static double findRoot(double[] C, double a, double b, double tolerance){
      tolerance= 0.00000000001;
      double mid=a, width;
      width = b-a;
      while ( width>tolerance ){
         mid = (a+b)/2;
         if( poly(C, a) * poly(C, mid) <= 0 ){
            b = mid;
         } else {
            a = mid;
         }
         width = b-a;
      }
      return mid;
   }

   static double findRootDiff(double[] C, double a, double b, double threshold){
      threshold= 0.00000000001;
      double mid=a, width;
      width = b-a;
      while ( width>threshold ){
         mid = (a+b)/2;
         if( diff(C, a) * diff(C, mid) <= 0 ){
            b = mid;
         } else {
            a = mid;
         }
         width = b-a;
      }
      return mid;
   }

   static double[] Gauss(double[] A){

      double p1 = A[0] / A[3];
      double p2 = ( ((A[0]*A[4])/A[3]) - A[1] ) / ( ( (A[0]*A[7]) /A[6])-A[1] );
      double[] B = new double[9];
      System.arraycopy(A, 0, B, 0, 9);

      if (A[3] == 0){
         if (!(A[6] == 0)){
            B[3] = 0;
            B[6] = 0;
            B[7] = 0;
            B[8] = A[5] / ((A[0]*A[7] / A[6]) - A[1]) - A[5];
         }else{
            B[3] = 0;
            B[6] = 0;
            B[7] = 0;
            B[8] = (A[4]*A[8]/A[7])-A[5];
         }
      }else if (A[6] == 0){
         if (A[7] == 0){
            B[3] = 0;
            B[4] = A[4] * p1 - A[1];
            B[5] = A[5] * p1 - A[2];
            B[6] = 0;
            B[7] = 0;
         }else{
            B[3] = 0;
            B[4] = A[4] * p1 - A[1];
            B[5] = A[5] * p1 - A[2];
            B[6] = 0;
            B[7] = 0;
            B[8] = (A[8] * ( ((A[0]*A[4])/A[3]) - A[1] ) / A[7]) - ((A[5]*A[0]/A[3]) - A[2]);
         }
      }else if ((A[0]/A[6])==(A[1]/A[7])){
            B[3] = 0;
            B[4] = A[4] * p1 - A[1];
            B[5] = A[5] * p1 - A[2];
            B[6] = 0;
            B[7] = 0;
      }else{
            B[3] = 0;
            B[4] = A[4] * p1 - A[1];
            B[5] = A[5] * p1 - A[2];
            B[6] = 0;
            B[7] = 0;
            B[8] = (( (A[0]*A[8]) / A[6] ) -A[2]) * p2 - ((A[5] * p1)-A[2]);
      }
      return B;
   }

   static void printMatrix(double[] A){
      for (int k=0; k<9; k++){
         if (k % 3 == 2){
            System.out.printf("%.5f%n",A[k]);
         }else{
            System.out.printf("%.5f",A[k]);
            System.out.print(" ");
         }
      }
   }

   static double det(double[] A){
      double a = A[0] * (A[4] * A[8] - A[5] * A[7]);
      double b = A[1] * (A[3] * A[8] - A[5] * A[6]);
      double c = A[2] * (A[3] * A[7] - A[4] * A[6]);
      double d = a-b+c;
      return d;
   }
}

