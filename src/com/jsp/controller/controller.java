package com.jsp.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.codec.binary.StringUtils;

import com.jsp.beans.detailsRegister;
import com.jsp.classes.OverAllPosts;
import com.jsp.classes.commnetModel;
import com.jsp.classes.blobModel;
import com.jsp.util.DbConnection;

@MultipartConfig(maxFileSize = 4194304)
public class controller {
	private Connection con;
	private int useId;
	private int pId;
	private int userId;
	private String tagLine;
	private int uId;
	private String base64Image="";
	private String self="";
	private int ImpId;
    public controller(){
    	
    }
    public Connection connect(){
    	con=DbConnection.getDBConn();
		System.out.println("Connected");
		return con;
    }
    public void loginCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
    	List<detailsRegister> l=new ArrayList<>();
    	String email=request.getParameter("email");
    	String password=request.getParameter("psw");
    	Statement stmt=con.createStatement();
    	Statement stmt2=con.createStatement();
    	ResultSet rs=stmt.executeQuery("select * from registration where email='"+email+"'");
    	rs.next();
		String CPassword = rs.getString("password");
//		System.out.println(password);
		if(CPassword.equals(password)){
			System.out.println(CPassword+" "+password);
			ResultSet rw=stmt2.executeQuery("select * from registration where email='"+email+"'");
			while(rw.next()){	
				uId=rw.getInt("userId");
				String fullname=rw.getString("fullname");
				String Email=rw.getString("email");
				String mobile=rw.getString("mobileNumber");
				String gen=rw.getString("gender");
				String rol=rw.getString("roles");
				detailsRegister d=new detailsRegister(uId,fullname,Email,mobile,gen,rol);
				System.out.println(d.toString());
				l.add(d);
				Cookie ck16=new Cookie("name",fullname);
				Cookie ck17=new Cookie("email",Email);
				String id=String.valueOf(uId);
				Cookie ck18=new Cookie("Id",id);
				Cookie ck19=new Cookie("roles",rol);
				response.addCookie(ck16);
				response.addCookie(ck17);
				response.addCookie(ck18);
			}
			System.out.println(uId);
			request.setAttribute("details", l);
			response.sendRedirect("homeServlet");
		}else{
			request.setAttribute("error","Invalid Password or invalid username!!");
			RequestDispatcher dispatcher=request.getRequestDispatcher("/index.html");
			dispatcher.forward(request,response);
		}
    }
    public void uploadPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
    	PreparedStatement pstmt = null;
    	FileInputStream fis=null;
    	ResultSet rs=null;
    	int i=0;
    	int userId=Integer.parseInt(request.getParameter("userId"));
    	String tag=request.getParameter("tag");
    	Part filePart=request.getPart("da");
    	InputStream inputStream=null;
    	if(filePart != null){
    		System.out.println(filePart.getName());
    		System.out.println(filePart.getSize());
    		System.out.println(filePart.getContentType());
    		inputStream=filePart.getInputStream();
    	}
    	pstmt=con.prepareStatement("insert into posts (userId,fileStore,tagLine)"+"values(?,?,?)");
    	pstmt.setInt(1, userId);
    	pstmt.setBinaryStream(2,inputStream);
    	pstmt.setString(3, tag);
    	i=pstmt.executeUpdate();
    	if(i>0){
    		System.out.println("File saved Successfully");
    	}
    	response.sendRedirect("MyPosts");
    }
    public void getMyPosts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
    	List<blobModel> li=new ArrayList<>();
    	System.out.println("hello..madam");
    	Statement stmt=con.createStatement();
    	Statement stmt2=con.createStatement();
