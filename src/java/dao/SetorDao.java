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
import upload.FileUploadView;
import util.ErroSistema;
import util.FabricaConexao;

/**
 *
 * @author Luciano
 */
public class SetorDao {

    public void salvarSetor(Hinos hinos, FileUploadView fileUpload, Setores setores) throws ErroSistema {

        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;

            if (hinos.getIdHino() == null) {
                ps = conexao.prepareCall("INSERT INTO `setor` (`setor`,`senha`,`login`) VALUES (?,?,?)");
            } else {
                ps = conexao.prepareStatement("update setor set setor=?,senha=?,login=?  where idSetor =?");
                ps.setInt(4, setores.getIdSetor());
            }

            ps.setString(1, setores.getSetor());
            ps.setString(2, setores.getSenha());
            ps.setString(3, setores.getLogin());

            ps.execute();

            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao inserir", ex);
        }
    }

    public String buscarSetoresParaLogar(Setores setores, Usuarios usuarios) throws ErroSistema {
        try {
            String senha = null;

            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("SELECT * FROM setor WHERE setor.login LIKE ?");
            ps.setString(1, setores.getLogin());

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                senha = resultSet.getString("senha");
                setores.setIdSetor(resultSet.getInt("idSetor"));
                usuarios.setIdSetor(resultSet.getInt("idSetor"));
                setores.setUsuario(resultSet.getString("setor"));
                FileUploadView.setLoginUsuraio(resultSet.getString("login"));
                setores.setLogin(resultSet.getString("login"));
            }
            FabricaConexao.fecharConexao();
            return senha;

        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao inserir na lista", ex);

        }
    }

    public List<Usuarios> buscarSetores(Usuarios usuarios) throws ErroSistema {
        try {

            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("SELECT * FROM setor ");

            ResultSet resultSet = ps.executeQuery();

            List<Usuarios> entidades = new ArrayList<>();
            while (resultSet.next()) {
                usuarios = new Usuarios();

                usuarios.setIdSetor(resultSet.getInt("idSetor"));
                usuarios.setSetor(resultSet.getString("setor"));

                entidades.add(usuarios);

            }
            FabricaConexao.fecharConexao();
            return entidades;
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao inserir na lista", ex);

        }
    }

    public List<Setores> buscarSetoresPeloId(Setores setores) throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("SELECT * FROM setor WHERE idSetor LIKE ? ");
            ps.setInt(1, setores.getIdSetor());

            ResultSet resultSet = ps.executeQuery();

            List<Setores> entidades = new ArrayList<>();
            while (resultSet.next()) {

                setores.setIdSetor(resultSet.getInt("idSetor"));
                setores.setSetor(resultSet.getString("setor"));

                entidades.add(setores);

            }
            FabricaConexao.fecharConexao();
            return entidades;
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao inserir na lista", ex);

        }
    }

}
