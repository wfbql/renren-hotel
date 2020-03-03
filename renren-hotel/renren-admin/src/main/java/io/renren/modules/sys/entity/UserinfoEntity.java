package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * �û���Ϣ��
 * 
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-03-02 08:45:38
 */
@Data
@TableName("userinfo")
public class UserinfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String userid;
	/**
	 * 
	 */
	private String usertype;
	/**
	 * 
	 */
	private String username;
	/**
	 * 
	 */
	private String userpassword;
	/**
	 * 
	 */
	private String userpicaddress;
	/**
	 * 
	 */
	private String useremail;
	/**
	 * 
	 */
	private String userphone;
	/**
	 * 
	 */
	private Date usercarddate;
	/**
	 * 
	 */
	private String useridcard;

}
