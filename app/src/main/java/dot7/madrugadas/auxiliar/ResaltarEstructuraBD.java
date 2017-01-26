package dot7.madrugadas.auxiliar;

import io.realm.RealmObject;

public class ResaltarEstructuraBD extends RealmObject {

    private int inicio;
    private int fin;
    private int numLeccion;



    public int getNumLeccion() {
        return numLeccion;
    }

    public void setNumLeccion(final int numLeccion) {
        this.numLeccion = numLeccion;
    }

    public int getFin() {
        return fin;
    }

    public void setFin(final int fin) {
        this.fin = fin;
    }

    public int getInicio() {
        return inicio;
    }

    public void setInicio(final int inicio) {
        this.inicio = inicio;
    }

}