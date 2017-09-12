package calcVisitTime;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class CalcVisitTimeJob {
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "calculate url visit time");

		// Jar
		job.setJarByClass(CalcVisitTimeJob.class);

		// Mapper
		job.setMapperClass(CalcVisitTimeMapper.class);
		job.setMapOutputKeyClass(CompositeKeyWritable.class);
		job.setMapOutputValueClass(RowWritable.class);

		// Sort
		job.setSortComparatorClass(SecondarySortBasicCompKeySortComparator.class);
		//Grouping
		job.setGroupingComparatorClass(SecondarySortBasicGroupingComparator.class);

		// Reducer
		job.setReducerClass(CalcVisitTimeReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Long.class);
		
		// File IO
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
