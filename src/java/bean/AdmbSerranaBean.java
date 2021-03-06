/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.HinosDao;
import dao.SetorDao;
import entidades.Hinos;
import entidades.Setores;
import entidades.Usuarios;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.PrimeFaces;
import renderizacao.Renderizacao;
import renderizacao.RenderizacaoSono;
import upload.FileUploadView;
import util.ErroSistema;

/**
 *
 * @author Luciano
 */
@Named
@SessionScoped
public class AdmbSerranaBean implements Serializable {

    private Renderizacao renderizacao;
    private RenderizacaoSono renderizacaSono;
    private Setores setores;
    private SetorDao setorDao;
    private FileUploadView fileUploadView;
    private Hinos hinos;
    private HinosDao hinosDao;
    private Usuarios usuarios;

    private boolean controle = true;

    private List<Hinos> listHinos;

    private List<Usuarios> listSetores;
    @Inject
    FileUploadView fuv;

    @PostConstruct
    public void init() {
        System.out.println("Entrou No Bean");
        setores = new Setores();
        renderizacao = new Renderizacao();
        setorDao = new SetorDao();
        fileUploadView = new FileUploadView();
        hinos = new Hinos();
        hinosDao = new HinosDao();
        renderizacaSono = new RenderizacaoSono();
        usuarios = new Usuarios();
    }

    //----------------Sonoplasta-------------------------------
    public void exibirBuscarHinosPeloSetor() throws ErroSistema {
        setores.setUsuario("Sonoplasta Louvando");
        hinos = new Hinos();
        renderizacaSono.mudarParaPesquisarSetor();

    }

    public List<Usuarios> buscarSetoresAutoComplete(String query) throws ErroSistema {
        String queryLowerCase = query.toLowerCase();
        listSetores = setorDao.buscarSetores(usuarios);

        return listSetores.stream().filter(t -> t.getSetor().toLowerCase().startsWith(queryLowerCase)).collect(Collectors.toList());
    }

