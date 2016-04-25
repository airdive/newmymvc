package entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ActionMappingManager {

	private Map<String, ActionMapping> actionMappingMap = new HashMap<String, ActionMapping>();

	public ActionMappingManager(String[] configFileArray) {
		createActionMapping(configFileArray);
	}

	private void createActionMapping(String[] configFileArray) {

		try {
			for (int i = 0; i < configFileArray.length; i++) {
				String configFile = configFileArray[i];

				SAXReader reader = new SAXReader();
				Document document = reader.read(this.getClass()
						.getResourceAsStream("/" + configFile));

				List<Element> actionElementList = document.getRootElement()
						.element("actions").elements("action");
				for (int j = 0; j < actionElementList.size(); j++) {
					ActionMapping actionMapping = new ActionMapping();
					Element actionElement = actionElementList.get(j);
					String actionValue = actionElement.attributeValue("name");
					String classValue = actionElement.attributeValue("class");

					actionMapping.setActionName(actionValue);
					actionMapping.setClassName(classValue);

					List<Element> resultElementList = actionElement
							.elements("result");
					if (resultElementList.size() > 0) {
						actionMapping.setResultMapping(new HashMap());

						for (int y = 0; y < resultElementList.size(); y++) {
							ResultMapping resultMappping = new ResultMapping();

							Element resultElement = resultElementList.get(y);
							String resultName = resultElement
									.attributeValue("name");
							String resultPath = resultElement.getText().trim();

							resultMappping.setResultName(resultName);
							resultMappping.setResultPath(resultPath);

							if (resultElement.attribute("type") != null) {
								String typeValue = resultElement
										.attributeValue("type");
								resultMappping.setIsRedirect("true");
							}
							actionMapping.getResultMapping().put(resultName,
									resultMappping);
						}

					}
					actionMappingMap.put(actionValue, actionMapping);
				}

			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String abc = "";
	}

	public ActionMapping getActionMapping(HttpServletRequest request) {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String actionPath = uri.substring(contextPath.length() + 1);
		String actionName = actionPath.substring(0, actionPath.indexOf("."));
		return actionMappingMap.get(actionName);
	}

}
