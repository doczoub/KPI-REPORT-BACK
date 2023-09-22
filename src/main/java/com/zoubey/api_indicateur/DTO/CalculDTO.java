package com.zoubey.api_indicateur.DTO;

public class CalculDTO {

        private int anneeCalcul;
        private String periode;
        private String resultatObtenu;
        private String Norme;



        public CalculDTO(int anneeCalcul, String periode, String resultatObtenu, String Norme) {
            this.anneeCalcul = anneeCalcul;
            this.periode = periode;
            this.resultatObtenu = resultatObtenu;
            this.Norme = Norme;
        }


    public int getAnneeCalcul() {
            return anneeCalcul;
        }

        public void setAnneeCalcul(int anneeCalcul) {
            this.anneeCalcul = anneeCalcul;
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

        public String getNorme() {
            return Norme;
        }

        public void setNorme(String norme) {
            this.Norme = norme;
        }

}
