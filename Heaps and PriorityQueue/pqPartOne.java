import java.util.PriorityQueue;

public class pqPartOne{


    public static class Student implements Comparable<Student>{
        String name;
        int rank;
        public Student(String name, int rank){
            this.name = name;
            this.rank = rank;
        }
        @Override
        public int compareTo(Student s2){
            return this.rank-s2.rank;
        }
    }

    public static void checkPq(){
      PriorityQueue<Student> pq = new PriorityQueue<>();
      pq.add(new Student("A", 54));
      pq.add(new Student("B", 342));
      pq.add(new Student("C", 2));
      while(!pq.isEmpty()){
        System.out.print(pq.remove().name+" ");
      }
    }
   
   
    public static void main(String[] args){

    }
}