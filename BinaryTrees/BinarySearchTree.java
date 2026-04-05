public class BinarySearchTree {
    static  class Node{
        int data;
        Node left;
        Node right;
        public Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public static Node insert(Node root,int val){
        if(root == null){
            return new Node(val);
        }
        if(root.data>val){
            root.left = insert(root.left, val);
        }
        if(root.data<val){
            root.right = insert(root.right,val);
        }
        return root;
    }

    //inorder
    public static void inOrder(Node root){
        if(root == null){
            return;
        }
        inOrder(root.left);
        System.out.print(root.data+" ");
        inOrder(root.right);
    }

    //Search in a BST
    public static Node searchBST(Node root, int val){
        if(root==null){
            return null;
        }
        if(root.data==val){
            return root;
        }
        if(root.data<val){
            searchBST(root.left, val);
        }else{
            searchBST(root.right, val);
        }
        return root;
    }

    //Delete Node in a BST
    public static Node delete(Node root,int val){
        if(root.data<val){
            root.right = delete(root.right,val);
        }
        else if(root.data>val){
            root.left = delete(root.left, val);
        }
        else{
            //Current root is the root to be deleted
            //single child
            if(root.left==null && root.right== null){
                return null;
            }else if(root.left == null){//case two
                return root.right;
            }else if(root.right==null){
                return root.left;
            }//case three
            Node IS = findInorderSuccessor(root.right);
            root.data = IS.data;
            root.right = delete(root.right, IS.data);
        }
        return root;
    }

    //IOS(Inorder successor)
    public static Node findInorderSuccessor(Node root){
        while(root!=null){
            root = root.left;
        }
        return root;
    }

    //Mirror a BST
    public static Node mirrorBST(Node root){
        if(root == null){
            return null;
        }
        Node leftMirror = mirrorBST(root.left);
        Node rightMirror = mirrorBST(root.right);
        root.left = rightMirror;
        root.right = leftMirror;
        return root;
    }



    //Print in range
    public static void printInRange(Node root, int k1, int k2){
        if(root == null){
            return;
        }
        if(root.data<=k2 && root.data>=k1){
            System.out.print(root.data+" ");
            printInRange(root.left, k1, k2);
            printInRange(root.right, k1, k2);
        }else if(root.data>k2){
            printInRange(root.left, k1, k2);
        }else{
            printInRange(root.right, k1, k2);
        }

    }

    public static void main(String[] args) {
        int values[] = {8,5,3,1,4,6,10,11,14};  
        Node root = null;
        for(int i = 0;i<values.length;i++){
            root = insert(root, values[i]);
        }
        inOrder(root);
        Node find = searchBST(root, 5);
        System.out.println();
        System.out.print(find.data);
        System.out.println();
        printInRange(root, 5, 12);
        System.out.println();
        System.out.println("-----InOrder-----");
        inOrder(root);
        System.out.println();
        System.out.print("-----Mirro BST------");
        mirrorBST(root);
        System.out.println();
        inOrder(root);
         
    }
}
