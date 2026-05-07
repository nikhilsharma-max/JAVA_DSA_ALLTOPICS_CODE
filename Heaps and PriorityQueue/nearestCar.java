import java.util.*;
public class nearestCar {

    static class Coordinates implements Comparable<Coordinates>{
        int x;
        int y;
        String name;
        public Coordinates(String name,int x,int y){
            this.name = name;
            this.x = x;
            this.y = y;
        }
        public int compareTo(Coordinates c2){
            int a = this.x;
            int b = this.y;
            return a*a+b*b;
        }
    }
    public static void main(String[] args) {
        PriorityQueue<Coordinates> pq = new PriorityQueue<>();
        pq.add(new Coordinates("A",3, 3));
        pq.add(new Coordinates("B",5, -1));
        pq.add(new Coordinates("C",-2, 4));
        while(!pq.isEmpty()){
            System.out.print(pq.remove().name+" ");
        }

    }
}
