package entities.DataBase;

import entities.Funcionario;
import entities.FuncionarioException;
import entities.Pessoa;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DAO {
    private Connection con;
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String user = "root";
    private String password = "PUC@1234";
    private String url = "jdbc:mysql://localhost:3306/pjbl";

    public DAO() {
        con = conectar();
    }

    public Connection conectar() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            return con;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void fecharCon() throws SQLException {
        if (con != null) {
            con.close();
        }
    }

    public void listagemPessoas() {
        try {
            Statement statement = con.createStatement();
            ResultSet rsPessoas = statement.executeQuery("SELECT * FROM Funcionario;");
            while (rsPessoas.next()) {
                System.out.println("Nome: " + rsPessoas.getString("Nome"));
            }
            rsPessoas.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void addPessoa(Pessoa funcionario) {
        try {
            if (funcionario instanceof Funcionario) {
                Funcionario func = (Funcionario) funcionario;
                PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO Funcionario (Nome, DataNasc, Salario, Cpf, NomeSetor, Turno, CarteiraTrabalho) VALUES (?, ?, ?, ?, ?, ?, ?)");
                preparedStatement.setString(1, func.getNome());

                // Converter a data de String para java.sql.Date (formato "dd/MM/yyyy")
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date parsedDate = dateFormat.parse(func.getDtNascimento());
                java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());

                preparedStatement.setDate(2, sqlDate);
                preparedStatement.setDouble(3, func.getSalario());
                preparedStatement.setString(4, func.getCpf());
                preparedStatement.setString(5, func.getSetor());
                preparedStatement.setString(6, func.getTurno());
                preparedStatement.setString(7, func.getCarteiraTrabalho());

                preparedStatement.executeUpdate();
            } else {
                throw new FuncionarioException("Você não está inserindo um funcionário e sim uma pessoa.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (FuncionarioException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println("Erro ao converter a data: " + e.getMessage());
        }
    }
}