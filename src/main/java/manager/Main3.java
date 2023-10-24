//package manager;
//
//import java.util.Date;
//import java.util.List;
//
//public class Main3 {
//  public static void main(String[] args) {
//
//   MemberService memberService =  new MemberService(new MemberDao());
//
//   //데이터베이스에 직접 집어넣지 않고,
//    //MemberDao의 메서드를 이용해서 데이터에 access함
//
//
//
//    //C(insert)
//    MemberVo vo1 = new MemberVo(1,"abcd","1234","adidas");
//    vo1.setRegdate(new Date());
//    MemberVo vo2 = new MemberVo(2,"efg","5678","nike");
//    vo1.setRegdate(new Date());
//    MemberVo vo3 = new MemberVo(3,"higk","9102","puma");
//    vo1.setRegdate(new Date());
//
//
//    memberService.regist(vo1);
//    memberService.regist(vo2);
//    memberService.regist(vo3);
//
//
////    memberDao.insertMember(vo1);
////    memberDao.insertMember(vo2);
////    memberDao.insertMember(vo3);
//    System.out.println("저장 완료!");
//
//    //R(select)
//    List<MemberVo> ls = memberDao.selectMemberAll();
//    for(MemberVo tmp : ls) {
//      System.out.println(tmp);
//    }
//    System.out.println("전체 조회 완료");
//
//    MemberVo vo =null;
//    vo= memberDao.selectMember(1);
//    System.out.println(vo);
//
//    vo= memberDao.selectMember(4);
//    System.out.println(vo);
//    System.out.println("조회 완료");
//
//
//
//
//
//
//    //U(update)
//    //검색부터 완료
//    vo = memberDao.selectMember(1);
//    System.out.println(vo);
//
//
//
//    if(vo !=null) {
//      vo.setMemberPw("4321");
//      vo.setNickName("kybo");
//      memberDao.updateMember(vo);
//    }
//
//    vo = memberDao.selectMember(1);
//    System.out.println(vo);
//    System.out.println("수정완료!");
//
//
//
//
//    //D(delete)
//    memberDao.deleteMember(2);
//    //조회하기
//    ls = memberDao.selectMemberAll();
//    for(MemberVo tmp : ls){
//      System.out.println(tmp);
//    }
//    System.out.println("전체조회 완료");
//
//    memberDao.deleMemberAll();
//    ls = memberDao.selectMemberAll();
//    for(MemberVo tmp : ls){
//      System.out.println(tmp);
//    }
//    System.out.println("전체조회 완료");
//
//
//
//  }
//
//
//
//
//}
