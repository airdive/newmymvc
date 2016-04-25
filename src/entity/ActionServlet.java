package entity;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionServlet extends HttpServlet {

	private ActionMappingManager actionMappingManager;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionMapping actionMapping = actionMappingManager
				.getActionMapping(request);
		Action action = ActionManager.getAction(actionMapping.getClassName());
		String resultName = action.execute(request, response);
		ResultMapping resultMapping = actionMapping.getResultMapping().get(
				resultName);
		if (resultMapping.getIsRedirect().equals("true")) {
			response.sendRedirect(resultMapping.getResultPath());
		} else {
			request.getRequestDispatcher(resultMapping.getResultPath().trim())
					.forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	public void init() throws ServletException {
		System.out.println("ActionServlet init()нч╡н");
		String configFileValue = this.getInitParameter("configFile");
		actionMappingManager = new ActionMappingManager(configFileValue
				.split(","));
	}

}
