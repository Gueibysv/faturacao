/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package faturacao;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author Gueiby SIlva
 */
public class Venda extends javax.swing.JPanel {

    public static String codigobarra ;
    public static String id_cliente = "0";
    public int stock = 0;
    public Venda() {
        initComponents();
         data_load ();
    }
    public void data_load () {
        //Carregar clientes
        try {
             Statement s = db.mycon().createStatement();
             ResultSet rs = s.executeQuery("Select *from clientes");
             Vector v = new Vector ();
             while(rs.next()){
                 
                 v.add(rs.getString("nome_cliente"));
                 DefaultComboBoxModel com = new DefaultComboBoxModel(v);
                 com_cliente.setModel(com);
                 
             }
        } catch (Exception e) {
            JOptionPane.showMessageDialog (null,e);
            System.out.println(e);
        }
        
        //Carregar produtos
        try {
             Statement s = db.mycon().createStatement();
             ResultSet rs = s.executeQuery("Select *from produtos");
             Vector v = new Vector ();
             while(rs.next()){
                 
                 v.add(rs.getString("nome_produto"));
                 DefaultComboBoxModel com = new DefaultComboBoxModel(v);
                 com_produto.setModel(com);
                 
             }
        } catch (Exception e) {
            System.out.println(e);
        }
        //carregar o ultimo numero da fatura
        try {
          Statement s = db.mycon().createStatement();
           ResultSet rs = s.executeQuery("Select *from extra where id_extra = 1"); 
            if(rs.next()){
                id_fatura.setText(rs.getString("valor"));
            
            
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog (null,e);
        }
    //Somar novo id_fatura
    int i = Integer.valueOf(id_fatura.getText());
    i++;
    id_fatura.setText(String.valueOf(i));
    
    }
    public void produto_totalcalculo(){
        //calculo do produto
             Double quantidade = Double.valueOf(p_quantidade.getText());
             Double preco_unit = Double.valueOf(preco_u.getText());
             Double total;
             total = quantidade * preco_unit;
             total_u.setText(String.valueOf(total));
    }
    public void total_carrinho (){
        int n = jTable1.getRowCount();
        double total = 0;
        for (int i=0;i<n;i++){
            double valor = Double.valueOf(jTable1.getValueAt(i, 5).toString());
            total +=valor;
        }
        total_pago.setText(Double.toString(total));
        //quantidade total
        int ns = jTable1.getRowCount();
        int totals = 0;
        for (int i=0;i<ns;i++){
            int valor = Integer.valueOf(jTable1.getValueAt(i, 3).toString());
            totals +=valor;
        }
        quantidade_total.setText(Integer.toString(totals));
    }
    public void total (){
    
        Double pagamento = Double.valueOf(valor_pago.getText());
        if(pagamento>=0){
        Double total = Double.valueOf(total_pago.getText());
        Double saldo;
        saldo = pagamento - total;
        saldo_pagar.setText(String.valueOf(saldo));
        }else {
        
         JOptionPane.showMessageDialog (null,"Erro: valor não aceite");
        
        }
    }
    public void checkStock(){
        
         DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
         int rc = dt.getRowCount();
         for(int i = 0; i<rc;i++){
             
             String codigob = dt.getValueAt(i, 2).toString();
             System.out.println(codigob);
             String sell_qtd = dt.getValueAt(i, 3).toString();
             System.out.println(sell_qtd);
             
         try {
            Statement s = db.mycon().createStatement();
            
             ResultSet rs = s.executeQuery("Select quantidade from produtos where codigo_barra ='"+codigob+"'");
             if(rs.next()){
             stock = Integer.valueOf(rs.getString("quantidade"));
             
             }
             
        } catch (Exception e) {
            JOptionPane.showMessageDialog (null,e);
            System.out.println("ali");
             System.out.println(e);
        }
         int st_qtd = stock;
         int sel_qtd = Integer.valueOf(sell_qtd);
         int quantidade = st_qtd -sel_qtd;
         int newquantidade = Integer.valueOf(quantidade);
             try {
                Statement s = db.mycon().createStatement(); 
                s.executeUpdate("Update produtos set quantidade = '"+newquantidade+"' where codigo_barra ='"+codigob+"'");
             } catch (Exception e) {
                 JOptionPane.showMessageDialog (null,e);
                 System.out.println("aqui");
                 System.out.println(e);
                 
             }
         
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

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        id_fatura = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        preco_u = new javax.swing.JLabel();
        com_cliente = new javax.swing.JComboBox<>();
        com_produto = new javax.swing.JComboBox<>();
        p_quantidade = new javax.swing.JTextField();
        total_u = new javax.swing.JLabel();
        total_preco = new javax.swing.JLabel();
        codigob = new javax.swing.JLabel();
        total_preco1 = new javax.swing.JLabel();
        stock_qtd = new javax.swing.JLabel();
        total_preco2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        valor_pago = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        total_pago = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        saldo_pagar = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        quantidade_total = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 943, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 42, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Fatura nº:");

        id_fatura.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        id_fatura.setText("1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(id_fatura)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(id_fatura))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Produto:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Quantidade:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Preço unit:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Cliente:");

        preco_u.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        preco_u.setText("00.00");

        com_cliente.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        com_cliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "selecionar" }));
        com_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                com_clienteActionPerformed(evt);
            }
        });

        com_produto.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        com_produto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "selecionar" }));
        com_produto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                com_produtoActionPerformed(evt);
            }
        });

        p_quantidade.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        p_quantidade.setText("0");
        p_quantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                p_quantidadeKeyReleased(evt);
            }
        });

        total_u.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        total_u.setText("00.00");

        total_preco.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        total_preco.setText("Total:");

        codigob.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        codigob.setText("00");

        total_preco1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        total_preco1.setText("Código de barra:");

        stock_qtd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        stock_qtd.setText("0");

        total_preco2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        total_preco2.setText("Stock:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(com_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(com_produto, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(total_preco2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stock_qtd, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(15, 15, 15)
                        .addComponent(p_quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(preco_u, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(total_preco)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(total_u, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(total_preco1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(codigob, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(total_preco1)
                        .addComponent(codigob))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(total_preco)
                        .addComponent(total_u))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6)
                        .addComponent(preco_u)
                        .addComponent(com_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(com_produto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(p_quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(total_preco2)
                    .addComponent(stock_qtd))
                .addGap(8, 8, 8))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id_Fatura", "Nome", "Codigo de Barra", "Quantidade", "Preço Unit", "Total"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Remover");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Adicionar ao carrinho");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 0, 0));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Remover tudo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Valor Pago:");

        valor_pago.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        valor_pago.setText("0");
        valor_pago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                valor_pagoKeyReleased(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Total:");

        total_pago.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        total_pago.setText("00.00");
        total_pago.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Saldo/A pagar:");

        saldo_pagar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        saldo_pagar.setText("00.00");
        saldo_pagar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(total_pago, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saldo_pagar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(total_pago))
                .addGap(16, 16, 16)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saldo_pagar)
                    .addComponent(jLabel11))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Quantidade total:");

        quantidade_total.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        quantidade_total.setText("00");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(valor_pago, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(quantidade_total, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(valor_pago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(quantidade_total)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jButton4.setBackground(new java.awt.Color(0, 0, 0));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Pagar e Imprimir");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(271, 271, 271))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void com_produtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_com_produtoActionPerformed
        // carregar o preço unitario
        String nome = com_produto.getSelectedItem().toString();
        try {
            Statement s = db.mycon().createStatement();
             ResultSet rs = s.executeQuery("Select codigo_barra, preco, quantidade from produtos where nome_produto='"+nome+"'");
             if(rs.next()){
                 preco_u.setText(rs.getString("preco"));
                codigob.setText(rs.getString("codigo_barra"));
                stock_qtd.setText(rs.getString("quantidade"));
             }
             produto_totalcalculo();
             
        } catch (Exception e) {
            JOptionPane.showMessageDialog (null,e);
        }
    }//GEN-LAST:event_com_produtoActionPerformed

    private void p_quantidadeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_p_quantidadeKeyReleased
        // TODO add your handling code here:
        produto_totalcalculo();
    }//GEN-LAST:event_p_quantidadeKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // adicionar no carrinho
        int st_sell = Integer.valueOf(p_quantidade.getText());
        int st_qtd = Integer.valueOf(stock_qtd.getText());
        
        if (st_sell<st_qtd){
        DefaultTableModel dt =(DefaultTableModel) jTable1.getModel();
        Vector v = new Vector();
        v.add(id_fatura.getText());
        v.add(com_produto.getSelectedItem().toString());
        v.add(codigob.getText());
        v.add(p_quantidade.getText());
        v.add(preco_u.getText());
        v.add(total_u.getText());
        dt.addRow(v);
        total_carrinho ();
        total ();
    
        }else{
        
        JOptionPane.showMessageDialog (null, "Stock tem "+st_qtd+"quantidade apenas");
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Opção de remover
        
       
        try {
            DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
            int rw = jTable1.getSelectedRow();
            //String id = dt.getValueAt(rw, 0).toString();
            dt.removeRow(rw);    
        } catch (Exception e) {
        }
       total_carrinho ();
       total ();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // remover tudo
         DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
         dt.setRowCount(0);
         total_carrinho ();
         total ();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void valor_pagoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_valor_pagoKeyReleased
        // Valor pago
        total();
    }//GEN-LAST:event_valor_pagoKeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // salvar e mandar para a base de dados
        try {
          DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();   
           int rc = dt.getRowCount();
           for (int i = 0; i<rc;i++){
              String id_fatura = dt.getValueAt(i, 0).toString();
         
              String nome = dt.getValueAt(i, 1).toString();
              String codigob = dt.getValueAt(i, 2).toString();
              String quantidade = dt.getValueAt(i, 3).toString();
              String preco = dt.getValueAt(i, 4).toString();
              String total = dt.getValueAt(i, 5).toString();
              //carrinho base de dados
            Statement s = db.mycon().createStatement();  
             s.executeUpdate("INSERT INTO carrinho(id_fatura,nome_produto, codigo_barra, quantidade, preco, total) VALUES"
                     + "('"+id_fatura+"','"+nome+"','"+codigob+"','"+quantidade+"', '"+preco+"','"+total+"')");
              
              
            }
            
            JOptionPane.showMessageDialog (null,"Pagamento feito com sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog (null,e);
            System.out.println(e);
        }
        
        
        try {
            String nome = com_cliente.getSelectedItem().toString();
            String quantidade = quantidade_total.getText();
            String total = total_pago.getText();
            String saldo = saldo_pagar.getText();
            String id = id_fatura.getText();
           
            
            //verificar se foi pago 
            Double tot = Double.valueOf(total_pago.getText());
            Double pago = Double.valueOf(valor_pago.getText());
             String estado = "null";
             if(pago.equals(0.0)){
                  estado = "Não pago";
             } else if (tot>pago){
                  estado = "Partial";
             }else if (tot<=pago){
                 estado = "Pago";
             }
             Statement s = db.mycon().createStatement(); 
                s.executeUpdate("INSERT INTO venda(id_fatura,id_cliente,nome_cliente,quantidade_total, total, estado, saldo) VALUES"
                     + "('"+id+"','"+id_cliente+"','"+nome+"','"+quantidade+"', '"+total+"','"+estado+"','"+saldo+"')");
        } catch (NumberFormatException | SQLException e) {
             JOptionPane.showMessageDialog (null,e);
        }
        
        //Salvar o ultimo id da fatura
        try {
            String id = id_fatura.getText();
            Statement s = db.mycon().createStatement(); 
            s.executeUpdate("Update extra set valor='"+id+"' where id_extra = 1");
        } catch (SQLException e) {
             JOptionPane.showMessageDialog (null,e);
        }
        
        try {
            
            
         
        HashMap para = new HashMap();
        para.put("faturaID", id_fatura.getText());
         ReportView r = new ReportView("src\\reports\\imprimir2.jrxml", para);
            r.setVisible(true);
            
        } catch (Exception e) {
        }
        
        checkStock();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void com_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_com_clienteActionPerformed
        // 
        String nome = com_cliente.getSelectedItem().toString();
        try {
            Statement s = db.mycon().createStatement();
             ResultSet rs = s.executeQuery("Select id_cliente from clientes where nome_cliente='"+nome+"'");
             if(rs.next()){
                 id_cliente = rs.getString("id_cliente");
                
             }
           
             
        } catch (Exception e) {
            JOptionPane.showMessageDialog (null,e);
        }
    }//GEN-LAST:event_com_clienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel codigob;
    private javax.swing.JComboBox<String> com_cliente;
    private javax.swing.JComboBox<String> com_produto;
    private javax.swing.JLabel id_fatura;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField p_quantidade;
    private javax.swing.JLabel preco_u;
    private javax.swing.JLabel quantidade_total;
    private javax.swing.JLabel saldo_pagar;
    private javax.swing.JLabel stock_qtd;
    private javax.swing.JLabel total_pago;
    private javax.swing.JLabel total_preco;
    private javax.swing.JLabel total_preco1;
    private javax.swing.JLabel total_preco2;
    private javax.swing.JLabel total_u;
    private javax.swing.JTextField valor_pago;
    // End of variables declaration//GEN-END:variables
}
