package entity;

public class DossierPatient {
    private int id;
    private int id_patient;
    private String adresse_post;
    private String mutuelle;
    private String num_ss;
    private String opt;
    private String regime;

    public DossierPatient(int id, int id_patient, String adresse_post, String mutuelle, String num_ss, String opt, String regime) {
        this.id = id;
        this.id_patient = id_patient;
        this.adresse_post = adresse_post;
        this.mutuelle = mutuelle;
        this.num_ss = num_ss;
        this.opt = opt;
        this.regime = regime;
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

    public String getAdresse_post() {
        return adresse_post;
    }

    public void setAdresse_post(String adresse_post) {
        this.adresse_post = adresse_post;
    }

    public String getMutuelle() {
        return mutuelle;
    }

    public void setMutuelle(String mutuelle) {
        this.mutuelle = mutuelle;
    }

    public String getNum_ss() {
        return num_ss;
    }

    public void setNum_ss(String num_ss) {
        this.num_ss = num_ss;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }
}
