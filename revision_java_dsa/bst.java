import java.util.*;
public class bst{
  //building bst using preorder traversal
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
  static  class BinaryTree{
      static int idx = -1;
      public static Node buildTree(int[] nodes){
        idx++;
        if(nodes[idx]==-1){
          return null;
        }
        Node newNode = new Node(nodes[idx]);
        newNode.left = buildTree(nodes);
        newNode.right = buildTree(nodes);
        return  newNode;
      }

      public static void preOrder(Node root){
        if(root == null){
          System.out.print("-1-->");
          return;
        }
        System.out.print(root.data+"-->");
        preOrder(root.left);
        preOrder(root.right);
      }
            public static int height(Node root){
        if(root == null){
          return 0;
        }
        int lh = height(root.left);
        int rh = height(root.right);
        return  Math.max(rh, rh)+1;
      }
      //Level order travesal
      public static void levelOrder(Node root){
        if(root == null){
          return;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        while(!q.isEmpty()){
          Node currNode = q.remove();
          if(currNode==null){
            System.out.println();
            if(q.isEmpty()){
              break;
            }else{
              q.add(null);
            }
          }else{
          System.out.print(currNode.data+" ");
          if(currNode.left!=null){
            q.add(root.left);
          }
          if(currNode.right !=null){
            q.add(root.right);
          }

          }

        }
      }
      //count number of nodes
      public static int countNodes(Node root){
          if(root == null){
            return 0;
          }
          int lcount = countNodes(root.left);
          int rcount = countNodes(root.right);
          return lcount+rcount+1;
      }

      //sum of nodes
      public static int  sumNodes(Node root){
        if(root == null){
          return 0;
        }
        int lsum = sumNodes(root.left);
        int rsum = sumNodes(root.right);
        return lsum+rsum+root.data;

      }

  }
  public static void main(String[] args) {
    int[] nodes = {1,2,5,-1,-1,5,-1,-1,3,-1,6,-1,-1};
    BinaryTree tree = new BinaryTree();
    Node root = tree.buildTree(nodes);
    System.out.println(root.data);
    System.out.println(tree.sumNodes(root));
    System.out.println(tree.countNodes(root));
    System.out.println(tree.height(root));
    tree.preOrder(root);   
  }
}