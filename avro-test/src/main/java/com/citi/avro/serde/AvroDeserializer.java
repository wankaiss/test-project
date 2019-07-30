package com.citi.avro.serde;

import com.gerald.model.avro.MyRecord;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

public class AvroDeserializer implements Deserializer<MyRecord> {
  @Override
  public void configure(Map<String, ?> configs, boolean isKey) {

  }

  @Override
  public MyRecord deserialize(String s, byte[] bytes) {
    SpecificDatumReader<MyRecord> datumReader = new SpecificDatumReader<>(MyRecord.SCHEMA$);
    BinaryDecoder decoder = DecoderFactory.get().binaryDecoder(bytes, null);
    try {
      return datumReader.read(null, decoder);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public void close() {

  }
}
