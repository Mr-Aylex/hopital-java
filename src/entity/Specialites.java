package entity;

public class Specialites {
	protected int id;
	protected String nom_spe;
	
	public Specialites(int id, String nom_spe) {
		this.id = id;
		this.nom_spe = nom_spe;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNomSpe() {
		return nom_spe;
	}
	
	public void setNomSpe(String nom_spe) {
		this.nom_spe = nom_spe;
	}
}
