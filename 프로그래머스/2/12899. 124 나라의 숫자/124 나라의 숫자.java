class Solution {
    public String solution(int n) {
        String[] digits = {"4", "1", "2"};
        StringBuilder sb = new StringBuilder();
        
        while (n > 0) {
            int remainder = n % 3;
            sb.insert(0, digits[remainder]);
            n = (remainder == 0) ? n / 3 - 1 : n / 3;
        }
        
        // 10 
        
        return sb.toString();
    }
}