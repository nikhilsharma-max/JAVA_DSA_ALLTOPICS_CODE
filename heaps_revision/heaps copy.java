
import java.util.*;


public class heaps {
    public static class dist implements Comparable<dist>{
        int x;
        int y;
        public dist(int x,int y){
            this.x = x;
            this.y = y;
        }
        @Override
        public int compareTo(dist d2){
            int disx = this.x*this.x-this.y*this.y;
            int disn = d2.x*d2.x-d2.y*d2.y;
            return disx-disn;
        }
    }
    public static void main(String[] args) {
        PriorityQueue<dist> d = new PriorityQueue<>();
        d.add(new dist(3, 3));
        d.add(new dist(-2, 4));
        d.add(new dist(5, -1));
        while(!d.isEmpty()){
            System.out.print(d.remove().x+" ");
        }

    }

}