package cn.com.oc.vo;
import java.util.Map;
import java.util.TreeMap;

public class StructureView {
	
	private Map<String , Object> departmentStructure = new TreeMap<>();

	public Map<String, Object> getDepartmentStructure() {
		return departmentStructure;
	}

	public void setDepartmentStructure(Map<String, Object> departmentStructure) {
		this.departmentStructure = departmentStructure;
	}
	
}
