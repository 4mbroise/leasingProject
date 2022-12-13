package ws.rest.leasingproject.entities.employee.dao;

import ws.rest.leasingproject.entities.employee.entity.Employee;

import java.util.List;

public interface IEmployeeDAO {
    List<Employee> findAll() throws Exception;
    Employee findByMemberId(int memberId) throws Exception;
    int createEmployee(Employee employee) throws Exception;
    void updateEmployeeByMemberId(int memberId, Employee employee) throws Exception;
    void removeEmployeeByMemberId(int memberId) throws Exception;
}
