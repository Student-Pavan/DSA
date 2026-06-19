class StockSpanner {

    Stack<Integer> stack;
    ArrayList<Integer> list;

    public StockSpanner() {
        stack = new Stack();
        list = new ArrayList();
    }

    public int next(int price) {
        int i = list.size();
        list.add(price);

        while (!stack.isEmpty() && list.get(stack.peek()) <= price) {
            stack.pop();
        }
        int span = 1;
        if (stack.isEmpty())
            span = i + 1;
        else
            span = i - stack.peek();

        stack.push(i);
        return span;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */