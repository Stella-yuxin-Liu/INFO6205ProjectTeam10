package edu.neu.coe.info6205.sort.TestFinal;

import edu.neu.coe.info6205.sort.linearithmic.TimSort;
import main.QuickSortDualPivot;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TimsortTest {
    TimSort tSort = new TimSort<>();

    public TimsortTest() throws IOException {
    }

    @Test
    public void sortChinese1() throws IOException, BadHanyuPinyinOutputFormatCombination {
        String[] strs = getLines("src/main/resources/shuffledChinese.txt", 10);
        String[] expected = { "曹玉德", "樊辉辉", "高民政", "洪文胜", "刘持平", "舒冬梅", "苏会敏", "许凤山", "杨腊香", "袁继鹏" };
        String[] str3 = convertToPinyin(strs);
        String[] str_tmp_ts = str3.clone();
        tSort.sort(str_tmp_ts, 0, str_tmp_ts.length);

        String[] st_ts = matchIndex(str3, str_tmp_ts, strs);

        assertArrayEquals(st_ts, expected);
    }
    @Test
    public void sortChinese2() throws IOException, BadHanyuPinyinOutputFormatCombination {
        String[] strs ={ "张三", "杨腊香", "袁继鹏","王广风","曹玉德", "樊辉辉", "高民政", "顾芳芳", "洪文胜", "黄锡鸿", "刘持平", "罗庆富", "舒冬梅", "宋雪光", "苏会敏",
                "许凤山",  "张安", "张四" };
        String[] expected = { "曹玉德", "樊辉辉", "高民政", "顾芳芳", "洪文胜", "黄锡鸿", "刘持平", "罗庆富", "舒冬梅", "宋雪光", "苏会敏",
                "王广风", "许凤山", "杨腊香", "袁继鹏", "张安", "张三", "张四" };
        String[] str3 = convertToPinyin(strs);
        String[] str_tmp_ts = str3.clone();
        tSort.sort(str_tmp_ts, 0, str_tmp_ts.length);

        String[] st_ts = matchIndex(str3, str_tmp_ts, strs);

        assertArrayEquals(st_ts, expected);

    }
    @Test
    public void sortChinese3() throws IOException, BadHanyuPinyinOutputFormatCombination {
        String[] strs ={ "李大营", "魏晓腾", "卢若冰", "胡会鹏", "颜建新", "姜元元"};
        String[] expected = {"胡会鹏", "姜元元", "李大营", "卢若冰", "魏晓腾", "颜建新"};

        String[] str3 = convertToPinyin(strs);
        String[] str_tmp_ts = str3.clone();
        tSort.sort(str_tmp_ts, 0, str_tmp_ts.length);

        String[] st_ts = matchIndex(str3, str_tmp_ts, strs);

        assertArrayEquals(st_ts, expected);
    }


    public static String[] getLines(String FileName, int lens) throws IOException {
        long lines = 0;
        String[] str = new String[lens];
        int i = 0;
        try {
            File file = new File(FileName);
            if (file.exists()) {
                FileReader fileReader = new FileReader((file));

                BufferedReader in = new BufferedReader(fileReader);
                while (i < lens) {

                    str[i] = new String(in.readLine().toString());
                    i++;
//					System.out.println("this line：" + str[i]);
                }
                fileReader.close();
//				lineNumberReader.close();
                return str;
            }

            {
                System.out.println("Path Not Found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
    public static String[] convertToPinyin(String[] Org) throws BadHanyuPinyinOutputFormatCombination {
        String[] Str = Org.clone();

        int i = 0;
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (String s : Str) {

            char[] tmp = s.toCharArray();
            int j = 0;
            Str[i] = "";
            for (char t : tmp) {
                Str[i] = Str[i] + PinyinHelper.toHanyuPinyinStringArray(t, defaultFormat)[0];
            }
            i++;
        }
        return Str;
    }
    public static String[] matchIndex(String[] str1, String[] str2, String[] init) {
        int len = str1.length;
        String[] retString = new String[len];
        int iterat = 0;
        for (String s1 : str2) {

            int i = 0;
            for (String s2 : str1) {
                if (s1.equals(s2)) {
                    retString[iterat] = init[i];
                    break;
                }
                i += 1;
            }
            iterat += 1;
        }
        return retString;
    }
}
