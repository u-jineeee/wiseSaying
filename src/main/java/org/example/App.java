package org.example;

import org.example.wiseSaying.entity.WiseSaying;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class App {
    private final BufferedReader br;
    private String fileName = "/Users/u_jin/Downloads/wiseSayingData.dat";
    List<WiseSaying> list;
    boolean dataChange;
    int num;
    public App(BufferedReader br) {
        this.br = br;
    }

    public void run() throws IOException {
        list = load();
        if(list==null) {
            list = new ArrayList<>();
            num = 0;
        }
        else
            num = list.get(list.size()-1).getId();
        System.out.println("== 명언 앱 ==");

        while (true) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("명령) ");
            String input = br.readLine();

            if (input.equals("종료")) {
                save();
                break;
            }
            else if (input.equals("등록")) {
                System.out.print("명언 : ");
                String content = br.readLine();
                System.out.print("작가 : ");
                String author = br.readLine();
                System.out.println(++num + "번 명령이 등록되었습니다.");
                dataChange = true;
                list.add(new WiseSaying(content, author, num));
            } else if (input.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                for(int i = list.size(); i > 0; i--){
                    System.out.printf("%d / %s / %s\n", list.get(i - 1).getId(), list.get(i - 1).getAuthor(), list.get(i - 1).getContent());
                dataChange = true;
                }
            } else {
                StringTokenizer st = new StringTokenizer(input, "=");
                Iterator<WiseSaying> iterator = list.iterator();
                String input2 = st.nextToken();
                if (input2.equals("삭제?id")) {
                    int id = Integer.parseInt(st.nextToken());
                    boolean f = false;
                    while (iterator.hasNext()) {
                        int index = iterator.next().getId();

                        if (index == id) {
                            iterator.remove();
                            System.out.println(id + "번 명언이 삭제되었습니다.");
                            f = true;
                            dataChange = true;
                        }
                    }
                    if (!f)
                        System.out.println(id + "번 명언은 존재하지 않습니다.");
                } else if (input2.equals("수정?id")) {
                    int id = Integer.parseInt(st.nextToken());
                    boolean f = false;
                    while (iterator.hasNext()) {
                        WiseSaying ws = iterator.next();
                        int index = ws.getId();

                        if (index == id) {
                            System.out.println("명언(기존) : " + ws.getContent());
                            System.out.print("멍언 : ");
                            String input3 = br.readLine();
                            ws.setContent(input3);
                            System.out.println("작가(기존) : " + ws.getAuthor());
                            System.out.print("작가 : ");
                            String input4 = br.readLine();
                            ws.setAuthor(input4);
                            f = true;
                            dataChange = true;
                        }
                    }
                    if (!f)
                        System.out.println(id + "번 명언은 존재하지 않습니다.");
                }
            }
        }
    }
    public void save() throws FileNotFoundException {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream((new FileOutputStream(fileName)));
            oos.writeObject(list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally{
            if(oos!=null)
                try{
                    oos.close();
                }catch (IOException e){
                }
        }
    }
    public ArrayList<WiseSaying> load(){
        ArrayList<WiseSaying> data = null;

        File file = new File(fileName);
        if(!file.exists())
            return null;

        ObjectInput ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            data = (ArrayList<WiseSaying>) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if(ois!=null)
                try{
                    ois.close();
                }catch (IOException e){

                }
        }
        return data;
    }
}

