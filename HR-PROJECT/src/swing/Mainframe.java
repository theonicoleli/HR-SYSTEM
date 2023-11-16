package swing;

import entities.DataBase.DAO;
import entities.Funcionario;
import entities.Pessoa;
import entities.RH;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;


public class Mainframe extends java.awt.Frame {

    private static DAO dao;
    private Pessoa pessoaLogada;

    public Mainframe () {
        this.dao = new DAO();
        pessoaLogada = dao.pessoaDoRh(dao.getSessao().loginAtual(), dao.getSessao().senha());
        initComponents();
    }

    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 51), new java.awt.Dimension(0, 51), new java.awt.Dimension(32767, 51));
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(173, 216, 230));
        jPanel2.setBackground(new java.awt.Color(173, 216, 230));
        jPanel3.setBackground(new java.awt.Color(173, 216, 230));
        jPanel4.setBackground(new java.awt.Color(173, 216, 230));

        setPreferredSize(new java.awt.Dimension(880, 432));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        pack();
        setLocationRelativeTo(null);

        add(jPanel1, java.awt.BorderLayout.NORTH);

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 24));
        jLabel2.setText("Lista de funcionários");
        jPanel1.add(jLabel2);

        jPanel2.setPreferredSize(new java.awt.Dimension(620, 412));
        jPanel2.add(filler1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] { "Nome", "Salário", "Turno", "Info"

                }
        ) {
            Class[] types = new Class [] {
                    java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setBackground(new java.awt.Color(173, 216, 230));
        jTable1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12));
        jTable1.setGridColor(new java.awt.Color(0, 0, 0));
        jTable1.setMinimumSize(new java.awt.Dimension(60, 120));
        jTable1.setPreferredSize(new java.awt.Dimension(205, 360));
        jTable1.setRowSelectionAllowed(true);
        jTable1.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jTable1.setShowGrid(true);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(1).setResizable(true);
        }
        atualizarTabela();
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = jTable1.rowAtPoint(evt.getPoint());
                int col = jTable1.columnAtPoint(evt.getPoint());
                System.out.println(row);
                if (row >= 0 && col >= 3) {
                    new TelaInfo(row).setVisible(true);

                }
            }
        });

        jPanel2.add(jScrollPane1);

        jPanel4.setAlignmentX(50.0F);
        jPanel4.setAlignmentY(100.0F);
        jPanel4.setMinimumSize(new java.awt.Dimension(10000, 10000));
        jPanel4.setPreferredSize(new java.awt.Dimension(150, 200));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jButton3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12));
        jButton3.setLabel("Adicionar Pessoa");
        jButton3.setPreferredSize(new java.awt.Dimension(140, 23));
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weighty = 0.4;
        jPanel4.add(jButton3, gridBagConstraints);
        jPanel4.add(filler2, new java.awt.GridBagConstraints());

        jButton1.setLabel("Editar Pessoa");
        jButton1.setPreferredSize(new java.awt.Dimension(130, 23));
        jButton1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 5;
        jPanel4.add(jButton1, gridBagConstraints);

        jButton4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setPreferredSize(new java.awt.Dimension(130, 23));
        jButton4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12));
        jButton4.setLabel("Excluir Pessoa");
        jButton4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weighty = 0.6;
        jPanel4.add(jButton4, gridBagConstraints);
        jPanel2.add(jPanel4);


        jButton5.setText("Encerrar sessão");
        jButton5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton5.setBackground(new Color(255,105,97));
        jButton5.setFocusPainted(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });



        jPanel1.add(jButton5);



        jPanel1.add(jPanel2);

        add(jPanel1, java.awt.BorderLayout.NORTH);

        add(jPanel3, java.awt.BorderLayout.CENTER);

        jButton1.setBackground(Color.WHITE);
        jButton3.setBackground(Color.WHITE);
        jButton4.setBackground(Color.WHITE);

        pack();
    }
    private void exitForm(java.awt.event.WindowEvent evt) {
        System.exit(0);
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        int row = jTable1.getSelectedRow();

        if (row != -1) {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

            if (pessoaLogada instanceof RH) {
                RH adm = (RH) pessoaLogada;

                String cpf = DAO.pegarCPF(row);

                new TelaEdit(adm.getFunc(cpf)).setVisible(true);
                dispose();
            } else {
                System.out.println("CPF não encontrado na tabela.");
            }

        } else {
            System.out.println("Nenhuma linha selecionada.");
        }
    }


    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {

        new AddEdit().setVisible(true);
        dispose();

    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        int row = jTable1.getSelectedRow();

        if (row != -1) {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

            if (pessoaLogada instanceof RH) {
                RH adm = (RH) pessoaLogada;
                String cpf = DAO.pegarCPF(row);
                System.out.println("CPF: " + cpf);
                adm.removerFuncionario(cpf);
                model.removeRow(row);
                JOptionPane.showMessageDialog(this, "Funcionário removido com sucesso! ", "Aviso", JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(this, "Você não possui permissão para realizar esta operação. ", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Não foi possível remover este funcionário, tente novamente. ", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int getColumnIndexByName(DefaultTableModel model, String columnName) {
        for (int i = 0; i < model.getColumnCount(); i++) {
            if (model.getColumnName(i).equalsIgnoreCase(columnName)) {
                return i;
            }
        }
        return -1;
    }

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {
    }
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
        new telalogin().setVisible(true);
        dispose();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mainframe().setVisible(true);
            }
        });
    }

    public static void atualizarTabela(){
        DAO dataBase = new DAO();
        try{
            Statement st = dataBase.con.createStatement();
            String sql = "Select * from funcionarios";
            ResultSet rs = st.executeQuery(sql);
            DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
            tableModel.setRowCount(0);
            while(rs.next()){
                String nome = String.valueOf(rs.getString("Nome"));
                String salario = String.valueOf(rs.getDouble("Salario"));
                String turno = String.valueOf(rs.getString("Turno"));
                String info = ("+info");

                String tbData[] = {nome,salario,turno,info};
                tableModel.addRow(tbData);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void info() {
        try (Statement st = dao.con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM funcionarios")) {

            DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
            tableModel.setRowCount(0);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            String[] columnNames = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columnNames[i - 1] = metaData.getColumnName(i);
            }

            tableModel.setColumnIdentifiers(columnNames);

            while (rs.next()) {
                Object[] tbData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    tbData[i - 1] = rs.getObject(i);
                }
                tableModel.addRow(tbData);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error atualizando a tabela", e);
        }
    }

    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable1;
}
