package com.zoubey.api_indicateur.DTO;

public class CalculIndicateurDto {
        private String NomMesure;
        private String Norme;
        private String periodicite;
        private String periode;
        private String resultatObtenu;

        public CalculIndicateurDto(String NomMesure, String Norme, String periodicite, String periode, String resultatObtenu) {
            this.NomMesure = NomMesure;
            this.Norme = Norme;
            this.periodicite = periodicite;
            this.periode = periode;
            this.resultatObtenu = resultatObtenu;
        }


        // Getters and setter


    public String getNomMesure() {
        return NomMesure;
    }

    public void setNomMesure(String nomMesure) {
        this.NomMesure = nomMesure;
    }

    public String getNorme() {
        return Norme;
    }

    public void setNorme(String norme) {
        this.Norme = norme;
    }

    public String getPeriodicite() {
        return periodicite;
    }

    public void setPeriodicite(String periodicite) {
        this.periodicite = periodicite;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public String getResultatObtenu() {
        return resultatObtenu;
    }

    public void setResultatObtenu(String resultatObtenu) {
        this.resultatObtenu = resultatObtenu;
    }
}
