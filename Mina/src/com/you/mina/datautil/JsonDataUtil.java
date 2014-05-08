package com.you.mina.datautil;

import java.util.ArrayList;
import java.util.HashMap;

import com.you.mina.bean.EquipmentBean;
import com.you.mina.dbutil.SQLUtil;

import net.sf.json.JSONObject;

//����Json��ʽ���ݵĹ�����
public class JsonDataUtil {
	
	public JsonDataUtil(){}

	//���������ݴ���
	//������{"Type":"���մ�����","Name":"�������","Value":"92.6"}
	//���أ�{"Successful": true,"Message": null}
	
	ArrayList<EquipmentBean> arrayList = new ArrayList<EquipmentBean>();
	@SuppressWarnings("unused")
	public EquipmentBean sensorData(String JsonStr){
		JSONObject jsonObject = JSONObject.fromObject(JsonStr);
		EquipmentBean equipmentBean = new EquipmentBean();
		equipmentBean.setType(jsonObject.getString("Type"));
		equipmentBean.setName(jsonObject.getString("Name"));
		equipmentBean.setValue(jsonObject.getString("Value"));
		
		return equipmentBean;

	}
	//���������ݴ���
	//������[{"Type":"Switch","Name":"���⿪��1"��"Value":"Open/Close"}]
	//���أ�{"Successful": true,"Message": null}
	@SuppressWarnings("unused")
	public void controlData(String JsonStr){
		
	}
}
