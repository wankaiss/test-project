package com.citi.avro.serde;

import com.gerald.model.avro.MyRecord;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

public class AvroSerializer implements Serializer<MyRecord> {

  @Override
  public void configure(Map<String, ?> configs, boolean isKey) {

  }

  @Override
  public byte[] serialize(String s, MyRecord myRecord) {
    SpecificDatumWriter<MyRecord> datumWriter = new SpecificDatumWriter<>(MyRecord.SCHEMA$);
    try {
      try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
        BinaryEncoder encoder = EncoderFactory.get().directBinaryEncoder(bos, null);
        datumWriter.write(myRecord, encoder);
        encoder.flush();
        return bos.toByteArray();
      }
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public void close() {

  }
}
