/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ltm18.main;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.font.TextAttribute;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import ltm18.CaeserCipher;
import ltm18.ConnectionInputDialog;
import ltm18.StringUtils;

/**
 *
 * @author MinhPhuc
 */
public class Client extends javax.swing.JFrame {

    private static final int BORDER_GAP = 1;
    private static final int NUM_ROWS = 2;
    private static final Font HEADER_FONT = new Font(Font.DIALOG, Font.PLAIN, 16);
    private static final Font CONTENT_FONT = new Font(Font.DIALOG, Font.PLAIN, 14);
    private static final Color VERY_LIGHT_YELLOW = new Color(255, 255, 204);
    private static final Border LABEL_BORDER = BorderFactory.createLineBorder(Color.darkGray, BORDER_GAP);

    public String stringIPA;
    public int serverPort;

    public Client(String stringIPA, int serverPort) {
        initComponents();
        this.setLocationRelativeTo(null);
        areaMaHoa.requestFocus();
        setHyperLinkFormat(lblXemDe);
        setHyperLinkFormat(lbHyper);
        setHyperLinkFormat(lblDoi);
        lblThongBao.setText("  ");
        this.getContentPane().setBackground(new Color(245, 245, 245));

        this.stringIPA = stringIPA;
        this.serverPort = serverPort;

        this.lblIP.setText(stringIPA);
        this.lblPort.setText(String.valueOf(this.serverPort));

        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/img/client.png")).getImage());
    }

    public void refreshConnection(String stringIPA, int serverPort) {
        this.stringIPA = stringIPA;
        this.serverPort = serverPort;

        this.lblIP.setText(stringIPA);
        this.lblPort.setText(String.valueOf(this.serverPort));
    }

    private static final String DE_BAI = "X??y ch????ng tr??nh giao di???n socket client ??? server b???ng java v???i giao th???c UDP m?? h??a v?? gi???i m?? v??n b???n v???i thu???t to??n m?? h??a Ceasar. "
            + "\nCh????ng tr??nh c?? th??? th???c hi???n c??c ch???c n??ng sau: \n"
            + " 	Client: \n"
            + "          - Cho ph??p nh???p v??n b???n v?? kh??a ????? m?? h??a v??n b???n tr?????c khi g???i l??n server.\n"
            + "          - Trao ?????i kh??a v???i server\n"
            + "          - Nh???n k???t qu??? tr??? v??? t??? server.\n"
            + " 	Server:\n"
            + "          - Nh???n b???n m?? v?? kh??a sau ???? gi???i m?? b???n m?? ????? t??m b???n r??.\n"
            + "          - Sau khi gi???i m?? xong th?? ?????m s??? l?????ng xu???t hi???n c???a c??c ch??? c??i trong b???n r??.\n"
            + "          - Tr??? v??? s??? l?????ng xu???t hi???n c???a c??c ch??? c??i cho client";

    private static final String QUY_TAC_MA_HOA = "-Kho?? t??? 0 ?????n 25."
            + "\n- N???u k?? t??? c?? d???u ti???ng Vi???t th?? s??? lo???i b??? d???u tr?????c khi m?? h??a."
            + "\n- Ch??? m?? h??a k?? t??? ch??? alphabet, c??c k?? t??? c??n l???i (tr??? k?? t??? d???u ti???ng Vi???t) s??? b??? qua kh??ng m?? h??a.";
    private static final Color HOVER_COLOR = new Color(0, 82, 157);
    private static final Color IDLE_COLOR = new Color(0, 109, 208);
    private static final Color ERROR_COLOR = new Color(236, 65, 52);
    private static final Color SUCCESS_COLOR = new Color(45, 198, 109);
    private static final Color PROCESSING_COLOR = new Color(78, 153, 255);

    private void setHyperLinkFormat(JLabel label) {
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Font font = label.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        label.setFont(font.deriveFont(attributes));
    }

    private enum Message {
        SERVER_ERROR, SUCCESS, PROCESSING, COPIED_1, FILE_ERROR, COPIED_2
    }

    private enum DialogMessage {
        INFO, ERROR
    }

