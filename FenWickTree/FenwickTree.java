package FenWickTree;

public class FenwickTree {
    int N = 16;
    int fen[];
    public FenwickTree(){
        fen = new int[N];
    }
    void update(int i,int val){
        while(i<N){
            fen[i]+=val;
            i = (i+(i&(-i)));
        }
    }

    int sum(int i){
        int s = 0;
        while(i>0){
            s+=fen[i];
            i = (i-(i&(-i)));
        }
        return s;
    }

    int rangeSum(int l,int r){
        return sum(r)-sum(l-1);
    }
}
