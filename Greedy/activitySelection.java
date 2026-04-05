
import java.util.*;

public class activitySelection{
    public static void main(String[] args) {
        activitySelection();
        System.out.println();
        indianCoins();
        System.out.println();
        chocola();

    }

    public static void activitySelection(){
        int start[] = {0,1,3,5,5,8};
        int end[] = {6,2,4,7,9,9};
        int minAct = 1;
        int activity[][] = new int[start.length][3];
        for(int i = 0;i<end.length;i++){
            activity[i][0] = i;
            activity[i][1] = start[i];
            activity[i][2] = end[i];
        }
        Arrays.sort(activity,Comparator.comparing(o->o[2]));
        for(int i = 1;i<start.length;i++){
            if(activity[i-1][2]<=activity[i][1]){
                minAct++;
            }
        }
        System.out.println("Minimum activity to be selected are : "+minAct);
    }
    public static void indianCoins(){
        int coins[] = {1,2,5,10,20,50,100,500,2000};
        Arrays.sort(coins);
        int val = 590;
        int reqCoin = 0;
        for(int i = coins.length-1;i>=0;i--){
            if(val>=coins[i]){
                while(val>=coins[i]){
                    val-=coins[i];
                    reqCoin++;
                }
            }
        }
        System.out.println("Minimum number of coins required are : "+reqCoin);
    }

    public static void chocola(){
        int n = 4, m = 6;
        Integer costVer[] = {2,1,3,1,4};
        Integer costHor[] = {4,1,2};
        Arrays.sort(costHor,Collections.reverseOrder());
        Arrays.sort(costVer,Collections.reverseOrder());

        int h = 0;
        int v = 0;
        int hp = 1,vp = 1;
        int cost = 0;
        while(h<costHor.length && v< costVer.length){
            //vertical cost
            if(costVer[v]<=costHor[h]){
                //horizontal cut
                cost += (costHor[h]*vp);
                hp++;
                h++;
            }else{
                cost+= costVer[v]*hp;
                vp++;
                v++;
            }
        }
        while(h<costHor.length){
                //horizontal cut
                cost += (costHor[h]*vp);
                hp++;
                h++;            
        }
        while(v<costVer.length){
                cost+= costVer[v]*hp;
                vp++;
                v++;            
        }
        System.out.println("Final cost to cut is : "+cost);
    }
}