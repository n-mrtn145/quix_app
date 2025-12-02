package com.quix.quix.utils;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.UUID;

public class OptionalUuidGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        try {
            Field idField = object.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            UUID id = (UUID) idField.get(object);
            if (id != null) return id; // falls manuell gesetzt
        } catch (Exception ignored) {}
        return UUID.randomUUID(); // sonst automatisch
    }
}
