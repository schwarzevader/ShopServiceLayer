package org.example.shopservicelayer.entity;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;




@Component

public class EntityIdResolver implements ObjectIdResolver {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void bindItem(ObjectIdGenerator.IdKey idKey, Object o) {

    }

    @Override
    public Object resolveId(ObjectIdGenerator.IdKey idKey) {
        return entityManager.find(idKey.scope,Long.parseLong(idKey.toString()));
    }

    @Override
    public ObjectIdResolver newForDeserialization(Object o) {
        return null;
    }

    @Override
    public boolean canUseFor(ObjectIdResolver objectIdResolver) {
        return false;
    }
}
