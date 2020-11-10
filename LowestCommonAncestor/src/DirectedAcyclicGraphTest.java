import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertArrayEquals;

public class DirectedAcyclicGraphTest {

    @Test
    public void getAdjacent() {

        DirectedAcyclicGraph dag = new DirectedAcyclicGraph();

        dag.addVertex(0);
        dag.addVertex(1);
        dag.addVertex(2);

        dag.addEdges(0,1);
        dag.addEdges(0,2);

        List<Integer> adjacents = dag.getAdjacent(0);
        Object[] cmp = adjacents.toArray();
        Object[] expect = {1,2};
        assertArrayEquals(expect,cmp);
    }

    @Test
    public void containsVertex() {
        DirectedAcyclicGraph dag = new DirectedAcyclicGraph();

        dag.addVertex(0);
        dag.addVertex(1);
        dag.addVertex(2);

        dag.addEdges(0,1);
        dag.addEdges(0,2);

        boolean cmp1;

        cmp1 = dag.containsVertex(3);
        assertFalse(cmp1);
        cmp1 = dag.containsVertex(1);
        assertTrue(cmp1);
    }

    @Test
    public void containsEdge() {
        DirectedAcyclicGraph dag = new DirectedAcyclicGraph();

        dag.addVertex(0);
        dag.addVertex(1);
        dag.addVertex(2);

        dag.addEdges(0,1);
        dag.addEdges(0,2);

        boolean cmp2;

        cmp2 = dag.containsEdge(0,3);
        assertFalse(cmp2);
        cmp2 = dag.containsEdge(0,1);
        assertTrue(cmp2);
    }

    @Test
    public void removeVertex() {
        DirectedAcyclicGraph dag = new DirectedAcyclicGraph();

        dag.addVertex(0);
        dag.addVertex(1);
        dag.addVertex(2);

        dag.addEdges(0,1);
        dag.addEdges(0,2);

        List<Integer> adjacents = dag.getAdjacent(0);
        Object[] cmp1 = adjacents.toArray();
        Object[] expect1 = {1,2};
        assertArrayEquals(expect1,cmp1);

        dag.removeVertex(2);

        adjacents = dag.getAdjacent(0);
        Object[] cmp2 = adjacents.toArray();
        Object[] expect2 = {1};
        assertArrayEquals(expect2,cmp2);
    }

    @Test
    public void removeEdge() {
        DirectedAcyclicGraph dag = new DirectedAcyclicGraph();

        dag.addVertex(0);
        dag.addVertex(1);
        dag.addVertex(2);

        dag.addEdges(0,1);
        dag.addEdges(0,2);

        List<Integer> adjacents = dag.getAdjacent(0);
        Object[] cmp1 = adjacents.toArray();
        Object[] expect1 = {1,2};
        assertArrayEquals(expect1,cmp1);

        dag.removeEdge(0,2);

        adjacents = dag.getAdjacent(0);
        Object[] cmp2 = adjacents.toArray();
        Object[] expect2 = {1};
        assertArrayEquals(expect2,cmp2);
    }

    @Test
    public void getAllPaths() {
        DirectedAcyclicGraph dag = new DirectedAcyclicGraph();

        dag.addVertex(0);
        dag.addVertex(1);
        dag.addVertex(2);
        dag.addVertex(3);
        dag.addVertex(4);

        dag.addEdges(0,1);
        dag.addEdges(1,2);
        dag.addEdges(1,3);
        dag.addEdges(2,4);
        dag.addEdges(3,4);

        List<List<Integer>> pathList = dag.getAllPaths(0,4);

        Object[] tmp1 = pathList.get(0).toArray();
        Object[] tmp2 = pathList.get(1).toArray();
        Object[][] cmp = {tmp1,tmp2};
        Object[][] expect = {{0,1,2,4},{0,1,3,4}};
        assertArrayEquals(expect,cmp);
    }

    @Test
    public void lowestCommonAncestor_DAG() {
        DirectedAcyclicGraph dag = new DirectedAcyclicGraph();

        dag.addVertex(0);
        dag.addVertex(1);
        dag.addVertex(2);
        dag.addVertex(3);
        dag.addVertex(4);
        dag.addVertex(5);
        dag.addVertex(6);
        dag.addVertex(7);

        dag.addEdges(0,1);
        dag.addEdges(0,2);
        dag.addEdges(1,5);
        dag.addEdges(2,3);
        dag.addEdges(5,6);
        dag.addEdges(3,7);
        dag.addEdges(7,6);
        dag.addEdges(7,4);

        /* Acyclic Graph
                        0
                       / \
                      1   2——3
                     /       /
                    5 __    7
                        \  / \
                         6    4


        */
        int cmp = dag.lowestCommonAncestor_DAG(0,6,4);
        int expected = 7;
        assertEquals(expected,cmp);

    }
}