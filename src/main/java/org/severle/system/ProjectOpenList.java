package org.severle.system;

import lombok.extern.log4j.Log4j2;
import org.dom4j.Attribute;
import org.dom4j.Element;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

@Log4j2
public class ProjectOpenList {
    public static final Deque<String> deque = new ArrayDeque<>(5);

    static {
        deque.addAll(Arrays.asList(Initializer.getLastOpenProjects()));
    }

    public static String[] getList() {
        Object[] array = deque.toArray();
        String[] strings = new String[5];
        for (int i = 0; i < 5; i++) {
            strings[i] = array[4 - i].toString();
        }
        return strings;
    }

    public static boolean add(String s) {
        // determine s is exist
        boolean remove = deque.remove(s);
        boolean isPoll = false;
        if (deque.size() == 5) {
            deque.poll();
            isPoll = true;
        }
        boolean offer = deque.offer(s);
        if ((remove || isPoll) && offer) {
            // poll and offer a new one.should offer to settings.
            Element lastOpen = Initializer.getLastOpenElement();
            List<Element> list = lastOpen.elements();
            String[] strings = getList();
            for (int i = 0;i < 5;i++) {
                Attribute value = list.get(i).attribute("value");
                value.setValue(strings[4 - i]);
            }
            Initializer.flushSettings();
            return true;
        }
        return offer;
    }
}
