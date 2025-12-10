package org.example.controller;

import org.example.dto.Article;
import org.example.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArticleController {

    Scanner sc;
    List<Article> articles;
    int lastArticleId = 0;

    public ArticleController(Scanner sc)
    {
        this.sc = sc;
        articles = new ArrayList<>();
        makeTestData();
    }

    public void write() {
        System.out.println("==게시글 작성==");
        String loginId = MemberController.requireLogin();
        if (loginId == null) return;

        int id = lastArticleId + 1;
        System.out.print("제목 : ");
        String title = sc.nextLine().trim();
        System.out.print("내용 : ");
        String body = sc.nextLine().trim();

        Article article = new Article(id, Util.dateTime(), title, body, loginId);
        articles.add(article);

        System.out.println(id + "번 글이 작성되었습니다.");
        lastArticleId++;

    }

    public void list() {
        System.out.println("==게시글 목록==");
        if (articles.isEmpty()) {
            System.out.println("아무것도 없음");
        } else {
            System.out.println("   번호  /          날짜          /     제목     /   내용   /   작성자 ");
            for (int i = articles.size() - 1; i >= 0; i--) {
                Article article = articles.get(i);
                System.out.printf(" %-3d / %s / %-10s / %-16s / %-6s\n", article.getId(), article.getDateTime(), article.getTitle(), article.getBody(), article.getWriter());
            }
        }
    }

    public void details(String cmd) {
        System.out.println("==게시글 상세보기==");

        String stringPostNumber = cmd.replace("article detail", "").trim();

        int id = 0;

        try {
            id = Integer.parseInt(stringPostNumber);
        } catch (NumberFormatException e) {
            System.out.println("형식에 맞게 입력해주세요. article detail + (열람번호)");
            return;
        }

        Article foundArticle = null;

        for (Article article : articles) {
            if (article.getId() == id) {
                foundArticle = article;
                break;
            }
        }

        if (foundArticle == null) {
            System.out.println("해당 게시글은 없습니다");
            return;
        }
        System.out.println("번호 : " + foundArticle.getId());
        System.out.println("제목 : " + foundArticle.getTitle());
        System.out.println("작성자 : "+foundArticle.getWriter());
        System.out.println("내용 : " + foundArticle.getBody());

    }

    public void delete(String cmd) {
        System.out.println("==게시글 삭제==");

        String loginId = MemberController.requireLogin();
        if (loginId == null) return;

        String stringPostNumber = cmd.replace("article delete", "").trim();
        int id = 0;

        try {
            id = Integer.parseInt(stringPostNumber);
        } catch (NumberFormatException e) {
            System.out.println("형식에 맞게 입력해주세요. article delete + (삭제번호)");
            return;
        }

        Article foundArticle = null;

        for (Article article : articles) {
            if (article.getId() == id) {
                foundArticle = article;
                break;
            }
        }

        if (foundArticle == null) {
            System.out.println("해당 게시글은 없습니다");
            return;
        }
        if(loginId.equals(foundArticle.getWriter())) {
            articles.remove(foundArticle);
            System.out.println(id + "번 게시글이 삭제되었습니다");
        }
        else System.out.println("작성자만 삭제할 수 있습니다.");

    }

    public void modify(String cmd) {
        System.out.println("==게시글 수정==");

        String loginId = MemberController.requireLogin();
        if (loginId == null) return;

        String stringPostNumber = cmd.replace("article modify", "").trim();
        int id = 0;

        try {
            id = Integer.parseInt(stringPostNumber);
        } catch (NumberFormatException e) {
            System.out.println("형식에 맞게 입력해주세요. article modify + (수정번호)");
            return;
        }

        Article foundArticle = null;

        for (Article article : articles) {
            if (article.getId() == id) {
                foundArticle = article;
                break;
            }
        }

        if (foundArticle == null) {
            System.out.println("해당 게시글은 없습니다");
            return;
        }
        if(loginId.equals(foundArticle.getWriter())) {
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
        else System.out.println("작성자만 수정할 수 있습니다.");
    }

    public void searchTitle(String cmd) {
        String searchTitle = cmd.replace("article list ","");
        int count = 0;
        for (int i = articles.size() - 1; i >= 0; i--) {
            Article article = articles.get(i);
            if(article.getTitle().contains(searchTitle)) {
                count++;
                System.out.printf(" %-3d / %s / %-10s / %-16s / %-6s\n", article.getId(), article.getDateTime(), article.getTitle(), article.getBody(), article.getWriter());
            }
        }
        System.out.println("\""+searchTitle+"\" 가 포함 된 모든 게시글 리스트입니다. ("+count+"개의 게시물)");
    }

    private void makeTestData() {
        System.out.println("==테스트 데이터 생성==");

        articles.add(new Article(1, Util.dateTime(), "test1", "asdf",null));
        articles.add(new Article(2, Util.dateTime(), "test2", "ㅁㄴㅇㄹ",null));
        articles.add(new Article(3, Util.dateTime(), "test3", "3333","asdf"));
        lastArticleId+=3;
    }
}
