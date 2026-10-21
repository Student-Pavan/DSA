class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        Stack<Integer> stack = new Stack();

        int[] res = new int[n];
        int prevtime = 0;
        for (String log : logs) {
            String[] parts = log.split(":");

            int funcid = Integer.parseInt(parts[0]);
            String op = parts[1];
            int time = Integer.parseInt(parts[2]);

            if (op.equals("start")) {
                if (!stack.isEmpty()) {
                    res[stack.peek()] += time - prevtime;
                }
                stack.push(funcid);
                prevtime = time;
            } else {
                res[stack.peek()] += time - prevtime + 1;
                stack.pop();
                prevtime = time + 1;
            }
        }
        return res;
    }
}