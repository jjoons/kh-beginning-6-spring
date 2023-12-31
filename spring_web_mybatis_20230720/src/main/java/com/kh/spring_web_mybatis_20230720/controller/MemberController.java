package com.kh.spring_web_mybatis_20230720.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.kh.spring_web_mybatis_20230720.vo.MemberVO;

public interface MemberController {
  // 공통적으로 사용할 메소드 정리
  public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response)
      throws Exception;

  public ModelAndView addMember(MemberVO memberVO, HttpServletRequest request,
      HttpServletResponse response) throws Exception;

  public ModelAndView login(MemberVO memberVO, HttpServletRequest request, RedirectAttributes rAttr,
      HttpServletResponse response) throws Exception;

  public ModelAndView removeMember(HttpServletRequest request, HttpServletResponse response)
      throws Exception;
}

