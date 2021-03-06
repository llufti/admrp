/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Luciano
 */

public class Hinos {

    private Integer idHino;
    private String nome;
    private int idSetor;
    private String nomeLouvor;
    private String nomeCantor;
    private String loginSonoplasta;
    private String numeroHino ;

    public Integer getIdHino() {
        return idHino;
    }

    public void setIdHino(Integer idHino) {
        this.idHino = idHino;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(int idSetor) {
        this.idSetor = idSetor;
    }

    public String getNomeLouvor() {
        return nomeLouvor;
    }

    public void setNomeLouvor(String nomeLouvor) {
        this.nomeLouvor = nomeLouvor;
    }

    public String getNomeCantor() {
        return nomeCantor;
    }

    public void setNomeCantor(String nomeCantor) {
        this.nomeCantor = nomeCantor;
    }

    public String getNumeroHino() {
        return numeroHino;
    }

    public void setNumeroHino(String numeroHino) {
        this.numeroHino = numeroHino;
    }

   

    public String getLoginSonoplasta() {
        return loginSonoplasta;
    }

    public void setLoginSonoplasta(String loginSonoplasta) {
        this.loginSonoplasta = loginSonoplasta;
    }

  

}
