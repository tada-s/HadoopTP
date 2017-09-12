package ranking;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class RankingJob {


	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "ranking");

		// Jar
		job.setJarByClass(RankingJob.class);
		
		// Mapper
		job.setMapperClass(RankingMapper.class);
		job.setMapOutputKeyClass(DummyKeyWritable.class);
		job.setMapOutputValueClass(Text.class);
		
		// Sort
		job.setSortComparatorClass(SecondarySortBasicCompKeySortComparator.class);
		//Grouping
		job.setGroupingComparatorClass(SecondarySortBasicGroupingComparator.class);
		
		// Reducer
		job.setReducerClass(RankingReducer.class);
		job.setOutputKeyClass(LongWritable.class);
		job.setOutputValueClass(Text.class);
		
		// IO
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}