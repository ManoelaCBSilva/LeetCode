import java.util.HashMap;
import java.util.Objects;

public class Solution {
    public int lengthOfLongestSubstring(String s) {

        if (Objects.isNull(s) || s.length() == 0) {
            return 0;
        }

        HashMap<Character, Integer> lastOccurrence = new HashMap<>();

        int longestSubstringLength = 1;
        int startCurrentSubstring = 0;

        lastOccurrence.put(s.charAt(0), 0);

        int idx;
        for (idx = 1; idx < s.length(); idx++) {
            if (lastOccurrence.containsKey(s.charAt(idx))) {

                // If the occurrence is after the beginning of the current substring
                if (lastOccurrence.get(s.charAt(idx)) >= startCurrentSubstring) {

                    // Needs to check if the current substring is longer than the longest one
                    if (idx - startCurrentSubstring > longestSubstringLength) {
                        longestSubstringLength = idx - startCurrentSubstring;
                    }

                    startCurrentSubstring = lastOccurrence.get(s.charAt(idx)) + 1;
                }
            }

            lastOccurrence.put(s.charAt(idx), idx);
        }

        if (idx - startCurrentSubstring > longestSubstringLength) {
            return idx - startCurrentSubstring;
        }

        return longestSubstringLength;
    }

}