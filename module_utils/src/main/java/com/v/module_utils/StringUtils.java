package com.v.module_utils;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理工具
 */
public final class StringUtils {

    public static final String EMPTY_STRING = "";

    // \u3000 is the double-byte space character in UTF-8
    // \u00A0 is the non-breaking space character (&nbsp;)
    // \u2007 is the figure space character (&#8199;)
    // \u202F is the narrow non-breaking space character (&#8239;)
    public static final String WHITE_SPACES = " \r\n\t\u3000\u00A0\u2007\u202F";

    public static final String LINE_BREAKS = "\r\n";

    private static final Pattern htmlTagPattern = Pattern.compile("</?[a-zA-Z][^>]*>");

    private static final Pattern characterReferencePattern = Pattern.compile("&#?[a-zA-Z0-9]{1,8};");

    private static final Pattern dbSpecPattern = Pattern.compile("(.*)\\{(\\d+),(\\d+)\\}(.*)");

    // This class should not be instantiated, hence the private constructor
    private StringUtils() {
    }

    /**
     * Split "str" by run of delimiters and return.
     */
    public static String[] split(String str, String delims) {
        return split(str, delims, false);
    }

    /**
     * Split "str" into tokens by delimiters and optionally remove white spaces
     * from the splitted tokens.
     *
     * @param trimTokens if true, then trim the tokens
     */
    public static String[] split(String str, String delims, boolean trimTokens) {
        StringTokenizer tokenizer = new StringTokenizer(str, delims);
        int n = tokenizer.countTokens();
        String[] list = new String[n];
        for (int i = 0; i < n; i++) {
            if (trimTokens) {
                list[i] = tokenizer.nextToken().trim();
            } else {
                list[i] = tokenizer.nextToken();
            }
        }
        return list;
    }

    public static String reMoveLeft(String time) {
        while (time.startsWith("0")) {
            time = time.substring(1);
        }

        if (TextUtils.isEmpty(time))
            return "0";
        else
            return time;
    }

    /**
     * Short hand for <code>split(str, delims, true)</code>
     */
    public static String[] splitAndTrim(String str, String delims) {
        return split(str, delims, true);
    }

    /**
     * Parse comma-separated list of ints and return as array.
     */
    public static int[] splitInts(String str) throws IllegalArgumentException {
        StringTokenizer tokenizer = new StringTokenizer(str, ",");
        int n = tokenizer.countTokens();
        int[] list = new int[n];
        for (int i = 0; i < n; i++) {
            String token = tokenizer.nextToken();
            list[i] = Integer.parseInt(token);
        }
        return list;
    }

    /**
     * Parse comma-separated list of longs and return as array.
     */
    public static long[] splitLongs(String str) throws IllegalArgumentException {
        StringTokenizer tokenizer = new StringTokenizer(str, ",");
        int n = tokenizer.countTokens();
        long[] list = new long[n];
        for (int i = 0; i < n; i++) {
            String token = tokenizer.nextToken();
            list[i] = Long.parseLong(token);
        }
        return list;
    }

    /**
     * <p>
     * Checks if a String is not empty ("") and not null.
     * </p>
     * <p/>
     * <pre>
     * StringUtils.isNotEmpty(null)      = false
     * StringUtils.isNotEmpty("")        = false
     * StringUtils.isNotEmpty(" ")       = true
     * StringUtils.isNotEmpty("bob")     = true
     * StringUtils.isNotEmpty("  bob  ") = true
     * </pre>
     *
     * @param s the String to check, may be null
     * @return <code>true</code> if the String is not empty and not null
     */
    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

    /**
     * Helper function for null and empty string testing.
     *
     * @return true iff s == null or s.equals("");
     */
    public static boolean isEmpty(String s) {
        return makeSafe(s).length() == 0;
    }

