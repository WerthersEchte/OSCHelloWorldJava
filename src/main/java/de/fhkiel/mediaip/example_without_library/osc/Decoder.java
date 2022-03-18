package de.fhkiel.mediaip.example_without_library.osc;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class Decoder {
    private Decoder() {
    }


    public static Message fromOSC(String payload) {
        String[] parts = payload.split(",");

        String path = stringFromOSC(parts[0]);
        List<Object> data = new ArrayList<>();

        if (parts.length > 1) {
            int positionInTypes = 0;
            byte[] dataTypes = parts[1].getBytes();

            int positionInPayload = parts[0].length() + lengthOfString(payload.substring(parts[0].length()));
            while (dataTypes[positionInTypes] != '\0') {
                switch (dataTypes[positionInTypes]) {
                    case 's':
                    {
                        int length = lengthOfString(payload.substring(positionInPayload));
                        data.add(stringFromOSC(payload.substring(positionInPayload, positionInPayload + length)));
                        positionInPayload += length;
                    }
                    break;
                    case 'i':
                    {
                        int length = 4;
                        data.add(intFromOSC(payload.substring(positionInPayload, positionInPayload + length)));
                        positionInPayload += length;
                    }
                        break;
                    case 'f':
                    {
                        int length = 4;
                        data.add(floatFromOSC(payload.substring(positionInPayload, positionInPayload + length)));
                        positionInPayload += length;
                    }
                        break;
                    case 'd':
                    {
                        int length = 8;
                        data.add(doubleFromOSC(payload.substring(positionInPayload, positionInPayload + length)));
                        positionInPayload += length;
                    }
                        break;
                }
                positionInTypes += 1;
            }
        }

        return new Message(path, data);
    }

    private static int lengthOfString(String substring) {
        int length = substring.indexOf('\0') + 1;
        while (length % 4 != 0) {
            length += 1;
        }
        return length;
    }

    public static String stringFromOSC(String data) {
        return data.replace('\0', ' ').trim();
    }

    public static int intFromOSC(String data) {
        return ByteBuffer.wrap(data.getBytes()).getInt();
    }

    public static float floatFromOSC(String data) {
        return ByteBuffer.wrap(data.getBytes()).getFloat();
    }

    public static double doubleFromOSC(String data) {
        return ByteBuffer.wrap(data.getBytes()).getDouble();
    }
}
