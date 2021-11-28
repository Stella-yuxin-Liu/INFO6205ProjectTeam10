/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205.sort.counting;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 *
 * @author Air
 */
public class PinyinConverter {
    
    //unicode to pinyin without tone eg: \u548c to he
    public static String unicodeToPinyinNOTone(String str) throws BadHanyuPinyinOutputFormatCombination {
        char ch = unicodeToChar(str);
        return charToPinyinNOTone(ch);
    }
    
    //unicode to pinyin with tone as a number eg: \u548c to he2
    public static String unicodeToPinyinWITone(String str) throws BadHanyuPinyinOutputFormatCombination {
        char ch = unicodeToChar(str);
        return charToPinyinWITone(ch);
    }
    
    //unicode to char
    public static char unicodeToChar(String str) {
        Integer code = Integer.parseInt(str.substring(2), 16);
        char ch = Character.toChars(code)[0];
        return ch;
    }
    
    //char to pinyin without tone
    public static String charToPinyinNOTone(char ch) throws BadHanyuPinyinOutputFormatCombination {
        HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
        outputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        return PinyinHelper.toHanyuPinyinStringArray(ch, outputFormat)[0];
    }
    
    //char to pinyin with tone
    public static String charToPinyinWITone(char ch) throws BadHanyuPinyinOutputFormatCombination {
        HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
        outputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        outputFormat.setToneType(HanyuPinyinToneType.WITH_TONE_NUMBER);
        return PinyinHelper.toHanyuPinyinStringArray(ch, outputFormat)[0];
    }
    
    //sample test \u548c to 和 to he/he2
    public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination {
        String test = "\\u548c";
        System.out.println(test);
        char ch = unicodeToChar(test);
        System.out.println(ch);
        String py1 = charToPinyinNOTone(ch);
        System.out.println(py1);
        String py2 = charToPinyinWITone(ch);
        System.out.println(py2);
        String py3 = unicodeToPinyinNOTone(test);
        System.out.println(py3);
        String py4 = unicodeToPinyinWITone(test);
        System.out.println(py4);
    }
}
