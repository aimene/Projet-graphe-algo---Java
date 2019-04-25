package Graphique;

import Algorithm.GraphAlgorithm;
import com.sun.deploy.panel.JavaPanel;
import principal.Edge;
import principal.Vertex;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class GraphiqueGraphe extends JPanel implements MouseListener,MouseMotionListener {

    //Constante recouvrant les différentes actions réaisables avec la souris
    static final int VERTEX_CREATION = 1;
    static final int VERTEX_LABELING = 2;
    static final int START_MOVING_VERTEX = 3;
    static final int NEXT_MOVING_VERTEX = 4;
    static final int START_EDGES_CREATION = 5;
    static final int NEXT_EDGES_CREATION= 6;
    static final int ATTRIBUTE_VALUE_TO_EDGES = 7;
    static final int DELETE_VERTEX = 8 ;
    static final int DELETE_EDGES = 9 ;
    static final int ALGO_DISTANCE = 10;
    static final int ALGO_RANGS = 11;
    static final int ALGO_DIJKSTRA = 12;
    static final int ALGO_KRUSKAL = 13;
    static final int ALGO_ORDONANCEMENT = 14;
    static final int ALGO_PRUFER = 15;
    static final int ALGO_TARJAN = 16;

    static final int VERY_CLOSE = 10;

    static final String INFO_DISTANCE =  "Donner la matrice des distances de ce graphe, c'est-à-dire\tla matrice dont le terme situé à l'intersection des i-ième ligne et\tj-ième colonne est égal à la distance du sommet i au sommet j. (les distances infinies sont notées -1.)\n" +
            "En déduire le diamètre du graphe (répondre \"inf\" si ce diamètre est infini).\n" +
            "Dire enfin si ce graphe est connexe.";
    static final String INFO_RANGS =  "Le rang est une valeur numérique associée à chaque sommet, telle que le rang d'un sommet est toujours strictement supérieur à celui de ses prédécesseurs. Ce rang permet d'ordonner les sommets pour faciliter l'exécution de certains traitements spécifiques (par exemple, calcul des dates au plus tôt pour un problème d'ordonnancement). ";
    static final String INFO_DIJKSTRA = " L'algorithme de Dijkstra sert à résoudre le problème du plus court chemin. Il permet, par exemple, de déterminer un plus court chemin pour se rendre d'une ville à une autre connaissant le réseau routier d'une région.Plus précisément, il calcule des plus courts chemins à partir d'une source dans un graphe orienté pondéré par des réels positifs. ";
    static final String INFO_TARJAN =  "En théorie des graphes, l'algorithme de Tarjan permet de déterminer les composantes fortement connexes d'un graphe orienté. " ;
    static final String INFO_ORDONNANCEMENT =  "La théorie de l'ordonnancement est une branche de la recherche opérationnelle qui s'intéresse au calcul de dates d'exécution optimales de tâches. Pour cela, il est très souvent nécessaire d'affecter en même temps les ressources nécessaires à l'exécution de ces tâches. Un problème d'ordonnancement peut être considéré comme un sous-problème de planification dans lequel il s'agit de décider de l'exécution opérationnelle des tâches planifiées.";
    static final String INFO_KRUSKAL =  "L'algorithme de Kruskal est un algorithme de recherche d'arbre recouvrant de poids minimum (ARPM) ou arbre couvrant minimum (ACM) dans un graphe connexe non-orienté et pondéré. Ce problème a de nombreuses applications, par exemple simplifier un câblage ou supprimer les liaisons maritimes les moins rentables en préservant l'accessibilité aux différents ports. ";
    static final String INFO_PRUFER =  " En mathématiques, le codage de Prüfer est une méthode pour décrire de façon compacte un arbre dont les sommets sont numérotés1. Ce codage représente un arbre de n sommets numérotés avec une suite {\\displaystyle P=(x_{1},x_{2},x_{3},...,x_{n-2})} P=(x_{1},x_{2},x_{3},...,x_{{n-2}}) de n-2 termes. Une suite P donnée correspond à un et un seul arbre numéroté de 1 à n.";


    JLabel stateBar;
    int state;
    Vertex vertexSelected;
    JPanel menuBouton = new JPanel();

    //Fonction principal
    public GraphiqueGraphe(){

        this.setBackground(Color.WHITE);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
         stateBar = new JLabel("Choisir une action");
         stateBar.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));


        JFrame cadre = new JFrame("Partie Graphique");
        cadre.setBounds(100, 50, 600, 500);
        cadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cadre.add(this, BorderLayout.CENTER);
        cadre.add(stateBar, BorderLayout.SOUTH);
        cadre.setJMenuBar(createBarMenu());
        cadre.setVisible(true);

    }

    //Création de la barre de menu
    JMenuBar createBarMenu() {
        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("Fichier");
        bar.add(menu);

        JMenuItem item = new JMenuItem("Ouvrir...");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
               load();
            }
        });
        menu.add(item);

        item = new JMenuItem("Enregistrer...");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                save();
            }
        });
        menu.add(item);

        menu.addSeparator();
        item = new JMenuItem("Quitter");
        menu.add(item);

        menu = new JMenu("Actions");
        bar.add(menu);

        item = new JMenuItem("Creer sommet");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                state = VERTEX_CREATION;
                refreshStateBar();
            }
        });
        menu.add(item);

        item = new JMenuItem("Etiqueter sommet");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                state = VERTEX_LABELING;
                refreshStateBar();
            }
        });
        menu.add(item);

        item = new JMenuItem("Déplacer sommet");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                state = START_MOVING_VERTEX;
                refreshStateBar();
            }
        });
        menu.add(item);

        item = new JMenuItem("Creer arête");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                state = START_EDGES_CREATION;
                refreshStateBar();
            }
        });
        menu.add(item);

        item = new JMenuItem("Modifier poids arrête");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                state = ATTRIBUTE_VALUE_TO_EDGES;
                refreshStateBar();
            }
        });
        menu.add(item);

        item = new JMenuItem("Supprimer sommet");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                state = DELETE_VERTEX;
                refreshStateBar();
            }
        });
        menu.add(item);

        item = new JMenuItem("Supprimer arrête");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                state = DELETE_EDGES;
                refreshStateBar();
            }
        });
        menu.add(item);


        menu = new JMenu("Algorithme");
        bar.add(menu);

        item = new JMenuItem("Algorithme des distances ");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                state = ALGO_DISTANCE;
                refreshStateBar();
            }
        });

        item.setToolTipText(INFO_DISTANCE);

        menu.add(item);

        item = new JMenuItem("Algorithme des rangs");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // state = START_EDGES_CREATION;
                refreshStateBar();
            }
        });

        item.setToolTipText(INFO_RANGS);

        menu.add(item);

        item = new JMenuItem("Dijkstra");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // state = START_EDGES_CREATION;
                refreshStateBar();
            }
        });
        item.setToolTipText(INFO_DIJKSTRA);

        menu.add(item);

        item = new JMenuItem("Kruskal");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // state = START_EDGES_CREATION;
                refreshStateBar();
            }
        });

        item.setToolTipText(INFO_KRUSKAL);

        menu.add(item);

        item = new JMenuItem("Ordonnancement");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // state = START_EDGES_CREATION;
                refreshStateBar();
            }
        });

        item.setToolTipText(INFO_ORDONNANCEMENT);

        item = new JMenuItem("Tarjan");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // state = START_EDGES_CREATION;
                refreshStateBar();
            }
        });

        menu.add(item);

        item.setToolTipText(INFO_TARJAN);


        menu.add(item);

        item = new JMenuItem("Prufer");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // state = START_EDGES_CREATION;
                refreshStateBar();
            }
        });

        item.setToolTipText(INFO_PRUFER);

        menu.add(item);



        return bar;
    }

    void refreshStateBar(){
        switch (state) {
            case VERTEX_CREATION:
                stateBar.setText("Creation sommet : cliquer pour designer l'endroit où le poser");
                break;
            case VERTEX_LABELING:
                stateBar.setText("Etiquetage sommet : désigner le sommet à étiqueter");
                break;
            case START_MOVING_VERTEX:
                stateBar.setText("Deplacement sommet : traîner le sommet à déplacer");
                break;
            case NEXT_MOVING_VERTEX:
                stateBar.setText("Deplacement sommet : lâcher le sommet à la place voulue");
                break;
            case START_EDGES_CREATION:
                stateBar.setText("Création arête : désigner le premier sommet");
                break;
            case NEXT_EDGES_CREATION:
                stateBar.setText("Création arête : désigner le second sommet");
                break;
            case ATTRIBUTE_VALUE_TO_EDGES:
                stateBar.setText("Modifier poids arrête: cliquer sur une arrête pour modifier son poids");
                break;
            case DELETE_VERTEX:
                stateBar.setText("Suppression d'un sommet: cliquer sur un sommet pour le supprimer");
                break;
            case DELETE_EDGES:
                stateBar.setText("Suppression d'une arrête: cliquer sur une arrête pour la supprimer");
                break;
        }
    }

    Vertex neighborVertex(int x, int y) {
        Iterator iter = Vertex.iterator();
        while (iter.hasNext()) {
            Vertex s = (Vertex) iter.next();
            if (Math.abs(x - s.getPosition().x) + Math.abs(y - s.getPosition().y) < VERY_CLOSE)
                return s;
        }
        return null;
    }

     Edge neighborEdge(int x, int y){
        Iterator iter = Edge.iterator();
        while (iter.hasNext()) {
            Edge s = (Edge) iter.next();
            if (x >= (s.getVertexA().getPosition().x + s.getVertexB().getPosition().x)/2 && y >= (s.getVertexA().getPosition().y + s.getVertexB().getPosition().y)/2)
                return s;
        }
         return null;
    }


    @Override
    public void mousePressed(MouseEvent e) {

        Vertex vertex;
        Edge edge;

        switch (state) {
            case VERTEX_CREATION:
                new Vertex(e.getX(),e.getY());
                repaint();
                return;
            case  VERTEX_LABELING:
                vertex = neighborVertex(e.getX(), e.getY());
                if (vertex == null)
                    return;
                String texte = JOptionPane.showInputDialog(this, "Nouveau texte:",
                        "Définition d'une étiquette", JOptionPane.QUESTION_MESSAGE);
                if (texte != null)
                    vertex.setName(texte);
                repaint();
                return;
            case START_MOVING_VERTEX:
                vertexSelected = neighborVertex(e.getX(), e.getY());
                if (vertexSelected == null)
                    return;
                state = NEXT_MOVING_VERTEX ;
                refreshStateBar();
                return;
            case START_EDGES_CREATION:
                vertexSelected = neighborVertex(e.getX(), e.getY());
                if (vertexSelected == null)
                    return;
                state = NEXT_EDGES_CREATION;
                refreshStateBar();
                return;
            case NEXT_EDGES_CREATION:
                vertex = neighborVertex(e.getX(), e.getY());
                if (vertex == null || vertex.equals(vertexSelected))
                    return;
                String valueTexte = JOptionPane.showInputDialog(this, "Donner  une valeur à l'arrête:",
                        "Valeur de l'arrête ", JOptionPane.QUESTION_MESSAGE);
                double value = Double.parseDouble(valueTexte);

                new Edge(vertexSelected, vertex,value);
                repaint();
                state = START_EDGES_CREATION;
                refreshStateBar();
                return;

            case ATTRIBUTE_VALUE_TO_EDGES:
                edge = neighborEdge(e.getX(),e.getY());
                if (edge == null)
                    return;
                String editValue = JOptionPane.showInputDialog(this, "Modifier la valeur de l'arrête:",
                        "Valeur de l'arrête ", JOptionPane.QUESTION_MESSAGE);
                double valueEdit = Double.parseDouble(editValue);
                edge.setValue(valueEdit);
                repaint();
                return;
            case  DELETE_VERTEX:
                vertex = neighborVertex(e.getX(), e.getY());
                if (vertex == null)
                    return;
                vertex.deleteVertex();
                repaint();
                return;

            case DELETE_EDGES:
                edge = neighborEdge(e.getX(),e.getY());
                if (edge == null)
                    return;
                edge.deleteEdge();
                repaint();
                return;

            case ALGO_DISTANCE:
                GraphAlgorithm g = new GraphAlgorithm();


        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (state == NEXT_MOVING_VERTEX)
            state = START_MOVING_VERTEX;
        refreshStateBar();

    }


    @Override
    public void mouseDragged(MouseEvent e) {
        if (state == NEXT_MOVING_VERTEX) {
            Point p = new Point(e.getX(),e.getY());
            vertexSelected.setPosition(p);
            repaint();
        }

    }

    public void mouseMoved(MouseEvent evt) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    public void paint(Graphics g) {
        super.paint(g);
        Color cp = g.getColor();

        g.setColor(Color.black);
        Iterator<Edge> iterA = Edge.iterator();
        while (iterA.hasNext()) {
            Edge a = (Edge) iterA.next();
            g.drawLine(a.getVertexA().getPosition().x, a.getVertexA().getPosition().y, a.getVertexB().getPosition().x, a.getVertexB().getPosition().y);
            g.setColor(Color.black);
            String value = String.valueOf(a.getValue());
            g.drawString( value,(a.getVertexA().getPosition().x + a.getVertexB().getPosition().x)/2, (a.getVertexA().getPosition().y + a.getVertexB().getPosition().y)/2);
        }

        Iterator<Vertex> iterS = Vertex.iterator();
        while (iterS.hasNext()) {
            Vertex s = (Vertex) iterS.next();
            g.setColor(Color.red);
            g.fillOval(s.getPosition().x - 5, s.getPosition().y - 5, 10, 10);
            if (s.getName()!= null) {
                g.drawString(s.getName(), s.getPosition().x + 5, s.getPosition().y - 5);
            }
        }
        g.setColor(cp);
    }

    void save() {
        JFileChooser dial = new JFileChooser();
        int result = dial.showSaveDialog(this);
        if (result != JFileChooser.APPROVE_OPTION)
            return;
        try {
            PrintStream sortie = new PrintStream(dial.getSelectedFile());

            sortie.println(Vertex.nombreSommets());
            Map<Integer, Vertex> iterS = Vertex.getAllVertexes();
            Map< Vertex,Integer> iterSV = Vertex.getAllVertexesV();
            sortie.println(Edge.nombreAretes());
            for (int i = 0 ; i<iterS.size(); i++)
                sortie.println(iterS.get(i).getName()+" "+iterS.get(i).getValue()+" "+iterS.get(i).getPosition().x+" "+ iterS.get(i).getPosition().y);

            Edge aa ;
            Collection<Edge> iterA = Edge.getEdge();
            for (int i = 0; i < iterS.size(); i++) {
                Iterator<Edge> ee = iterA.iterator();
                boolean b = true ;
                for (int j = 1; j <= iterA.size(); j++) {
                    aa =ee.next();
                    if (iterS.get(i)==aa.getVertexA()){
                        sortie.print(iterSV.get(aa.getVertexB())+" "+aa.getValue()+" ");
                        b=false;
                    }
                }
                if(!b)
                sortie.println("");
            }

            sortie.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
    }

    StreamTokenizer analyseurLexical;

    String prochainMot() throws IOException {
        int typeUnite = analyseurLexical.nextToken();
        if (typeUnite != StreamTokenizer.TT_WORD && typeUnite != '"')
            throw new IOException("Erreur de syntaxe");
        return analyseurLexical.sval;
    }

    int prochainEntier() throws IOException {
        int typeUnite = analyseurLexical.nextToken();
        if (typeUnite != StreamTokenizer.TT_NUMBER)
            throw new IOException("Erreur de syntaxe");
        return (int) analyseurLexical.nval;
    }

    void load() {
        JFileChooser dial = new JFileChooser();
        int result = dial.showOpenDialog(this);
        if (result != JFileChooser.APPROVE_OPTION)
            return;
        try {
            analyseurLexical = new StreamTokenizer(new FileReader(dial
                    .getSelectedFile()));
            int nbrSommet = prochainEntier();
            int nbrEdge = prochainEntier();
            for (int i = 0; i < nbrSommet; i++) {

                String name = prochainMot();
                Double value =(double) prochainEntier();
                int x = prochainEntier();
                int y = prochainEntier();
                Point p = new Point(x,y);
                new Vertex(name,value,p);
            }


            for (int i = 0; i < nbrSommet; i++) {
                int indiceSuccesseur = prochainEntier();
                double value = prochainEntier();

                Vertex s1 = Vertex.trouverSommet(i);
                if (s1 == null)
                    throw new IOException(i + " sommet inexistant");
                Vertex s2 = Vertex.trouverSommet(indiceSuccesseur);
                if (s2 == null)
                    throw new IOException(indiceSuccesseur + " sommet inexistant");

                new Edge(s1, s2,value);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        repaint();
    }




}
