package mmetsa.graphtask;

import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * Container class to different classes, that makes the whole
 * set of classes one class formally.
 */
public class GraphTask {

    /**
     * Main method.
     */
    public static void main(String[] args) {
        GraphTask a = new GraphTask();
        a.run();
    }

    /**
     * Actual main method to run examples and everything.
     */
    public void run() {

        Graph g = new Graph("G");

        Vertex v6 = g.createVertex("v6");
        Vertex v5 = g.createVertex("v5");
        Vertex v4 = g.createVertex("v4");
        Vertex v3 = g.createVertex("v3");
        Vertex v2 = g.createVertex("v2");
        Vertex v1 = g.createVertex("v1");

        Arc a1 = g.createArc("a" + v1 + "_" + v2, v1, v2);
        Arc a2 = g.createArc("a" + v2 + "_" + v1, v2, v1);

        Arc a3 = g.createArc("a" + v2 + "_" + v3, v2, v3);
        Arc a4 = g.createArc("a" + v3 + "_" + v2, v3, v2);

        Arc a5 = g.createArc("a" + v3 + "_" + v4, v3, v4);
        Arc a6 = g.createArc("a" + v4 + "_" + v3, v4, v3);

        Arc a7 = g.createArc("a" + v4 + "_" + v5, v4, v5);
        Arc a8 = g.createArc("a" + v5 + "_" + v4, v5, v4);

        Arc a9 = g.createArc("a" + v5 + "_" + v6, v5, v6);
        Arc a10 = g.createArc("a" + v6 + "_" + v5, v6, v5);

        Arc a11 = g.createArc("a" + v6 + "_" + v1, v6, v1);
        Arc a12 = g.createArc("a" + v1 + "_" + v6, v1, v6);

        Arc a13 = g.createArc("a" + v1 + "_" + v3, v1, v3);
        Arc a14 = g.createArc("a" + v3 + "_" + v1, v3, v1);

        a1.info = 21;
        a2.info = 20;
        a3.info = 22;
        a4.info = 18;
        a5.info = 2;
        a6.info = 19;
        a7.info = 21;
        a8.info = 20;
        a9.info = 10;
        a10.info = 11;
        a11.info = 20;
        a12.info = 8;
        a13.info = 5;
        a14.info = 14;

        System.out.println("Original graph: " + g);

        //Maiko Metsalu's Bellman-Ford algorithm
        System.out.println("Graph is simple: " + g.checkIfGraphIsSimple());
        System.out.println("Shortest path: " + g.BellmanFord(v1, v4, false));
        int shortestPathDistance = g.BellmanFord(v1, v4, false).stream().mapToInt(x -> x.info).sum();
        System.out.println("Distance of shortest path: " + shortestPathDistance);

        //Gerlin Vainomäe's algorithm to remove Vertices from Graph
        System.out.println(g.removeVertices(3));

        //Karl Tammaru's algorithm to find the Complement and Direct complement of the Graph.
        System.out.println(g.complementGraph());
        System.out.println(g.complementGraphdirect());

        //Lii Ratt's algorithm to find the Distances Matrix & radius
        System.out.println("Distances matrix:");
        int[][] distMatrix2 = (g.getDistMatrix());
        for (int i = 0; i < distMatrix2.length; i++) {
            System.out.println(Arrays.toString(distMatrix2[i]));
        }
        g.shortestPaths(distMatrix2);
        System.out.println();
        System.out.println("Distances matrix which contain shortest paths:");
        for (int i = 0; i < distMatrix2.length; i++) {
            System.out.println(Arrays.toString(distMatrix2[i]));
        }
        System.out.println();
        System.out.println("Radius:");
        System.out.println(g.radius(distMatrix2));

        //Merilin Kalda's algorithm to remove a Vertex and it's direct children
        g.removeVertexAndItsDirectChildren(v2);
        System.out.println("\nRemoved vertex v2: " + g);


        // Another test, Graph with 2000 Vertices.

      /*
      System.out.println("TESTING A SIMPLE GRAPH WITH 2000 VERTICES");

      Graph newG = new Graph("New G");
      newG.createRandomSimpleGraph(2000, 3400);

      for (Vertex v = newG.first; v != null; v = v.next) {
         for (Arc a = v.first; a != null; a = a.next) {
            a.info = (int)(Math.random() * 100);
         }
      }

      System.out.println("Original Graph: " + newG);


      //Maiko Metsalu's Bellman-Ford algorithm
      System.out.println("Graph is simple: " + newG.checkIfGraphIsSimple());
      System.out.println("Shortest path: " + newG.BellmanFord(newG.first, newG.first.next.next.next, false));
      shortestPathDistance = newG.BellmanFord(newG.first, newG.first.next.next.next, false).stream().mapToInt(x -> x.info).sum();
      System.out.println("Distance of shortest path: " + shortestPathDistance);

      //Gerlin Vainomäe's algorithm to remove Vertices from Graph
      Graph removedVertices = newG.removeVertices(8);
      System.out.println(removedVertices);

      //Karl Tammaru's algorithm to find the Complement and Direct complement of the Graph.
      System.out.println(newG.complementGraph());
      System.out.println(newG.complementGraphdirect());

      //Lii Ratt's algorithm to find the Distances Matrix & radius
      System.out.println("Distances matrix:");
      distMatrix2 = (newG.getDistMatrix());
      for (int i = 0; i < distMatrix2.length; i++) {
         System.out.println(Arrays.toString(distMatrix2[i]));
      }
      newG.shortestPaths(distMatrix2);
      System.out.println("Distances matrix which contain shortest paths:");
      for (int i = 0; i < distMatrix2.length; i++) {
         System.out.println(Arrays.toString(distMatrix2[i]));
      }
      System.out.println("Radius:");
      System.out.println(newG.radius(distMatrix2));

      //Merilin Kalda's algorithm to remove a Vertex and it's direct children
      newG.removeVertexAndItsDirectChildren(newG.first.next);
      System.out.println("\nRemoved vertex v2: " + newG);
       */
    }

