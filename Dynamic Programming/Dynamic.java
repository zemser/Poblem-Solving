import java.util.Arrays;

public class Dynamic {
    public static void main(String args[]) {
        System.out.println("---------------Start-----------------");
        Dynamic obj = new Dynamic();
        String [] input = new String[]{"10","0001","111001","1","0"};
        /* Test Desired fucntion */
      
        System.out.println(obj.findMaxForm(input, 5, 3));
        // int x = obj.nthUglyNumber(10);
        // System.out.println(x); 
    
    }

    //return the nth Fibonacci number
    public int fibBasic(int N) {
        // Time complexity : O(2^N)  Space complexity : O(N)
        if(N == 0){
            return 0;
        }
        if(N == 1)
            return 1;
        return fibBasic(N-2) + fibBasic(N-1); 
    }
    public int fibMemoization(int N) {
        if(N == 0) 
             return 0;
        int [] mem = new int[N+1];
        mem[1] = 1; 
        mem = fibHelp(N, mem);
        return mem[N];
    }
    public int[] fibHelp(int N, int[] mem) {
         if(N<2){
             return mem;
        }
        mem = fibHelp(N-1, mem);
        mem = fibHelp(N-2, mem);
        mem[N] = mem[N-1] + mem[N-2];
        return mem;
    }


    /*Given weights and values of n items, put these items in a knapsack of capacity W to get the maximum total value in the knapsack* */
    public int knapSack(int W, int wt[], int val[], int n){ 
        int i, w; 
        int K[][] = new int[n + 1][W + 1]; 
        for (i = 0; i <= n; i++){ 
            for (w = 0; w <= W; w++){ 
                if (i == 0 || w == 0) 
                    K[i][w] = 0; 
                else if (wt[i - 1] <= w) 
                    K[i][w] 
                        = Math.max(val[i - 1] + K[i - 1][w - wt[i - 1]], K[i - 1][w]); 
                else
                    K[i][w] = K[i - 1][w]; 
            } 
        } 
        return K[n][W]; 
    } 


    /*MEDIUM* Given a non-empty array nums containing only positive integers, 
    find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.*/
    public boolean canPartition(int[] nums) {
         int sums=0; // total sum of the array 
         for(int i = 0; i < nums.length; i++){
             sums += nums[i];
         }
         if(sums%2 != 0) return false; // the total sum of the array need to be equal
         int half = sums/2;
         boolean [][] dp = new boolean[nums.length+1][half+1];
         for(int i = 0; i < nums.length+1; i++){
             for(int s = 0; s < half+1; s++){
                 if(i == 0){
                     if(s == 0) 
                        dp[i][s] = true;
                     else 
                        dp[i][s] = false;
                 }else{
                    if(s == 0 ) // && i != 0
                        dp[i][s] = false;
                    else{
                        if((i != 0 && s <= 0) || ((i == 0 && s > 0))) dp[i][s] = false;
                        if(i==0 && s == 0) dp[i][s] = true;
                        if(nums[i-1] > s){
                            dp[i][s] = dp[i-1][s];
                        }else{
                            dp[i][s] = dp[i-1][s] || dp[i-1][s-nums[i-1]];
                        }
                    }
                 }
             }
         }
         return dp[nums.length][half];
    }


    /*MEDIUM: Write a program to find the n-th ugly number.
      Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. */
    int nthUglyNumber(int n) {
        int[] arr = new int[n];
		arr[0] = 1;
		n--;
		int two = 0;
		int three = 0;
		int five = 0;
		int c = 1;
		int prev=1;
		while(n>0){
			if((arr[two]*2 < arr[three]*3) && (arr[two]*2 < arr[five]*5)){
				arr[c] = arr[two]*2;
				two++;
			}
			else if(arr[three]*3 < arr[five]*5){
				arr[c] = arr[three]*3;
				three++;
			}
			else{
				arr[c] = arr[five]*5;
				five++;
			}

			if(prev != arr[c]){
				prev = arr[c];
				c++;
				n--;
			}
		}

		return arr[arr.length-1];
	}


    /*Given two strings ‘X’ and ‘Y’, find the length of the longest common substring.          ***Medium****/
    public int LCSubStr(char X[], char Y[], int m, int n){ 
        /*Brute Force: one by one consider all substrings of first string and for every substring check if it is a substring in second string.
        Keep track of the maximum length substring. There will be O(m^2) substrings and we can find whether a string is subsring on another string in O(n) time. 
        So overall time complexity of this method would be O(n * m2)

        Dynamic Programming can be used to find the longest common substring in O(m*n) time.
        The idea is to find length of the longest common suffix for all substrings of both strings and store these lengths in a table. */
        int LCStuff[][] = new int[m + 1][n + 1]; 
        int result = 0;  // To store length of the longest common substring 
        // Following steps build LCSuff[m+1][n+1] in bottom up fashion 
        for (int i = 0; i <= m; i++)  { 
            for (int j = 0; j <= n; j++){ 
                if (i == 0 || j == 0) 
                    LCStuff[i][j] = 0; 
                else if (X[i - 1] == Y[j - 1]){ 
                    LCStuff[i][j] = LCStuff[i - 1][j - 1] + 1; 
                    result = Integer.max(result, LCStuff[i][j]); 
                }  
                else
                    LCStuff[i][j] = 0; 
            } 
        } 
        return result; 
    } 


    /*Given a value N, if we want to make change for N cents, and we have infinite supply of each of S = { S1, S2, .. , Sm} valued coins, how many ways can we make the change? The order of coins doesn’t matter.*/
    public long countWays(int S[], int m, int n) { 
        //Time complexity of this function: O(mn) 
        //Space Complexity of this function: O(n) 
  
        // table[i] will be storing the number of solutions 
        // for value i. We need n+1 rows as the table is 
        // constructed in bottom up manner using the base 
        // case (n = 0) 
        long[] table = new long[n+1]; 
  
        // Initialize all table values as 0 
        Arrays.fill(table, 0);   //O(n) 
  
        // Base case (If given value is 0) 
        table[0] = 1; 
  
        // Pick all coins one by one and update the table[] 
        // values after the index greater than or equal to 
        // the value of the picked coin 
        for (int i=0; i<m; i++) 
            for (int j=S[i]; j<=n; j++) 
                table[j] += table[j-S[i]]; 
  
        return table[n]; 
    } 
  
    
    /*MEDIUM: "Ones and Zeroes": You are given an array of binary strings strs and two integers m and n.
    Return the size of the largest subset of strs such that there are at most m 0's and n 1's in the subset. */
    public int findMaxForm(String[] strs, int m, int n) {
        int [][] dp = new int [m+1][n+1];
        for(String s: strs){
            int zeros = countStringZeros(s);
            int ones = s.length() - zeros;
            for (int i = m ; i >= zeros; i --) {
                for (int j = n; j >= ones; j --) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
                }
            }
        }
         return dp[m][n];
    }
    public int countStringZeros(String s){
        int count = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '0')
                count++;
        }
        return count;
    }
    class Pair{
        int index;
        int repeats;
        public Pair(int i, int r){
            this.index = i;
            this.repeats = r;
        }
    }


}


