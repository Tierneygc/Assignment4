import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.LinearProbingHashST;
import java.io.*;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Separate Chaining Hash: ");
        SeparateChainingHashST<String, Integer> sc = new SeparateChainingHashST<String, Integer>(1000, 1);
        SeparateChainingHashST<String, Integer> sc2 = new SeparateChainingHashST<String, Integer>(1000, 2);
        LinearProbingHashST<String, Integer> lp = new LinearProbingHashST<String, Integer>(20000, 1);
        LinearProbingHashST<String, Integer> lp2 = new LinearProbingHashST<String, Integer>(20000, 2);

        String csvFile = "src/Book1.csv";
        String line = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader("/Users/tierneyclark/IdeaProjects/Assignment4/out/production/Assignment4/Book1.csv"));
            int index = 1;
            while ((line = br.readLine()) != null){

                sc.put(line, index);
                sc2.put(line, index);
                lp.put(line, index);
                lp2.put(line, index);
                index++;

            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        for (int i = 1; i <= 5; i++){
            System.out.println();
            System.out.println("Insert password");
            String password = scanner.nextLine();

            //is at least 8 characters long
            boolean check1 = true;
            int length = password.length();
            if(length < 8){
                check1 = false;

            }
            //is not a word in the dictionary
            System.out.println("Separate Chaining Hash: ");
            boolean sc_check2 = !sc.contains(password);
            System.out.println("Cost of search(hashcode1): " + sc.counter1);
            sc.counter1 = 0;

            boolean sc2_check2 = !sc2.contains(password);
            System.out.println("Cost of search(hashcode2): " + sc2.counter2);
            sc2.counter2 = 0;

            System.out.println("Linear Probing Hash:");
            boolean lp_check2 = !lp.contains(password);
            System.out.println("Cost of search(hashcode1): " + lp.count1);
            lp.count1 = 0;

            boolean lp2_check2 = !lp2.contains(password);
            System.out.println("Cost of search(hashcode2): " + lp2.count2);
            lp2.count2 = 0;

            //is not a word in the dictionary followed by a digit 0-9(e.g. hello5)


            String p = password.substring(0, password.length() - 1);
            boolean sc_check3 = !sc.contains(p);


            if (check1 && sc_check2 && sc_check3){
                System.out.println("Password is strong");

            }

            else {
                System.out.println("Password is not strong");
            }
        }

        System.out.println();



        System.out.println();


    }
}