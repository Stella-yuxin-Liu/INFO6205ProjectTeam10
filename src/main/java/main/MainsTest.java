package main;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import edu.neu.coe.huskySort.sort.huskySort.PureHuskySort;
import edu.neu.coe.huskySort.sort.huskySortUtils.HuskyCoderFactory;
import edu.neu.coe.info6205.sort.counting.LSDStringSort;
import edu.neu.coe.info6205.sort.counting.MSDStringSort;
import edu.neu.coe.info6205.sort.linearithmic.TimSort;
import edu.neu.coe.info6205.util.Config;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class MainsTest {
	private static Config config;

	public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination, IOException {
		String[] strs = getLines("\\INFO6205_Final\\INFO6205ProjectTeam10\\src\\main\\resources\\shuffledChinese.txt", 10);
		String[] expected = { "曹玉德", "樊辉辉", "高民政", "洪文胜", "刘持平", "舒冬梅", "苏会敏", "许凤山", "杨腊香", "袁继鹏" };

		String[] expected2 = new String[] { "曹玉德", "樊辉辉", "高民政", "顾芳芳", "洪文胜", "黄锡鸿", "刘持平", "罗庆富", "舒冬梅", "宋雪光", "苏会敏",
				"王广风", "许凤山", "杨腊香", "袁继鹏", "张安", "张三", "张四" };

		System.out.println();
		int len = 16;
		Timer[] timer = new Timer[5];
		timer[0] = new Timer(1);
		timer[1] = new Timer(1);
		timer[2] = new Timer(1);
		timer[3] = new Timer(1);
		timer[4] = new Timer(1);

		MSDStringSort ms = new MSDStringSort();
		QuickSortDualPivot qsdp = new QuickSortDualPivot();
		LSDStringSort ls = new LSDStringSort();
		TimSort tSort = new TimSort<>();
		PureHuskySort ph = new PureHuskySort<>(HuskyCoderFactory.asciiCoder, false, false);



		String[] str3 = convertToPinyin(strs);
		for (String s : str3)
			System.out.println("INIT:" + s);
		String[] str_tmp_msd = str3.clone();
		String[] str_tmp_qsdp = str3.clone();
		String[] str_tmp_lsd = str3.clone();
		String[] str_tmp_ts = str3.clone();
		String[] str_tmp_phs = str3.clone();


		timer[0].startCounting();
		ms.sort(str_tmp_msd);
		timer[0].endCounting();
//
		timer[1].startCounting();
		qsdp.sort(str_tmp_qsdp, 0, strs.length - 1);
		timer[1].endCounting();
//
		timer[2].startCounting();
		ls.sort(str_tmp_lsd);
		timer[2].endCounting();
//
		timer[3].startCounting();
		tSort.sort(str_tmp_ts, 0, str_tmp_ts.length);
		timer[3].endCounting();
//
		timer[4].startCounting();
		ph.sort(str_tmp_phs);
		timer[4].endCounting();
		timer[0].getTime();

		for (Timer t : timer) {
			System.out.println(t.getTime());
		}

//		ph.huskySort.sort(str_tmp);
//		ls.huskySort.sort(str_tmp);
//		ms.huskySort.sort(str_tmp);
//		qsdp.huskySort.sort(str_tmp, 0, strs.length - 1);
//		tSort.huskySort.sort(str_tmp, 0, str_tmp.length);

//		ms.huskySort.sort(str_tmp);
//		System.out.println("MSDStringSort");
//		for (String s : strs)
//			System.out.println(s);
//
//		System.out.println("----------------\n");

		String[] st_msd = matchIndex(str3, str_tmp_msd, strs);
		String[] st_qsdp = matchIndex(str3, str_tmp_qsdp, strs);
		String[] st_lsd = matchIndex(str3, str_tmp_lsd, strs);
		String[] st_ts = matchIndex(str3, str_tmp_ts, strs);
		String[] st_phs = matchIndex(str3, str_tmp_phs, strs);

		assertArrayEquals(expected, st_qsdp);
		assertArrayEquals(expected, st_msd);
		assertArrayEquals(st_lsd, expected);
		assertArrayEquals(st_ts, expected);
		assertArrayEquals(st_phs, expected);

		assert str_tmp_ts == str3;
		for (String s : st_msd)
			System.out.println(s);

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

}
