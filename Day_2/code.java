package Day_2;

import java.io.*;
import java.util.*;

import java.util.regex.*;

public class code {
    public static void main (String[] args) throws Exception{
        System.out.println(new part_1().run());
        System.out.println(new part_2().run());
    }
}

class part_1 {
    private List<Integer> id_list = new ArrayList<Integer>();
    private Integer sum_id = 0;

    Integer run () throws Exception {
        get_ok();
        
        for (int i = 0; i < id_list.size(); i++) {
            sum_id += id_list.get(i);
        }

        return sum_id;
    }

    private void get_ok () throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("Day_2/input.txt"));
        String line = reader.readLine();

        while (line != null) {
            Matcher id = Pattern.compile("Game [0-9]+").matcher(line);
            Matcher blue = Pattern.compile("[0-9]+ blue").matcher(line);
            Matcher green = Pattern.compile("[0-9]+ green").matcher(line);
            Matcher red = Pattern.compile("[0-9]+ red").matcher(line);

            boolean r_ok = true;
            boolean g_ok = true;
            boolean b_ok = true;

            while (green.find()) {
                if (Integer.parseInt(green.group(0).split(" ")[0]) > 13) {
                    g_ok = false;
                }
            }

            while (blue.find()) {
                if (Integer.parseInt(blue.group(0).split(" ")[0]) > 14) {
                    b_ok = false;
                }
            }

            while (red.find()) {
                if (Integer.parseInt(red.group(0).split(" ")[0]) > 12) {
                    r_ok = false;
                }
            }

            if (g_ok && r_ok && b_ok) {
                id.find();
                id_list.add(Integer.parseInt(id.group(0).split(" ")[1]));
            }

            line = reader.readLine();
        }

        reader.close();
    }
}

class part_2 {
    private List<Integer> cube_power = new ArrayList<Integer>();
    private Integer sum_power = 0;

    Integer run () throws Exception {
        get_power();
        
        for (int i = 0; i < cube_power.size(); i++) {
            sum_power += cube_power.get(i);
        }

        return sum_power;
    }

    private void get_power () throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("Day_2/input.txt"));
        String line = reader.readLine();

        while (line != null) {
            Matcher blue = Pattern.compile("[0-9]+ blue").matcher(line);
            Matcher green = Pattern.compile("[0-9]+ green").matcher(line);
            Matcher red = Pattern.compile("[0-9]+ red").matcher(line);

            Integer r_min = 1;
            Integer g_min = 1;
            Integer b_min = 1;

            Integer r_curr = 0;
            Integer g_curr = 0;
            Integer b_curr = 0;

            while (green.find()) {
                g_curr = Integer.parseInt(green.group(0).split(" ")[0]);
                if (g_curr > g_min) {
                    g_min = g_curr;
                }
            }

            while (blue.find()) {
                b_curr = Integer.parseInt(blue.group(0).split(" ")[0]);
                if (b_curr > b_min) {
                    b_min = b_curr;
                }
            }

            while (red.find()) {
                r_curr = Integer.parseInt(red.group(0).split(" ")[0]);
                if (r_curr > r_min) {
                    r_min = r_curr;
                }
            }

            cube_power.add(r_min * b_min * g_min);
            line = reader.readLine();
        }

        reader.close();
    }
}