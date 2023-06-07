package com.test.xeptagon.models;

import com.test.xeptagon.enums.ClassModules;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteModule {
    @NotNull
    private ClassModules module;
}
