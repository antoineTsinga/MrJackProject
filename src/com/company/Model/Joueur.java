package com.company.Model;

public class Joueur {
    private String type;//s'il s'agit d'un enqueteur ou de MrJack

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean partiGagnee(){
        return false;
    }
}
