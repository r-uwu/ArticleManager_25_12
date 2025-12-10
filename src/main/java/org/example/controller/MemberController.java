package org.example.controller;

import org.example.dto.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController {

    Scanner sc;

    List<Member> members;

    //private static boolean isLoggedIn = false;
    boolean isLoggedIn = false;
    private static String userId = null;

    /*
    public static boolean getLoggedIn()
    {
        return isLoggedIn;
    }

     */
    public static String getLoggedIn()
    {
        return userId;
    }


    public MemberController(Scanner sc) {
        this.sc = sc;
        members = new ArrayList<>();
        makeTestMember();
    }

    private void makeTestMember() {
        System.out.println("==테스트 멤버 생성==");
        members.add(new Member("member1", "asdf"));
        members.add(new Member("member2", "asdf"));
        members.add(new Member("member3", "asdf"));
    }

    public void join(){
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

    public void login()
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
            }
            if(!isLoggedIn) {
                System.out.println("잘못된 ID, 비밀번호 입니다.");
            }
        }
        else
        {
            System.out.println("이미 로그인 상태입니다.");
        }
    }

    public void logout()
    {
        if(!isLoggedIn) {
            System.out.println("이미 로그아웃 상태입니다.");
        }
        else {
            System.out.println("로그아웃 되었습니다.");
            userId = null;
            isLoggedIn = false;
        }
    }
}
