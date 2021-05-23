import java.util.HashMap;

class Solution {
    
    private HashMap<Integer, Integer> numsIndex;
    
    public int[] twoSum(int[] nums, int target) {
        
        this.fullFillMap(nums);

        for (int firstIdx = 0; firstIdx < nums.length; firstIdx++) {

            if (this.numsIndex.containsKey(target - nums[firstIdx])) {

                int secondIdx = this.numsIndex.get(target - nums[firstIdx]);
                if (secondIdx != firstIdx) {
                    return new int[]{firstIdx, secondIdx};
                }
            }
        }

        return null;
        
    }
    
    private void fullFillMap(int[] nums) {
        this.numsIndex = new HashMap<>();
        
        for (int idx = 0; idx < nums.length; idx++) {
            this.numsIndex.put(nums[idx], idx);
        }
    }
}