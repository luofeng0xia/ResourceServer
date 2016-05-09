package com.example.weixi.resourceserver;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void printList(){
        List<Student> list=new ArrayList<Student>();
        for (int i=0;i<10;i++){
            list.add(new Student(2012+i*10,"Snail"+i));
            System.out.println(list);
        }
    }

    class Student{
        private int xuehao;
        private String name;

        public Student(int xuehao, String name) {
            this.xuehao = xuehao;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "xuehao=" + xuehao +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}