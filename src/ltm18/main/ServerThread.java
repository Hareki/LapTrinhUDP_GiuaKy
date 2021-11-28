/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ltm18.main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import javax.swing.JTextArea;
import ltm18.Caeser;
import ltm18.StringUtils;

/**
 *
 * @author MinhPhuc
 */
public class ServerThread extends Thread {

    public static int PORT = 8888;
    private static DatagramPacket inPacket;
    private static DatagramPacket outPacket;
    private static JTextArea LOG_TEXT;
    private static boolean listening = true;
    private static DatagramSocket mainSocket;
    DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("dd/MM/yyyy HH:mm:ss");

    public ServerThread(JTextArea logText) {
        super();
        ServerThread.LOG_TEXT = logText;
        System.out.println("Đã khởi tạo");
    }

    private static String receiveData(DatagramSocket socketServer) throws IOException {
        byte[] receive = new byte[1024];
        inPacket = new DatagramPacket(receive, receive.length);
        socketServer.receive(inPacket);
        String ketQua = new String(inPacket.getData(), 0, inPacket.getLength()).trim();
        return ketQua;
    }

    private static void sendData(DatagramSocket socketServer, String data) throws IOException {
        byte[] outData = data.getBytes();
        outPacket = new DatagramPacket(outData, outData.length);
        outPacket.setAddress(inPacket.getAddress());
        outPacket.setPort(inPacket.getPort());
        socketServer.send(outPacket);
    }

    private static final int[] COUNT_UPPER = new int[StringUtils.NUMBER_CHAR];
    private static final int[] COUNT_LOWER = new int[StringUtils.NUMBER_CHAR];

    private static void countLetters(String vanBan) {
        Arrays.fill(COUNT_UPPER, 0);
        Arrays.fill(COUNT_LOWER, 0);
        int length = vanBan.length();
        for (int i = 0; i < length; i++) {
            char cha = vanBan.charAt(i);
            if (Character.isLetter(cha)) {
                if (Character.isUpperCase(cha)) {
                    COUNT_UPPER[(int) cha - StringUtils.UPPER_A_CODE] += 1;
                } else {
                    COUNT_LOWER[(int) cha - StringUtils.LOWER_A_CODE] += 1;
                }
            }
        }
    }

    private static String arrayToString(int[] array) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int number : array) {
            stringBuilder.append(String.valueOf(number)).append(' ');
        }
        return stringBuilder.toString().trim();
    }

    @Override
    public void interrupt() {
        listening = false;
        mainSocket.close();
    }

    @Override
    public void start() {
        super.start();
        listening = true;
    }

    @Override
    public void run() {
        LOG_TEXT.append(">> Server đã khởi động, port = " + PORT);
        while (listening) {
            try {
                try (DatagramSocket socketServer = new DatagramSocket(PORT)) {
                    mainSocket = socketServer;
                    int key = Integer.parseInt(receiveData(socketServer));
                    LOG_TEXT.append("\n----------------");
                    LOG_TEXT.append("\nĐang chờ nhận thông tin từ client...");
                    LOG_TEXT.append("\n----------------");
                    LocalDateTime localDateTime = LocalDateTime.now();
                    LOG_TEXT.append("\n" + localDateTime.format(formatter));
                    LOG_TEXT.append("\nThông tin client: ");
                    LOG_TEXT.append("\n  - IP: " + inPacket.getAddress().toString());
                    LOG_TEXT.append("\n  - Port: " + inPacket.getPort());
                    if (key == -1) {//test kết nối
                        LOG_TEXT.append("\nĐã nhận được tín hiểu kiểm tra thử kết nối");
                        LOG_TEXT.append("\nKết thúc");
                        sendData(socketServer, "Success!");
                        continue;
                    }
                    LOG_TEXT.append("\nKhóa nhận được: " + key);
                    int soMau = Integer.parseInt(receiveData(socketServer));
                    LOG_TEXT.append("\nSố mẫu: " + soMau);
                    System.out.println(soMau);
                    StringBuilder matMa = new StringBuilder();
                    for (int i = 0; i < soMau; i++) {
                        matMa.append(receiveData(socketServer));
                    }

                    String vanBan = Caeser.decrypt(matMa.toString(), key);

                    countLetters(vanBan);
                    String countLower = arrayToString(COUNT_LOWER);
                    String countUpper = arrayToString(COUNT_UPPER);

                    sendData(socketServer, countLower);
                    sendData(socketServer, countUpper);
                    LOG_TEXT.append("\nĐã gửi trả kết quả đếm chữ cái");
                    LOG_TEXT.append("\nKết thúc");
                }
            } catch (Exception ex) {

            }

        }
        System.out.println("đã ngắt socket");
    }

}
