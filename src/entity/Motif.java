package entity;

public class Motif {
	private int id;
    private String nom_motif;
    private int id_spe;

    public Motif(int id, String nom_motif, int id_spe) {
        this.id = id;
        this.nom_motif = nom_motif;
        this.id_spe = id_spe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_motif() {
        return nom_motif;
    }

    public void setNom_motif(String nom_motif) {
        this.nom_motif = nom_motif;
    }

    public int getId_spe() {
        return id_spe;
    }

    public void setId_spe(int id_spe) {
        this.id_spe = id_spe;
    }
}
