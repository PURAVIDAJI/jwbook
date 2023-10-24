package manager;

import ch06.Product;

import java.util.List;

public class MemberService {
  //사용자와 핵심기능이 소통하기 위한 것=> service
  //사용자가 요청한 것이 무엇이냐에 따라 처리를 하기 위한 것.

  //memberDao를 가지고 있어야 한다.
  private MemberDao memberDao;

  public MemberService(MemberDao memberDao){

    this.memberDao = memberDao;
  }

  //등록하기
  public boolean regist(MemberVo vo){
    //select해서 가져 온 값이 없으면(동일한 회원이 없으면) 새로 넣는다.
    if(memberDao.selectMember(vo.getNum())==null){
      memberDao.insertMember(vo);
      return true;
    }else {
      return false;
    }
  }

  //조회하기
  public MemberVo read(int num){
    return memberDao.selectMember(num);
  }

  //전체조회
  public List<MemberVo> listAll(){
    return memberDao.selectMemberAll();
  }

  //수정
  public void edit(MemberVo vo){
    MemberVo searchMember = memberDao.selectMember(vo.getNum());
    if(searchMember.getMemberPw().equals(vo.getMemberPw())){
      memberDao.updateMember(vo);
      //전달받은 멤버의 패스워드가 원래의 패스워드와 일치할때만 변경해라
      // 패스워드가 다르면 변경하지 말아라.

    }

  }
  //탈퇴
  public void remove(int num){
    memberDao.deleteMember(num);


  }





}
