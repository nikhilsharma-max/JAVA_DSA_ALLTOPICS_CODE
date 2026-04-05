import java.util.*;
public class connectNropes {
    public static class length implements Comparable<length>{
        int len;
        public length(int len){
            this.len = len;
        }
        public int compareTo(length l2){
            return this.len-l2.len;
        }
    }
    public static void main(String[] args) {
        PriorityQueue<length> q = new PriorityQueue<>();
        int ropes[] = {4,3,1,6};
        for(int i = 0;i<ropes.length;i++){
            q.add(new length(ropes[i]));
        }
        int cost = 0;
        while(!q.isEmpty()){
            length num1 = q.remove();
            length num2 = new length(0);
            if(!q.isEmpty()){
                 num2 = q.remove();
            }else{
                break;
            }
            
            int sum = num1.len+num2.len;
            cost+=sum;
            q.add(new length(sum));
        }
        System.out.print("Final cost is :"+cost);
    }
}
