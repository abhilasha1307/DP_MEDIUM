/*
RECURSIVE APPROACH:
public static boolean equalPartition(int n, int[] arr)
{
 int sum = 0;
 for(int x : arr)
 {
  sum+=x;
 }

 return Util(arr, n, sum/2);
}

public static boolean Util(int[] arr, int n, int sum)
{
 if(sum == 0)
 {
  return true;
 }
 if(n == 0 && sum!=0)
 {
  return false;
 }

 if(arr[n-1] == sum)
 {
  return Util(arr, n-1, sum);
 }

 return Util(arr, n-1, sum) || Util(arr, n-1, sum - arr[n-1]);
}
*/
public class p5_partition_equal_subset_sum {
 public static void main(String[] args) {
  int[] arr = new int[] { 6, 3, 2, 1 };
  int n = arr.length;

  // System.out.println(equalPartition(n, arr));
  System.out.println(equalPartition_2(n, arr));
 }

 static int equalPartition(int n, int arr[]) {

  int sum = 0;
  int i, j;

  for (int x : arr) {
   sum += x;
  }
  if (sum % 2 != 0) {
   return 0;
  }

  boolean[][] t = new boolean[sum / 2 + 1][n + 1];

  for (i = 0; i <= n; i++) {
   t[0][i] = true;
  }

  for (j = 1; j <= sum / 2; j++) {
   t[j][0] = false;
  }

  for (i = 1; i <= sum / 2; i++) {
   for (j = 1; j <= n; j++) {
    t[i][j] = t[i][j - 1];
    if (i >= arr[j - 1]) {
     t[i][j] = t[i][j] || t[i - arr[j - 1]][j - 1];
    }
   }
  }

  if (t[sum / 2][n] == true) {
   return 1;
  } else {
   return 0;
  }
 }

 public static boolean equalPartition_2(int n, int[] arr) {
  int sum = 0;
  for (int x : arr) {
   sum += x;
  }

  return Util(arr, n, sum / 2);
 }

 public static boolean Util(int[] arr, int n, int sum) {
  if (sum == 0) {
   return true;
  }
  if (n == 0 && sum != 0) {
   return false;
  }

  if (arr[n - 1] > sum) {
   return Util(arr, n - 1, sum);
  }

  return Util(arr, n - 1, sum) || Util(arr, n - 1, sum - arr[n - 1]);
 }
}
