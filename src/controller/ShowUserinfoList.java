package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Action;

public class ShowUserinfoList implements Action {
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		System.out.println("÷¥––¡ÀShowUserinfoList");

		List userinfoList = new ArrayList();
		for (int i = 0; i < 5; i++) {
			userinfoList.add("userinfo" + (i + 1));
		}
		request.setAttribute("userinfoList", userinfoList);

		return "toShowUserinfoListJSP";
	}

}
