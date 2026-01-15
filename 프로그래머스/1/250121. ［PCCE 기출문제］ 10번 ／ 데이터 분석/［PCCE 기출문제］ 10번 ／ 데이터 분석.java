import java.util.*;

class Solution {
    public ArrayList<int[]> solution(int[][] data, String ext, int val_ext, String sort_by) {
        ArrayList<int[]> answer = new ArrayList<>();
        
        // code date maximum remain 
        
        // ext : 어떤 정보를 기준으로 뽑을지 | val_ext : 뽑아낼 정보의 기준값 | sort_by : 해당 값을 기준으로 오름차순 정렬 

        // 열 인덱스 찾기 
        HashMap<String , Integer> index = new HashMap<>(); 
        index.put("code",0);
        index.put("date",1);
        index.put("maximum",2);
        index.put("remain",3);
        
        int indExt = index.get(ext), 
            indSort = index.get(sort_by); 
        
//      sort_by값 : Row 값   
        HashMap <Integer, Integer> rows = new HashMap<>(); 
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // sort 최솟값 뽑기 
        for(int i=0;i<data.length;i++) {
            if(data[i][indExt] < val_ext) {
                 rows.put(data[i][indSort] , i);
                 pq.add(data[i][indSort]); 
            }
        }
        
        while (!pq.isEmpty()) {
            int row = rows.get(pq.poll());
            answer.add(data[row]); 
        }
        
        return answer;
    }
}