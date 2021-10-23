package com.study;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Opn {
    static Double createOpn(String s) {
        Pattern pattern = Pattern.compile("-*[0-9]+\\.*[0-9]*|[-()+/*]");
        Matcher matcher = pattern.matcher(s);
        List<Object> listStart = new LinkedList<>();
        while (matcher.find()) {
            listStart.add(matcher.group());
        } // Parse string

        Stack<Object> tmp = new Stack<>();
        List<Object> opn = new LinkedList<>();
        for (int i = 0; i < listStart.size(); i++) {
            String current = listStart.get(i).toString();
            Double numb = null;
            try {
                numb = Double.parseDouble(current);
            } catch (Exception ignored) {
            }
            if (numb != null) {
                opn.add(numb);
            } else if (current.equals("(")) {
                tmp.push(current);

            } else if (current.equals(")")) {
                String ss;
                do {
                    if (tmp.size() == 0) {
                        System.out.println("Input error");
                        System.exit(0);
                    }
                    ss = tmp.pop().toString();
                    if (!ss.equals("(")) opn.add(ss);


                } while (!ss.equals("("));
            } else {
                if (tmp.size() != 0 && (tmp.peek().equals("*") || tmp.peek().equals("/") || tmp.peek().equals("+") || tmp.peek().equals("-"))
                        && ((current.equals("+") || current.equals("-")) || (tmp.peek().equals("*") || tmp.peek().equals("/")))) {
                    opn.add(tmp.pop());
                }
                tmp.push(current);
            }
        }
        while (tmp.size() != 0) {
            opn.add(tmp.pop());
        }// creating opn
        Stack<Object> tmp2 = new Stack<>();
        for (int i = 0; i < opn.size(); i++) {
            String cur = opn.get(i).toString();
            Double nw = null;
            try {
                nw = Double.parseDouble(cur);
            } catch (Exception ignored) {
            }
            if (nw != null) {
                tmp2.push(nw);
            } else {
                Double second = Double.parseDouble(tmp2.pop().toString());
                Double first = Double.parseDouble(tmp2.pop().toString());
                if (cur.equals("+")) {
                    tmp2.push(first + second);
                } else if (cur.equals("-")) {
                    tmp2.push(first - second);
                } else if (cur.equals("*")) {
                    tmp2.push(first * second);
                } else {
                    tmp2.push(first / second);
                }

            }
        }
        return Double.parseDouble(tmp2.pop().toString());
    }
}
