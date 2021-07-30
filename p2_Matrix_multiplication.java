import java.util.Arrays;

/*
===============================================================================
PROBLEM:
===============================================================================
Given a sequence of matrices, find the most efficient way to multiply these matrices together. The efficient way is the one that involves the least number of multiplications. The dimensions of the matrices are given in an array arr[] of size N (such that N = number of matrices + 1) where the ith matrix has the dimensions (arr[i-1] x arr[i]).

Expected Time Complexity: O(N3)
Expected Auxiliary Space: O(N2)

============================================================
EXAMPLE:
============================================================
Input: N = 4
arr = {10, 30, 5, 60}
Output: 4500

Input: N = 4
arr = {1, 2, 3, 4}
Output: 18
*/

/*
==================================================================
APPROACH:
==================================================================
For DP we make a matrix 'm' and to store the place where we split a particular matrix chain to get the least value for scalar multiplication we make a matrix 'S'
Intitialize m[index][index] = 0 as scalar * of a matrix with itself = 0
l is the chain length; chain length = number of matrices getting *; for (1,2) its 2, for (1,3) its 3 and so on
for every value of 'l', 'i' and 'j' are picked
i start from 1(bcoz we have to access element at i-1 too) and goes till n-l+1; j = i+l-1;
we use a for loop with variable k that ranges from i to j-1, this is used to find out the split (for least scalar multiplication)
for each computed split, if the present computed split is less than the already computed one, we replace the prev with current
whenever we go to any value, we are taking all possible splits (thatswhy k is going from i to j-1)


If we have 1 to n matrices, number of splits possible is (n-1), and we have to observe each split to know the minimum time for one split. Therefore, time taken to compute one entry is O(N)
Total time complexity : O(N^2).O(N) = O(N^3)
Space Comp: O(N^2); space taken by the table

Time : O(N^3)
Space : O(N^2)
*/

public class p2_Matrix_multiplication {

  public static void main(String[] args) {
    int[] arr = new int[] { 1, 2, 1, 4, 1 };
    // System.out.println(Matrix_chain(arr));
    System.out.println(Practice.Memoised(arr));
  }

  public static int Matrix_chain(int[] p) {
    int[][] m = new int[p.length][p.length];
    int n = p.length;
    int[][] S = new int[n][n + 1];
    int i, k, l, j, q;

    for (i = 1; i < n; i++)
      m[i][i] = 0;

    for (l = 2; l < n; l++) { // l is the chain length
      for (i = 1; i < n - l + 1; i++) {
        j = i + l - 1;
        if (j == n)
          continue;
        m[i][j] = Integer.MAX_VALUE;
        for (k = i; k <= j - 1; k++) {
          q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
          if (q < m[i][j]) {
            m[i][j] = q;
            S[i][j] = k;
          }
        }
      }
    }
    for (int x = 1; x < n - 1; x++) {
      for (int y = 2; y < n; y++) {
        System.out.print(S[x][y] + " ");
      }
      System.out.println();
    }

    return m[1][n - 1];

  }

}

/*
 * ======================================================================
 * APPROACH:
 * ======================================================================
 * initially keeping i =1 and j as the last index of p array, we call a function
 * Util to compute the values of scalar * for a given chain length We start from
 * the top node, - from there whenever we call the function, we check its value
 * is already present in the table If the value is present, we use the value; if
 * not then only we call the function Number of function calls here is same as
 * the number of unique subproblems in the bottom up approach
 * 
 * TC: O(N^2) . O(N) = O(N^3) SC: O(N^2) + depth of the recursion stack in worst
 * case
 * 
 * - recursion stack depends on the depth of the tree which is O(N) and
 * therefore stack space required is O(N) - therefore, O(N^2) + O(N) = O(N^2).
 * Actual space taken is a bit more bcoz of the recursion required
 * 
 */

class Practice {

  static int[][] m = new int[100][100];

  public static int Memoised(int[] p) {
    int n = p.length;
    for (int[] row : m)
      Arrays.fill(row, Integer.MAX_VALUE);

    return Util(p, 1, n - 1);
  }

  public static int Util(int[] p, int i, int j) {
    if (m[i][j] < Integer.MAX_VALUE) {
      return m[i][j];
    }
    if (i == j) {
      m[i][j] = 0;
    }

    for (int k = i; k < j; k++) {
      m[i][j] = Math.min(m[i][j], Util(p, i, k) + Util(p, k + 1, j) + p[i - 1] * p[k] * p[j]);
    }

    return m[i][j];
  }

}