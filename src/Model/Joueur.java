package Model;

public class Joueur {
    private String couleur;
    private final Jeu jeu;

    public Joueur(String couleur, Jeu jeu) {
        this.couleur = couleur;
        this.jeu = jeu;
    }

    public String getCouleur(){
        return couleur;
    }

    public void setCouleur(String col){
        couleur = col;
    }

    public Coup getCoup() throws InterruptedException {
        synchronized (jeu){
            jeu.wait();
        }

        return jeu.getNextCoup();
    }

}
