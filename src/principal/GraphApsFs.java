package principal;

import javax.xml.stream.events.EntityDeclaration;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Vector;
import java.util.stream.Stream;

public class  GraphApsFs extends Graph {
    private int[] aps;
    private int[] fs;
    private double[] valueEdge;

    public GraphApsFs(Numerotation num) {
        super(num);

    }
    public GraphApsFs(Numerotation num,int[]fs){
        this(num);
        this.fs=fs;
        fs2aps();
    }
    public GraphApsFs(Numerotation num,int numberoFVertex,int numberOfEdge){
        this(num);
        fs = new int[numberOfEdge+numberoFVertex+1];
        aps = new int[numberOfEdge+1];
        fs[0]=numberoFVertex+numberOfEdge;
        aps[0]=numberoFVertex;
    }

    public int vertexNumber() {
        return aps[0];
    }

    public int edgeNumber() {
        return fs[0]-aps[0];
    }

    public  void setVertexNumber(int n){ this.aps[0] = n; }

    public  void setEdgeNumber(int n){ fs[0] += n ;}


    @Override
    public int[] getDDI() {
        int[] ddi = new int[vertexNumber() + 1];

        for (int i = 0; i <= vertexNumber(); ++i) {
            ddi[i] = 0;
        }
        ddi[0]=aps[0];
        for (int i = 1; i <= vertexNumber() + edgeNumber(); ++i) {
            ddi[fs[i]]++;
        }
        return ddi;
    }



    @Override
    public boolean existEdge(Vertex a, Vertex b) {
        if( existVertex(a) && existVertex(b)){
            return true;
        }
        return false;
    }

    @Override
    public int[] getAPP() {
        int[] app = new int[vertexNumber()+1];
        app[0] = vertexNumber();
        app[1]=1;
        int[] ddi=new int[vertexNumber()+1];
        ddi=getDDI();
        for (int i=2;i<=vertexNumber();++i){
            app[i]=app[i-1]+ddi[i-1] + 1;
        }
        return app;
    }


    private int[] dist(int s,int[]dist){
        int d=0;
        int t=0;
        int p,q;
        p=q=1;
        int x,y;
        dist = new int[vertexNumber()+1];
        int[] fa = new int[vertexNumber()-1];
        fa[1]=s;
        dist[s]=0;
        for (int i=1;i<=vertexNumber();++i){
            dist[i]=-1;
        }
        while (t<q){
            d++;
            for (int i=t+1;i<=q;++i){
                x=fa[i];
                for (int j =aps[x];(y=fs[j])!=0;++j){
                    if(dist[y]==-1){
                        dist[y]=d;
                        fa[++p]=y;
                    }
                }
            }
            t=q;
            q=p;
        }
        return dist;
    }
    private int[][] matDist(){
        int matDist[][] = new int[vertexNumber()+1][vertexNumber()+1];
        matDist[0][0]=vertexNumber();
        for (int s=1;s<=vertexNumber();++s){
            dist(s,matDist[s]);
        }
        return matDist;
    }

    @Override
    public int[] getFP() {
        int []fp = new int [vertexNumber()+edgeNumber()+1];
        int[] ddi = new int[vertexNumber()+1];
        ddi = getDDI();
        int[]app = new int[vertexNumber()+1];
        app = getAPP();
        fp[0]=vertexNumber()+edgeNumber();
        int j;
        for(int i=1;i<=vertexNumber();++i){
            for (int k=aps[i];(j=fs[k])!=0;++k){
                fp[app[j]]=i;
                app[j]++;
            }
        }
        for(int i=1;i<=vertexNumber();++i){
            fp[app[i]]=0;
        }
        //facultatif
        for (int i = app[0];i>1;i--){
            app[i]=app[i-1]+1;
        }
        return fp;
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
        int i  = numerotation.indexOf(v);
        // DEBUT
        int[]fsTemp = new int[fs[0]+2];
        double valueEgeTemp[] = new double[fs[0]+2];
        int longueur = aps[0];
        fsTemp[0]=fs[0]+1;
        for (int j=1;j<longueur;++j){
            fsTemp[j]=fs[j];
            valueEgeTemp[j]=valueEdge[j];
        }
        fsTemp[longueur]=i;
        valueEdge[longueur] = v.getValue();
        for(int j=longueur;j<=fs[0];++j){
            fsTemp[j+1]= fs[j];
            valueEgeTemp[j+1]=valueEdge[j];
        }
        fs = new int [fsTemp[0]+1];
        fs[0]=fsTemp[0];
        valueEdge = new double[fsTemp[0]+1];
        for(int j=1;j<=fsTemp[0];++j){
            fs[j] = fsTemp[j];
            valueEdge[j] = valueEgeTemp[j];
        }
        return true;
    }

