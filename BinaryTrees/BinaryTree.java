import java.util.*;

public class BinaryTree {
    static class Node{
        int data;
        Node left;
        Node right;
        public Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    //Top view of a tree
    public static class newInfo{
        int hd;
        Node node;
        public newInfo(int hd, Node next){
            this.hd = hd;
            this.node = node;
        }
    }

    public static void topView(Node root){
        Queue<newInfo> q = new LinkedList<>();
        Map<Integer,Node> map = new HashMap<>();
        q.add(new newInfo(0, root));
        q.add(null);
        int min = 0,max=0;
        while(!q.isEmpty()){
            newInfo curr = q.remove();
            if(curr == null){
                if(q.isEmpty()){
                    break;
                }else{
                q.add(null);
            }
            }else{
                if(!map.containsKey(curr.hd)){
                    map.put(curr.hd, curr.node);
                }
                if(curr.node.left!=null){
                    q.add(new newInfo(curr.hd-1,curr.node.left));
                    min = Math.min(min, curr.hd-1);
                }
                if(curr.node.right !=null){
                    q.add(new newInfo(curr.hd+1, curr.node.right));
                    max = Math.max(max,curr.hd+1);
                }
            }
        }
            for(int i = min;i<=max;i++){
                System.out.print(map.get(i)+" ");
            }

    }


    //Diameter of a tree using class Info
    public static class Info{
        int diam;
        int ht;
        public Info(int diam, int ht){
            this.diam = diam;
            this.ht = ht;
        }
    }
    public static Info diameter(Node root){
        if(root == null){
            return new Info(0, 0);
        }
        Info leftInfo = diameter(root.left);
        Info rightInfo = diameter(root.right);
        int diam = Math.max(leftInfo.diam, Math.max(rightInfo.diam, leftInfo.ht+rightInfo.ht+1));
        int height = Math.max(leftInfo.ht, rightInfo.ht)+1;
        return new Info(diam, height);
    }

    public static class Tree{
        //inOrder tree building
        static int idx = -1;
        public static Node buildTree(int arr[]){
            idx++;
            if(arr[idx]==-1){
                return null;
            }
            Node newNode = new Node(arr[idx]);
            newNode.left = buildTree(arr);
            newNode.right = buildTree(arr);
            return newNode;
        }
        //Transform to sum tree
        public static int sumTree(Node root){
            if(root == null){
                return 0;
            }
            int rootData = root.data;
            int left = sumTree(root.left);
            int right = sumTree(root.right);
            int newLeft = root.left==null?0:root.left.data;
            int newRight = root.right==null? 0:root.right.data;
            root.data = left+right+newLeft+newRight;
            return rootData;
        }

        //PreOrder traversal
        public static void PreOrderTraversal(Node root){
            if(root == null){
                System.out.print(-1+" ");
                return;
            }
            System.out.print(root.data+" ");
            PreOrderTraversal(root.left);
            PreOrderTraversal(root.right);
        }

        // Kth level of a tree
        public static void kLevel(Node root,int k,int level){
            if(root == null){
                return;
            }
            if(level==k){
                System.out.print(root.data+"  ");
            }
            kLevel(root.left, k, level+1);
            kLevel(root.right, k, level+1);

        }

        //Lowest common ancestor
        public static Node lca(Node root,int n1, int n2){
            if(root == null || root.data == n1 || root.data == n2){
                return root;
            }
            Node foundL = lca(root.left, n1, n2);
            Node foundR = lca(root.right, n1, n2);
            if(foundL==null){
                return foundR;
            }
            if(foundR==null){
                return foundL;
            }
            return root;
        }

        //kth Ancestor
        public static int KAncestor(Node root,int n,int k){
            if(root==null){
                return -1;
            }
            if(root.data == n){
                return 0;
            }
            int left = KAncestor(root.left, n, k);
            int right = KAncestor(root.right, n, k);
            if(left == -1 && right == -1){
                return -1;
            }
            int max = Math.max(left,right);
            if(max+1==k){
                System.out.println(root.data);
            }
            return root.data;
        }
        //Minimum distance between nodes
        public static int minDist(Node root, int n1,int n2){
            Node anc = lca(root, n1, n2);
            int l1 = minDistUtil(anc,n1);
            int l2 = minDistUtil(anc,n2);
            return l1+l2;
        }
        public static int minDistUtil(Node root,int n){
            if(root==null){
                return -1;
            }
            if(root.data == n){
                return 0;
            }
            int left = minDistUtil(root.left, n);
            int right = minDistUtil(root.right, n);
            if(left == -1 && right == -1){
                return -1;
            }else if(left == -1){return right+1;}else{return left+1;}

        }


        //height of a tree
        public static int height(Node root){
            if(root == null){
                return 0;
            }
            int lh = height(root.left);
            int rh = height(root.right);
            return Math.max(lh,rh) + 1;
        }

        //Diameter of a tree
        public static int diameter(Node root){
            if(root == null){
                return 0;
            }
            int ldiam = diameter(root.left);
            int rdiam = diameter(root.right);
            int lh = height(root.left);
            int rh = height(root.right);
            return Math.max(ldiam,Math.max(rdiam, rh+lh+1)); 
        }
        // Subtree of a binary tree
         public static boolean isSubtree(Node node, Node subRoot){
            if(node == null){
                return false;
            }
            if(node.data == subRoot.data){
                if(isIdentical(node,subRoot)){
                    return true;
                }
            }
            return isSubtree(node.right, subRoot)||isSubtree(node.left, subRoot);
         }
         public static  boolean isIdentical(Node root, Node subRoot){
            if(root == null && subRoot == null){
                return true;
            }
            else if(root == null || subRoot == null || root.data!=subRoot.data){
                return false;
            }
            if(!isIdentical(root.right, subRoot.right)){
                return false;
            }
            
             if(!isIdentical(root.left, subRoot.left)){
                return false;
            }
            return true;
         }
        
    }
    public static void main(String[] args) {
        Tree tree = new Tree();
        // int arr[] = {1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
        // Node root = tree.buildTree(arr);
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);


        // subtree
        Node subRoot = new Node(2);
        subRoot.left = new Node(4);
        subRoot.right = new Node(5);


        System.out.println(root.data);
        System.out.println(tree.height(root));
        System.out.println(tree.diameter(root));
        System.out.println(diameter(root).diam);
        System.out.println(diameter(root).ht);
        System.out.println(tree.isSubtree(root,subRoot));

        tree.kLevel(root, 3, 1);
        // topView(root);
        System.out.println();
        System.out.println(tree.lca(root, 4, 6).data);
        System.out.println(tree.minDist(root, 4, 6));
        System.out.println(tree.KAncestor(root, 5, 1));
        tree.sumTree(root);
        tree.PreOrderTraversal(root);

    }
}
