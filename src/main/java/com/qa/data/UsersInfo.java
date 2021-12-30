package com.qa.data;

public class UsersInfo {

	
	//POJO - plain old java object
	//we have to convert usersInfo to JSON //Marshaling 
String name;
String job;
int id;
String createdAt;

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getCreatedAt() {
	return createdAt;
}
public void setCreatedAt(String createdAt) {
	this.createdAt = createdAt;
}
public UsersInfo()
{
	
}
public UsersInfo(String name,String job)
{
	this.name=name;
	this.job=job;	
}


//getters and setter 
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getJob() {
	return job;
}
public void setJob(String job) {
	this.job = job;
}



}
