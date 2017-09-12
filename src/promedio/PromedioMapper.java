package promedio;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class PromedioMapper extends Mapper<Object, Text, Text, LongWritable> {

	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		
		String row[] = value.toString().split("\t");
		
		String url = row[0];
		long visitTime = Long.parseLong(row[1]);
		
		context.write(new Text(url), new LongWritable(visitTime));
	}
}
