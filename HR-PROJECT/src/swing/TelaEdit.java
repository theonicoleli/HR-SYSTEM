package swing;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import entities.DataBase.DAO;
import entities.Funcionario;
import entities.Pessoa;
import entities.RH;
import entities.enumerator.Setor;
import entities.enumerator.Turno;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Dell
 */
public class TelaEdit extends javax.swing.JFrame {

    private DAO dao;
    private Pessoa pessoaLogada;
    private static Funcionario funcionario;

    /**
     * Creates new form TelaEdit
     */
    public TelaEdit(Funcionario funcionario) {
        this.funcionario = funcionario;
        this.dao = new DAO();
        pessoaLogada = dao.pessoaDoRh(dao.getSessao().loginAtual(), dao.getSessao().senha());
        initComponents();
    }

    public static Funcionario getFuncionario() {
        return funcionario;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12));
        jLabel7.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12));
        jLabel9.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12));
        jLabel10.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12));
        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12));

        jPanel4.setBackground(new java.awt.Color(173, 216, 240));
        jPanel1.setBackground(new java.awt.Color(173, 216, 240));

        jButton1.setBackground(java.awt.Color.WHITE);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(173, 216, 240));

        jPanel4.setPreferredSize(new java.awt.Dimension(237, 420));

        jLabel7.setText("Salário:");

        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField5.setForeground(new java.awt.Color(153, 153, 153));
        jTextField5.setText("Salário");
        jTextField5.setPreferredSize(new java.awt.Dimension(100, 22));
        jTextField5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (jTextField5.getText().equals("Salário")) {
                    jTextField5.setText("");
                    jTextField5.setForeground(new java.awt.Color(0, 0, 0));
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (jTextField5.getText().isEmpty()) {
                    jTextField5.setForeground(new java.awt.Color(153, 153, 153));
                    jTextField5.setText("Salário");
                }
            }
        });

        jLabel8.setText("Setor:");

        jTextField6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField6.setForeground(new java.awt.Color(153, 153, 153));
        jTextField6.setText("Setor");
        jTextField6.setPreferredSize(new java.awt.Dimension(100, 22));
        jTextField6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (jTextField6.getText().equals("Setor")) {
                    jTextField6.setText("");
                    jTextField6.setForeground(new java.awt.Color(0, 0, 0));
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (jTextField6.getText().isEmpty()) {
                    jTextField6.setForeground(new java.awt.Color(153, 153, 153));
                    jTextField6.setText("Setor");
                }
            }
        });

        jLabel9.setText("Função:");

        jTextField7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField7.setForeground(new java.awt.Color(153, 153, 153));
        jTextField7.setText("Função");
        jTextField7.setPreferredSize(new java.awt.Dimension(100, 22));
        jTextField7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (jTextField7.getText().equals("Função")) {
                    jTextField7.setText("");
                    jTextField7.setForeground(new java.awt.Color(0, 0, 0));
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (jTextField7.getText().isEmpty()) {
                    jTextField7.setForeground(new java.awt.Color(153, 153, 153));
                    jTextField7.setText("Função");
                }
            }
        });

        jLabel10.setText("Turno:");

        jTextField8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField8.setForeground(new java.awt.Color(153, 153, 153));
        jTextField8.setText("Turno");
        jTextField8.setPreferredSize(new java.awt.Dimension(100, 22));
        jTextField8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (jTextField8.getText().equals("Turno")) {
                    jTextField8.setText("");
                    jTextField8.setForeground(new java.awt.Color(0, 0, 0));
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (jTextField8.getText().isEmpty()) {
                    jTextField8.setForeground(new java.awt.Color(153, 153, 153));
                    jTextField8.setText("Turno");
                }
            }
        });

        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setLabel("Enviar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Editando: " + funcionario.getNome() + ", CPF: " + funcionario.getCpf());
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(jLabel1)
                                .addContainerGap(45, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(123, 123, 123)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGap(29, 29, 29)
                                                .addComponent(jLabel9))
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGap(32, 32, 32)
                                                .addComponent(jLabel7))
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGap(36, 36, 36)
                                                .addComponent(jLabel8))
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGap(34, 34, 34)
                                                .addComponent(jLabel10))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jButton1)
                                                .addGap(12, 12, 12)))
                                .addContainerGap(130, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(jButton1)
                                .addContainerGap(56, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
        );

        pack();

    }

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        if (pessoaLogada instanceof RH) {
            RH adm = (RH) pessoaLogada;

            try {
                Double salario = Double.parseDouble(jTextField5.getText());
                Setor setor = Setor.valueOf(jTextField6.getText().toUpperCase());
                String funcao = jTextField7.getText();
                Turno turno = Turno.valueOf(jTextField8.getText().toUpperCase());

                adm.alterarFuncionario(funcionario.getCpf(), salario, setor, turno, funcao);

                JOptionPane.showMessageDialog(this, "Usuário alterado com sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);

                new Mainframe().setVisible(true);
                dispose();
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, "Por favor, preencha os campos corretamente.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro desconhecido ao editar funcionário.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new TelaEdit(TelaEdit.getFuncionario()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration                   
}