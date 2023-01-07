package ws.rest.leasingproject.entities.rent.dao;

import ws.rest.leasingproject.entities.employee.dao.IEmployeeDAO;
import ws.rest.leasingproject.entities.employee.dao.MySQLEmployeeDAO;
import ws.rest.leasingproject.entities.employee.entity.Employee;
import ws.rest.leasingproject.entities.rent.entity.Rent;
import ws.rest.leasingproject.entities.vehicle.dao.IVehicleDAO;
import ws.rest.leasingproject.entities.vehicle.dao.MySQLVehicleDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysSQLRentDAO implements IRentDao{

    private Connection dbConnection;

    public MysSQLRentDAO() throws SQLException, ClassNotFoundException {
        Connection connection = initConnection();

        // create table if not exists rent (rentId int not null,renter int not null,rentedVehicle varchar(100) not null,rentDate DATE not null,hourStartRent TIME not null,hourEndRent TIME not null,constraint rent_pkprimary key (rentId),constraint rentedVehicleforeign key (rentedVehicle) references vehicle (registration),constraint renterforeign key (renter) references employee (memberId));
        String createRentTable = "create table if not exists rent (rentId int not null,renter int not null,rentedVehicle varchar(100) not null,rentDate DATE not null,hourStartRent TIME not null,hourEndRent TIME not null,constraint rent_pk primary key (rentId),constraint rentedVehicle foreign key (rentedVehicle) references vehicle (registration),constraint renter foreign key (renter) references employee (memberId));";

        try(Statement statement = connection.createStatement()){
            statement.execute(createRentTable);
        }
        connection.close();
    }

    @Override
    public List<Rent> findAll() throws Exception {
        Connection connection = initConnection();

        String queryAllRent = "SELECT * from leasing.rent";
        PreparedStatement statement = connection.prepareStatement(queryAllRent);

        ResultSet rs = statement.executeQuery();
        List<Rent> result = new ArrayList<>();

        IEmployeeDAO IEmployeeDAO = new MySQLEmployeeDAO();
        IVehicleDAO IVehicleDAO = new MySQLVehicleDAO();

        while (rs.next()){

            System.out.println("Elliot");

            Rent rent = new Rent();
            rent.setRentId(rs.getInt("rentId"));


            // Gestion Employé qui loue
            rent.setRenter(IEmployeeDAO.findByMemberId(rs.getInt("renter")));

            // Gestion Véhicule loué
            rent.setRentedVehicle(IVehicleDAO.findByRegistration(rs.getString("rentedVehicle")));


            rent.setRentDay(rs.getDate("rentDate"));
            rent.setHourStartRent(rs.getTime("hourStartRent"));
            rent.setHourEndRent(rs.getTime("hourEndRent"));

            result.add(rent);
        }

        return result;

    }

    @Override
    public Rent findByRentId(int rentId) throws Exception {

        Connection connection = initConnection();

        String queryAllRent = "SELECT * from leasing.rent WHERE rentId";
        PreparedStatement statement = connection.prepareStatement(queryAllRent);

        ResultSet rs = statement.executeQuery();

        IEmployeeDAO IEmployeeDAO = new MySQLEmployeeDAO();
        IVehicleDAO IVehicleDAO = new MySQLVehicleDAO();

        while (rs.next()){
            Rent rent = new Rent();
            rent.setRentId(rs.getInt("rentId"));


            // Gestion Employé qui loue
            rent.setRenter(IEmployeeDAO.findByMemberId(rs.getInt("renter")));

            // Gestion Véhicule loué
            rent.setRentedVehicle(IVehicleDAO.findByRegistration(rs.getString("rentedVehicle")));


            rent.setRentDay(rs.getDate("rentDate"));
            rent.setHourStartRent(rs.getTime("hourStartRent"));
            rent.setHourEndRent(rs.getTime("hourEndRent"));

            return rent;
        }
        return null;
    }

    @Override
    public void createRent(Rent rent) {

    }

    @Override
    public void updateRentByRentId(int rentId) {

    }

    @Override
    public void removeRentByRentId(int rentId) throws SQLException, ClassNotFoundException {
        Connection connection = initConnection();

        String queryAllRent = "DELETE FROM leasing.rent WHERE rentId = ?";
        PreparedStatement statement = connection.prepareStatement(queryAllRent);

        statement.setInt(1, rentId);


        ResultSet rs = statement.executeQuery();
    }

    private Connection initConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/leasing", "root", "");
    }
}
