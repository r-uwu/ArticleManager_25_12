package org.example;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;

public class Main {

    Scanner sc = new Scanner(System.in);
    int lastArticleId = 0;
    List<Article> articles = new ArrayList<>();
    List<Member> members = new ArrayList<>();
    boolean isLoggedIn = false;
    String userId;

    public static void main(String[] args) {
        Main main = new Main();

        main.makeTestData();
        main.makeTestMember();
        main.run();
    }

    private void makeTestData() {
        System.out.println("==테스트 데이터 생성==");

        articles.add(new Article(1, dateTime(), "test1", "asdf"));
        articles.add(new Article(2, dateTime(), "test2", "ㅁㄴㅇㄹ"));
        articles.add(new Article(3, dateTime(), "test3", "3333"));
        lastArticleId+=3;
    }

    private void makeTestMember() {
        System.out.println("==테스트 멤버 생성==");
        members.add(new Member("member1", "asdf"));
        members.add(new Member("member2", "asdf"));
        members.add(new Member("member3", "asdf"));
    }

    public void run() {

        System.out.println("==프로그램 시작==");

        while (true) {
            System.out.print("명령어 ) ");
            String cmd = sc.nextLine().trim();

            if (cmd.equals("exit")) {
                break;
            } else if (cmd.length() == 0) {
                System.out.println("명령어 입력하세요");
                continue;
            }

            if (cmd.equals("article write")||cmd.equals("작성")) {
                write();
            }
            else if (cmd.equals("article list")||cmd.equals("목록")) {
                list();
            }
            else if (cmd.startsWith("article list "))
            {
                searchTitle(cmd);
            }
            else if (cmd.startsWith("article detail")||cmd.equals("보기")) {
                details(cmd);
            }
            else if (cmd.startsWith("article delete")||cmd.equals("삭제")) {
                delete(cmd);
            }
            else if(cmd.equals("join")){
                join();
            }


            if(cmd.equals("log in")){
                logIn();
            }





            if (cmd.startsWith("article modify")||cmd.equals("수정")) {
                modify(cmd);
            }
            else{
                sc.nextLine();
                System.out.println("사용할 수 없는 명령어입니다");
            }
        }

        System.out.println("==프로그램 끝==");
        sc.close();
    }


    String dateTime() {
        LocalDateTime now = LocalDateTime.now();
        String date = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
        return date;
    }

    void write() {
        System.out.println("==게시글 작성==");
        int id = lastArticleId + 1;
        System.out.print("제목 : ");
        String title = sc.nextLine().trim();
        System.out.print("내용 : ");
        String body = sc.nextLine().trim();

        Article article = new Article(id, dateTime(), title, body);
        articles.add(article);

        System.out.println(id + "번 글이 작성되었습니다.");
        lastArticleId++;
    }

    void list() {
        System.out.println("==게시글 목록==");
        if (articles.size() == 0) {
            System.out.println("아무것도 없음");
        } else {
            System.out.println("   번호  /          날짜          /     제목     /   내용  ");
            for (int i = articles.size() - 1; i >= 0; i--) {
                Article article = articles.get(i);
                System.out.printf("   %d    / %s /    %s     /     %s   \n", article.getId(), article.getDateTime(), article.getTitle(), article.getBody());
            }
        }
    }

    void details(String cmd) {
        System.out.println("==게시글 상세보기==");

        int id = Integer.parseInt(cmd.split(" ")[2]);

        Article foundArticle = null;

        for (Article article : articles) {
            if (article.getId() == id) {
                foundArticle = article;
                break;
            }
        }

        if (foundArticle == null) {
            System.out.println("해당 게시글은 없습니다");
        }
        System.out.println("번호 : " + foundArticle.getId());
        System.out.println("제목 : " + foundArticle.getTitle());
        System.out.println("내용 : " + foundArticle.getBody());
    }

    void delete(String cmd) {
        System.out.println("==게시글 삭제==");

        int id = Integer.parseInt(cmd.split(" ")[2]);

        Article foundArticle = null;

        for (Article article : articles) {
            if (article.getId() == id) {
                foundArticle = article;
                break;
            }
        }

        if (foundArticle == null) {
            System.out.println("해당 게시글은 없습니다");
        }
        articles.remove(foundArticle);
        System.out.println(id + "번 게시글이 삭제되었습니다");
    }

