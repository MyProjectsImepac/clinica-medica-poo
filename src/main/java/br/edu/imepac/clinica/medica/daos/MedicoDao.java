package br.edu.imepac.clinica.medica.daos;

import br.edu.imepac.clinica.medica.entidades.Especialidade;
import br.edu.imepac.clinica.medica.entidades.Medico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoDao {

    private final String url = "jdbc:mysql://localhost:3306/clinica_medica?useSSL=false&allowPublicKeyRetrieval=true";
    private final String user = "root";
    private final String senha = "AzSx718293!";
    private final Connection connection;

    public MedicoDao() throws SQLException {
        connection = DriverManager.getConnection(url, user, senha);
    }

    public void salvar(Medico medico) throws SQLException {
        String sql = "INSERT INTO medico (nome, crm, especialidade_id) VALUES (?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, medico.getNome());
        stmt.setString(2, medico.getCrm());
        stmt.setInt(3, medico.getEspecialidade().getId());
        stmt.executeUpdate();
    }

    public Medico buscarPorId(int id) throws SQLException {
        String sql = "SELECT m.id, m.nome, m.crm, e.id AS especialidade_id, e.nome AS especialidade_nome " +
                     "FROM medico m JOIN especialidade e ON m.especialidade_id = e.id WHERE m.id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Medico m = new Medico();
            m.setId(rs.getInt("id"));
            m.setNome(rs.getString("nome"));
            m.setCrm(rs.getString("crm"));

            Especialidade e = new Especialidade();
            e.setId(rs.getInt("especialidade_id"));
            e.setNome(rs.getString("especialidade_nome"));
            m.setEspecialidade(e);

            return m;
        }
        return null;
    }

    public List<Medico> listarTodos() throws SQLException {
        String sql = "SELECT m.id, m.nome, m.crm, e.id AS especialidade_id, e.nome AS especialidade_nome " +
                     "FROM medico m JOIN especialidade e ON m.especialidade_id = e.id";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<Medico> lista = new ArrayList<>();
        while (rs.next()) {
            Medico m = new Medico();
            m.setId(rs.getInt("id"));
            m.setNome(rs.getString("nome"));
            m.setCrm(rs.getString("crm"));

            Especialidade e = new Especialidade();
            e.setId(rs.getInt("especialidade_id"));
            e.setNome(rs.getString("especialidade_nome"));
            m.setEspecialidade(e);

            lista.add(m);
        }
        return lista;
    }

    public void atualizar(Medico medico) throws SQLException {
        String sql = "UPDATE medico SET nome = ?, crm = ?, especialidade_id = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, medico.getNome());
        stmt.setString(2, medico.getCrm());
        stmt.setInt(3, medico.getEspecialidade().getId());
        stmt.setInt(4, medico.getId());
        stmt.executeUpdate();
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM medico WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}
