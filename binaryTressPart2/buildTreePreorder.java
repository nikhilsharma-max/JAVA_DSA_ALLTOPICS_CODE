import  java.util.*;
public class buildTreePreorder{

    //Class for the question of diameter of a tree
    public static class Info{
        int dia;
        int ht;
        public Info(int dia,int ht){
            this.dia = dia;
            this.ht = ht;
        }
    }

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


        //PreOrder traversal of binary tree
        public static void PreOrder(Node root){
            if(root==null){
                System.out.print(" -1 ");
                return;
            }
            System.out.print(" "+root.data+" ");
            PreOrder(root.left);
            PreOrder(root.right);
        }


        //LevelOrder traversal of a binary tree
        public static void levelOrder(Node root){
            Queue<Node> q = new LinkedList<>();
            q.add(root);
            q.add(null);

            while(!q.isEmpty()){
                Node curr = q.remove();
                if(curr==null){
                    if(q.isEmpty()){
                        break;
                    }else{
                        System.out.println();
                        q.add(null);
                    }
                }else{
                    System.out.print(curr.data+" ");
                    if(curr.left!=null){
                        q.add(curr.left);
                    }
                    if(curr.right!=null){
                        q.add(curr.right);
                    }
                }
            }
        }


        //Height of a binary tree
        public static int height(Node root){
            if(root==null)return 0;
            int h1 = height(root.left);
            int h2 = height(root.right);
            return Math.max(h1, h2)+1;
        }

        //Count number of nodes in a tree
        public static int countNodes(Node root){
            if(root==null)return 0;
            int leftNode = countNodes(root.left);
            int rightNode = countNodes(root.right);
            return leftNode+rightNode+1;
        }

        //sum of node values
        public static int sumOfNodeValues(Node root){
            if(root==null)return 0;
            int sumLeft = sumOfNodeValues(root.left);
            int sumRight = sumOfNodeValues(root.right);
            return sumLeft+sumLeft+root.data;
        }


        //Diameter of a tree O(n*n)
        public static int diameter(Node root){
            if(root==null)return 0;
            int leftDia = diameter(root.left);
            int leftHeight = height(root.left);
            int rightDia = diameter(root.right);
            int rightHeight = height(root.right);
            int selfDia = leftHeight+rightHeight+1;
            return Math.max(Math.max(rightDia, leftDia), selfDia);
        }


        //Calculating diameter in O(n)
        public static Info diameterTwo(Node root){
            if(root == null){
                return new Info(0,0);
            }
            Info leftInfo = diameterTwo(root.left);
            Info rightInfo = diameterTwo(root.right);
            int dia = Math.max(Math.max(leftInfo.dia,rightInfo.dia),rightInfo.ht+1);
            int ht = Math.max(leftInfo.ht, rightInfo.ht)+1;
            return new Info(dia, ht);
        }
    }

    public static void main(String[] args) {
        int nodes[] = {1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
        BinaryTree tree = new BinaryTree();
        Node root = tree.buildTree(nodes);
        tree.PreOrder(root);
        System.out.println();
        System.out.println();
        System.out.println("Level Order traversal");
        tree.levelOrder(root);
        System.out.println();
        System.out.println();
        System.out.println("Height of tree");
        System.out.println(tree.height(root));
        System.out.println();
        System.out.println("Number of nodes in binary tree");
        System.out.println(tree.countNodes(root));
        System.out.println();
        System.out.println("Sum of node values of the binary tree");
        System.out.println(tree.sumOfNodeValues(root));
        System.out.println();
        System.out.println("Diameter of the tree");
        System.out.println(tree.diameter(root));
    }
}