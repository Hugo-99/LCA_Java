import org.junit.Test;

import static org.junit.Assert.*;

public class BinaryTreeTest {

    @Test
    public void lowestCommonAncestor() {
        BinaryTree tree = new BinaryTree(10);
        tree.root.left = new Node(8);
        tree.root.right = new Node(12);
        tree.root.left.left = new Node(6);
        tree.root.right.right = new Node(14);
        /*
               10
              / \
             8   12
            /     \
           6       14
         */
        Node p = new Node(8);
        Node q = new Node(12);
        Node LCA = tree.lowestCommonAncestor(p,q);
        //assertNull(LCA);
        assertEquals(LCA.val, 10);
    }
}