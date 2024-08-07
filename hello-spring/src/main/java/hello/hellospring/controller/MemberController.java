package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;

import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MemberController {
  private final MemberService memberService;

  // @Autowired
  public MemberController(MemberService memberService) {
    this.memberService = memberService;

    // 실제 서비스가 프록시 적용이 되는지 확인하는 부분!
    System.out.println("MemberController = " + memberService.getClass());
  }

  // createMemberForm.html 을 보여준다.
  @GetMapping(value = "/members/new")
  public String createForm() {
    return "members/createMemberForm";
  }

  // 회원 가입 처리 로직
  @PostMapping(value = "/members/new")
  public String create(MemberForm form) {
    Member member = new Member();
    member.setName(form.getName());

    // 입력한 이름을 콘솔로 확인할 수 있다!!
    System.out.println("member = " + member.getName());

    memberService.join(member);
    return "redirect:/"; // 처음 화면(루트경로)으로 돌아가게 하는것!
  }

  
  @GetMapping(value = "/members")
  public String list(Model model) {
    List<Member> members = memberService.findMembers();
    model.addAttribute("members", members);
    return "members/memberList";
  }


}