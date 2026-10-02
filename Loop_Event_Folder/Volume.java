public class Volume {

    public class Vol{
        int ht;
        int wt;
        int lb;
        public Vol(int lb,int wt,int ht){
            this.ht = ht;
            this.wt = wt;
            this.lb = lb;
        }
        
        public int getVolume(){
            return this.ht*this.lb*this.wt;
            // return ht*lb*wt;
        }
    }
    public void main(String[] args) {
        Vol vol = new Vol(2, 3, 6);
        System.out.println(vol.getVolume());
    }
}
