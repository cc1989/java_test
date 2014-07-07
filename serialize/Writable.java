package serialize;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 用户模拟hadoop中的序列化
 * @author huangchen.hc
 *
 */
public interface Writable {
	public void write(DataOutput out) throws IOException;
	public void readFields(DataInput in) throws IOException;
}
