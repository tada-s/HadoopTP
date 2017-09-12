package calcVisitTime;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class CalcVisitTimeMapper extends Mapper<Object, Text, CompositeKeyWritable, RowWritable> {
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		String row[] = value.toString().split(",");
		
		String userID = row[0];
		String url = row[1];
		long timeStamp = strDate2long(row[2]);
		
		context.write(new CompositeKeyWritable(userID, timeStamp), new RowWritable(url, timeStamp));
	}
	
	private static long strDate2long(String strDate){
		DateFormat format = new SimpleDateFormat("YYYY/MM/DD HH:mm:ss", Locale.ENGLISH);
		Date date = null;
		try {
			date = format.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return date.getTime();
	}
}
