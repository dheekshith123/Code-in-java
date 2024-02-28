import java.util.*;
public class tree {
    static class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data=data;
            this.left=null;
            this.right=null;
        }
    }
    public static class Binarytree{
        static int i=-1;  
        public static Node Buildtree(int nodes[]){
            i++;
            if(nodes[i]==-1){
                return null;
            }
            Node newnode = new Node(nodes[i]);
            newnode.left=Buildtree(nodes);
            newnode.right=Buildtree(nodes);

            return newnode;
        }
    }
    public static Node insert(Node root , int val){
        if(root== null){
            root= new Node(val);
            return root;
        }
        if(root.data>val){
            root.left=insert(root.left, val);
        }else{
            root.right=insert(root.right, val);
        }
        return root;
    }
    public static void preoredr(Node root){
        if(root == null){
            return ;
        }
        System.out.print(root.data+" ");
        preoredr(root.left);
        preoredr(root.right);
    }
    public static void Inoredr(Node root){
        if(root==null){
            return;
        }
        Inoredr(root.left);
        System.out.print(root.data+" ");
        Inoredr(root.right);
    }
    public static void postorder(Node root){
        if(root == null){
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data+" ");
    }
    public static void levelorder(Node root){
        if(root==null){
            return;
        }
        Queue<Node> q =new LinkedList<>();
        q.add(root);
        q.add(null);
        while(!q.isEmpty()){
            Node currNode=q.remove();
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
                    q.add(currNode.left);
                }
                if(currNode.right!=null){
                    q.add(currNode.right);
                }
            }
        }
    }
    public static int countnodes(Node root){
        if(root==null){
            return 0;
        }
        int lc=countnodes(root.left);
        int rc=countnodes(root.right);
        return lc+rc+1;
    }
    public static int sumnode(Node root){
        if(root==null){
            return 0;
        }
         int ls= sumnode(root.left);
        int rs= sumnode(root.right);
        return ls+rs+root.data;

    }
    public static int height(Node root){
        if(root==null){
            return 0;
        }
        int lh=height(root.left);
        int rh=height(root.right);
        int myheight=Math.max(lh, rh)+1;
        return myheight;
    }
    public static int diameter(Node root){
        if(root == null){
            return 0;
        }
        int ld=diameter(root.left);
        int rd=diameter(root.right);
        int ll= height(root.left)+height(root.right)+1;
        return Math.max(ll,Math.max(ld,rd));
    }
    public static boolean search(Node root, int key ){
        if(root==null){
            return false;
        }
        if(root.data==key){
            return true;
        }else if(root.data>key){
            return search(root.left, key);
        }else{
            return search(root.right,key);
        }
    }
    //delete class and its function written 
    public static class deletenode{
        public  Node delete(Node root, int val){
            if(root.data>val){
                root.left=delete(root.left, val);
            }else if(root.data<val){
                root.right=delete(root.right, val);
            }else{
                if(root.left==null&&root.right==null){
                    return null;
                }
                if(root.left==null){
                    return root.right;
                } else if(root.right==null){
                    return root.left;
                }
                Node Is= Inoredrsuccer(root.right);
                root.data=Is.data;
                root.right=delete(root.right, Is.data);
            }
            return root;

        }
        public static Node Inoredrsuccer(Node root){
            while(root.left!=null){
                root=root.left;
            }
            return root;
        }
    }
    public static void main(String[] args) {
        int nodes[]={8, 5, 3, 1, 4, 6, 10, 11, 14};
        //Binarytree tree=new Binarytree();
        //Node root= tree.Buildtree(nodes);
        //System.out.println("\npreoredr");
        //preoredr(root);
        //System.out.println("\ninorder");
        //Inoredr(root);
        //System.out.println("\npostoredr");
        //postorder(root);
        //System.out.println("\nlevelored ");
        //levelorder(root);
        //System.out.println("no.of nodes are "+countnodes(root));
        //System.out.println("the sum of the nodes "+sumnode(root));
        //System.out.println("height of node is "+height(root));
        //System.out.println("the diameter of node is "+diameter(root));
        Node root=null;
        
        for(int j = 0;j<nodes.length;j++){
           root=insert(root, nodes[j]);

        }
        Inoredr(root);
        System.out.println("\nwhat do you wanna search ");
        Scanner sc = new Scanner(System.in);
        int key = sc.nextInt();
       System.out.println( "\n"+search(root,key));
       deletenode dd=new deletenode();
       dd.delete(root, key);
       System.out.println("\n");
       Inoredr(root);
    }
}