    /**
     * Represents a vertex in the graph.
     */
    static class Vertex {

        private final String id;
        public Vertex next;
        public Arc first;
        public Vertex predecessor;
        int info = 0;
        private Vertex previous = null;
        private int color = 0; //white
        private boolean isChildToRemovedVertex = false;


        Vertex(String s, Vertex v, Arc e) {
            id = s;
            next = v;
            first = e;
        }

        Vertex(String s) {
            this(s, null, null);
        }

        /**
         * @return string representation of vertex
         */
        @Override
        public String toString() {
            return id;
        }


        /**
         * Return the id number of a vertex.
         * v1 --> 1
         *
         * @param id vertex id
         * @return vertex number
         */
        public static int getIdNumber(Vertex id) {
            return parseInt(id.toString().replace("v", ""));
        }


        @Override
        public boolean equals(Object o) {
            if (o instanceof Vertex) {
                Vertex v = (Vertex) o;
                if (v.id.equals(this.id)) {
                    return true;
                }
            }
            return false;
        }

        public void setColor(int i) {
            color = i;
        }

        public int getColor() {
            return color;
        }

        public Iterator<Arc> outArcs() {
            List<Arc> arcList = new LinkedList<Arc>();
            Arc arc = this.first;
            while (arc != null) {
                arcList.add(arc);
                arc = arc.next;
            }
            return arcList.iterator();
        }

        public void setFirst(Arc a1) {
            this.first = a1;
        }

        public void setNext(Vertex v2) {
            this.next = v2;
        }
    }

    /**
     * Arc represents one arrow in the graph. Two-directional edges are
     * represented by two Arc objects (for both directions).
     */
    static class Arc {

        private final String id;
        public Vertex target;
        public Arc next;
        private int info = 0;
        private Vertex source;

        Arc(String s, Vertex v, Arc a) {
            id = s;
            target = v;
            next = a;
        }

        Arc(String s) {
            this(s, null, null);
        }

        /**
         * @return string representation of arc
         */
        @Override
        public String toString() {
            return id;
        }


        public Vertex getToTarget() {
            return target;
        }

        public void setTarget(Vertex first) {
            this.target = first;
        }

        public void setNext(Arc a2) {
            this.next = a2;
        }

        public void setInfo(int i) {
            this.info = i;
        }
    }

    static class Graph {

        public String id;
        public Vertex first;
        private int info = 0;

        Graph(String s, Vertex v) {
            id = s;
            first = v;
        }

        Graph(String s) {
            this(s, null);
        }

        public void setFirst(Vertex v1) {
            this.first = v1;
        }

