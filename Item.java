//v1.0
//Diego Javier Ríos Sánchez
//Propósito: Gestionar los elementos de la mochila


class Item {
    private int weight;
    private int value;

    public Item ( int weight , int value ) {
        this.weight = weight;
        this.value = value;
    }

    public int getWeight () {
        return weight;
    }

    public void setWeight ( int weight ) {
        this.weight = weight;
    }

    public int getValue () {
        return value;
    }

    public void setValue ( int value ) {
        this.value = value;
    }
}