    public void buscarHinosPeloSetorSonoplasta() throws ErroSistema {
        listHinos = new ArrayList<>();
        listHinos = hinosDao.buscarLouvoresPeloIdSetorSonoplasta(hinos, setores, usuarios);
        hinos.setNumeroHino("");
        setores.setUsuario("Sonoplasta Louvando");
        renderizacaSono.mudarParaPesquisarHino();

        if (listHinos.isEmpty() || listHinos == null) {
            adicionarMensagem("Hinos não encontrado!", FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void exibirHinoSelecionadoSonoplasta(Hinos hinos) {
        this.hinos = hinos;
        setores.setUsuario("Sonoplasta Louvando");
        renderizacaSono.mudarPlayerHino();

    }

    //-----------------------Adicionar Louvor-----------------
    public void cancelarUploadDeHino() {
        fuv.ocultarPlayerDOHino();
        fuv.deletaArquivo();
        hinos = new Hinos();
        adicionarMensagem("Cancelado com sucesso!", FacesMessage.SEVERITY_INFO);

    }

    public void salvarHinos() {
        try {
            if (!hinosDao.verificarSeNumeroDoHinoJaExiste(hinos, setores, usuarios)) {
                hinosDao.salvarHino(hinos, fileUploadView, setores);
                fuv.setExibirPlayer(false);
                hinos = new Hinos();
                fuv.setNomeArquivoReal(null);
                adicionarMensagem("Gloria a Deus Salvo Com Sucesso!", FacesMessage.SEVERITY_INFO);
            } else {
                adicionarMensagem("Este numero ja existe!", FacesMessage.SEVERITY_ERROR);
                hinos.setNumeroHino("");
            }
        } catch (ErroSistema ex) {
            Logger.getLogger(AdmbSerranaBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String exibirEcontrarLouvor() throws ErroSistema {
        hinos = new Hinos();
        buscarHinosPeloSetor();
        renderizacao.mudarParaDataTableHinos();

        return "/logado/encontrarLouvor?faces-redirect=true";
    }

    public String exibirAdicionarLouvor() throws ErroSistema {
        hinos = new Hinos();

        return "/logado/adicionarLouvor1?faces-redirect=true";
    }

    public void buscarHinosPeloSetor() throws ErroSistema {
        listHinos = hinosDao.buscarLouvoresPeloIdSetor(hinos, setores, usuarios);
        if (listHinos.isEmpty() || listHinos == null) {
            adicionarMensagem("Hinos não encontrado!", FacesMessage.SEVERITY_ERROR);
        }
    }

    //-------------------Encontrar hinos para ouvir-------------
    public List<Hinos> buscarHinosAutoComplete(String query) throws ErroSistema {
        String queryLowerCase = query.toLowerCase();
        listHinos = hinosDao.buscarLouvoresPeloIdSetor(hinos, setores, usuarios);

        return listHinos.stream().filter(t -> t.getNumeroHino().toLowerCase().startsWith(queryLowerCase)).collect(Collectors.toList());
    }

    public void buscarHinosPeloIdHino() throws ErroSistema {

        listHinos = hinosDao.buscarLouvoresPeloNumeroDoHino(hinos, setores, usuarios);
        if (listHinos.isEmpty() || listHinos == null) {
            adicionarMensagem("Hinos não encontrado!", FacesMessage.SEVERITY_ERROR);
        }
        adicionarMensagem("Hinos encontrado!", FacesMessage.SEVERITY_INFO);

    }

    public void deletarSelecionarLouvou() {
        controle = !controle;
        hinos.setNumeroHino("");
    }

    public String exibirHinoSelecionado(Hinos hinos) {
        this.hinos = hinos;
        return "/logado/louvorEncontrado?faces-redirect=true";
    }

    public void deletarHino(Hinos hinos) throws ErroSistema {
        this.hinos = hinos;
        hinosDao.deletarHino(hinos);
        deletaArquivoUsuario();
        listHinos.remove(hinos);
        adicionarMensagem("Louvor deletado com sucesso!", FacesMessage.SEVERITY_INFO);
    }

    public void deletaArquivoUsuario() {
        String realPath = FacesContext.getCurrentInstance()
                .getExternalContext().getRealPath("/");
        File hino = new File(realPath + "resources/" + setores.getLogin() + "/" + hinos.getNome());
        hino.delete();

    }

    public String logar() throws ErroSistema {
        String logar = setorDao.buscarSetoresParaLogar(setores, usuarios);
        if (setores.getLogin().equals("sonoplasta") && setores.getSenhaConfirma().equals("1234")) {
            setores.setUsuario("Sonoplasta Louvando");

            return "/sonoplasta?faces-redirect=true";

        }

        if (logar != null && setores.getSenhaConfirma().equals(logar)) {
            renderizacao.mudarParaGridUploadHino();
            renderizacao.mudarParaSiteLogado();
            renderizacao.mudarParaGridUsuarioLogado();
            return "/logado/adicionarLouvor1?faces-redirect=true";

        } else {

            adicionarMensagem("Usuario ou senha errada!", FacesMessage.SEVERITY_ERROR);
        }

        return null;
    }

    public String logout() throws ErroSistema {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        adicionarMensagem("Usuario ou senha errada!", FacesMessage.SEVERITY_ERROR);

        renderizacao.mudarParaLogarNoSite();
        setores = new Setores();

        return "/index?faces-redirect=true";
    }

    //------------------------------navegação--------------------------------
    public void adicionarMensagem(String mensagem, FacesMessage.Severity tipoErro) {
        FacesMessage fm = new FacesMessage(tipoErro, mensagem, null);
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }

    public Renderizacao getRenderizacao() {
        return renderizacao;
    }

    public Setores getSetores() {
        return setores;
    }

    public void setSetores(Setores setores) {
        this.setores = setores;
    }

    public SetorDao getSetorDao() {
        return setorDao;
    }

    public void setSetorDao(SetorDao setorDao) {
        this.setorDao = setorDao;
    }

    public FileUploadView getFileUploadView() {
        return fileUploadView;
    }

    public void setFileUploadView(FileUploadView fileUploadView) {
        this.fileUploadView = fileUploadView;
    }

    public List<Usuarios> getListSetores() {
        return listSetores;
    }

    public void setListSetores(List<Usuarios> listSetores) {
        this.listSetores = listSetores;
    }

    public Hinos getHinos() {
        return hinos;
    }

    public void setHinos(Hinos hinos) {
        this.hinos = hinos;
    }

    public HinosDao getHinosDao() {
        return hinosDao;
    }

    public void setHinosDao(HinosDao hinosDao) {
        this.hinosDao = hinosDao;
    }

    public FileUploadView getFuv() {
        return fuv;
    }

    public void setFuv(FileUploadView fuv) {
        this.fuv = fuv;
    }

    public List<Hinos> getListHinos() {
        return listHinos;
    }

    public void setListHinos(List<Hinos> listHinos) {
        this.listHinos = listHinos;
    }

    public RenderizacaoSono getRenderizacaSono() {
        return renderizacaSono;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public boolean isControle() {
        return controle;
    }

    public void setControle(boolean controle) {
        this.controle = controle;
    }

}
