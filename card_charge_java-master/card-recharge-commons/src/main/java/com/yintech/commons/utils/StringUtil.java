package com.yintech.commons.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author ming.li1@yintech.cn
 * @version 1.0-SNAPSHOT
 * @description 字符串工具
 * @Company: yintech
 * @date 2021/6/21 4:35 下午
 */
public class StringUtil {
    /**
     * 判断2个字符串是否相等
     * <pre>
     * StringUtil.equals(null, null)   = true
     * StringUtil.equals(null, "abc")  = false
     * StringUtil.equals("abc", null)  = false
     * StringUtil.equals("abc", "abc") = true
     * StringUtil.equals("abc", "ABC") = false
     * </pre>
     *
     * @param str1 字串串1
     * @param str2 字符串2
     * @return
     */
    public static boolean equals(String str1, String str2) {
        return StringUtils.equals(str1, str2);
    }

    /**
     * 判断2个字符串是否相等忽略大小写
     * <pre>
     * StringUtil.equals(null, null)   = true
     * StringUtil.equals(null, "abc")  = false
     * StringUtil.equals("abc", null)  = false
     * StringUtil.equals("abc", "abc") = true
     * StringUtil.equals("abc", "ABC") = false
     * </pre>
     *
     * @param str1 字串串1
     * @param str2 字符串2
     * @return
     */
    public static boolean equalsIgnoreCase(String str1, String str2) {
        return StringUtils.equalsIgnoreCase(str1, str2);
    }

    /**
     * 判断字符串是否为空,对于字符串里面的空格不会忽略
     * <pre>
     * StringUtil.isEmpty(null)      = true
     * StringUtil.isEmpty("")        = true
     * StringUtil.isEmpty(" ")       = false
     * StringUtil.isEmpty("字符串")     = false
     * </pre>
     * 还有一个会忽略空格的isBlank方法
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return StringUtils.isEmpty(str);
    }

    /**
     * 判断字符串是否空,对于字符串里面的空格会忽略
     * <pre>
     * StringUtil.isBlank(null)      = true
     * StringUtil.isBlank("")        = true
     * StringUtil.isBlank(" ")       = true
     * StringUtil.isBlank("字符串")     = false
     * </pre>
     * 还有一个不会忽略空格的isEmpty方法
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        return StringUtils.isBlank(str);
    }

    /**
     * 判断字符串是否非空
     * <pre>
     * StringUtil.isNotEmpty(null)      = false
     * StringUtil.isNotEmpty("")        = false
     * StringUtil.isNotEmpty(" ")       = true
     * StringUtil.isNotEmpty("字符串")     = true
     * </pre>
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return StringUtils.isNotEmpty(str);
    }

    /**
     * 判断字符串是否非空
     * <pre>
     * StringUtil.isNotEmpty(null)      = false
     * StringUtil.isNotEmpty("")        = false
     * StringUtil.isNotEmpty(" ")       = true
     * StringUtil.isNotEmpty("字符串")     = true
     * </pre>
     *
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str) {
        return StringUtils.isNotBlank(str);
    }

    /**
     * 去掉空格和回车
     * <pre>
     * StringUtil.trim(null)          = null
     * StringUtil.trim("")            = ""
     * StringUtil.trim("     ")       = ""
     * StringUtil.trim("abc")         = "abc"
     * StringUtil.trim("    abc    ") = "abc"
     * </pre>
     *
     * @param str
     * @return
     */
    public static String trim(String str) {
        return StringUtils.trim(str);
    }

    /**
     * 将字母都转换成大写
     * <pre>
     * StringUtil.upperCase(null)  = null
     * StringUtil.upperCase("")    = ""
     * StringUtil.upperCase("aBc") = "ABC"
     * </pre>
     *
     * @param str
     * @return
     */
    public static String upperCase(final String str) {
        return StringUtils.upperCase(str);
    }

    /**
     * 将字母转都换成小写
     *
     * <pre>
     * StringUtil.lowerCase(null)  = null
     * StringUtil.lowerCase("")    = ""
     * StringUtil.lowerCase("aBc") = "abc"
     * </pre>
     *
     * @param str
     * @return
     */
    public static String lowerCase(final String str) {
        return StringUtils.lowerCase(str);
    }

    /**
     * 判断字符是不是都是字母组成 *
     * <pre>
     * StringUtil.isAlpha(null)   = false
     * StringUtil.isAlpha("")     = false
     * StringUtil.isAlpha("  ")   = false
     * StringUtil.isAlpha("abc")  = true
     * StringUtil.isAlpha("ab2c") = false
     * StringUtil.isAlpha("ab-c") = false
     * </pre>
     *
     * @param str
     * @return
     */
    public static boolean isAlpha(final String str) {
        return StringUtils.isAlpha(str);
    }

