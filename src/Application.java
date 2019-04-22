import Algorithm.GraphAlgorithm;
import principal.Graph;
import principal.GraphAdjMat;
import principal.GraphApsFs;
import principal.Numerotation;

import java.util.Scanner;

public class Application {
    private Graph currentGraph;
    private GraphAlgorithm grapheAlgorithm;


    Application(){

    }

    public void run(){
        Numerotation n = new Numerotation();

        GraphApsFs g = new GraphApsFs(n);
        Graph gM = new GraphAdjMat(n);
        g.readFromKeyBoard();
        g.fs2aps();
        g.displayOnConsole();

        System.out.println("Quelle algorithme souhaitez vous appliquer sur le graphe  ?");

        Scanner sc  =  new Scanner(System.in);
        int reponse = 0;

        System.out.println("-------------------------Algorithme --------------------------");
        System.out.println("               1.Calcul des distances                                 ");
        System.out.println("               2.Determiner les rangs des sommets                                ");
        System.out.println("               3.Problème de l'ordonnancement                            ");
        System.out.println("               5.Chemins le plus court selon Dijkstra                           ");
        System.out.println("               6. Algorithme de Kruskal et Arbre recouvrant minimal                             ");
        System.out.println("               7. Codage de Prüfer                              ");
        System.out.println("               8. Quitter                              ");
        System.out.println("Taper votre choix :                                       ");
        reponse=sc.nextInt();




        switch (reponse){

            case 1:
                grapheAlgorithm = new GraphAlgorithm();

                int[][] distMatrix =  grapheAlgorithm.distanceMatrix(g,1);

                for (int i = 1; i < distMatrix[0][0] ; i++) {
                    for (int j = 1; j <  distMatrix[0][0] ; j++) {
                         System.out.println(distMatrix[i][j]);
                    }
                    System.out.println();
                }
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
                default:
                    System.out.println("Vous  avez souhaitez quitter le menu ou bien votre saisie n'est pas bonne");

        }



    }

    public static void main(String[] args){
         Application a  = new Application();
         a.run();
    }

}
