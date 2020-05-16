package com.credit.calculator.controllers;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Count {
        @NotNull
        @Min(30000)
        @Max(1000000)
        public static double sumCr;
        @NotNull
        @Min(1)
        @Max(12)
        private static int timeCr;
        @NotNull
        private static double rateCr;
        public static double monthPay;
        public static int[][] newArr;

        public double getSumCr() { return sumCr; }
        public void setSumCr(double sumCr) { this.sumCr = sumCr; }
        public int getTimeCr() { return timeCr; }
        public void setTimeCr(int timeCr) { this.timeCr = timeCr; }
        public double getRateCr() { return rateCr; }
        public void setRateCr(double rateCr) { this.rateCr = rateCr; }
        public double getMonthPay() { return monthPay;}

        public static void countRes() {
            double[][] arrCred = new double[timeCr + 1][5];
            countMonthPay(sumCr, timeCr, rateCr, arrCred);
            printChart(arrCred);
        }
        
        public static void countMonthPay(double sumCr, int timeCr, double rateCr, double[][] arrCred) {
            double monthRate = rateCr / 12 / 100;
            double factor = (monthRate * Math.pow(1 + monthRate, timeCr)) / (Math.pow(1+monthRate, timeCr) - 1); //factor of annuity
            monthPay = factor * sumCr;
            System.out.println("Ваш ежемесячный платеж составит: " + monthPay);
            for (int i = 1; i < arrCred.length; i++) {
                arrCred[i][0] = i;
                arrCred[i][1] = monthPay;
            }
            arrCred[0][4] = sumCr;
            fillChart(arrCred, monthRate);
        }

        public static void fillChart(double[][] arr, double monthRate) {
            double temp = arr[0][4];
            for (int i = 1; i < arr.length; i++) {
                double payment = arr[i][1];
                double perc = monthRate * temp;
                arr[i][2] = perc;
                arr[i][3] = payment - perc;
                arr[i][4] = temp - arr[i][3];
                temp = arr[i][4];
            }
        }

        public static void printChart(double[][] arr) {
                System.out.println("График платежей:");
                System.out.println("номер платежа - сумма платежа - проценты - тело кредита - остаток");
                //for simplify use array of ints. doubles need for accuracy of count
                newArr = new int[arr.length][arr[0].length];
                for (int i = 0; i < arr.length; i++) {
                        for (int j = 0; j < arr[i].length; j++) {
                                newArr[i][j] = (int) arr[i][j];
                                System.out.print(newArr[i][j] + " ");
                        }
                        System.out.println();
                }
		/*
		//if we want to see accuracy output with numbers after comma
		for (double[] doubles : arr) {
			for (double aDouble : doubles) {
				System.out.print(aDouble + " ");
			}
			System.out.println();
		}*/
        }

}
