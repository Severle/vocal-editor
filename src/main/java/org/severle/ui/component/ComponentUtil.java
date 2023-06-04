package org.severle.ui.component;

public class ComponentUtil {
    public static enum Location {
        TOP_LEFT(0),
        TOP_RIGHT(1),
        BOTTOM_LEFT(2),
        BOTTOM_RIGHT(3);
        public final int value;
        Location(int v) {
            value = v;
        }
    }
}