        /**
         * @return string representation of graph
         */
        @Override
        public String toString() {
            String nl = System.getProperty("line.separator");
            StringBuffer sb = new StringBuffer(nl);
            sb.append(id);
            sb.append(nl);
            Vertex v = first;
            while (v != null) {
                sb.append(v.toString());
                sb.append(" -->");
                Arc a = v.first;
                while (a != null) {
                    sb.append(" ");
                    sb.append(a.toString());
                    sb.append(" (");
                    sb.append(v.toString());
                    sb.append("->");
                    sb.append(a.target.toString());
                    sb.append(")");
                    a = a.next;
                }
                sb.append(nl);
                v = v.next;
            }
            return sb.toString();
        }

        private boolean containsVertex(Vertex vertex) {
            Vertex temp = this.first;
            while (temp != null) {
                if (temp.equals(vertex)) {
                    return true;
                } else {
                    temp = temp.next;
                }
            }
            return false;
        }

        /**
         * @param vid vertex id
         * @return new vertex
         */
        public Vertex createVertex(String vid) {
            Vertex res = new Vertex(vid);
            res.next = first;
            first = res;
            return res;
        }

        /**
         * @param aid  arc id
         * @param from vertex
         * @param to   target vertex
         * @return new arc
         */
        public Arc createArc(String aid, Vertex from, Vertex to) {
            Arc res = new Arc(aid);
            res.next = from.first;
            from.first = res;
            res.target = to;
            return res;
        }

        /**
         * Create a connected undirected random tree with n vertices.
         * Each new vertex is connected to some random existing vertex.
         *
         * @param n number of vertices added to this graph
         */
        public void createRandomTree(int n) {
            if (n <= 0)
                return;
            Vertex[] varray = new Vertex[n];
            for (int i = 0; i < n; i++) {
                varray[i] = createVertex("v" + (n - i));
                if (i > 0) {
                    int vnr = (int) (Math.random() * i);
                    createArc("a" + varray[vnr].toString() + "_"
                            + varray[i].toString(), varray[vnr], varray[i]);
                    createArc("a" + varray[i].toString() + "_"
                            + varray[vnr].toString(), varray[i], varray[vnr]);
                } else {
                }
            }
        }

        /**
         * Create an adjacency matrix of this graph.
         * Side effect: corrupts info fields in the graph
         *
         * @return adjacency matrix
         */
        public int[][] createAdjMatrix() {
            info = 0;
            Vertex v = first;
            while (v != null) {
                v.info = info++;
                v = v.next;
            }
            int[][] res = new int[info][info];
            v = first;
            while (v != null) {
                int i = v.info;
                Arc a = v.first;
                while (a != null) {
                    int j = a.target.info;
                    res[i][j]++;
                    a = a.next;
                }
                v = v.next;
            }
            return res;
        }

        /**
         * Create a connected simple (undirected, no loops, no multiple
         * arcs) random graph with n vertices and m edges.
         *
         * @param n number of vertices
         * @param m number of edges
         */
        public void createRandomSimpleGraph(int n, int m) {
            if (n <= 0)
                return;
            if (n > 2500)
                throw new IllegalArgumentException("Too many vertices: " + n);
            if (m < n - 1 || m > n * (n - 1) / 2)
                throw new IllegalArgumentException
                        ("Impossible number of edges: " + m);
            first = null;
            createRandomTree(n);       // n-1 edges created here
            Vertex[] vert = new Vertex[n];
            Vertex v = first;
            int c = 0;
            while (v != null) {
                vert[c++] = v;
                v = v.next;
            }
            int[][] connected = createAdjMatrix();
            int edgeCount = m - n + 1;  // remaining edges
            while (edgeCount > 0) {
                int i = (int) (Math.random() * n);  // random source
                int j = (int) (Math.random() * n);  // random target
                if (i == j)
                    continue;  // no loops
                if (connected[i][j] != 0 || connected[j][i] != 0)
                    continue;  // no multiple edges
                Vertex vi = vert[i];
                Vertex vj = vert[j];
                createArc("a" + vi.toString() + "_" + vj.toString(), vi, vj);
                connected[i][j] = 1;
                createArc("a" + vj.toString() + "_" + vi.toString(), vj, vi);
                connected[j][i] = 1;
                edgeCount--;  // a new edge happily created
            }
        }

        //-------------------------------------- Remove vertices ---------------------------------------
        //Gerlin Vainomäe

