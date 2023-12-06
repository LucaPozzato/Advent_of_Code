package Day_5;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class code {
    public static void main (String[] args) throws Exception {
        System.out.println(new part_1().run());
    }
}

class part_1 {
    private String[] line_split;
    private List<Long[]> map_from;
    private List<Long[]> map_to;
    private List<Long[]> seeds = new ArrayList<Long[]>();
    private List<String> seeds_list;
    private List<Long[]> soil = new ArrayList<Long[]>();
    private List<Long[]> fert = new ArrayList<Long[]>();
    private List<Long[]> water = new ArrayList<Long[]>();
    private List<Long[]> light = new ArrayList<Long[]>();
    private List<Long[]> temp = new ArrayList<Long[]>();
    private List<Long[]> hum = new ArrayList<Long[]>();
    private List<Long[]> loc = new ArrayList<Long[]>();
    private Long res = Long.MAX_VALUE;

    Long run () throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(System.getenv("INPUT_FILE")));
        String line = reader.readLine();

        seeds_list = new ArrayList<String>(Arrays.asList(Pattern.compile(" ").split(line)));
        seeds_list.remove(0);
        for (String seed : seeds_list) {
            seeds.add(new Long[]{Long.parseLong(seed), Integer.toUnsignedLong(0)});
        }

        line = reader.readLine();
        line = reader.readLine();

        while (line != null) {
            switch (line) {
                case "seed-to-soil map:":
                    line = reader.readLine();
                    map_from = seeds;
                    map_to = soil;
                    break;
                case "soil-to-fertilizer map:":
                    line = reader.readLine();
                    map_from =  soil;
                    map_to = fert;
                    break;
                case "fertilizer-to-water map:":
                    line = reader.readLine();
                    map_from = fert;
                    map_to = water;
                    break;
                case "water-to-light map:":
                    line = reader.readLine();
                    map_from = water;
                    map_to = light;
                    break;
                case "light-to-temperature map:":
                    line = reader.readLine();
                    map_from = light;
                    map_to = temp;
                    break;
                case "temperature-to-humidity map:":
                    line = reader.readLine();
                    map_from = temp;
                    map_to = hum;
                    break;
                case "humidity-to-location map:":
                    line = reader.readLine();
                    map_from = hum;
                    map_to = loc;
                    break;
                default:
                    break;
            }

            if (!(line.equals(""))) {
                line_split = line.split(" ");
                for (Long[] i : map_from) {
                    if (i[0] >= Long.parseLong(line_split[1]) && i[0] < Long.parseLong(line_split[1]) + Long.parseLong(line_split[2])) {
                        map_from.get(map_from.indexOf(i))[1] = Integer.toUnsignedLong(1);
                        map_to.add(new Long[]{Long.parseLong(line_split[0]) + (i[0] - Long.parseLong(line_split[1])), Integer.toUnsignedLong(0)});
                    }
                }
            }
            else {
                for (Long[] i : map_from) {
                    if (i[1] == 0) {
                        map_to.add(new Long[]{i[0], Integer.toUnsignedLong(0)});
                    }
                }
            }
            line = reader.readLine();
        }
        
        for (Long[] i : map_from) {
            if (i[1] == 0) {
                map_to.add(new Long[]{i[0], Integer.toUnsignedLong(0)});
            }
        }

        reader.close();

        for (Long[] i : loc) {
            if (i[0] < res) {
                res = i[0];
            }
        }
        return res;
    }
}