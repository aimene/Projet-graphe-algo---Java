package principal;

import javax.xml.stream.events.EntityDeclaration;
import java.awt.*;
import java.io.*;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Vector;

public class GraphApsFs extends Graph {
    private int[] aps;
    private int[] fs;
    private double[] valueEdge;

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
        for (int i = 1; i <= vertexNumber() + edgeNumber(); ++i) {
            ddi[fs[i]]++;
        }
        return ddi;
    }

    @Override
    public boolean existEdge(Edge e) {
        if( existVertex(e.getVertexA()) && existVertex(e.getVertexB())){
            int indexB = numerotation.indexOf(e.getVertexB());
            if(valueEdge(e.getVertexA(),e.getVertexB()) == e.getValue()){
                return true;
            }
            return false;
        }
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
    public boolean deleteVertex(Vertex v) {// à modifier
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
        valueEdge = new double[numberOfEdge+numberOfVertex+1];
        valueEdge[0]=numberOfVertex + numberOfEdge;
        int k = 1;
        int sommet;
        int value;
        for (int i = 1; i <=numberOfVertex; ++i) {
            System.out.println("Entrer les successeurs du sommet " + i);
            while (reponse.equals("o")){
                System.out.println("Entrer le sommet");
                sommet = in.nextInt();
                fs[k++] = sommet;
                System.out.println("Entrer la valeur de l'arc");
                value = in.nextInt();
                valueEdge[k++]= value;
                System.out.println("Encore un successeur du sommet " + i + "? o/n");
                String rep = new String();
                rep=in.nextLine();
                reponse = in.nextLine();
            }
            k++;
            fs[k] = 0;
            reponse="o";
        }
        fs2aps();
    }

    public boolean readFromFile(String fileName) {
        Scanner lecteur = null;
        int size = Integer.parseInt(lecteur.nextLine());
        int numberofEdge = Integer.parseInt(lecteur.nextLine());
        try {
            int k =1;
            lecteur = new Scanner(new FileReader(fileName + ".txt"));
            fs = new int[size + numberofEdge+1];
            fs[0]=size+numberofEdge;
            while (lecteur.hasNextLine()) {
                String ligne = lecteur.nextLine();
                String[] ligneTableau = ligne.split(" ");
                int taille = ligneTableau.length;
                for (int j = 0; j <taille; ++j) {
                    fs[k] = Integer.parseInt(ligneTableau[j]);
                    ++k;
                }
                fs[k] =  0;
            }
            fs2aps();
            return true;
        } catch (Exception e) {
            System.out.println("erreur de lecture");
            return false;
        }
    }

    @Override
    public double valueEdge(Vertex a, Vertex b) {
        if(existVertex(a) && existVertex(b)){
            int indexB = numerotation.indexOf(b);
            int tmp = aps[indexB+1]-aps[indexB]-1;
                if(tmp>=0){
                    return valueEdge[indexB];
                }
            }
        return -1;
    }

    @Override
    public double valueEdge(int a, int b) {
        Vertex v1 = numerotation.vertexOf(a);
        Vertex v2 = numerotation.vertexOf(b);
        return valueEdge(v1,v2);
    }

    @Override
    public Graph copyGraph() { // à revoir
        Numerotation n = new Numerotation();
        GraphApsFs graph = new GraphApsFs(n,vertexNumber(),edgeNumber());
        for (int i=0;i<fs[0];++i){
            graph.fs[i]= fs[i];
            graph.valueEdge[i]=valueEdge[i];
        }
        graph.fs2aps();
        return graph ;
    }

    @Override
    public boolean addEgde(Edge e){
        if(existEdge(e)){
            int[]fsTemp = new int[fs[0]+2];
            double valueEgeTemp[] = new double[fs[0]+2];
            int indexA = numerotation.indexOf(e.getVertexA());
            int longueur = aps[indexA];
            fsTemp[0]=fs[0]+1;
            for (int i=1;i<longueur;++i){
                fsTemp[i]=fs[i];
                valueEgeTemp[i]=valueEdge[i];
            }
            int indexB = numerotation.indexOf(e.getVertexB());
            fsTemp[longueur]=indexB;
            valueEdge[longueur] = e.getValue();
            for(int i=longueur;i<=fs[0];++i){
                fsTemp[i+1]= fs[i];
                valueEgeTemp[i+1]=valueEdge[i];
            }
            fs = new int [fsTemp[0]+1];
            fs[0]=fsTemp[0];
            valueEdge = new double[fsTemp[0]+1];
            for(int i=1;i<=fsTemp[0]+1;++i){
                fs[i] = fsTemp[i];
                valueEdge[i] = valueEgeTemp[i];
            }
            aps[indexB]++;
            return  true;
        }
        else
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
            System.out.print(fs[i] + " ");
        System.out.println("Affichage des sommets qui ont des successeurs ");
        for (int i=1;i<=fs[0];++i){
            System.out.println("Les successeurs du sommet " + i);
            while(fs[i]!=0){
                System.out.print(fs[i] + " ");
            }
        }
    }

    @Override
    public boolean writeToFile(String fileName) {
        try {
            PrintWriter writer = new PrintWriter(fileName+".txt");
            writer.println(vertexNumber());
            writer.println(edgeNumber());
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
    public Edge[][] getAdjMat() {// à faire
        Edge adjMat[][] = new Edge[vertexNumber() + 1][vertexNumber() + 1];
        return new Edge[0][];
    }
    private void fs2aps(){
        int n = fs[0];
        aps = new int[n+1];
        aps[0]=n;
        aps[1]=1;
        int j=2;
        for (int i = 2;i<=n;++i){
            while(fs[j]!=0){
                ++j;
            }
            j++;
            aps[i]=j;
        }
    }
    public static void main(String[] args) {
        Numerotation n = new Numerotation();
        GraphApsFs g = new GraphApsFs(n, 5, 5);
        g.readFromKeyBoard();
        g.displayOnConsole();
        //g.writeToFile("fichier");
    }

}