        /**
         * Create a graph with removed vertices.
         * A vertex is removed if the number of edges connected to it
         * is smaller than the given number.
         *
         * @param n number used for comparison
         * @return graph with removed vertices
         */
        public Graph removeVertices(int n) {
            System.out.println("\nRemoving vertices with less than " + n + " edges");
            if (first == null) return new Graph("Removed vertices from graph " + id); //given graph doesn't have any vertices
            if (n < 1) {
                this.id = "Removed vertices from graph " + id;
                return this;
            }

            ArrayList<Vertex> removableVertices = findRemovableVertices(n);
            Map<Vertex, ArrayList<Arc>> map = createMap(removableVertices);
            Graph newGraph = createVertices(map);

            int[][] connected = newGraph.createAdjMatrix();
            int length = map.size();
            if (length == 0) return new Graph("Removed vertices from graph " + id); //new graph doesn't have any vertices

            Vertex[] vertices = new Vertex[length];
            Vertex v = newGraph.first;
            int count = 0;
            while (v != null) {
                vertices[count] = v;
                v = v.next;
                count++;
            }

            for (Map.Entry<Vertex, ArrayList<Arc>> entry : map.entrySet()) {
                for (Arc arc : entry.getValue()) {
                    for (int j = 0; j < length; j++) {
                        for (int k = 0; k < length; k++) {
                            if (Vertex.getIdNumber(entry.getKey()) == Vertex.getIdNumber(vertices[j]) &&
                                    Vertex.getIdNumber(arc.target) == Vertex.getIdNumber(vertices[k])) {
                                String arcId = "a" + vertices[j] + "_" + vertices[k];
                                createArc(arcId, vertices[j], vertices[k]);
                                connected[j][k] = 1;
                            }
                        }
                    }
                }
            }

            return newGraph;
        }

        /**
         * Create a list of vertices that have less edges
         * than the given number.
         *
         * @param n number used for comparison
         * @return list of vertices that have to be removed
         */
        public ArrayList<Vertex> findRemovableVertices(int n) {
            ArrayList<Vertex> removableVertices = new ArrayList<>();
            Vertex v = first;

            while (v != null) {
                int count = 0;
                Arc a = v.first;
                while (a != null) {
                    a = a.next;
                    count++;
                }
                if (count < n) removableVertices.add(v);
                v = v.next;
            }

            return removableVertices;
        }

        /**
         * Create a map, where:
         * key: vertex
         * value: list of edges connected with key vertex
         *
         * @param removableVertices list of vertices that have to be removed
         * @return map of vertices
         */
        public Map<Vertex, ArrayList<Arc>> createMap(ArrayList<Vertex> removableVertices) {
            Map<Vertex, ArrayList<Arc>> map = new LinkedHashMap<>();
            ArrayList<Arc> temp = new ArrayList<>();

            Vertex v = first;
            while (v != null) {
                if (!removableVertices.contains(v)) {
                    temp = new ArrayList<>();
                    Arc a = v.first;
                    while (a != null) {
                        if (!removableVertices.contains(a.target)) {
                            temp.add(a);
                        }
                        a = a.next;
                    }
                }
                if (!removableVertices.contains(v)) {
                    map.put(v, temp);
                }
                v = v.next;
            }
            map = reverse(map);
            return map;
        }

        /**
         * Create a reversed map.
         * Key order and order inside value lists will be reversed.
         * {a[1,2], b[3,4]} --> {b[4,3], a[2,1]}
         *
         * @param map map of vertices
         * @return reversed map of vertices
         */
        public Map<Vertex, ArrayList<Arc>> reverse(Map<Vertex, ArrayList<Arc>> map) {
            int size = map.size();
            Vertex[] vertices = new Vertex[size];
            int countVertices = 0;
            Map<Vertex, ArrayList<Arc>> newMap = new LinkedHashMap<>();
            Object[] reversedArcLists = new Object[size];

            for (Map.Entry<Vertex, ArrayList<Arc>> entry : map.entrySet()) {
                vertices[countVertices] = entry.getKey();

                ArrayList<Arc> arcs = new ArrayList<>(entry.getValue());
                Collections.reverse(arcs);
                reversedArcLists[countVertices] = arcs;

                countVertices++;
            }

            Collections.reverse(Arrays.asList(reversedArcLists));
            Collections.reverse(Arrays.asList(vertices));
            int i = 0;
            for (Vertex v : vertices) {
                newMap.put(v, (ArrayList) reversedArcLists[i]);
                i++;
            }
            return newMap;
        }

