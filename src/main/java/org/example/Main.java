package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        int num = 1;
        System.out.println("== 명언 앱 ==");
        while(true){
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("명령) ");
            String input = br.readLine();
            if(input.equals("종료")) break;
            else if(input.equals("등록")) {
                System.out.print("명언 : ");
                String wiseSaying = br.readLine();
                System.out.print("작가 : ");
                String author = br.readLine();
                System.out.println(num + "번 명령이 등록되었습니다.");
                num++;
            }
        }
    }
}

class WiseSaying {
    String wiseSaying;
    String author;
    int number;
    static int count;
    WiseSaying(String _wiseSaying, String _author){
        this.wiseSaying = _wiseSaying;
        this.author = _author;
        this.number = ++count;
    }
}