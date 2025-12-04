import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Day4 {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        ArrayList<String[]> lines = getFileData("Day4/data");

        long partOneAnswer = 0;
        long partTwoAnswer = 0;
        partOneAnswer = getPartOneNumber(lines);
        partTwoAnswer = getPartTwoNumber(lines);
        System.out.println("Part one answer: " + partOneAnswer);
        System.out.println("Part two answer: " + partTwoAnswer);
    }

    public static long getPartOneNumber(ArrayList<String[]> info) {
        // do part 1
        long total = 0;
        //xy
        ArrayList<int[]> directionMap = new ArrayList<int[]>();
        int[] a = {1, 0};
        int[] b = {-1, 0};
        int[] c = {1, 1};
        int[] d = {-1, 1};
        int[] e = {0, 1};
        int[] f = {0, -1};
        int[] g = {1, -1};
        int[] h = {-1, -1};
        directionMap.add(a);
        directionMap.add(b);
        directionMap.add(c);
        directionMap.add(d);
        directionMap.add(e);
        directionMap.add(f);
        directionMap.add(g);
        directionMap.add(h);

        final int HEIGHT = info.size();
        int currHeight = 0;

        for (String[] line: info) {
            final int LENGTH = line.length;
            int currIndex = 0;

            for (String s: line) {
                int adjacent = 0;
                if (s.equals(".")) {
                    currIndex ++;
                    continue;
                }
                for (int[] entry: directionMap) {
                   // System.out.println(currIndex);
                    int deltaX = entry[0];
                    int deltaY = entry[1];

                    //System.out.println(deltaX + " " + deltaY);
                    
                    int x = currIndex + deltaX;
                    int y = currHeight + deltaY;
                    
                    if (x < 0 || x >= LENGTH) {
                        continue;
                    }
                    if (y < 0 || y >= HEIGHT) {
                        continue;
                    }

                    String valueAtIndex = info.get(y)[x];
                    if (valueAtIndex.equals("@")) {
                        adjacent ++;
                    }
                }
                if (adjacent < 4) {
                    total ++;
                }
                currIndex ++;
            }
            currHeight ++;
        }
        return total;
    }

    public static long getPartTwoNumber(ArrayList<String[]> info) {
        // do part 2
        long total = 0;
        //xy
        ArrayList<int[]> directionMap = new ArrayList<int[]>();
        int[] a = {1, 0};
        int[] b = {-1, 0};
        int[] c = {1, 1};
        int[] d = {-1, 1};
        int[] e = {0, 1};
        int[] f = {0, -1};
        int[] g = {1, -1};
        int[] h = {-1, -1};
        directionMap.add(a);
        directionMap.add(b);
        directionMap.add(c);
        directionMap.add(d);
        directionMap.add(e);
        directionMap.add(f);
        directionMap.add(g);
        directionMap.add(h);

        final int HEIGHT = info.size();
        ArrayList<int[]> toBeProcessed = new ArrayList<int[]>();

        while (true) {
            int currHeight = 0;
            for (String[] line: info) {
                final int LENGTH = line.length;
                int currIndex = 0;

                for (String s: line) {
                    int adjacent = 0;
                    if (s.equals(".")) {
                        currIndex ++;
                        continue;
                    }
                    for (int[] entry: directionMap) {
                    // System.out.println(currIndex);
                        int deltaX = entry[0];
                        int deltaY = entry[1];

                        //System.out.println(deltaX + " " + deltaY);
                        
                        int x = currIndex + deltaX;
                        int y = currHeight + deltaY;
                        
                        if (x < 0 || x >= LENGTH) {
                            continue;
                        }
                        if (y < 0 || y >= HEIGHT) {
                            continue;
                        }

                        String valueAtIndex = info.get(y)[x];
                        if (valueAtIndex.equals("@")) {
                            adjacent ++;
                        }
                    }
                    if (adjacent < 4) {
                        int[] coords = {currIndex, currHeight};
                        toBeProcessed.add(coords);
                    }
                    currIndex ++;
                }
                currHeight ++;
            }
            if (toBeProcessed.size() == 0) {
                return total;
            } else {
                for (int[] coords: toBeProcessed) {
                    int x = coords[0];
                    int y = coords[1];
                    info.get(y)[x] = ".";
                    total ++;
                }
                toBeProcessed.clear();
            }
        }
    }

    public static ArrayList<String[]> getFileData(String fileName) {
        ArrayList<String[]> fileData = new ArrayList<String[]>();
        try {
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                fileData.add(line.split(""));
            }
            return fileData;
        }
        catch (FileNotFoundException e) {
            return fileData;
        }
    }
}