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
        System.out.println("Separate Chaining Hash-using hashcode1: ");
        SeparateChainingHashST<String, Integer> sc = new SeparateChainingHashST<String, Integer>(1000, 1);

        String csvFile = "src/Book1.csv";
        String line = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader("Book1.csv"));
            int index = 1;
            while ((line = br.readLine()) != null){

                sc.put(line, index);
                index++;

            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        for (int i = 1; i <= 5; i++){
            System.out.println("Insert password");
            String password = scanner.nextLine();

            //is at least 8 characters long
            boolean check1 = true;
            int length = password.length();
            if(length < 8){
                check1 = false;
            }


            //is not a word in the dictionary
            boolean check2 = sc.contains(password);


            //is not a word in the dictionary followed by a digit 0-9(e.g. hello5)

            boolean check3 = true;
            String p = password.substring(0, password.length() - 1);
            String digit = password.substring(password.length() - 2, password.length() - 1);
            boolean digitCheck = false;
            if (digit == "0" || digit == "1" || digit == "2" || digit == "3" || digit == "4" || digit == "5" || digit == "6" || digit == "7" || digit == "8" || digit == "9"){
                digitCheck = true;
            }

            boolean containpassword = sc.contains(p);

            if (containpassword && digitCheck){
                check3 = false;
            }




        }

//        System.out.println("Insert password");
//        String password1 = scanner.nextLine();
//        String password2 = scanner.nextLine();
//        String password3 = scanner.nextLine();
//        String password4 = scanner.nextLine();
//        String password5 = scanner.nextLine();

            //LinearProbingHashST<String, Integer> lp = new LinearProbingHashST<String, Integer>(1000, 1);

//        for (int i = 0; !StdIn.isEmpty(); i++) {
//            String key = StdIn.readString();
//            st.put(key, i);
//        }
//
//        // print keys
//        for (String s : st.keys())
//            StdOut.println(s + " " + st.get(s));
//    }
    }
}