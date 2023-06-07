package com.test.xeptagon.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "students")
@Getter
@Setter
public class Student extends User {

    @ManyToMany(mappedBy = "students")
    private Set<Class> classes;

}
