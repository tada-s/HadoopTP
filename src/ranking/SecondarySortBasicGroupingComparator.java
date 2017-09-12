package ranking;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class SecondarySortBasicGroupingComparator extends WritableComparator {
  protected SecondarySortBasicGroupingComparator() {
		super(DummyKeyWritable.class, true);
	}

	@Override
	public int compare(WritableComparable w1, WritableComparable w2) {
		return 0;
	}
}