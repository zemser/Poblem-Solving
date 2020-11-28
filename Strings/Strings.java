public class Strings{
    
    public static void main(String args[]) {
        System.out.println("---------------Start-----------------");
        Strings strings = new Strings();
        //int [] a = new int[]{4,5,6,7,0,2,1,3};
        String s = "art";
        
        /* Test Desired fucntion */
        
        System.out.println(strings.sortString(s));

        System.out.println("---------------End-----------------");
    }


    /*Easy but not that trivial: Given a string s and an integer array indices of the same length.
      The string s will be shuffled such that the character at the ith position moves to indices[i] in the shuffled string. */
    public String restoreString(String s, int[] indices) {
        char[] wordArray = s.toCharArray();
        for (int i = 0; i < wordArray.length; i++) {
            if(i != indices[i]){
               wordArray[indices[i]] = s.charAt(i);
            }
        }
        return new String(wordArray);
    }


    /*Easy* Implement strStr().
     Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.*/
     public int strStr(String haystack, String needle) {
        if(needle.equals("")) return 0;
        int hayL = haystack.length(), needleL = needle.length();
        for(int i=0; i<=hayL-needleL; i++){
            if(haystack.substring(i,i+needleL).equals(needle))
                return i;
        }

        return -1;
    }


    /*Easy: Balanced strings are those who have equal quantity of 'L' and 'R' characters.
      Given a balanced string s split it in the maximum amount of balanced strings.
      Return the maximum amount of splitted balanced strings. */
    public int balancedStringSplit(String s) {
        int ret = 0;
        int count = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == 'R'){
                count++;
            }else{
                count--;
            }
            if(count == 0)
                ret++;
        }
        return ret;
    }
    

    /*Given a string s. You should re-order the string using the following algorithm:
     1.Pick the smallest character from s and append it to the result.
     2.Pick the smallest character from s which is greater than the last appended character to the result and append it.
     3.Repeat step 2 until you cannot pick more characters.
     4.Pick the largest character from s and append it to the result.
     5.Pick the largest character from s which is smaller than the last appended character to the result and append it.
     6.Repeat step 5 until you cannot pick more characters.
     7.Repeat the steps from 1 to 6 until you pick all characters from s.*/
    public String sortString(String s) {
        String ret="";
        int[] chars = new int[26];
        int counter = s.length();
        for(int i = 0; i<s.length(); i++){
            chars[s.charAt(i) - 'a']++;
        }
        while(counter != 0){
            int index = 0;
            while(index < chars.length && counter != 0){
                if(chars[index] != 0){
                    char add = (char) (index + 'a');
                    ret += add;
                    chars[index]--;
                    counter--;
                }
                index++;
            }
            index = chars.length-1;
            while(index >= 0 && counter != 0){
                if(chars[index] != 0){
                    char add = (char) (index + 'a');
                    ret += add;
                    chars[index]--;
                    counter--;   
                }
                index--;
            }
        }
        return ret;
    }

    /*Easy but tricky: Given a string s1 and a string s2, write a snippet to say whether s2 is a rotation of s1?
     (eg given s1 = ABCD and s2 = CDAB, return true, given s1 = ABCD, and s2 = ACBD , return false) */
    public  boolean areRotations(String str1, String str2) { 
        // There lengths must be same and str2 must be  
        // a substring of str1 concatenated with str1.   
        return (str1.length() == str2.length()) && 
               ((str1 + str1).indexOf(str2) != -1); 
    } 


    /*Given a string s, find the length of the longest substring without repeating characters. */
    public int lengthOfLongestSubstring(String s) {
        //implmenting a sliding window 
        int[] c = new int[256];
        if(s.length() == 0) return 0;
        int ans = 0, i= 0, j = 1;
        ans++;
        c[s.charAt(0)]++;
        while(i<s.length()-1 && j<s.length()){
            if(c[s.charAt(j)] == 0){
                ans = Math.max(ans, j-i+1);
                c[s.charAt(j)]++;
                j++;
            }
            else {
                c[s.charAt(i)]--;
                i++;
            }
        }
        return ans;
    }
    public int lengthOfLongestSubstring2(String s) {
        //here we use the int array to also tell the index of the char and not only if its flagged.
        int n = s.length(), ans = 0;
        int[] index = new int[128]; // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    } 
    
}