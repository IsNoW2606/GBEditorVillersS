package org.helmo.gbeditor.repository;

import java.util.IdentityHashMap;
import java.util.Map;

public class DBID {
    private static Map<Object, Object> ids = new IdentityHashMap<>();

    public static void register(Object object, Object id) {
        ids.put(object, id);
    }

    public static  <T extends Object> T getId(Object object) {
        return (T) ids.get(object);
    }

    public static boolean hasId(Object object) {
        return ids.containsKey(object);
    }
}
