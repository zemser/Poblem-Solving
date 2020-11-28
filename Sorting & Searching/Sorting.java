public class Sorting {
    public static void main(String args[]) {
        System.out.println("---------------Start-----------------");
       // Sorting sortings = new Sorting();
        //int [] a = new int[]{4,5,6,7,0,2,1,3};
    
        
        /* Test Desired fucntion */
        
       

        System.out.println("---------------End-----------------");
    }

    /*Some Basic Sorting Algorithms */
    /*Merge Sort - O(nlogn) , space - O(n)*/
    void merge(int arr[], int l, int m, int r){
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;
        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];
        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];
        int i = 0, j = 0;
        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
    void sort(int arr[], int l, int r){
        if (l < r) {
            // Find the middle point
            int m = (l + r) / 2;
 
            // Sort first and second halves
            sort(arr, l, m);
            sort(arr, m + 1, r);
 
            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }


    /*Binary Search */
    int binarySearch(int arr[], int l, int r, int x) { 
        if (r >= l) { 
            int mid = l + (r - l) / 2; 
            if (arr[mid] == x) 
                return mid; 

            if (arr[mid] > x) 
                return binarySearch(arr, l, mid - 1, x); 

            return binarySearch(arr, mid + 1, r, x); 
        } 
        return -1; 
    }   


    /*You are given an integer array nums sorted in ascending order, and an integer target.
    Suppose that nums is rotated at some pivot unknown to you beforehand (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
    If target is found in the array return its index, otherwise, return -1 */
    public int search(int[] nums, int target) {
        return binaryHelp(nums,0 , nums.length, target);
    }
    public int binaryHelp(int[]arr, int l, int r, int x){
        if(l <= r){
            int mid = (l + r)/2;
            int midNum = arr[mid];
            if(midNum == x) return mid;
            if(arr[l] <= midNum){ // my left side is sorted
                if(x < arr[l] || x > midNum)
                    return binaryHelp(arr,mid+1,r,x);
                return binaryHelp(arr,l,mid-1,x);
            } else{ // my right side is sorted
                if(x < midNum || x > arr[r])
                    return binaryHelp(arr,l,mid-1,x);
                return binaryHelp(arr,mid+1,r,x);
            }
        }
        return -1;
    }
    /*Medium: Follow up - u r given now that the array contains duplicates. the above approach */
    public int search2(int[] nums, int target) {
        int start = 0, n = nums.length, end = n - 1;
        if (n == 0) return -1;
        while (start <= end){
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }	
            if (!isBinarySearchHelpful(nums, start, nums[mid])) {
                start++;
                continue;
           }
            boolean pivotArray = existsInFirst(nums, start, nums[mid]); // which array does pivot belong to.
            boolean targetArray = existsInFirst(nums, start, target);   // which array does target belong to.
            if (pivotArray ^ targetArray) { // If pivot and target exist in different sorted arrays, xor is true when both operands are distinct
                if (pivotArray) {
                    start = mid + 1; // pivot in the first, target in the second
                } else {
                    end = mid - 1; // target in the first, pivot in the second
                }
            } else { // If pivot and target exist in same sorted array
                if (nums[mid] < target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return -1;
    }
    private boolean isBinarySearchHelpful(int[] arr, int start, int element) {
        return arr[start] != element; // returns true if we can reduce the search space in current binary search space
    }
    private boolean existsInFirst(int[] arr, int start, int element) {
        return arr[start] <= element;  // returns true if element exists in first array, false if it exists in second
    }




}