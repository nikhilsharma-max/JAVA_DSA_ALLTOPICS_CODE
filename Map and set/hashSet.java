
import java.util.*;

public class hashSet{

    public static void main(String[] args) {
        HashSet<Integer> hs = new HashSet<>();
        hs.add(1);
        hs.add(3);
        hs.add(2);
        hs.add(5);
        hs.add(-1);
        System.out.println(hs);
        Iterator it = hs.iterator();
        while(it.hasNext()){
            System.out.print(it.next()+"-->");
        }
        System.out.println("null");
    }
}