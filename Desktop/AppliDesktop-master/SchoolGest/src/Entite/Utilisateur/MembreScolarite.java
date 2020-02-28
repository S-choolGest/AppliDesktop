/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite.Utilisateur;

import java.util.Date;

/**
 *
 * @author Ahmed
 */
public class MembreScolarite {
    private int idMSco;
    private String nomMsco;
    private String prenomMsco;
    private int cin;
    private Date dateNaisSco;
    private int telsco;
    private int email;

    public MembreScolarite() {
    }

    public MembreScolarite(int idMSco, String nomMsco, String prenomMsco, int cin, Date dateNaisSco, int telsco, int email) {
        this.idMSco = idMSco;
        this.nomMsco = nomMsco;
        this.prenomMsco = prenomMsco;
        this.cin = cin;
        this.dateNaisSco = dateNaisSco;
        this.telsco = telsco;
        this.email = email;
    }

    public int getIdMSco() {
        return idMSco;
    }

    public String getNomMsco() {
        return nomMsco;
    }

    public String getPrenomMsco() {
        return prenomMsco;
    }

    public int getCin() {
        return cin;
    }

    public Date getDateNaisSco() {
        return dateNaisSco;
    }

    public int getTelsco() {
        return telsco;
    }

    public int getEmail() {
        return email;
    }

    public void setIdMSco(int idMSco) {
        this.idMSco = idMSco;
    }

    public void setNomMsco(String nomMsco) {
        this.nomMsco = nomMsco;
    }

    public void setPrenomMsco(String prenomMsco) {
        this.prenomMsco = prenomMsco;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public void setDateNaisSco(Date dateNaisSco) {
        this.dateNaisSco = dateNaisSco;
    }

    public void setTelsco(int telsco) {
        this.telsco = telsco;
    }

    public void setEmail(int email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "MembreScolarite{" + "idMSco=" + idMSco + ", nomMsco=" + nomMsco + ", prenomMsco=" + prenomMsco + ", cin=" + cin + ", dateNaisSco=" + dateNaisSco + ", telsco=" + telsco + ", email=" + email + '}';
    }
    
    
    
}
