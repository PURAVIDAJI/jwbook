package manager;

import java.util.*;

public class MemberDao {
//MAIN함수에 작성했던 CRUD 부분을 DAO로 바꿔보겠음
  //Dao가 데이터베이스를 조작하게 하고
  //나는 Dao를 조작한다.
  //데이터 베이스와 쿼리를 주고 받는 역할!

  private Map<Integer,MemberVo> db = new HashMap<>();

  //C
  public void insertMember(MemberVo vo){
    vo.setRegdate(new Date());
    db.put(vo.getNum(), vo);
  }

  //R
  //회원번호를 받아서 해당번호에 맞는 멤버 정보를 돌려준다.
  public MemberVo selectMember(int num){
    return db.get(num);
  }
  //전체 조회 -> 리스트를 반환해줌
  public List<MemberVo> selectMemberAll(){
    return new ArrayList<MemberVo>(db.values());
  }

  //U
  public void updateMember(MemberVo vo){
    db.put(vo.getNum(),vo);
 // 그 번호에 해당하는 것을 바꿈
  }
  //D
  public void deleteMember(int num){
    db.remove(num);
  }

  public void deleMemberAll(){
    db.clear();
  }

}
