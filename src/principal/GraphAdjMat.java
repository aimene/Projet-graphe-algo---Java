package principal;
import java.awt.Point;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class GraphAdjMat extends Graph {

    private Edge[][] adjencyMatrix;

    public GraphAdjMat(Numerotation num) {
        super(num);
        adjencyMatrix= new Edge[1][1];
        adjencyMatrix[0][0]= new Edge(0);
        //adjencyMatrix[0][1]= new Edge(0);
    }

    public GraphAdjMat(Numerotation num, Edge[][] adjencyMatrix) {
        super(num);
        this.adjencyMatrix = adjencyMatrix;
    }


    @Override
    public Graph copyGraph() {
        return new GraphAdjMat(numerotation, adjencyMatrix);
    }

    @Override
    public boolean addVertex(Vertex v) {
        if (!numerotation.existVertex(v)) {
            int nbVertex =vertexNumber();
            int nbEdge = edgeNumber();
            Edge[][] adjencyMatrixTemp = new Edge[vertexNumber() + 2][vertexNumber() + 2];
            if (vertexNumber()==0){
            }else {

                for (int i = 1; i <= vertexNumber(); i++) {
                    for (int j = 1; j <= vertexNumber(); j++) {
                        adjencyMatrixTemp[i][j] = adjencyMatrix[i][j];
                    }
                }

                adjencyMatrixTemp[0][1]=adjencyMatrix[0][1];
            }
           adjencyMatrix = new Edge[vertexNumber() + 2][vertexNumber() + 2];
            adjencyMatrix[0][0]=new Edge(nbVertex +1);
            adjencyMatrix[0][1]=new Edge(nbEdge );


            for (int i = 1; i <= vertexNumber(); i++) {
                for (int j = 1; j <= vertexNumber(); j++) {
                    adjencyMatrix[i][j] = adjencyMatrixTemp[i][j];
                }
            }

            adjencyMatrix[0][0].setValue(vertexNumber() );
            numerotation.addVertex(v);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean deleteVertex(Vertex v) {
        if (numerotation.existVertex(v)) {
            for (int i = 1; i <= vertexNumber(); i++) {
                adjencyMatrix[numerotation.indexOf(v)][i] = null;
                adjencyMatrix[i][numerotation.indexOf(v)] = null;
            }
            int nb = 0;
            for (int i = 1; i <= vertexNumber(); i++) {

                if (adjencyMatrix[numerotation.indexOf(v)][i]!=null && existEdge(adjencyMatrix[numerotation.indexOf(v)][i].getVertexA(), adjencyMatrix[numerotation.indexOf(v)][i].getVertexB())) {
                    nb++;
                }
                if (adjencyMatrix[i][numerotation.indexOf(v)]!= null && existEdge(adjencyMatrix[i][numerotation.indexOf(v)].getVertexA(), adjencyMatrix[i][numerotation.indexOf(v)].getVertexB())) {
                    nb++;
                }
            }
            // update numver of edge
            if(vertexNumber()<1)
               adjencyMatrix[0][1].setValue(edgeNumber() - nb);

            adjencyMatrix[0][0].setValue(vertexNumber() - 1);
            numerotation.deleteVertex(v);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean addEgde(Edge e) {
        if (numerotation.existVertex(e.getVertexA()) && numerotation.existVertex(e.getVertexB())) {
            adjencyMatrix[numerotation.indexOf(e.getVertexA())][numerotation.indexOf(e.getVertexB())] = e;
            adjencyMatrix[0][1].setValue(edgeNumber() + 1);

            return true;
        } else
            return false;
    }

    @Override
    public boolean deleteEdge(Vertex a, Vertex b) {
        if (numerotation.existVertex(a) && numerotation.existVertex(b)) {
            adjencyMatrix[numerotation.indexOf(a)][numerotation.indexOf(b)] = null;
            adjencyMatrix[0][1].setValue(edgeNumber() - 1);
            return true;
        } else
            return false;
    }

    @Override
    public void readFromKeyBoard() {
        Edge vertexNumber = new Edge(0);
        Edge edgeNumber = new Edge(0);
        Scanner in = new Scanner(System.in);

        String reponse = "o";

        System.out.print("Entrer le nombre de sommets : ");
        int numberOfVertex = in.nextInt();

        System.out.print("Entrer le nombre d'arcs : ");
        int numberOfEdge = in.nextInt();

        vertexNumber.setValue(numberOfVertex);
        edgeNumber.setValue(numberOfEdge);

        adjencyMatrix= new Edge[numberOfVertex+1][numberOfVertex+1];

        adjencyMatrix[0][0] = vertexNumber;
        adjencyMatrix[0][1] = edgeNumber;


        String name;
        double value;
        int x, y;
        Vertex v;
        int k = 1;

        // ajout des sommet
        while (k <= vertexNumber()) {
            System.out.println("Entrer le sommet " + (k ));

            System.out.println("Entrer le nom du sommet");
            name = in.next();
            System.out.println("Entrer la valeur du sommet");
            value = in.nextDouble();
            System.out.println("Entrer la position du sommet");
            System.out.print("Entrer la valeur du X = ");
            x = in.nextInt();
            System.out.print("Entrer la valeur du Y = ");
            y = in.nextInt();

            v = new Vertex(name, value, new Point(x, y));
            if (addVertex(v))
                k++;

            reponse = in.nextLine();
        }
        k = 0;
        reponse = "oui";
        int indiceSec;
        double valeur;

        // ajout des aretes
        for (int i = 1; i <= numberOfVertex; ++i) {

                System.out.println("Entrer  les successeurs du sommet " + i);
                while (reponse.equals("oui") && k != edgeNumber()) {
                    System.out.println("Entrer  l'indice du successeurs ");
                    indiceSec = in.nextInt();
                    System.out.println("Entrer  la valeur de l'arete  ");
                    valeur = in.nextDouble();
                    System.out.println("Voulez vous entrer un autre successeur du sommet   " + i);
                    System.out.println("Répodez par oui ou non   ");
                    reponse = in.nextLine();
                    if (addEgde(new Edge(numerotation.vertexOf(i), numerotation.vertexOf(indiceSec), valeur)))
                        k++;
                    else
                        System.out.println("Cette arete n'a pas été ajouté ");
                }
            }

    }

    @Override
    public boolean readFromFile(String fileName) {
        Scanner lecteur = null;
        String ligne;
        Vertex v = null;
        String name;
        Double value;
        Point position;
        int x, y;
        Edge e = null;

        try {
            int k = 2;
            lecteur = new Scanner(new FileReader(fileName + ".txt"));
            if (lecteur.hasNextLine()) {
                int numberOfVertex = Integer.parseInt(lecteur.nextLine());
                int numberOfEdge = Integer.parseInt(lecteur.nextLine());
                adjencyMatrix =new Edge[numberOfVertex+1][numberOfVertex+1];

                adjencyMatrix[0][0]= new Edge(numberOfVertex);
                adjencyMatrix[0][1]= new Edge(numberOfEdge);

                Edge vertexNumber = new Edge(0);
                vertexNumber.setValue(numberOfEdge);
                Edge edgeNumber = new Edge(0);
                edgeNumber.setValue(numberOfEdge);
                while (lecteur.hasNextLine() && k != 2 + numberOfVertex) {
                    ligne = lecteur.nextLine();
                    String[] ligneTableau = ligne.split(" ");
                    name = ligneTableau[0];
                    value = Double.parseDouble(ligneTableau[1]);
                    x = Integer.parseInt(ligneTableau[2]);
                    y = Integer.parseInt(ligneTableau[3]);
                    position = new Point(x, y);

                    numerotation.addVertex(new Vertex(name, value, position));
                    k++;

                }
                k = 1;
                // lecture des edge
                Vertex vertexA, vertexB;
                while (lecteur.hasNextLine()) {
                    ligne = lecteur.nextLine();
                    String[] ligneTableau = ligne.split(" ");
                    vertexA = numerotation.vertexOf(k);
                    for (int i = 0; i < ligneTableau.length; i = i + 2) {
                        vertexB = numerotation.vertexOf(Integer.parseInt(ligneTableau[i]));
                        value = Double.parseDouble(ligneTableau[i + 1]);
                        adjencyMatrix[numerotation.indexOf(vertexA)][numerotation.indexOf(vertexB)] =
                                new Edge(vertexA, vertexB, value);
                    }
                    k++;
                }
                return true;
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        return false;


    }

    @Override
    public void readFromGUI() {

    }

    @Override
    public void displayOnConsole() {
        for (int i = 1; i <= vertexNumber(); i++) {
            System.out.println("The vertex " + numerotation.vertexOf(i).toString() + " has like successors : ");
            for (int j = 1; j <= vertexNumber(); j++) {
                if (adjencyMatrix[i][j] != null) {
                    if (existEdge(adjencyMatrix[i][j].getVertexA(),adjencyMatrix[i][j].getVertexB()))
                    System.out.println(adjencyMatrix[i][j].getVertexB());
                }
            }
        }
    }

    @Override
    public boolean writeToFile(String fileName) {
        try {
            PrintWriter writer = new PrintWriter(fileName + ".txt");
            writer.println(adjencyMatrix[0][0].getValue());
            writer.println(adjencyMatrix[0][1].getValue());
            for (int i = 1; i <= vertexNumber(); ++i) {
                Vertex v = numerotation.vertexOf(i);
                writer.println(v.getName() + " " + v.getValue() + " " + v.getPosition().x + " " + v.getPosition().y);
            }
            int b;
            for (int i = 1; i <= vertexNumber(); ++i) {
                for (int j = 1; j <= vertexNumber(); ++j) {
                    if (adjencyMatrix[i][j] != null) {
                        b = numerotation.indexOf(adjencyMatrix[i][j].getVertexB());
                        Vertex v1 = numerotation.vertexOf(i);
                        Vertex v2 = numerotation.vertexOf(j);
                        writer.print(b + " " + valueEdge(v1, v2) + " ");
                    }
                }
                writer.println();
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

        int[] fs = new int[vertexNumber() + edgeNumber() + 1];
        fs[0] = vertexNumber() + edgeNumber();
        int k = 1;
        for (int i = 1; i <= vertexNumber(); i++) {
            for (int j = 1; j <= vertexNumber(); j++) {
                if (adjencyMatrix[i][j] != null) {

                    fs[k] = numerotation.indexOf(adjencyMatrix[i][j].getVertexA());
                    k++;
                }
            }
            fs[k] = 0;
            k++;
        }

        return fs;

    }

    @Override
    public int[] getAPS() {

        int aps[] = new int[vertexNumber() + 1];
        aps[0] = vertexNumber();
        aps[1] = 1;
        int j = 2;
        int[] fs = getFS();
        for (int i = 1; i < vertexNumber(); i++) {
            if (fs[i] == 0) {
                aps[j] = i + 1;
                j++;
            }
        }
        return aps;
    }

    @Override
    public int[] getDDI() {

        int ddi[] = new int[vertexNumber() + 1];
        ddi[0] = vertexNumber();

        int[] fs = getFS();

        for (int i = 1; i <= vertexNumber(); i++) {
            ddi[i] = 0;
        }
        for (int i = 1; i <= vertexNumber() + edgeNumber(); i++) {
            ddi[fs[i]]++;
        }
        return ddi;
    }

    @Override
    public int[] getAPP() {
        int[] ddi = getDDI();
        int[] app = new int[vertexNumber() + 1];
        app[0] = vertexNumber();
        app[1] = 1;
        for (int i = 2; i <= vertexNumber(); i++) {
            app[i] = app[i - 1] + ddi[i - 1] + 1;
        }
        return app;
    }

    @Override
    public int[] getFP() {
        int fp[] = new int[vertexNumber() + 1];
        int app[] = getAPP();
        int fs[] = getFS();
        int aps[] = getAPS();
        fp[0] = fs[0];
        int j = 0;
        for (int i = 1; i <= aps[0]; i++) {
            for (int k = aps[i]; (j = fs[k]) != 0; k++) {
                fp[app[j]] = i;
                app[j]++;
            }
        }
        for (int i = 1; i <= aps[0]; i++) {
            fp[app[i]] = 0;
        }
        for (int i = app[0]; i > 1; i--) {
            app[i] = app[i - 1] + 1;
        }
        app[1] = 1;
        return app;
    }


    @Override
    public Edge[][] getAdjMat() {
        return adjencyMatrix;
    }


    @Override
    public boolean existEdge(Vertex a, Vertex b) {
        if (a==null || b ==null) return false;
        if (existVertex(a) && existVertex(b) && adjencyMatrix[numerotation.indexOf(a)][numerotation.indexOf(b)] != null)
            return true;
        else
            return false;
    }

    @Override
    public boolean existVertex(Vertex a) {
        if (numerotation.indexOf(a) == -1)
            return false;
        else
            return true;
    }

    @Override
    public double valueEdge(Vertex a, Vertex b) {
        return adjencyMatrix[numerotation.indexOf(a)][numerotation.indexOf(b)].getValue();
    }

    @Override
    public double valueEdge(int a, int b) {
        return adjencyMatrix[a][b].getValue();
    }

    @Override
    public int vertexNumber() {
        return (int) adjencyMatrix[0][0].getValue();
    }

    @Override
    public int edgeNumber() {
        if(vertexNumber()<1)
            return 0;
        else
            return (int) adjencyMatrix[0][1].getValue();
    }

    public void setVertexNumber(int n) {
        adjencyMatrix[0][0].setValue(n);
    }

    public void setEdgeNumber(int n) {
        adjencyMatrix[0][1].setValue(n);
    }

    @Override
    public void setValueEdge(int i, int j, int n) {
        adjencyMatrix[i][j].setValue(n);
    }




}