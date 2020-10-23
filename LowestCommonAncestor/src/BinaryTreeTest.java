import org.junit.Test;

import static org.junit.Assert.*;

public class BinaryTreeTest {

    @Test
    public void lowestCommonAncestor() {
        /*
            Empty Tree with null nodes
         */
        BinaryTree tree = new BinaryTree();
        Node p = null;
        Node q = null;
        Node LCA = tree.lowestCommonAncestor(p,q);

        assertNull(LCA);

        /*
            Empty Tree with nodes
         */
        p = new Node(100);
        q = new Node(100);
        LCA = tree.lowestCommonAncestor(p,q);

        assertNull(LCA);

        /*
            Normal Tree Case 1

                  10
                  / \
                 8   12
                /     \
               6       14
         */
        tree = new BinaryTree(10);
        p = new Node(8);
        q = new Node(12);
        tree.root.left = p;
        tree.root.right = q;
        tree.root.left.left = new Node(6);
        tree.root.right.right = new Node(14);
        LCA = tree.lowestCommonAncestor(p,q);

        assertEquals(LCA, tree.root);

        /*
            Normal Tree Case 2

                  10
                  / \
                 8   12
                / \
               6   16
         */
        tree = new BinaryTree(10);
        p = new Node(6);
        q = new Node(16);
        tree.root.left = new Node(8);
        tree.root.right = new Node(12);
        tree.root.left.left = p;
        tree.root.left.right = q;
        LCA = tree.lowestCommonAncestor(p,q);

        assertEquals(LCA, tree.root.left);

        /*
            Normal Tree Case 3

                  10
                  / \
                 8   12
                /
               6
              /
             1
         */
        tree = new BinaryTree(10);
        p = new Node(1);
        q = new Node(12);
        tree.root.left = new Node(8);
        tree.root.right = q;
        tree.root.left.left = new Node(6);
        tree.root.left.left.left = p;
        LCA = tree.lowestCommonAncestor(p,q);

        assertEquals(LCA, tree.root);
    }
}