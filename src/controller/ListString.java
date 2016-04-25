package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Action;

public class ListString implements Action {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		System.out.println("÷¥––¡ÀListString");

		List listString = new ArrayList();
		for (int i = 0; i < 10; i++) {
			listString.add("∏ﬂ∫È—“" + (i + 1));
		}
		request.setAttribute("listString", listString);

		return "toShowUserinfoList";
	}
}
