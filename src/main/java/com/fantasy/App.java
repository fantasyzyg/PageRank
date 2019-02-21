package com.fantasy;

import com.fantasy.mapper.PRMapper;
import com.fantasy.reducer.PRReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

import static com.fantasy.config.Config.*;

/**
 * Hello world!
 *
 */
public class App {

    // Main Function
    public static void main( String[] args ) throws IOException, ClassNotFoundException, InterruptedException {
       long start = System.currentTimeMillis();

       Configuration conf = new Configuration();
       conf.set("fs.defaultFS", hdfsUri);

       // 40 次迭代
       for (int i = 1;i < 41;++i) {
           System.out.println("Iteration: " + i);
           Job job = Job.getInstance(conf);
           job.setJarByClass(App.class);
           job.setMapperClass(PRMapper.class);
           job.setReducerClass(PRReducer.class);
           job.setOutputKeyClass(Text.class);
           job.setOutputValueClass(Text.class);

           FileInputFormat.addInputPath(job, new Path(inputUri));
           outputUri = dir + i;
           FileOutputFormat.setOutputPath(job, new Path(outputUri));

           if (!job.waitForCompletion(true)) {
               System.out.println("PageRank 未完成！");
               System.exit(-1);
           }

           middleOutput = outputUri + "/part-r-00000";
       }

       long end = System.currentTimeMillis();
       System.out.println("共花费时间：" + (end-start)/1000 + "s");
    }
}
