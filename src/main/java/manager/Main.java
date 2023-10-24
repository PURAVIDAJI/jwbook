package manager;

import java.util.*;

public class Main {
  public static void main(String[] args) {

    Map <Integer, MemberVo> db = new HashMap<>();



    //C(insert)
    MemberVo vo1 = new MemberVo(1,"abcd","1234","adidas");
    vo1.setRegdate(new Date("2023-11-01"));
    MemberVo vo2 = new MemberVo(2,"efg","5678","nike");
    vo1.setRegdate(new Date("2023-12-02"));
    MemberVo vo3 = new MemberVo(3,"higk","9102","puma");
    vo1.setRegdate(new Date("2023-12-05"));

    db.put(1,vo1);
    db.put(2,vo2);
    db.put(3,vo3);
    System.out.println("저장 완료!");

    //R(select)
    List<MemberVo> ls = new ArrayList<>(db.values());
    for(MemberVo tmp : ls) {
      System.out.println(tmp);
    }
    System.out.println("전체 조회 완료");

    MemberVo vo =null;
    vo= db.get(1);
    System.out.println(vo);

    vo= db.get(4);
    System.out.println(vo);
    System.out.println("조회 완료");





    //U(update)
    //검색부터 완료
    vo = db.get(1);
    System.out.println(vo);



    if(vo !=null) {
      vo.setMemberPw("4321");
      vo.setNickName("kybo");
      db.put(vo.getNum(),vo);
    }

    vo = db.get(1);
    System.out.println(vo);
    System.out.println("수정완료!");




    //D(delete)
    db.remove(2);
    //조회하기
    ls = new ArrayList<>(db.values());
    for(MemberVo tmp : ls){
      System.out.println(tmp);
    }
    System.out.println("전체조회 완료");

    db.clear();
    ls = new ArrayList<>(db.values());
    for(MemberVo tmp : ls){
      System.out.println(tmp);
    }
    System.out.println("전체조회 완료");



  }




}
