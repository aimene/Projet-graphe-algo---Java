package Algorithm;

import principal.Graph;

public class GraphAlgorithm {

    //Algo des distances

    public int[] distance(Graph g, int s, int[] distance){

        int[] aps = g.getAPS();
        int[] fs = g.getFS();
       int n =    aps[0] ;
        int[] fileAttente =  new int[n+1];
        int tete = 0, queue = 1,p = 1, k=0;
        distance[0] = //n ;
        for(int i=1;i <= n; i++){
            distance[i] = -1;
        }

        distance[1] = 0;

        fileAttente[queue] = s;

        while(tete < queue){
            k++;

            for(int i = tete+1; i<= queue;i++){

                int suiv = aps[fileAttente[i]];
                while( aps[suiv] != 0){
                    if(distance[fs[suiv]] == -1){

                        distance[fs[suiv]] = k;
                        p++;
                        fileAttente[p] = fs[suiv];
                    }
                    suiv++;
                }
            }
            tete = queue;
            queue = p;
        }

        return distance;

    }

    public int[][] distanceMatrix(int s){
        int[] aps = g.getAPS();
        int n = aps[0];
         int[][] mDist = new int[n+1][n+1];
         mDist[0] = new int[1];
         mDist[0][0] = n;
        //int n = //getAPS[0] ;
        for(int i=1; i < n; i++){
            distance(s,mDist[i]);

        }
        return mDist;
    }




//Algo des rangs




    public int[] rang(Graph g){
        int[] aps = g.getAPS();
        int[] fs = g.getFS();
        int[] ddi = g.getDDI();

        int n = aps[0], taillefs = fs[0], s=0,k=0,h=0,t=0;

        int[] rang = new int[n+1];
        int[] prem = new int[n+1];
        int[] pilch = new int[n+1];

        for(int i =1; i <= n; i++){

        }

        pilch[0]=0;
        for(s = 1;s <=n; s++){
            rang[s] = -1;
            if(ddi[s] == 0) {
                pilch[s] = pilch[0];
                pilch[0] = s;
            }
        }

        k =-1;
        s = pilch[0];
        prem[0] = s;

        while( s > 0 ){
            k++;
            pilch[0] = 0;
            while(s >0){
                rang[s] = k;
                h = aps[s];
                t = fs[h];
                while(t >0){
                    ddi[t]--;
                    if(ddi[t] == 0) {
                        pilch[t] = pilch[0];
                        pilch[0] = t;
                    }
                    h++;
                    t = fs[h];
                }
                s = pilch[s];
            }
            s = pilch[0];
            prem[k+1] = s;

        }


        return rang;

    }


    //Calculer la matrice des Cout avec le tableau qui contient les poids des différentes arrêtes, arc
    public int[] Dijkstra(Graph g,int[][]Cout ,int s){
        int[] aps = g.getAPS();
        int[] fs = g.getFS();
        int n = aps[0];
        int [] distance = new int[n+1];
        int [] pred = new int[n+1];
        int v=0,j=0,max=0;
        final int MAXPOIDS = -1;

        distance[0] = n;
        pred[0] = n;
        boolean [] tabBool = new boolean[n+1];

        for(int i=1; i<=n;i++){
            distance[i] = Cout[s][i];
            pred[i] = 0;
            tabBool[i] = true;
        }

        tabBool[s] = false;
        distance[s] =0;

        for(int cpt = 0; cpt<n; cpt++){
            max = MAXPOIDS;
            for(int i=1; i<=n;i++){
                if(tabBool[i] && distance[i]<max){
                    max = distance[i];
                    j=i;
                }
                if(distance[j] != MAXPOIDS){

                    tabBool[j] = false;
                    for(int l = aps[j]; (k = fs[l]!=0) ;l++){
                        if(tabBool[k]){
                            v = distance[j] + Cout[j][k];
                            if(v < distance[k]){
                                distance[k] = v;
                                pred[k] = j;
                            }
                        }
                    }
                }else{
                    return distance;
                }

            }
        }

      return distance;
    }




    //Codage de Prüfer
    // Utilise la matrice d'adjacence

    public int[] codagePrufer(Graph g){
        int[][] matAdj = g.AdjMat(); // On récupère la matrice d'adjacence
        int n = matAdj[0][0];
        
        int[] codage  = new int[n-1];
        codage[0] = n-2;

        for (int i = 1; i <= n ; i++) {
            matAdj[i][0] = 0;
        }

        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= n; j++) {
                if (matAdj[i][j] == 1) matAdj[i][0]++;
            }
        }

            for(int k=1; k <=n-2; k++){
                int i=1, j=1;

                for(;matAdj[i][0] != 1;i++);
                for(;matAdj[i][j] != 1;j++);

                codage[k] = j;
                matAdj[i][j] = 0;
                matAdj[j][i] = 0;
                matAdj[i][0] = 0;
                matAdj[j][0]-- ;


            }

        return codage;
        
    }



    // Algorithme de Kruskal

    //Algorithme de l'ordonnancement

















}
