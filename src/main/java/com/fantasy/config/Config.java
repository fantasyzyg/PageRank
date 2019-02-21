package com.fantasy.config;

public class Config {
    public static String hdfsUri = "hdfs://192.168.44.100:9000";
    public static String vUri = hdfsUri + "/PageRank/V/v";
    public static String MUri = hdfsUri + "/PageRank/M";
    public static String dir = hdfsUri + "/PageRank/V";

    public static String inputUri = MUri;
    public static String middleOutput = vUri;
    public static String outputUri = null;

    public static int n = 4;
    public static double b = 0.8;
}
