package com.citi.avro.serde;

import com.gerald.model.avro.MyRecord;
import org.junit.Test;

import java.util.Arrays;

public class AvroDeserializerTest {

  @Test
  public void test() {
    // given
    AvroSerializer avroSerializer = new AvroSerializer();
    AvroDeserializer avroDeserializer = new AvroDeserializer();
    byte[] inputs = avroSerializer.serialize("input", createMyRecord());

    // when
    MyRecord output = avroDeserializer.deserialize("output", inputs);

    System.out.printf("", output);
  }

  static MyRecord createMyRecord() {
    return MyRecord.newBuilder()
        .setMyStrings(Arrays.<CharSequence>asList("string1", "string2"))
        .build();
  }
}
