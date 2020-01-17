package API;
import level2.TV;
import level2.Registratore;
import level2.GestoreCanale;

public class Application {

    TV tv;
    GestoreCanale gestore;
    Registratore registratore;

    public Application() {
        tv = new TV();
        gestore = new GestoreCanale();
        registratore = new Registratore();
    }

    /*public void show(Integer programma) {
        tv.show(programma);
    }*/

}
