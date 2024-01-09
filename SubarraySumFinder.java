import java.util.*;

public class SubarraySumFinder {
    public static void findSubarrays(int[] nums, int target) {
        Map<Integer, List<Integer>> prefixSumIndices = new HashMap<>();
        int currentSum = 0;
        List<String> results = new ArrayList<>();

        // Initialize with prefix sum 0 at index -1 to handle subarrays starting from index 0
        prefixSumIndices.put(0, new ArrayList<>(Arrays.asList(-1)));

        // Iterate through the array
        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i];

            // Check if (currentSum - target) exists in the map
            if (prefixSumIndices.containsKey(currentSum - target)) {
                for (int startIdx : prefixSumIndices.get(currentSum - target)) {
                    // Extract subarray
                    int[] subarray = Arrays.copyOfRange(nums, startIdx + 1, i + 1);
                    results.add(Arrays.toString(subarray));
                }
            }

            // Store currentSum with the current index
            prefixSumIndices.putIfAbsent(currentSum, new ArrayList<>());
            prefixSumIndices.get(currentSum).add(i);
        }

        // Print results
        if (results.isEmpty()) {
            System.out.println("No subarrays found.");
        } else {
            for (String result : results) {
                System.out.println(result);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int target = 3;

        System.out.println("Subarrays that sum to " + target + ":");
        findSubarrays(nums, target);
    }
}
