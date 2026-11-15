class Solution {
    public int reverse(int x) {
        String str = String.valueOf(x);
        long rem = 0;
        long rev = 0;
        long q = x;

        if (str.length() == 1) {
            return x;
        }

        if (x < 0) {
            for (int i = 1; i < str.length(); i++) {
                rem = q % 10;
                rev = rev * 10 + rem;
                q /= 10;
            }
        } else {
            for (int i = 0; i < str.length(); i++) {
                rem = q % 10;
                rev = rev * 10 + rem;
                q /= 10;
            }
        }

        if (rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE) {
            return 0;
        }

        return (int) rev;
    }
}
