package renderizacao;

public class Renderizacao {

    private String linhaUm = "logarSite";
    private String linhaDois = "";
    private String linhaTres = "";
    public String linhaQuatro = "";
    public String logarCadastrar = "logarSistema" ;
    
    public boolean isLogarSistema() {
        return "logarSistema".equals(logarCadastrar);
    }
    public boolean isCadastrarSistema() {
        return "sistemaCadastrar".equals(logarCadastrar);
    }

    public void mudarParaLogarSistema() {       
        logarCadastrar = "logarCadastrar";
    }
    public void mudarParaCadastrarSistema() {       
        logarCadastrar = "sistemaCadastrar";
    }
    
    //---------------Linha Um---------------------
    public boolean isSonoplasta() {
        return "admin".equals(linhaUm);
    }

    public void mudarParaSonoPlasta() {
        linhaUm = "admin";
        linhaTres = "setorPesquisarSono";
    }

    public boolean isGridBuscarSetor() {
        return "setorBuscarGrid".equals(linhaUm);
    }

    public void mudarBuscarSetorParaUpload() {
        linhaUm = "setorBuscarGrid";
    }

    public boolean isLogar() {
        return "logarSite".equals(linhaUm);
    }

    public void mudarParaLogarNoSite() {
        linhaUm = "logarSite";
    }

    public boolean isUsuaioLogado() {
        return "logadoUsuario".equals(linhaDois);
    }

    public void mudarParaGridUsuarioLogado() {
        linhaDois = "logadoUsuario";
    }

    public void ocultarGridMenuUsuarioLogado() {
        linhaDois = "";
    }

    public boolean isCadastrar() {
        return "cadastrarSite".equals(linhaUm);
    }

    public void mudarParaCadastrarNoSite() {
        linhaUm = "cadastrarSite";
    }
    public void mudarParaOcultarCadastro() {
        linhaUm = "";
    }

    public boolean isGridUplodHino() {
        return "uploadGrid".equals(linhaUm);
    }

    public void mudarParaGridUploadHino() {
        linhaUm = "uploadGrid";
    }

    public void ocultarGridUploadHino() {
        linhaUm = "";
    }

    public boolean isDataTableHinos() {
        return "hinosDataTable".equals(linhaUm);
    }

    public void mudarParaDataTableHinos() {
        linhaUm = "hinosDataTable";
    }

    public boolean isOuvirHinoSelecionado() {
        return "selecionadoHino".equals(linhaUm);
    }

    public void mudarParaHinoSelecionado() {
        linhaUm = "selecionadoHino";
    }

    public boolean isHinoSonoplasta() {
        return "sonoplastaHino".equals(linhaUm);
    }

    public void mudarParaHinoSonoplasta() {
        linhaUm = "sonoplastaHino";
    }

    //---------------Linha Dois-------------------
    public boolean isDataTableHinosSetor() {
        return "setorTable".equals(linhaDois);
    }

    public void mudarParaTableHinosSetor() {
        linhaDois = "setorTable";
    }

    public void ocultarDataTableHinosSetor() {
        linhaDois = "";
    }

    public boolean isPlayerHinoSetor() {
        return "setorHino".equals(linhaDois);
    }

    public void mudarParaPlayerHinoSetor() {
        linhaDois = "setorHino";
    }

    public void ocultarPlayerHinoSetor() {
        linhaDois = "";
    }

    public boolean isGridSetorEncontrado() {
        return "encontratoSetor".equals(linhaDois);
    }

    public void mudarParaGridSetorEncontrado() {
        linhaDois = "encontratoSetor";
    }

    public void ocultarGridSetorEncontrado() {
        linhaDois = "";
    }

    //---------------Linha Três-------------------

    public boolean isSonoPesquisarSetor() {
        return "setorPesquisarSono".equals(linhaTres);
    }

    public void mudarParaPesquisarSetorSono() {
        linhaTres = "setorPesquisarSono";
    }

    public void ocultarSonoPesquisarSetor() {
        linhaTres = "";
    }
    public boolean isSonoPesquisarHino() {
        return "hinoPesquisarSono".equals(linhaTres);
    }

    public void mudarParaPesquisarHinoSono() {
        linhaTres = "hinoPesquisarSono";
    }

    public void ocultarSonoPesquisarHino() {
        linhaTres = "";
    }

    //---------------Linha Quatro--------------

    public boolean isPlayerHino() {
        return "hinoPlayer".equals(linhaQuatro);
    }

    public void mudarParaPlayerHino() {
        linhaQuatro = "hinoPlayer";
    }

    public void ocultarPlayerHino() {
        linhaQuatro = "";
    }
    public boolean isLogado() {
        return "logadoSite".equals(linhaQuatro);
    }

    public void mudarParaSiteLogado() {
        linhaQuatro = "logadoSite";
    }

    public void ocultarParaSiteLogado() {
        linhaQuatro = "";
    }

}
