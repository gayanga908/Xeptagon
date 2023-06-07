package com.test.xeptagon.fatories;

import com.test.xeptagon.enums.UserType;
import com.test.xeptagon.services.ModuleService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ModuleServiceFactory {

    private final Map<String, ModuleService> moduleServiceMap;

    public ModuleServiceFactory(Map<String, ModuleService> moduleServiceMap) {
        this.moduleServiceMap = moduleServiceMap;
    }


    public ModuleService getModuleService(UserType userType) {
        if (UserType.STUDENT.equals(userType)) {
            return moduleServiceMap.get("studentModuleService");
        }
        return moduleServiceMap.get("defaultModuleService");
    }
}
