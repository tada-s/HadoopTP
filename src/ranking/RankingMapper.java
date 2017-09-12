package ranking;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class RankingMapper extends Mapper<Object, Text, DummyKeyWritable, Text> {

	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		
		String row[] = value.toString().split("\t");
		
		String url = row[0];
		long visitTime = Long.parseLong(row[1]);
		
		context.write(new DummyKeyWritable(visitTime), new Text(url));
	}
}
