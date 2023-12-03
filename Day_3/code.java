package Day_3;

import java.io.*;
import java.util.*;

public class code {
    public static void main (String[] args) throws Exception {
        System.out.println(new part_1().run());
        // System.out.println(new part_2().run());
    }
}

class read {
    private List<String[]> matrix = new ArrayList<String[]>();
    private String[] s_line;

    List<String[]> get_input () throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("Day_3/input.txt"));
        String line = reader.readLine();

        while (line != null) {
            s_line = line.split("(?<=.)|(?=.)");
            matrix.add(s_line);
            line = reader.readLine();
        }

        reader.close();
        return matrix;
    }
}

class part_1 {
    private List<Integer> num = new ArrayList<Integer>();
    private List<String[]> matrix = new ArrayList<String[]>();

    Integer run () throws Exception {
        matrix = new read().get_input();
        v_num();
        int sum = 0;
        for (int i = 0; i < num.size(); i++) {
            // System.out.println(num.get(i));
            sum += num.get(i);
        }
        return sum;
    }

    private void v_num () {
        Integer temp_num = 0;
        String curr_s;
        boolean is_num = false;
        Integer ci = 0;
        Integer cf = 0;
        Integer c = 0;

        for (int r = 0; r < matrix.size(); r++) {
            while (c < matrix.get(r).length) {
                curr_s = matrix.get(r)[c];
                if (curr_s.matches("[0-9]")) {
                    ci = c;
                    temp_num = Integer.parseInt(curr_s);
                    is_num = true;
                    c++;
                    while (is_num && c < matrix.get(r).length) {
                        curr_s = matrix.get(r)[c];
                        if (curr_s.matches("[0-9]")) {
                            temp_num *= 10;
                            temp_num += Integer.parseInt(curr_s);
                            c++;
                        }
                        else {
                            is_num = false;
                            cf = c - 1;
                            if (check(r, ci, cf)) {
                                num.add(temp_num);
                            }
                        }
                    }
                    if (is_num) {
                        is_num = false;
                        cf = c - 1;
                        if (check(r, ci, cf)) {
                            num.add(temp_num);
                        }
                    }
                }
                c++;
            }
            c = 0;
        }
    }

    private boolean check (Integer r, Integer ci, Integer cf) {
        boolean valid = false;
        if (r > 0) valid = check_up(r, ci, cf) || valid;
        if (ci > 0) valid = check_left(r, ci, cf) || valid;
        if (cf < matrix.get(r).length - 1) valid = check_right(r, ci, cf) || valid;
        if (r < matrix.size() - 1) valid = check_down(r, ci, cf) || valid;
        if (r > 0 && ci > 0) valid = check_dul(r, ci, cf) || valid;
        if (r > 0 && cf < matrix.get(r).length - 1) valid = check_dur(r, ci, cf) || valid;
        if (r < matrix.size() - 1 && ci > 0) valid = check_ddl(r, ci, cf) || valid;
        if (r < matrix.size() - 1 && cf < matrix.get(r).length - 1) valid = check_ddr(r, ci, cf) || valid;
        return valid;
    }

    private boolean check_up (Integer r, Integer ci, Integer cf) {
        boolean valid = false;
        for (int i = ci; i <= cf; i++) {
            if (!(matrix.get(r - 1)[i].equals("."))) valid = true;
        }
        return valid;
    }

    private boolean check_down (Integer r, Integer ci, Integer cf) {
        boolean valid = false;
        for (int i = ci; i <= cf; i++) {
            if (!(matrix.get(r + 1)[i].equals("."))) valid = true;
        }   
        return valid;
    }

    private boolean check_left (Integer r, Integer ci, Integer cf) {
        boolean valid = false;
        if (!(matrix.get(r)[ci - 1].equals("."))) valid = true;
        return valid;
    }

    private boolean check_right (Integer r, Integer ci, Integer cf) {
        boolean valid = false;
        if (!(matrix.get(r)[cf + 1].equals("."))) valid = true;
        return valid;
    }

    private boolean check_dur (Integer r, Integer ci, Integer cf) {
        boolean valid = false;
        if (!(matrix.get(r - 1)[cf + 1].equals("."))) valid = true;
        return valid;
    }

    private boolean check_dul (Integer r, Integer ci, Integer cf) {
        boolean valid = false;
        if (!(matrix.get(r - 1)[ci - 1].equals("."))) valid = true;
        return valid;
    }

    private boolean check_ddr (Integer r, Integer ci, Integer cf) {
        boolean valid = false;
        if (!(matrix.get(r + 1)[cf + 1].equals("."))) valid = true;
        return valid;
    }

    private boolean check_ddl (Integer r, Integer ci, Integer cf) {
        boolean valid = false;
        if (!(matrix.get(r + 1)[ci - 1].equals("."))) valid = true;
        return valid;
    }
}

class part_2 {
    private List<Integer> num = new ArrayList<Integer>();
    private List<String[]> matrix = new ArrayList<String[]>();

    Integer run () throws Exception {
        matrix = new read().get_input();
        int sum = 0;
        for (int i = 0; i < num.size(); i++) {
            // System.out.println(num.get(i));
            sum += num.get(i);
        }
        return sum;
    }

    
}