/*
Title: Student Class 
Author: Tan Zhi Guang 
Date: 20/07/2019
File name: Student.java
Purpose: The purpose of this class is to provide an instance to store 
         information about a student.
Assumptions/Conditions: - Student number is unique. 
                        - There should not be any two students with the same 
                        first name, last name and date of birth. Such cases will
                        be treated as duplicate records. 
*/
package ict167assignment2;
import java.util.Scanner;

public class Student 
{
    private String title, fname, lname;
    private long sNum;
    private int day, month, year;
    Scanner kb = new Scanner(System.in);
    
    //default constructor
    public Student()
    {}
    
    //constructor with input of all student information 
    public Student(String t, String first, String last, long studentNum, int d, int m, int y)
    {
        title = t;
        fname = first;
        lname = last; 
        sNum = studentNum;
        day = d;
        month = m;
        year = y;
    }
    
    //set method to input student's information
    public void setStudent()
    {
        System.out.print("Enter title: ");
        title = kb.nextLine();
        System.out.print("Enter first name: ");
        fname = kb.nextLine();
        System.out.print("Enter last name: ");
        lname = kb.nextLine();
        System.out.print("Enter student number: ");
        sNum = kb.nextLong();
        System.out.print("Enter day of birthday: ");
        day = kb.nextInt();
        System.out.print("Enter month of birthday: ");
        month = kb.nextInt();
        System.out.print("Enter year of birthday: ");
        year = kb.nextInt();
    }
    
    //get method to get an output of student's information
    public void getStudent()
    {
        System.out.println("Title: " + title);
        System.out.println("Fullname: " + fname + " " + lname);
        System.out.println("Student number: " + sNum);
        System.out.printf("Date of birth: %02d/%02d/%04d\n", day, month, year);
    }
    
    //method to compare the name of this student with an input student name
    public boolean compareName(String first, String last)
    {
        return(fname.equalsIgnoreCase(first) && lname.equalsIgnoreCase(last));
    }
    
    //method to retrieve student number
    public long getSNum()
    {
        return sNum;
    }
    
    //method to get CSV output of student's information
    public String getStudentCSV()
    {
        return (title + "," + fname + "," + lname + "," + Long.toString(sNum) + "," + 
                Integer.toString(day) + "," + Integer.toString(month) + "," + Integer.toString(year));
    }
    
    /* method to input student's marks that will be present in the subclasses 
    UndergraduateStudent and PostGraduateStudent */
    public void setMark()
    {}
    
    /* method to get an output of student's marks that will be present in 
    the subclasses UndergraduateStudent and PostGraduateStudent */
    public void getMarks()
    {}
    
    /* method to check that student's marks are in the correct range. 
    This method will be present in the subclasses UndergraduateStudent and PostGraduateStudent */
    public boolean checkMarks()
    {
        return true;
    }
    
    /* method to input student's marks that will be present in the subclasses 
    UndergraduateStudent and PostGraduateStudent */
    public double overallMark()
    {
        return 0;
    }
    
    //method to determine student's grade based on the overall marks 
    public String grade(double marks)
    {
        String grade;
        
        if(marks >= 80)
        {
            grade = "HD";
        }
        else if(marks < 80 && marks >= 70)
        {
            grade = "D";
        }
        else if(marks < 70 && marks >= 60)
        {
            grade = "C";
        }
        else if (marks <60 && marks >= 50)
        {
            grade = "P";
        }
        else 
        {
            grade = "N";
        }
        return grade;
    }
    
    /* method to compare two students and determine if it is a duplicate record, 
    with the same first name, last name and date of birth */
    public boolean equals(Student other)
    {
        return (fname.equalsIgnoreCase(other.fname) && lname.equalsIgnoreCase(other.lname) 
                && day == other.day && month == other.month && year == other.year);
    }
    
    //method to check if date entered is valid
    public boolean checkDate()
    {
        //ensure that year entered is positive
        if(year <= 0)
        {
            return false;
        }
        //ensure that month entered is in the correct range
        if(month <= 0 || month > 12)
        {
            return false;
        }
        //checking day input for months with 31 days 
        else if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
        {
            if(day < 1 || day > 31)
            {
                return false;
            }
        }
        //checking day input for months with 30 days 
        else if(month == 4 || month == 6 || month == 9 || month == 11)
        {
            if(day < 1 || day > 30)
            {
                return false;
            }
        }
        //checking day input for February month 
        //includes check for leap year
        else if(month == 2)
        {
            if(year%4 == 0 && year%400 == 0)
            {
                if(day < 1 || day > 29)
                {
                    return false;
                }
            }
            else if(year%4 == 0 && year%100 != 0)
            {
                if(day < 1 || day > 29)
                {
                    return false;
                }
            }
            else 
            {
                if(day < 1 || day > 28)
                {
                    return false;
                }
            }
        }
        return true;
    }
    
    //method to check if title of student is correct
    public boolean checkTitle()
    {
        return ("Mr".equalsIgnoreCase(title) || "Miss".equalsIgnoreCase(title) || 
                "Ms".equalsIgnoreCase(title) || "Mrs".equalsIgnoreCase(title));
    }
    
    //ensuring the the student's name is not null
    public boolean checkName()
    {
        return !(fname.length() <= 0 || lname.length() <= 0);
    }
    
    /* method to get CSV string output that will be present in both subclasses 
    UndergraduateStudent and PostGraduateStudent */
    public String getCSV()
    {
        return "";
    }
}
