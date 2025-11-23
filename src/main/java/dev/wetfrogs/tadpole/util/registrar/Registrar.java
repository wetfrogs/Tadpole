package dev.wetfrogs.tadpole.util.registrar;

import java.util.HashMap;
import java.util.Map;

public abstract class Registrar<T> {
    final String modid;
    final Map<String, T> registered = new HashMap<>();

    public Registrar(String modid) {
        this.modid = modid;
    }

    void put(String name, T object) {
        if (this.registered.containsKey(name)) {
            throw new IllegalStateException("Duplicate registration: " + name);
        }

        this.registered.put(name, object);
    }

    public T get(String name) {
        return registered.get(name);
    }

    public T getOrThrow(String name) {
        T t = registered.get(name);
        if (t == null) throw new IllegalArgumentException("Not registered: " + name);
        return t;
    }
}
