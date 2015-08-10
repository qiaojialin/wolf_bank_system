package bean;

public class User {
	private String userid ;
	private String name ;
	private String password ;
	private String usersex ;
	private String userage ;
	private String userphone ;
	private String roleid ;
	private String useridentity;
//	public User(String userid,String password  ){
//		this.userid=userid;
//		this.password=password;
//	}

	public void setUserid(String userid){
		this.userid = userid ;
	}
	public void setName(String name){
		this.name = name ;
	}
	public void setPassword(String password){
		this.password = password ;
	}
	public void setSex(String sex){
		this.usersex = sex ;
	}
	public void setAge(String userage){
		this.userage = userage ;
	}
	public void setPhone(String name){
		this.userphone = name ;
	}
	public void setRoleid(String name){
		this.roleid = name ;
	}
	public void setidentity(String name){
		this.useridentity = name ;
	}
	
	
	public String getUserid(){
		return this.userid ;
	}
	public String getName(){
		return this.name ;
	}
	public String getPassword(){
		return this.password ;
	}
	public String getSex(){
		return this.usersex ;
	}
	public String getAge(){
		return this.userage ;
	}
	public String getPhone(){
		return this.userphone ;
	}
	public String getRoleid(){
		return this.roleid ;
	}
	public String getIdentity(){
		return this.useridentity;
	}
}
