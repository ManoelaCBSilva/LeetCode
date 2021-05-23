public class Solution {

    public boolean isMatch(String s, String p) {

        if (s.equals(p)) {
            return true;
        }

        if (p.isEmpty() && !s.isEmpty()) {
            return false;
        }

        char token = p.charAt(0);
        boolean firstCharacterIsAMatch = !s.isEmpty() && (token == s.charAt(0) || token == '.');

        if (p.length() >= 2 && p.charAt(1) == '*') {
            return isMatch(s, p.substring(2))
                    || (firstCharacterIsAMatch && isMatch(s.substring(1), p));
        }

        return firstCharacterIsAMatch && isMatch(s.substring(1), p.substring(1));
    }
}
