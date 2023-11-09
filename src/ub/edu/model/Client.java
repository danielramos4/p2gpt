package ub.edu.model;


public class Client {
    private String pwd;
    private String nom;


    public Client(String nom, String pwd) {
        this.pwd = pwd;
        this.nom = nom;
    }

    public String getPwd() {
        return pwd;
    }

    public String getName() {
        return nom;
    }

    public void setName(String nom) {
        this.nom = nom;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

}
