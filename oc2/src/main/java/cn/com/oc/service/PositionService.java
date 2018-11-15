package cn.com.oc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.com.oc.domain.Department;
import cn.com.oc.dto.AddPositionForm;

@Service
public interface PositionService {

	boolean addPosition(AddPositionForm addPositionForm);
	
	List<Department> getAllExistedDepartment();
	
}
