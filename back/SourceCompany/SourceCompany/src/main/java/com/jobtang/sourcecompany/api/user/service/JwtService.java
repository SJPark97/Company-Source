package com.jobtang.sourcecompany.api.user.service;

import javax.servlet.http.HttpServletRequest;

public interface JwtService {
    Long userPkByRequest(HttpServletRequest request);
}
