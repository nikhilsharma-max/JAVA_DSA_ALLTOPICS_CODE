public class trie {
    public static  class Node{
        Node children[] = new Node[26];
        boolean eow = false;
        public Node(){
            for(int i = 0;i<26;i++){
                children[i] = null;
            }
        }   
    }
    static Node root = new Node();
    public static void main(String[] args) {
        String words[] = {"the","a","there","their","any","thee"};
        for(int i = 0;i<words.length;i++){
            insert(words[i]);
        }
        System.out.println(search("ther"));
    }

    public static void insert(String word){
        Node curr = root;
        for(int i = 0;i<word.length();i++){
            int idx = word.charAt(i)-'a';
            if(curr.children[idx]==null){
                curr.children[idx] = new Node();
            }
            curr = curr.children[idx];
        }
        curr.eow = true;
    }

    public static boolean search(String word){
        Node curr = root;
        for(int i = 0;i<word.length();i++){
            int idx = word.charAt(i)-'a';
            if(curr.children[idx]==null)return false;
            curr = curr.children[idx];
        }
        if(curr.eow)return true;
        return false;
    }
}
