package entity;

public class Medicaments {
	protected int id;
	protected String nom;
	protected String toxicite;
	protected int nb;
	
	public Medicaments(int id, String nom, String toxicite, int nb) {
		this.id = id;
		this.nom = nom;
		this.toxicite = toxicite;
		this.nb = nb;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getTocixite() {
		return toxicite;
	}
	
	public void setToxicite(String toxicite) {
		this.toxicite = toxicite;
	}
	
	public int getNb() {
		return nb;
	}
	
	public void setNb(int nb) {
		this.nb = nb;
	}
	
	@Override
    public String toString() {
        return "Medicaments [nom="+nom+", toxicite="+toxicite+", nb="+nb+"]";
    }
}
