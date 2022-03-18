package de.fhkiel.seg.example_with_library;

import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCSerializeException;
import com.illposed.osc.transport.udp.OSCPortOut;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class OSCSender {
    public static void main(String[] args) {

        try {
            // einen Port an eine  Addresse an die OSC Nachrichten gesendet werden sollen definieren, in diesem Fall der eigene Rechner('localhost')
            // genutzte Bibliothek: https://github.com/hoijui/JavaOSC
            OSCPortOut sender = new OSCPortOut(InetAddress.getByName("localhost"), 9001);

            // eine Liste von Daten, die gesendet werden sollen definieren, in diesem Fall ein Int, Float, Double und String
            List<Object> data = new ArrayList<Object>();
            data.add(100);
            data.add(1.1f);
            data.add(1.1);
            data.add("Hello");
            data.add(1.1);

            // die zu sendende Nachricht mit dem Pfad und den Daten erstellen
            OSCMessage test = new OSCMessage("/hello/world", data);

            // die Nachricht senden
            sender.send(test);
        } catch (IOException | OSCSerializeException e) {
            e.printStackTrace();
        }
    }
}
