import java.util.Arrays;

/*
============================================================
PROBLEM:)
============================================================
Given two sequences, find the length of longest subsequence present in both of them. Both the strings are of uppercase.

==============================================================
EXAMPLE:
==============================================================
A = 6, B = 6
str1 = ABCDGH
str2 = AEDFHR
Output: 3
*/

/*
================================================================
APPROACH:)
================================================================
make a matrix to store the solution
in Bottom up approach, we use nested for loops, that loop till the length of respective strings
if length of either string is 0, we store 0 as solution for c[i][j]
if the S1.charAt(i) = S2.charAt(j), we add it to previous answer (c[i][j] i.e. the lcs that was before the current element was encountered) and store it at c[i][j]
else we selet the maximum of c[i-1][j] and c[i][j-1] and store that in c[i][j]

Time : O(m*n)
Time taken to compute each subproblem is constant and there are m*n unique subproblems

Space Complexity : O(m*n) for the table

===========================================================================
APPROACH: 2
top down recursive solution if either m or n is 0 (one of the strings is empty) we return 0 
if the dp[m-1][n-1] is not -1 (that is it has been visited before) we return the solution at this place 
if characters match in the 2 strings, we add it to the recursive call where we will decrease m and n both by one and then store this in dp[m-1][n-1] 
if they dont match we will see which of the 2  possibilities returns the longest common subseq and assign it to dp[m-1][n-1]

time and space remain same.
*/
public class p3_LCS {

 public static void main(String[] args) {
  String S1 = "ABCBDAB";
  String S2 = "BDCABA";

  char[] X = S1.toCharArray();
  char[] Y = S2.toCharArray();

  int m = X.length;
  int n = Y.length;

  System.out.println(LCS(X, Y, m, n));
  int[][] c = new int[m + 1][n + 1];
  for (int[] row : c) {
   Arrays.fill(row, -1);
  }
  System.out.println(lcs(S1, S2, m, n, c));

 }

 public static int LCS(char[] X, char[] Y, int m, int n) {

  int[][] c = new int[m + 1][n + 1];
  int i, j;

  // WE START WITH 1 AND NOT ZERO, SO AT ZERO, 0 IS INITIALIZED
  for (i = 0; i <= m; i++) {
   for (j = 0; j <= n; j++) {
    if (i == 0 || j == 0)
     c[i][j] = 0;
    else if (X[i - 1] == Y[j - 1]) {
     c[i][j] = c[i - 1][j - 1] + 1;
    } else {
     c[i][j] = Math.max(c[i - 1][j], c[i][j - 1]);
    }
   }
  }

  return c[m][n];
 }

 // memoization
 public static int lcs(String X, String Y, int m, int n, int[][] c) {
  if (m == 0 || n == 0) {
   return 0;
  }

  if (c[m - 1][n - 1] != -1) {
   return c[m - 1][n - 1];
  }

  if (X.charAt(m - 1) == Y.charAt(n - 1)) {
   c[m - 1][n - 1] = 1 + lcs(X, Y, m - 1, n - 1, c);
   return c[m - 1][n - 1];
  }

  else {
   c[m - 1][n - 1] = Math.max(lcs(X, Y, m, n - 1, c), lcs(X, Y, m - 1, n, c));

   return c[m - 1][n - 1];
  }
 }
}