        /**
         * Create new vertices for graph by iterating over given map.
         *
         * @param map map of vertices
         * @return graph with only vertices
         */
        public Graph createVertices(Map<Vertex, ArrayList<Arc>> map) {
            Graph result = new Graph("Removed vertices from graph " + id);
            for (Map.Entry<Vertex, ArrayList<Arc>> vertex : map.entrySet()) {
                result.createVertex(vertex.getKey().toString());
            }
            return result;
        }

        //-------------------------------------- Remove vertices ---------------------------------------

        //-------------------------------------- Complement graph --------------------------------------
        //Karl Tammaru

        // The algorithm for finding the adjacency matrix used in this individual task-
        //         https://www.baeldung.com/java-graphs
        // The base of the program obtained from - https://bitbucket.org/itc_algorithms/kt6.git

        /**
         * Create an adjacency matrix for complement graph
         * Uses method createAdjMatrix
         *
         * @return complementary graph adjacency matrix
         */
        public int[][] complementAdjMatrix() {
            int[][] adjMatrix = this.createAdjMatrix();
            for (int row = 0; row < adjMatrix.length; row++) {
                for (int col = 0; col < adjMatrix[row].length; col++) {
                    if (row != col) {
                        if (adjMatrix[row][col] == 1) {
                            adjMatrix[row][col] = 0;
                        } else {
                            adjMatrix[row][col] = 1;
                        }
                    }
                }
            }
            return adjMatrix;
        }

        /**
         * Create complement of graph without arcs
         * @param graphName name of the original graph
         * @return complement of graph without arcs
         */
        public Graph createComplementGraphWithoutArcs(String graphName){
            Vertex firstVertex = new Vertex(this.first.id);
            Graph complementGraph = new Graph(graphName, firstVertex);
            Vertex v = this.first;
            int c = 0;

            while (v != null) {
                if (c != 0) {
                    firstVertex.next = new Vertex(v.id);
                    firstVertex = firstVertex.next;
                }
                c++;
                v = v.next;
            }
            return complementGraph;
        }


        /**
         * Creates new list of original graph vertices
         * @param complementGraph the original graph
         * @return list of original graph vertices
         */
        public LinkedList<Vertex> createVertexList(Graph complementGraph){
            LinkedList<Vertex> vert = getVertexList();

            int c = 0;
            Vertex k = complementGraph.first;
            while (k != null) {
                vert.set(c++, k);
                k = k.next;
            }
            return vert;
        }



        /**
         * Create the complement of current graph
         * Complement of graph has same vertices as original graph but the opposite edges.
         * Contains no loops
         *
         * @return complement graph object
         */
        public Graph complementGraph() {
            Graph complementGraph = createComplementGraphWithoutArcs("Complement graph of: " + this.id);
            LinkedList<Vertex> vert = createVertexList(complementGraph);


            int[][] connectionsMatix = complementAdjMatrix();

            for (int row = 0; row < connectionsMatix.length; row++) {
                for (int col = 0; col < connectionsMatix[row].length; col++) {
                    if (connectionsMatix[row][col] == 1) {
                        Vertex vi = vert.get(row);
                        Vertex vj = vert.get(col);
                        complementGraph.createArc("a" + vi.id + "_" + vj.id, vi, vj);
                        connectionsMatix[col][row] = 0;
                        complementGraph.createArc("a" + vj.id + "_" + vi.id, vj, vi);
                        connectionsMatix[row][col] = 0;
                    }
                }
            }
            return complementGraph;
        }

        /**
         * Create the complement of current graph
         * Complement of graph has same vertices as original graph but the opposite edges.
         * Contains no loops
         * Similar to method complementGraph() with the exception that it uses the original
         * graph adjacency matrix
         * @return complement graph object
         */
        public Graph complementGraphdirect() {
            Graph complementGraph = createComplementGraphWithoutArcs("Direct complement graph of: " + this.id);
            LinkedList<Vertex> vert = createVertexList(complementGraph);


            int[][] connectionsMatix = createAdjMatrix();

            for (int row = 0; row < connectionsMatix.length; row++) {
                for (int col = 0; col < connectionsMatix[row].length; col++) {
                    if (connectionsMatix[row][col] == 0 && row != col) {
                        Vertex vi = vert.get(row);
                        Vertex vj = vert.get(col);
                        complementGraph.createArc("a" + vi.id + "_" + vj.id, vi, vj);
                        connectionsMatix[col][row] = 1;
                        complementGraph.createArc("a" + vj.id + "_" + vi.id, vj, vi);
                        connectionsMatix[row][col] = 1;
                    }
                }
            }
            return complementGraph;
        }

