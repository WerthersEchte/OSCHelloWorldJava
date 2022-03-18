package de.fhkiel.mediaip.example_with_library;

import com.illposed.osc.MessageSelector;
import com.illposed.osc.OSCMessageEvent;
import com.illposed.osc.OSCMessageListener;
import com.illposed.osc.messageselector.OSCPatternAddressMessageSelector;
import com.illposed.osc.transport.udp.OSCPortIn;

import java.io.IOException;

public class OSCReceiver {
    public static void main(String[] args) {
        try {
            // einen Port zum Lauschen auf OSC Nachrichten definieren
            // genutzte Bibliothek: https://github.com/hoijui/JavaOSC
            OSCPortIn in = new OSCPortIn(9001);

            // einen Listener erstellen, der die ankommenden Nachrichten verarbeitet
            OSCMessageListener listener = new OSCMessageListener() {
                public void acceptMessage(OSCMessageEvent event) {

                    System.out.println("Message received: " + event.getMessage().getAddress());

                    for (Object o : event.getMessage().getArguments()) {
                        System.out.println(o);
                    }
                }
            };
            // das gewünschte Addressierungsmuster definieren, in diesem Fall '/hello/world'
            MessageSelector selector = new OSCPatternAddressMessageSelector("/hello/world");

            // Addressierungsmuster und Listener setzen
            in.getDispatcher().addListener(selector, listener);

            // auf Nachrichten warten
            while (true) { // im Beispiel Endlosschleife, normal eine Abbruchbedingung!
                in.startListening();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
