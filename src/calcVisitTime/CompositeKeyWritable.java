package calcVisitTime;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Date;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;

public class CompositeKeyWritable implements Writable,
  	WritableComparable<CompositeKeyWritable> {

	private String userID;
	private String timeStamp;

	public CompositeKeyWritable() {
	}

	public CompositeKeyWritable(String userID, String timeStamp) {
		this.userID = userID;
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return (new StringBuilder().append(userID).append("\t")
				.append(timeStamp)).toString();
	}

	public void readFields(DataInput dataInput) throws IOException {
		userID = WritableUtils.readString(dataInput);
		timeStamp = WritableUtils.readString(dataInput);
	}

	public void write(DataOutput dataOutput) throws IOException {
		WritableUtils.writeString(dataOutput, userID);
		WritableUtils.writeString(dataOutput, timeStamp);
	}

	public int compareTo(CompositeKeyWritable objKeyPair) {
		int cmp1 = userID.compareTo(objKeyPair.userID);
		if(cmp1 != 0){
			return cmp1;
		}else{
			return timeStamp.compareTo(objKeyPair.timeStamp);
		}
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

}