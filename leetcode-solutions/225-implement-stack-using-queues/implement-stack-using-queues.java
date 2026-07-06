class MyStack {
    Queue<Integer> queue1;
    Queue<Integer> queue2;
    int size;

    public MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
        size = 0;
    }

    public void push(int x) {
        queue1.add(x);
        while(!queue2.isEmpty())
            queue1.add(queue2.remove());
        
         Queue<Integer> temp = queue1;
         queue1 = queue2;
         queue2 = temp;
         size++;
         
    }

    public int pop() {
        if(size == 0)
            return -1;
        
        size--;
        return queue2.remove();
    }

    public int top() {
        if(size == 0)
            return -1;

        return queue2.element();
    }

    public boolean empty() {
        return size == 0;
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */