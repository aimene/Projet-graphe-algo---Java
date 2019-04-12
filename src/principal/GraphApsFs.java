package principal;

import java.awt.*;
import java.io.*;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Vector;

public class GraphApsFs extends Graph {
    private int[] aps;
    private int[] fs;

    public GraphApsFs(Numerotation num, int numberOfVertex, int numberOfEdge) {
        super(num, numberOfVertex, numberOfEdge);
    }

    public int vertexNumber() {
        return fs[0];
    }

    public int edgeNumber() {
        return aps[0];
    }

    @Override
    public int[] getDDI() {
        int[] ddi = new int[vertexNumber() + 1];

        for (int i = 0; i <= vertexNumber(); ++i) {
            ddi[i] = 0;
        }
        for (int i = 1; i < vertexNumber() + edgeNumber(); ++i) {
            //Vertex vertex = numerotation.vertexOf(fs[i]);
            ddi[fs[i]]++;
        }
        return ddi;
    }

    @Override
    public boolean existEdge(Vertex a, Vertex b) {
        return false;
    }

    @Override
    public boolean existVertex(Vertex a) {
        int valeur = numerotation.indexOf(a);
        if (valeur!=-1)
            return true;
        return false;
    }

    @Override
    public boolean addVertex(Vertex v) {
        if (existVertex(v)) {
            return false;
        }
        numerotation.addVertex(v);
        return true;
    }

    @Override
    public boolean deleteVertex(Vertex v) {
        if (existVertex(v)) {
            int i = numerotation.indexOf(v);
            numerotation.getAllVertex().remove(i);
            return true;
        }
        return false;
    }

    @Override
    public void readFromKeyBoard() {
        String reponse = "o";
        System.out.print("Entrer le nombre de sommets : ");
        Scanner in = new Scanner(System.in);
        int numberOfVertex = in.nextInt();
        System.out.print("Entrer le nombre d'arcs : ");
        int numberOfEdge = in.nextInt();
        fs=new int[numberOfEdge+numberOfVertex+1];
        fs[0] = numberOfVertex + numberOfEdge;
        int k = 1;
        int sommet;
        for (int i = 1; i <=numberOfVertex; ++i) {
            System.out.println("Entrer les successeurs du sommet " + i);
            while (reponse.equals("o")){
                System.out.println("Entrer le sommet");
                sommet = in.nextInt();
                fs[k++] = sommet;
                System.out.println("Encore un successeur du sommet " + i + "? o/n");
                String rep = new String();
                rep=in.nextLine();
                reponse = in.nextLine();
            }
            k++;
            fs[k] = 0;
            reponse="o";
        }
    }

    public boolean readFromFile(String fileName) {
        Scanner lecteur = null;
        int size = Integer.parseInt(lecteur.nextLine());
        int[][] tableau = new int[size][size];//le tableau où stocker tes résultats. Tu peux aussi utiliser un ArrayList
        try {
            int i = 0;
            lecteur = new Scanner(new FileReader(fileName + ".txt"));
            while (lecteur.hasNextLine()) {
                String ligne = lecteur.nextLine();
                String[] ligneTableau = ligne.split("\t");//transforme par exemple "aaa;bbb;ccc" en {"aaa","bbb","ccc"}
                int taille = ligneTableau.length;
                for (int j = 0; j < taille; ++j) {
                    tableau[i][j] = Integer.parseInt(ligneTableau[j]);
                }
                i++;
            }
            return true;
        } catch (Exception e) {
            System.out.println("erreur de lecture");
            return false;
        }
    }

    @Override
    public double valueEdge(Vertex a, Vertex b) {
        //not do
        return 0;
    }

    @Override
    public double valueEdge(int a, int b) {
        // not do
        return 0;
    }

    @Override
    public Graph copyGraph() {

        return null;
    }

    @Override
    public boolean addEgde(Vertex a, Vertex b) {
        if(existVertex(a)){
            int[]fsTemp = new int[fs[0]+2];
            int indexA = numerotation.indexOf(a);
            fsTemp[0] = fs[0]+1;
            for (int i=1;i<indexA;++i){
                fsTemp[i]=fs[i];
            }
            int indexB = numerotation.indexOf(b);
            fsTemp[indexA]=indexB;
            for(int i=indexA+1;i<fs[0];++i){
                fsTemp[i]= fs[i];
            }
            fs = new int[fsTemp[0]+1];
            for (int i=0;i<fs[0];++i){
                fs[i]=fsTemp[i];
            }
        }
        return false;
    }

    @Override
    public boolean deleteEdge(Vertex a, Vertex b) {
        return false;
    }

    @Override
    public void readFromGUI() {
        System.out.println("a");
    }

    @Override
    public void displayOnConsole() {
        for (int i =0;i<fs[0];i++)
        System.out.println(fs[i]+" ");
    }

    @Override
    public boolean writeToFile(String fileName) {
        try {
            PrintWriter writer = new PrintWriter(fileName+".txt");
            writer.println(vertexNumber());
            int k = 1;
            for (int i = 1; i <vertexNumber(); ++i) {

                while (fs[k]!=0) {
                    writer.print(fs[i]);
                    writer.print(" ");
                   k++;
                }
                writer.println();
                k++;
                writer.print((fs[k]));
                writer.print(" ");
                k++;
            }
            writer.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void displayOnGUI() {

    }

    @Override
    public int[] getFS() {
        return fs;
    }

    @Override
    public int[] getAPS() {
        return aps;
    }

    @Override
    public Edge[][] getAdjMat() {
        Edge adjMat[][] = new Edge[vertexNumber() + 1][vertexNumber() + 1];


           /* a = new int * [n+1]; // matrice d'adjacence
            a[0] = new int[2];
            a[0][0]=n;
            a[0][1]=m;
            for(int i = 1; i ‹ n; i++)
            {
                int nbs = aps[i+1] ‐ aps[i] ‐ 1; // calcul le nombre d'arc du sommet i.
                a[i] = new int [nbs+1];
                a[i][0]=nbs; // on insere le nombre d'arc du sommet i en 0
                for(int j = 1; fs[aps[i] + j ‐ 1] != 0; j++)
                a[i][j] = fs[aps[i]+j‐1]; // on insere les valeurs des arcs du sommet i.
            }*/

        return new Edge[0][];
    }
    public static void main(String[] args) {
        Numerotation n = new Numerotation(5);
        GraphApsFs g = new GraphApsFs(n, 5, 5);
        g.readFromKeyBoard();
        g.displayOnConsole();
        //g.writeToFile("fichier");
    }

}
