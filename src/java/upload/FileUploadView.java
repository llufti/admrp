package upload;

import bean.AdmbSerranaBean;
import entidades.Hinos;
import entidades.Setores;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import util.ErroSistema;

@Named
@ViewScoped
public class FileUploadView implements Serializable {

    private Setores setores;

    private String nomeArquivo = null;
    static String staticArquivoReal;
    private String nomeArquivoReal = null;
    private boolean exibirPlayer;
    static String loginUsuraio;
    private String realPath;

    @Inject
    AdmbSerranaBean asb;

    UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void handleFileUpload(FileUploadEvent event) throws IOException, ErroSistema {
        if (nomeArquivoReal == null) {

            FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);

            realPath = FacesContext.getCurrentInstance()
                    .getExternalContext().getRealPath("/");
            File hinos = new File(realPath + "resources/" + loginUsuraio);
            hinos.mkdirs();

            String dirarquivo = realPath + "resources/" + loginUsuraio + "/" + event.getFile().getFileName();
            byte[] conteudo = event.getFile().getContent();
            FileOutputStream fos;
            try {
                fos = new FileOutputStream(dirarquivo);
                fos.write(conteudo);
                fos.close();

            } catch (FileNotFoundException ex) {
                Logger.getLogger(FileUploadView.class.getName()).log(Level.SEVERE, null, ex);
            }
            int tamanhoTitulo = event.getFile().getFileName().indexOf(".mp3");
            if (tamanhoTitulo < 45) {
                nomeArquivo = event.getFile().getFileName();
            } else {
                nomeArquivo = event.getFile().getFileName().substring(0, 45) + ".....";
            }
            nomeArquivoReal = event.getFile().getFileName();
            staticArquivoReal = event.getFile().getFileName();
            exibirPlayer = true;
        } else {
            deletaArquivo();
            FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);

//            realPath = FacesContext.getCurrentInstance()
//                    .getExternalContext().getRealPath("/");
//            File hinos = new File("C:\\Users\\Luciano\\Documents\\NetBeansProjects\\admbserrana\\web\\admin\\resources\\" + loginUsuraio);
//            hinos.mkdirs();
//            System.out.println(realPath);
            String dirarquivo = realPath + "resources/" + loginUsuraio + "/" + event.getFile().getFileName();
            byte[] conteudo = event.getFile().getContent();
            FileOutputStream fos;
            try {
                fos = new FileOutputStream(dirarquivo);
                fos.write(conteudo);
                fos.close();

            } catch (FileNotFoundException ex) {
                Logger.getLogger(FileUploadView.class.getName()).log(Level.SEVERE, null, ex);
            }
            int tamanhoTitulo = event.getFile().getFileName().indexOf(".mp3");
            if (tamanhoTitulo < 45) {
                nomeArquivo = event.getFile().getFileName();
            } else {
                nomeArquivo = event.getFile().getFileName().substring(0, 45) + ".....";
            }
            setNomeArquivoReal(event.getFile().getFileName());
            staticArquivoReal = event.getFile().getFileName();
            exibirPlayer = true;
        }

    }

    public void deletaArquivo() {
        File hino = new File(realPath + "resources/" + loginUsuraio + "/" + nomeArquivoReal);
        hino.delete();

    }
    

    public void ocultarPlayerDOHino() {
        exibirPlayer = false;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public boolean isExibirPlayer() {
        return exibirPlayer;
    }

    public String getNomeArquivoReal() {
        return nomeArquivoReal;
    }

    public void setExibirPlayer(boolean exibirPlayer) {
        this.exibirPlayer = exibirPlayer;
    }

    public Setores getSetores() {
        return setores;
    }

    public static String getLoginUsuraio() {
        return loginUsuraio;
    }

    public static void setLoginUsuraio(String loginUsuraio) {
        FileUploadView.loginUsuraio = loginUsuraio;
    }

    public String getRealPath() {
        return realPath;
    }

    public void setNomeArquivoReal(String nomeArquivoReal) {
        this.nomeArquivoReal = nomeArquivoReal;
    }

    public static String getStaticArquivoReal() {
        return staticArquivoReal;
    }

    public static void setStaticArquivoReal(String staticArquivoReal) {
        FileUploadView.staticArquivoReal = staticArquivoReal;
    }

    public void isExibirPlayer(boolean b) {
        this.exibirPlayer = b;
    }

    public AdmbSerranaBean getAsb() {
        return asb;
    }

    public void setAsb(AdmbSerranaBean asb) {
        this.asb = asb;
    }
    
    

}
