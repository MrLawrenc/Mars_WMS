package ${basePkg}.service.impl;

import ${basePkg}.dao.I${className}DAO;
import ${basePkg}.domain.${className};
import ${basePkg}.query.PageResult;
import ${basePkg}.query.QueryObject;
import ${basePkg}.service.I${className}Service;
import java.util.List;

public class ${className}ServiceImpl implements I${className}Service{
    @lombok.Setter
    private I${className}DAO ${objName}DAO;

    @Override
    public void save(${className} ${objName}){
        ${objName}DAO.save(${objName});
    }

    @Override
    public void update(${className} ${objName}){
        ${objName}DAO.update(${objName});
    }

    @Override
    public void delete(Long id){
        ${objName}DAO.delete(id);
    }

    @Override
    public ${className} get(Long id){
        return ${objName}DAO.get(id);
    }

    @Override
    public List<${className}> listAll(){
        return ${objName}DAO.listAll();
    }

    @Override
    public PageResult query(QueryObject qo){
        return ${objName}DAO.query(qo);
    }
 }
