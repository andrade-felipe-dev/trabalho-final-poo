package br.edu.irati.tads.view;

import br.edu.irati.ifpr.tads.controller.ClienteController;
import br.edu.irati.ifpr.tads.controller.CursoController;
import br.edu.irati.ifpr.tads.exception.PersistenceException;
import br.edu.irati.ifpr.tads.model.Cliente;
import br.edu.irati.ifpr.tads.model.Curso;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class GerenciarClienteGUI extends javax.swing.JFrame {
    private Cliente clienteAtual;
    
    private List<Cliente> listaClientes;
    
    /**
     * Creates new form GerenciarClienteGUI
     */
    public GerenciarClienteGUI() {
        initComponents();
        clienteAtual = new Cliente();
        this.atualizarTela();
    }
    
    private void atualizarTela() {
        jTextFieldNome.setText(this.clienteAtual.getNome());
        jTextFieldTelefone.setText(this.clienteAtual.getTelefone());
        jTextFieldEmail.setText(this.clienteAtual.getEmail());
        jTextFieldEndereco.setText(this.clienteAtual.getEndereco());
        jFormattedSaldoAtual.setText(Float.toString(this.clienteAtual.getSaldoAtual()));
        jFormattedTextFieldLimiteFiado.setText(Float.toString(this.clienteAtual.getLimiteFiado()));
        this.atualizarComboCurso();
        this.atualizarTabela();
    }
    
    private void atualizarComboCurso() {
        try {
            CursoController cursoController = new CursoController();
            List<Curso> listaCurso = cursoController.listarTodos();
            List<String> listaNome = cursoController.formatarCursoNome(listaCurso);
            DefaultComboBoxModel model = new DefaultComboBoxModel(listaNome.toArray());
            
            jComboBoxCurso.setModel(model);
            jComboBoxCurso.setSelectedIndex(listaNome.indexOf(this.clienteAtual.getCurso().getNome()));
        } catch (PersistenceException ex) {
            JOptionPane.showMessageDialog(null, "Cheguei");

            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    private void atualizarTabela() {
        try {
            ClienteController clienteController = new ClienteController();
            this.listaClientes = clienteController.listarTodos();
        } catch (PersistenceException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível acessar o banco de dados.");
        }
       
        Object colunas[] = new Object[7];
        colunas[0] = "Nome";
        colunas[1] = "Telefone";
        colunas[2] = "Email";
        colunas[3] = "Endereço";
        colunas[4] = "Saldo Atual";
        colunas[5] = "Limite Fiado";
        colunas[6] = "Curso";
        
        Object data[][] = new Object[this.listaClientes.size()][7];
        for (int i = 0; i < this.listaClientes.size(); i++) {
            Cliente cliente = this.listaClientes.get(i);
            data[i][0] = cliente.getNome();
            data[i][1] = cliente.getTelefone();
            data[i][2] = cliente.getEmail();
            data[i][3] = cliente.getEndereco();
            data[i][4] = cliente.getSaldoAtual();
            data[i][5] = cliente.getLimiteFiado();
            data[i][6] = cliente.getCurso().getNome();
        }
        
        DefaultTableModel model = new DefaultTableModel(data, colunas);
        jTableGerenciarClientes.setModel(model);
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
        jTable1 = new javax.swing.JTable();
        jLabelNome = new javax.swing.JLabel();
        jLabelTelefone = new javax.swing.JLabel();
        jLabelEmail = new javax.swing.JLabel();
        jLabelEndereco = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jTextFieldEmail = new javax.swing.JTextField();
        jTextFieldEndereco = new javax.swing.JTextField();
        jLabelSaldoAtual = new javax.swing.JLabel();
        jFormattedSaldoAtual = new javax.swing.JFormattedTextField();
        jLabelLimiteFiado = new javax.swing.JLabel();
        jFormattedTextFieldLimiteFiado = new javax.swing.JFormattedTextField();
        jComboBoxCurso = new javax.swing.JComboBox<>();
        jLabelCurso = new javax.swing.JLabel();
        jButtonCancelar = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jTextFieldTelefone = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableGerenciarClientes = new javax.swing.JTable();
        jButtonExcluir = new javax.swing.JButton();
        jButtonSelecionar = new javax.swing.JButton();
        jButtonFechar = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gerenciar Clientes");
        setResizable(false);

        jLabelNome.setText("Nome");

        jLabelTelefone.setText("Telefone");

        jLabelEmail.setText("Email");

        jLabelEndereco.setText("Endereco");

        jLabelSaldoAtual.setText("Saldo Atual");

        jFormattedSaldoAtual.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        jLabelLimiteFiado.setText("Limite Fiado");

        jFormattedTextFieldLimiteFiado.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));

        jComboBoxCurso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabelCurso.setText("Curso");

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jTableGerenciarClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Nome", "Telefone", "Email", "Endereco", "Saldo Atual", "Limite Fiado", "Curso"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableGerenciarClientes);

        jButtonExcluir.setText("Excluir");
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });

        jButtonSelecionar.setText("Selecionar");
        jButtonSelecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSelecionarActionPerformed(evt);
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelCurso)
                                    .addComponent(jLabelSaldoAtual))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jFormattedSaldoAtual)
                                    .addComponent(jComboBoxCurso, 0, 266, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButtonCancelar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonSalvar))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabelLimiteFiado)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jFormattedTextFieldLimiteFiado, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelEndereco)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelTelefone)
                                            .addComponent(jLabelNome))
                                        .addGap(25, 25, 25))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabelEmail)
                                        .addGap(44, 44, 44)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextFieldEmail)
                                    .addComponent(jTextFieldTelefone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
                                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.Alignment.LEADING))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonFechar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonExcluir)
                        .addGap(23, 23, 23)
                        .addComponent(jButtonSelecionar)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNome)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTelefone)
                    .addComponent(jTextFieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelEmail)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEndereco)
                    .addComponent(jTextFieldEndereco))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSaldoAtual)
                    .addComponent(jFormattedSaldoAtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelLimiteFiado, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldLimiteFiado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonCancelar)
                        .addComponent(jButtonSalvar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelCurso)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonExcluir)
                        .addComponent(jButtonSelecionar))
                    .addComponent(jButtonFechar))
                .addGap(11, 11, 11))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.clienteAtual = new Cliente();
        this.atualizarTela();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSelecionarActionPerformed
        int indexSelectedRow = jTableGerenciarClientes.getSelectedRow();
        if (indexSelectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma linha da tabela.", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        } else {
            this.clienteAtual = this.listaClientes.get(indexSelectedRow);
            this.atualizarTela();
        }
    }//GEN-LAST:event_jButtonSelecionarActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
       int indexSelectedRow = jTableGerenciarClientes.getSelectedRow();
        if (indexSelectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma linha da tabela.", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        } else {
            Cliente cliente = this.listaClientes.get(indexSelectedRow);
            int confirm = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir os dados de " + cliente.getNome(), "Confirmação", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                try {
                    ClienteController controller = new ClienteController();
                    controller.excluir(cliente);
                    this.listaClientes.remove(indexSelectedRow);
                    this.atualizarTabela();

                } catch (PersistenceException e) {
                    JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        try {
            CursoController cursoController = new CursoController();
            ClienteController clienteController = new ClienteController();
            
            String nome = jTextFieldNome.getText();
            String telefone = jTextFieldTelefone.getText();
            String email = jTextFieldEmail.getText();
            String endereco = jTextFieldEndereco.getText();
            float saldoAtual = Float.parseFloat(jFormattedSaldoAtual.getText());
            float limiteFiado = Float.parseFloat(jFormattedTextFieldLimiteFiado.getText());
            Curso curso = cursoController.buscarCursoPorNome((String) jComboBoxCurso.getSelectedItem());
            this.clienteAtual.setNome(nome);
            this.clienteAtual.setTelefone(telefone);
            this.clienteAtual.setEmail(email);
            this.clienteAtual.setEmail(email);
            this.clienteAtual.setEndereco(endereco);
            this.clienteAtual.setSaldoAtual(saldoAtual);
            this.clienteAtual.setLimiteFiado(limiteFiado);
            this.clienteAtual.setCurso(curso);
            clienteController.salvar(clienteAtual);
            JOptionPane.showMessageDialog(this, "Dados salvos", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            this.clienteAtual = new Cliente();
            this.atualizarTela();
        } catch (PersistenceException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFecharActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButtonFecharActionPerformed

 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonFechar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JButton jButtonSelecionar;
    private javax.swing.JComboBox<String> jComboBoxCurso;
    private javax.swing.JFormattedTextField jFormattedSaldoAtual;
    private javax.swing.JFormattedTextField jFormattedTextFieldLimiteFiado;
    private javax.swing.JLabel jLabelCurso;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelEndereco;
    private javax.swing.JLabel jLabelLimiteFiado;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelSaldoAtual;
    private javax.swing.JLabel jLabelTelefone;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTableGerenciarClientes;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldEndereco;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldTelefone;
    // End of variables declaration//GEN-END:variables
}
