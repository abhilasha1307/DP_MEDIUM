//use stack or queue to print the subsets too
public class p4_subset_sum {
 public static void main(String[] args) {
  int[] arr = new int[] { 6, 3, 2, 1 };
  int n = 5;

  if (Subset_sum(arr, n) == 1) {
   System.out.println("true");
  } else {
   System.out.println("false");

  }
 }

 public static int Subset_sum(int[] arr, int n) {
  int[][] t = new int[arr.length + 1][n + 1];
  int i, j;

  for (i = 0; i <= arr.length; i++) {
   t[i][0] = 1;
  }

  for (j = 1; j <= n; j++) {
   t[0][j] = 0;
  }

  for (i = 1; i <= arr.length; i++) {
   for (j = 1; j <= n; j++) {
    t[i][j] = t[i - 1][j];
    if (j >= arr[i - 1]) {
     t[i][j] = Math.max(t[i][j], t[i - 1][j - arr[i - 1]]);
    }
   }
  }

  return t[arr.length][n];
 }
}
