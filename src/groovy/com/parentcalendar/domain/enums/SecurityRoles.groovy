package com.parentcalendar.domain.enums


public enum SecurityRoles {

  ROLE_USER("ROLE_USER"),
  ROLE_ADMIN("ROLE_ADMIN")

  String name

  public SecurityRoles(String name) {
    this.name = name
  }

}