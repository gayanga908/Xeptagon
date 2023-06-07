package com.test.xeptagon.mappers;

import com.test.xeptagon.entities.Class;
import com.test.xeptagon.entities.Student;
import com.test.xeptagon.models.CreateClass;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DefaultClassMapper implements ClassMapper{
    @Override
    public Class toEntity(CreateClass payload, Set<Student> students) {
        Class classEntity = new Class();
        classEntity.setClassName(payload.getClassName());
        classEntity.setClassModules(payload.getModules());
        classEntity.setStudents(students);
        return classEntity;
    }
}
