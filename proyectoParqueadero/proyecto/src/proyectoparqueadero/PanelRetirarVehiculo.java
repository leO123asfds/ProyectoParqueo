
package proyectoparqueadero;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class PanelRetirarVehiculo extends javax.swing.JPanel {


    public PanelRetirarVehiculo() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tfPlacaRetiro = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        button1 = new java.awt.Button();

        setPreferredSize(new java.awt.Dimension(453, 400));

        tfPlacaRetiro.setFont(new java.awt.Font("Segoe UI Symbol", 1, 24)); // NOI18N

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI Symbol", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Modulo de salida de vehiculos del parqueadero");

        jLabel2.setText("Placa");

        button1.setBackground(new java.awt.Color(255, 51, 0));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setLabel("Retirar");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(223, 223, 223)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(189, 189, 189)
                        .addComponent(tfPlacaRetiro, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(39, 39, 39)
                .addComponent(jLabel2)
                .addGap(11, 11, 11)
                .addComponent(tfPlacaRetiro, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(153, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
            Double valorAPagar=0.0;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar cal  = Calendar.getInstance();
            Date date = cal.getTime();
            String fechaHora = dateFormat.format(date);
        try {
            // TODO add your handling code here:

            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/bdparqueadero","root","");
            Statement stat = conexion.createStatement();
            ResultSet rs = stat.executeQuery("SELECT horaentrada,tipovehiculo FROM vehiculo WHERE placa='"+tfPlacaRetiro.getText()+"' AND estado='Disponible'");
            rs.first();
            String horaSalida = rs.getString(1);
            Date horasalida = dateFormat.parse(horaSalida);
            int minuntosACobrar = (int) (date.getTime()-horasalida.getTime())/6;
            
            System.out.println(minuntosACobrar);
            
            if(rs.getString(2).equals("Automovil")){
                valorAPagar=minuntosACobrar*3.00;
            }else if(rs.getString(2).equals("Motocicleta")){
                 valorAPagar=minuntosACobrar*2.00;
            }
            
            System.out.println("Valos a pagar por "+rs.getString(2)+"= "+valorAPagar);
            stat.executeUpdate("UPDATE vehiculos SET horasalida='"+fechaHora+"',estado='No Disponible', valorpagado='"+valorAPagar+"' WHERE placa='"+tfPlacaRetiro.getText()+"' AND estado='Disponible'");
            int respuesta = JOptionPane.showConfirmDialog(null,"Valor a pagar:  $"+valorAPagar+"'\nDesea Imprimir Recibo","Salida de vehiculo",JOptionPane.YES_NO_OPTION);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PanelRetirarVehiculo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Vehiculo retirado");
            
            Logger.getLogger(PanelRetirarVehiculo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(PanelRetirarVehiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_button1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button button1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField tfPlacaRetiro;
    // End of variables declaration//GEN-END:variables
}
