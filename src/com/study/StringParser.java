package com.study;

import java.util.*;

public class StringParser {
    static private List<String> farg = Arrays.asList("sin(", "cos(", "tan(", "cot()", "asin(", "acos(", "atan(", "acot(", "sqrt(", "ln(");
    static private List<String> sarg = Arrays.asList("pow(", "log(");
    private static Map<String, Double> constants = new HashMap<>() {
        {
            put("E", Math.E);
            put("PI", Math.PI);
        }
    };


    static String parseArg(String inp) {
        StringBuilder s = new StringBuilder(inp);
        boolean check = true;
        while (check) {
            check = false;
            for (int i = 0; i < farg.size(); i++) {
                int st = 0;

                while (s.indexOf(farg.get(i), st) != -1) {
                    check = true;
                    st = s.indexOf(farg.get(i));
                    int ed = findCloseBracket(st + farg.get(i).length(), s.toString());
                    String tmp = s.substring(st + farg.get(i).length(), ed);
                    Double inBracket = Opn.createOpn(parseArg(tmp));
                    Double res = useFunc(farg.get(i), inBracket, null);
                    s.replace(st, ed + 1, res.toString());

                }
            }
            for (int i = 0; i < sarg.size(); i++) {
                int st = 0;

                while (s.indexOf(sarg.get(i), st) != -1) {
                    check = true;
                    st = s.indexOf(sarg.get(i));
                    int fed = s.indexOf(",", st);
                    int sed = findCloseBracket(fed, s.toString());
                    Double inBracket1 = Opn.createOpn(s.substring(st + sarg.get(i).length(), fed + 1));
                    Double inBracket2 = Opn.createOpn(s.substring(fed + 1, sed));
                    Double res = useFunc(sarg.get(i), inBracket1, inBracket2);
                    s.replace(st, sed + 1, res.toString());

                }
            }
        }
        return s.toString();
    }

    static String parseConst(String inp) {
        StringBuilder s = new StringBuilder(inp);
        for (var i : constants.keySet()) {
            while (s.indexOf(i) != -1) {
                s.replace(s.indexOf(i), s.indexOf(i) + i.length(), constants.get(i).toString());
            }
        }
        return s.toString();
    }

    static String parseFactorial(String inp) {
        StringBuilder s = new StringBuilder(inp);

        while (s.indexOf("!") != -1) {
            int ind = s.indexOf("!");
            String tmp = "";
            int i= ind - 1;
            for ( ; i >= 0 && (s.charAt(i) >= '0' && s.charAt(i) <= '9'); i--){
                tmp = s.charAt(i) + tmp;
            }
            Integer numb = Integer.valueOf(tmp);
            Double res = fact(numb);
            s.replace(i + 1,ind + 1,res.toString());
        }
        return s.toString();
    }

    static private int findCloseBracket(int start, String s) {
        int v = 1;
        int z = 0;
        int i = start;

        while (v != z) {
            if (s.charAt(i) == '(') {
                v++;
            } else if (s.charAt(i) == ')') {
                z++;
            }
            i++;
        }
        return i - 1;
    }

    static private Double useFunc(String func, Double fpar, Double spar) {
        switch (func) {
            case "sin(":
                return Math.sin(fpar);
            case "cos(":
                return Math.cos(fpar);
            case "tan(":
                return Math.tan(fpar);
            case "cot(":
                return 1 / Math.tan(fpar);
            case "asin(":
                return Math.asin(fpar);
            case "acos(":
                return Math.acos(fpar);
            case "atan(":
                return Math.atan(fpar);
            case "acot(":
                return Math.atan(1 / fpar);
            case "sqrt(":
                return Math.sqrt(fpar);
            case "ln(":
                return Math.log(fpar);
            case "pow(":
                return Math.pow(fpar, spar);
            case "log(":
                return Math.log10(fpar) / Math.log10(spar);
        }
        return null;
    }

    static private Double fact(int f){
        if(f == 0)return 1.0;
        return 1.0*fact(f - 1)*f;
    }
}
