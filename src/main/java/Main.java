import solver.Solver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {
        Solver s = new Solver();
        List<Integer> l = s.readInput();
        System.out.println(s.solve(l));
    }
}
