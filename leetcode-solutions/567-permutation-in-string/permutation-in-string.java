class Solution {
    public boolean checkInclusion(String s1, String s2) {
        
        int freq1[] = new int[26];
        int freq2[] = new int[26];


        for(char c : s1.toCharArray()){
            freq1[c - 'a']++; 
        }

        int left = 0;

        for(int right = 0; right < s2.length(); right++){
            freq2[s2.charAt(right) - 'a']++;

            if(right - left + 1 > s1.length()){
                freq2[s2.charAt(left) - 'a']--;
                left++;
            }
            if(right - left + 1 == s1.length()){
                boolean ismatch = true;

                for( int i = 0; i < 26 ; i++){
                    if(freq1[i] != freq2[i]){
                        ismatch = false;
                        break;
                    }
                }
                if(ismatch) 
                    return true;
            }
        }
        return false;
    }
}