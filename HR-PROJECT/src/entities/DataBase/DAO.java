package entities.DataBase;

import entities.Funcionario;
import entities.FuncionarioException;
import entities.Pessoa;
import entities.RH;
import entities.enumerator.Setor;
import entities.enumerator.Turno;
import entities.records.RHTABLE;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DAO {

    private Connection con;
    private RHTABLE rhtable;
    private SimpleDateFormat sdf;
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String user = "root";
    private String password = "PUC@1234";
    private String url = "jdbc:mysql://localhost:3306/pjbl";

    public DAO() {
        con = conectar();
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        rhtable = new RHTABLE(new ArrayList<>());
        pegandoFuncionariosBd();
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
                this.funcionariosDb(funcionario.getCpf(), false)) {
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
            ResultSet rsPessoas = statement.executeQuery("SELECT * FROM Funcionarios;");
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
                PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO Funcionarios (Nome, DataNasc, Salario, Cpf, NomeSetor, Turno, CarteiraTrabalho) VALUES (?, ?, ?, ?, ?, ?, ?)");
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
                String deleteSQL = "DELETE FROM FUNCIONARIOS WHERE CPF = ?";
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

    public ArrayList<? super Pessoa> pegandoFuncionariosBd() {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Funcionarios");

            while (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String cpfSearch = resultSet.getString("cpf");
                Date dtNascimento = resultSet.getDate("DataNasc");
                Double salario = resultSet.getDouble("salario");
                Turno turno = Turno.valueOf(resultSet.getString("turno"));
                String carteiraTrabalho = resultSet.getString("CarteiraTrabalho");
                Setor nomeSetor = Setor.valueOf(resultSet.getString("NomeSetor"));
                String funcao = resultSet.getString("Funcao");

                Pessoa func = new Funcionario(nome, cpfSearch, dtNascimento,
                        nomeSetor, salario, carteiraTrabalho, funcao, turno);

                rhtable.addFuncionarioList(func);
            }

            return rhtable.funcionarios();
        }
        catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public boolean funcionariosDb(String cpf, boolean excecao) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Funcionarios");

            while (resultSet.next()) {
                String cpfSearch = resultSet.getString("cpf");

                if (cpfSearch.equals(cpf)) {
                    if (excecao) {
                        throw new FuncionarioException("Funcionário já existente, reveja o cpf.");
                    }
                    return false;
                }
            }

            resultSet.close();
            statement.close();
            connection.close();

            return true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public int sizeOfTable() {
        return rhtable.sizeOfList();
    }

    public void printPessoa(String cpf) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Funcionarios");

            while (resultSet.next()) {
                String cpfSearch = resultSet.getString("cpf");
                Double doubleSalario = resultSet.getDouble("salario");

                if (cpfSearch.equals(cpf)) {
                    System.out.println(doubleSalario);
                }
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}