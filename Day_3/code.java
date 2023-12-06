package Day_3;

import java.io.*;
import java.util.*;

public class code {
    public static void main (String[] args) throws Exception {
        System.out.println(new part_1().run());
        System.out.println(new part_2().run());
    }
}

class read {
    private List<String[]> matrix = new ArrayList<String[]>();
    private String[] s_line;

    List<String[]> get_input () throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(System.getenv("INPUT_FILE")));
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
        get_nums();
        int sum = 0;
        for (int i = 0; i < num.size(); i++) {
            sum += num.get(i);
        }
        return sum;
    }

    private void get_nums () {
        Integer num_u = 1;
        Integer num_d = 1;
        Integer num_r = 1;
        Integer num_l = 1;
        Integer found = 0;

        for (int r = 0; r < matrix.size(); r++) {
            for (int c = 0; c < matrix.get(r).length; c++) {
                if (matrix.get(r)[c].equals("*")) {
                    num_u = num_ver(r, c, - 1)[0];
                    found += num_ver(r, c, - 1)[1];
                    num_d = num_ver(r, c, + 1)[0];
                    found += num_ver(r, c, + 1)[1];
                    if (matrix.get(r)[c + 1].matches("[0-9]")) {
                        num_r = get_num(r, c + 1);
                        found++;
                    }
                    if (matrix.get(r)[c - 1].matches("[0-9]")) {
                        num_l = get_num(r, c - 1);
                        found++;
                    }
                    if (found == 2) {
                        num.add(num_u * num_d * num_l * num_r);
                    }
                    found = 0;
                    num_r = 1;
                    num_l = 1;
                }
            }
        }
    }

    private Integer[] num_ver (Integer r, Integer c, Integer dir) {
        Integer temp_num = 0;
        Integer product = 1;
        Integer[] res = new Integer[2];

        if (matrix.get(r + dir)[c - 1].matches("[0-9]")) {
            temp_num = get_num(r + dir, c - 1);
            if (matrix.get(r + dir)[c].equals(".") && matrix.get(r + dir)[c + 1].matches("[0-9]")) {
                product *= temp_num;
                temp_num = get_num(r + dir, c + 1);
                temp_num *= product;
                res[0] = temp_num;
                res [1] = 2;
                return res;
            }
        }
        else if (matrix.get(r + dir)[c].matches("[0-9]")) {
            temp_num = get_num(r + dir, c);
        }
        else if (matrix.get(r + dir)[c + 1].matches("[0-9]")) {
            temp_num = get_num(r + dir, c + 1);
        }
        else {
            res[0] = 1;
            res[1] = 0;
            return res;
        }
        res[0] = temp_num;
        res[1] = 1;
        return res;
    }

    private Integer get_num (Integer r, Integer c) {
        Integer temp_num = 0;

        while (c > -1 && matrix.get(r)[c].matches("[0-9]")) {
            c--;
        }
        c++;
        while (c < matrix.get(r).length && matrix.get(r)[c].matches("[0-9]")) {
            temp_num *= 10;
            temp_num += Integer.parseInt(matrix.get(r)[c]);
            c++;
        }
        return temp_num;
    }
}