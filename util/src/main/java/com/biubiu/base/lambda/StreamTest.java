package com.biubiu.base.lambda;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @description TODO
 * @date 2019/5/29
 */
public class StreamTest {

    public static void main(String[] args) {
        //学生
        List<Student> students = initStudentList();
        System.out.println("源数据：" + JSONObject.toJSONString(students));
        //筛选出武汉大学的学生
        List<Student> whuStudents = students.stream()
                .filter(student -> "华中科技大学".equals(student.getSchool()))
                .collect(Collectors.toList());
        System.out.println("filter：" + JSONObject.toJSONString(whuStudents));
        //limit
        List<Student> civilStudents = students.stream()
                .filter(student -> "土木工程".equals(student.getMajor()))
                .limit(2)
                .collect(Collectors.toList());
        System.out.println("limit：" + JSONObject.toJSONString(civilStudents));
        //skip
        List<Student> skitCivilStudents = students.stream()
                .filter(student -> "土木工程".equals(student.getMajor()))
                .skip(2)
                .collect(Collectors.toList());
        System.out.println("skip：" + JSONObject.toJSONString(skitCivilStudents));
        //sort
        List<Student> sortedCivilStudents = students.stream()
                .filter(student -> "土木工程".equals(student.getMajor()))
                .sorted(Comparator.comparingInt(Student::getAge))
                .limit(2)
                .collect(Collectors.toList());
        System.out.println("sort：" + JSONObject.toJSONString(sortedCivilStudents));
        //map
        List<String> names = students.stream()
                .filter(student -> "计算机科学".equals(student.getMajor()))
                .map(Student::getName)
                .collect(Collectors.toList());
        System.out.println("map：" + JSONObject.toJSONString(names));
        //mapToInt
        int totalAge = students.stream()
                .filter(student -> "计算机科学".equals(student.getMajor()))
                .mapToInt(Student::getAge)
                .sum();
        System.out.println(totalAge);
    }

    private static List<Student> initStudentList() {
        List<Student> students = new ArrayList<Student>() {
            {
                add(new Student(20160001, "孔明", 20, 1, "土木工程", "武汉大学"));
                add(new Student(20160002, "伯约", 21, 2, "信息安全", "武汉大学"));
                add(new Student(20160003, "玄德", 22, 3, "经济管理", "武汉大学"));
                add(new Student(20160004, "云长", 21, 2, "信息安全", "武汉大学"));
                add(new Student(20161001, "翼德", 21, 2, "机械与自动化", "华中科技大学"));
                add(new Student(20161002, "元直", 23, 4, "土木工程", "华中科技大学"));
                add(new Student(20161003, "奉孝", 23, 4, "计算机科学", "华中科技大学"));
                add(new Student(20162001, "仲谋", 22, 3, "土木工程", "浙江大学"));
                add(new Student(20162002, "鲁肃", 23, 4, "计算机科学", "浙江大学"));
                add(new Student(20163001, "丁奉", 24, 5, "土木工程", "南京大学"));
            }
        };
        return students;
    }

}
