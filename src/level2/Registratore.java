package level2;
import level4.Trasmissione;
import java.util.List;

public class Registratore {

    List<Trasmissione> trasmRegistate;
    List<Trasmissione> trasmDaRegistare;

    public List<Trasmissione> getTrasmRegistate() {
        return trasmRegistate;
    }

    public void setTrasmRegistate(List<Trasmissione> trasmRegistate) {
        this.trasmRegistate = trasmRegistate;
    }

    public List<Trasmissione> getTrasmDaRegistare() {
        return trasmDaRegistare;
    }

    public void setTrasmDaRegistare(List<Trasmissione> trasmDaRegistare) {
        this.trasmDaRegistare = trasmDaRegistare;
    }
}
