package com.company.Model;

public class JetonAction {
    Action recto ;
    Action verso ;
    Boolean rectoIsVisible;

    public Action getRecto() {
        return recto;
    }
    public Action getVerso() {
        return verso;
    }
    public Boolean getRectoIsVisible() {
        return rectoIsVisible;
    }
    public void setRectoIsVisible(Boolean rectoIsVisible) {
        this.rectoIsVisible = rectoIsVisible;
    }
    public JetonAction(Action recto, Action verso, Boolean rectoIsVisible) {
        this.recto = recto;
        this.verso = verso;
        this.rectoIsVisible = rectoIsVisible;
    }

}

