/*
Title: Student menu program
Author: Tan Zhi Guang 
Date: 20/07/2019
File name: Demo.java
Purpose: This program will read student's information from the file student.txt 
         and store it in an array. The program will then display a menu of options
         for the user to choose from, and execute the chosen option. 
Assumptions/Conditions: - The user has placed in the correct student.txt file. 
                        - The values in the student.txt file are separated by a comma. 
                        - Every record in the student.txt is entered in a new line.
                        - There must not be any blank lines between records 
                        - The option undergraduate or postgraduate must be chosen 
                        correctly. This is because if the file is meant for
                        undergraduate but the user chooses postgraduate, the 
                        program will take the first three marks of the record and 
                        calculate the overall mark according to the marking scheme 
                        of the postgraduate student. No error will be flagged. 
*/
package ict167assignment2;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Demo 
{
    public static void main(String[] args)
    {
        String option;
        int choice = 0;
        Scanner kb = new Scanner(System.in);
        
        studentInfo();
        
        //keep prompting for undergraduate or graduate until correct option is chosen
        do
        {
            System.out.print("\nUndergraduate(U) or Postgraduate(P): ");
            option = kb.nextLine().trim().toLowerCase();
        }while(!"u".equals(option) && !"p".equals(option));
        
        /* declaring reader as null so that if file is not opened due to error, 
        there is no need to close the file */
        Scanner reader = null; 
        File datafile = new File("student.txt");
        
        //creating a student array that can hold 100 student records
        Student[] result = new Student[100];
        
        //if chosen option is undergraduate
        if("u".equals(option))
        {
            try
            {
                reader = new Scanner(datafile);
                int currIdx = 0;
                boolean checks = true;
                
                //reading records in student.txt 
                while(reader.hasNext())
                {
                    String oneLine = reader.nextLine();
                    String[] data = oneLine.split(",");

                    //converting the values into their respective data type
                    long Snum = Long.parseLong(data[3]);
                    int d = Integer.parseInt(data[4]);
                    int m = Integer.parseInt(data[5]);
                    int y = Integer.parseInt(data[6]);
                    int a1 = Integer.parseInt(data[7]);
                    int a2 = Integer.parseInt(data[8]);
                    int a3 = Integer.parseInt(data[9]);
                    int prac = Integer.parseInt(data[10]);
                    int finalE = Integer.parseInt(data[11]);
                    
                    //Stroing the record into an undergraduate instance 
                    UndergraduateStudent temp = new UndergraduateStudent(data[0], data[1], data[2], Snum, d, m, y, a1, a2, a3, prac, finalE);
                    
                    //check if length of first and last name is more than 0
                    if(temp.checkName() == false)
                    {
                        System.out.printf("\nStudent with student number %d has an invalid name\n", Snum);
                        checks = false;
                    }
                    
                    //check if date of birth is a valid date
                    if(temp.checkDate() == false)
                    {
                        System.out.printf("\nStudent with student number %d has an invalid date of birth\n", Snum);
                        checks = false;
                    }
                    
                    //check if title is valid
                    if(temp.checkTitle() == false)
                    {
                        System.out.printf("\nStudent with student number %d has an invalid title\n", Snum);
                        checks = false;
                    }
                    
                    //check if marks are in the correct range
                    if(temp.checkMarks() == false)
                    {
                        System.out.printf("\nStudent with student number %d has an invalid mark input\n", Snum);
                        checks = false;
                    }
                    
                    //check if record is a duplicated record using first name, last name and date of birth
                    for(int k = currIdx - 1; k >= 0; k--)
                    {
                        if(result[k].equals(temp))
                        {
                            System.out.printf("\nStudent with first name %s, last name %s and "
                                    + "date of birth %02d/%02d/%04d is a duplicate\n", data[1], data[2], d, m, y);
                            checks = false;
                        }
                    }
                    
                    //if all the checks passes, then store the record into the array 
                    if(checks == true)
                    {
                        result[currIdx] = temp;
                        currIdx++;
                    }
                }
                
                //if any of the checks fail, end the program
                if(checks == false)
                {
                    System.out.println("End of program");
                    choice = 1;
                }
                
                while(choice != 1)
                {
                    //displaying the menu
                    System.out.println("\nUndergraduate");
                    menu();
                    
                    try
                    {
                        choice = kb.nextInt();
                    }
                    catch(InputMismatchException e)
                    {
                        System.out.println("\nEnter an integer");
                        kb.next();  //discard bad input
                        choice = 0; //reset choice to get invalid input to show
                    }
                    
                    switch(choice)
                    {
                        case 1: 
                            System.out.println("\nEnd of program");
                            break;
                        case 2: 
                            System.out.println(); //print line at the start of every case for better readability of output
                            kb.nextLine(); //consume next line so able to get next string input correctly
                            
                            //getting the current size of array so able to continue adding to the array
                            int currentIdx = currSize(result);
                            
                            /*currentIdxHold is holding the current size of the array so that if any error happens 
                            while reading file, all the instances added to the array will be removed with respect to 
                            this index */
                            int currentIdxHold = currSize(result);
                            
                            String method = "";
                            checks = true; //reinitialise checks 
                            
                            //prompt user until correct input is obtained
                            while(!"type".equals(method) && !"file".equals(method))
                            {
                                System.out.print("Type or file: ");
                                method = kb.nextLine().toLowerCase().trim();
                                
                                if("type".equals(method))
                                {
                                    try
                                    {
                                        String cont;
                                        do
                                        {
                                            UndergraduateStudent temp = new UndergraduateStudent();
                                            
                                            temp.setStudent();
                                            
                                            //check if length of name is more than 0
                                            if(temp.checkName() == false)
                                            {
                                                System.out.println("Invalid name");
                                                checks = false;
                                            }
                    
                                            //check if title entered is correct
                                            if(temp.checkTitle() == false)
                                            {
                                                System.out.println("Invalid title");
                                                checks = false;
                                            }
                                            
                                            //check if date of birth is a valid date
                                            if(temp.checkDate() == false)
                                            {
                                                System.out.println("Invalid date of birth");
                                                checks = false;
                                            }

                                            //checking current record against records in array for duplicate
                                            for(int n = currIdx - 1; n >= 0; n--)
                                            {
                                                if(result[n].equals(temp))
                                                {
                                                    System.out.println("Student with  the same first name, last name and date of birth already exists");
                                                    checks = false;
                                                }
                                            }

                                            //if previous checks passes, prompt for marks 
                                            if(checks == true)
                                            {
                                                temp.setMark();
                                                
                                                //check mark input
                                                if(temp.checkMarks() == false)
                                                {
                                                    System.out.println("Invalid mark input");
                                                    checks = false;
                                                }
                                            }
                                            
                                            //if all checks passes, add record into array 
                                            if(checks == true)
                                            {
                                                try
                                                {
                                                    result[currentIdx] = temp;
                                                    currentIdx++;
                                                    System.out.println("Student added");
                                                }
                                                //if array is full, display error message 
                                                catch(ArrayIndexOutOfBoundsException e)
                                                {
                                                    System.out.println("Array is full! Record not added.");
                                                    System.out.println("Please ensure that total number of records is 100 or lesser");
                                                }
                                            }
                                            //if any of the checks fail, record will not be added into array
                                            else
                                            {
                                                System.out.println("Student not added due to error in input");
                                                checks = true; //reset checks 
                                            }
                                            System.out.print("Do you want to continue [Y/N]: ");
                                            cont = kb.nextLine().toLowerCase().trim();
                                        }while(!"n".equals(cont)); //continue loop as long as input is not n
                                    }
                                    /* if marks entered is of a different data type, an error message will show, 
                                     and current record will be deleted as the record does not have proper marks */
                                    catch(InputMismatchException e)
                                    {
                                        System.out.println("Enter numbers for student number");
                                        System.out.println("Record not added");
                                        clearArrayInput(result, currentIdx);
                                    }
                                }
                                else if("file".equals(method))
                                {
                                    System.out.print("Enter file name: ");
                                    String newFile = kb.nextLine().trim();
                                    
                                    //ensuring that file name includes ".txt" 
                                    if(newFile.contains(".txt") == false)
                                    {
                                        newFile = newFile + ".txt";
                                    }
                                    
                                    Scanner read = null;
                                    File secondFile = new File(newFile);
                                    try
                                    {
                                        read = new Scanner(secondFile);
                                        
                                        //reading the specified file
                                        while(read.hasNext())
                                        {
                                            String oneLine = read.nextLine();
                                            String[] data = oneLine.split(",");

                                            //converting string obtained into respective data type
                                            long Snum = Long.parseLong(data[3]);
                                            int d = Integer.parseInt(data[4]);
                                            int m = Integer.parseInt(data[5]);
                                            int y = Integer.parseInt(data[6]);
                                            int a1 = Integer.parseInt(data[7]);
                                            int a2 = Integer.parseInt(data[8]);
                                            int a3 = Integer.parseInt(data[9]);
                                            int prac = Integer.parseInt(data[10]);
                                            int finalE = Integer.parseInt(data[11]);

                                            UndergraduateStudent temp = new UndergraduateStudent(data[0], data[1], data[2], Snum, d, m, y, a1, a2, a3, prac, finalE);
                                            
                                            //check name length more than 0 
                                            if(temp.checkName() == false)
                                            {
                                                System.out.printf("\nStudent with student number %d has an invalid name\n", Snum);
                                                checks = false;
                                            }
                                            
                                            //check if date is valid
                                            if(temp.checkDate() == false)
                                            {
                                                System.out.printf("\nStudent with student number %d has an invalid date of birth\n", Snum);
                                                checks = false;
                                            }

                                            //check if title entered is correct
                                            if(temp.checkTitle() == false)
                                            {
                                                System.out.printf("\nStudent with student number %d has an invalid title\n", Snum);
                                                checks = false;
                                            }

                                            //check if marks entered is in the correct range
                                            if(temp.checkMarks() == false)
                                            {
                                                System.out.printf("\nStudent with student number %d has an invalid mark input\n", Snum);
                                                checks = false;
                                            }

                                            //check current record against records in array for duplicate
                                            for(int k = currentIdx - 1; k >= 0; k--)
                                            {
                                                if(result[k].equals(temp))
                                                {
                                                    System.out.printf("\nStudent with first name %s, last name %s and "
                                                            + "date of birth %02d/%02d/%04d is a duplicate\n", data[1], data[2], d, m, y);
                                                    checks = false;
                                                }
                                            }

                                            //if all checks passes, add record to array
                                            if(checks == true)
                                            {
                                                result[currentIdx] = temp;
                                                currentIdx++;
                                            }
                                        }

                                        /* if any of the record has an error, all the records in the file 
                                        will be removed from the array */
                                        if(checks == false)
                                        {
                                            System.out.println("Records not added");
                                            clearArrayInput(result, currentIdxHold);
                                        }
                                        else
                                        {
                                            System.out.println("Records added");
                                        }
                                    }
                                    //if specified file is not found, display error message
                                    catch(FileNotFoundException e)
                                    {
                                        System.out.println(e);
                                        System.out.println("No record added");
                                    }
                                    /* if file contains wrong data type, display error message and remove all 
                                    records added from specified file to array */
                                    catch(NumberFormatException e)
                                    {
                                        System.out.println("Text file contains wrong type of input");
                                        System.out.println(e);
                                        System.out.println("No record added");
                                        clearArrayInput(result, currentIdxHold);
                                    }
                                    /* if array size is exceeded, display error message and remove all records 
                                    added from specified file to array */
                                    catch(ArrayIndexOutOfBoundsException e)
                                    {
                                        System.out.println("Array is full! No records added.");
                                        System.out.println("Please ensure that total number of records is 100 or lesser");
                                        clearArrayInput(result, currentIdxHold);
                                    }
                                    //close file
                                    finally
                                    {
                                        if(read != null)
                                            read.close();
                                    }
                                }
                                else 
                                {
                                    System.out.println("Invalid input");
                                }
                            }
                            break;
                        case 3: 
                            output(result);
                            break;
                        case 4: 
                            System.out.println();
                            double avg = getAverageOverall(result);
                            System.out.printf("The average overall mark is %.1f\n", avg);
                            break;
                        case 5: 
                            System.out.println();
                            higherLower(result);
                            break;
                        case 6: 
                            System.out.println();
                            long hold = 0;
                            
                            //prompt for student number until correct input is obtained
                            do
                            {
                                System.out.print("Enter student number: ");
                                try
                                {
                                    hold = kb.nextLong();
                                }
                                //catch error if input contains characters
                                catch(InputMismatchException e)
                                {
                                    System.out.println("Numbers only");
                                    kb.next();
                                }
                            }while(hold == 0);
                            
                            //calling method to search for student number in array 
                            //if student number exists, the method will return the index of the student in the array
                            int idx = searchWithSNum(result, hold);
                            System.out.println();
                            
                            if(idx == -1)
                            {
                                System.out.printf("Student with student number %d is not found\n", hold);
                            }
                            //displaying details of student if found 
                            else
                            {
                                result[idx].getStudent();
                                result[idx].getMarks();
                            }
                            break;
                        case 7: 
                            System.out.println();
                            kb.nextLine();  //consume next line so able to accept user input
                            
                            System.out.print("Enter first name: ");
                            String fnameHold = kb.nextLine().trim();
                            System.out.print("Enter last name: ");
                            String lnameHold = kb.nextLine().trim();
                            
                            int validate = searchWithName(result, fnameHold, lnameHold);
                            
                            //if no matching student is found, display message
                            if(validate == 0)
                            {
                                System.out.printf("\nStudent with first name %s and last name %s is not found\n", fnameHold, lnameHold);
                            }
                            break;
                        case 8: 
                            sortArray(result);
                            output(result);
                            break;
                        case 9:
                            System.out.println();
                            outputCSV(result);
                            break;
                        default: 
                            System.out.println("\nInvalid choice");
                    }
                }
            }
            //if student.txt file is not found, display error message
            catch(FileNotFoundException e)
            {
                System.out.println(e);
                System.out.println("End of program");
            }
            //if student.txt contains data that are not in the correct domain, display error message
            catch(NumberFormatException e)
            {
                System.out.println("Text file contains wrong type of input");
                System.out.println(e);
                System.out.println("End of program");
            }
            //if number of records in student.txt exceeds array size, display error message
            catch(ArrayIndexOutOfBoundsException e)
            {
                System.out.println("Array size exceeded!"); 
                System.out.println("Please ensure that total number of records is 100 or lesser");
                System.out.println("End of program");
            }
            //close file
            finally
            {
                if(reader != null)
                    reader.close();
            }
        }
        //if postgraduate is chosen
        else
        {
            try
            {
                reader = new Scanner(datafile);
                int currIdx = 0;
                boolean checks = true;
                
                //reading records from student.txt
                while(reader.hasNext())
                {
                    String oneLine = reader.nextLine();
                    String[] data = oneLine.split(",");

                    //converting the values into their respective data type
                    long Snum = Long.parseLong(data[3]);
                    int d = Integer.parseInt(data[4]);
                    int m = Integer.parseInt(data[5]);
                    int y = Integer.parseInt(data[6]);
                    int group = Integer.parseInt(data[7]);
                    int pres = Integer.parseInt(data[8]);
                    int finalE = Integer.parseInt(data[9]);
                    
                    //Stroing the record into a postgraduate instance 
                    PostGraduateStudent temp = new PostGraduateStudent(data[0], data[1], data[2], Snum, d, m, y, group, pres, finalE);
                    
                    //check if length of first and last name is more than 0
                    if(temp.checkName() == false)
                    {
                        System.out.printf("\nStudent with student number %d has an invalid name\n", Snum);
                        checks = false;
                    }
                    
                    //check if date of birth is a valid date
                    if(temp.checkDate() == false)
                    {
                        System.out.printf("\nStudent with student number %d has an invalid date of birth\n", Snum);
                        checks = false;
                    }
                    
                    //check if title is valid
                    if(temp.checkTitle() == false)
                    {
                        System.out.printf("\nStudent with student number %d has an invalid title\n", Snum);
                        checks = false;
                    }
                    
                    //check if marks are in the correct range
                    if(temp.checkMarks() == false)
                    {
                        System.out.printf("\nStudent with student number %d has an invalid mark input\n", Snum);
                        checks = false;
                    }
                    
                     //check if record is a duplicated record using first name, last name and date of birth
                    for(int k = currIdx - 1; k >= 0; k--)
                    {
                        if(result[k].equals(temp))
                        {
                            System.out.printf("\nStudent with first name %s, last name %s and "
                                    + "date of birth %02d/%02d/%04d is a duplicate\n", data[1], data[2], d, m, y);
                            checks = false;
                        }
                    }
                    
                    //if all the checks passes, then store the record into the array 
                    if(checks == true)
                    {
                        result[currIdx] = temp;
                        currIdx++;
                    }
                }
                
                //if any of the checks fail, end the program
                if(checks == false)
                {
                    System.out.println("End of program");
                    choice = 1;
                }
                
                while(choice != 1)
                {
                    //displaying the menu
                    System.out.println("\nPostgraduate");
                    menu();
                    
                    try
                    {
                        choice = kb.nextInt();
                    }
                    catch(InputMismatchException e)
                    {
                        System.out.println("\nEnter an integer");
                        kb.next();  //discard bad input
                        choice = 0; //reset choice to get invalid input to show
                    }
                    
                    switch(choice)
                    {
                        case 1: 
                            System.out.println("\nEnd of program");
                            break;
                        case 2: 
                            System.out.println(); //print line at the start of every case for better readability of output
                            kb.nextLine(); //consume next line so able to get next string input correctly
                            
                            //getting the current size of array so able to continue adding to the array
                            int currentIdx = currSize(result);
                            
                            /*currentIdxHold is holding the current size of the array so that if any error happens 
                            while reading file, all the instances added to the array will be removed with respect to 
                            this index */
                            int currentIdxHold = currSize(result);
                            
                            String method = "";
                            checks = true; //re-initialise checks 
                            
                            //prompt user until correct input is obtained
                            while(!"type".equals(method) && !"file".equals(method))
                            {
                                System.out.print("Type or file: ");
                                method = kb.nextLine().toLowerCase().trim();
                                
                                if("type".equals(method))
                                {
                                    try
                                    {
                                        String cont;
                                        do
                                        {
                                            PostGraduateStudent temp = new PostGraduateStudent();
                                            
                                            temp.setStudent();
                                            
                                            //check if length of name is more than 0
                                            if(temp.checkName() == false)
                                            {
                                                System.out.println("Invalid name");
                                                checks = false;
                                            }
                                            
                                            //check if title entered is correct
                                            if(temp.checkTitle() == false)
                                            {
                                                System.out.println("Invalid title");
                                                checks = false;
                                            }
                                            
                                            //check if date of birth is a valid date
                                            if(temp.checkDate() == false)
                                            {
                                                System.out.println("Invalid date of birth");
                                                checks = false;
                                            }

                                            //checking current record against records in array for duplicate
                                            for(int n = currIdx - 1; n >= 0; n--)
                                            {
                                                if(result[n].equals(temp))
                                                {
                                                    System.out.println("Student with  the same first name, last name and date of birth already exists");
                                                    checks = false;
                                                }
                                            }

                                            //if previous checks passes, prompt for marks 
                                            if(checks == true)
                                            {
                                                temp.setMark();
                                                
                                                //check mark input
                                                if(temp.checkMarks() == false)
                                                {
                                                    System.out.println("Invalid mark input");
                                                    checks = false;
                                                }
                                            }
                                            
                                            //if all checks passes, add record into array 
                                            if(checks == true)
                                            {
                                                try
                                                {
                                                    result[currentIdx] = temp;
                                                    currentIdx++;
                                                    System.out.println("Student added");
                                                }
                                                //if array is full, display error message 
                                                catch(ArrayIndexOutOfBoundsException e)
                                                {
                                                    System.out.println("Array is full! Record not added.");
                                                    System.out.println("Please ensure that total number of records is 100 or lesser");                                                }
                                            }
                                            //if any of the checks fail, record will not be added into array
                                            else
                                            {
                                                System.out.println("Student not added due to error in input");
                                                checks = true; //reset checks 
                                            }
                                            System.out.print("Do you want to continue [Y/N]: ");
                                            cont = kb.nextLine().toLowerCase().trim();
                                        }while(!"n".equals(cont)); //continue loop as long as input is not n
                                    }
                                    /* if marks entered is of a different data type, an error message will show, 
                                     and current record will be deleted as the record does not have proper marks */
                                    catch(InputMismatchException e)
                                    {
                                        System.out.println(e);
                                        System.out.println("Record not added");
                                        clearArrayInput(result, currentIdx);
                                    }
                                }
                                else if("file".equals(method))
                                {
                                    System.out.print("Enter file name: ");
                                    String newFile = kb.nextLine().trim();
                                    
                                    //ensuring that file name includes ".txt" 
                                    if(newFile.contains(".txt") == false)
                                    {
                                        newFile = newFile + ".txt";
                                    }
                                    
                                    Scanner read = null;
                                    File secondFile = new File(newFile);
                                    try
                                    {
                                        read = new Scanner(secondFile);
                                        
                                        //reading the specified file
                                        while(read.hasNext())
                                        {
                                            String oneLine = read.nextLine();
                                            String[] data = oneLine.split(",");

                                            //converting string obtained into respective data type
                                            long Snum = Long.parseLong(data[3]);
                                            int d = Integer.parseInt(data[4]);
                                            int m = Integer.parseInt(data[5]);
                                            int y = Integer.parseInt(data[6]);
                                            int group = Integer.parseInt(data[7]);
                                            int pres = Integer.parseInt(data[8]);
                                            int finalE = Integer.parseInt(data[9]);

                                            PostGraduateStudent temp = new PostGraduateStudent(data[0], data[1], data[2], Snum, d, m, y, group, pres, finalE);

                                            //check name length more than 0 
                                            if(temp.checkName() == false)
                                            {
                                                System.out.printf("\nStudent with student number %d has an invalid name\n", Snum);
                                                checks = false;
                                            }
                                            
                                            //check if date is valid
                                            if(temp.checkDate() == false)
                                            {
                                                System.out.printf("\nStudent with student number %d has an invalid date of birth\n", Snum);
                                                checks = false;
                                            }

                                            //check if title entered is correct
                                            if(temp.checkTitle() == false)
                                            {
                                                System.out.printf("\nStudent with student number %d has an invalid title\n", Snum);
                                                checks = false;
                                            }

                                            //check if marks entered are in the correct range
                                            if(temp.checkMarks() == false)
                                            {
                                                System.out.printf("\nStudent with student number %d has an invalid mark input\n", Snum);
                                                checks = false;
                                            }

                                            //check current record against records in array for duplicate
                                            for(int k = currentIdx - 1; k >= 0; k--)
                                            {
                                                if(result[k].equals(temp))
                                                {
                                                    System.out.printf("\nStudent with first name %s, last name %s and "
                                                            + "date of birth %02d/%02d/%04d is a duplicate\n", data[1], data[2], d, m, y);
                                                    checks = false;
                                                }
                                            }

                                            //if all checks passes, add record to array
                                            if(checks == true)
                                            {
                                                result[currentIdx] = temp;
                                                currentIdx++;
                                            }
                                        }

                                        /* if any of the record has an error, all the records in the file 
                                        will be removed from the array */
                                        if(checks == false)
                                        {
                                            System.out.println("Records not added");
                                            clearArrayInput(result, currentIdxHold);
                                        }
                                        else
                                        {
                                            System.out.println("Records added");
                                        }
                                    }
                                    //if specified file is not found, display error message
                                    catch(FileNotFoundException e)
                                    {
                                        System.out.println(e);
                                        System.out.println("No record added");
                                    }
                                    /* if file contains wrong data type, display error message and remove all 
                                    records added from specified file to array */
                                    catch(NumberFormatException e)
                                    {
                                        System.out.println("Text file contains wrong type of input");
                                        System.out.println(e);
                                        System.out.println("No record added");
                                        clearArrayInput(result, currentIdxHold);
                                    }
                                    /* if array size is exceeded, display error message and remove all records 
                                    added from specified file to array */
                                    catch(ArrayIndexOutOfBoundsException e)
                                    {
                                        System.out.println("Array is full! No records added.");
                                        System.out.println("Please ensure that total number of records is 100 or lesser");
                                        clearArrayInput(result, currentIdxHold);
                                    }
                                    //close file
                                    finally
                                    {
                                        if(read != null)
                                            read.close();
                                    }
                                }
                                else 
                                {
                                    System.out.println("Invalid input");
                                }
                            }
                            break;
                        case 3: 
                            output(result);
                            break;
                        case 4: 
                            System.out.println();
                            double avg = getAverageOverall(result);
                            System.out.printf("The average overall mark is %.1f\n", avg);
                            break;
                        case 5: 
                            System.out.println();
                            higherLower(result);
                            break;
                        case 6: 
                            System.out.println();
                            long hold = 0;
                            
                            //prompt for student number until correct input is obtained
                            do
                            {
                                System.out.print("Enter student number: ");
                                try
                                {
                                    hold = kb.nextLong();
                                }
                                //catch error if input contains characters
                                catch(InputMismatchException e)
                                {
                                    System.out.println("Numbers only");
                                    kb.next();
                                }
                            }while(hold == 0);
                            
                            //calling method to search for student number in array 
                            //if student number exists, the method will return the index of the student in the array
                            int idx = searchWithSNum(result, hold);
                            System.out.println();
                            
                            if(idx == -1)
                            {
                                System.out.printf("Student with student number %d is not found\n", hold);
                            }
                            //displaying details of student if found 
                            else
                            {
                                result[idx].getStudent();
                                result[idx].getMarks();
                            }
                            break;
                        case 7: 
                            System.out.println();
                            kb.nextLine();  //consume next line so able to accept user input
                            
                            System.out.print("Enter first name: ");
                            String fnameHold = kb.nextLine().trim();
                            System.out.print("Enter last name: ");
                            String lnameHold = kb.nextLine().trim();
                            
                            int validate = searchWithName(result, fnameHold, lnameHold);
                            
                            //if no matching student is found, display message
                            if(validate == 0)
                            {
                                System.out.printf("Student with first name %s and last name %s is not found\n", fnameHold, lnameHold);
                            }
                            break;
                        case 8: 
                            sortArray(result);
                            output(result);
                            break;
                        case 9:
                            System.out.println();
                            outputCSV(result);
                            break;
                        default: 
                            System.out.println("\nInvalid choice");
                    }
                }
            }
            //if student.txt file is not found, display error message
            catch(FileNotFoundException e)
            {
                System.out.println(e);
                System.out.println("End of program");
            }
            //if student.txt contains data that are not in the correct domain, display error message
            catch(NumberFormatException e)
            {
                System.out.println("Text file contains wrong type of input");
                System.out.println(e);
                System.out.println("End of program");
            }
            //if number of records in student.txt exceeds array size, display error message
            catch(ArrayIndexOutOfBoundsException e)
            {
                System.out.println("Array size exceeded!"); 
                System.out.println("Please ensure that total number of records is 100 or lesser");
                System.out.println("End of program");
            }
            //close file
            finally
            {
                if(reader != null)
                    reader.close();
            }
        }
    }
    
    //method to find out the current index that the array is filled to
    public static int currSize(Student[] in)
    {
        int currNum = 0;
        //count number of objects in array 
        for(int k=0; k<in.length; k++)
        {
            if(in[k] != null)
            {
                currNum++;
            }
        }
        return currNum;
    }
    
    //using bubble sort to sort array
    public static void sortArray(Student[] in)
    {
        int currNum = currSize(in);
        
        for(int i=0; i < currNum - 1; i++)
        {
            for(int idx=0; idx < currNum - 1; idx++)
            //index only need to reach second last number
            {
                if(in[idx].getSNum() > in[idx+1].getSNum())
                {
                    //swap the values
                    Student temp = new Student();
                    temp = in[idx];
                    in[idx] = in[idx+1];
                    in[idx+1] = temp;
                }
            }
        }
    }
    
    //displaying the menu
    public static void menu()
    {
        System.out.println("\n1. Quit\n");
        System.out.println("2. Add new student(s) by typing(type) or from another text file(file)\n");
        System.out.println("3. Output all details of students\n");
        System.out.println("4. Output average overall mark\n");
        System.out.println("5. Display number of students above and below the average overall mark\n");
        System.out.println("6. Get details of student using student number\n");
        System.out.println("7. Get details of student using student name\n");
        System.out.println("8. Sort array into ascending order with respect to student number\n");
        System.out.println("9. Output sorted array from (8) into CSV file\n");
        System.out.print("Choice: ");
    }
    
    //output student's details, overall mark and grade
    public static void output(Student[] result)
    {
        for(int i = 0; i < result.length; i++)
        {
            if(result[i] != null)
            {
                System.out.println();
                result[i].getStudent();
                result[i].getMarks();
                System.out.printf("Overall mark: %.1f\n", result[i].overallMark());
                System.out.printf("Grade: %s\n", result[i].grade(result[i].overallMark()));
            }
        }
    }
    
    //find out the average overall marks in the array 
    public static double getAverageOverall(Student[] data)
    {
        double avg = 0;
        int count = 0;
        for(int i=0; i<data.length; i++)
        {
            if(data[i] != null)
            {
                avg = avg + data[i].overallMark();
                count++;
            }
        }
        avg = avg/count;
        return avg;
    }
    
    //method to find out how many students score above and below the average overall mark
    //students who score exactly the same as the average overall is counted as part of higher
    public static void higherLower(Student[] data)
    {
        double avg = getAverageOverall(data);
        int lower=0, higher=0;
        for(int i=0; i<data.length; i++)
        {
            if(data[i] != null)
            {
                if(data[i].overallMark() < avg)
                {
                    lower++;
                }
                else
                {
                    higher++;
                }
            }
        }
        System.out.println("The number of students scoring below the average mark is " + lower);
        System.out.println("The number of students scoring equal to or above the average mark is " + higher);
    }
    
    //method that accepts a student array and student number. 
    //the method will then check if student number exist in array
    //if exist, the index of that student is returned
    //if it does not exist, the value -1 will be returned instead
    public static int searchWithSNum(Student[] data, long hold)
    {
        int idx = -1;
        for(int i=0; i<data.length; i++)
        {
            if(data[i] != null)
            {
                if(data[i].getSNum() == hold)
                {
                    idx = i;
                    break; //break out of loop once found
                }
            }
        }
        return idx;
    }
    
    //method to check if student with specified first and last name exist in the array
    //if it exist, the details will be displayed
    //if it does not exist, the value 0 will be returned
    public static int searchWithName(Student[] data, String first, String last)
    {
        int check = 0;
        
        for(int i=0; i<data.length; i++)
        {
            if(data[i] != null)
            {
                if(data[i].compareName(first, last) == true)
                {
                    System.out.println();
                    data[i].getStudent();
                    data[i].getMarks();
                    check++;
                }
            }
        }
        return check;
    }
    
    //method to clear the array from the specified index to the last index of the array
    public static void clearArrayInput(Student[] data, int idx)
    {
        for(int i=idx; i<data.length; i++)
        {
            data[i] = null;
        }
    }
    
    //method to output array into a CSV file 
    //output file name will be called CSVoutput.csv
    public static void outputCSV(Student[] data)
    {
        PrintWriter writer = null;
        
        try
        {
            writer = new PrintWriter("CSVoutput.csv"); //output file name
            for(int i=0; i<data.length; i++)
            {
                //if index is not null, write details into output file
                if(data[i] != null)
                {
                    writer.println(data[i].getCSV());
                }
            }
            System.out.println("File output successful");
        }
        catch(FileNotFoundException e)
        {
            System.out.println(e);
        }
        //close file
        finally
        {
            if(writer != null)
                writer.close();
        }
    }
    
    public static void studentInfo()
    {
        System.out.println("Name: Tan Zhi Guang");
        System.out.println("Student number: 33672765");
        System.out.println("Class: ICT 285 A");
        System.out.println("Lesson timing: 2.15pm to 4.15pm");
    }
}
