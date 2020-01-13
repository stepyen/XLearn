package com.stepyen.xlearn.java;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * date：2019/7/31
 * author：stepyen
 * description：
 */
public class JavaTest {


    public static void main(String[] args) {
        int a = 0x00000001;
        int b = 0x00000010;
        int c = 0x00000100;

        int ab = 0x00000011;
        int ac = 0x00000101;
        int bc = 0x00000110;
        int abc = 0x00000111;

        System.out.println((a | b) == ab);
        System.out.println((a | c) == ac);
        System.out.println((c | b) == bc);
        System.out.println((a | b | c) == abc);


    }


    public void onEvent(String val, int type) {

    }
    public void onEvent(String val) {

    }
}
