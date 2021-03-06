/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderizacao;

/**
 *
 * @author Luciano
 */
public class RenderizacaoSono {
    private String principal = "setorPesquisar";
    
    public boolean isPesquisarSetor() {
        return "setorPesquisar".equals(principal);
    }
    public void mudarParaPesquisarSetor(){
        principal = "setorPesquisar";
    }
    public void ocultarPesquisarSetor(){
        principal = " ";
    }
    public boolean isPesquisarHino() {
        return "hinoPesquisar".equals(principal);
    }
    public void mudarParaPesquisarHino(){
        principal = "hinoPesquisar";
    }
    public void ocultarPesquisarHino(){
        principal = " ";
    }
    public boolean isPlayerHino() {
        return "hinoPlayer".equals(principal);
    }
    public void mudarPlayerHino(){
        principal = "hinoPlayer";
    }
    public void ocultarPlayerHino(){
        principal = " ";
    }
    

}
