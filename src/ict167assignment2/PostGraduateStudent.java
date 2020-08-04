/*
Title: Postgraduate student subclass
Author: Tan Zhi Guang 
Date: 20/07/2019
File name: PostGraduateStudent.java
Purpose: The purpuse of this subclass is to get a postgraduate student's marks for each 
         assessment component correctly, as well as to calculate the overall mark. 
Assumptions/Conditions: - It is assumed that the user knows the total number of marks
                        that can be awarded in each assessment component. 
                        - It is assumed that the score of each assessment component is a whole number. 
*/
package ict167assignment2;

public class PostGraduateStudent extends Student
{
    private int groupAsg, presentation, finalExam;
    
    //default constructor
    public PostGraduateStudent()
    {}
    
    //constructor to input all the information about a student, including the student's marks
    public PostGraduateStudent(String t, String first, String last, long studentNum, int d, int m, int y, 
            int GAsg, int present, int finalE)
    {
        super(t, first, last, studentNum, d, m, y);
        groupAsg = GAsg;
        presentation = present;
        finalExam = finalE;
    }
    
    //method to set student's marks for each assessment component
    @Override
    public void setMark()
    {
        System.out.print("Enter marks for group assignment: ");
        groupAsg = kb.nextInt();
        System.out.print("Enter marks for group presentation: ");
        presentation = kb.nextInt();
        System.out.print("Enter marks for final exam: ");
        finalExam = kb.nextInt();
    }
    
    //method to calculate student's overall mark
    @Override
    public double overallMark()
    {
        return ((double)groupAsg*30/100 + (double)presentation*20/100 + (double)finalExam/2);
    }
    
    //method to get an output of student's marks for each assessment component
    @Override
    public void getMarks()
    {
        System.out.printf("Marks for group assignment: %d/100\n", groupAsg);
        System.out.printf("Marks for presentation: %d/100\n", presentation);
        System.out.printf("Marks for final exam: %d/100\n", finalExam);
    }
    
    //checking if marks of student are in the correct range
    @Override
    public boolean checkMarks()
    {
        return !(groupAsg<0 || groupAsg>100 || presentation<0 || presentation>100 || finalExam<0 || finalExam>100);
    }
    
    //CSV output of student's information
    @Override
    public String getCSV()
    {
        return (getStudentCSV() + "," + Integer.toString(groupAsg) + "," + 
                Integer.toString(presentation) + "," + Integer.toString(finalExam));
    }
}
