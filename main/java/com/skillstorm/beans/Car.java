package com.skillstorm.beans;

import java.util.Objects;

public class Car {
	private int id;
	private String name;
	private String make;
	private String model;
	private int year;
	private String engine_type;
	private String transmission;
	private String engine_transmission;
	private String driveLine;
	private String fuel_type;

	public Car() {
		super();
	}
	
	public Car(int id) {
		super();
		this.id = id;
	}
	

	public Car(int id, String name, String make, String model, int year, String engine_type, String transmission,
			String engine_transmission, String driveLine, String fuel_type) {
		super();
		this.id = id;
		this.name = name;
		this.make = make;
		this.model = model;
		this.year = year;
		this.engine_type = engine_type;
		this.transmission = transmission;
		this.engine_transmission = engine_transmission;
		this.driveLine = driveLine;
		this.fuel_type = fuel_type;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getEngine_type() {
		return engine_type;
	}

	public void setEngine_type(String engine_type) {
		this.engine_type = engine_type;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public String getEngine_transmission() {
		return engine_transmission;
	}

	public void setEngine_transmission(String engine_transmission) {
		this.engine_transmission = engine_transmission;
	}

	public String getDriveLine() {
		return driveLine;
	}

	public void setDriveLine(String driveLine) {
		this.driveLine = driveLine;
	}

	public String getFuel_type() {
		return fuel_type;
	}

	public void setFuel_type(String fuel_type) {
		this.fuel_type = fuel_type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(driveLine, engine_transmission, engine_type, fuel_type, id, make,
				model, name, transmission, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		return Objects.equals(driveLine, other.driveLine)
				&& Objects.equals(engine_transmission, other.engine_transmission)
				&& Objects.equals(engine_type, other.engine_type) && Objects.equals(fuel_type, other.fuel_type) && id == other.id
				&& Objects.equals(make, other.make) && Objects.equals(model, other.model)
				&& Objects.equals(name, other.name) && Objects.equals(transmission, other.transmission)
				&& year == other.year;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", year=" + year + ", engine_type=" + engine_type + ", transmission=" + transmission + ", engine_transmission="
				+ engine_transmission + ", driveLine=" + driveLine + ", fuel_type=" + fuel_type + ", name=" + name
				+ ", make=" + make + ", model=" + model + "]";
	}

}