        //Karl Tammaru
        //-------------------------------------- Complement graph --------------------------------------

        //----------------------------------- Bellman-Ford algorithm -----------------------------------
        //Maiko Metsalu

        /**
         * Assign the field "info" to 0 in all of the Vertices in the Graph.
         * Assign the field "predecessor" to null in all of the Vertices in the Graph.
         */
        public void resetVerticesInfo() {
            Vertex v = this.first;
            while (v != null) {
                v.info = 0;
                v.predecessor = null;
                v = v.next;
            }
        }

        /**
         * Check if the Graph has any Arcs whose target Vertex is equal to the source Vertex,
         * or if the Graph has more than 1 Arc (that is with the same direction) between any 2 Vertices.
         *
         * @return true if the Graph does not contain any Arcs whose target Vertex is equal to the source Vertex and if
         * the Graph does not contain more than 1 same-directional Arcs between any 2 Vertices.
         */
        public boolean checkIfGraphIsSimple() {
            resetVerticesInfo();
            Vertex v = this.first;
            while (v != null) {
                Arc a = v.first;
                while (a != null) {
                    if (a.target.info == 1 || a.target.equals(v)) {
                        return false;
                    } else {
                        a.target.info = 1;
                    }
                    a = a.next;
                }
                resetVerticesInfo();
                v = v.next;
            }
            return true;
        }

        /**
         * Check if it is possible to travel from one Vertex to another Vertex.
         * This check is done with BFS (Breadth-First Search)
         * This method only works if the Graph does not contain two Nodes with the same id.
         *
         * @param startVertex - The starting Vertex
         * @param endVertex   - The ending Vertex
         * @return true if it is possible to travel from startVertex to endVertex
         */
        public boolean canTravelFromStartToEndVertex(Vertex startVertex, Vertex endVertex) {
            resetVerticesInfo();
            if (startVertex == null || endVertex == null) {
                throw new IllegalArgumentException("Start Vertex nor End Vertex can be null");
            }
            if (startVertex.equals(endVertex)) {
                return true;
            }
            LinkedList<Vertex> queue = new LinkedList<>();
            Vertex v = startVertex;
            v.info = 1;
            queue.push(v);
            while (!queue.isEmpty()) {
                v = queue.remove(0);
                if (v.id.equals(endVertex.id)) {
                    return true;
                }
                Arc a = v.first;
                while (a != null) {
                    if (a.target.info != 1) {
                        a.target.info = 1;
                        queue.push(a.target);
                    }
                    a = a.next;
                }
            }
            resetVerticesInfo();
            return false;
        }

        /**
         * Check if the current Graph is empty, if it doesn't contain the given startVertex or endVertex.
         * If any checks fail, an Exception will be thrown.
         *
         * @param startVertex - the start Vertex.
         * @param endVertex   - the end Vertex.
         */
        public void checkBasicErrors(Vertex startVertex, Vertex endVertex) {
            if (this.first == null) {
                throw new RuntimeException("Error: Empty graph provided!");
            }
            if (!this.containsVertex(startVertex)) {
                throw new IllegalArgumentException("Error: The Graph doesn't contain the Start vertex: " + startVertex);
            }
            if (!this.containsVertex(endVertex)) {
                throw new IllegalArgumentException("Error: The Graph doesn't contain the End vertex: " + endVertex);
            }
        }

