import java.util.*;
public class subTress {

    public static class Node{
        int data;
        Node left;
        Node right;
        public Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    //Top view of a binary tree
    public static class Info{
        Node node;
        int hd;
        public Info(Node node,int hd){
            this.node = node;
            this.hd = hd;
        }
    }



    public static class BinaryTree{
        //Building tree 
        static int idx = -1;
        public static Node buildTree(int nodes[]){
            idx++;
            if(nodes[idx]==-1){
                return null;
            }
            Node newNode = new Node(nodes[idx]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);
            return newNode;
        }


    }

    public static boolean isSubtree(Node root,Node subRoot){
        if(root==null)return false;
        if(root.data == subRoot.data){
            if(isIdentical(root,subRoot)){
                return true;
            }
        }
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private static boolean isIdentical(Node root,Node subRoot){
        if(root==null && subRoot==null)return true;
        if(root==null || subRoot==null || root.data!=subRoot.data){
            return false;
        }
        if(!isIdentical(root.left, subRoot.left))return false;
        if(!isIdentical(root.right, subRoot.right))return false;
        return true;
    }

    //Top view
    public static void topView(Node root){
        //level order
        Queue<Info> q = new LinkedList<>();
        HashMap<Integer,Node> map = new HashMap<>();
        int min = 0,max = 0;
        q.add(new Info(root, 0));
        q.add(null);
        while(!q.isEmpty()){
            Info curr = q.remove();
            if(curr==null){
                if(q.isEmpty()){
                    break;
                }else{
                    q.add(null);
                }
            }else{
                if(!map.containsKey(curr.hd)){
                    //first time the horizontal distance is occuring
                    map.put(curr.hd, curr.node);
                }
                //  map.put(curr.hd, curr.node);
                if(curr.node.left!=null){
                    q.add(new Info(curr.node.left,curr.hd-1));
                    min = Math.min(min,curr.hd-1);
                }
                if(curr.node.right!=null){
                    q.add(new Info(curr.node.right, curr.hd+1));
                    max = Math.max(max,curr.hd+1);
                }
            }
        }

        for(int i = min;i<=max;i++){
            System.out.print(map.get(i).data+"  ");
        }
    }

    //Kth ancestor
    public static void kAncestor(Node root,int level,int k){
        if(root==null)return;
        if(level == k){
            System.out.print(root.data+" ");
        }
        kAncestor(root.left, level+1, k);
        kAncestor(root.right, level+1, k);
    }

    //Lowest common ancestor
    public static Node lca(Node root,int n1,int n2){
        if(root == null || root.data == n1 || root.data == n2){
            return root;
        }
        Node leftLca = lca(root.left, n1, n2);
        Node rightLca = lca(root.right, n1, n2);

        if(leftLca==null)return rightLca;
        if(rightLca==null)return leftLca;

        return root;
    }

    //Minimun distance between two nodes
    public static int minDist(Node root,int n1,int n2){
        Node lca = lca(root,n1,n2);
        int leftDist = lcaDist(lca,n1);
        int rightDist = lcaDist(lca,n2);
        return leftDist+rightDist;
    }
    private static int lcaDist(Node root,int n){
        if(root==null)return -1;
        if(root.data==n)return 0;
        int ld = lcaDist(root.left, n);
        int rd = lcaDist(root.right, n);
        if(ld==-1 && rd==-1){
            return -1;
        }
        else if(ld==-1){
            return rd+1;
        }
            return ld+1;
    }

    public static int transformSumTree(Node root){
        if(root==null)return 0;
        int left = transformSumTree(root.left);
        int right = transformSumTree(root.right);
        int currData = root.data;
        root.data = left+right;
        return root.data+currData;
    }

    public static void preOrder(Node root){
        if(root == null){
            System.out.print(" -1 ");
            return;
        }
        System.out.print(" "+root.data+" ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void main(String[] args) {

            Node root = new Node(1);
            root.left = new Node(2);
            root.right = new Node(3);
            root.left.left = new Node(4);
            root.left.right = new Node(5);
            root.right.left = new Node(6);
            root.right.right = new Node(7);

            //Subtree
            Node subRoot = new Node(2);
            subRoot.left = new Node(4);
            subRoot.right = new Node(5);

            System.out.println(isSubtree(root, subRoot));
            System.out.println();
            System.out.println("Top view of a binary tree");
            topView(root);
            System.out.println();
            System.out.println("Kth ancestor");
            kAncestor(root, 1, 3);
            System.out.println();
            System.out.println("Lowest common ancestor");
            System.out.println(lca(root, 4, 6).data);
            System.out.println();
            System.out.println("LCA Distance");
            System.out.println(minDist(root, 4, 6));
            System.out.println();
            preOrder(root);
            System.out.println();
            transformSumTree(root);
            System.out.println();
            preOrder(root);

    }



}
