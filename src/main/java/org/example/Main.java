package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        int num = 0;
        List<WiseSaying> list = new ArrayList<>();

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
                System.out.println(++num + "번 명령이 등록되었습니다.");
                list.add(new WiseSaying(wiseSaying, author, num));
            }
            else if(input.equals("목록")){
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                for(int i = list.size(); i > 0; i--){
                    System.out.printf("%d / %s / %s\n", list.get(i - 1).number, list.get(i - 1).author, list.get(i - 1).wiseSaying);
                }
            }
            else {
                StringTokenizer st = new StringTokenizer(input,"=");
                Iterator<WiseSaying> iterator = list.iterator();
                if (st.nextToken().equals("삭제?id")){
                    int id = Integer.parseInt(st.nextToken());
                    boolean f = false;
                    while(iterator.hasNext()){
                        int index = iterator.next().number;

                        if(index == id) {
                            iterator.remove();
                            System.out.println(id+"번 명언이 삭제되었습니다.");
                            f = true;
                        }
                    }
                    if(!f)
                        System.out.println(id+"번 명언은 존재하지 않습니다.");
                }
            }
        }
    }
}

class WiseSaying {
    String wiseSaying;
    String author;
    int number;
    WiseSaying(String _wiseSaying, String _author, int _number){
        this.wiseSaying = _wiseSaying;
        this.author = _author;
        this.number = _number;
    }
}