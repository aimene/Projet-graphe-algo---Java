package principal;

import Graphique.GraphiqueGraphe;

import java.awt.*;
import java.util.Vector;

public class Main {

   /* public static void main(String[] args) {
>>>>>>> c821756c20b664e92689f58b9c4ed543b215e9a9
        Numerotation n = new Numerotation();
        Vertex v1 = new Vertex("un", 2.5, new Point(100, 200));
        Vertex v2 = new Vertex("Deux", 3.1, new Point(25, 50));
        //n.addVertex(v1);
        //n.addVertex(v2);
        Vector<Vertex> V = new Vector<Vertex>();
        System.out.println(V.size());
        Graph g = new GraphAdjMat(n);

        //  g.readFromKeyBoard();

    //    g.addVertex(v1);
      //  g.addVertex(v2);
     //   g.deleteVertex(v2);
      //  g.addEgde(new Edge(v1,v2,10));
       // g.deleteEdge(v1,v2);

        g.readFromFile("fichier");
        g.displayOnConsole();
        g.writeToFile("fichierAdjMat");
        //  g.addVertex(v1);
        //  g.addVertex(v2);
       V = n.getAllVertex();
        System.out.println(" avvicher all vertex");
        System.out.println(V.size());
        for (int i=1;i<V.size();++i){
            System.out.println("sommet"+ i+ ": ");

            System.out.println(n.indexOf(V.get(i)));
        }

        boolean t;
        t = g.readFromFile("fichierMatAdj.txt");
        System.out.println(t);
        g.displayOnConsole();
    }*/

    public static void main(String[] args){
        new GraphiqueGraphe();
    }

    }

