package com.accolite.miniAU.restCRUD.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import com.accolite.miniAU.restCRUD.DatabaseConf;
import com.accolite.miniAU.restCRUD.model.Model;

public class Services {
	static int a=1;
	Connection con;
	
	public List<Model> getMembers() {
		DatabaseConf conf=new DatabaseConf();
		con=conf.DatabaseConnection();
		ResultSet rs;
		List<Model> l = new ArrayList<Model>();
		try {
			PreparedStatement pr=con.prepareStatement("select * from Employee");
			
			rs = pr.executeQuery();
		while(rs.next())
			l.add(new Model(rs.getInt(1),rs.getString(2),rs.getString(3)));
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return l;
	}
	public Model AddEmployee(Model model)
	{
		model.setId(a++);
		try {
		DatabaseConf conf=new DatabaseConf();
		con=conf.DatabaseConnection();
		String query="insert into Employee(id,name,mobile) values(?,?,?)";
		PreparedStatement pq=con.prepareStatement(query);
		pq.setInt(1,model.getId());
		pq.setString(2, model.getName());
		pq.setString(3, model.getMobile());
		pq.executeUpdate();
		con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return model;
	}
	public Model UpdateEmployee(Model model)
	{
		try {
			DatabaseConf conf=new DatabaseConf();
			con=conf.DatabaseConnection();
			String query="update Employee set name=?, mobile=? where id=? ";
			PreparedStatement pq=con.prepareStatement(query);
			pq.setInt(3, model.getId());
			pq.setString(1,model.getName());
			pq.setString(2,model.getMobile());
			pq.executeUpdate();
			con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			model.getId();
			model.getName();
			model.getMobile();
			return model;
	}
	public Model RetriveEmployee(int id)
	{
		Model md=new Model();
		try {
			DatabaseConf conf=new DatabaseConf();
			con=conf.DatabaseConnection();
			String query="select * from Employee where id = ?";
			PreparedStatement pr=con.prepareStatement(query);
			pr.setInt(1, id);
			
			ResultSet rs=pr.executeQuery();
			while(rs.next())
			{
				md.setId(rs.getInt(1));
				md.setName(rs.getString(2));
				md.setMobile(rs.getString(3));
			}
			rs.close();
			pr.close();
			con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return md;
	}
	public void DeleteEmployee(int id)
	{
		try {
			DatabaseConf conf=new DatabaseConf();
			con=conf.DatabaseConnection();
			String query="delete from Employee where id = ?";
			PreparedStatement pr=con.prepareStatement(query);
			pr.setInt(1, id);
			pr.executeUpdate();
			pr.close();
			con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			
	}
	

}
