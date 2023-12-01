package Day_1;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class part_2 {
    public static void main(String[] args) throws Exception{
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

        reader = new BufferedReader(new FileReader("Day_1/input.txt"));
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
        System.out.println(sum_calval);
        reader.close();
    }
}
