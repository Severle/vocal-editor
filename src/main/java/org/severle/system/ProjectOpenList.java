package org.severle.system;

import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

@Log4j2
public class ProjectOpenList {
    private static final Queue<String> queue = new ArrayBlockingQueue<>(5);

    static {
        queue.addAll(Arrays.asList(Initializer.getLastOpenProjects()));
    }

    public static String[] getList() {
        Object[] array = queue.toArray();
        String[] strings = new String[5];
        for (int i = 0; i < 5; i++) {
            strings[i] = array[4 - i].toString();
        }
        return strings;
    }

    public static boolean add(String s) {
        if (queue.size() == 5) {
            queue.poll();
        }
        return queue.add(s);
    }
}