    private void showMessage(DialogMessage type, String message, String title) {
        switch (type) {
            case INFO:
                JOptionPane.showMessageDialog(this,
                        message,
                        title,
                        JOptionPane.INFORMATION_MESSAGE,
                        new javax.swing.ImageIcon(getClass().getResource("/img/infor.png")));
                break;
            case ERROR:
                JOptionPane.showMessageDialog(this,
                        message,
                        title,
                        JOptionPane.ERROR_MESSAGE,
                        new javax.swing.ImageIcon(getClass().getResource("/img/error.png")));
                break;
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

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnGiai = new javax.swing.JButton();
        lbHyper = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        spinKey = new javax.swing.JSpinner();
        btnChonFile = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        areaVanBan = new javax.swing.JTextArea();
        btnCopy1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        panelLower = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnLamMoiKetQua = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaMaHoa = new javax.swing.JTextArea();
        panelUpper = new javax.swing.JPanel();
        btnCopy2 = new javax.swing.JButton();
        lblThongBao = new javax.swing.JLabel();
        lblXemDe = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lblIP = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblPort = new javax.swing.JLabel();
        lblDoi = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Client");
        setMinimumSize(new java.awt.Dimension(888, 980));
        setSize(new java.awt.Dimension(888, 980));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(" ????? T??I 18 - M?? H??A CAESAR V?? ?????M CH??? C??I");
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "?????u v??o", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 2, 15), new java.awt.Color(190, 190, 190))); // NOI18N

        btnGiai.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnGiai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/problem-solving.png"))); // NOI18N
        btnGiai.setText("Gi???i");
        btnGiai.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnGiai.setFocusPainted(false);
        btnGiai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGiaiActionPerformed(evt);
            }
        });

        lbHyper.setFont(new java.awt.Font("Segoe UI", 2, 15)); // NOI18N
        lbHyper.setForeground(new java.awt.Color(0, 109, 208));
        lbHyper.setText("Quy t???c m?? ho???");
        lbHyper.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbHyperMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbHyperMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbHyperMouseExited(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(70, 70, 70));
        jLabel2.setText("Nh???p kho??:");

        spinKey.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        spinKey.setModel(new javax.swing.SpinnerNumberModel(0, 0, 25, 1));
        spinKey.setToolTipText("Kho?? t??? 0 t???i 25");

        btnChonFile.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnChonFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/folder.png"))); // NOI18N
        btnChonFile.setText("Ch???n t???p v??n b???n");
        btnChonFile.setFocusPainted(false);
        btnChonFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonFileActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(70, 70, 70));
        jLabel6.setText("Nh???p v??n b???n");

        areaVanBan.setColumns(20);
        areaVanBan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        areaVanBan.setLineWrap(true);
        areaVanBan.setRows(5);
        areaVanBan.setWrapStyleWord(true);
        jScrollPane3.setViewportView(areaVanBan);

        btnCopy1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnCopy1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/copy.png"))); // NOI18N
        btnCopy1.setText("Copy");
        btnCopy1.setFocusPainted(false);
        btnCopy1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCopy1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbHyper, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGiai, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCopy1)
                                .addGap(18, 18, 18)
                                .addComponent(btnChonFile))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE))))
                .addGap(43, 43, 43))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnChonFile, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCopy1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGiai, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbHyper)
                    .addComponent(spinKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(8, 8, 8))
        );

        jPanel2.setBackground(new java.awt.Color(245, 245, 245));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(180, 180, 180)), "?????u ra", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 2, 15), new java.awt.Color(190, 190, 190))); // NOI18N

        panelLower.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(130, 135, 144)));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(70, 70, 70));
        jLabel4.setText("K???t qu??? m?? ho?? tr?????c khi g???i ??i");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(70, 70, 70));
        jLabel3.setText("S??? l?????ng ch??? th?????ng");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(70, 70, 70));
        jLabel5.setText("S??? l?????ng ch??? hoa");

        btnLamMoiKetQua.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnLamMoiKetQua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/clear.png"))); // NOI18N
        btnLamMoiKetQua.setText("X??a k???t qu???");
        btnLamMoiKetQua.setFocusPainted(false);
        btnLamMoiKetQua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiKetQuaActionPerformed(evt);
            }
        });

        areaMaHoa.setEditable(false);
        areaMaHoa.setColumns(20);
        areaMaHoa.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        areaMaHoa.setLineWrap(true);
        areaMaHoa.setRows(5);
        areaMaHoa.setWrapStyleWord(true);
        jScrollPane2.setViewportView(areaMaHoa);

        panelUpper.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(130, 135, 144)));

        btnCopy2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnCopy2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/copy.png"))); // NOI18N
        btnCopy2.setText("Copy");
        btnCopy2.setFocusPainted(false);
        btnCopy2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCopy2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLamMoiKetQua, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCopy2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(panelUpper, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(panelLower, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE))))
                .addGap(47, 47, 47))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCopy2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelUpper, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelLower, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLamMoiKetQua, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        lblThongBao.setFont(new java.awt.Font("Segoe UI", 2, 15)); // NOI18N
        lblThongBao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThongBao.setText("X???y ra l???i! Server ch??a ???????c kh???i ?????ng, sai th??ng tin k???t n???i (IP, Port) ho???c l???i kh??ng x??c ?????nh");

        lblXemDe.setFont(new java.awt.Font("Segoe UI", 2, 17)); // NOI18N
        lblXemDe.setForeground(new java.awt.Color(0, 109, 208));
        lblXemDe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblXemDe.setText("Xem chi ti???t ?????");
        lblXemDe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblXemDeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblXemDeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblXemDeMouseExited(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(170, 170, 170));
        jPanel3.setForeground(new java.awt.Color(227, 227, 227));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 5));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("?????a ch??? IP Server:");
        jPanel3.add(jLabel7);

        lblIP.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblIP.setForeground(new java.awt.Color(255, 255, 255));
        lblIP.setText("192.168.1.1");
        jPanel3.add(lblIP);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("|  C???ng k???t n???i:");
        jPanel3.add(jLabel8);

        lblPort.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblPort.setForeground(new java.awt.Color(255, 255, 255));
        lblPort.setText("8080");
        jPanel3.add(lblPort);

        lblDoi.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblDoi.setForeground(new java.awt.Color(0, 109, 208));
        lblDoi.setText("?????i k???t n???i");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblThongBao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblXemDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(4, 4, 4)))
                .addGap(45, 45, 45))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblXemDe)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblThongBao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setLblThongBaoState(Message state) {

        switch (state) {
            case SERVER_ERROR:
                lblThongBao.setText("X???y ra l???i! Server ch??a ???????c kh???i ?????ng, sai th??ng tin k???t n???i (IP, Port) ho???c l???i kh??ng x??c ?????nh");
                lblThongBao.setForeground(ERROR_COLOR);
                break;
            case FILE_ERROR:
                lblThongBao.setText("Hi???n t???i kh??ng th??? m??? file n??y, vui l??ng th??? l???i sau");
                lblThongBao.setForeground(ERROR_COLOR);
                break;
            case PROCESSING:
                lblThongBao.setText("??ang ch??? k???t qu??? t??? server...");
                lblThongBao.setForeground(PROCESSING_COLOR);
                break;
            case SUCCESS:
                lblThongBao.setText("Nh???n k???t qu??? t??? server th??nh c??ng!");
                lblThongBao.setForeground(SUCCESS_COLOR);
                break;
            case COPIED_1:
                lblThongBao.setText("???? sao ch??p to??n b??? v??n b???n m?? h??a v??o b??? nh??? trong.");
                lblThongBao.setForeground(SUCCESS_COLOR);
                break;
            case COPIED_2:
                lblThongBao.setText("???? sao ch??p to??n b??? v??n b???n r?? v??o b??? nh??? trong.");
                lblThongBao.setForeground(SUCCESS_COLOR);
                break;
        }

    }

    private void sendData(DatagramSocket clientSocket, InetAddress ip, String data) throws IOException {
        System.out.println("send to IP: " + ip.toString());
        byte[] mang = data.getBytes();
        DatagramPacket output = new DatagramPacket(mang, mang.length, ip, serverPort);
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

    private void sendLargeData(DatagramSocket clientSocket, InetAddress ip, String largeData) throws IOException {
        // chia d??? li???u th??nh t???ng nh??m data nh??? ????? g???i ??i.
        byte[] bytes = largeData.getBytes();
        int soMau = (int) Math.ceil((double) bytes.length / 1024.0);

        sendData(clientSocket, ip, String.valueOf(soMau));

        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        int n = 0;
        byte[] buffer = new byte[1024];
        StringBuilder smallData = new StringBuilder();
        while ((n = bis.read(buffer)) > 0) {
            for (byte b : buffer) {
                if (b != 0) {
                    smallData.append((char) b);
                }
            }
            Arrays.fill(buffer, (byte) 0);
            sendData(clientSocket, ip, smallData.toString());
        }
    }

    private static int[] convertStringToIntArray(String string) {
        String[] strArray = string.split(" ");
        int[] intArray = new int[StringUtils.NUMBER_CHAR];
        Arrays.fill(intArray, 0);
        for (int i = 0; i < strArray.length; i++) {
            intArray[i] = Integer.parseInt(strArray[i]);
        }
        return intArray;
    }

    private static int countNonZero(int[] array) {
        int count = 0;
        for (int number : array) {
            if (number != 0) {
                count++;
            }
        }
        return count;
    }

    private void createResult(int[] countArr, boolean isUpperCase) {
        int letterACode;
        JPanel panel;
        if (isUpperCase) {
            letterACode = StringUtils.UPPER_A_CODE;
            panel = panelUpper;
        } else {
            letterACode = StringUtils.LOWER_A_CODE;
            panel = panelLower;
        }

        panel.removeAll();
        int numNoneZero = countNonZero(countArr);
        GridLayout gridLayout = new GridLayout(NUM_ROWS, numNoneZero, BORDER_GAP, BORDER_GAP);
        panel.setLayout(gridLayout);

        JLabel[][] gridLabel = new JLabel[NUM_ROWS][numNoneZero];

        for (int row = 0; row < NUM_ROWS; row++) {
            int col = 0;
            for (int i = 0; i < countArr.length; i++) {
                if (countArr[i] == 0) {
                    continue;
                }
                JLabel label = new JLabel();
                label.setHorizontalAlignment(SwingConstants.CENTER);
                if (row == 0) {
                    // t???o label ch???a ch??? c??i
                    String letter = String.valueOf((char) (letterACode + i));
                    label.setText(letter);
                    label.setFont(HEADER_FONT);
                    label.setOpaque(true);
                    label.setBackground(VERY_LIGHT_YELLOW);
                } else {
                    // t???o label ch???a s??? l???n xu???t hi???n
                    label.setText(String.valueOf(countArr[i]));
                    label.setFont(CONTENT_FONT);
                }
                label.setBorder(LABEL_BORDER);
                gridLabel[row][col++] = label;
                panel.add(label);
            }
        }
        panel.revalidate();
        panel.repaint();
    }


    private void btnGiaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGiaiActionPerformed
        try {
            this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            String vanBan = areaVanBan.getText().trim();
            if (vanBan.isEmpty()) {
                this.showMessage(DialogMessage.INFO, "Vui l??ng nh???p v??n b???n", "Th??ng b??o");
                return;
            }
            Integer key = (Integer) spinKey.getValue();

            //Kh??ng ch???y ???????c, do kh??ng th??? repaint ngay sau khi set text
            setLblThongBaoState(Message.PROCESSING);

            try (DatagramSocket clientSocket = new DatagramSocket()) {
                InetAddress serverIP = InetAddress.getByName(stringIPA);
                // chuy???n kho?? ??i tr?????c
                sendData(clientSocket, serverIP, key.toString());
                // chuy???n ti???ng vi???t (n???u c??) th??nh kh??ng d???u
                vanBan = StringUtils.removeAccent(vanBan);
                String cipher = CaeserCipher.encrypt(vanBan, key);
                areaMaHoa.setText(cipher);
                // chuy???n d??? li???u ??i
                sendLargeData(clientSocket, serverIP, cipher);
                // nh???n k???t qu??? 
                int[] countLower = convertStringToIntArray(receiveData(clientSocket));
                createResult(countLower, false);
                //    System.out.println(Arrays.toString(countLower));
                int[] countUpper = convertStringToIntArray(receiveData(clientSocket));
                createResult(countUpper, true);

                setLblThongBaoState(Message.SUCCESS);
            }

        } catch (SocketTimeoutException ex) {
            setLblThongBaoState(Message.SERVER_ERROR);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_btnGiaiActionPerformed

    private void btnChonFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonFileActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Documents (*.dat, *.in, *.txt)", "dat", "in", "txt"));
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File selectedFile = fileChooser.getSelectedFile();
                String path = selectedFile.getAbsolutePath();
                byte[] allBytes = Files.readAllBytes(Paths.get(path));
                String allText = new String(allBytes, StandardCharsets.UTF_8);
                areaVanBan.setText(allText);
            } catch (IOException ex) {
                this.setLblThongBaoState(Message.FILE_ERROR);

                this.showMessage(DialogMessage.ERROR, ex.getMessage(), "L???i ?????c file");
            }
        }
    }//GEN-LAST:event_btnChonFileActionPerformed

    private void btnLamMoiKetQuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiKetQuaActionPerformed
        panelLower.removeAll();
        panelLower.revalidate();
        panelLower.repaint();

        panelUpper.removeAll();
        panelUpper.revalidate();
        panelUpper.repaint();

        areaMaHoa.setText("");
        lblThongBao.setText("  ");
    }//GEN-LAST:event_btnLamMoiKetQuaActionPerformed


    private void lbHyperMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbHyperMouseEntered
        lbHyper.setForeground(HOVER_COLOR);
    }//GEN-LAST:event_lbHyperMouseEntered

    private void lbHyperMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbHyperMouseExited
        lbHyper.setForeground(IDLE_COLOR);
    }//GEN-LAST:event_lbHyperMouseExited

    private void lbHyperMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbHyperMouseClicked
        this.showMessage(DialogMessage.INFO, QUY_TAC_MA_HOA, "Quy t???c m?? h??a");
    }//GEN-LAST:event_lbHyperMouseClicked

    private void lblXemDeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblXemDeMouseClicked

        this.showMessage(DialogMessage.INFO, DE_BAI, "????? 18");
    }//GEN-LAST:event_lblXemDeMouseClicked

    private void lblXemDeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblXemDeMouseEntered
        lblXemDe.setForeground(HOVER_COLOR);
    }//GEN-LAST:event_lblXemDeMouseEntered

    private void lblXemDeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblXemDeMouseExited
        lblXemDe.setForeground(IDLE_COLOR);
    }//GEN-LAST:event_lblXemDeMouseExited

    private void btnCopy2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCopy2ActionPerformed
        Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(
                        new StringSelection(areaMaHoa.getText()),
                        null
                );
        setLblThongBaoState(Message.COPIED_1);
    }//GEN-LAST:event_btnCopy2ActionPerformed

    private void lblDoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDoiMouseClicked
        new ConnectionInputDialog(this, true, this.stringIPA).setVisible(true);
    }//GEN-LAST:event_lblDoiMouseClicked

    private void lblDoiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDoiMouseEntered
        lblDoi.setForeground(HOVER_COLOR);
    }//GEN-LAST:event_lblDoiMouseEntered

    private void lblDoiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDoiMouseExited
        lblDoi.setForeground(IDLE_COLOR);
    }//GEN-LAST:event_lblDoiMouseExited

    private void btnCopy1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCopy1ActionPerformed
        Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(
                        new StringSelection(areaVanBan.getText()),
                        null
                );
        setLblThongBaoState(Message.COPIED_1);
    }//GEN-LAST:event_btnCopy1ActionPerformed

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
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConnectionInputDialog().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaMaHoa;
    private javax.swing.JTextArea areaVanBan;
    private javax.swing.JButton btnChonFile;
    private javax.swing.JButton btnCopy1;
    private javax.swing.JButton btnCopy2;
    private javax.swing.JButton btnGiai;
    private javax.swing.JButton btnLamMoiKetQua;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbHyper;
    private javax.swing.JLabel lblDoi;
    public javax.swing.JLabel lblIP;
    public javax.swing.JLabel lblPort;
    private javax.swing.JLabel lblThongBao;
    private javax.swing.JLabel lblXemDe;
    private javax.swing.JPanel panelLower;
    private javax.swing.JPanel panelUpper;
    private javax.swing.JSpinner spinKey;
    // End of variables declaration//GEN-END:variables
}
