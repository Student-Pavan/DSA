class Solution {
    public String frequencySort(String s) {
        int[] freq = new int[256];

        
        for (char c : s.toCharArray()) {
            freq[c]++;
        }
        
        StringBuilder result = new StringBuilder();

        for(int f = 1 ; f <= s.length(); f++){
            for(char c = 0; c < 256; c++){
                if(freq[c] == f){
                    for(int j  = 0 ; j < f ; j++){
                        result.append(c);
                    }
                }
            }
        }

        return result.reverse().toString();
    }
}