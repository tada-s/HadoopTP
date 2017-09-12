package ranking;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Date;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;

public class DummyKeyWritable implements Writable,
  	WritableComparable<DummyKeyWritable> {

	
	private Long time;

	public DummyKeyWritable() {
	}

	public DummyKeyWritable(Long time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return (new StringBuilder().append(time)).toString();
	}

	public void readFields(DataInput dataInput) throws IOException {
		time = WritableUtils.readVLong(dataInput);
	}

	public void write(DataOutput dataOutput) throws IOException {
		WritableUtils.writeVLong(dataOutput, time);
	}

	public int compareTo(DummyKeyWritable objKeyPair) {
		return time.compareTo(objKeyPair.time);
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

}