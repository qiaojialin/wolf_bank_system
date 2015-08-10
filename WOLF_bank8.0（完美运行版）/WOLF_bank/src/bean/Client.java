package bean;

public class Client {
	private String clientid ;
	private String clientname ;
	private String city ;
	private String address ;
	private String country ;
	private String job ;
	
	private String roleid ;
	private String useridentity;


	public void setUserid(String userid){
		this.clientid = userid ;
	}
	public void setName(String name){
		this.clientname = name ;
	}
	public void setPassword(String password){
		this.city = password ;
	}
	public void setSex(String sex){
		this.address = sex ;
	}
	public void setAge(String userage){
		this.country = userage ;
	}
	public void setPhone(String name){
		this.job = name ;
	}
	public void setRoleid(String name){
		this.roleid = name ;
	}
	public void setidentity(String name){
		this.useridentity = name ;
	}
	
	
	public String getUserid(){
		return this.clientid ;
	}
	public String getName(){
		return this.clientname ;
	}
	public String getPassword(){
		return this.city ;
	}
	public String getSex(){
		return this.address ;
	}
	public String getAge(){
		return this.country ;
	}
	public String getPhone(){
		return this.job ;
	}
	public String getRoleid(){
		return this.roleid ;
	}
	public String getIdentity(){
		return this.useridentity;
	}
}
