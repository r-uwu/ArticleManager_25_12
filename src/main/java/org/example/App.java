package org.example;

import java.util.Scanner;

public class App {

    public void run() {

        Scanner sc = new Scanner(System.in);
        System.out.println("==프로그램 시작==");
        MemberController memberController = new MemberController(sc);
        ArticleController articleController = new ArticleController(sc);

        while (true) {
            System.out.print("명령어 ) ");
            String cmd = sc.nextLine().trim();

            if (cmd.equals("exit")) {
                break;
            } else if (cmd.isEmpty()) {
                System.out.println("명령어 입력하세요");
                continue;
            }

            if (cmd.equals("article write")||cmd.equals("작성")) {
                articleController.write();
            }
            else if (cmd.equals("article list")||cmd.equals("목록")) {
                articleController.list();
            }
            else if (cmd.startsWith("article list "))
            {
                articleController.searchTitle(cmd);
            }
            else if (cmd.startsWith("article detail")||cmd.equals("보기")) {
                articleController.details(cmd);
            }
            else if (cmd.startsWith("article delete")||cmd.equals("삭제")) {
                articleController.delete(cmd);
            }



            else if(cmd.equals("join")){
                memberController.join();
            }
            else if(cmd.equals("log in")){
                memberController.logIn();
            }



            else if (cmd.startsWith("article modify")||cmd.equals("수정")) {
                articleController.modify(cmd);
            }
            else{
                //sc.nextLine();
                System.out.println("사용할 수 없는 명령어입니다");
            }
        }

        System.out.println("==프로그램 끝==");
        sc.close();
    }
}

