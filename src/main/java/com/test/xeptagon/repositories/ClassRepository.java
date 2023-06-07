package com.test.xeptagon.repositories;

import com.test.xeptagon.entities.Class;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends CrudRepository<Class, Integer> {
}