        /**
         * My implementation of the Bellman-Ford algorithm to find the shortest path between two given Vertices in the Graph.
         * <p>
         * "The algorithm initializes the distance to the source to 0 and all other nodes to infinity.
         * Then for all edges, if the distance to the destination can be shortened by taking the edge, the distance is updated to the new lower value.
         * At each iteration i that the edges are scanned, the algorithm finds all shortest paths of at most length i.
         * Since the longest possible path without a cycle can be |V|-1 edges,
         * the edges must be scanned |V|-1 times to ensure the shortest path has been found for all nodes.
         * A final scan of all the edges is performed and if any distance is updated,
         * then a path of length |V| edges has been found which can only occur if at least one negative cycle exists in the graph."
         * Source: https://en.wikipedia.org/wiki/Bellman%E2%80%93Ford_algorithm
         * <p>
         * This method works, if the given startVertex and endVertex are in the Graph and it is possible to travel from startVertex to endVertex.
         * The user of this method needs to choose whether to validate him/herself that it is possible to travel from startVertex to endVertex,
         * or let the algorithm do it. The algorithm will be slower, if it needs to validate the inputs.
         * <p>
         * This method will return an ArrayList which contains the Arc objects that make up the shortest path.
         *
         * @param startVertex         - the starting Vertex in the Graph
         * @param endVertex           - the ending Vertex in the Graph
         * @param shouldValidateGraph - whether to validate if the Graph is empty, and if it's possible to travel from startVertex to endVertex.
         * @return an ArrayList<Arc> which is the shortest path from startVertex to endVertex.
         */
        public List<Arc> BellmanFord(Vertex startVertex, Vertex endVertex, boolean shouldValidateGraph) {
            checkBasicErrors(startVertex, endVertex);

            if (shouldValidateGraph) {
                if (!checkIfGraphIsSimple()) {
                    throw new RuntimeException("Provided graph is not simple: " + this);
                }
                if (!canTravelFromStartToEndVertex(startVertex, endVertex)) {
                    throw new RuntimeException("Impossible to travel from Start vertex: " + startVertex + " to End vertex: " + endVertex);
                }
            }

            if (startVertex.equals(endVertex)) {
                throw new RuntimeException("Source and destination Vertices must not be the same!");
            }
            int relaxCount = 0;
            Vertex v = this.first;
            while (v != null) {
                relaxCount++;
                if (v.equals(startVertex)) {
                    v.info = 0;
                } else {
                    v.info = Integer.MAX_VALUE / 2;
                }
                v = v.next;
            }

            v = first;
            for (int i = 0; i < relaxCount; i++) {
                while (v != null) {
                    Arc a = v.first;
                    while (a != null) {
                        if (a.target.info > v.info + a.info) {
                            a.target.info = v.info + a.info;
                            a.target.predecessor = v;
                        }
                        a = a.next;
                    }
                    v = v.next;
                }
                v = first;
            }

            v = first;
            while (v != null) {
                Arc a = v.first;
                while (a != null) {
                    if (a.target != v && a.target.info > v.info + a.info) {
                        throw new RuntimeException("Error: Graph contains a negative-weight cycle!");
                    }
                    a = a.next;
                }
                v = v.next;
            }
            v = endVertex;

            LinkedList<Vertex> path = new LinkedList<>();
            List<Arc> arcs = new ArrayList<>();

            while (v != null) {
                path.addFirst(v);
                v = v.predecessor;
            }
            for (int i = 0; i < path.size(); i++) {
                for (Arc a = path.get(i).first; a != null; a = a.next) {
                    if (i + 1 < path.size() && a.target.equals(path.get(i + 1))) {
                        arcs.add(a);
                    }
                }
            }
            resetVerticesInfo();
            return arcs;
        }

        //----------------------------------- Bellman-Ford algorithm -----------------------------------

        //--------------------------------------- Remove vertex ----------------------------------------
        //Merilin Kalda

        /**
         * Get list of all the vertices in the graph.
         *
         * @return LinkedList of vertices.
         */
        public LinkedList<Vertex> getVertexList() {
            LinkedList<Vertex> vertexList = new LinkedList<Vertex>();
            Vertex v = this.first;
            while (v != null) {
                vertexList.add(v);
                v = v.next;
            }
            return vertexList;
        }

        /**
         * Remove arc between two vertices.
         *
         * @param source source vertex of arc.
         * @param target target vertex of arc.
         */
        public void removeArc(Vertex source, Vertex target) {
            Iterator arcIterator = source.outArcs();
            Arc previousArc = null;
            while (arcIterator.hasNext()) {
                Arc a = (Arc) arcIterator.next();
                if (a.target.equals(target)) {
                    if (previousArc != null) {
                        previousArc.next = a.next;
                    } else {
                        source.first = a.next;
                    }
                }
                previousArc = a;
            }
        }


        /**
         * Set previous vertex to each vertex in graph.
         */
        public void setPreviousVertex() {
            Vertex s = this.first;
            Vertex next = s.next;
            while (next != null) {
                next.previous = s;
                s = s.next;
                next = next.next;
            }
        }


