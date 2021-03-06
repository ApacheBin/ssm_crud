package cn.edu.ecust.service;

import cn.edu.ecust.bean.Employee;
import cn.edu.ecust.bean.EmployeeExample;
import cn.edu.ecust.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 查询所有员工
     *
     * @return
     */
    public List<Employee> getAll() {
        //这不是分页查询
        return employeeMapper.selectByExampleWithDept(null);
    }

    public void saveEmp(Employee employee) {

        employeeMapper.insertSelective(employee);
    }

    //检验用户名是否可用
    public boolean checkUser(String empName) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpNameEqualTo(empName);
        long count = employeeMapper.countByExample(example);
        return count == 0;
    }
}
