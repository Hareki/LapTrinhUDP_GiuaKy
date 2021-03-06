/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltm18;

import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import ltm18.main.Client;

/**
 *
 * @author Minh Tu
 */
public class ConnectionInputDialog extends javax.swing.JDialog {

    /**
     * Creates new form ConnectionInputDialog
     */
    private Client mainForm = null;

    public ConnectionInputDialog(java.awt.Frame parent, boolean modal, String stringIPA) {
        super(parent, modal);
        initComponents();
        if (parent != null) {
            this.mainForm = (Client) parent;
        }
        this.textIP.setText(stringIPA);
        this.setLocationRelativeTo(null);
        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/img/client.png")).getImage());
    }
    public ConnectionInputDialog() {
        super((Dialog)null);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/img/client.png")).getImage());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textIP = new javax.swing.JTextField();
        textPort = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnOK = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("K???t n???i m???i");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/inscription_480px.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Nh???p ?????a ch??? IP c???a Server");

        textIP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        textIP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textIPKeyPressed(evt);
            }
        });

        textPort.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        textPort.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textPortKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Nh???p c???ng k???t n???i c???a Server");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textPort)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textIP)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE))
                .addGap(26, 26, 26))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        btnOK.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnOK.setText("OK");
        btnOK.setFocusPainted(false);
        btnOK.setPreferredSize(new java.awt.Dimension(100, 35));
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        btnCancel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnCancel.setText("Cancel");
        btnCancel.setFocusPainted(false);
        btnCancel.setPreferredSize(new java.awt.Dimension(100, 35));
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
private void sendData(DatagramSocket clientSocket, InetAddress ip, String data, int port) throws IOException {
        System.out.println("send to IP: " + ip.toString());
        byte[] mang = data.getBytes();
        DatagramPacket output = new DatagramPacket(mang, mang.length, ip, port);
        clientSocket.send(output);
    }

    private String receiveData(DatagramSocket clientSocket) throws IOException, SocketTimeoutException {
        byte mangData[] = new byte[1024];
        DatagramPacket dataPacket = new DatagramPacket(mangData, mangData.length);
        clientSocket.setSoTimeout(4000);
        clientSocket.receive(dataPacket);
        String ketQua = new String(dataPacket.getData(), 0, dataPacket.getLength());
        return ketQua;
    }

    private void showErrorMessage(String title, String message) {
        JOptionPane.showMessageDialog(this,
                message,
                title,
                JOptionPane.ERROR_MESSAGE,
                new javax.swing.ImageIcon(getClass().getResource("/img/error.png")));
    }

    private void connect() {
        //check IP h???p l???
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        Pattern patternIP = Pattern.compile("^((25[0-5]|(2[0-4]|1[0-9]|[1-9]|)[0-9])(\\.(?!$)|$)){4}$");
        String stringIPA = textIP.getText().trim();
        boolean test1 = patternIP.matcher(stringIPA).matches() || stringIPA.equals("localhost");
        if (!test1) {
            showErrorMessage("?????a ch??? IP kh??ng h???p l???", "Vui l??ng nh???p ????ng ?????nh d???ng c???a ?????a ch??? IP");
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            return;
        }

        //check Port h???p l???
        try {
            int port = Integer.parseInt(this.textPort.getText());
            if (port >= 0 && port <= 65535) {
                System.out.println("port v???a nh???p: " + port);

                try (DatagramSocket clientSocket = new DatagramSocket()) {
                    InetAddress serverIP = InetAddress.getByName(stringIPA);
                    // truy???n t??n hi???u test k???t n???i
                    this.sendData(clientSocket, serverIP, "-1", port);
                    // xem nh???n ???????c t??n hi???u tr??? v??? kh??ng
                    String ketQua = this.receiveData(clientSocket);
                    if (!ketQua.equals("Success!")) {
                        throw new IOException();
                    }
                }
                this.dispose();
                if (this.isModal() && this.mainForm != null) {
                    this.mainForm.refreshConnection(stringIPA, port);
                } else {
                    new Client(textIP.getText(), port).setVisible(true);
                }

            } else {
                showErrorMessage("Port kh??ng h???p l???", "Port ph???i n???m trong kho???ng [0, 65535]");
            }
        } catch (NumberFormatException ex) {
            showErrorMessage("Port kh??ng h???p l???", "Vui l??ng nh???p Port l?? m???t s???");
        } catch (IOException ex) {
            showErrorMessage("L???i k???t n???i", "Kh??ng th??? k???t n???i ?????n server. ???? c?? th??? l?? do: \n - Th??ng tin ?????a ch??? IP ho???c (v??) port kh??ng ch??nh x??c.\n - Server ch??a ???????c kh???i ?????ng.");
        } finally {
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }
    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        this.connect();
    }//GEN-LAST:event_btnOKActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void textIPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textIPKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.connect();
        }
    }//GEN-LAST:event_textIPKeyPressed

    private void textPortKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textPortKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.connect();
        }
    }//GEN-LAST:event_textPortKeyPressed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConnectionInputDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConnectionInputDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConnectionInputDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConnectionInputDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ConnectionInputDialog dialog = new ConnectionInputDialog();
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField textIP;
    private javax.swing.JTextField textPort;
    // End of variables declaration//GEN-END:variables
}
