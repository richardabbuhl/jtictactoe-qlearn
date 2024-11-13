package com.jmentor.jqlearn;

import com.jmentor.jtictactoe.DecimalHelper;
import com.jmentor.jtictactoe.Player;
import com.jmentor.jtictactoe.Random;
import com.jmentor.jtictactoe.Board;
import org.apache.commons.math3.random.MersenneTwister;

import java.io.*;
import java.text.DecimalFormat;

public record QLearn(double alpha, double epsilon, double MaxOutput, double MinOutput, boolean disp_out, double[] qvalues, int nqvalues, Random random, MersenneTwister rg) implements Player {

    public QLearn() {
        this(0.10, 0.00, -1000.0, 1000.0, false, new double[getPower(3, 9)], getPower(3, 9), new Random(), new MersenneTwister(13));
        for (int i = 0; i < nqvalues; i++) {
            qvalues[i] = 0.0;
        }
    }

    public double GetMin() {
        return MinOutput;
    }

    public double GetMax() {
        return MaxOutput;
    }

    public void set_display_outputs(boolean display_outputs) {
        disp_out = display_outputs;
    }

    public void setAlpha(double value) {
        alpha = value;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setEpsilon(double value) {
        epsilon = value;
    }

    public double getEpsilon() {
        return epsilon;
    }

    public void SaveMinMax(double result) {
        if (result < MinOutput) {
            MinOutput = result;
        }
        if (result > MaxOutput) {
            MaxOutput = result;
        }
    }

    /* random integer generator, uniform */
    public int Pick_Value(int i, int n) {
        /* return an integer in i, i+1, ... n */
        return rg.nextInt(n + 1);
    }

    public int getIndex(Board board) {
        int theIndex = 0;
        for (int i = 0; i < Board.BOXSIZE; i++) {
            theIndex += board.Get(i) * getPower(i, 3);
        }
        if (theIndex < 0 || theIndex >= nqvalues) {
            System.out.println("theIndex = " + theIndex);
        }
        return theIndex;
    }

    public int getIndex(int[] board) {
        int theIndex = 0;
        for (int i = 0; i < Board.BOXSIZE; i++) {
            theIndex += board[i] * getPower(i, 3);
        }
        if (theIndex < 0 || theIndex >= nqvalues) {
            System.out.println("theIndex = " + theIndex);
        }
        return theIndex;
    }

    public int getPower(int i, int n) {
        return (int) Math.pow(i, n);
    }

    public void save(String pName) {
        try {
            FileWriter pfd = new FileWriter(pName);
            BufferedWriter file = new BufferedWriter(pfd);

            for (int i = 0; i < nqvalues; i++) {
                file.write(decfmt.format(qvalues[i]));
                file.newLine();
            }

            file.close();
        } catch (IOException e) {
            System.out.println("Error -- " + e.toString());
        }
    }

    private InputStream getResourceAsStream(String name) throws IOException {
        InputStream result = getClass().getResourceAsStream(name);
        if (result == null) {
            result = getClass().getClassLoader().getResourceAsStream(name);
            if (result == null) {
                result = new FileInputStream(name);
            }
        }
        return result;
    }

    public void load(String pName) {
        try {
            InputStream pfd = getResourceAsStream(pName);
            BufferedReader file = new BufferedReader(new InputStreamReader(pfd));

            for (int i = 0; i < nqvalues; i++) {
                qvalues[i] = Double.parseDouble(file.readLine());
            }

            file.close();
        } catch (IOException e) {
            System.out.println("Error -- " + e.toString());
        }
    }

    public double getValue(Board curb) {
        int cur_Index;
        double result;

        cur_Index = getIndex(curb);
        return qvalues[cur_Index];
    }

    public double getValue(int[] curb) {
        int cur_Index;
        double result;

        cur_Index = getIndex(curb);
        return qvalues[cur_Index];
    }

    public void Learn(Board prevb, Board curb, boolean isTerminal, double finalValue) {
        int prev_Index;
        int cur_Index;

        if (isTerminal) {
            cur_Index = getIndex(curb);
            qvalues[cur_Index] = finalValue;
        } else {
            prev_Index = getIndex(prevb);
            cur_Index = getIndex(curb);
            qvalues[prev_Index] += alpha * (qvalues[cur_Index] - qvalues[prev_Index]);
        }
    }

    public int Move(Board b, int value) {
        int[] saved = new int[Board.BOXSIZE];
        int count = 0;
        double lowest = 10001.0;
        double highest = -10001.0;
        double new_value;
        int cur_Index;
        int j = -1;

        if (rg.nextDouble() < (1.0 - epsilon)) {
            for (int i = 0; i < Board.BOXSIZE; i++) {
                if (b.Get(i) == 0) {
                    Board next = (Board) b.clone();
                    next.Set(i, value);
                    cur_Index = getIndex(next);
                    new_value = qvalues[cur_Index];

                    if (disp_out) System.out.println("(" + i + ") new_value = " + decfmt.format(new_value));

                    if (value == 1) {
                        if (new_value > highest) {
                            highest = new_value;
                            count = 0;
                            saved[count++] = i;
                            if (disp_out) System.out.println("1. " + i + " " + decfmt.format(new_value) + " " + decfmt.format(highest));
                        } else if (Math.abs(new_value - highest) < 0.00001) {
                            highest = new_value;
                            saved[count++] = i;
                            if (disp_out) System.out.println("2. " + i + " " + decfmt.format(new_value) + " " + decfmt.format(highest));
                        }
                    } else if (value == 2) {
                        if (new_value < lowest) {
                            lowest = new_value;
                            count = 0;
                            saved[count++] = i;
                            if (disp_out) System.out.println("1. " + i + " " + decfmt.format(new_value) + " " + decfmt.format(lowest));
                        } else if (Math.abs(new_value - lowest) < 0.00001) {
                            lowest = new_value;
                            saved[count++] = i;
                            if (disp_out) System.out.println("2. " + i + " " + decfmt.format(new_value) + " " + decfmt.format(lowest));
                        }
                    } else {
                        System.out.println("Something is wrong with " + value);
                    }

                    SaveMinMax(new_value);
                }
            }

            int x = Pick_Value(0, count - 1);
            if (disp_out) System.out.print("count = " + count + ", x = " + x + " " + saved[x]);
            b.Set(saved[x], value);
            b.IncrementMove();
            if (disp_out) System.out.println();

            return saved[x];
        } else {
            int move = random.Move(b, value);
            return move;
        }
    }
}
