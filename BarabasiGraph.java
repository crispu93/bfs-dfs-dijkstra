import java.io.IOException;

public class BarabasiGraph {
    Graph G;
    int n;
    int d;
    int m;

    public BarabasiGraph(int n, int d, boolean directed, boolean self){
        this.n = n;
        this.d = d;
        this.m = 0;

        G = new Graph(directed, self);

        while(d-- != 0) {
            G.addNode("N" + (this.d-d));
            for (int i=1; i<(this.d-d); i++){
                G.addEdge("N" + (this.d-d), "N" + i);
                this.m++;
            }
        }
        

        for(int i=this.d+1; i<=this.n; i++){
            G.addNode("N" + i);
            
            for(int j=1; j<i; j++){
                double p = (double) G.getDegree("N"+j)/this.m;
                double rand = Math.random();
                //System.out.println(rand);
                if (rand < p) {
                    G.addEdge("N" + i, "N" + j);
                    this.m++;
                }
            }
            /*for(int j=1, aux=0; j<d && aux<this.n; j++, aux++){
                double rand = Math.random();
                if (rand < p) {
                    G.addEdge("N" + i, "N" + j);
                    this.m++;
                }
                else {
                    j--;
                }
            }*/
        }

    }

    public void printEdges(){
        this.G.printEdges();
    }

    public void graphToFile(String filename) throws IOException{
        try {
            this.G.graphToFile(filename);
        }catch(IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public Graph BFS(String s){
        Graph Tree = this.G.BFS(s);
        Tree.printEdges();
        return Tree;
    }

    public Graph DFS_I(String s){
        Graph Tree = this.G.DFS_I(s);
        Tree.printEdges();
        return Tree;
    }

    public Graph DFS_R(String s){
        Graph Tree = this.G.DFS_R(s);
        Tree.printEdges();
        return Tree;
    }
    public static void main(String[] args) {
        BarabasiGraph g1 = new BarabasiGraph(30, 5, false, false);
        BarabasiGraph g2 = new BarabasiGraph(100, 5, false, false);
        BarabasiGraph g3 = new BarabasiGraph(500, 5, false, false);

        Graph T1_1 = g1.BFS("N1");
        Graph T1_2 = g1.DFS_I("N1");
        Graph T1_3 = g1.DFS_R("N1");

        Graph T2_1 = g2.BFS("N1");
        Graph T2_2 = g2.DFS_I("N1");
        Graph T2_3 = g2.DFS_R("N1");

        Graph T3_1 = g3.BFS("N1");
        Graph T3_2 = g3.DFS_I("N1");
        Graph T3_3 = g3.DFS_R("N1");
        try {
            g1.graphToFile("csv/Barabasi1.csv");
            g2.graphToFile("csv/Barabasi2.csv");
            g3.graphToFile("csv/Barabasi3.csv");
            T1_1.graphToFile("csv/BarabasiTree1-1.csv");
            T1_2.graphToFile("csv/BarabasiTree1-2.csv");
            T1_3.graphToFile("csv/BarabasiTree1-3.csv");
            T2_1.graphToFile("csv/BarabasiTree2-1.csv");
            T2_2.graphToFile("csv/BarabasiTree2-2.csv");
            T2_3.graphToFile("csv/BarabasiTree2-3.csv");
            T3_1.graphToFile("csv/BarabasiTree3-1.csv");
            T3_2.graphToFile("csv/BarabasiTree3-2.csv");
            T3_3.graphToFile("csv/BarabasiTree3-3.csv");
        }catch(IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}