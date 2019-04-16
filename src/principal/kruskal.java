package principal;

public class kruskal {

    krus samp = new krus();
    //  initialize fathers for the disjoint sets
    for(int i=0;i<100;i++){
        samp.fathers[i]=i;
    }
    //  declaring the variables to load input
    int n,m;
    int a,b,w;
    ArrayList<pair3> edges = new ArrayList<pair3>();

    //  loading the input
    n = stdin.nextInt();
    m = stdin.nextInt();
    for(int i=0;i<m;i++){
        a = stdin.nextInt(); //Vertex A
        b = stdin.nextInt(); // Vertex B
        w = stdin.nextInt(); // Poids
        edges.add(new pair3(w,a,b)); // Utiliser la structure g.GraphAdjMat() ;
    }
    //  we print a line to separate input from output
    System.out.println();
    //  NOW THE KRUSKAL'S ALGORITHM BEGINS
    //  We firstly declare the variables for the MST
    int mst_weight = 0, mst_edges = 0;
    int mst_ni = 0;
    //  STEP 1: sort the list of edges
    //  comparator is interface that sort uses
     // Comparer les deux arrêtes
     Collections.sort(edges, new Comparator<pair3>() {
        @Override
        public int compare(pair3 p1, pair3 p2) {
            return p1.w - p2.w;
        }
    });

    //  STEP 2-3:
    //
    while( ( mst_edges < n-1) || (mst_ni < m) ){
        //  we brake the edge into the three integers they describe it
        a = edges.get(mst_ni).a; // Vertex a
        b = edges.get(mst_ni).b; // Vertex b
        w = edges.get(mst_ni).w; // poids
        //  we check if the edge is ok to be included in the MST
        //  if a and b are in different trees (if they are on the same we will create a cycle)

        if( samp.find(a) != samp.find(b) ) {
            //  we unite the two trees the edge connects
            samp.unite(a,b);
            //  we add the weight of the edge
            mst_weight += w;
            //  we print the edge and count it
            System.out.println(a + " " + b + " " + w);
            mst_edges++;
        }
        //  increase the index of the edge we will be chacking
        mst_ni++;
    }
    //  Presenting the WEIGHT
    System.out.println( "\nWeight of the MST is " + mst_weight);
    //  THE END
}

}
}
