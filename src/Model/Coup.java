package Model;

public class Coup {

    private Point depart;
    private Point arrivee;

    public Coup(Point depart,Point arrivee) {
        this.depart = depart;
        this.arrivee = arrivee;
    }

    public Point getDepart() {
        return depart;
    }
    public Point getArrivee(){
        return arrivee;
    }

}
