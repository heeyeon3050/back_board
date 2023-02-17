package lab.back_board.controller;

import lab.back_board.dto.MemberDTO;
import lab.back_board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MemberController {
    //생성자 주입
    private final MemberService memberService;

    @GetMapping("/member/save")
    public String saveForm(){
        return "save";
    }

    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO){
        if(memberDTO.getMemberPassword().equals(memberDTO.getMemberPassword2())){
            memberService.save(memberDTO);
            return "login";
        } else {
            return "save";
        }
    }

    @GetMapping("/member/login")
    public String loginForm(){
        return "login";
    }

    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session){
        MemberDTO loginResult = memberService.login(memberDTO);
        if(loginResult != null){
            //login 성공
            session.setAttribute("loginId", loginResult.getMemberId());
            return "main";
        } else {
            //login 실패
            return "login";
        }
    }
}
