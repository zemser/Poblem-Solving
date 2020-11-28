import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;


//import java.util.*;

public class ArraysBasic {
    public static void main(String args[]) {
        System.out.println("---------------Start-----------------");
        ArraysBasic array = new ArraysBasic();
        int [] a = new int[]{2,3,1,3,2,4,6,7,9,2,19};
        int [] b = new int[]{2,1,4,3,9,6};
    
        /* Test Desired fucntion */
        int[] ans = array.relativeSortArray(a, b);
        System.out.print(Arrays.toString(ans));


        System.out.println("---------------End-----------------");
    }

    public static void swap(int []a, int i, int j){
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

   
    /*Given the array nums consisting of 2n elements in the form [x1,x2,...,xn,y1,y2,...,yn].
      Return the array in the form [x1,y1,x2,y2,...,xn,yn].*/
    public int[] shuffle(int[] nums, int n) { 
        int [] ret = new int[2 * n];
        int x = 0;
        int y = n;
        for(int i = 0; i < nums.length; i++){
            if(i % 2 == 0){
                ret[i] = nums[x];
                x++;
            }else{
                ret[i] = nums[y];
                y++;
            }  
        }
        return ret;
    }


    /*EASY* Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target. */
    public int[] twoSum(int[] nums, int target) {
        int [] ret = new int[2];
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = nums.length-1; i>=0; i--){
            int comp = target-nums[i];
            if(map.containsKey(comp)){
                return new int[]{map.get(comp), i};
            }
             map.put(nums[i], i);
        }
        return ret;
    }
    //now solve the same problem, given that the input is sorted. (return not zero based indexes)
    public int[] twoSumSorted(int[] numbers, int target) {
        int i=0, j=numbers.length-1;
        if(j==-1) return new int[]{};
        while(i < j){
            int sum = numbers[i] + numbers[j];
            if(sum == target) return new int[]{i+1, j+1};
            if(sum > target)
                j--;
            else
                i++;
        }
        
        return new int[]{};
    }


