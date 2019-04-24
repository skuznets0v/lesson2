package com.firstlinesoftware.lesson2.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;

public class NioExample {

    public void readFromFile() throws IOException {
        try (final FileChannel channel = FileChannel.open(Paths.get("user.json"))) {
            final ByteBuffer bb = ByteBuffer.allocate(1024);
            int bytesRead = channel.read(bb);
            while (bytesRead != -1) {
                bb.flip();
                System.out.println(new String(Arrays.copyOf(bb.array(), bb.limit())));
                bb.clear();
                bytesRead = channel.read(bb);
            }
        }
    }

    public void writeToFile() throws IOException {
        final List<byte[]> data = Stream.generate(Math::random)
                .limit(20)
                .map(Object::toString)
                .map(String::getBytes)
                .collect(Collectors.toList());

        try (final FileChannel channel = FileChannel.open(Paths.get("nio.tmp"), CREATE, WRITE)) {
            final ByteBuffer bb = ByteBuffer.allocate(1024);

            for (byte[] d : data) {
                bb.put(d);
                bb.flip();
                channel.write(bb);
                bb.clear();
            }
        }
    }
}
