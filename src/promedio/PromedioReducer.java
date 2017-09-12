package promedio;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class PromedioReducer extends Reducer<Text, LongWritable, Text, Long> {
	public void reduce(Text key, Iterable<LongWritable> values, Context context)
			throws IOException, InterruptedException {

		int c = 0;
		long totalTime = 0;
		for(LongWritable value : values){
			totalTime += value.get();
			c++;
		}
		if(c != 0){
			context.write(key, totalTime / c);
		}else{
			context.write(key, new Long(0));
		}
	}
}

