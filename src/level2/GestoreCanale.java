package level2;
import level3.Canale;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class GestoreCanale {


    /*
    STATO ASTRATTO:
        il gestore dei canali, li contiene l'insiemee li gestisce:
        consente aggiunta modifica e rimozione

    STATO CONCRETO:
        il gestore canali contiene una HashMap<Integer, Canale>
        associa a ogni numero un canale

    INVARIANTE
        HashMap può essere nullo (non ci sono canali) ma se non è nullo:
        ogni suo elemento chiave-valore è composto da chiavi-valori non nulli
     */

    HashMap<Integer, Canale> canali;

    public GestoreCanale(HashMap<Integer, Canale> canali) {
        if(canali != null) {
            List<Integer> keys = canali.keySet().stream().filter(i -> i == null).collect(Collectors.toList());
            List<Canale> values = canali.values().stream().filter(c -> c == null).collect(Collectors.toList());
            if(!(keys == null && values == null)) {
                throw new NullPointerException("ogni canale deve avere chiave e valore non nulli");
            }
        }
        this.canali = canali;
    }

    public GestoreCanale() {
        canali = new HashMap<Integer, Canale>();
    }

    public HashMap<Integer, Canale> getCanali() {
        return canali;
    }

    public void addCanale(Integer programma, Canale canale) {
        this.canali.put(programma, canale);
    }

    public void removeTrasmissioni(Canale canale) {
        this.canali.remove(canale);
    }

}
