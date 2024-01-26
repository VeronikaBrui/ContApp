package com.example.contapp2;

public class Model {
    private String contname;
    private String contphone;
    private String contemail;
    private int id;


    public Model(int id, String contname, String contphone, String contemail) {
        this.id = id;
        this.contname = contname;
        this.contphone = contphone;
        this.contemail = contemail;
    }


    public String getContname() {
        return contname;
    }

    public void setContname(String contname) {
        this.contname = contname;
    }

    public String getContphone() {
        return contphone;
    }

    public void setContphone(String contphone) {
        this.contphone = contphone;
    }

    public String getContemail() {
        return contemail;
    }

    public void setContemail(String contemail) {
        this.contemail = contemail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
