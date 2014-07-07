package serialize;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class IntWritableTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws IOException {
		IntWritable writable = new IntWritable();
		writable.Set(163);
		byte[] bytes = serialize(writable);
		assertEquals(bytes.length, 4);
		
		deserialize(writable, bytes);
		assertEquals(writable.Get(), 163);
	}

	public byte[] serialize(Writable writable) throws IOException{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DataOutputStream dataOut = new DataOutputStream(out);
		writable.write(dataOut);
		dataOut.close();
		return out.toByteArray();
	}
	
	public byte[] deserialize(Writable writable, byte[] bytes) throws IOException{
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		DataInputStream dataIn = new DataInputStream(in);
		writable.readFields(dataIn);
		dataIn.close();
		return bytes;
	}
}
