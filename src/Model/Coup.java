package Model;

public class Coup {

    private Point depart;
    private Point arrivee;
    private Joueur joueur;

    public Coup(Point depart,Point arrivee, Joueur joueur) {
        this.depart = depart;
        this.arrivee = arrivee;
        this.joueur = joueur;
    }

    public Point getDepart() {
        return depart;
    }
    public Point getArrivee(){
        return arrivee;
    }

    public Joueur getJoueur() {
        return joueur;
    }

}
