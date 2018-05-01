package ${basePkg}.service;


import ${basePkg}.domain.${className};
import ${basePkg}.query.PageResult;
import ${basePkg}.query.QueryObject;

import java.util.List;

public interface I${className}Service {
    void save(${className} ${objName});

    void update(${className} ${objName});

    void delete(Long id);

    ${className} get(Long id);

    List<${className}> listAll();

    //分页查询
    public PageResult query(QueryObject qo);
}
