package principal;

import java.awt.*;
import java.io.FileReader;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Vector;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.File;

public class GraphApsFs extends Graph {
  private int[] aps;
  private int[] fs;



    public int vertexNumber(){
        return aps[0];
    }
    public int edgeNumber(){
        return fs[0]-aps[0];
    }

  @Override
  public int[] getDDI(){
      int[] ddi = new int[vertexNumber()+1];

      for (int i = 0 ; i<vertexNumber();++i){
          ddi[0]=i;
      }
      for (int i = 1 ; i<vertexNumber()+edgeNumber();++i){
          Vertex vertex = numerotation.vertexOf(fs[i]);
          ddi[fs[i]]++;
      }
      return ddi;
  }


    @Override
    public boolean existVertex(Vertex a) {
          for (int i=1;i<=fs.size();++i){
              if(fs.get(i).equals(a)){
                  return true;
              }
          }
          return false;
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
        GraphApsFs graphe = new GraphApsFs();
        for(int i=1;i<=fs.size();++i){
            graphe.addVertex(fs.elementAt(i));
        }
        return graphe;
    }

    @Override
    public boolean addVertex(Vertex v) {
        if(existVertex(v)){
            return false;
        }
        fs.addElement(v);
        return true;
    }

    @Override
    public boolean deleteVertex(Vertex v) {
       if(existVertex(v)){
           fs.remove(v);
           return true;
       }
        return false;
    }

    @Override
    public boolean addEgde(Vertex a, Vertex b) {
        return false;
    }

    @Override
    public boolean deleteEdge(Vertex a, Vertex b) {
        return false;
    }

    @Override
    public void readFromKeyBoard() {
        System.out.println("Entrer le nombre de sommets");
        Scanner in = new Scanner(System.in);
        numberOfVertex = in.nextInt();
        String reponse = "o";
        String nom;
        double value;
        int index;
        Point position = new Point();
        for (int i = 1; i < numberOfVertex; ) {
            System.out.println("Entrer les successeurs du sommet " + i);
            Vertex v = new Vertex();
            int k = 1;
            while (!reponse.equals("o")) {
                System.out.println("Entrer le nom du sommet");
                nom = in.nextLine();
                v.setName(nom);
                System.out.println("Entrer l'index du sommet");
                index = in.nextInt();
                v.setIndex(index);
                System.out.println("Entrer la position du sommet");
                position.x = in.nextInt();
                position.y = in.nextInt();
                v.setPosition(position);
                System.out.println("Entrer la valeur du sommet");
                value = in.nextDouble();
                v.setValue(value);
                fs.set(k++, v);
                System.out.println("Encore un prédécésseur du sommet " + i + "? o/n");
                String rep = new String();
                reponse = in.nextLine();
            }
            Vertex f = new Vertex("fin", 0.0, 0, new Point());
            fs.set(k, f);

        }
    }

        public boolean readFromFile(String fileName){
        Scanner lecteur=null;
        int[][] tableau;//le tableau où stocker tes résultats. Tu peux aussi utiliser un ArrayList
        try {
            int i = 0;
            lecteur = new Scanner(new FileReader(fileName+".txt"));
            while(lecteur.hasNextLine()) {
                String ligne = lecteur.nextLine();
                String[] ligneTableau = ligne.split("\t");//transforme par exemple "aaa;bbb;ccc" en {"aaa","bbb","ccc"}
                int taille = ligneTableau.length;
                tableau = new int[i][taille];
                for(int j=0;j<taille;++j) {
                    tableau[i][j] = Integer.parseInt(ligneTableau[j]);
                }
                i++;
            }
            return true;
        }
        catch (Exception e) {
            System.out.println("erreur de lecture");
            return false;
        }
    }

    @Override
    public void readFromGUI() {
            System.out.println("a");
    }

    @Override
    public void displayOnConsole() {
            System.out.println("a");
    }

    @Override
    public boolean writeToFile(String fileName) {
        try{
            File f =new File(fileName+".txt"); // définir l'arborescence
            f.createNewFile();
            FileWriter file=new FileWriter(f);
            file.write(numberOfVertex);  // écrire une ligne dans le fichier resultat.txt
            file.write("\n"); // forcer le passage à la ligne
            for(int i=1;i<numberOfVertex;++i){
                int k = 1;
                while( (fs.get(k)).getIndex()!=0 ){
                    file.write(k);
                    file.write("\t");
                    k++;
                }
                file.write("\n");
                k++;
                file.write( (fs.get(k)).getIndex() );
                file.write("\t");
                k++;
            }
            file.close(); // fermer le fichier à la fin des traitements
            return true;
        } catch (Exception e){
            return false;
        }

        }


    @Override
    public void displayOnGUI() {

    }
}
