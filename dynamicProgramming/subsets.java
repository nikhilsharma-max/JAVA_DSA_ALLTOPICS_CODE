public class subsets {
    public static void main(String[] args) {
        String str = "abc";
        subSet(str, new String(), 0);
        System.out.println();
        permutations(str, new String());
    }

    //Subsets
    public static void subSet(String str,String ans,int i){
        if(i==str.length()){
            System.out.print(ans+"  ");
            return;
        }
        //yes choice
        subSet(str, ans+str.charAt(i), i+1);
        //No choice
        subSet(str, ans, i+1);
    }

    //Permutations
    public static void permutations(String str, String ans){
        if(str.length()==0){
            System.out.print(ans+"   ");
            return;
        }
        for(int i = 0;i<str.length();i++){
            char ch = str.charAt(i);
            String newString = str.substring(0,i)+str.substring(i+1);
            permutations(newString, ans+ch);
        }
    }
}
