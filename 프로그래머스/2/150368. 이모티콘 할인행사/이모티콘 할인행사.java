import java.util.*; 

class Solution {
    int eLen; 
    ArrayList<String> eDiscount = new ArrayList<>(); 
    PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] != b[0] ? b[0] - a[0] : b[1] - a[1]); 
    
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = {};
        eLen = emoticons.length; 
        
        // 할인율 설정 
        HashMap<Character, Integer> rate = new HashMap<>(); 
        rate.put('0', 10); 
        rate.put('1', 20); 
        rate.put('2', 30); 
        rate.put('3', 40); 
        
        bfs(new StringBuilder()); 
        
        for(String eDis : eDiscount) {
            int totalPlus = 0, totalBuy = 0; 
            
            for(int[] user : users) {
                int sum = 0; 
                boolean buyPlus = false; 
                
                int uRate = user[0], 
                    uPrice = user[1]; 
                
                for(int i=0;i<eLen;i++) {
                    int ePrice = emoticons[i]; 
                    int eRate = rate.get(eDis.charAt(i)); 
                    int disPrice = ePrice * (100-eRate) / 100; 
                    
                    if(uRate <= eRate) {
                        sum += disPrice; 
                        if(sum >= uPrice) {
                            buyPlus = true; 
                            break; 
                        }
                    }
                }
                
                if(buyPlus) {
                    totalPlus++; 
                }else {
                    totalBuy += sum; 
                }
            }
            
            pq.add(new int[]{totalPlus, totalBuy}); 
        }
            
        return pq.poll();
    }

    void bfs(StringBuilder sb) {
        if(sb.length() >= eLen) {
            eDiscount.add(sb.toString()); 
            return; 
        }
            
        for(char c='0';c<='3';c++) {
            sb.append(c); 
            bfs(sb); 
            sb.deleteCharAt(sb.length()-1); 
        }
    }
}