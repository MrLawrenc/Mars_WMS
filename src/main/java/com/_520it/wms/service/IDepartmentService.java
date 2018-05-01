package com._520it.wms.service;
import com._520it.wms.domain.Department;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import java.util.List;

public interface IDepartmentService {
	void save(Department e);

	void update(Department e);

	void delete(Long id);

	Department get(Long id);

	List<Department> listAll();
	public PageResult query(QueryObject qo);
}
