package level2;

import java.util.Objects;

public class TV {

    /*
    INVARIANTE:
        canaleSelezSecond == NULL se e solo se doppioSchermo == false
     */

    int volume;
    int luminosita;
    String lingua;
    boolean doppioSchermo;
    Integer canaleSelezPrinc;
    Integer canaleSelezSecond;

    public TV() { }

    public TV(int volume, int luminosita, String lingua, boolean doppioSchermo, Integer canaleSelezPrinc, Integer canaleSelezSecond) {
        this.volume = volume;
        this.luminosita = luminosita;
        this.lingua = lingua;
        this.doppioSchermo = doppioSchermo;
        this.canaleSelezPrinc = canaleSelezPrinc;
        this.canaleSelezSecond = canaleSelezSecond;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getLuminosita() {
        return luminosita;
    }

    public void setLuminosita(int luminosita) {
        this.luminosita = luminosita;
    }

    public String getLingua() {
        return lingua;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    public boolean isDoppioSchermo() {
        return doppioSchermo;
    }

    /**
     * cambia impostazione del doppio schermo
     * se true canale secondario deve essere NOT NULL
     * se false canale secondario verrà impostato a NULL (e il valore parametro superfluo)
     * @param doppioSchermo vero: doppio schermo abilitato
     * @param canaleSecondario se doppio schermo abilitato, indica il secondo canale da vedere
     * @throws NullPointerException se doppioSchermo == true && canale == NULL
     */
    private void setDoppioSchermo(boolean doppioSchermo, Integer canaleSecondario) throws NullPointerException {
        if(doppioSchermo) {
            Objects.requireNonNull(canaleSecondario);
            canaleSelezSecond = canaleSecondario;
        } else {
            canaleSelezSecond = null;
        }
        this.doppioSchermo = doppioSchermo;
    }

    public Integer getCanaleSelezPrinc() {
        return canaleSelezPrinc;
    }

    public Integer getCanaleSelezSecond() {
        return canaleSelezSecond;
    }

    //todo not null
    public void show(Integer programma) {
        setDoppioSchermo(false, null);
        canaleSelezPrinc = programma;
        //TO DO
    }

    //todo not null
    public void show(Integer programmaPrincipale, Integer programmaSecondario) {
        setDoppioSchermo(true, programmaSecondario);
        canaleSelezPrinc = programmaPrincipale;
        canaleSelezSecond = programmaSecondario;
        //TO DO
    }
}
