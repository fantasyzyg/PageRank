package com.fantasy.reducer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

import static com.fantasy.config.Config.b;
import static com.fantasy.config.Config.n;

public class PRReducer extends Reducer<Text, Text, Text, Text>{
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        double sum = 0;
        for (Text value: values)
            sum += Double.parseDouble(value.toString());

        sum = b * sum + (1-b) / n;
        // shuffle 阶段也是会经过排序的，所以限制一个reducer的时候结果是不会乱的
        System.out.println(key.toString() + "       " + sum);
        context.write(new Text(""), new Text(""+sum));
    }
}
