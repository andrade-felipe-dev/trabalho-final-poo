/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.edu.irati.tads.view;

import br.edu.irati.ifpr.tads.controller.CompraController;
import br.edu.irati.ifpr.tads.exception.PersistenceException;
import br.edu.irati.ifpr.tads.model.Compra;
import br.edu.irati.ifpr.tads.model.CompraEstadoENUM;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author felipe
 */
public class GerenciarCompraGUI extends javax.swing.JFrame {
    private List<Compra> listaCompras;
    
    
    /**
     * Creates new form GerenciarCompra
     */
    public GerenciarCompraGUI() {
        initComponents();
        atualizarTabela();
    }
    
    private void atualizarTabela() {
        try {
            CompraController compraController = new CompraController();
            this.listaCompras = compraController.buscarTodos();
            
            Object colunas[] = new Object[4];
            colunas[0] = "Data";
            colunas[1] = "Nome Cliente";
            colunas[2] = "Estado";
            colunas[3] = "Valor";


            Object data[][] = new Object[listaCompras.size()][4];
            for (int i = 0; i < listaCompras.size(); i++) {
                Compra compra = listaCompras.get(i);
                data[i][0] = compra.getDataHora();
                data[i][1] = compra.getCliente().getNome();
                data[i][2] = compra.getEstado();
            }
           
            DefaultTableModel model = new DefaultTableModel(data, colunas);
            jTableGerenciarCompra.setModel(model);

        } catch (PersistenceException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível acessar o banco de dados.");
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
        jTableGerenciarCompra = new javax.swing.JTable();
        jButtonExcluir = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();
        jButtonFechar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTableGerenciarCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableGerenciarCompra);

        jButtonExcluir.setText("Excluir");
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });

        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jButtonFechar.setText("Fechar");
        jButtonFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFecharActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonFechar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonExcluir)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonSalvar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonExcluir)
                    .addComponent(jButtonSalvar)
                    .addComponent(jButtonFechar))
                .addGap(0, 23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        int indexSelectedRow = jTableGerenciarCompra.getSelectedRow();

        if (indexSelectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma linha da tabela.", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        } else {
            Compra compra = this.listaCompras.get(indexSelectedRow);
            
            int confirm = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir os dados do pagamento do cliente" + compra.getCliente().getNome(), "Confirmação", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                try {
                    CompraController controller = new CompraController();
                    if(compra.getEstado().equals(CompraEstadoENUM.PAGO)) {
                        controller.desvincularPagamento(compra);
                    }

                    controller.excluir(compra);
                    this.listaCompras.remove(indexSelectedRow);
                    this.atualizarTabela();

                } catch (PersistenceException e) {
                    JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
       List<Compra> lancarPagamentoPendente = new ArrayList<>();
       
       for (Compra compra : this.listaCompras) {
           if (compra.getEstado().equals(CompraEstadoENUM.PENDENTE)) {
               lancarPagamentoPendente.add(compra);
           }
       }
               
       String nome = lancarPagamentoPendente.get(0).getCliente().getNome();
       for (int i = 0; i < lancarPagamentoPendente.size(); i++) {
           if (!nome.equals(lancarPagamentoPendente.get(i).getCliente().getNome())) {
                JOptionPane.showMessageDialog(this, "Selecione somente um cliente", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
           }
       }
       
       RealizarPagamentoGUI gerenciarPagamentoGUI = new RealizarPagamentoGUI(lancarPagamentoPendente.get(0).getCliente(), lancarPagamentoPendente);
       gerenciarPagamentoGUI.setVisible(true);
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFecharActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButtonFecharActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonFechar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableGerenciarCompra;
    // End of variables declaration//GEN-END:variables
}
