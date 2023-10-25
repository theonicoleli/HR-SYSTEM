package entities.DataBase;

import entities.Funcionario;
import entities.FuncionarioException;
import entities.Pessoa;
import entities.RH;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DAO {

    private RH rh;
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

    public boolean possivelAlteracao(Pessoa rh, Pessoa funcionario) {
        if (funcionario instanceof Funcionario && rh instanceof RH &&
                ((RH) rh).verificandoFuncionarioExistente(funcionario.getCpf())) {
            return true;
        }
        return false;
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
                System.out.println("Data de nascimento: " + rsPessoas.getString("DataNasc"));
                System.out.println("Salário: " + rsPessoas.getString("Salario"));
                System.out.println("CPF: " + rsPessoas.getString("Cpf"));
                System.out.println("Setor: " + rsPessoas.getString("NomeSetor"));
                System.out.println("Turno: " + rsPessoas.getString("Turno"));
                System.out.println("Carteira de Trabalho: " + rsPessoas.getString("CarteiraTrabalho"));
            }
            rsPessoas.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void addPessoa(Pessoa rh, Pessoa funcionario) {
        try {
            if (possivelAlteracao(rh, funcionario)){
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
                preparedStatement.close();
            } else {
                throw new FuncionarioException("Ocorreu um erro, não foi possível a inserção de um novo funcionário.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("Erro ao converter a data: " + e.getMessage());
        }
    }

    public void removerFuncionario(Pessoa rh, Pessoa funcionario) {
        try {
            if (possivelAlteracao(rh, funcionario)) {
                Funcionario func = (Funcionario) funcionario;
                String deleteSQL = "DELETE FROM FUNCIONARIO WHERE CPF = ?";
                PreparedStatement preparedStatement = con.prepareStatement(deleteSQL);
                preparedStatement.setString(1, func.getCpf());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            else {
                throw new FuncionarioException("Ocorreu um erro, não foi possível a exclusão do funcionário " + funcionario.getNome() + ".");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}