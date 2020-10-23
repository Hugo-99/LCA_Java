class Node{
    int val;
    Node left, right;

    Node(int val){
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class BinaryTree {
    Node root;

    BinaryTree(int val){
        this.root = new Node(val);
    }

    BinaryTree(){
        this.root = null;
    }

    public boolean isEmpty(){
        return root == null;
    }


    public Node get(int val){
        return get(root, val);
    }

    private Node get(Node node, int val){

        if(node == null){
            return null;
        }
        else if(node.val == val){
            return node;
        }

        Node left = get(node.left, val);

        if(left != null && left.val == val){
            return left;
        }

        Node right = get(node.right, val);

        return right;
    }

    public boolean isExist(Node leaf){
        return get(leaf.val) != null;
    }

    public Node lowestCommonAncestor(Node p, Node q){
        if(p==null || q==null){
            return null;
        }
        if(isExist(p) && isExist(q)){
            return lowestCommonAncestor(root, p, q);
        }
        return null;
    }

    private Node lowestCommonAncestor(Node root, Node p, Node q){
        if(root == null || root == p || root == q){
            return root;
        }

        Node left = lowestCommonAncestor(root.left, p, q);
        Node right = lowestCommonAncestor(root.right, p, q);

        if(left == null){
            return right;
        }
        else if(right == null){
            return left;
        }
        else {
            return root;
        }
    }
}
