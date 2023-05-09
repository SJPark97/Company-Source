package com.jobtang.sourcecompany.api.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public enum Role {
//  ROLE_GUEST,
//  ROLE_USER,
//  ROLE_ADMIN
//
//  private final String roleName;
//  private final String parentName;
//
//  public static class ROLE {
//
//    public static final String ROLE_GUEST = "ROLE_GUEST";
//    public static final String ROLE_USER = "ROLE_USER";
//    public static final String ROLE_ADMIN = "ROLE_ADMIN";
//
//    private ROLE() {
//    }
//  }

  ADMIN("ADMIN,MEMBER"),
  MEMBER("MEMBER"),
  GUEST("GUEST");

  private String value;
//
//  Role(String description) {
//    this.description = description;
//  }
//
//    public static class ROLE {
//
//    public static final String GUEST = "GUEST";
//    public static final String USER = "USER";
//    public static final String ADMIN = "ADMIN";
//
//      private ROLE() {
//      }
//  }
}
