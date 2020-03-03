package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-03-02 08:47:08
 */
@Data
@TableName("roominfo")
public class RoominfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String roomid;
	/**
	 * 
	 */
	private String roomtype;
	/**
	 * 
	 */
	private Double roomprice;
	/**
	 * 
	 */
	private Integer roomstatus;
	/**
	 * 
	 */
	private Integer roomnumber;

}
