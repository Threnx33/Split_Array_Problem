package solver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Solver {
    public Solver() {
    }

    public List<Integer> readInput() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Data/data.in"));
        String content = br.readLine();
        content = content.substring(1, content.length() - 1);
        String[] numbers = content.split(",");
        List<Integer> l = new ArrayList<Integer>();
        for (String n : numbers) l.add(Integer.parseInt(n));
        return l;
    }

    public boolean solve(List<Integer> l) {
        /*
            List exemple: [1,2,3,4,5,6,7,8]
            For this problem I used dynamic programming.
            Given n the length of the list, n1 length of A and n2 length of B.
            Let s be sum of list, s1 be sum of A, s2 be sum of B.
            We know average of s1 needs to be equal to average of s2 so
            avg(s1) = avg(s2) <=>
            s1/n1 = s2/n2 <=>
            s1/n1 = (s-s1)/(n-n1) <=>
            s1*(n-n1) = (s-s1)*n1 <=>
            s1*n - s1*n1 = s*n1 - s1*n1 <=>
            s1*n = s*n1 <=>
            s1 = (s*n1)/n (formula I will use to check if list is splittable)
            The only unknown is n1, so I created all possible sums for each length( maximum length is half the size of
             the list. This is because if we check the sum of length 2 we automatically check the other 6 elements because
             s-s1=s2).
            I used a set because we don't care about repeatable values.
        */
        int n = l.size(), i;
        int halfLen = n / 2 + 1;
        List<Set<Integer>> sumsA = new ArrayList<>();
        for(i = 0; i<halfLen; i++){
            sumsA.add(new HashSet<>());
        }
        sumsA.get(0).add(0);
        int s = 0, n1;
        for (Integer el : l) {
            s += el;
            for (n1 = halfLen - 2; n1 >= 0; n1--) {
                for (Integer num : sumsA.get(n1)) {
                    sumsA.get(n1 + 1).add(num + el);
                }
            }
        }
        for (n1 = 1; n1 < halfLen; n1++) {
            int s1 = (s * n1) / n;
            // s1 = (s*n1)/n, and s1 is int so (s*n1)/n should divide perfectly
            if ((s * n1) % n == 0 && sumsA.get(n1).contains(s1))
                return true;
        }
        return false;
    }

}