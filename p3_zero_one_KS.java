public class p3_zero_one_KS {
 public static void main(String[] args) {
  int[] W = new int[] { 1, 2, 4 };
  int[] p = new int[] { 10, 12, 28 };
  int C = 6;
  int n = p.length;

  System.out.println(KnapSack(W, p, C, n));
 }

 public static int KnapSack(int[] W, int[] p, int C, int n) {
  int[][] t = new int[n + 1][C + 1];
  int i, j;

  for (i = 0; i <= n; i++) {
   for (j = 0; j <= C; j++) {
    if (i == 0 || j == 0) {
     t[i][j] = 0;
    } else if (W[i - 1] <= j && t[i - 1][j - W[i - 1]] + p[i - 1] > t[i - 1][j]) {
     t[i][j] = t[i - 1][j - W[i - 1]] + p[i - 1];
    } else {
     t[i][j] = t[i - 1][j];
    }
   }
  }

  return t[n][C];
 }

}
