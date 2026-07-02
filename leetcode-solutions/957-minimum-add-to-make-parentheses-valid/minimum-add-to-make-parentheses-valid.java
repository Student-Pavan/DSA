class Solution {
    public int minAddToMakeValid(String s) {
        Stack<Character> stack = new Stack();

        for(char ch : s.toCharArray()){
            if(ch == '(' || ch == '[' || ch == '{')
                stack.push(ch);
            else if(stack.isEmpty() || (ch == ')' && stack.peek() != '(')
                    || (ch == ']' && stack.peek() != '[')
                    || (ch == '}' && stack.peek() != '{'))
                stack.push(ch);
            else{
                if((ch == ')' && stack.peek() == '(') ||
                   (ch == ')' && stack.peek() == '(') ||
                   (ch == ')' && stack.peek() == '('))
                    stack.pop();
            }
        }
        return stack.size();
    }
}