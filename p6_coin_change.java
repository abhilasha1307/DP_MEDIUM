public class p6_coin_change {
 public static void main(String[] args) {
  int n = 10, m = 4;
  int S[] = { 2, 5, 3, 6 };

  System.out.println(count(S, m, n));
 }

 public static long count(int S[], int m, int n) {
  int[] dp = new int[n + 1];
  dp[0] = 1; // null subset

  for (int i = 0; i < m; i++) {
   for (int j = S[i]; j <= n; j++) {
    dp[j] += dp[j - S[i]];
   }
  }

  return dp[n];

 }

}
