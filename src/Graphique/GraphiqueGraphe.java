package Graphique;

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

    static final int VERY_CLOSE = 10;

    JLabel stateBar;
    int state;
    Vertex vertexSelected;

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
              //  restauration(); // load
            }
        });
        menu.add(item);

        item = new JMenuItem("Enregistrer...");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                save(); // Save()
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
                refreshStateBar(); //refreshStateBar()
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


    @Override
    public void mousePressed(MouseEvent e) {

        Vertex sommet;

        switch (state) {
            case VERTEX_CREATION:
                new Vertex(e.getX(),e.getY());
                repaint();
                return;
            case  VERTEX_LABELING:
                sommet = neighborVertex(e.getX(), e.getY());
                if (sommet == null)
                    return;
                String texte = JOptionPane.showInputDialog(this, "Nouveau texte:",
                        "Définition d'une étiquette", JOptionPane.QUESTION_MESSAGE);
                if (texte != null)
                sommet.setName(texte);
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
                sommet = neighborVertex(e.getX(), e.getY());
                if (sommet == null || sommet.equals(vertexSelected))
                    return;
                new Edge(vertexSelected, sommet);
                repaint();
                state = START_EDGES_CREATION;
                refreshStateBar();
                return;
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (state == NEXT_MOVING_VERTEX)
            state = NEXT_MOVING_VERTEX;
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
        Iterator iterA = Edge.iterator();
        while (iterA.hasNext()) {
            Edge a = (Edge) iterA.next();
            g.drawLine(a.getVertexA().getPosition().x, a.getVertexB().getPosition().y, a.getVertexB().getPosition().x, a.getVertexB().getPosition().y);
        }

        Iterator iterS = Vertex.iterator();
        while (iterS.hasNext()) {
            Vertex s = (Vertex) iterS.next();
            g.setColor(Color.red);
            g.fillOval(s.getPosition().x - 5, s.getPosition().y - 5, 10, 10);
            if (s.getName()!= null) {
                g.setColor(Color.blue);
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
            Iterator iterS = Vertex.iterator();
            while (iterS.hasNext())
                sortie.println(iterS.next());

            sortie.println(Edge.nombreAretes());
            Iterator iterA = Edge.iterator();
            while (iterA.hasNext())
                sortie.println(iterA.next());
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





}
