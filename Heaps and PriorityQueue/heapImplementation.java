import java.util.*;
public class heapImplementation {

    //Heap class
    public static class Heap{
        
        ArrayList<Integer> arr = new ArrayList<>();

        //to add a value in a heap
        public void add(int data){
            //add at last index
            arr.add(data);
            int x = arr.size()-1;//index of child
            int par = (x-1)/2;//index of parent;
            while(arr.get(x)<arr.get(par) && par>=0){
                //swap
                int temp = arr.get(x);
                arr.set(x, arr.get(par));
                arr.set(par, temp);
                x = par;
                par = (x-1)/2;
            }
        }

        //to peek a value from a heap
        public int peek(){
            return arr.get(0);
        }
    
        //to delete value from a minHeap
        private void heapify(int i){

            int minIdx = i;
            int left = 2*i+1;
            int right = 2*i+2;

            if(left<arr.size() && arr.get(left)<arr.get(minIdx)){
                minIdx = left;
            }
            if(right<arr.size() && arr.get(minIdx)>arr.get(right)){
                minIdx = right;
            }
            if(minIdx!=i){
                //swap
                int temp = arr.get(i);
                arr.set(i, arr.get(minIdx));
                arr.set(minIdx, temp);
                heapify(minIdx);
            }
            
        }

        public int remove(){
            int data = arr.get(0);

            //step1 - swap first and last node
            int temp = arr.get(0);
            arr.set(0,arr.get(arr.size()-1));
            arr.set(arr.size()-1,temp);

            //step2 - delete last node
            arr.remove(arr.size()-1);
            //step3 - ca  ll heapify
            heapify(0);
            return data;
        }

        //isEmpty()
        public boolean isEmpty(){
            return arr.size()==0;
        }
    }

    public static void main(String[] args) {
        Heap h = new Heap();
        h.add(54);
        h.add(2);
        h.add(453);
        while(!h.isEmpty()){
            System.out.print(h.remove()+" ");
        }
    }


}
