package com.test.xeptagon.services;

import com.test.xeptagon.models.CreateClass;
import org.springframework.security.core.userdetails.UserDetails;

public interface ClassService {
    String createClass(CreateClass payload, UserDetails userDetails);
}
