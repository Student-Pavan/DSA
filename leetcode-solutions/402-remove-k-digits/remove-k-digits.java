class Solution {
    public String removeKdigits(String num, int k) {
        // 1 insert as per checking top element
        // 2 check k 
        // 3 append to SB
        // 4 remove the trailling zeroes
        // 5 return the valid number

        Stack<Character> stack = new Stack<>();

        for (char ch : num.toCharArray()) {
            while (!stack.isEmpty() && k > 0 && stack.peek() > ch) {
                stack.pop();
                k--;
            }
            stack.push(ch);
        }

        while (k > 0) {
            stack.pop();
            k--;
        }

        StringBuilder sb = new StringBuilder();

        for (char ch : stack)
            sb.append(ch);

        while (sb.length() > 0 && sb.charAt(0) == '0')
            sb.deleteCharAt(0);

        return sb.length() == 0 ? "0" : sb.toString();
    }
}