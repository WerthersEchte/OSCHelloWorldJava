package de.fhkiel.seg.example_without_library;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import static de.fhkiel.seg.example_without_library.osc.Decoder.fromOSC;

public class OSCReceiver {
    public static void main(String[] args) {

        // einen Socket an dem gewüschtem Port öffnen
        try (DatagramSocket socket = new DatagramSocket(9001)) {
            // den Puffer für die erhaltenden Daten vorbereiten
            byte[] buf = new byte[65515];

            // auf die Daten lauschen
            while (true) {
                // das erwartete Paket erstellen
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                // die daten erhalten
                socket.receive(packet);

                // die addresse des senders auslesen
                InetAddress address = packet.getAddress();
                // die port des senders auslesen
                int port = packet.getPort();
                // den Payload des senders auslesen
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println(address + " " + port + ": \n" + received);

                // den Payload verarbeiten
                System.out.println(fromOSC(received));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
