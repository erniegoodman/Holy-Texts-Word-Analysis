/******************************************************************************
 *      Student: Ernest Goodman
 *         Date: 1 Jul 2022
 *       Course: IT-516 Data Structures & Algorithms
 *   Group Discussion 3
 *   Green Group
 *
 *  Uses a RegEx to print out all matches from the input stream.
 *
 *  Compile...
 *  javac-algs4 holytexts.java
 *
 *  Use...
 *
 *  java-algs4 holytexts "\b[A-Z].*?\b" Mahabharta.txt
 *  java-algs4 holytexts "\b[A-Z].*?\b" Babylonian-Talmud.txt
 *  java-algs4 holytexts "\b[A-Z].*?\b" King-James-Bible.txt
 *  java-algs4 holytexts "\b[A-Z].*?\b" Quran.txt
 *
 *
 *
 *
 ******************************************************************************/


import edu.princeton.cs.algs4.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class holytexts {

    public static void main(String[] args) {
        String regexp = args[0];
        In in = new In(args[1]);

        TST<Integer> st = new TST<Integer>();
        int cnt = 0;
        int word_count = 0;

        String input = in.readAll();
// This part of the code utilizes regular expressions
        Pattern pattern1 = Pattern.compile("(\\w+)");
        Matcher matcher1 = pattern1.matcher(input);

        while (matcher1.find()) {
            String key = matcher1.group();
            word_count++;

            if (st.contains(key))
                cnt = st.get(key);
            else
                cnt = 0;
            st.put(key, cnt + 1);
        }
// We are counting number of words.
        for (int i = 0; !in.isEmpty(); i++) {
            word_count++;

            String key = in.readString();

            if (st.contains(key))
                cnt = st.get(key);
            else
                cnt = 0;
            st.put(key, cnt + 1);
        }
        StdOut.println("-------------------------------------------------------------");
        StdOut.println("The Holy text has " + word_count + " words total.");


        int uniquewords_count = 0;
// Loop that counts unique words with the use of TST class of algs4 library.
        for (String key : st.keys()) {
            uniquewords_count++;
        }

        StdOut.println("--------------------------------------------------------------");
        StdOut.println("The Holy Text has " + uniquewords_count + " unique words.");
        StdOut.println("--------------------------------------------------------------");
        Pattern pattern = Pattern.compile(regexp);

// Frequency analysis
        class Frequency implements Comparable<Frequency> {
            String word;
            int frequency;

            public Frequency(String word, int frequency) {
                this.word = word;
                this.frequency = frequency;
            }

            public int compareTo(Frequency w) {
                return w.frequency - this.frequency;
            }
        }
        List<Frequency> word_frequency = new ArrayList<>();
        for (String key : st.keys()) {
            if (key.toUpperCase().equals(key)) // ALL CAPS
                continue;

            Matcher matcher = pattern.matcher(key); //Get a matcher object
            if (matcher.find() == false)
                continue;

            cnt = st.get(key);

            Frequency w = new Frequency(key, cnt);

            word_frequency.add(w);
        }

        int i = 0;
        //Arryalist for word frequencies.
        Frequency[] new_list = new Frequency[word_frequency.size()];
        for (Frequency w : word_frequency) {
            new_list[i] = w;
            i++;
        }

        Selection.sort(new_list);

        StdOut.println("Word frequencies:");
        int size = 10;
        if (size > word_frequency.size())
            size = word_frequency.size();

        for (i = 0; i < size; i++) {

// Creating arrays of frequent words.
            Frequency m = new_list[i];

            StdOut.println(String.format(" %d - %s was used %d times", (i + 1), m.word, m.frequency)); //Prints the offset after the character matches
        }


        Integer[] myArgs = new Integer[new_list.length];


        StdDraw.setCanvasSize(1000, 500);
        StdDraw.setPenColor(Color.BLUE);

        int counter = 2;


        for (int j = 1; j <= size; j++) {
            Frequency m = new_list[i];

// This part of the program creates bars.
            StdDraw.setPenColor(Color.BLUE);// Change pencolor to blue to show sorted value chart


            // Normalization and Collation
            double x1 = (new_list[0].frequency);

// For normalization of the chart we used 0.00001 coefficient because STDraw does not accept number over 1.
            double y1 = x1 * 0.00001;


            double x2 = (new_list[1].frequency);
            double y2 = x2 * 0.00001;


            double x3 = (new_list[2].frequency);
            double y3 = x3 * 0.00001;

            double x4 = (new_list[3].frequency);
            double y4 = x4 * 0.00001;

            double x5 = (new_list[4].frequency);
            double y5 = x5 * 0.00001;

            double x6 = (new_list[5].frequency);
            double y6 = x6 * 0.00001;

            double x7 = (new_list[6].frequency);
            double y7 = x7 * 0.00001;

            double x8 = (new_list[7].frequency);
            double y8 = x8 * 0.00001;

            double x9 = (new_list[8].frequency);
            double y9 = x9 * 0.00001;

            // Drawing the figure 1 ###################
            StdDraw.setPenColor(Color.BLUE);

            StdDraw.filledRectangle(0.1, 0.05, 0.03, y1);

            // Drawing the figure 2 ########################

            StdDraw.filledRectangle(0.2, 0.05, 0.03, y2);

            // Drawing the figure 3######################################


            StdDraw.filledRectangle(0.3, 0.00, 0.03, y3);

            // Drawing the figure 4######################################


            StdDraw.filledRectangle(0.4, 0.00, 0.03, y4);

// Drawing the figure 5######################################

            StdDraw.filledRectangle(0.5, 0.00, 0.03, y5);


            // Drawing the figure 6######################################


            StdDraw.filledRectangle(0.6, 0.00, 0.03, y6);


            // Drawing the figure 7######################################


            StdDraw.filledRectangle(0.7, 0.00, 0.03, y7);

            // Drawing the figure 8######################################


            StdDraw.filledRectangle(0.8, 0.00, 0.03, y8);


            // Drawing the figure 9######################################


            StdDraw.filledRectangle(0.9, 0.00, 0.03, y9);


            StdDraw.textLeft(0.1, 0.7, "Words frequency chart:");
            StdDraw.text(0.1, 0.5, new_list[0].word);
            StdDraw.text(0.2, 0.5, new_list[1].word);
            StdDraw.text(0.3, 0.5, new_list[2].word);
            StdDraw.text(0.4, 0.5, new_list[3].word);
            StdDraw.text(0.5, 0.5, new_list[4].word);
            StdDraw.text(0.6, 0.5, new_list[5].word);
            StdDraw.text(0.7, 0.5, new_list[6].word);
            StdDraw.text(0.8, 0.5, new_list[7].word);
            StdDraw.text(0.9, 0.5, new_list[8].word);


            counter = counter + 2;
        }


    }


}