    /**
     * Helper function for null, empty, and whitespace string testing.
     *
     * @return true if s == null or s.equals("") or s contains only whitespace
     * characters.
     */
    public static boolean isEmptyOrWhitespace(String s) {
        s = makeSafe(s);
        for (int i = 0, n = s.length(); i < n; i++) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Helper function for making null strings safe for comparisons, etc.
     *
     * @return (s = = null) ? "" : s;
     */
    public static String makeSafe(String s) {
        return (s == null) ? "" : s;
    }

    /**
     * Helper function for making empty strings into a null.
     *
     * @return null if s is zero length. otherwise, returns s.
     */
    public static String toNullIfEmpty(String s) {
        return (StringUtils.isEmpty(s)) ? null : s;
    }

    /**
     * Helper function for turning empty or whitespace strings into a null.
     *
     * @return null if s is zero length or if s contains only whitespace
     * characters. otherwise, returns s.
     */
    public static String toNullIfEmptyOrWhitespace(String s) {
        return (StringUtils.isEmptyOrWhitespace(s)) ? null : s;
    }

    /**
     * Serializes a map
     *
     * @param map           A map of String keys to arrays of String values
     * @param keyValueDelim Delimiter between keys and values
     * @param entryDelim    Delimiter between entries
     * @return String A string containing a serialized representation of the
     * contents of the map.
     * <p/>
     * e.g. arrayMap2String({"foo":["bar","bar2"],"foo1":["bar1"]}, "=",
     * "&") returns "foo=bar&foo=bar2&foo1=bar1"
     */
    public static String arrayMap2String(Map<String, String[]> map, String keyValueDelim, String entryDelim) {
        Set<Entry<String, String[]>> entrySet = map.entrySet();
        Iterator<Entry<String, String[]>> itor = entrySet.iterator();
        StringWriter sw = new StringWriter();
        while (itor.hasNext()) {
            Entry<String, String[]> entry = itor.next();
            String key = entry.getKey();
            String[] values = entry.getValue();
            for (int i = 0; i < values.length; i++) {
                sw.write(entry.getKey() + keyValueDelim + values[i]);
                if (i < values.length - 1) {
                    sw.write(entryDelim);
                }
            }
            if (itor.hasNext()) {
                sw.write(entryDelim);
            }
        }
        return sw.toString();
    }

    /**
     * Compares two strings, guarding against nulls If both Strings are null we
     * return true
     */
    public static boolean equals(String s1, String s2) {
        if (s1 == s2) {
            return true; // Either both the same String, or both null
        }
        if (s1 != null) {
            if (s2 != null) {
                return s1.equals(s2);
            }
        }
        return false;
    }

    public static boolean equalsIgnoreCase(String s1, String s2) {
        if (s1 == s2) {
            return true; // Either both the same String, or both null
        }
        if (s1 != null) {
            if (s2 != null) {
                return s1.equalsIgnoreCase(s2);
            }
        }
        return false;
    }

    /**
     * Splits s with delimiters in delimiter and returns the last token
     */
    public static String lastToken(String s, String delimiter) {
        String[] parts = split(s, delimiter);
        return (parts.length == 0) ? "" : parts[parts.length - 1];
    }

    /**
     * Determines if a string contains only ascii characters
     */
    public static boolean allAscii(String s) {
        int len = s.length();
        for (int i = 0; i < len; ++i) {
            if ((s.charAt(i) & 0xff80) != 0) {
                return false;
            }
        }
        return true;
    }

    private static Stack<BigDecimal> numbers = new Stack<BigDecimal>();

    private static Stack<Character> chs = new Stack<Character>();

    private static boolean isNum(String num) {
        return num.matches("[0-9]");
    }

    /***
     * 验证是否 纯数字
     */
    public static boolean checkIsNum(String str) {
        String regEx = "[0-9]+";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 整理字符串 去掉无用的换行
     */
    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    public static boolean containsChinese(String s) {
        if (null == s || "".equals(s.trim()))
            return false;
        for (int i = 0; i < s.length(); i++) {
            if (isChinese(s.charAt(i)))
                return true;
        }
        return false;
    }

    public static boolean isChinese(char a) {
        int v = (int) a;
        return (v >= 19968 && v <= 171941);
    }

    /**
     * 判断某个字符串是否存在于数组中
     *
     * @param stringArray 原数组
     * @param source      查找的字符串
     * @return 是否找到
     */
    public static boolean contains(String[] stringArray, String source) {
        // 转换为list
        List<String> tempList = Arrays.asList(stringArray);

        // 利用list的包含方法,进行判断
        return tempList.contains(source);
    }

    public static String getNameByUrl(String url) {
        try {
            return url.substring(url.lastIndexOf("/") + 1, url.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unused")
    public static HashMap<Integer, Integer> checkWordInPosition(final String word, String msg) {
        HashMap<Integer, Integer> maps = new HashMap<Integer, Integer>();
        int start, end, pEnd = 0;
        int count = 0;
        while (true) {
            if (msg.indexOf(word) >= 0) {
                start = msg.indexOf(word);
                end = start + word.length() - 1;
                int nextIndex = end + 1;

                String leftStr = leftWord(msg, start);
                String endStr = rightWord(msg, end);

                if ((leftStr == null || !isLetter(leftStr)) && (endStr == null || !isLetter(endStr))) {
                    int finalStart = start + pEnd + (count);
                    maps.put(finalStart, finalStart + word.length());
                    // System.out.println("true");
                } else {
                    // System.out.println("false");
                }

                if (nextIndex <= msg.length()) {
                    pEnd += end;
                    msg = msg.substring(nextIndex, msg.length());
                } else {
                    break;
                }
                count++;
            } else {
                break;
            }
        }
        return maps;
    }

    public static String rightWord(String msg, int end) {
        try {
            if ((end + 1) >= msg.length())
                return null;
            return msg.substring(end + 1, end + 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String leftWord(String msg, int start) {
        try {
            if (start == 0)
                return null;
            return msg.substring(start - 1, start);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isLetter(String str) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]+");
        Matcher m = pattern.matcher(str);
        return m.matches();
    }

    public static float parseSizeForMB(int size) {
        float fs = ((float) size / (float) 1024 / (float) 1024);
        return (float) (Math.round(fs * 100)) / 100;
    }

    /**
     * 将InputStream转换成某种字符编码的String
     *
     * @param in
     * @param encoding
     * @return
     * @throws Exception
     */
    public static String InputStreamTOString(InputStream in, String encoding) throws Exception {
        int BUFFER_SIZE = 4096;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count = -1;
        while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
            outStream.write(data, 0, count);
        data = null;
        return new String(outStream.toByteArray(), encoding);
    }


    public static String getFixedLenString(String str, int len, char paddingChar) {
        if (str == null) {
            return null;
        }
        if (len <= 0) {
            // Do something
        }
        if (str.length() >= len) {
            return str.substring(0, len);
        }

        char[] cs = new char[len];
        str.getChars(0, str.length(), cs, 0);
        Arrays.fill(cs, str.length(), len, paddingChar);
        return new String(cs);
    }


    // tb\u674ea\u661fb
    public static String readUnicodeStr2(String unicodeStr) {
        StringBuilder buf = new StringBuilder();

        for (int i = 0; i < unicodeStr.length(); i++) {
            char char1 = unicodeStr.charAt(i);
            if (char1 == '\\' && isUnicode(unicodeStr, i)) {
                String cStr = unicodeStr.substring(i + 2, i + 6);
                int cInt = Integer.parseInt(cStr, 16);
                buf.append((char) cInt);
                // 跨过当前unicode码，因为还有i++，所以这里i加5，而不是6
                i = i + 5;
            } else {
                buf.append(char1);
            }
        }
        return buf.toString();
    }

    // 判断以index从i开始的串，是不是unicode码
    private static boolean isUnicode(String unicodeStr, int i) {
        int len = unicodeStr.length();
        int remain = len - i;
        // unicode码，反斜杠后还有5个字符 uxxxx
        if (remain < 5)
            return false;

        char flag2 = unicodeStr.charAt(i + 1);
        if (flag2 != 'u')
            return false;
        String nextFour = unicodeStr.substring(i + 2, i + 6);
        return isHexStr(nextFour);
    }

    /**
     * hex str 1-9 a-f A-F
     */
    private static boolean isHexStr(String str) {
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            boolean isHex = (ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'f') || (ch <= 'A' && ch <= 'F');
            if (!isHex)
                return false;
        }
        return true;
    }

    /**
     * 单位缩进字符串。
     */
    private static String SPACE = "   ";

    /**
     * 返回格式化JSON字符串。
     *
     * @param json 未格式化的JSON字符串。
     * @return 格式化的JSON字符串。
     */
    public static String formatJson(String json) {
        StringBuffer result = new StringBuffer();

        int length = json.length();
        int number = 0;
        char key = 0;

        //遍历输入字符串。
        for (int i = 0; i < length; i++) {
            //1、获取当前字符。
            key = json.charAt(i);

            //2、如果当前字符是前方括号、前花括号做如下处理：
            if ((key == '[') || (key == '{')) {
                //（1）如果前面还有字符，并且字符为“：”，打印：换行和缩进字符字符串。
                if ((i - 1 > 0) && (json.charAt(i - 1) == ':')) {
                    result.append('\n');
                    result.append(indent(number));
                }

                //（2）打印：当前字符。
                result.append(key);

                //（3）前方括号、前花括号，的后面必须换行。打印：换行。
                result.append('\n');

                //（4）每出现一次前方括号、前花括号；缩进次数增加一次。打印：新行缩进。
                number++;
                result.append(indent(number));

                //（5）进行下一次循环。
                continue;
            }

            //3、如果当前字符是后方括号、后花括号做如下处理：
            if ((key == ']') || (key == '}')) {
                //（1）后方括号、后花括号，的前面必须换行。打印：换行。
                result.append('\n');

                //（2）每出现一次后方括号、后花括号；缩进次数减少一次。打印：缩进。
                number--;
                result.append(indent(number));

                //（3）打印：当前字符。
                result.append(key);

                //（4）如果当前字符后面还有字符，并且字符不为“，”，打印：换行。
                if (((i + 1) < length) && (json.charAt(i + 1) != ',')) {
                    result.append('\n');
                }

                //（5）继续下一次循环。
                continue;
            }

            //4、如果当前字符是逗号。逗号后面换行，并缩进，不改变缩进次数。
            if ((key == ',')) {
                result.append(key);
                result.append('\n');
                result.append(indent(number));
                continue;
            }

            //5、打印：当前字符。
            result.append(key);
        }

        return result.toString();
    }

    /**
     * 返回指定次数的缩进字符串。每一次缩进三个空格，即SPACE。
     *
     * @param number 缩进次数。
     * @return 指定缩进次数的字符串。
     */
    private static String indent(int number) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < number; i++) {
            result.append(SPACE);
        }
        return result.toString();
    }


    /**
     * @param unicodeStr
     * @return
     */
    public static String decode(String unicodeStr) {
        if (unicodeStr == null) {
            return null;
        }
        StringBuffer retBuf = new StringBuffer();
        int maxLoop = unicodeStr.length();
        for (int i = 0; i < maxLoop; i++) {
            if (unicodeStr.charAt(i) == '\\') {
                if ((i < maxLoop - 5) && ((unicodeStr.charAt(i + 1) == 'u') || (unicodeStr.charAt(i + 1) == 'U')))
                    try {
                        retBuf.append((char) Integer.parseInt(unicodeStr.substring(i + 2, i + 6), 16));
                        i += 5;
                    } catch (NumberFormatException localNumberFormatException) {
                        retBuf.append(unicodeStr.charAt(i));
                    }
                else
                    retBuf.append(unicodeStr.charAt(i));
            } else {
                retBuf.append(unicodeStr.charAt(i));
            }
        }
        return retBuf.toString();
    }

    public static String getEncoding(String str) {
        String encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s = encode;
                return s;
            }
        } catch (Exception exception) {
        }
        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s1 = encode;
                return s1;
            }
        } catch (Exception exception1) {
        }
        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s2 = encode;
                return s2;
            }
        } catch (Exception exception2) {
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s3 = encode;
                return s3;
            }
        } catch (Exception exception3) {
        }
        return "";
    }

    public static int getInt(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        } else {
            try {
                int integer = Integer.parseInt(str);
                return integer;
            } catch (Exception e) {
            }
        }
        return 0;
    }

    public static boolean isDigit(String num) {
        String telRegex = "^[0-9]?+[0-9]*+\\.?+[0-9]*+$";
        return num.matches(telRegex);
    }

    /**
     * 是否正整数
     *
     * @param num
     * @return
     */
    public static boolean isPositiveInteger(String num) {
        String telRegex = "^[0-9]++$";
        return num.matches(telRegex);
    }

    /**
     * 将数字float 如果是.00结尾就返回int，否则返回float
     *
     * @param price
     * @return
     */
    public static String floatToInt(String price) {
        if (!TextUtils.isEmpty(price) && isDigit(price)) {
            float price_first = Float.parseFloat(price);
            int price_second = (int) Float.parseFloat(price);
            if (price_first - price_second == 0) {
                return String.valueOf(price_second);
            }
        }
        return price;
    }

    /**
     * 设置TextView字符串区间颜色
     *
     * @param view  TextView实例
     * @param str   整个字符串
     * @param start 开始字符
     * @param end   结束字符
     * @param color 颜色值
     */
    public static void setTextIntervalColor(@NonNull TextView view, @NonNull String str, char start, char end, int color) {
        if (TextUtils.isEmpty(str) || color <= 0) return;
        try {
            int a = str.indexOf(start);//从字符start的下标开始
            int b = str.indexOf(end) + 1;//到字符end的下标+1结束,因为SpannableStringBuilder的setSpan方法中区间为[ a,b )左闭右开
            if (a <= 0 || b <= 0) return;
            SpannableStringBuilder builder = new SpannableStringBuilder(str);
            builder.setSpan(new ForegroundColorSpan(color), a, b, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            view.setText(builder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取非空的text
     */
    public static String obtainNoNullText(String content) {
        return obtainNoNullText(content, "");
    }

    /**
     * 获取非空的text，null或者empty时候可以设置默认值
     * 对content, defaultContent都进行判空操作
     */
    public static String obtainNoNullText(String content, @NonNull String defaultContent) {
        return !TextUtils.isEmpty(content) ? content
                : (!TextUtils.isEmpty(defaultContent) ? defaultContent : "");
    }
}
