import java.io.*;
import java.net.*;

public class FileSender {

    public static void main(String[] args) throws IOException {
        
        

        String hostname = args[0];
        int port = Integer.parseInt(args[1]);
        String filename = args[2];

        InetAddress address = InetAddress.getByName(hostname);
        DatagramSocket socket = new DatagramSocket();

        File file = new File(filename);
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

        int packetSize = 1024;
        byte[] packetData = new byte[packetSize];

        int bytesRead = 0;
        int packetNumber = 0;

        while ((bytesRead = bufferedInputStream.read(packetData)) != -1) {
            DatagramPacket packet = new DatagramPacket(packetData, bytesRead, address, port);
            socket.send(packet);
            packetNumber++;
        }

        byte[] endData = String.format("fichier terminer.  %d packets envoyeé.", packetNumber).getBytes();
        DatagramPacket endPacket = new DatagramPacket(endData, endData.length, address, port);
        socket.send(endPacket);

        bufferedInputStream.close();
        fileInputStream.close();
        socket.close();
    }
}
