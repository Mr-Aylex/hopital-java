package entity;

import java.sql.Date;

public class Rdv {
	protected int id;
	protected int id_patient;
	protected int id_medecin;
	protected int id_motif;
	protected Date date_rdv;
	protected int heure_id;
	
	public Rdv(int id, int id_patient, int id_medecin, int id_motif, Date date_rdv, int heure_id) {
		this.id = id;
		this.id_patient = id_patient;
		this.id_medecin = id_medecin;
		this.id_motif = id_motif;
		this.date_rdv = date_rdv;
		this.heure_id = heure_id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_patient() {
		return id_patient;
	}
	public void setId_patient(int id_patient) {
		this.id_patient = id_patient;
	}
	public int getId_medecin() {
		return id_medecin;
	}
	public void setId_medecin(int id_medecin) {
		this.id_medecin = id_medecin;
	}
	public int getId_motif() {
		return id_motif;
	}
	public void setId_motif(int id_motif) {
		this.id_motif = id_motif;
	}
	public Date getDate_rdv() {
		return date_rdv;
	}
	public void setDate_rdv(Date date_rdv) {
		this.date_rdv = date_rdv;
	}
	public int getHeure_id() {
		return heure_id;
	}
	public void setHeure_id(int heure_id) {
		this.heure_id = heure_id;
	}
	
	
}
