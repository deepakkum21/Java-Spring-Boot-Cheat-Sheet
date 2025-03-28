## maximum sum of all sub-arrays of size K - use QUEUE to store

        static int maxSum(int arr[], int n, int k) {
            // n must be greater
            if (n <= k) {
                System.out.println("Invalid");
                return -1;
            }

            // Compute sum of first window of size k
            int max_sum = 0;
            for (int i = 0; i < k; i++)
                max_sum += arr[i];

            // Compute sums of remaining windows by
            // removing first element of previous
            // window and adding last element of
            // current window.
            int window_sum = max_sum;
            for (int i = k; i < n; i++) {
                window_sum += arr[i] - arr[i - k];
                max_sum = Math.max(max_sum, window_sum);
            }

            return max_sum;
        }

## Smallest sub-array with sum greater than a given value - [Using Two Pointers]

        static int smallestSubWithSum(int x, int[] arr) {

            int i = 0, j = 0;
            int sum = 0;
            int ans = Integer.MAX_VALUE;

            while (j < arr.length) {

                // Expand window until sum > x
                // or end of array reached
                while (j < arr.length && sum <= x) {
                    sum += arr[j++];
                }

                // If we reached end of array and sum
                // still <= x, no valid subarray exists
                if (j == arr.length && sum <= x) break;

                // Minimize window from start
                // while maintaining sum > x
                while (i < j && sum - arr[i] > x) {
                    sum -= arr[i++];
                }

                ans = Math.min(ans, j - i);

                // Remove current start
                // element and shift window
                sum -= arr[i];
                i++;
            }
            if (ans == Integer.MAX_VALUE) return 0;
            return ans;
        }

## Subarray with Given Sum - K

        static ArrayList<Integer> subarraySum(int[] arr, int target) {
            // Initialize window
            int s = 0, e = 0;
            ArrayList<Integer> res = new ArrayList<>();

            int curr = 0;
            for (int i = 0; i < arr.length; i++) {
                curr += arr[i];

                // If current sum becomes more or equal,
                // set end and try adjusting start
                if (curr >= target) {
                    e = i;

                    // While current sum is greater,
                    // remove starting elements of current window
                    while (curr > target && s < e) {
                        curr -= arr[s];
                        ++s;
                    }

                    // If we found a subarray
                    if (curr == target) {
                        res.add(s + 1);
                        res.add(e + 1);
                        return res;
                    }
                }
            }
            // If no subarray is found
            res.add(-1);
            return res;
        }

## Maximum of all subarrays of size K - DEQUEUE

        static ArrayList<Integer> maxOfSubarrays(int[] arr, int k) {
            int n = arr.length;

            // to store the results
            ArrayList<Integer> res = new ArrayList<Integer>();

            // create deque to store max values
            Deque<Integer> dq = new ArrayDeque<Integer>();

            // Process first k (or first window) elements of array
            for (int i = 0; i < k; ++i) {

                // For every element, the previous smaller elements
                // are useless so remove them from dq
                while (!dq.isEmpty() && arr[i] >= arr[dq.peekLast()]) {

                    // Remove from rear
                    dq.pollLast();
                }

                // Add new element-index at rear of queue
                dq.addLast(i);
            }

            // Process rest of the elements, i.e., from arr[k] to arr[n-1]
            for (int i = k; i < arr.length; ++i) {

                // The element at the front of the queue is the largest
                // element of previous window, so store it
                res.add(arr[dq.peekFirst()]);

                // Remove the elements which are out of this window
                while (!dq.isEmpty() && dq.peekFirst() <= i - k) {

                    // Remove from front of queue
                    dq.pollFirst();
                }

                // Remove all elements smaller than the currently being
                // added element (remove useless elements)
                while (!dq.isEmpty() && arr[i] >= arr[dq.peekLast()]) {
                    dq.pollLast();
                }

                // Add current element at the rear of dq
                dq.addLast(i);
            }

            // store the maximum element-index of last window
            res.add(arr[dq.peekFirst()]);

            return res;
        }

## Longest Subarray With Sum K || Smallest Subarray with SUM k - [Hash Map and Prefix Sum]

        static int longestSubarray(int[] arr, int k) {
            Map<Integer, Integer> mp = new HashMap<>();
            int res = 0;
            int prefSum = 0;

            for (int i = 0; i < arr.length; ++i) {
                prefSum += arr[i];

                // Check if the entire prefix sums to k
                if (prefSum == k)
                    res = i + 1;

                // If prefixSum - k exists in the map then there exist such
                // subarray from (index of previous prefix + 1) to i.
                else if (mp.containsKey(prefSum - k))
                    res = Math.max(res, i - mp.get(prefSum - k));

                // Store only first occurrence index of prefSum
                if (!mp.containsKey(prefSum))
                    mp.put(prefSum, i);
            }

            return res;
        }

