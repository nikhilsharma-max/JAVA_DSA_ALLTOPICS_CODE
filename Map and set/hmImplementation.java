import java.util.*;
public class hmImplementation{

    static class HashMap<K,V>{//generics=mtlb
    //  jb is class ka object banaige to isme 
    // 2 parameters aayege (K,V) or ye kuch 
    // bhi ho skte h like boolean, float,
    //  string, etc.
        private class Node{
            K key;
            V value;
            public Node(K key, V value){
                this.key = key;
                this.value = value;
            }
        }
        private int n;//n
        private int N;
        private LinkedList<Node> buckets[];//Array of linkedlist of type node
        @SuppressWarnings("unchecked")
        public HashMap(){//constructor of this class
            this.N = 4;
            this.buckets = new LinkedList[4];
            for(int i= 0;i<4;i++){
                buckets[i] = new LinkedList<>();
            }
        } 

        private int hashFunction(K key){
            int hc = key.hashCode();
            return Math.abs(hc)%N;
        }
        private int SearchInLL(K key,int bi){
            LinkedList<Node> ll = buckets[bi];//bi index
            //  pr jo LinkedList hai use lelo
            int di = 0;
            for(int i =0;i<ll.size();i++){
                Node node = ll.get(i);
                if(node.key==key){
                    return di;
                }
                di++;
            }
            return -1;

        }
        @SuppressWarnings("unchecked") 
        private void reHash(){
            //current data ko khi store krvaige pehle
            LinkedList<Node> oldBucket[] = buckets;
            buckets = new LinkedList[N*2];
            N = N*2;
            for(int i =0;i<buckets.length;i++){
                buckets[i] = new LinkedList<>();
            }
            //oldBucket se ek ek krkr sare nodes nikal kr new bucket m put krdo
            for(int i = 0;i<oldBucket.length;i++){
                LinkedList<Node> ll = oldBucket[i];
                for(int j = 0;j<ll.size();j++){
                    Node node = ll.remove();
                    put(node.key,node.value);//is data ko bucket m put kr diya
                }
            }
        }

        public void put(K key, V value){ // O(lambda)--> O
            int bi = hashFunction(key);//bucket index
            int di = SearchInLL(key,bi);//data index at this bucket index
            if(di!=-1){
                Node node = buckets[bi].get(di);
                //bucket array ke bi index pr jo ll hai, uske ll ke di index valu node
                node.value = value;//node ki value ko new value se update kr diya
            }else{
                Node newNode = new Node(key,value);
                buckets[bi].add(newNode);

                n++;
            }
            double lambda = (double)n/N;
            if(lambda>2.0){
                reHash();
            }

        }
        //containskey
        public boolean containsKey(K key){
            int bi = hashFunction(key);//bucket index
            int di = SearchInLL(key,bi); 
            if(di==-1)return false;
            return true;    
        }

        public V get(K key){
            int bi = hashFunction(key);//bucket index
            int di = SearchInLL(key,bi);//data index at this bucket index
            if(di!=-1){
                Node node = buckets[bi].get(di);
                return node.value;
            }else{
               return null;
            }     
        }
        public V remove(K key){
            int bi = hashFunction(key);//bucket index
            int di = SearchInLL(key,bi);//data index at this bucket index
            if(di!=-1){
                Node node = buckets[bi].get(di);
                n--;
                return node.value;
            }else{
               return null;
            } 
        }

        public ArrayList <K> keySet(){
            ArrayList<K> keys = new ArrayList<>();
            for(int i = 0;i<buckets.length;i++){
                LinkedList<Node> ll = buckets[i];
                for(Node node : ll){
                    keys.add(node.key);
                }
            }
            return keys;
        }

        public boolean isEmpty(){
            return n==0;
        }

    }   

    public static void main(String[] args) {
        HashMap<String,Integer> hm = new HashMap<>();
        hm.put("India", 12312);
        hm.put("China",41324);
        hm.put("USA",1234);
        ArrayList<String> keys = hm.keySet();
        for(String key:keys){
            System.out.print(key+" ");
        }
    }

}