import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String str = "Alibaba or Alibubab? I do not know!";
        char[] text = str.toCharArray();
        ArrayList<Integer> pairs = search(text, "b*b");
        System.out.println(pairs);

    }

    public static ArrayList<Integer> search(char[] source, String pattern) {
        char[] patternArr = pattern.toCharArray();
        int asterik_position = getSymbolPosition(patternArr, '*');

        if (source.length < patternArr.length) {
            System.out.println("Такой подстроки не существует!");
        }
        ArrayList<Integer> found = new ArrayList<>();
        int patternHash = getPatternHash(patternArr);
        int windowHash = 0;

        for (int start = 0; start < source.length - patternArr.length; start++) {
            char[] checkArr = new char[]{source[start], source[start + 1], source[start + 2]};
            if (start == 0) {
                windowHash = getPatternHash(checkArr);
                windowHash -= getSymbolHash(source[start + asterik_position]);
            } else {
                windowHash -= getSymbolHash(source[start - 1]);
                windowHash += getSymbolHash(source[start + patternArr.length]);
                windowHash += getSymbolHash(source[start - 1 + asterik_position]);
            }
            if (windowHash == patternHash){
                int counter = 0;
                for (int i = 0; i < patternArr.length; i++) {
                    if (patternArr[i] != '*' && source[start + 1] != patternArr[i]){
                        System.out.println("Позиция " + source[start] + " не подходит!");
                        counter ++;
                    }
                }
                if (counter == 0){
                    found.add(start);
                }
            }
            windowHash += getSymbolHash(source[start + asterik_position]);
        }
        return found;
    }

    public static int getPatternHash(char[] str){
        int hash = 0;

        for (char i : str) {
            if (i == '*') {
                continue;
            } else {
                String s = Character.toString(i);
                int symbolHash = s.hashCode();
                hash += symbolHash;
            }
        }

        return hash;
    }

    public static int getSymbolHash(char symbol) {
        String s = Character.toString(symbol);
        return s.hashCode();
    }

    public static int getSymbolPosition(char[] pattern, char symbol){
        int position = -1;

        for (int i = 0; i < pattern.length; i++) {
            if (pattern[i] == symbol){
                position = i;
                break;
            } else {
                continue;
            }
        }

        return position;
    }
}