package com.test.xeptagon.mappers;

import com.test.xeptagon.entities.Class;
import com.test.xeptagon.entities.Student;
import com.test.xeptagon.models.CreateClass;

import java.util.Set;

public interface ClassMapper {
    Class toEntity(CreateClass payload, Set<Student> students);
}
