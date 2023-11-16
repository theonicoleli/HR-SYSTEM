package entities.DataBase;

import entities.Funcionario;
import entities.FuncionarioException;
import entities.Pessoa;
import entities.RH;
import entities.enumerator.Setor;
import entities.enumerator.Turno;
import entities.records.RHTABLE;
import entities.records.SessaoAtual;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

public class DAO {

    private RH rh;
    private static SessaoAtual session;
    public Connection con;
    private RHTABLE rhtable;
    private SimpleDateFormat sdf;
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String user = "root";
    private String password = "PUC@1234";
    private String url = "jdbc:mysql://localhost:3306/pjbl";

    public DAO() {
        con = conectar();
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        rhtable = new RHTABLE(new HashSet<>());
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
                this.funcionariosDb(funcionario.getCpf(), false) == false) {
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
            Funcionario func = (Funcionario) funcionario;
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO Funcionarios (Nome, DataNasc, Salario, Cpf, NomeSetor, Turno, CarteiraTrabalho, funcao) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
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
            preparedStatement.setString(8, func.getFuncao());

            preparedStatement.executeUpdate();
            preparedStatement.close();
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

    public Set<? super Pessoa> pegandoFuncionariosBd() {
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
                Pessoa func;

                if (nomeSetor.equals(Setor.RH)) {
                    func = new RH(nome, cpfSearch, dtNascimento,
                            nomeSetor, salario, carteiraTrabalho, funcao, turno);
                } else {
                    func = new Funcionario(nome, cpfSearch, dtNascimento,
                            nomeSetor, salario, carteiraTrabalho, funcao, turno);
                }

                if (rhtable.contains(func) == false) {
                    rhtable.addFuncionarioList(func);
                }
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

    public String verificarPessoa(String login) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Funcionarios");

            while (resultSet.next()) {
                String loginSearch = resultSet.getString("Login");
                String senhaSearch = resultSet.getString("Senha");
                String cpf = resultSet.getString("cpf");

                if (loginSearch.equals(login)) {
                    return rh.getFuncionario(cpf);
                }
            }
            resultSet.close();
            statement.close();
            connection.close();
            return null;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public Pessoa pessoaDoRh(String login, String senha) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Funcionarios");

            while (resultSet.next()) {
                String loginSearch = resultSet.getString("Login");
                String setor = resultSet.getString("NomeSetor");
                String cpf = resultSet.getString("cpf");

                if (loginSearch.equals(login) && setor.equalsIgnoreCase("rh")) {
                    return RH.getFuncionarioRh(cpf);
                }
            }
            resultSet.close();
            statement.close();
            connection.close();
            return null;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public boolean booleanPessoaDoRh(String login,String senha) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Funcionarios");

            while (resultSet.next()) {
                String loginSearch = resultSet.getString("Login");
                String setor = resultSet.getString("NomeSetor");
                String cpf = resultSet.getString("cpf");

                if (loginSearch.equals(login) && setor.equalsIgnoreCase("rh")) {
                    return true;
                }
            }
            resultSet.close();
            statement.close();
            connection.close();
            return false;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public void setSession(SessaoAtual session) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "SELECT * FROM Funcionarios WHERE LOGIN = ? AND SENHA = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, session.loginAtual());
                preparedStatement.setString(2, session.senha());

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    this.session = session;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static SessaoAtual getSessao() {
        return session;
    }
    public static String pegarCPF(int linha){
        DAO dataBase = new DAO();
        try {
            Statement st = dataBase.con.createStatement();
            String sql = "SELECT CPF FROM funcionarios ORDER BY ID_Func LIMIT "+linha+",1;";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String cpf = String.valueOf(rs.getString("CPF"));
                System.out.println(cpf);
                return cpf;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


}