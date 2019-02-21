#### 一个十分简单的PageRank实现
PageRank是谷歌提出的网页排序算法。

利用MapReduce简单实现其中的迭代算法。

###### org.apache.hadoop.io.nativeio.NativeIO 是在windows上idea上执行需要打的patch

```$xslt
M 矩阵如以下所示
A 3 B C D
B 2 A D
C 1 C
D 2 B C
```

```$xslt
v 向量如以下所示
0.25
0.25
0.25
0.25
```