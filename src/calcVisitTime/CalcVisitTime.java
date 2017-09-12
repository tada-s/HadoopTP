package calcVisitTime;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class CalcVisitTime {
	public static class TokenizerMapper extends Mapper<Object, Text, CompositeKeyWritable, Text> {
		public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
			String row[] = value.toString().split(",");
			
			context.write(new CompositeKeyWritable(row[0], row[2]), value);
		}
	}

	public static class VisitTimeDifferenceReducer extends Reducer<CompositeKeyWritable, Text, Text, Long> {
		public void reduce(CompositeKeyWritable key, Iterable<Text> values, Context context)
				throws IOException, InterruptedException {

			boolean firstTime = true;
			String lastRow[] = null;
			String currentRow[] = null;
			for(Text value : values){
				if(firstTime){
					lastRow = value.toString().split(",");
					firstTime = false;
				}else{
					currentRow = value.toString().split(",");
					Long deltaTime = timeDifference(currentRow[2], lastRow[2]);
					context.write(new Text(lastRow[1]), deltaTime);
					
					lastRow = currentRow;
				}
			}
		}
	}
	
	private static Long timeDifference(String strDate2, String strDate1){

		//String string = "2016/08/31 17:31:05";
		DateFormat format = new SimpleDateFormat("YYYY/MM/DD HH:mm:ss", Locale.ENGLISH);
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = format.parse(strDate1);
			date2 = format.parse(strDate2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return date2.getTime() - date1.getTime();
	}

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "calculate visit time");

		job.setJarByClass(CalcVisitTime.class);
		job.setMapperClass(TokenizerMapper.class);
		job.setReducerClass(VisitTimeDifferenceReducer.class);
		
		job.setMapOutputKeyClass(CompositeKeyWritable.class);
		job.setMapOutputValueClass(Text.class);
		
		job.setSortComparatorClass(SecondarySortBasicCompKeySortComparator.class);
		job.setGroupingComparatorClass(SecondarySortBasicGroupingComparator.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Long.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}