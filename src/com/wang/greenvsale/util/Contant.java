package com.wang.greenvsale.util;
/**
 * ������
 * @author wang
 *
 */
public class Contant {
	
	//����id��ȡ�û���Ϣ
	public static final String getUserById="http://192.168.1.102:8080/GreenSale/getUserByID.do?id=1";
	//��ȡ��ֲ����Ϣ
	public static final String getWholesale="http://192.168.1.102:8080/GreenSale/WholeSale/getWholeSales.do";
	//��ȡ��������ֲ����Ϣ
	public static final String getGoodWholesale="http://192.168.1.102:8080/GreenSale/WholeSale/getGoodWholeSales.do";
	//��ȡ��ֲ��ӵ�еĲ�Ʒ
	public static final String getGoodsByWholesaleId="http://192.168.1.102:8080/WholeSale/getWholeSaleById.do?id=1";
	//��ȡ�����µĲ�Ʒ
	public static final String getGoodsByClass="http://192.168.1.102:8080/GreenSale/product/getProductByClassId.do?class_id=1s";
	//��ȡ��Ʒ��Ϣ
	public static final String getGoodsInfo="http://192.168.1.102:8080/GreenSale/product/getProductById.do?product_id=1";
	//��ȡ���������еĲ�Ʒ��Ϣ
	public static final String getGoodsFromClass="http://192.168.1.102:8080/GreenSale/product/getWProductByClassId.do?class_id=1";
	

}
