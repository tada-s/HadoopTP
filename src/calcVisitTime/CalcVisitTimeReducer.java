package calcVisitTime;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CalcVisitTimeReducer extends Reducer<CompositeKeyWritable, RowWritable, Text, Long> {
	
	public void reduce(CompositeKeyWritable key, Iterable<RowWritable> values, Context context)
			throws IOException, InterruptedException {

		boolean firstTime = true;
		
		String lastURL = null;
		long lastTimeStamp = 0;

		for(RowWritable value : values){
			if(firstTime){
				lastURL = value.getUrl();
				lastTimeStamp = value.getTimeStamp();
				firstTime = false;
			}else{
				long deltaTime = value.getTimeStamp() - lastTimeStamp;
				context.write(new Text(lastURL), deltaTime);
				
				lastURL = value.getUrl();
				lastTimeStamp = value.getTimeStamp();
			}
		}
	}
}