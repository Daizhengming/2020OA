package com.company;

public class Main {

    // 5 0 0 4 3 3 0 -1 pass
    // 6 5 1 0 5 2 3 3  pass
    // 6 0 0 0 2 0 1 2  pass
    public static void main(String[] args) {

        int[] input = {5, 0, 0, 4, 3, 3, 0};

        int n = input[0];
        int startRow = input[1];
        int startCol = input[2];
        int endRow = input[3];
        int endCol = input[4];
        int bishopRow = input[5];
        int bishopCol = input[6];

        int ret = new Result().moves(n, startRow, startCol, endRow, endCol, bishopRow, bishopCol);

        String out = String.valueOf(ret);

        System.out.print(out);
    }
}
