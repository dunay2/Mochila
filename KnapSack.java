//v1.0
//Diego Javier Ríos Sánchez
//Propósito: Solucionar el problema de la mochila

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

class KnapSack {

    private static int maxVolumen; //Copia local de volumen de mochila
    private final int[][] matrizMochila; //Matriz final con los cálculos
    private ArrayList <Item> items;//Copia local de items
    private int contaRec; //Contador para función recursiva

    //Constructor de la clase.
    //Recibe un arralist de items (con su peso y beneficio) y el volumen máximo de la mochila
    KnapSack ( ArrayList <Item> items , int maxVolumen ) {

        KnapSack.maxVolumen = maxVolumen;
        this.items = items;

        //Creamos una matriz con tamaño cantidad de elementos+1 x volumen máximo +1
        matrizMochila = new int[items.size() + 1][maxVolumen + 1];

        //Inicializamos la fila y la columna 0 a 0
        for (int i = 0; i <= items.size(); i++)
            matrizMochila[i][0] = 0; //Para todos los items volumen 0 es 0

        //Inicializamos las mochilas de valor 0
        for (int j = 0; j <= maxVolumen; j++)
            matrizMochila[0][j] = 0;

    }
///Fin Constructor

    void solveKnapSack ()
    {
        solveRecursiveKnapSack(1,1);
        printMatriz();
        solveIteraKnapSack ();
        printMatriz();

        System.out.println("Iteraciones recursivas" + contaRec);
    }

    int solveRecursiveKnapSack (int nItem,int nMochila ) {

        contaRec++;

        //caso base
        if (nItem -1== items.size())
            return 0;
        else
        {
            int itemValue = items.get(nItem-1).getValue();
            int itemWeight = items.get(nItem-1).getWeight();
            /***********************************************************************
             //si el peso del elemento actual es superior al volumen de la mochila el ítem
             // no cabe en la mochila y guardamos el beneficio del item anterior
             ***********************************************************************/

            if (itemWeight > nMochila) {
                //El nuevo elemento no cabe. Tomamos la ganancia del elemento anterior
                matrizMochila[nItem][nMochila] = matrizMochila[nItem - 1][nMochila];
            }
            // En caso contrario tomamos la ganancia máxima entre
            // A: el valor de ganancia del ítem anterior en esta submochila
            // B: o bien la ganancia del ítem anterior en la submochila diferencia de
            // la mochila actual menos el peso del ítem más la ganancia del nuevo ítem.
            // Para obtener la ganancia del ítem anterior restamos al peso de la submochila
            // actual el peso del elemento que estamos tratando

            else {
                int iGain = matrizMochila[nItem - 1][nMochila - itemWeight] + itemValue;
                //Tomamos la ganancia mayor para item-submochila actual.
                // El cálculo de incremento en la ganancia
                // o la ganancia guardada del elemento anterior
                matrizMochila[nItem][nMochila] =
                        Math.max(matrizMochila[nItem - 1][nMochila] , iGain);
            }

            if (nMochila<maxVolumen)
            {
                return solveRecursiveKnapSack(nItem  , nMochila +1);
            }
            else
            {
                nMochila=1;
                return solveRecursiveKnapSack(nItem + 1 , nMochila);
            }
        }
    }

    //public void solveKnapSack
    //Propósito : Resolver problema mochila conste asintótico ixw
    public void solveIteraKnapSack () {
        int lContador = 0; //Variable de control para llevar cuenta de las iteraciones

//Recorremos con un doble bucle los elementos en cada submochila
        for (int nItems = 1; nItems <= items.size(); nItems++) {
            {
                int itemValue = items.get(nItems - 1).getValue();
                int itemWeight = items.get(nItems - 1).getWeight();
//Recorremos las submochilas desde capacidad 1 hasta su máximo volumen
                for (int nCurrentVolume = 1; nCurrentVolume <= maxVolumen; nCurrentVolume++) {
                    lContador++;
                    /***********************************************************************
                     //si el peso del elemento actual es superior al volumen de la mochila el ítem
                     // no cabe en la mochila y guardamos el beneficio del item anterior
                     ***********************************************************************/


                    if (itemWeight > nCurrentVolume) {
                        //El nuevo elemento no cabe. Tomamos la ganancia del elemento anterior
                        matrizMochila[nItems][nCurrentVolume] = matrizMochila[nItems - 1][nCurrentVolume];
                    }
                    // En caso contrario tomamos la ganancia máxima entre
                    // A: el valor de ganancia del ítem anterior en esta submochila
                    // B: o bien la ganancia del ítem anterior en la submochila diferencia de
                    // la mochila actual menos el peso del ítem más la ganancia del nuevo ítem.
                    // Para obtener la ganancia del ítem anterior restamos al peso de la submochila
                    // actual el peso del elemento que estamos tratando

                    else {
                        int iGain = matrizMochila[nItems - 1][nCurrentVolume - itemWeight] + itemValue;
                        //Tomamos la ganancia mayor para item-submochila actual.
                        // El cálculo de incremento en la ganancia
                        // o la ganancia guardada del elemento anterior
                        matrizMochila[nItems][nCurrentVolume] =
                                Math.max(matrizMochila[nItems - 1][nCurrentVolume] , iGain);
                    }
                }
            }

        }
        System.out.println("Valor de iteraciones es " + lContador);
    }

    //Propósito: Imprimir la salida de la mochila
    void printMatriz () {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i <= items.size(); i++) {

            s = new StringBuilder();
            for (int j = 0; j <= maxVolumen; j++) {
                s.append(Integer.toString(matrizMochila[i][j]));
                // System.out.println(matrizMochila[i][j]);
            }
            System.out.println(s);
            // writeLine(s.toString());
        }
        int k = matrizMochila[items.size()][maxVolumen];
        writeLine(Integer.toString(k));
    }

    public void writeLine ( String text ) {
        PrintWriter out = null;
        try {

            out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\data\\Mochila\\test\\out1.txt" , true)));
            out.println(text);
        } catch (IOException e) {
            System.out.println("FILE NOT FOUND EXCEPTION!");
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }


}
