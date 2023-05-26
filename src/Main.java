import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        String file1 = "src/input1.txt";
        String file2 = "src/input2.txt";
        String merge = "src/merge.txt";
        String common = "src/common.txt";
        List<String> list1 = new ArrayList<>();   //for file1
        List<String> list2 = new ArrayList<>();   //for file2
        List<String> list3 = new ArrayList<>();   //to keep track of shared
        List<String> allInts = new ArrayList<>(); //to keep track of all the integers for merge.txt

        //file1
        try (BufferedReader reader = new BufferedReader(new FileReader(file1))) {
            String intInFile1;
            while ((intInFile1 = reader.readLine()) != null) {
                try {
                    Integer.parseInt(intInFile1);
                    allInts.add(intInFile1);
                    list1.add(intInFile1);
                } catch (NumberFormatException e) {
                    System.out.println("invalid number format: " + intInFile1);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.print("file not found:  " + file1);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.print("error while reading file");
            e.printStackTrace();
        }
        System.out.println("integers in the the first file: " + list1);

        //file2
        try (BufferedReader reader = new BufferedReader(new FileReader(file2))) {
            String intsInFile2;
            while ((intsInFile2 = reader.readLine()) != null) {
                allInts.add(intsInFile2);
                list2.add(intsInFile2);
            }
        } catch (FileNotFoundException e) {
            System.out.print("file not found: " + file2);
            e.printStackTrace();
        }
        catch (IOException e) {
            System.out.print("error reading file");
            e.printStackTrace();
        }

        System.out.println("integers in the second file: " + list2);


        /////////////////////////////////////////////////////////////////////////////
        System.out.println("all integers from both files to go to merge.txt:  " + allInts);
        try {
            File outputFile = new File(merge);
            if (outputFile.createNewFile()) {
                System.out.println("file created: " + outputFile.getName());
            } else {
                System.out.println(outputFile.getName() + " already exists");
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(merge));
            for (String integers : allInts) {
                writer.write(integers);
                writer.newLine();
            }
            writer.close(); //merge.txt updated
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String num1 : list1) {
            for (String num2 : list2) {
                if (num1.equals(num2)) {
                    list3.add(num2); //to add to common number list
                    break;
                }
            }
        }

        ///////////////////////////////////////////////////////////////////////////
        System.out.println("shared integers: " + list3);
        try {
            File outputFile = new File(common);
            if (outputFile.createNewFile()) {
                System.out.println("File created: " + outputFile.getName());
            } else {
                System.out.println("common file already exists");
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(common));
            for (String integers : list3) {
                writer.write(integers);
                writer.newLine();
            }
            writer.close(); //common.txt updated
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}