    @Override
    public boolean deleteVertex(Vertex v) {
        if (existVertex(v)) {
            int i = numerotation.indexOf(v);
            numerotation.getAllVertex().remove(i);
            int[] fsTemp = new  int[vertexNumber()+edgeNumber()+1];
            fsTemp = getFS();
            int supprCounter=0;
            int sommet,k;
            for (k=1;k<=vertexNumber();++k){
                if(aps[k]==i){
                    sommet=k;
                }
            }
           for (int j=1;j<=vertexNumber()+edgeNumber();++j){
                if(fs[j]==k){
                    supprCounter++;
                    fsTemp[j]=-1;
                }
            }
           int m=i;
           while(fs[m]!=0){
               supprCounter++;
               ++m;
               fsTemp[m]=-1;
           }
           fsTemp[m]=-1;
           supprCounter++;
           fs = new int[fsTemp[0]-supprCounter+1];
           fs[0]=fsTemp[0]-supprCounter;
           for (int j = 0;j<=fsTemp[0];++j ){
               if(fsTemp[j]!=-1){
                   fs[j]=fsTemp[j];
               }
           }
           fs2aps();
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
        fs = new int[numberOfEdge+numberOfVertex+1];
        fs[0] = numberOfVertex + numberOfEdge;
        aps = new int[numberOfVertex+1];
        aps[0]=numberOfVertex;
        valueEdge = new double[numberOfEdge+numberOfVertex+1];
        valueEdge[0]=numberOfVertex + numberOfEdge;
        int k = 0;
        int sommet;
        int value;
        for (int i = 1; i <=numberOfVertex; ++i) {
            System.out.println("Entrer les successeurs du sommet " + i);
            while (reponse.equals("o")){
                k++;
                System.out.println("Entrer le sommet");
                sommet = in.nextInt();
                fs[k] = sommet;
                System.out.println("Entrer la valeur de l'arc");
                value = in.nextInt();
                valueEdge[k]= value;
                System.out.println("Encore un successeur du sommet " + i + "? o/n");
                String rep = new String();
                rep=in.nextLine();
                reponse = in.nextLine();
            }
            k++;
            fs[k] = 0;
            valueEdge[k]=-1; // convention separateur des sommets
            reponse="o";
        }
        fs2aps();
    }

    public boolean readFromFile(String fileName) {
        int k =1;
        int l =1;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line = new String();
            int size = Integer.parseInt(line = bufferedReader.readLine());
            int numberofEdge = Integer.parseInt(line = bufferedReader.readLine());
            fs = new int[size + numberofEdge+1];
            valueEdge = new double [size + numberofEdge+1] ;
            int indexSucc = size + 2;
            fs[0]=size+numberofEdge;
            valueEdge[0]=size+numberofEdge;
            aps = new int[size+1];
            aps[0]=size;
            for (int i=3;i<=indexSucc;++i){
                line = bufferedReader.readLine();
                String[] ligneTableau = line.split(" ");
                String name = ligneTableau[0];
                double value = Double.parseDouble(ligneTableau[1]);
                int x = Integer.parseInt(ligneTableau[2]);
                int y = Integer.parseInt(ligneTableau[3]);
                Point position = new Point(x, y);
                numerotation.addVertex(new Vertex(name, value, position));
            }
           while ((line = bufferedReader.readLine()) != null) {
                String contain = line;
               String[] ligneTableau = new String[line.length()];
                ligneTableau = contain.split(" ");
                int taille = ligneTableau.length;
                for (int j = 0; j<taille; j+=2) {
                    fs[k] = Integer.parseInt(ligneTableau[j]);
                    valueEdge[k]=Double.parseDouble(ligneTableau[j+1]);
                    k++;
                }
                fs[k] =  0;
                valueEdge[k]=-1;
                k++;
            }
           k++;
           fs[k]=0;
            fs2aps();
            bufferedReader.close();

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

    public void setValueEdge(int i, int j, int n) {

    }

    @Override
    public Graph copyGraph() {

        Numerotation n = new Numerotation();
        GraphApsFs graph = new GraphApsFs(n);
        for (int i=0;i< fs[0];++i){
            graph.fs[i]= fs[i];
            graph.valueEdge[i]=valueEdge[i];
        }
        graph.fs2aps();
        return graph ;
    }

    @Override
    public boolean addEgde(Edge e){
        if(existEdge(e.getVertexA(),e.getVertexB())){
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
        if(existVertex(a)&& existVertex(b)){
            int [] fsTemp = new int[vertexNumber()+edgeNumber()-1];
             double [] valueEdgeTemp = new  double[vertexNumber()+edgeNumber()-1];
             fsTemp[0]=vertexNumber()+edgeNumber()-1;
            for (int i=0;i<=vertexNumber()+edgeNumber()+1;++i){
                if(b!=(numerotation.vertexOf(i))){
                    fsTemp[i]=fs[i];
                    valueEdgeTemp[i]=valueEdge[i];
                }
            }
            fs = new int [vertexNumber()+edgeNumber()-1];
            valueEdge = new double [vertexNumber()+edgeNumber()-1];
            for (int i=0;i<vertexNumber()+edgeNumber();++i){
                fs[i]=fsTemp[i];
                valueEdge[i]=fsTemp[i];
            }
            //fs2aps();
            return true;
        }
        return false;
    }

    @Override
    public void readFromGUI() {
        System.out.println("a");
    }

    @Override
    public void displayOnConsole() {
        System.out.println("Contenu de fs : file de successeurs : ");
        for (int i =0;i<=vertexNumber()+edgeNumber();i++) {
            System.out.print(fs[i] + " ");
        }
        System.out.println();
        System.out.println("Affichage des sommets qui ont des successeurs : ");
        int j=1;
        for (int i=1;i<=vertexNumber();++i){
            System.out.println("Les successeurs du sommet " + i + " :");
            while(fs[j]!=0){
                System.out.print(fs[j] + " ");
                j++;
            }
            System.out.println();
            j++;
        }
        System.out.println("Contenu de aps :");
        for (int i=0;i<=vertexNumber();++i){
            System.out.print(aps[i]+" ");
        }
        System.out.println();
        System.out.println("Contenu les valeurs des arcs entre les sommets et leurs successeurs respectifs :");
        for (int i=1;i<vertexNumber()+edgeNumber();++i){
            System.out.print(valueEdge[i]+" ");
        }
     }

    @Override
    public boolean writeToFile(String fileName) {
        try {
            PrintWriter writer = new PrintWriter(fileName+".txt");
            writer.println(vertexNumber());
            writer.println(edgeNumber());
            Vector<Vertex> vertex=new Vector<Vertex>();
            vertex = numerotation.getAllVertex();
            for (int i = 1; i <=vertexNumber(); ++i) {
                writer.print(vertex.get(i).getName() + " " + vertex.get(i).getValue() + " " + vertex.get(i).getPosition().x + " " + vertex.get(i).getPosition().y);
                writer.println();
            }
            int k = 1;
            for (int i = 1; i <vertexNumber(); ++i) {

                while (fs[k]!=0) {
                    writer.print(fs[k]+" " + valueEdge[k]+ " ");
                    writer.println();
                   k++;
                }
                k++;
                writer.print(fs[k]+" " + valueEdge[k]+ " ");
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
        Edge n = new Edge(vertexNumber());
        n.setVertexA(new Vertex());
        n.setVertexB(new Vertex());
        Edge m = new Edge(edgeNumber());
        m.setVertexA(new Vertex());
        m.setVertexB(new Vertex());
        Edge adjMat[][] = new Edge[vertexNumber() + 1][vertexNumber() + 1];
        adjMat[0][0]=n;
        adjMat[0][1]=m;
        int fsIndice;
        for(int j=1;j<=vertexNumber();++j){
            fsIndice=aps[j];
            for (int k=1;k<=vertexNumber();++k ){
                while (fs[fsIndice]!=0){
                    if(fs[fsIndice]==k){
                        Vertex v1 = numerotation.vertexOf(j);
                        Vertex v2 = numerotation.vertexOf(k);
                        adjMat[j][k]=new Edge(v1,v2,valueEdge(v1,v2));
                    }
                    fsIndice++;
                }
                fsIndice=aps[j];
            }
        }

        return adjMat;
    }
    public void fs2aps(){
        int n = vertexNumber();
        aps = new int[n+1];
        aps[0]=n;
        aps[1]=1;
        int j=2;
        for (int i = 2;i<=n;++i){
           while((fs[j])!=0 && j<= fs.length){
                ++j;
           }
            j++;
            aps[i]=j;
        }
    }
    public static void main(String[] args) {
        Numerotation n = new Numerotation();
        Vertex v1 =new Vertex("un",2.5,new Point(100,200));
        Vertex v2 = new Vertex("Deux",3.1,new Point(25,50));
        n.addVertex(v1);
        n.addVertex(v2);
        Vector<Vertex> V = new Vector<Vertex>();
        System.out.println(V.size());
        GraphApsFs g = new GraphApsFs(n);

        //g.readFromKeyBoard();
        //g.fs2aps();
        //g.displayOnConsole();
        //g.addVertex(v1);
        //g.addVertex(v2);
       // V = n.getAllVertex();
        //System.out.println();
        //System.out.println(V.size());
        //for (int i=1;i<V.size();++i){
           // System.out.println(n.indexOf(V.get(i)));
        //}
        //g.writeToFile("fichier");
        boolean t;
        t = g.readFromFile("fichier.txt");
        System.out.println(t);
        g.fs2aps();
        g.displayOnConsole();
    }



}
