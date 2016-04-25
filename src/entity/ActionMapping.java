package entity;

import java.util.Map;

public class ActionMapping {

	private String actionName;
	private String className;
	private Map<String, ResultMapping> resultMapping;

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Map<String, ResultMapping> getResultMapping() {
		return resultMapping;
	}

	public void setResultMapping(Map<String, ResultMapping> resultMapping) {
		this.resultMapping = resultMapping;
	}

}
