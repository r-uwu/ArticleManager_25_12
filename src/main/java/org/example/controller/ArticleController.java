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
        int id = lastArticleId + 1;
        System.out.print("제목 : ");
        String title = sc.nextLine().trim();
        System.out.print("내용 : ");
        String body = sc.nextLine().trim();

        Article article = new Article(id, Util.dateTime(), title, body);
        articles.add(article);

        System.out.println(id + "번 글이 작성되었습니다.");
        lastArticleId++;
    }

    public void list() {
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
        System.out.println("내용 : " + foundArticle.getBody());

    }

    public void delete(String cmd) {
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

    public void modify(String cmd) {
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

    public void searchTitle(String cmd) {
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

    private void makeTestData() {
        System.out.println("==테스트 데이터 생성==");

        articles.add(new Article(1, Util.dateTime(), "test1", "asdf"));
        articles.add(new Article(2, Util.dateTime(), "test2", "ㅁㄴㅇㄹ"));
        articles.add(new Article(3, Util.dateTime(), "test3", "3333"));
        lastArticleId+=3;
    }

}
