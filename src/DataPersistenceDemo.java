import java.io.*;
import java.util.*;

public class DataPersistenceDemo {
    public static void main(String[] args) {

        /* ----- EXAMPLE 1 ----- */
        // IMPORT DATA FROM A FILE
        ArrayList<String> words = new ArrayList<>();
        try {
            File myFile = new File("src\\data.txt");
            Scanner fileScanner = new Scanner(myFile);
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                words.add(data);
            }
        } catch(IOException exception) {
            System.out.println(exception.getMessage());
        }
        System.out.println(words);

        // MODIFY DATA
        words.set(1, "GOODBYE");

        // WRITE UPDATED DATA BACK TO SAME FILE
        try {
            PrintWriter dataFile = new PrintWriter("src\\data.txt");
            for (String word : words) {
                dataFile.println(word);
            }
            dataFile.close();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

        /* ----- EXAMPLE 2 ----- */
        // IMPORT DATA FROM A COMMA DELIMITED FILE
        // (oftentimes these are saved as ".csv" files rather than .txt, csv = comma separated values)
        ArrayList<Student> studentList = new ArrayList<>();
        try {
            File myFile = new File("src\\students.txt");
            Scanner fileScanner = new Scanner(myFile);
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                String[] splitData = data.split(",");
                String name = splitData[0];
                int age = Integer.parseInt(splitData[1]);
                double gpa = Double.parseDouble(splitData[2]);
                Student s = new Student(name, age, gpa);
                studentList.add(s);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        for (Student student : studentList) {
            System.out.println(student.info());
        }

        // MODIFY DATA
        studentList.add(new Student("Gary", 18,92.5));
        studentList.add(new Student("Marcy",15,91.62));

        // WRITE UPDATED DATA BACK TO SAME FILE
        try {
            PrintWriter dataFile = new PrintWriter("src\\students.txt");
            for (Student student : studentList) {
                dataFile.println(student.getName() + "," + student.getAge() + "," + student.getGpa());
            }
            dataFile.close();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
