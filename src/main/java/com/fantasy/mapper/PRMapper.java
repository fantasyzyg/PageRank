package com.fantasy.mapper;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.fantasy.config.Config.middleOutput;
import static com.fantasy.config.Config.n;
import static com.fantasy.config.Config.outputUri;


public class PRMapper extends Mapper<LongWritable, Text, Text, Text> {

    // run 函数首先setup context，然后再对context进行文本读写
//    public void run(Mapper<KEYIN, VALUEIN, KEYOUT, VALUEOUT>.Context context) throws IOException, InterruptedException {
//        this.setup(context);
//
//        try {
//            while(context.nextKeyValue()) {
//                this.map(context.getCurrentKey(), context.getCurrentValue(), context);
//            }
//        } finally {
//            this.cleanup(context);
//        }
//
//    }

    private double[] prs = new double[n];

    // 先把v向量完全读进内存
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        FileSystem fs = FileSystem.get(context.getConfiguration());
        InputStream inputStream = fs.open(new Path(middleOutput));
        DataInputStream in = new DataInputStream(inputStream);

        int i = 0;
        while (i < n) {
            String vi = in.readLine();
            System.out.println(vi);
            prs[i] = Double.parseDouble(vi);
            ++i;
        }
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        // 处理文本行如： A 3 B C D     出度节点 总出度数 连接指向节点
        String s = value.toString().trim();
        String[] ss = s.split("\\s+");
        String node = ss[0];
        int len = Integer.parseInt(ss[1]);

        if (len == 0)
            return;

        double pro = 1.0 / len;
        int index = node.charAt(0) - 'A';
        for (int i = 2;i < len + 2;++i) {
            String toNode = ss[i];
            System.out.println(toNode + " pro is   " + prs[index]+ "   " + pro);
            context.write(new Text(toNode), new Text(""+pro*prs[index]));
        }
    }
}
