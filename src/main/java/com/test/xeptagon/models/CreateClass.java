package com.test.xeptagon.models;

import com.test.xeptagon.enums.ClassModules;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class CreateClass {
    @NotBlank
    private String className;
    @NotBlank
    private List<ClassModules> modules;
    @NotBlank
    private List<String> students;
}
