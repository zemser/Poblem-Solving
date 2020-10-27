
//Given an array of integers nums.             ***Easy***

//A pair (i,j) is called good if nums[i] == nums[j] and i < j.

//Return the number of good pairs.

//Constraints:

//1 <= nums.length <= 100
//1 <= nums[i] <= 100

// Example:
// Input: nums = [1,2,3,1,1,3]
// Output: 4
// Explanation: There are 4 good pairs (0,3), (0,4), (3,4), (2,5) 0-indexed.
Let us first approach Brute Force.

int ans =0;
for(int i=0;i<nums.length;i++) {
	for(int j=i+1;j<nums.length;j++) {
		if(nums[j]==nums[i]) {
			ans++;
		}
	 }
}
return ans;
}

//Now as we see the constraint it is mentioned that 1<=nums[i]<=100, So to optimize our solution, let us have a frequency array for length 101 and then iterate nums array from left to right, and add if the frequency is greater than one add the frequency to the answer.

class Solution {
    public int numIdenticalPairs(int[] nums) {
        int arr[] = new int[101];
        int ans =0;
        for(int n:nums) {
            ans+=arr[n];
            arr[n]+=1;
        }
        return ans;
    }
}

//*יכולנו למיין את המערך ואז לרוץ עליו ועבור כל סדרה של איקס מספרים זהים להוסיף לתשובה 2 בחר איקס (כמות הזוגות שאפשר להרכיב מאיקס מספרים
