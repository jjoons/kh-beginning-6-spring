package com.kh.spring_legacy.spring_basic_20230809.member.model.vo;

public class Member {
  private String id;
  private String name;
  private int age;
  private String gender;
  private String address;
  private String[] devLang;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String[] getDevLang() {
    return devLang;
  }

  public void setDevLang(String[] devLang) {
    this.devLang = devLang;
  }


}
