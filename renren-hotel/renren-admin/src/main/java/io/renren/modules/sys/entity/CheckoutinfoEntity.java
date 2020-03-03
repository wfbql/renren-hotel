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
 * @date 2020-03-02 08:45:38
 */
@Data
@TableName("checkoutinfo")
public class CheckoutinfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String orderid;
	/**
	 * 
	 */
	private String roomid;
	/**
	 * 
	 */
	private String userid;
	/**
	 * 
	 */
	private Integer paymoney;
	/**
	 * 
	 */
	private String transactor;

}
