package com.skillstorm.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.skillstorm.beans.Car;

public class CarDAO {

	private static final String url = "jdbc:mysql://localhost:3306/car";
	private static final String username = "root";
	private static final String password = "root";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<Car> findAll() {

		List<Car> allCars = new LinkedList<>();

		// 2. create the connection -AND- 5. closing the connection
		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			// 3. creating our statement
			String sql = "select * from cars";
			PreparedStatement stmt = conn.prepareStatement(sql);

			// 4. execute the statement
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				// retrieving our returned attributes
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String make = rs.getString("make");
				String model = rs.getString("model");
				int year = rs.getInt("year");
				String engine_type = rs.getString("engine_type");
				String transmission = rs.getString("transmission");
				String engine_transmission = rs.getString("engine_transmission");
				String driveLine = rs.getString("driveLine");
				String fuel_type = rs.getString("fuel_type");
				Car car = new Car(id, name, make, model, year, engine_type, transmission, engine_transmission,
						driveLine, fuel_type);

				allCars.add(car);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		// return back the set of all movies
		return allCars;
	}

	public Car create(Car car) {

		// 2. create the connection -AND- 5. closing the connection
		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			// 3. creating our statement
			String sql = "insert into cars(name, make, model, year, engine_type, transmission, engine_transmission"
					+ ", driveLine, fuel_type) values (? ,? ,? ,? ,? ,? ,? ,? ,?)";

			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, car.getName());
			stmt.setString(2, car.getMake());
			stmt.setString(3, car.getModel());
			stmt.setInt(4, car.getYear());
			stmt.setString(5, car.getEngine_type());
			stmt.setString(6, car.getTransmission());
			stmt.setString(7, car.getEngine_transmission());
			stmt.setString(8, car.getDriveLine());
			stmt.setString(9, car.getFuel_type());

			// 4. execute the statement
			stmt.executeUpdate(); // Update because changing the table

			// getting back the auto-incremented id from database
			ResultSet keys = stmt.getGeneratedKeys();

			keys.next();
			int id = keys.getInt(1);
			car.setId(id);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		// return back the updated movie with an ID
		return car;
	}
	
	public void update(Car newCar) {
		System.out.println("update in DAO");
		// 2. create the connection -AND- 5. closing the connection
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			
			// 3. creating our statement
			String sql = "update cars set name = ?, make = ?,model = ?, year = ?,"
					+ " engine_type = ?, transmission = ?,engine_transmission = ?, driveLine = ?,"
					+ " fuel_type = ? where cars.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);	
			stmt.setString(1, newCar.getName());
			stmt.setString(2, newCar.getMake());
			stmt.setString(3, newCar.getModel());
			stmt.setInt(4, newCar.getYear());
			stmt.setString(5, newCar.getEngine_type());
			stmt.setString(6, newCar.getTransmission());
			stmt.setString(7, newCar.getEngine_transmission());
			stmt.setString(8, newCar.getDriveLine());
			stmt.setString(9, newCar.getFuel_type());
			stmt.setInt(10, newCar.getId());
			
			// 4. execute the statement
			stmt.executeUpdate();	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Car car) {
		System.out.println("Delete in DAO");
		System.out.println(car.getId());

		try(Connection conn = DriverManager.getConnection(url, username, password)){
			
			// 3. creating our statement
			String sql = "delete from cars where cars.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);	
			stmt.setInt(1, car.getId());
			
			// 4. execute the statement
			stmt.executeUpdate();	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
}