//    	response.setContentType("image/jpeg");
    	Cookie ck[]=request.getCookies();
		for(int i=0;i<ck.length;i++){
			if(ck[i].getName().equals("Id")){
				useId=Integer.parseInt(ck[i].getValue());
			}
		}
		request.setAttribute("userId", useId);
		System.out.println("userId "+useId);
		Blob image=null;
		byte[] imgData=null;
		ResultSet rs=stmt.executeQuery("select * from posts where userId='"+useId+"'");
		List<String> list=new ArrayList<>();
		while(rs.next()){
			pId=rs.getInt("pId");
			userId=rs.getInt("userId");
			tagLine=rs.getString("tagLine");
			image=rs.getBlob("fileStore");
			imgData=image.getBytes(1, (int)image.length());
			byte[] by=Base64.getEncoder().encode(imgData);
			System.out.println(by);
			base64Image=new String(by,"UTF-8");
			blobModel b=new blobModel(pId,userId,base64Image,tagLine);
			li.add(b);
		}
		System.out.println("UptoMark");
		ResultSet rw=stmt2.executeQuery("select fullname from registration where userId='"+useId+"'");
		rw.next();
		String fullname=rw.getString("fullname");
		request.setAttribute("name", fullname);
		request.setAttribute("postList", li);
		RequestDispatcher dispatcher=request.getRequestDispatcher("/Posts.jsp");
		dispatcher.forward(request,response);
    }
    public void commentPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
    	int pId=Integer.parseInt(request.getParameter("postId"));
    	System.out.println("pid"+pId);
    	int userId=Integer.parseInt(request.getParameter("userId"));
    	String comment=request.getParameter("comment");
    	Statement stmt=con.createStatement();
    	int i=stmt.executeUpdate("Insert into answersTable (pId,userId,reply) values ('"+pId+"','"+userId+"','"+comment+"')");
    	if(i>0){
    		request.setAttribute("success", "Commented successfully");
    	}else{
    		request.setAttribute("error", "Comment is not saved");
    	}
    	response.sendRedirect("homeServlet");
    	
    }
    public void getAllPosts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
    	System.out.println("AllPosts....");
    	List<OverAllPosts> l=new ArrayList<>();
    	Statement stmt=con.createStatement();
    	Statement stmt2=con.createStatement();
    	Blob image=null;
		byte[] imgData=null;
    	ResultSet rs=stmt.executeQuery("select * from posts");
    	while(rs.next()){
    		pId=rs.getInt("pId");
			userId=rs.getInt("userId");
			System.out.println("user "+userId);
			tagLine=rs.getString("tagLine");
			image=rs.getBlob("fileStore");
			imgData=image.getBytes(1, (int)image.length());
			byte[] by=Base64.getEncoder().encode(imgData);
			System.out.println(by);
			base64Image=new String(by,"UTF-8");
			ResultSet rw=stmt2.executeQuery("select fullname from registration where userId='"+userId+"'");
			rw.next();
			String name=rw.getString("fullname");
			OverAllPosts b=new OverAllPosts(pId,userId,base64Image,tagLine,name);
			System.out.println(b.toString());
			l.add(b);
    	}
    	Cookie ck[]=request.getCookies();
		for(int i=0;i<ck.length;i++){
			if(ck[i].getName().equals("Id")){
				ImpId=Integer.parseInt(ck[i].getValue());
			}
		}
		request.setAttribute("ImpId", ImpId);
    	request.setAttribute("AllPostsList", l);
		RequestDispatcher dispatcher=request.getRequestDispatcher("/Home.jsp");
		dispatcher.forward(request,response);
    }
    public void getAllComments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
    	List<commnetModel> l=new ArrayList<>();
    	List<blobModel> li=new ArrayList<>();
    	int id=Integer.parseInt(request.getParameter("id"));
    	Statement stmt=con.createStatement();
    	Statement stmt2=con.createStatement();
    	Statement stmt3=con.createStatement();
    	ResultSet rs=stmt.executeQuery("select * from answersTable where pId='"+id+"'");
    	while(rs.next()){
    		int UId=rs.getInt("userId");
    		System.out.println(UId);
    		String reply=rs.getString("reply");
    		ResultSet rt=stmt2.executeQuery("select fullname from registration where userId='"+UId+"'");
    		rt.next();
    		String name=rt.getString("fullname");
    		commnetModel c=new commnetModel(name,reply);
    		l.add(c);
    	}
    	Blob image=null;
    	ResultSet rw=stmt3.executeQuery("select * from posts where pId='"+id+"'");
    	rw.next();
    	int userId=rw.getInt("userId");
    	String tag=rw.getString("tagLine");
    	image=rw.getBlob("fileStore");
		byte[] imgData=image.getBytes(1, (int)image.length());
		byte[] by=Base64.getEncoder().encode(imgData);
		System.out.println(id+" "+userId+" "+tag);
		String base64=new String(by,"UTF-8");
		blobModel b=new blobModel(id,userId,base64,tag);
		li.add(b);
		request.setAttribute("post", li);
    	request.setAttribute("comments", l);
    	RequestDispatcher dispatcher=request.getRequestDispatcher("/Comment.jsp");
		dispatcher.forward(request,response);
    	
    }

}
