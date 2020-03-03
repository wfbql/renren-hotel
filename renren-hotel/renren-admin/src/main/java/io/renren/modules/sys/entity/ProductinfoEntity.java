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
 * @date 2020-02-04 20:58:47
 */
@Data
@TableName("productinfo")
public class ProductinfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer productid;
	/**
	 * 
	 */
	private Integer producttype;
	/**
	 * 
	 */
	private String productname;
	/**
	 * 
	 */
	private Integer productprice;
	/**
	 * 
	 */
	private Integer productnumber;
	/**
	 * 
	 */
	private String productimagepath;
	/**
	 * 
	 */
	private Integer productsalenumber;
	/**
	 * 
	 */
	private Integer consumepoint;

}
