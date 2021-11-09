package com.skillstorm.servlets;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.beans.Car;
import com.skillstorm.data.CarDAO;

@WebServlet(urlPatterns = "/api/cars")
public class CarServlet extends HttpServlet  {

	CarDAO dao = new CarDAO();
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		// called once when servlet is created
		System.out.println("servlet init()");
		
	}
	
	@Override
	public void destroy() {
		super.destroy();
		
		
		// called once when the servlet is destroyed
		System.out.println("servlet destroy()");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// called before EACH incoming HTTP request
		
		System.out.println("servlet service()");
		super.service(req, resp);	
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("servlet GET()");
		
		
		List<Car> allCars = dao.findAll();
		
		String json = new ObjectMapper().writeValueAsString(allCars);
		resp.getWriter().print(json);
		resp.setContentType("application/json");

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("servlet POST()");
		
		InputStream requestBody = req.getInputStream();
		
		Car car = new ObjectMapper().readValue(requestBody, Car.class);
		
		Car updatedCar = dao.create(car);
		
		resp.getWriter().print(new ObjectMapper().writeValueAsString(updatedCar));
		
		resp.setStatus(201);
		
		resp.setContentType("application/json");
		
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("servlet PUT()");

		InputStream requestBody = req.getInputStream();
		
		System.out.println("in doput " + requestBody);
		Car car = new ObjectMapper().readValue(requestBody, Car.class);
		System.out.println(car);
 
		dao.update(car);
		
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("servlet DELETE()");
		
		// parse the body of the http request
		InputStream requestBody = req.getInputStream();
		
		System.out.println("in dodelete " + requestBody);
		Car car = new ObjectMapper().readValue(requestBody, Car.class);
		System.out.println(car);
        dao.delete(car);
	}
}
