public class Main {
    public static void main(String[] args) {
        String str = "Alibaba or Alibubab? I do not know!";
        char[] text = str.toCharArray();

        
//        int testHash = getHash(text);
//        System.out.println(testHash);
//        int iPosition = getSymbolPosition(text, 'I');
//        System.out.println(iPosition);
//        search(text, "b*b");
    }

    public static void search(char[] source, String pattern) {
        char[] patternArr = pattern.toCharArray();

        if (source.length < patternArr.length) {
            System.out.println("Такой подстроки не существует!");
        }
        int found;
        int patternHash = getHash(patternArr);


    }

    public static int getHash(char[] str){
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