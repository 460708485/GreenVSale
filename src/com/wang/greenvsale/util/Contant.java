package com.wang.greenvsale.util;
/**
 * 常量类
 * @author wang
 *
 */
public class Contant {
	
	//根据id获取用户信息
	public static final String getUserById="http://192.168.1.102:8080/GreenSale/getUserByID.do?id=1";
	//获取种植户信息
	public static final String getWholesale="http://192.168.1.102:8080/GreenSale/WholeSale/getWholeSales.do";
	//获取好评的种植户信息
	public static final String getGoodWholesale="http://192.168.1.102:8080/GreenSale/WholeSale/getGoodWholeSales.do";
	//获取种植户拥有的产品
	public static final String getGoodsByWholesaleId="http://192.168.1.102:8080/WholeSale/getWholeSaleById.do?id=1";
	//获取分类下的产品
	public static final String getGoodsByClass="http://192.168.1.102:8080/GreenSale/product/getProductByClassId.do?class_id=1s";
	//获取产品信息
	public static final String getGoodsInfo="http://192.168.1.102:8080/GreenSale/product/getProductById.do?product_id=1";
	//获取分类下所有的产品信息
	public static final String getGoodsFromClass="http://192.168.1.102:8080/GreenSale/product/getWProductByClassId.do?class_id=1";
	

}
