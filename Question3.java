import java.util.*;
public class Question3 {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        String  s =sc.nextLine();
    HashMap<Character,Integer> hs = new HashMap<>();
    for(char ch: s.toCharArray()){
        hs.put(ch,hs.getOrDefault(ch,0)+1);

    }

    for(char ch:hs.keySet()){
        System.out.println(ch+hs.get(ch));
    }
}
}
