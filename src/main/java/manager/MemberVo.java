package manager;

import java.util.Date;

public class MemberVo {
  //변수 선언
  private int num;
  private String memberId;
  private String memberPw;
  private String nickName;

  private Date regdate;

  //생성자 선언
  public MemberVo(int num, String memberId, String memberPw, String nickName) {
    this.num = num;
    this.memberId = memberId;
    this.memberPw = memberPw;
    this.nickName = nickName;
  }

  public int getNum() {
    return num;
  }

  public String getMemberId() {
    return memberId;
  }

  public String getMemberPw() {
    return memberPw;
  }

  public String getNickName() {
    return nickName;
  }

  public Date getRegdate() {
    return regdate;
  }

  public void setNum(int num) {
    this.num = num;
  }

  public void setMemberId(String memberId) {
    this.memberId = memberId;
  }

  public void setMemberPw(String memberPw) {
    this.memberPw = memberPw;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public void setRegdate(Date regdate) {
    this.regdate = regdate;
  }

  @Override
  public String toString() {
    return "MemberVo{" +
        "num=" + num +
        ", memberId='" + memberId + '\'' +
        ", memberPw='" + memberPw + '\'' +
        ", nickName='" + nickName + '\'' +
        ", regdate=" + regdate +
        '}';
  }
}
