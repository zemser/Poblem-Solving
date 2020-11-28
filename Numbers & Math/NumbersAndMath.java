import static java.lang.String.valueOf;

import java.util.*;
public class NumbersAndMath {
    static int sola = 68;
    public static void main(String args[]) {
        System.out.println("---------------Start-----------------");
        //NumbersAndMath obj = new NumbersAndMath();

        /* Test Desired fucntion */
       // int x = obj.findMinFibonacciNumbers(7);
       
    }

    // return the nth Fibonacci number
    public int fibBasic(int N) {
        // Time complexity : O(2^N) Space complexity : O(N)
        if (N == 0) {
            return 0;
        }
        if (N == 1)
            return 1;
        return fibBasic(N - 2) + fibBasic(N - 1);
    }


    public int gcd(int n, int m){
        int reminder = n % m;
        while(reminder != 0){
            n = m;
            m = reminder;
            reminder = n % m;
        }
        return m;
        //rec version:
        // if(number2 == 0){
        //     return number1;
        // } 
        // return findGCD(number2, number1%number2);
    }


    /*Given a 32-bit signed integer, reverse digits of an integer.   ***Easy*** */
    public int reverse(int x) {
       long ret = 0;
        while(x != 0){
            ret = ret*10 + x%10;
            x /= 10;
        }
        if(ret > Integer.MAX_VALUE || ret < Integer.MIN_VALUE)
            return 0;
        return (int)ret;
    }

