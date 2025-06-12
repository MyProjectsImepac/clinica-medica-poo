package br.edu.imepac.clinica.medica.daos;

import br.edu.imepac.clinica.medica.entidades.Login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {

    private String url = "jdbc:mysql://localhost:3306/clinica_medica?useSSL=false&allowPublicKeyRetrieval=true";
    private String user = "root";
    private String senha = "AzSx718293!";

    private final Connection connection;

    public LoginDao() throws SQLException {
        connection = DriverManager.getConnection(url, user, senha);
    }

    public Login buscarUsuario(String usuario, String senha) throws SQLException {
        String sql = "select * from login where usuario = ? and senha = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, usuario);
        preparedStatement.setString(2, senha);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next() == true) {
            Login login = new Login();
            login.setUsuario(resultSet.getString("usuario"));
            login.setSenha(resultSet.getString("senha"));
            return login;
        }
        return null;
    }
}
