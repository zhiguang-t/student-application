/*
Title: Undergraduate student subclass 
Author: Tan Zhi Guang 
Date: 20/07/2019
File name: UndergraduateStudent.java
Purpose: The purpuse of this subclass is to get an undergraduate student's marks for each 
         assessment component correctly, as well as to calculate the overall mark. 
Assumptions/Conditions: - It is assumed that the user knows the total number of marks
                        that can be awarded in each assessment component. 
                        - It is assumed that the score of each assessment component is a whole number. 
*/
package ict167assignment2;

public class UndergraduateStudent extends Student
{
    private int asg1, asg2, asg3, practical, finalExam;
    
    //default constructor
    public UndergraduateStudent()
    {}
    
    //constructor to input all the information about a student, including the student's marks
    public UndergraduateStudent(String t, String first, String last, long studentNum, int d, int m, int y, 
            int a1, int a2, int a3, int prac, int finalE)
    {
        super(t, first, last, studentNum, d, m, y);
        asg1 = a1;
        asg2 = a2;
        asg3 = a3;
        practical = prac;
        finalExam = finalE;
    }
    
    //method to set student's marks for each assessment component
    @Override
    public void setMark()
    {
        System.out.print("Enter marks for assignment 1: ");
        asg1 = kb.nextInt();
        System.out.print("Enter marks for assignment 2: ");
        asg2 = kb.nextInt();
        System.out.print("Enter marks for assignment 3: ");
        asg3 = kb.nextInt();
        System.out.print("Enter marks for practical work: ");
        practical = kb.nextInt();
        System.out.print("Enter marks for final exam: ");
        finalExam = kb.nextInt();
    }
    
    //method to calculate student's overall mark
    @Override
    public double overallMark()
    {
        return ((double)asg1*15/100 + (double)asg2*15/100 + (double)asg3*15/100 + (double)practical + (double)finalExam*45/100);
    }
    
    //method to get an output of student's marks for each assessment component
    @Override
    public void getMarks()
    {
        System.out.printf("Marks for assignment 1: %d/100\n", asg1);
        System.out.printf("Marks for assignment 2: %d/100\n", asg2);
        System.out.printf("Marks for assignment 3: %d/100\n", asg3);
        System.out.printf("Marks for practical work: %d/10\n", practical);
        System.out.printf("Marks for final exam: %d/100\n", finalExam);
    }
    
    //checking if marks of student are in the correct range
    @Override 
    public boolean checkMarks()
    {
        return !(asg1<0 || asg1>100 || asg2<0 || asg2>100 || asg3<0 || asg3>100 || 
                practical<0 || practical>10 || finalExam<0 || finalExam>100); 
    }
    
    //CSV output of student's information
    @Override
    public String getCSV()
    {
        return (getStudentCSV() + "," + Integer.toString(asg1) + "," + Integer.toString(asg2) + "," + 
                Integer.toString(asg3) + "," + Integer.toString(practical) + "," + Integer.toString(finalExam));
    }
}