    /**
     * 判断字符串是否都是数字,不能包含小数点
     * <pre>
     * StringUtil.isNumeric(null)   = false
     * StringUtil.isNumeric("")     = false
     * StringUtil.isNumeric("  ")   = false
     * StringUtil.isNumeric("123")  = true
     * StringUtil.isNumeric("\u0967\u0968\u0969")  = true
     * StringUtil.isNumeric("12 3") = false
     * StringUtil.isNumeric("ab2c") = false
     * StringUtil.isNumeric("12-3") = false
     * StringUtil.isNumeric("12.3") = false
     * StringUtil.isNumeric("-123") = false
     * StringUtil.isNumeric("+123") = false
     * </pre>
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(final String str) {

        return StringUtils.isNumeric(str);
    }

    /**
     * 查看该字符串是否是以指定字符开始
     *
     * <pre>
     * StringUtil.startsWith(null, null)      = true
     * StringUtil.startsWith(null, "abc")     = false
     * StringUtil.startsWith("abcdef", null)  = false
     * StringUtil.startsWith("abcdef", "abc") = true
     * StringUtil.startsWith("ABCDEF", "abc") = false
     * </pre>
     *
     * @param str1
     * @param prefix
     * @return
     */
    public static boolean startsWith(final String str1, final String prefix) {
        return StringUtils.startsWith(str1, prefix);
    }

    /**
     * 判断字符串是否以指定字符结尾
     * <pre>
     * StringUtil.endsWith(null, null)      = true
     * StringUtil.endsWith(null, "def")     = false
     * StringUtil.endsWith("abcdef", null)  = false
     * StringUtil.endsWith("abcdef", "def") = true
     * StringUtil.endsWith("ABCDEF", "def") = false
     * StringUtil.endsWith("ABCDEF", "cde") = false
     * StringUtil.endsWith("ABCDEF", "")    = true
     * </pre>
     *
     * @param str
     * @param suffix
     * @return
     */
    public static boolean endsWith(final String str, final String suffix) {
        return StringUtils.endsWith(str, suffix);
    }

    /**
     * 判断字符串内是否包含指定字符串
     * <pre>
     * StringUtil.contains(null, *)     = false
     * StringUtil.contains(*, null)     = false
     * StringUtil.contains("", "")      = true
     * StringUtil.contains("abc", "")   = true
     * StringUtil.contains("abc", "a")  = true
     * StringUtil.contains("abc", "z")  = false
     * </pre>
     *
     * @param str
     * @param searchStr
     * @return
     */
    public static boolean contains(final String str, final String searchStr) {
        return StringUtils.contains(str, searchStr);
    }

    /**
     * 将字符串通过指定字符串切割成一个数组
     * <pre>
     * StringUtil.split(null, *)         = null
     * StringUtil.split("", *)           = []
     * StringUtil.split("abc def", null) = ["abc", "def"]
     * StringUtil.split("abc def", " ")  = ["abc", "def"]
     * StringUtil.split("abc  def", " ") = ["abc", "def"]
     * StringUtil.split("ab:cd:ef", ":") = ["ab", "cd", "ef"]
     * </pre>
     *
     * @param str
     * @param splitStr
     * @return
     */
    public static String[] split(final String str, final String splitStr) {
        return StringUtils.split(str, splitStr);
    }

    /**
     * 替换字符串
     * <pre>
     * StringUtil.replace(null, *, *)        = null
     * StringUtil.replace("", *, *)          = ""
     * StringUtil.replace("any", null, *)    = "any"
     * StringUtil.replace("any", *, null)    = "any"
     * StringUtil.replace("any", "", *)      = "any"
     * StringUtil.replace("aba", "a", null)  = "aba"
     * StringUtil.replace("aba", "a", "")    = "b"
     * StringUtil.replace("aba", "a", "z")   = "zbz"
     * </pre>
     *
     * @param text
     * @param searchString
     * @param replacement
     * @return
     */
    public static String replace(final String text, final String searchString, final String replacement) {
        return StringUtils.replace(text, searchString, replacement);
    }

    public static String join(final CharSequence delimiter, final Iterable<? extends CharSequence> elements) {
        if (elements == null) {
            return null;
        }
        return String.join(delimiter, elements);
    }

    /**
     * 字符串首字母小写
     *
     * @param s
     * @return
     */
    public static String toLowerCaseFirstOne(String s) {
        if (s == null || s.length() == 0) return s;
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    /**
     * 字符串首字母大写
     *
     * @param s
     * @return
     */
    public static String toUpperCaseFirstOne(String s) {
        if (s == null || s.length() == 0) return s;
        if (Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }

}
