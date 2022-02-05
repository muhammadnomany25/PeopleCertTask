package com.alphaapps.peoplecerttask.utils;

import java.text.DecimalFormat;

/**
 * @usage: Helper class for Sizes features
 * @Author: Muhammad Noamany
 * @Email: muhammadnoamany@gmail.com
 */
public class SizeUtils {
    private static final String LOG_TAG = SizeUtils.class.getSimpleName();
    private static final String[] SIZE_UNITS = new String[]{"B", "KB", "MB", "GB", "TB"};   // File size units
    private static final String SIZE_FORMAT = "#,##0.#";   // th decimal format of file sizes
    private static final String ZERO_FILE_SIZE = "0";   //  zero file size value

    /**
     * Return Readable size of file
     */
    public static String formatSize(long size) {
        if (size <= 0)
            return ZERO_FILE_SIZE;

        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));

        return new DecimalFormat(SIZE_FORMAT).format(size / Math.pow(1024, digitGroups)) + " " + SIZE_UNITS[digitGroups];
    }
}
