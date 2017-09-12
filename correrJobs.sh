/usr/local/hadoop/bin/hdfs dfs -rm -R /user/hduser/inputTP/
/usr/local/hadoop/bin/hdfs dfs -mkdir /user/hduser/inputTP/
/usr/local/hadoop/bin/hdfs dfs -put ./input.txt /user/hduser/inputTP
/usr/local/hadoop/bin/hdfs dfs -rm -R /user/hduser/outputTP
/usr/local/hadoop/bin/hadoop jar ./TPBigData.jar calcVisitTime.CalcVisitTimeJob /user/hduser/inputTP/input.txt /user/hduser/outputTP/output1
/usr/local/hadoop/bin/hadoop jar ./TPBigData.jar promedio.PromedioJob /user/hduser/outputTP/output1 /user/hduser/outputTP/output2
/usr/local/hadoop/bin/hadoop jar ./TPBigData.jar ranking.RankingJob /user/hduser/outputTP/output2 /user/hduser/outputTP/output3
