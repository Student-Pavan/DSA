class MinStack {
    Stack<Integer> stack;
    Stack<Integer> minstack;

    public MinStack() {
        stack = new Stack<>();
        minstack = new Stack<>();
    }

    public void push(int value) {
        stack.push(value);
        if (minstack.isEmpty())
            minstack.push(value);
        else
            minstack.push(Math.min(minstack.peek(), value));
    }

    public void pop() {
        if (stack.isEmpty())
            return;
        stack.pop();
        minstack.pop();
    }

    public int top() {
        if (stack.isEmpty())
            return -1;
        return stack.peek();

    }

    public int getMin() {
        if (stack.isEmpty())
            return -1;
        return minstack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(value);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */