/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltm18.main;

import ltm18.ServerThread;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.font.TextAttribute;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import ltm18.PortInputDialog;

/**
 *
 * @author Minh Tu
 */
public class Server extends javax.swing.JFrame {

    /**
     * Creates new form Server
     */
    public Server() {
        initComponents();
        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/img/server.png")).getImage());
        mode = ButtonMode.STOP;
        this.setLocationRelativeTo(null);
        setHyperLinkFormat(lblDoi);
        try {
            this.lblIP.setText(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

    private static final Color HOVER_COLOR = new Color(0, 82, 157);
    private static final Color IDLE_COLOR = new Color(0, 109, 208);
    private static final Color ERROR_COLOR = new Color(236, 65, 52);
    private static final Color SUCCESS_COLOR = new Color(45, 198, 109);
    private static final Color PROCESSING_COLOR = new Color(78, 153, 255);
    private static int serverThreadPort = 8888;

    public static int getServerThreadPort() {
        return serverThreadPort;
    }

    public static void setServerThreadPort(int serverThreadPort) {
        Server.serverThreadPort = serverThreadPort;
    }

    private void setHyperLinkFormat(JLabel label) {
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Font font = label.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        label.setFont(font.deriveFont(attributes));
    }

    enum ButtonMode {
        START, STOP
    }
    private ButtonMode mode;
    private ServerThread server;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        areaLogText = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnPower = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lblIP = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblPort = new javax.swing.JLabel();
        lblDoi = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        btnCopy = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server");

        areaLogText.setEditable(false);
        areaLogText.setColumns(20);
        areaLogText.setFont(new java.awt.Font("Consolas", 0, 16)); // NOI18N
        areaLogText.setRows(5);
        jScrollPane1.setViewportView(areaLogText);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jLabel1.setText("Nhật ký phiên làm việc");

        btnPower.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnPower.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/power.png"))); // NOI18N
        btnPower.setText("Khởi động");
        btnPower.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnPower.setFocusPainted(false);
        btnPower.setPreferredSize(new java.awt.Dimension(130, 40));
        btnPower.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPowerActionPerformed(evt);
            }
        });
        jPanel1.add(btnPower);

        jPanel3.setBackground(new java.awt.Color(170, 170, 170));
        jPanel3.setForeground(new java.awt.Color(227, 227, 227));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 5));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Địa chỉ IP Server:");
        jPanel3.add(jLabel7);

        lblIP.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblIP.setForeground(new java.awt.Color(255, 255, 255));
        lblIP.setText("127.0.0.1");
        jPanel3.add(lblIP);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("|  Cổng kết nối:");
        jPanel3.add(jLabel8);

        lblPort.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblPort.setForeground(new java.awt.Color(255, 255, 255));
        lblPort.setText("8888");
        jPanel3.add(lblPort);

        lblDoi.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblDoi.setForeground(new java.awt.Color(0, 109, 208));
        lblDoi.setText("Đổi cổng");
        lblDoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDoiMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblDoiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblDoiMouseExited(evt);
            }
        });
        jPanel3.add(lblDoi);

        lblStatus.setFont(new java.awt.Font("Segoe UI", 2, 15)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(236, 65, 52));
        lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStatus.setText("Server chưa hoạt động");
        lblStatus.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        btnCopy.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/copy.png"))); // NOI18N
        btnCopy.setText("Copy");
        btnCopy.setFocusPainted(false);
        btnCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCopyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCopy, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(49, 49, 49))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnCopy, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private boolean showConfirmMessage(String message, String title) {
        return (JOptionPane.showConfirmDialog(this,
                message,
                title,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                new javax.swing.ImageIcon(getClass().getResource("/img/infor.png"))) == JOptionPane.YES_OPTION);
    }

    public void refreshServer() {
        if (mode == ButtonMode.START) {
            if (showConfirmMessage("Server đang hoạt động, cần khởi động lại để chuyển sang port mới.\nKhởi động lại"
                    + " ngay bây giờ?", "Xác nhận")) {
                server.interrupt();
                areaLogText.setText(null);
                server = new ServerThread(areaLogText, serverThreadPort);
                this.lblPort.setText(String.valueOf(serverThreadPort));
                server.start();
            }
        }else{
            this.lblPort.setText(String.valueOf(serverThreadPort));
        }
        
    }
    private void btnPowerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPowerActionPerformed
        if (mode == ButtonMode.START) {
            mode = ButtonMode.STOP;
            btnPower.setText("Khởi động");
            lblStatus.setText("Server chưa hoạt động");
            server.interrupt();
            areaLogText.setText(null);
            lblStatus.setForeground(ERROR_COLOR);
        } else if (mode == ButtonMode.STOP) {
            mode = ButtonMode.START;
            btnPower.setText("Ngừng");
            lblStatus.setText("Server đang hoạt động");
            lblStatus.setForeground(SUCCESS_COLOR);
            server = new ServerThread(areaLogText, serverThreadPort);
            server.start(); 
        }
        this.lblPort.setText(String.valueOf(serverThreadPort));
    }//GEN-LAST:event_btnPowerActionPerformed

    private void lblDoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDoiMouseClicked
        new PortInputDialog(this, true).setVisible(true);
    }//GEN-LAST:event_lblDoiMouseClicked

    private void lblDoiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDoiMouseEntered
        lblDoi.setForeground(HOVER_COLOR);
    }//GEN-LAST:event_lblDoiMouseEntered

    private void lblDoiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDoiMouseExited
        lblDoi.setForeground(IDLE_COLOR);
    }//GEN-LAST:event_lblDoiMouseExited

    private void btnCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCopyActionPerformed
        Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(
                        new StringSelection(areaLogText.getText()),
                        null
                );
    }//GEN-LAST:event_btnCopyActionPerformed

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
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Server().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaLogText;
    private javax.swing.JButton btnCopy;
    private javax.swing.JButton btnPower;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDoi;
    public javax.swing.JLabel lblIP;
    public javax.swing.JLabel lblPort;
    private javax.swing.JLabel lblStatus;
    // End of variables declaration//GEN-END:variables
}
