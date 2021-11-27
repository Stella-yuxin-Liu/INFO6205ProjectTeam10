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
    
    
    public static char unicodeToChar(String str) {
        Integer code = Integer.parseInt(str.substring(2), 16);
        char ch = Character.toChars(code)[0];
        return ch;
    }
    
    public static String charToPinyin(char ch) throws BadHanyuPinyinOutputFormatCombination {
        HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
        outputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        return PinyinHelper.toHanyuPinyinStringArray(ch, outputFormat)[0];
    }
}
