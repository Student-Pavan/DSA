class Solution {
    public int minLength(String s) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < s.length() ;i++){
            char ch = s.charAt(i);
            int len = sb.length();
            if(len > 0 && ((sb.charAt(len - 1) == 'A' && ch == 'B') || (sb.charAt(len - 1) == 'C' && ch == 'D'))) {
                sb.deleteCharAt(len - 1);
                continue;
            }
            sb.append(ch);
        }
        return sb.length();
    }
}