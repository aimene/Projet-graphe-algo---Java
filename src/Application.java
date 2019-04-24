import Algorithm.GraphAlgorithm;
import principal.*;

import java.util.Scanner;

public class Application {
    private Graph currentGraph;
    private GraphAlgorithm grapheAlgorithm;


    Application(){

    }

    public void run(){
        Numerotation n = new Numerotation();

        //GraphApsFs g = new GraphApsFs(n);
      //  Graph gM = new GraphAdjMat(n);
       /* g.readFromKeyBoard();
        g.fs2aps();
        g.displayOnConsole();*/

        GraphAlgorithm ga = new GraphAlgorithm();

        Numerotation num = new Numerotation();
        Graph g =  new GraphApsFs(num);

        g.readFromFile("fichier2.txt");
        ((GraphApsFs) g).getFS();
        ((GraphApsFs) g).fs2aps();

        System.out.println("Quelle algorithme souhaitez vous appliquer sur le graphe  ?");

        Scanner sc  =  new Scanner(System.in);
        int reponse = 0;

        System.out.println("-------------------------Algorithme --------------------------");
        System.out.println("               1.Calcul des distances                                 ");
        System.out.println("               2.Determiner les rangs des sommets                                ");
        System.out.println("               3.Problème de l'ordonnancement                            ");
        System.out.println("               4.Chemins le plus court selon Dijkstra                           ");
        System.out.println("               5. Algorithme de Kruskal et Arbre recouvrant minimal                             ");
        System.out.println("               6. Codage de Prüfer                              ");
        System.out.println("               7. Quitter                              ");
        System.out.println("Taper votre choix :                                       ");
        reponse=sc.nextInt();




        switch (reponse){

            case 1:
                grapheAlgorithm = new GraphAlgorithm();

                int[][] distMatrix =  grapheAlgorithm.distanceMatrix(g,1);

                System.out.println("Matrice des ditances ");
                for (int i = 1; i < distMatrix[0][0] ; i++) {
                    for (int j = 1; j <=  distMatrix[0][0] ; j++) {
                         System.out.print(distMatrix[i][j]);
                    }
                    System.out.println();
                }
                break;
            case 2:
                grapheAlgorithm = new GraphAlgorithm();
                System.out.println("Tableau des rangs des sommets");
                int[] rang = grapheAlgorithm.rang(g);
                for (int i = 1; i <= g.getAPS()[0] ; i++) {
                    System.out.print(rang[i]+" ");
                }
                break;
            case 3:
                System.out.println("Problème de l'ordonnancement");
                ga.chemin_critique(g);


                break;
            case 4:
                grapheAlgorithm = new GraphAlgorithm();
                System.out.println("Algorithme de Djikstra");
                double[] distanceMinimal = grapheAlgorithm.Dijkstra(g,g.getAdjMat(),1);
                for (int i = 1; i <= g.getAPS()[0] ; i++) {
                    System.out.print(distanceMinimal[i]);
                }
                break;
            case 5:
                grapheAlgorithm = new GraphAlgorithm();
                System.out.println("Algorithme de Kruskal");
                Graph  ARM = grapheAlgorithm.kruskal(g);
                Edge[][] matAdj = ARM.getAdjMat();

                for (int i = 1; i <  ARM.vertexNumber(); i++) {
                    for (int  j= 1; j <ARM.vertexNumber() ; j++) {
                        if (matAdj[i][j]!=null){
                            System.out.println(" Sommet de départ" +matAdj[i][j].getVertexA().getValue());
                            System.out.println(" Sommet d'arriver"+ matAdj[i][j].getVertexB().getValue());
                            System.out.println(" poids de l'arrête" + matAdj[i][j].getValue());
                        }

                    }
                }
                break;
            case 6:
                grapheAlgorithm = new GraphAlgorithm();
                System.out.println("Codage de Prüfer");
                int[] cPrufer = grapheAlgorithm.codagePrufer(g);
                for (int i = 1; i <=g.getAPS()[0] ; i++) {
                    System.out.print(cPrufer[i]);
                }

                break;
                default:
                    System.out.println("Vous  avez souhaitez quitter le menu ou bien votre saisie n'est pas bonne");

        }



    }

    public static void main(String[] args){
         Application a  = new Application();
         a.run();
       // GraphAlgorithm ga = new GraphAlgorithm();

        //Numerotation num = new Numerotation();
       // Graph g =  new GraphApsFs(num);

       // g.readFromFile("fichier.txt");
       // ((GraphApsFs) g).fs2aps();
        //ga.Dijkstra(g,g.getAdjMat(),1);

        //Graph k =ga.kruskal(g);

        //k.displayOnConsole();
    }

}
