
package com.lin.entity;


public class UserEntity
{

	private String id;
	private String user_lid;
	private String user_l_leader;
	
	public String getUser_l_leader() {
		return user_l_leader;
	}

	public void setUser_l_leader(String user_l_leader) {
		this.user_l_leader = user_l_leader;
	}

	public String getUser_lid() {
		return user_lid;
	}

	public void setUser_lid(String user_lid) {
		this.user_lid = user_lid;
	}

	private String pas;
	private String name;
	private String user_danwei;
	public String getUser_danwei() {
		return user_danwei;
	}

	public void setUser_danwei(String user_danwei) {
		this.user_danwei = user_danwei;
	}

	private String position;
	private String bumen;
	private String positionname;
	private String role;
	private String ngnameflg;
	public String getNgnameflg() {
		return ngnameflg;
	}

	public void setNgnameflg(String ngnameflg) {
		this.ngnameflg = ngnameflg;
	}

	private String leader;
	private String leadername;
	private String manager;
	private String geofficer;
	public String getBumen() {
		return bumen;
	}

	public void setBumen(String bumen) {
		this.bumen = bumen;
	}
	public String getPas() {
		return pas;
	}

	public void setPas(String pas) {
		this.pas = pas;
	}

	public String getPositionname() {
		return positionname;
	}

	public void setPositionname(String positionname) {
		this.positionname = positionname;
	}
	public String getLeadername() {
		return leadername;
	}

	public void setLeadername(String leadername) {
		this.leadername = leadername;
	}
	public UserEntity()
	{
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	public String getRole()
	{
		return role;
	}

	public void setRole(String role)
	{
		this.role = role;
	}



	public String getLeader()
	{
		return leader;
	}

	public void setLeader(String leader)
	{
		this.leader = leader;
	}

	public String getManager()
	{
		return manager;
	}

	public void setManager(String manager)
	{
		this.manager = manager;
	}

	public String getGeofficer()
	{
		return geofficer;
	}

	public void setGeofficer(String geofficer)
	{
		this.geofficer = geofficer;
	}
}
