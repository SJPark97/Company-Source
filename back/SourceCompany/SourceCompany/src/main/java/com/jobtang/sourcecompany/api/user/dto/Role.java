package com.jobtang.sourcecompany.api.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

  ADMIN("ADMIN,MEMBER"),
  MEMBER("MEMBER"),
  GUEST("GUEST");

  private String value;
}
