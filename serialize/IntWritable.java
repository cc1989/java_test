package serialize;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class IntWritable implements Writable{

	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeInt(value);
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		value = in.readInt();
	}
	
	public void Set(int value){
		this.value = value;
	}
	
	public int Get(){
		return value;
	}
	private int value = 0;
}
