package de.fhkiel.seg.example_without_library.osc;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Encoder {

  private Encoder(){}

  public static String toOSC(String route, Object... arguments){
    StringBuilder output = new StringBuilder(toOSC(route));

    if(arguments.length > 0) {
      StringBuilder argType = new StringBuilder(",");
      StringBuilder args = new StringBuilder();
      for (Object o : arguments) {
        if(o instanceof String){
          argType.append("s");
          args.append(toOSC((String) o));
        } else
        if(o instanceof Integer){
          argType.append("i");
          args.append(toOSC((Integer) o));
        } else
        if(o instanceof Float){
          argType.append("f");
          args.append(toOSC((Float) o));
        } else
        if(o instanceof Double){
          argType.append("d");
          args.append(toOSC((Double) o));
        }
      }
      output.append(toOSC(argType.toString())).append(args);
    }

    return output.toString();
  }

  public static String toOSC(String value){
    StringBuilder output = new StringBuilder(value + "\0");
    while (output.length()%4 != 0){
      output.append("\0");
    }
    return output.toString();
  }

  public static String toOSC(int value){
    byte[] array = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(value).array();
    return new String(array, 0, array.length);
  }

  public static String toOSC(float value){
    byte[] array = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putFloat(value).array();
    return new String(array, 0, array.length);
  }

  public static String toOSC(double value){
    byte[] array = ByteBuffer.allocate(8).order(ByteOrder.BIG_ENDIAN).putDouble(value).array();
    return new String(array, 0, array.length);
  }

}
