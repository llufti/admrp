/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Hinos;
import entidades.Setores;
import entidades.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.primefaces.component.fileupload.FileUpload;
import upload.FileUploadView;
import util.ErroSistema;
import util.FabricaConexao;

/**
 *
 * @author Luciano
 */
public class HinosDao {

    public void salvarHino(Hinos hinos, FileUploadView fileUpload, Setores setores) throws ErroSistema {
        try {

            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;

            if (hinos.getIdHino() == null) {
                ps = conexao.prepareCall("INSERT INTO `hinos` (`idSetor`,`nomeLouvor`,`numeroHino`,`nomeLouvorReal`) VALUES (?,?,?,?)");
            } else {
                ps = conexao.prepareStatement("update hinos set idSetor=?,nomeLouvor=?,numeroHino=?,nomeLouvorReal=? where idHinos=?");
                ps.setInt(4, hinos.getIdHino());
            }
            ps.setInt(1, setores.getIdSetor());
            ps.setString(2, hinos.getNomeLouvor() + ".mp3");
            ps.setString(3, hinos.getNumeroHino());
            ps.setString(4, FileUploadView.getStaticArquivoReal());
            ps.execute();

            FabricaConexao.fecharConexao();

        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao inserir", ex);
        }
    }

    public List<Hinos> buscarLouvoresPeloIdSetor(Hinos hinos, Setores setores, Usuarios usuarios) throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("SELECT hinos.idHinos, hinos.nomeLouvor, hinos.numeroHino, hinos.nomeLouvorReal  FROM hinos WHERE hinos.idSetor LIKE ? ORDER BY hinos.numeroHino");
            ps.setInt(1, usuarios.getIdSetor());
            ResultSet resultSet = ps.executeQuery();

            List<Hinos> entidades = new ArrayList<>();

            while (resultSet.next()) {
                hinos = new Hinos();

                hinos.setIdHino(resultSet.getInt("idHinos"));
                hinos.setNomeLouvor(resultSet.getString("nomeLouvor"));
                hinos.setNumeroHino(resultSet.getString("numeroHino"));
                hinos.setNome(resultSet.getString("nomeLouvorReal"));
                entidades.add(hinos);
            }
            FabricaConexao.fecharConexao();
            return entidades;
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao inserir na lista", ex);
        }
    }

    public List<Hinos> buscarLouvoresPeloIdSetorSonoplasta(Hinos hinos, Setores setores, Usuarios usuarios) throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("SELECT hinos.idHinos, hinos.nomeLouvor, hinos.numeroHino, setor.idSetor , setor.login,hinos.nomeLouvorReal  FROM hinos JOIN setor ON setor.idSetor = hinos.idSetor WHERE hinos.idSetor LIKE ? ORDER BY hinos.numeroHino");
            ps.setInt(1, usuarios.getIdSetor());
            ResultSet resultSet = ps.executeQuery();

            List<Hinos> entidades = new ArrayList<>();

            while (resultSet.next()) {
                hinos = new Hinos();
                hinos.setIdHino(resultSet.getInt("idHinos"));
                hinos.setIdSetor(resultSet.getInt("idSetor"));
                hinos.setNomeLouvor(resultSet.getString("nomeLouvor"));
                hinos.setNumeroHino(resultSet.getString("numeroHino"));
                hinos.setLoginSonoplasta(resultSet.getString("login"));
                hinos.setNome(resultSet.getString("nomeLouvorReal"));
                entidades.add(hinos);
            }

            FabricaConexao.fecharConexao();
            return entidades;
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao inserir na lista", ex);
        }
    }

    public List<Hinos> buscarLouvoresPeloNumeroDoHino(Hinos hinos, Setores setores, Usuarios usuarios) throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("SELECT hinos.idHinos, hinos.nomeLouvor, hinos.numeroHino, setor.login,hinos.nomeLouvorReal FROM hinos JOIN setor ON setor.idSetor = hinos.idSetor WHERE hinos.numeroHino LIKE ? AND hinos.idSetor LIKE ? ");

            ps.setString(1, hinos.getNumeroHino());
            ps.setInt(2, usuarios.getIdSetor());
            ResultSet resultSet = ps.executeQuery();

            List<Hinos> entidades = new ArrayList<>();

            while (resultSet.next()) {
                hinos.setIdHino(resultSet.getInt("idHinos"));
                hinos.setNomeLouvor(resultSet.getString("nomeLouvor"));
                hinos.setNumeroHino(resultSet.getString("numeroHino"));
                hinos.setLoginSonoplasta(resultSet.getString("login"));
                hinos.setNome(resultSet.getString("nomeLouvorReal"));
                entidades.add(hinos);
            }
            FabricaConexao.fecharConexao();
            return entidades;
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao inserir na lista", ex);
        }
    }

    public boolean verificarSeNumeroDoHinoJaExiste(Hinos hinos, Setores setores, Usuarios usuarios) throws ErroSistema {
        boolean numeroHino = false;
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("SELECT hinos.numeroHino FROM hinos WHERE hinos.idSetor  LIKE ? AND hinos.numeroHino LIKE ?");
            ps.setInt(1, usuarios.getIdSetor());
            ps.setString(2, hinos.getNumeroHino());
            ResultSet resultSet = ps.executeQuery();

            numeroHino = resultSet.next();

            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao inserir na lista", ex);
        }
        return numeroHino;
    }

    public void deletarHino(Hinos hinos) throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("delete from hinos where idHinos  = ?");
            ps.setInt(1, hinos.getIdHino());
            ps.execute();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao deletar", ex);
        }
    }

}
