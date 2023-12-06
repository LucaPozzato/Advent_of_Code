package Day_1;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class code {
    public static void main(String[] args) throws Exception{
        System.out.println(new part_1().run());
        System.out.println(new part_2().run());
    }
}

class part_1 {
    Integer run () throws Exception {
        BufferedReader reader;
        Integer sum_calval = 0;
        Integer temp_calval = 0;

        reader = new BufferedReader(new FileReader(System.getenv("INPUT_FILE")));
        String line = reader.readLine();

        while (line != null) {
            String[] split = line.split("");
            for (int i = 0; i < split.length; i++) {
                if (split[i].matches("[0-9]")) {
                    temp_calval = Integer.parseInt(split[i]) * 10;
                    break;
                }
            }
            for (int i = split.length - 1; i >= 0; i--) {
                if (split[i].matches("[0-9]")) {
                    temp_calval += Integer.parseInt(split[i]);
                    break;
                }
            }
            sum_calval += temp_calval;
            temp_calval = 0;
            line = reader.readLine();
        }

        reader.close();
        return sum_calval;
    }
}

class part_2 {
    Integer run () throws Exception {
        BufferedReader reader;
        Integer sum_calval = 0;
        Integer temp_calval = 0;

        String[] numbers = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        String beg_regex = "[0-9]|one|two|three|four|five|six|seven|eight|nine";
        String end_regex = ".*([0-9]|one|two|three|four|five|six|seven|eight|nine)";

        Map<String, Integer> dict = new HashMap<String,Integer>();

        for (Integer i = 0; i < 9; i++) {
            dict.put(numbers[i], i + 1);
        }

        reader = new BufferedReader(new FileReader(System.getenv("INPUT_FILE")));
        String line = reader.readLine();

        Matcher m_first;
        Matcher m_last;

        while (line != null) {
            m_first = Pattern.compile(beg_regex).matcher(line);
            m_last = Pattern.compile(end_regex).matcher(line);
            m_first.find();
            m_last.find();

            if (m_first.group(0).matches("[0-9]")) {
                temp_calval = Integer.parseInt(m_first.group(0)) * 10;
            }
            else if (dict.containsKey(m_first.group(0))) {
                temp_calval = dict.get(m_first.group(0)) * 10;
            }

            if (m_last.group(1).matches("[0-9]")) {
                temp_calval += Integer.parseInt(m_last.group(1));
            }
            else if (dict.containsKey(m_last.group(1))) {
                temp_calval += dict.get(m_last.group(1));
            }
            sum_calval += temp_calval;
            temp_calval = 0;
            line = reader.readLine();
        }
        
        reader.close();
        return sum_calval;
    }
}