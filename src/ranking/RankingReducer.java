package ranking;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class RankingReducer extends Reducer<DummyKeyWritable, Text, LongWritable, Text> {
	public void reduce(DummyKeyWritable key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {

		int ranking = 1;
		for(Text value : values){
			context.write(new LongWritable(ranking), value);
			ranking++;
		}
	}
}

