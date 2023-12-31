package com.kh.spring_xml_in_20230714;

import java.util.ArrayList;

/**
 * @author user1
 *
 */
public class Student {
  private String name;
  private int age;
  private double height;
  private double weight;
  private ArrayList<String> hobbies;

  public Student() {}

  public Student(String name, int age, ArrayList<String> hobbies) {
    this.name = name;
    this.age = age;
    this.hobbies = hobbies;
  }


  public Student(String name, int age, double height, double weight, ArrayList<String> hobbies) {
    this.name = name;
    this.age = age;
    this.height = height;
    this.weight = weight;
    this.hobbies = hobbies;
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

  public double getHeight() {
    return height;
  }

  public void setHeight(double height) {
    this.height = height;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public ArrayList<String> getHobbies() {
    return hobbies;
  }

  public void setHobbies(ArrayList<String> hobbies) {
    this.hobbies = hobbies;
  }

  @Override
  public String toString() {
    return "Student [name=" + name + ", age=" + age + ", height=" + height + ", weight=" + weight
        + ", hobbies=" + hobbies + "]";
  }
}
