class Solution {
    public boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            int frontchar = s.charAt(left);
            int rightchar = s.charAt(right);

            if (frontchar != rightchar)
                return palindrome(s, left + 1, right) ||
                       palindrome(s, left, right - 1);

            left++;
            right--;
        }
        return true;
    }

    private boolean palindrome(String s, int left, int right) {
        while (left < right) {
            int frontchar = s.charAt(left);
            int rightchar = s.charAt(right);

            if (frontchar != rightchar)
                return false;

            left++;
            right--;
        }
        return true;
    }
}