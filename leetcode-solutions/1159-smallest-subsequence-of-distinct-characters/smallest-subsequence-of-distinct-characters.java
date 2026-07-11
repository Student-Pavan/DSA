class Solution {
    public String smallestSubsequence(String s) {
        // 1 : mark the lastindex of the each char in string
        // 2 : check and make the visited array as true 
        // 3 : delete the element from the builder
        // 4 : insert i  sb
        // retutn sb


        boolean visited[] = new boolean[26];
        int lastindex[] = new int[26];

        for(int i = 0; i < s.length(); i++ )
            lastindex[s.charAt(i) - 'a'] = i;

        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < s.length(); i++){
            
            char ch = s.charAt(i);

            if(visited[ch- 'a'])
                continue;

            while(!sb.isEmpty() && sb.charAt(sb.length() - 1) > ch  && lastindex[sb.charAt(sb.length() - 1) - 'a'] > i){
                visited[sb.charAt(sb.length() - 1) - 'a'] = false;
                sb.deleteCharAt(sb.length() - 1);                      
            }

            sb.append(s.charAt(i));
            visited[s.charAt(i) - 'a'] = true;

        }
        return sb.toString();
    }
}