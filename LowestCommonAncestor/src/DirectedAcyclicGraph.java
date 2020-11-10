import java.util.*;

public class DirectedAcyclicGraph {

    static Map<Integer,List<Integer>> edges = null;

    DirectedAcyclicGraph(){
        edges = new HashMap<>();
    }

    public static void addVertex(int v){
        if(!edges.containsKey(v)){
            edges.put(v,new ArrayList<>());
        }
    }

    public static void addEdges(int start, int end){
        if(edges.containsKey(start)){
            List<Integer> tmp = edges.get(start);
            tmp.add(end);
            edges.put(start,tmp);
            return;
        }
        else{
            addVertex(start);
            addEdges(start,end);
        }
    }

    public static List<Integer> getAdjacent(int v){
        return edges.get(v);
    }

    public static boolean containsVertex(int v){
        return edges.containsKey(v);
    }

    public static boolean containsEdge(int start, int end){
        if(containsVertex(start)){
            List<Integer> tmp = edges.get(start);
            return tmp.contains(end);
        }
        return false;
    }

    public static void removeVertex(int vertex){
        if(containsVertex(vertex)){
            edges.remove(vertex);
            for(List<Integer> tmp : edges.values()){
                tmp.remove(Integer.valueOf(vertex));
            }
        }
    }

    public static void removeEdge(int start, int end){
        if(containsEdge(start,end)){
            List<Integer> tmp = edges.get(Integer.valueOf(start));
            tmp.remove(Integer.valueOf(end));
            edges.put(start,tmp);
        }
    }

    public static void getPath(int src, int dst, List<Integer> path, List<List<Integer>> pathList){
        path.add(src);

        if(src == dst){
            pathList.add(new ArrayList<>(path));
        }

        List<Integer> adjacentList = getAdjacent(src);

        for(int adj : adjacentList){
            getPath(adj, dst, path, pathList);
        }
        path.remove(path.size()-1);
    }

    public static List<List<Integer>> getAllPaths(int src, int dst){
        if(!containsVertex(src) || !containsVertex(dst)){
            System.out.println("The current src or dst is not in this graph.");
            return null;
        }

        List<List<Integer>> pathList = new ArrayList<>();

        getPath(src, dst, new ArrayList<>(), pathList);

        return pathList;
    }

    public static int lowestCommonAncestor_DAG(int root, int p, int q){

        List<List<Integer>> pathP = getAllPaths(root,p);

        List<List<Integer>> pathQ = getAllPaths(root,q);

        int lca_depth = 0;
        int lca = -1;

        for(List<Integer> tmpP : pathP){
            for(List<Integer> tmpQ : pathQ){
                int curNode = -1;
                int curDepth = 0;


                for(int i=0; i<tmpP.size() && i<tmpQ.size(); i++){
                    if(tmpP.get(i) != tmpQ.get(i)){
                        break;
                    }
                    curDepth++;
                    curNode = tmpP.get(i);
                }

                if(curNode != -1 && curDepth > lca_depth){
                    lca_depth = curDepth;
                    lca = curNode;
                }
            }
        }
        return lca;
    }

    public static void main(String args[]){

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

        int ans = lowestCommonAncestor_DAG(0,5,4);
        System.out.print(ans);



        /*
            Normal Tree Case 1

                  10
                  / \
                 8   12
                / \  / \
               6   4   14
         */

        BinaryTree tree = new BinaryTree();


    }
}
