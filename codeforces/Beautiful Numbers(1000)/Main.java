import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            String s = sc.next();
            System.out.println(solve(s));
        }
    }

    static int solve(String s) {
        int n = s.length();
        int sum = 0;
        int[] cnt = new int[10];

        for (char c : s.toCharArray()) {
            int d = c - '0';
            sum += d;
            cnt[d]++;
        }

        if (sum <= 9) return 0;          // already beautiful

        int need = sum - 9;              // how much we must reduce the sum
        int changes = 0;

        // Greedily reduce the largest digits first
        for (int d = 9; d >= 1 && need > 0; d--) {
            while (cnt[d] > 0 && need > 0) {
                int canReduce = d;       // normally we can turn d → 0

                // Special case: this is the leading digit and it is the only occurrence of d
                if (s.charAt(0) - '0' == d && cnt[d] == 1) {
                    canReduce = d - 1;   // can only turn it into 1..d (cannot become 0)
                }

                if (canReduce == 0) break;   // cannot reduce this digit further

                int take = Math.min(canReduce, need);
                need -= take;
                changes++;
                cnt[d]--;
            }
        }

        return changes;
    }
}