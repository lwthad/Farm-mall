package com.farm.pojo;

/**
 * tb_admin
 * @author 
 */
public class TbAdmin  {
    /**
     * 管理员登录名
     */
    private String adname;

    private String adpassword;

    
    public TbAdmin() {
		super();
	}

	public TbAdmin(String adname, String adpassword) {
		super();
		this.adname = adname;
		this.adpassword = adpassword;
	}

	public String getAdname() {
        return adname;
    }

    public void setAdname(String adname) {
        this.adname = adname;
    }

    public String getAdpassword() {
        return adpassword;
    }

    public void setAdpassword(String adpassword) {
        this.adpassword = adpassword;
    }
}