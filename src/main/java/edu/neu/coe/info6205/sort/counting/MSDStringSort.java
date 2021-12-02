package edu.neu.coe.info6205.sort.counting;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.*;
import edu.neu.coe.info6205.sort.elementary.InsertionSortMSD;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Class to implement Most significant digit string sort (a radix sort).
 */
public class MSDStringSort {

    /**
     * Sort an array of Strings using MSDStringSort.
     *
     * @param a the array to be sorted.
     */
    public static void sort(String[] a) {
        int n = a.length;
        aux = new String[n];
        sort(a, 0, n, 0);
    }

    /**
     * Sort from a[lo] to a[hi] (exclusive), ignoring the first d characters of each String.
     * This method is recursive.
     *
     * @param a the array to be sorted.
     * @param lo the low index.
     * @param hi the high index (one above the highest actually processed).
     * @param d the number of characters in each String to be skipped.
     */

    private static void sort(List<String> a, int lo, int hi, int d) {
        // character.
        if (hi <= lo + radix) {
            Insertion.sort(a, lo, hi, d);
            return;
        }
        int[] count = new int[radix + 2]; // Compute frequency counts.

        for (int i = lo; i <= hi; i++) {
            count[charAt(a.get(i), d) + 2]++;
        }
        for (int r = 0; r < radix + 1; r++) {
            count[r + 1] += count[r];
        } // Transform counts to indices.
        for (int i = lo; i <= hi; i++) {
            aux[count[charAt(a.get(i), d) + 1]++] = a.get(i);
        } // Distribute.
        for (int i = lo; i <= hi; i++) {
            a.set(i, aux[i - lo]);
        } // Copy back.
        // Recursively sort for each character value.
        for (int r = 0; r < radix; r++) {
            sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1);
        }
    }

    private static int charAt(String s, int d) {
        if (d < s.length()) return s.charAt(d);
        else return -1;
    }



    }
    private static final int radix = 256;
    private static final int cutoff = 15;
    private static String[] aux;       // auxiliary array for distribution
}

