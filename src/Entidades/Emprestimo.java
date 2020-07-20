/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;

/**
 *
 * @author T-Gamer
 */
public class Emprestimo implements Serializable{
    int idemprestimo;
    String dataE;
    String dataD;
    String idusuario;
    int idexemplar;
    String idfuncionario;

    public int getIdemprestimo() {
        return idemprestimo;
    }

    public void setIdemprestimo(int idemprestimo) {
        this.idemprestimo = idemprestimo;
    }


    public String getDataE() {
        return dataE;
    }

    public void setDataE(String dataE) {
        this.dataE = dataE;
    }

    public String getDataD() {
        return dataD;
    }

    public void setDataD(String dataD) {
        this.dataD = dataD;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }

    public int getIdexemplar() {
        return idexemplar;
    }

    public void setIdexemplar(int idexemplar) {
        this.idexemplar = idexemplar;
    }


    public String getIdfuncionario() {
        return idfuncionario;
    }

    public void setIdfuncionario(String idfuncionario) {
        this.idfuncionario = idfuncionario;
    }
    
}
