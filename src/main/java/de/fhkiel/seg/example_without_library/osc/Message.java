package de.fhkiel.seg.example_without_library.osc;

import java.util.Collections;
import java.util.List;

public class Message {
    public final String path;
    public final List<Object> data;

    public Message(String path, List<Object> data) {
        this.path = path;
        this.data = Collections.unmodifiableList(data);
    }

    @Override
    public String toString() {
        return "Message{" +
                "path='" + path + '\'' +
                ", data=" + data +
                '}';
    }
}
