/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.edu.imepac.clinica.medica.medicos;

import br.edu.imepac.clinica.medica.daos.EspecialidadeDao;
import br.edu.imepac.clinica.medica.daos.MedicoDao;
import br.edu.imepac.clinica.medica.entidades.Especialidade;
import br.edu.imepac.clinica.medica.entidades.Medico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * @author ehf_v
 */
public class ListagemMedico extends javax.swing.JFrame {

    private DefaultTableModel defaultTableModel;
    private EspecialidadeDao especialidadeDao;
    private MedicoDao medicoDao;
    private List<Medico> itemsMedicos = new java.util.ArrayList<>();

    /**
     * Creates new form ListagemMedico
     */
    public ListagemMedico() {
        initComponents();
        inicializarColunasNaTabela();
        inicializarEpecialidadeDao();
        inicializarMedicoDao();
        carregarListaMedicos();
        exibirListaMedicoNaTabela();
    }

    private void carregarListaMedicos() {
        try {
            for (Medico medico : medicoDao.listarTodos()) {
                int especialidadeId = medico.getEspecialidadeId();
                Especialidade especialidade = especialidadeDao.buscarPorId(especialidadeId);
                medico.setEspecialidade(especialidade);
                itemsMedicos.add(medico);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar médicos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            dispose();
        }
    }

    private void exibirListaMedicoNaTabela() {
        defaultTableModel.setRowCount(0);
        try {
            defaultTableModel.setRowCount(0);
            for (Medico medico : itemsMedicos) {
                Object[] linha = {medico.getId(), medico.getNome(), medico.getCrm(), medico.getEspecialidade().getNome()};
                defaultTableModel.addRow(linha);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar médicos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void inicializarEpecialidadeDao() {
        try {
            this.especialidadeDao = new EspecialidadeDao();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados - especialidades!");
            dispose();
        }
    }

    private void inicializarMedicoDao() {
        try {
            this.medicoDao = new MedicoDao();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "Erro ao inicializar ao banco de dados - médico!");
            dispose();
        }
    }

    private void inicializarColunasNaTabela() {
        String[] colunas = {"Id", "Nome", "CRM", "Especialidade"};
        this.defaultTableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // impede edição de todas as células
            }
        };
        medicoJtable.setModel(defaultTableModel);
    }

    private void excluirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirButtonActionPerformed
        int linhaSelecionada = medicoJtable.getSelectedRow();
        if (linhaSelecionada != -1) {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Tem certeza que deseja excluir este médico?",
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                int id = (int) medicoJtable.getModel().getValueAt(linhaSelecionada, 0);
                excluirMedicoNoBanco(id);
                itemsMedicos.remove(linhaSelecionada);
                this.carregarDadosNaTabela();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma linha para excluir.");
        }
    }//GEN-LAST:event_excluirButtonActionPerformed

    private void excluirMedicoNoBanco(int id) {
        try {
            this.medicoDao.deletar(id);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar os dados!");
            dispose();
        }
    }

    private void carregarDadosNaTabela() {
        defaultTableModel.setRowCount(0);
        for (Medico medico : itemsMedicos) {
            Object[] linha = {medico.getId(), medico.getNome(), medico.getCrm(), medico.getEspecialidade().getNome()};
            defaultTableModel.addRow(linha);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        medicoJtable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        medicoJtable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String[]{
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        jScrollPane1.setViewportView(medicoJtable);

        jButton1.setText("Editar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Excluir");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int linhaSelecionada = medicoJtable.getSelectedRow();

        if (linhaSelecionada != -1) {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Tem certeza que deseja atualizar este médico?",
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                int id = (int) medicoJtable.getModel().getValueAt(linhaSelecionada, 0);
                AtualizarMedico atualizarMedico = new AtualizarMedico(id);
                atualizarMedico.pack();
                atualizarMedico.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma linha para excluir.");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ListagemMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListagemMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListagemMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListagemMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListagemMedico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable medicoJtable;
    // End of variables declaration//GEN-END:variables
}
