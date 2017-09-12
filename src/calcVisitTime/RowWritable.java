package calcVisitTime;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableUtils;

public class RowWritable implements Writable{
	
	private String url;
	private Long timeStamp;

	public RowWritable() {
	}

	public RowWritable(String url, Long timeStamp) {
		this.url = url;
		this.timeStamp = timeStamp;
	}
	
	@Override
	public String toString() {
		return (new StringBuilder().append(url).append("\t")
				.append(timeStamp)).toString();
	}

	public void readFields(DataInput dataInput) throws IOException {
		//url = WritableUtils.readString(dataInput);
		timeStamp = dataInput.readLong();
		url = dataInput.readUTF();
		
	}

	public void write(DataOutput dataOutput) throws IOException {
		//WritableUtils.writeString(dataOutput, url);
		dataOutput.writeLong(timeStamp);
		dataOutput.writeUTF(url);
	}

	public int compareTo(RowWritable objKeyPair) {
		int cmp1 = url.compareTo(objKeyPair.url);
		if(cmp1 != 0){
			return cmp1;
		}else{
			return timeStamp.compareTo(objKeyPair.timeStamp);
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	
}
