/usr/local/hadoop/bin/hdfs dfs -rm -R /user/hduser/inputTP/
/usr/local/hadoop/bin/hdfs dfs -mkdir /user/hduser/inputTP/
/usr/local/hadoop/bin/hdfs dfs -put ./input.txt /user/hduser/inputTP
/usr/local/hadoop/bin/hdfs dfs -rm -R /user/hduser/outputTP
/usr/local/hadoop/bin/hadoop jar ./TPBigData.jar calcVisitTime.CalcVisitTimeJob$
/usr/local/hadoop/bin/hadoop jar ./TPBigData.jar promedio.PromedioJob /user/hdu$
/usr/local/hadoop/bin/hadoop jar ./TPBigData.jar ranking.RankingJob /user/hduse$