## Find maximum (or minimum) sum of a subarray of size k - QUEUE

    static int maxSum(int arr[], int n, int k)  {
        // k must be smaller than n
        if (n < k) {
            System.out.println("Invalid");
            return -1;
        }

        // Create Queue
        Queue<Integer> q = new LinkedList<Integer>();

        // Initialize maximum and current sum
        int m = Integer.MIN_VALUE;
        int su = 0;

        // Compute sum of first k elements
        // and also store them inside queue
        for (int i = 0; i < k; i++) {
            q.add(arr[i]);
            su += arr[i];
        }

        // Compute sum of remaining elements
        for (int i = k; i < n; i++) {
            m = Math.max(m, su);
            // remove first element from the queue
            su -= q.peek();
            q.remove();

            // add current element to the queue
            q.add(arr[i]);
            su += arr[i];

            // update maximum sum

        }
          m = Math.max(m, su);
        return m;
    }

## Longest Substring Without Repeating Characters

- examples

  Input: s = “geeksforgeeks”
  Output: 7
  Explanation: The longest substrings without repeating characters are “eksforg” and “ksforge”, with lengths of 7.

  Input: s = “aaa”
  Output: 1
  Explanation: The longest substring without repeating characters is “a”

  Input: s = “abcdefabcbb”
  Output: 6
  Explanation: The longest substring without repeating characters is “abcdef”.

            static int longestUniqueSubstr(String s) {
                int n = s.length();
                int res = 0;

                // last index of all characters is initialized as -1
                int[] lastIndex = new int[26];
                for (int i = 0; i < 26; i++) {
                    lastIndex[i] = -1;
                }

                // Initialize start of current window
                int start = 0;

                // Move end of current window
                for (int end = 0; end < n; end++) {

                    // Find the last index of s[end]
                    // Update starting index of current window as
                    // maximum of current value of end and last index + 1
                    start = Math.max(start, lastIndex[s.charAt(end) - 'a'] + 1);

                    // Update result if we get a larger window
                    res = Math.max(res, end - start + 1);

                    // Update last index of s[end]
                    lastIndex[s.charAt(end) - 'a'] = end;
                }
                return res;
            }

## First negative integer in every window of size k - [Dequeue]

            public static int[] firstNegInt(int[] arr, int k) {
                Deque<Integer> dq = new LinkedList<>();
                List<Integer> res = new ArrayList<>();
                int n = arr.length;

                // Process first k (or first window) elements
                for (int i = 0; i < k; i++)
                    if (arr[i] < 0)
                        dq.addLast(i);

                // Process rest of the elements, i.e.,
                // from arr[k] to arr[n-1]
                for (int i = k; i < n; i++) {
                    if (!dq.isEmpty())
                        res.add(arr[dq.peekFirst()]);
                    else
                        res.add(0);

                    // Remove the elements which are out of
                    // this window
                    while (!dq.isEmpty() && dq.peekFirst() < (i - k + 1))
                        dq.pollFirst();

                    // Add current element at the rear
                    // of dq if it is a negative integer
                    if (arr[i] < 0)
                        dq.addLast(i);
                }

                // Print the first negative integer of
                // the last window
                if (!dq.isEmpty())
                    res.add(arr[dq.peekFirst()]);
                else
                    res.add(0);

                return res.stream().mapToInt(i -> i).toArray();
            }

## Count Distinct Elements In Every Window of Size K

    static List<Integer> countDistinct(int[] arr, int k) {
        int n = arr.length;
        ArrayList<Integer> res = new ArrayList<>();
        Map<Integer, Integer> freq = new HashMap<>();

        // Store the frequency of elements of the first window
        for (int i = 0; i < k; i++) {
            freq.put(arr[i], freq.getOrDefault(arr[i], 0) + 1);
        }

        // Store the count of distinct elements of the first window
        res.add(freq.size());

        for (int i = k; i < n; i++) {
            freq.put(arr[i], freq.getOrDefault(arr[i], 0) + 1);
            freq.put(arr[i - k], freq.get(arr[i - k]) - 1);

            // If the frequency of arr[i - k] becomes 0,
            // remove it from the hash map
            if (freq.get(arr[i - k]) == 0) {
                freq.remove(arr[i - k]);
            }

            res.add(freq.size());
        }

        return res;
    }
