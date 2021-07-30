//do memoization

import java.util.Arrays;

/*
========================================================================
PROBLEM:)
========================================================================
Given two integers n and r, find nCr. Since the answer may be very large, calculate the answer modulo 109+7.

Input: n = 3, r = 2
Output: 3
Explaination: 3C2 = 3. 

Expected Time Complexity: O(n*r)
Expected Auxiliary Space: O(r)
*/

/*
=============================================================================
APPROACH:)
=============================================================================
COMBINATORIAL FORMULA FOR PASCAL'S TRIANGLE : nCr = (n-1)C(r-1) + (n-1)Cr;
concept of pascal's triangle

We take (in nCr), C[] of size r+1; We have to compute till the value of r
taking nested for loops, where the outer loop is to keep track of 'n' (from 1 to n) and j is for r (from min(i,r) to 1) [not from 1 to i, becoz the formula is C[j] + C[j-1] and its important that previous value to get the correct value at C[j]. We are using 1D array here too]

return C[r]

Time: O(n*r) (for loop)
Space: O(r) (C[])
*/
public class p1_Binomial_coeff_problem {

 static int p = 1000000007;

 public static void main(String[] args) {
  // System.out.println(nCr(5, 2));
  // System.out.println(C(15, 3));
  // System.out.println(BiCoeff(15, 3));
  System.out.println(binomaial_memo(5, 2));
 }

 public static int nCr(int n, int r) {
  int C[] = new int[r + 1];
  C[0] = 1;

  for (int i = 1; i <= n; i++) {
   for (int j = Math.min(i, r); j > 0; j--) {
    C[j] = (C[j] % p + C[j - 1] % p) % p; // using the concept of pascals triangle
   }
  }
  return C[r];
 }

 // recursive solution
 // Time: O(2^n)
 // Space: O(n), depth of the tree
 public static int C(int n, int r) {
  if (r == 0 || n == r) {
   return 1;
  }

  else if (n < r) {
   return 0;
  }

  else {
   return (C(n - 1, r - 1) % p + C(n - 1, r) % p) % p;
  }
 }

 // bottom up (no space optimization)
 // Time: O(n*r)
 // Space: (n*r)
 public static int BiCoeff(int n, int r) {
  int C[][] = new int[n + 1][r + 1];
  int i, j;

  for (i = 0; i <= n; i++) {
   for (j = 0; j <= Math.min(i, r); j++) {
    if (j == 0 || i == j) {
     C[i][j] = 1;
    }

    else {
     C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
    }
   }
  }
  return C[n][r];
 }

 // MEMOIZATION
 // O(n*r)
 // O(n*r) + size of recursion stack (will be equal to number of unique
 // subproblems, here at most it can be (n^2)/2, when k = n/2)
 public static int binomaial_memo(int n, int k) {

  // Make a temporary lookup table
  int[][] dp = new int[n + 1][];

  // Loop to create table dynamically
  for (int i = 0; i < (n + 1); i++) {
   dp[i] = new int[k + 1];
  }
  for (int j[] : dp) {
   Arrays.fill(j, -1);
  }
  return binomialCoeffUtil(n, k, dp);
 }

 private static int binomialCoeffUtil(int n, int k, int[][] dp) {
  if (dp[n][k] != -1)
   return dp[n][k];

  // store value in a table
  // before return
  if (k == 0 || k == n) {
   dp[n][k] = 1;
   return dp[n][k];
  }

  // save value in lookup table
  // before return
  dp[n][k] = binomialCoeffUtil(n - 1, k - 1, dp) + binomialCoeffUtil(n - 1, k, dp);
  return dp[n][k];
 }
}
