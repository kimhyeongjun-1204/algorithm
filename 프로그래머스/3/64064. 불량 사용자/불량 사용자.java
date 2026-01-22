import java.util.*; 

class Solution {
    List<TreeSet<Integer>> list;  
    HashSet<String> sum = new HashSet<>(); 
    int answer = 0; 
    int ban_len; 
    
    public int solution(String[] user_id, String[] banned_id) {
        ban_len = banned_id.length;
        // 밴 가능성 여부 체크
        list = new ArrayList<>();
        for(int i=0;i<ban_len;i++) {
            list.add(new TreeSet<>()); 
        }
        
        int b = 0; 
        for(String ban : banned_id) {
            int u=0; 
            for(String user : user_id) {
                if(ban.length() != user.length()) {
                    u++;
                    continue; 
                }
                boolean isBan = true; 
                for(int i=0;i<user.length();i++) {
                    if(ban.charAt(i) == '*') continue; 
                    if(ban.charAt(i) != user.charAt(i)) {
                        isBan = false; 
                        break; 
                    }
                }
                if(isBan) {
                    list.get(b).add(u); 
                }
                u++;
            }
            b++; 
        }
        
        dfs(0, new TreeSet<Integer>()); 
        
        for(int i=0;i<ban_len;i++) {
            
            Iterator<Integer> iter = list.get(i).iterator(); 
            while(iter.hasNext()) {
                Integer element = iter.next(); // 다음 데이터를 가져오고 포인터를 이동
            }
        }
            
        
        return sum.size();
    }
    
    public void dfs(int index, TreeSet<Integer> set) {
        if(index == ban_len) { 
            sum.add(Arrays.toString(set.toArray()));    
            return; 
        }
        
        for(Integer ban : list.get(index)) {
            if(!set.contains(ban)) {
                set.add(ban); 
                dfs(index+1, set); 
                set.remove(ban); 
            }
        }
    }
}