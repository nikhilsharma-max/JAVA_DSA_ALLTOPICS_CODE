import java.util.Locale;

public class validPalindrome {
    public static void main(String[] args){
        String str = "P0";
        System.out.println(isPalindrome(str));
    }

    public static  boolean isPalindrome(String s) {
        if(s.length()==0 || s.length()==1){
            return true;
        }
       s = s.toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9]", "");
        StringBuilder inp = new StringBuilder("");
        for(int i = s.length()-1;i>=0;i--){
            char ch = s.charAt(i);
            if(ch>='a' && ch<='z'){
                inp.append(ch);
            }
        }
        
        StringBuilder res = new StringBuilder("");
        for(int i = 0;i<inp.length();i++){
            res.append(inp.charAt(i));
        }
        for(int i = 0;i<res.length();i++){
            if(res.charAt(i)!=s.charAt(i)){
                return false;
            }
        }
        return true;
    }
}