    /*MEDIUM* Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
    Notice that the solution set must not contain duplicate triplets. */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        HashSet<List<Integer>> set=new HashSet<List<Integer>>();
        for(int i = 0; i < nums.length-2; i++){
            int l = i + 1;
            int r = nums.length-1;
            while(l < r){
                int sum = nums[i] + nums[l] + nums[r];
                if(sum == 0){
                    set.add(Arrays.asList(nums[i], nums[l++], nums[r--]));
                }else if(sum > 0){
                    r--;
                }else l++;

            }
        }
        return null; //replace with line below 
       // return new ArrayList(set);
    }

    
    /*Say you have an array prices for which the ith element is the price of a given stock on day i.
      Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
      Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again). */
    public int maxProfit(int[] prices) {
        int ret = 0;
        boolean invested = false; 
        for(int i=1,cur=0; i<prices.length; i++,cur++){
            // prices goes down
            if(prices[cur] > prices[i]){
                if(invested){
                    ret += prices[cur];
                    invested = false;
                }
            }else{  // prices goes up  
                if(invested == false){
                    ret -= prices[cur]; 
                    invested = true;
            }
            // we've reached the last day
            if(invested == true && i == prices.length-1)
                ret += prices[prices.length-1];
            }
        }
        return ret;   
    }
    public int maxProfitAnotherApproach(int[] prices) {
        int i = 0;
        int valley = prices[0];
        int peak = prices[0];
        int maxprofit = 0;
        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1])
                i++;
            valley = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1])
                i++;
            peak = prices[i];
            maxprofit += peak - valley;
        }
        return maxprofit;
    }

    /*Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.
    Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2.  Elements that don't appear in arr2 should be placed at the end of arr1 in ascending order. */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int a1Len = arr1.length;
        int[] ret = new int[a1Len];
        Hashtable<Integer, Integer> table = new Hashtable<>();
        for (int num : arr2) {
            table.put(num, 0);
        }
        int countRepeatsFromA2 = 0;
        for (int num : arr1) {              //find all the nums that are also in arr2 and put in the table
            if(table.containsKey(num)){
                countRepeatsFromA2++;
                table.put(num, table.get(num)+1);
            }
        }
        int[] notInA2 = new int[a1Len-countRepeatsFromA2];
        int index = 0;
        for (int num : arr1) {      //find all the nums that are not from a2
            if(!table.containsKey(num)){
                notInA2[index++] = num;;
            }
        }
        Arrays.sort(notInA2);   // sort notInA2
        index = 0;
        for (int num : arr2) {      //insert all the nums that are also in arr2, by the order they are in arr2
            int repeats = table.get(num);
            while(repeats-- != 0){
                ret[index++] = num;
            }
        }
       for (int num : notInA2) {
           if(index > ret.length-1){
               return null;
           }
            ret[index++] = num;
       }
       return ret;
    }
    public int[] relativeSortArrayWay2(int[] arr1, int[] arr2) {
        Map<Integer, Integer> hm = new HashMap<>();
        for(int i = 0; i < arr2.length; i += 1) {
            hm.put(arr2[i], i);
        }
        
        List<int[]> list = new ArrayList<>();
        
        for(int i = 0; i < arr1.length; i += 1) {
            int[] pair = new int[2];
            Integer val = hm.get(arr1[i]);
            //If arr1[i] is not appear in arr2
			//then mark its position with Integer.MAX_VALUE
			//while sorting it helps us to determine that
			//we've to sort arr1[i] in ascending order
            if(val == null) {
                pair[0] = Integer.MAX_VALUE;
                pair[1] = arr1[i];
            }else {
                pair[0] = val;
                pair[1] = arr1[i];
            }
            
            list.add(pair);
        }
        
        Collections.sort(list, (a, b) -> {
           
            if(a[0] == Integer.MAX_VALUE && b[0] == Integer.MAX_VALUE)
                return (a[1] - b[1]);//In Ascending -> Based on value -> if elements of arr1 dont' appear in arr2
            return (a[0] - b[0]);//In Ascending Based on relative postion.
            
        });
        
        
        int[] result = new int[arr1.length];
        int index = 0;
        for(int[] ele : list) {
            result[index] = ele[1];
            index += 1;
        }
        
        return result;
    }

    /*Rotate a matrix by 90 degree in clockwise direction without using any extra space */
    public void rotate90Clockwise(int a[][], int N){
        // Traverse each cycle
        for (int i = 0; i < N / 2; i++){ // N/2 = number of layers in the matrix
            for (int j = i; j < N - i - 1; j++){  // how many cells to rotate in each layer
                // Swap elements of each cycle, in clockwise direction
                int temp = a[i][j];
                a[i][j] = a[N - 1 - j][i];
                a[N - 1 - j][i] = a[N - 1 - i][N - 1 - j];
                a[N - 1 - i][N - 1 - j] = a[j][N - 1 - i];
                a[j][N - 1 - i] = temp;
            }
        }
    } 


    /*EASY* Given an array A of N elements. Find the majority element in the array. A majority element in an array A of size N is an element that appears more than N/2 times in the array.
     we can sort the array and then go through it and it will be o(nlogn) */
    public int majorityElement(int a[], int size){
        Hashtable<Integer, Integer> map = new Hashtable<Integer,Integer>();
        for(int i = 0; i<size; i++){
            if(!map.containsKey(a[i])){
                map.put(a[i], 1);
            }else{
                map.put(a[i], map.get(a[i])+1);
            }
            
        }
        for(Integer key: map.keySet()){
             if(map.get(key) > size/2)
                 return key;
         }
         return -1;
     }
     
     
     /*Given an array A of N positive numbers. The task is to find the first Equilibium Point in the array. 
       Equilibrium Point in an array is a position such that the sum of elements before it is equal to the sum of elements after it. */
    public int equilibriumPointWithSpace(long arr[], int n) {
       long [] sum = new long[n];
       sum[0] = arr[0];
       if(n == 1){
           return 1;
       }
       for(int i = 1; i<n; i++){
           sum[i] += arr[i] + sum[i-1];
       }
       for(int i = 1; i<n-1; i++){
           if(sum[i-1] == sum[n-1]- sum[i]){
               return i+1;
           }
       }
       return -1;
   }
    public int equilibriumPoint(long arr[], int n) {
        int left_sum=0, right_sum=0;
        for (long num : arr) {
            right_sum += num;
        }
        if(n==1){
            return n;
        }
        right_sum -= (arr[0]);
        for(int i = 1; i<n-1; i++){
            right_sum -= arr[i];
            left_sum += arr[i-1];
            if(left_sum == right_sum){
                return i+1;
            }
        }
        return -1;
    }


    /*Given an unsorted array A of size N of non-negative integers, return true if there is a continuous sub-array which adds to a given number S.
    first with assumption that the numbers are positive and then without */
    public boolean subArraySum(int[] arr, int n, int sum){
        int curr_sum = arr[0], start = 0, i; 
        // Pick a starting point 
        for (i = 1; i <= n; i++) { 
            // If curr_sum exceeds the sum, 
            // then remove the starting elements 
            while (curr_sum > sum && start < i - 1) { 
                curr_sum = curr_sum - arr[start]; 
                start++; 
            }  
            if (curr_sum == sum) {  //If curr_sum becomes equal to sum, return true
                return true;
            } 
            if (i < n)  // Add this element to curr_sum 
                curr_sum = curr_sum + arr[i]; 
        } 
        return false; 
    }
    public boolean subArraySumNegative(int[] arr, int n, int sum){
        HashMap<Integer, Integer> map = new HashMap<>(); 
        int cur_sum = 0;
        for(int i = 0; i < n; i++){
            cur_sum += arr[i];
            if(cur_sum == sum)  //startig index is 0, ending index is i
                return true;
            int complement = sum - cur_sum;
            if(map.containsKey(complement)){    //starting index is map.get(complement), ending index is i
                return true;
            }
            map.put(cur_sum,i);
        }
        return false;
    }


    /*Medium* Given an array of 0s and 1s. Find the length of the largest subarray with equal number of 0s and 1s. */
    int MaxLength(int[] nums) {
        int maxlen = 0;
        for (int start = 0; start < nums.length; start++) {
            int zeroes = 0, ones = 0;
            for (int end = start; end < nums.length; end++) {
                if (nums[end] == 0) {
                    zeroes++;
                } else {
                    ones++;
                }
                if (zeroes == ones) {
                    maxlen = Math.max(maxlen, end - start + 1);
                }
            }
        }
        return maxlen;
    }
    public int MaxLengthArray(int[] nums) {
        /*we need to think about a graphic approach. the arr represetns index that the sum equals a spefic number */
        int[] arr = new int[2 * nums.length + 1];
        Arrays.fill(arr, -2);
        arr[nums.length] = -1;
        int maxlen = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            count = count + (nums[i] == 0 ? -1 : 1);
            if (arr[count + nums.length] >= -1) {
                maxlen = Math.max(maxlen, i - arr[count + nums.length]);
            } else {
                arr[count + nums.length] = i;
            }

        }
        return maxlen;
    }
    int maxLenHash(int[] arr, int N){
        /*same idea as above only with hash */
        Map<Integer, Integer> map = new HashMap<>();
        int ret = 0, sum = 0;
        map.put(0, -1); //put in for key sum 1 , value index -1
        for(int i = 0; i < N; i++){
            sum += arr[i] == 0 ? -1 : 1;
            if(!map.containsKey(sum)){
                map.put(sum, i);
            }else{
                int index = map.get(sum);
                ret = Math.max(ret, i-index);
            }
            
        }
        return ret;
    }
   

    /*Basic Given an array of size N containing 0s, 1s, and 2s; sort the array in ascending order.
     constraint you can solve this problem using single scan */
    public void sortZeroOneTwO(int []a){
        int low = 0, mid = 0, high = a.length-1;
        while(mid <= high){
            if(a[mid] == 0){
                swap(a, low++, mid++);
                continue;
            }
            if(a[mid] == 1){
                mid++;
                continue;
            }
            if(a[mid] == 2){
                swap(a, mid, high--);
                continue;
            }
        }
    }


    /*Easy but very TRICKY** Given an integer array nums, find the contiguous subarray (containing at least one number)
     which has the largest sum and return its sum.*/
     public int maxSubArray(int[] nums) {
        int currSum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i ++) {
            currSum = Math.max(currSum + nums[i], nums[i]);
            max = Math.max(currSum, max);
        }
        return max;
    }


    /*MEDIUM* Given an array of integers. Find the Inversion Count in the array. 
    Inversion Count: For an array, inversion count indicates how far (or close) the array is from being sorted. If array is already sorted then the inversion count is 0. If an array is sorted in the reverse order then the inversion count is the maximum. 
    Formally, two elements a[i] and a[j] form an inversion if a[i] > a[j] and i < j. */
    public int mergeAndCount(int[] arr,int l, int m, int r){
        int[] left = Arrays.copyOfRange(arr, l, m + 1);
        int[] right = Arrays.copyOfRange(arr, m + 1, r + 1);
        int i = 0, j = 0, k = l, swaps = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j])
                 arr[k++] = left[i++];
            else {
                arr[k++] = right[j++];
                swaps += (m + 1) - (l + i);
            }
        }
        return swaps;
    }
    public int mergeSortAndCount(int[] arr,int l, int r){
        int count = 0; //Keeps track of the inversion count
        if (l < r) {
            int m = (l + r) / 2;
            count += mergeSortAndCount(arr, l, m); // Left subarray count
            count += mergeSortAndCount(arr, m + 1, r); // Right subarray count
            count += mergeAndCount(arr, l, m, r);  // Merge count
        }
        return count;
    }


    /*MEDIUM: You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once. Find this single element that appears only once.
    Follow up: Your solution should run in O(log n) time and O(1) space. */
    public int singleNonDuplicate(int[] nums) {
        int ret = 0;
        for(int num: nums){
            ret ^= num;
        }
        return ret;
    }
    public int singleNonDuplicate2(int[] nums) { //note that there is another solution- to do a binary search and choose what side the element is by the pairty of the side.(e.g if n[mid] = n[mid+1] && leftsize%2=0 then go left) 
        int p1 = 0;
        int p2 = nums.length-1;
        while(p1 < p2){
            if(nums[p1] != nums [p1+1]){
                return nums[p1] ^ nums[p1+1] ^ nums[p1+2];
                
            }
            if(nums[p2] != nums [p2-1]){
                return nums[p2] ^ nums[p2-1] ^ nums[p2-2];
            }
            p1 += 2;
            p2 -= 2;
        }
        return nums[p1];
    }




}