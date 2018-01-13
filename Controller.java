import java.util.ArrayList;
import java.util.Objects;

class Controller

{
    private static final int NUM_CONFIG_LINES = 1;
    private final String outFile = "";
    private final boolean trace = false;
    private final ArrayList <Item> items = new ArrayList <>();

    Controller ( String inFile ) {

        HandleFile handleFile = new HandleFile(inFile , "C:\\data\\Mochila\\test\\out1.txt");

        //Procedemos a la lectura del archivo de datos
        try {
            handleFile.readFile();
        } catch (Exception e) {
            //Mostrar error
            System.out.println(e.getMessage());
            System.exit(-1);
        } finally {
            //Extraemos los datos del fichero de texto y los guardamos en textMatrix, arraylist de string
            ArrayList <String> textMatrix = handleFile.getMatrix();

            //Extraemos los parámetros del array
            String[] parameters = new String[NUM_CONFIG_LINES];
            for (int i = 0; i < NUM_CONFIG_LINES; i++)//leemos los parametros
                parameters[i] = textMatrix.get(i);

            //Eliminamos los elementos que son parámetros de textMatrix
            for (int i = 0; i < NUM_CONFIG_LINES; i++)
                textMatrix.remove(0);


            ArrayList <Item> items = new ArrayList <>();

            for (int i = 0; i < Integer.parseInt(parameters[0]); i++) {
                String lineValues = textMatrix.get(i);

                String[] splitedValues = lineValues.split("\\s+");

                items.add(new Item(Integer.parseInt(splitedValues[0]) , Integer.parseInt(splitedValues[1])));
            }

            KnapSack knapSack = new KnapSack(items , Integer.parseInt(textMatrix.get(textMatrix.size() - 1)));
           // knapSack.solveKnapSack();
            knapSack.solveKnapSack();
         //   knapSack.printMatriz();

        }


    }


    public static void main ( String args[] ) {
        //controlamos el número de parámetros: 1 || 2 || 3
        //   validar primero que todos los parametros estan ok


        if (args.length > 0) {
            String string = args[0];
            switch (args.length) {
                case 1:
                    if (string.indexOf("-h") == 0) {
                        printHelp(); //muestra la ayuda y salimos
                    } else {
                        System.out.println("Asumimos que tenemos el fichero de entrada" + string.indexOf("-h"));
                        Controller mochila = new Controller(args[0]);

                    }
                    break;
                case 2:
                    if (string.indexOf("-h") == 0) {
                        printHelp(); //muestra la ayuda y salimos
                        break;

                    }

                    if (string.indexOf("-t") == 0) {
                        System.out.println("Asumimos que tenemos la entrada de fichero con traza en pantalla");
                        Controller backpack = new Controller(args[1]);
                        setTrace();


                    } else {
                        System.out.println("Asumimos que tenemos la entrada de dos ficheros, entrada y salida");
                        Controller backpack = new Controller(args[0]);


                    }
                    break;
                case 3:
                    if (string.indexOf('-') == 0) {

                        if (Objects.equals(string , "-t")) {
                            System.out.println("Asumimos que tenemos entrada de dos ficheros y salida de traza en fichero");
                            Controller backpack = new Controller(args[1]);
                            setTrace();

                            break;
                        }

                        if (Objects.equals(string , "-h")) {
                            printHelp(); //muestra la ayuda y salimos
                            break;
                        }
                        //que salte al mensaje de error
                    }

                default:
                    System.out.println("Entrada no válida");
            }

        } else {
            System.out.println("Entrada no válida");

        }
    }

    //Propósito: Mostrar la traza
    private static void setTrace () {
        // trace = true;
    }

    //Propósito: mostrar la ayuda
    private static void printHelp () {
        System.out.println("SINTAXIS");
        System.out.println("mochila-dinamica [-t][-h][fichero_entrada] [fichero_salida]");
        System.out.println("-t Traza la aplicación del algoritmo a los datos");
        System.out.println("-h Muestra esta ayuda");
        System.out.println("fichero_entrada Nombre del fichero de entrada");
        System.out.println("fichero_salida Nombre del fichero de salida");
    }


}
