
import java.util.ArrayList;

public class bst {

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
    //Creating bst
    public static Node insert(Node root,int val){
        if(root==null){
            root = new Node(val);
            return root;
        }
        if(root.data>val){
            root.left = insert(root.left, val);
        }else{
            root.right = insert(root.right, val);
        }
        return root;
    }

    //Inorder
    public static void  inOrder(Node root){
        if(root == null){
            return;
        }
        
        inOrder(root.left);
        System.out.print(" "+root.data+" ");
        inOrder(root.right);
        return;
    }

    //Search
    public static boolean search(Node root,int val){
        if(root==null)return false;
        if(root.data==val)return true;
        if(val<root.data){
            return search(root.left, val);
        }
        else{
           return search(root.right, val);
        }
    }

    
    //Delete a node in bst
    public static Node delete(Node root,int val){
        if(root.data<val){
            root.right = delete(root.right, val);
        }else if(root.data>val){
            root.left = delete(root.left, val);
        }else{
            //current node is to be deleted

            //case 1  leaf node
            if(root.left==null && root.right==null){
                return null;
            }

            //case 2 single child
            if(root.left==null){
                return root.right;
            }else if(root.right==null){
                return root.left;
            }

            //case 3 two child
            Node is = inOrderSucessor(root.right);
            root.data = is.data;
            root.right = delete(root.right,is.data);
        }
        return root;
    }

    private static Node inOrderSucessor(Node root){
        while(root.left!=null){
            root = root.left;
        }
        return root;
    }   

    //Print in range

    public static void printInRange(Node root,int k1, int k2){
        if(root==null)return;
        if(root.data>=k1 && root.data<=k2){
            printInRange(root.left, k1, k2);
            System.out.print(" "+root.data+" ");
            printInRange(root.right, k1, k2);
        }
        if(root.data<k1){
            printInRange(root.right, k1, k2);
        }
        else{
            printInRange(root.left, k1, k2);
        }

    }

    //Print root to leaf

    public static void rootToLeaf(Node root,ArrayList<Integer> path){
        if(root==null){
            return;
        }
        path.add(root.data);
        if(root.left==null && root.right==null){
            for(int i = 0;i<path.size();i++){
                System.out.print(path.get(i)+"-->");
            }
            System.out.println();
        }
        rootToLeaf(root.left, path);
        rootToLeaf(root.right, path);
        path.remove(path.size()-1);
    }
    
    
//validate bst
public static boolean validateBST(Node root,Node min,Node max){
    if(root==null)return true;
    if(min!=null && root.data <= min.data){
        return false;
    }else if(max!=null && root.data>=max.data){
        return false;
    }
    return validateBST(root.left, min, root) && validateBST(root.right, root, max);

}


//Mirror a bst
public static Node mirrorBST(Node root){
    if(root==null)return null;
    Node leftMirror = mirrorBST(root.left);
    Node rightMirror = mirrorBST(root.right);
    root.left = rightMirror;
    root.right = leftMirror;
    return root;
}
    public static void main(String[] args) {
        int values[] = {5,1,3,4,2,7};
        Node root = null;
        for(int i = 0;i<values.length;i++){
            root = insert(root, values[i]);
        }
        inOrder(root);
        System.out.println();
        System.out.println(search(root, 7));
        // delete(root, 5);
        // delete(root, 1);
        // delete(root, 3);
        // System.out.println();
        // inOrder(root);
        // System.out.println();
        System.out.println();
        System.out.println("Print in range");
        printInRange(root, 3, 7);
        System.out.println();
        System.out.println("Print path root to leaf");
        ArrayList<Integer> path = new ArrayList<>();
        rootToLeaf(root, path);
        System.out.println();
        System.out.println("Validate bst");
        System.out.println(validateBST(root, null, null));
    }


}