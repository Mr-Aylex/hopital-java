package entity;

public class Medecin {
	protected int id;
	protected int id_user;
	protected int id_specialite;
	protected String telephone;
	protected String lieu;
	
	public Medecin(int id, int id_user, int id_specialite, String telephone, String lieu) {
		this.id = id;
		this.id_user = id_user;
		this.id_specialite = id_specialite;
		this.telephone = telephone;
		this.lieu = lieu;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdUser() {
		return id_user;
	}
	
	public void setIdUser(int id_user) {
		this.id_user = id_user;
	}
	
	public int getIdSpe() {
		return id_specialite;
	}
	
	public void setIdSpe(int id_specialite) {
		this.id_specialite = id_specialite;
	}
	
	public String getTelephone() {
		return telephone;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public String getLieu() {
		return lieu;
	}
	
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
}
