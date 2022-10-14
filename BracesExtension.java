import java.util.*;
import java.io.*;
class BracesExtension {
    public static void main(String args[]) {
        System.out.println(Arrays.toString(expand("{a,b}c{d}")));
    }
    static List<String> results = new ArrayList<String>();
    public static String[] expand(String s) {
        List<List<Character>> blocks = new ArrayList<>();
        for(int i = 0; i < s.length();) {
            List<Character> block = new ArrayList<>();
            if(s.charAt(i) == '{') {
                i++;
                while(s.charAt(i) != '}') {
                    if(s.charAt(i) != ',') {
                        block.add(s.charAt(i));
                    } 
                    i++;
                }
            }
            else {
                block.add(s.charAt(i));
            }
            i++;
            Collections.sort(block);
            blocks.add(block);
        }
        backtrack(blocks,0,new StringBuilder());
        String[] resultsArr = new String[results.size()];
        for(int k = 0; k < results.size();k++) {
            resultsArr[k] = results.get(k);
        }
        return resultsArr;
    }
    public static void backtrack(List<List<Character>> blocks, int idx, StringBuilder sb) {
        if(idx == blocks.size()) {
            results.add(sb.toString());
            return;
        } 
        List<Character> block = blocks.get(idx);
        for(int i = 0; i < block.size();i++) {
            //action
            char c = block.get(i);
            sb.append(c);
            // recurse
            backtrack(blocks,idx+1,sb);
            // bactrack
            sb.setLength(sb.length() - 1);
        }
    }
}