package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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

public class Mains {
	private static Config config;

	public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination, IOException {
		int times = 5;
		int numnbers = 1000000;


		//only double when we need input larger than 1M


		String[] type = { "MSDStringSort", "QuickSortDualPivot", "LSDStringSort", "TimSort", "PureHuskySorts" };


		Timer[] timer = new Timer[5];
		timer[0] = new Timer(times);
		timer[1] = new Timer(times);

		timer[2] = new Timer(times);
		timer[3] = new Timer(times);
		timer[4] = new Timer(times);


		for (int i = 0; i < times; i++) {

			timer[0].startCounting();
			String[] strMSD = getLines(
					"\\INFO6205_Final\\INFO6205ProjectTeam10\\src\\main\\resources\\shuffledChinese.txt",
					999998);
			//add to 2M
			//strMSD = doubleArray(strMSD);
			//add to 4M
			//strMSD = doubleArray(strMSD);
			MSD msd = new MSD();
			String[] str_tmp_msd = convertToPinyin(strMSD);
			msd.MSD_sort(str_tmp_msd);
			timer[0].endCounting();
//
			timer[1].startCounting();
			String[] strsQS = getLines(
					"\\INFO6205_Final\\INFO6205ProjectTeam10\\src\\main\\resources\\shuffledChinese.txt",
					999998);
			//strsQS = doubleArray(strsQS);
			//strsQS = doubleArray(strsQS);
			QuickSortDualPivot qsdp = new QuickSortDualPivot();
			String[] str_tmp_qsdp = convertToPinyin(strsQS);
			qsdp.sort(str_tmp_qsdp, 0, strsQS.length - 1);
			timer[1].endCounting();
//
			timer[2].startCounting();
			String[] strsLSD = getLines(
					"\\INFO6205_Final\\INFO6205ProjectTeam10\\src\\main\\resources\\shuffledChinese.txt",
					999998);
			//strsLSD = doubleArray(strsLSD);
			//strsLSD = doubleArray(strsLSD);
			LSDStringSort ls = new LSDStringSort();
			String[] str_tmp_lsd = convertToPinyin(strsLSD);
			ls.sort(str_tmp_lsd);
			timer[2].endCounting();
//
			timer[3].startCounting();
			String[] strsTS = getLines(
					"\\INFO6205_Final\\INFO6205ProjectTeam10\\src\\main\\resources\\shuffledChinese.txt",
					999998);
			//strsTS = doubleArray(strsTS);
			//strsTS = doubleArray(strsTS);
			TimSort tSort = new TimSort<>();
			String[] str_tmp_ts = convertToPinyin(strsTS);
			tSort.sort(str_tmp_ts, 0, str_tmp_ts.length);
			timer[3].endCounting();
//
			timer[4].startCounting();
			String[] strsPH = getLines(
					"\\INFO6205_Final\\INFO6205ProjectTeam10\\src\\main\\resources\\shuffledChinese.txt",
					999998);
			//strsPH = doubleArray(strsPH);
			//strsPH = doubleArray(strsPH);
			PureHuskySort ph = new PureHuskySort<>(HuskyCoderFactory.asciiCoder, false, false);
			String[] str_tmp_phs = convertToPinyin(strsPH);
			ph.sort(str_tmp_phs);
			timer[4].endCounting();
			//print output
			/*
			if (i == times - 1) {
				String[] st_phs = matchIndex(str3, str_tmp_phs, strs);
				FileWriter fw = new FileWriter("ary.txt");
				for (int is = 0; is < st_phs.length; is++) {
					fw.write(st_phs[is] + ",");
				}
				fw.close();
			}*/
		}

		int i = 0;
		System.out.println("Iter Times:" + times);
		System.out.println("Data Amounts:" + numnbers);
		for (Timer t : timer) {
			System.out.println(type[i] + ":");
			System.out.println(t.getFinalDuring());
			i++;
		}


	}

	public static String[] doubleArray(String[] old) {
		String[] doubled = new String[old.length * 2];
		for (int i = 0; i < old.length; i++) {
			doubled[i] = old[i];
			doubled[old.length + i] = old[i];
		}
		return doubled;
	}

	public static String[] getLines(String FileName, int lens) throws IOException {
		long lines = 0;
		String[] str = new String[lens];
		int i = 0;
		try {
			File file = new File(FileName);
			if (file.exists()) {
				FileReader fileReader = new FileReader((file));
//				LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
//				lineNumberReader.skip(Long.MAX_VALUE);
//				lines = lineNumberReader.getLineNumber() + 1;
//				if (lens > lines) {
//					lens = (int) (lines);
//				}
				BufferedReader in = new BufferedReader(fileReader);
				while (i < lens) {

					str[i] = new String(in.readLine().toString());
					i++;
//					System.out.println("this line???" + str[i]);
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
//		for (String s : Str)
//			System.out.println("INIT:" + s);
		int i = 0;
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (String s : Str) {
//			if (s == null) {
//				
//				continue;
//			}
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