    /*Follow up: find if the the number is a palindrome 
     we can do use the reverse and check if the original number is the same as the rev or the following function */
    public boolean checkPal(int x) {
        String s = valueOf(x);  
        int l = s.length();
        int i = 0;
        int j = l - 1;
        while(i < j) {
            if(s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
     
        
    /*
     * Given a non-empty array of integers nums, every element appears twice except
     * for one. Find that single one.
     */
    public int singleNumber(int[] nums) {
        Hashtable<Integer, Integer> table = new Hashtable<Integer, Integer>();
        for (int i : nums) {
            table.put(i, table.getOrDefault(i, 0) + 1);
          }
          for (int i : nums) {
            if (table.get(i) == 1) {
              return i;
            }
          }
          return 0;
   
    }
     //implement without using extra memory?
     public int singleNumberNoExtraMemory(int[] nums) {
        int a = 0;
        for (int i : nums) {
          a ^= i;
        }
        return a;
    }


    /*Given an array nums containing n distinct numbers in the range [0, n], 
    return the only number in the range that is missing from the array.*/
    public int missingNumber(int[] nums) {
        int sum = 0;
        int total = nums.length;
        for (int i = 0; i<nums.length; i++){
            sum += nums[i];
            total += i;
        }
        return total-sum;
    }
   //implement a solution using only O(1) extra space complexity and O(n) runtime complexity?
   public int missingNumberNoExtraMemory(int[] nums){
    int missing = nums.length;
    for (int i = 0; i < nums.length; i++) {
        missing ^= i ^ nums[i];
    }
    return missing;
   }


   /*Write a program to check whether a given number is an ugly number.
    Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.*/
   public boolean isUgly(int num) {
        if(num == 0)
            return false;
        while(num % 2 == 0){
            num /= 2;
        }
        while(num % 3 == 0){
            num /= 3;
        }
        while(num % 5 == 0){
            num /= 5;
        }
        return num == 1;
    }


    /*MEDIUM: Write a program to find the n-th ugly number.
      Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. */
    public int nthUglyNumber(int n) {
        int count = 1;
        int ret = 1;
        ArrayList<Integer> q2 = new ArrayList<>();
        ArrayList<Integer> q3 = new ArrayList<>();
        ArrayList<Integer> q5 = new ArrayList<>();
        q2.add(2);
        q3.add(3);
        q5.add(5);
        while(count < n){
            int next2 = q2.get(0);
            int next3 = q3.get(0);
            int next5 = q5.get(0);
            if(next2 < next3 && next2 < next5){
                ret = q2.remove(0);
                q2.add(ret*2);
                q3.add(ret*3);
                q5.add(ret*5);
            }else if(next3 < next2 && next3 < next5){
                ret = q3.remove(0);
                q3.add(ret*3);
                q5.add(ret*5);
            }else{
                ret = q5.remove(0);
                q5.add(ret*5);
            }
            count++;
        }
        return ret;
    }


    /*Write an algorithm to determine if a number n is "happy".
    A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
    Return True if n is a happy number, and False if not.*/
    public boolean isHappy(int n) {
        int slow = n;
        int fast = n;  //to detect if there is a loop we have fast var (if there is a loop it will be equal to the value of the slow eventually)
         while(slow != 1){
            fast = happify(fast);
            fast = happify(fast);
            slow = happify(slow);
           if(slow == 1) 
            return true;
            if(slow == fast)
                return false;
        }
        return true;
       
    }
    public int happify(int num){
        int ret = 0;
        while(num != 0){
            int dig = num % 10;
            ret += (dig * dig);
            num /= 10;
        }
        return ret;
    }


    /*A perfect number is a positive integer that is equal to the sum of its positive divisors, excluding the number itself. A divisor of an integer x is an integer that can divide x evenly.
    Given an integer n, return true if n is a perfect number, otherwise return false.*/
    public boolean checkPerfectNumber(int num) {
        if (num <= 0) {
            return false;
        }
        int sum = 0;
        for (int i = 1; i * i <= num; i++) {  //instead of iterating over all the integers to find the factors of numnum, we only iterate upto the sqrt{n}
            if (num % i == 0) {
                sum += i;
                if (i * i != num) {
                    sum += num / i;
                }

            }
        }
        return sum - num == num;  //while considering 1 as such a factor, numnum will also be considered as the other factor. Thus, we need to subtract numnum from the sum
    }



    /*Meduim: Given an integer k, return the minimum number of Fibonacci numbers whose sum is equal to k. 
    The same Fibonacci number can be used multiple times.*/
    public int findMinFibonacciNumbers(int k) {
        ArrayList<Integer> fibs = makeFibs(k);
        int sum = k;
        int ret = 0;
        for(int i = fibs.size()-1; i>=0; i--){
            if(fibs.get(i) <= sum){
                ret++;
                sum -= fibs.get(i);
            }
            if(sum == 0)
                return ret;
            if(i == 0)
                return ret + sum;
        }
        return 0;
    }
    public  ArrayList<Integer> makeFibs( int k){
        ArrayList<Integer> fibs = new ArrayList<>();
        int index = 1;
        fibs.add(1);
        fibs.add(1);
        while(k >= fibs.get(index)){
            fibs.add(fibs.get(index) + fibs.get(index-1));
            index++;
        }
        return fibs;
    }

 

    /* Given an Integer N between -8,000 to 8,000. insert the digit '5' in some index, so that the returned number will be the maximum possbile*/
    public int findMax(int N){
        int ret = 0;
        boolean positive = true;
        if(N < 0){
            positive = false;
            N *= -1;
        }
        String stringN = Integer.toString(N);
        int[] arrayN = new int[stringN.length()];
        for (int i = 0; i < stringN.length(); i++){
            arrayN[i] = stringN.charAt(i) - '0';
        }
        int insert = stringN.length();  // if index isnt found insert it to the end 
        for(int i = 0; i < arrayN.length; i++){
            if(positive && arrayN[i] < 5){
                insert = i;
                break;
            }
            if(!positive && arrayN[i] >= 5){
                insert = i;
                break;
            }
        }
        ret = Integer.parseInt(stringN.substring(0, insert) + "5" + stringN.substring(insert, stringN.length()));  
        if(!positive){
            ret *= -1;

        } 
        return ret;
    }

    
   
}
