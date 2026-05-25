class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
        int start = 0, end = 0, len = s.length();
        if(len == 0 || s.charAt(0) == '1' || s.charAt(len-1) == '1') {
            return false;
        }
        boolean[] dp = new boolean[len];
        dp[0] = true;

        for(int i = 0; i < len; i++) {
            if(!dp[i]) {
                continue;
            }

            start = Math.max(end + 1, i + minJump);
            end = Math.min(len-1, i + maxJump);

            for(int j = start; j <= end; j++) {
                if(s.charAt(j) == '0') {
                    dp[j] = true;
                }
            }
            if(dp[len-1]) {
                return true;
            }
        }
        return dp[len-1];
    }
}