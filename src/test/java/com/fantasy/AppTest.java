package com.fantasy;

import static org.junit.Assert.assertTrue;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws IOException {
        // assertTrue( true );
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://192.168.44.100:9000");          // NameNode 通信端口

        FileSystem fs = FileSystem.get(conf);
        if (fs.exists(new Path("/wc"))) {
            System.out.println("The Path exists!");
        }

        fs.close();
    }
}
