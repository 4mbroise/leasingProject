package ws.rest.leasingproject.entities.employee.dao;

import ws.rest.leasingproject.entities.employee.entity.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLEmployeeDAO implements IEmployeeDAO{

    @Override
    public List<Employee> findAll() throws SQLException, ClassNotFoundException {
        Connection connection = initConnection();
        String queryAllEmployee = "SELECT * from leasing.employee;";
        PreparedStatement statement = connection.prepareStatement(queryAllEmployee);
        ResultSet rs = statement.executeQuery();

        List<Employee> result = new ArrayList<>();

        while(rs.next()) {
            Employee employee = new Employee();
            employee.setMemberId(rs.getInt("memberID"));
            employee.setName(rs.getString("name"));
            employee.setSurname(rs.getString("surname"));
            employee.setSocialSecurityId(rs.getString("socialSecurityId"));
            employee.setDriverLicenseId(rs.getString("driverLicenseId"));
            employee.setAdress(rs.getString("adress"));
            result.add(employee);
        }
        connection.close();

        return result;
    }

    @Override
    public Employee findByMemberId(int memberId) throws SQLException, ClassNotFoundException, IndexOutOfBoundsException {
        Connection connection = initConnection();
        String queryAllEmployee = "SELECT * from leasing.employee WHERE memberId = ?;";
        PreparedStatement prepareStatement = connection.prepareStatement(queryAllEmployee);
        prepareStatement.setInt(1, memberId);
        ResultSet rs = prepareStatement.executeQuery();

        List<Employee> result = new ArrayList<>();

        while(rs.next()) {
            Employee employee = new Employee();
            employee.setMemberId(rs.getInt("memberID"));
            employee.setName(rs.getString("name"));
            employee.setSurname(rs.getString("surname"));
            employee.setSocialSecurityId(rs.getString("socialSecurityId"));
            employee.setDriverLicenseId(rs.getString("driverLicenseId"));
            employee.setAdress(rs.getString("adress"));
            result.add(employee);
        }
        connection.close();

        return result.get(0);
    }

    @Override
    public int createEmployee(Employee employee) throws Exception {
        Connection connection = initConnection();
        String queryAllEmployee = "INSERT INTO leasing.employee (name, surname, socialSecurityId, driverLicenseId, adress) VALUES (?,?,?,?,?);";
        PreparedStatement prepareStatement = connection.prepareStatement(queryAllEmployee);
        prepareStatement.setString(1, employee.getName());
        prepareStatement.setString(2, employee.getSurname());
        prepareStatement.setString(3, employee.getSocialSecurityId());
        prepareStatement.setString(4, employee.getDriverLicenseId());
        prepareStatement.setString(5, employee.getAdress());
        prepareStatement.execute();

        String getLastId = "SELECT MAX(memberId) FROM leasing.employee AS lastId;";
        prepareStatement = connection.prepareStatement(getLastId);
        ResultSet rs = prepareStatement.executeQuery();

        while(rs.next()){
            connection.close();
            return rs.getInt(1);
        }
        throw new Exception("bizarre n'est-ce pas ?");
    }

    // UPDATE leasing.employee SET name=?, surname=?,socialSecurityId=?, driverLicenseId=?, adress=? WHERE memberId=?;

    @Override
    public void updateEmployeeByMemberId(int memberId, Employee employee) throws SQLException, ClassNotFoundException {
        Connection connection = initConnection();

        if(employee.getName() != null){
            String update = "UPDATE leasing.employee SET name=? WHERE memberId=?;";
            PreparedStatement statement = connection.prepareStatement(update);
            statement.setString(1, employee.getName());
            statement.setInt(2, memberId);
            statement.execute();
        }

        if(employee.getSurname() != null){
            String update = "UPDATE leasing.employee SET surname=? WHERE memberId=?;";
            PreparedStatement statement = connection.prepareStatement(update);
            statement.setString(1, employee.getSurname());
            statement.setInt(2, memberId);
            statement.execute();
        }

        if(employee.getSocialSecurityId() != null){
            String update = "UPDATE leasing.employee SET socialSecurityId=? WHERE memberId=?;";
            PreparedStatement statement = connection.prepareStatement(update);
            statement.setString(1, employee.getSocialSecurityId());
            statement.setInt(2, memberId);
            statement.execute();
        }

        if(employee.getDriverLicenseId() != null){
            String update = "UPDATE leasing.employee SET driverLicenseId=? WHERE memberId=?;";
            PreparedStatement statement = connection.prepareStatement(update);
            statement.setString(1, employee.getDriverLicenseId());
            statement.setInt(2, memberId);
            statement.execute();
        }
        if(employee.getAdress() != null){
            String update = "UPDATE leasing.employee SET adress=? WHERE memberId=?;";
            PreparedStatement statement = connection.prepareStatement(update);
            statement.setString(1, employee.getAdress());
            statement.setInt(2, memberId);
            statement.execute();
        }

    }

    @Override
    public void removeEmployeeByMemberId(int memberId) throws SQLException, ClassNotFoundException {
        Connection connection = initConnection();
        String delete = "DELETE FROM leasing.employee WHERE memberId=10;";
        Statement statement = connection.createStatement();
        statement.execute(delete);
    }

    private Connection initConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/leasing", "root", "");
    }
}