        /**
         * Method to remove given vertex and it's children from given random simple graph
         * Algorithm inspired by BFS from https://enos.itcollege.ee/%7ejpoial/algoritmid/graafid.html
         *
         * @param vertexToRemove vertex to remove from this graph.
         */
        public void removeVertexAndItsDirectChildren(Vertex vertexToRemove) {
            if (vertexToRemove.id.equals(this.first.id)) {
                this.first = null;
            }
            if (this.first == null) return;
            if ((!getVertexList().contains(vertexToRemove))) {
                throw new RuntimeException("Graph doesn't contain vertex " + vertexToRemove.id);
            }

            List vertexQueue = Collections.synchronizedList(new LinkedList());
            Vertex s = this.first;
            setPreviousVertex();
            vertexQueue.add(s);
            s.setColor(1); // "gray"

            List<Arc> arcsToRemove = new ArrayList<Arc>();

            while (vertexQueue.size() > 0) {
                Vertex v = (Vertex) vertexQueue.remove(0); // breadth == FIFO
                v.setColor(2); // "black"
                Iterator arcIterator = v.outArcs();
                while (arcIterator.hasNext()) {
                    Arc a = (Arc) arcIterator.next();
                    Vertex target = a.getToTarget();
                    if (v.equals(vertexToRemove) && a.target.first.next == null) {
                        a.target.isChildToRemovedVertex = true;
                    }
                    if (target.equals(vertexToRemove)) {
                        a.source = v;
                        arcsToRemove.add(a);
                        target.previous.next = target.next;
                    }
                    if (target.getColor() == 0) {
                        vertexQueue.add(target);
                        target.setColor(1);
                    }
                }
            }
            if (!arcsToRemove.isEmpty()) {
                for (Arc a : arcsToRemove) {
                    removeArc(a.source, a.target);
                }
            }

            Vertex x = this.first;
            while (x != null) {
                if (x.isChildToRemovedVertex) {
                    if (x.previous != null) {
                        x.previous.next = x.next;
                    }
                    if (x.next != null) {
                        x.next.previous = x.previous;
                    }
                }
                x = x.next;
            }
        }

        //--------------------------------------- Remove vertex ----------------------------------------

        //---------------------------------------- Find radius -----------------------------------------
        //Lii Ratt

        /**
         * Value that represents if there is no Arc.
         */
        public int INFINITY = 900000000;
        //    public int INFINITY = 100;

        /**
         * Converts adjacency matrix to the matrix of distances.
         * param  adjacency matrix
         *
         * @return matrix of distances
         */

        public int[][] getDistMatrix() {
            int[][] distMatrix = createAdjMatrix();

            Vertex v = first;
            while (v != null) {
                int i = v.info;
                Arc a = v.first;
                while (a != null) {
                    int j = a.target.info;
                    distMatrix[i][j] = a.info;
                    a = a.next;
                }
                v = v.next;
            }

            int matrixLength = distMatrix.length;
            if (matrixLength < 1) return distMatrix;
            for (int i = 0; i < matrixLength; i++) {
                for (int j = 0; j < matrixLength; j++) {
                    if (distMatrix[i][j] == 0) {
                        distMatrix[i][j] = INFINITY;
                    }
                }
            }
            for (int i = 0; i < matrixLength; i++) {
                distMatrix[i][i] = 0;
            }
            return distMatrix;
        }

        /**
         * Calculates shortest paths.
         *
         * @param matrix distances matrix
         *               Now  distances matrix will contain shortest paths.
         */

        public void shortestPaths(int[][] matrix) {
            int n = matrix.length;
            if (n < 1) return;
            for (int k = 0; k < n; k++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        int newlength = matrix[i][k] + matrix[k][j];
                        if (matrix[i][j] > newlength) {
                            matrix[i][j] = newlength; // new path is shorter
                        }

                    }
                }
            }
        }

        /**
         * Calculates radius.
         *
         * @param matrix distances matrix contain shortest paths.
         * @return radius
         */
        public int radius(int[][] matrix) {
            int radius = 0;
            int maximumDistanceFinder = 0;
            int[] maximumDistance = new int[matrix.length];
            int count = 0;
            int n = matrix.length;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (maximumDistanceFinder < matrix[i][j]) {
                        maximumDistanceFinder = matrix[i][j];
                    }
                }
                maximumDistance[count] = maximumDistanceFinder;
                count++;
                maximumDistanceFinder = 0;
            }

            radius = maximumDistance[0];
            for (int y = 0; y < maximumDistance.length; y++) {
                if (radius > maximumDistance[y]) {
                    radius = maximumDistance[y];
                }
            }
            return radius;
        }

        //---------------------------------------- Find radius -----------------------------------------

    }
}

