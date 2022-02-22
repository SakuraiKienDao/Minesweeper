package com.company;
import java.lang.Math;
import java.util.Scanner;


public class Main {
    static final int WIDTH = 16;
    static final int  HEIGHT = 16;
    static final int  BOMB = 40;
    static int[][] map = new int[HEIGHT][WIDTH];
    static boolean[][] check = new boolean[HEIGHT][WIDTH];


    static void ui(){
        System.out.print("\t");
        for(int i=0; i<WIDTH; i++ ){
            System.out.print(i+"\t" );
        }
        System.out.println();


        for( int row =0; row <map.length; row++ ){
            System.out.print(row+"\t");
            for(int col=0; col <map[row].length; col++) {

                if(!check[row][col]){
                    System.out.print("[]\t");

                }else if(map[row][col]==-1){
                    System.out.print("#\t");

                }else System.out.print(map[row][col]+"\t");

            }
            System.out.println();
        }
    }

    static void boom (int bom){
        while(bom>0){
            int x = (int) (Math.random() * 100) % HEIGHT;
            int y = (int) (Math.random() * 100) % WIDTH;

            if (map[x][y] != -1) {
                map[x][y] = -1;
                bom--;
            }
        }
    }
    static void boomCount(){
        for( int row =0; row <map.length; row++ ){
            for(int col=0; col <map[row].length; col++){
                if(map[row][col]==-1) continue;
                int count =0;
                if(col<WIDTH-1 && row>0&& map[row-1][col+1] ==-1) count++;

                if(col<WIDTH-1 && map[row][col+1] ==-1) count++;

                if(col<WIDTH-1 && row<HEIGHT-1 && map[row+1][col+1] ==-1) count++;

                if(row<HEIGHT-1 &&  map[row+1][col] ==-1) count++;

                if(row<HEIGHT-1 && col>0 && map[row+1][col-1] ==-1) count++;

                if(col>0 && map[row][col-1] ==-1) count++;

                if(col>0 && row>0 && map[row-1][col-1] ==-1) count++;

                if(row>0 &&  map[row-1][col] ==-1) count++;

                map[row][col]=count;
            }
        }
    }

    static void bolCheck(){
        for (boolean[] x : check)
        {
            for (boolean y : x)
            {
                System.out.print(y + " ");
            }
            System.out.println();
        }
    }

    static void mapCheck(){
        for (int[] x : map)
        {
            for (int y : x)
            {
                System.out.print(y + " ");
            }
            System.out.println();
        }
    }
    static boolean  clickPoint(int m, int n) {
        if (!check[m][n]) {
            check[m][n] = true;

        }

            if (map[m][n] ==  0 ) {
                if(m<HEIGHT-1 && !check[m+1][n] ) clickPoint(m + 1, n);
                if(m>0 && !check[m-1][n]) clickPoint(m - 1, n);
                if(n<WIDTH-1 && !check[m][n+1] ) clickPoint(m, n + 1);
                if(n>0 && !check[m][n-1]) clickPoint(m, n - 1);
            }
        return map[m][n]==-1;
    }



    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        boolean fail;
        boom(BOMB);
        boomCount();
        ui();


        do {
            System.out.print("col :");
            int col = myObj.nextInt();
            System.out.print("row :");
            int row = myObj.nextInt();

            fail =clickPoint(col, row);
            ui();

        }while(!fail);
//        mapCheck();
//        System.out.println("----------------------------");
//        bolCheck();

    }
}
