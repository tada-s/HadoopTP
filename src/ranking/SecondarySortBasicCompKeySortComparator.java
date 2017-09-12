package ranking;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class SecondarySortBasicCompKeySortComparator extends WritableComparator {

  protected SecondarySortBasicCompKeySortComparator() {
		super(DummyKeyWritable.class, true);
	}

	@Override
	public int compare(WritableComparable w1, WritableComparable w2) {
		DummyKeyWritable key1 = (DummyKeyWritable) w1;
		DummyKeyWritable key2 = (DummyKeyWritable) w2;

		return -key1.compareTo(key2);
	}
}