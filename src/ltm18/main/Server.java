/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ltm18.main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;
import ltm18.Caeser;
import ltm18.StringUtils;

/**
 *
 * @author MinhPhuc
 */
public class Server {

    public static int PORT = 8888;
    public static int test = 8888;
    private static DatagramPacket inPacket;
    private static DatagramPacket outPacket;

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

    public static void main(String args[]) throws SocketException, IOException {
        while (true) {
            try (DatagramSocket socketServer = new DatagramSocket(PORT)) {
                System.out.println(">> Server is running, port = " + PORT);

                int key = Integer.parseInt(receiveData(socketServer));
                System.out.println("key = " + key);
                if (key == -1) {//yêu cầu refresh port
                    PORT = Integer.parseInt(receiveData(socketServer));
                    System.out.println("reset, new port: " + PORT);
                    continue;
                }
                int soMau = Integer.parseInt(receiveData(socketServer));
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
            }
        }

    }
}
