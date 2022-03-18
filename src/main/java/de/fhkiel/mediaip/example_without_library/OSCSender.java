package de.fhkiel.mediaip.example_without_library;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import static de.fhkiel.mediaip.example_without_library.osc.Encoder.toOSC;

public class OSCSender {
    public static void main(String[] args) {

        // einen DatagrammSocket(UDP) erstellen
        try (DatagramSocket socket = new DatagramSocket()) {

            // den OSC Payload erstellen
            String toSend = toOSC("/hello/world", 100, 7f, "Hello", "World", 15.0);
            System.out.println(toSend.length() + ": " + toSend);

            // ein Packet mit dem Payload, der Adresse und Port erstellen
            DatagramPacket packet = new DatagramPacket(toSend.getBytes(), toSend.length(), InetAddress.getByName("149.222.206.225"),9000);

            // das Packet senden
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
