
/*
PROBLEM:
Given an integer array arr, partition the array into (contiguous) subarrays of length at most k. After partitioning, each subarray has their values changed to become the maximum value of that subarray.
Return the largest sum of the given array after partitioning. Test cases are generated so that the answer fits in a 32-bit integer.
*/

/*
APPROACH:)
Going right to left for every increasing 'i'
*/

public class x2_Partition_array_for_maximum_sum {
 public static void main(String[] args) {
  int[] arr = { 1, 15, 7, 9, 2, 5, 10 };
  int k = 3;
  System.out.println(maxSumAfterPartitioning(arr, k));
 }

 public static int maxSumAfterPartitioning(int[] A, int K) {
  int N = A.length, dp[] = new int[N + 1];
  for (int i = 1; i <= N; ++i) {
   int curMax = 0, best = 0;
   for (int k = 1; k <= K && i - k >= 0; ++k) {
    curMax = Math.max(curMax, A[i - k]);
    best = Math.max(best, dp[i - k] + curMax * k);
   }
   dp[i] = best;
  }
  return dp[N];
 }
}
