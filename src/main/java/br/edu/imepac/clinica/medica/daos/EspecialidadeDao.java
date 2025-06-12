package br.edu.imepac.clinica.medica.daos;

import br.edu.imepac.clinica.medica.entidades.Especialidade;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EspecialidadeDao {

    private final String url = "jdbc:mysql://localhost:3306/clinica_medica?useSSL=false&allowPublicKeyRetrieval=true";
    private final String user = "root";
    private final String senha = "AzSx718293!";
    private final Connection connection;

    public EspecialidadeDao() throws SQLException {
        connection = DriverManager.getConnection(url, user, senha);
    }

    public void salvar(Especialidade especialidade) throws SQLException {
        String sql = "INSERT INTO especialidade (nome) VALUES (?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, especialidade.getNome());
        stmt.executeUpdate();
    }

    public Especialidade buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM especialidade WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Especialidade e = new Especialidade();
            e.setId(rs.getInt("id"));
            e.setNome(rs.getString("nome"));
            return e;
        }
        return null;
    }

    public List<Especialidade> listarTodas() throws SQLException {
        String sql = "SELECT * FROM especialidade";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<Especialidade> lista = new ArrayList<>();
        while (rs.next()) {
            Especialidade e = new Especialidade();
            e.setId(rs.getInt("id"));
            e.setNome(rs.getString("nome"));
            lista.add(e);
        }
        return lista;
    }

    public void atualizar(Especialidade especialidade) throws SQLException {
        String sql = "UPDATE especialidade SET nome = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, especialidade.getNome());
        stmt.setInt(2, especialidade.getId());
        stmt.executeUpdate();
    }

    public void deletar(long id) throws SQLException {
        String sql = "DELETE FROM especialidade WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        stmt.executeUpdate();
    }
}
