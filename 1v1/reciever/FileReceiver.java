import java.io.*;
import java.net.*;

public class FileReceiver {

    public static void main(String[] args) throws IOException {
     

        

        int port = Integer.parseInt(args[0]);
        String filename = args[1];

        DatagramSocket socket = new DatagramSocket(port);

        FileOutputStream fileOutputStream = new FileOutputStream(filename);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

        int packetSize = 1024;
        byte[] packetData = new byte[packetSize];
        DatagramPacket packet = new DatagramPacket(packetData, packetSize);

        while (true) {
            socket.receive(packet);
            String message = new String(packet.getData(), 0, packet.getLength());
            if (message.startsWith("fichier terminer.")) {
                System.out.println(message);
                break;
            } else {
                bufferedOutputStream.write(packet.getData(), 0, packet.getLength());
            }
        }

        bufferedOutputStream.close();
        fileOutputStream.close();
        socket.close();
    }
}