    void modify(String cmd) {
        System.out.println("==게시글 수정==");

        int id = Integer.parseInt(cmd.split(" ")[2]);

        Article foundArticle = null;

        for (Article article : articles) {
            if (article.getId() == id) {
                foundArticle = article;
                break;
            }
        }

        if (foundArticle == null) {
            System.out.println("해당 게시글은 없습니다");
        }
        System.out.println("기존 title : " + foundArticle.getTitle());
        System.out.println("기존 body : " + foundArticle.getBody());
        System.out.print("새 제목 : ");
        String newTitle = sc.nextLine().trim();
        System.out.print("새 내용 : ");
        String newBody = sc.nextLine().trim();

        foundArticle.setTitle(newTitle);
        foundArticle.setBody(newBody);
        System.out.println(id + "번 게시글이 수정되었습니다");
    }

    void searchTitle(String cmd) {
        String searchTitle = cmd.replace("article list ","");
        /*
        for(Article article : articles) {
            if (article.getTitle().contains(searchTitle)) {
                System.out.println(article.getId()+" / "+article.getDateTime());
            }
        }
        */

        for (int i = articles.size() - 1; i >= 0; i--) {
            Article article = articles.get(i);
            if(article.getTitle().contains(searchTitle)) {
                System.out.printf(" %d / %-16s  / %s \n", article.getId(), article.getTitle(), article.getDateTime());
            }
        }

    }

    void join(){
        String memberId;

        while(true) {
            while(true) {
                System.out.print("아이디를 입력하세요 : ");
                memberId = sc.nextLine().trim();

                boolean exists = false;

                for(Member m : members) {
                    if(m.getMemberId().equals(memberId)) {
                        exists = true;
                        break;
                    }
                }

                if (exists)
                    System.out.println("중복되는 ID입니다. 다른 ID를 사용해주세요");
                else break;
            }

            System.out.print("비밀번호를 입력하세요 : ");
            String memberPassword = sc.nextLine().trim();

            System.out.print("아이디를 다시 한번 입력하세요 : ");
            String tempMemberId = sc.nextLine().trim();
            System.out.print("비밀번호를 다시 한번 입력하세요 : ");
            String tempMemberPassword = sc.nextLine().trim();

            if (memberId.equals(tempMemberId) && memberPassword.equals(tempMemberPassword)) {
                members.add(new Member(memberId, memberPassword));
                System.out.println("회원 가입이 완료되었습니다.");
                break;
            } else {
                System.out.println("아이디 비밀번호가 맞지 않습니다. 다시 처음부터 가입해 주세요.");
            }
        }
    }

    void logIn()
    {
        if(!isLoggedIn) {
            System.out.print("ID : ");
            String inputId = sc.nextLine().trim();
            System.out.print("Password : ");
            String inputPassword = sc.nextLine().trim();

            for(Member member : members) {
                if(member.getMemberId().equals(inputId) && member.getMemberPassword().equals(inputPassword)) {

                    System.out.println("로그인 되었습니다");
                    userId = inputId;
                    isLoggedIn = true;
                    break;
                }
                else
                    System.out.println("잘못된 ID, 비밀번호 입니다.");
            }
        }
        else
        {
            System.out.println("이미 로그인 상태입니다.");
        }
    }
}

class Article {
    private int id;
    private String title;
    private String body;
    private String dateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDateTime() {
        return dateTime;
    }

    public Article(int id, String dateTime, String title, String body)
    {
        this.id = id;
        this.title = title;
        this.body = body;
        this.dateTime = dateTime;
    }
}

class Member{
    private String memberId;
    private String memberPassword;
    
    public String getMemberId() {
        return memberId;
    }
    public String getMemberPassword() {
        return memberPassword;
    }
    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }
    public Member(String memberId, String memberPassword) {
        this.memberId = memberId;
        this.memberPassword = memberPassword;
    }
    
}