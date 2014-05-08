package com.you.mina.dbutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;


import com.you.mina.bean.EquipmentBean;

/**
 * @author lucher 
 * ����Ϊsql�����࣬����sql�޲Ρ��вβ�ѯ���Լ�sql������䷽�� 
 * ����һ�����DBUtilʹ�ã�ֻ���о��˳��õķ���
 */
public class SQLUtil {

	// �޲����Ĳ�ѯ���
	private static final String sql1 = "select * from Equipment";

	// �в����Ĳ�ѯ���
	private static final String sql2 = "select * from ControlModel";
	
	//����������������״̬��¼
	private static final String sql3 = "select top 1 * from YanWuData  order by YanWuDataId  desc";

	// �������
	private static final String sql4 = "update ControlModel set ControlModel=? where ControlModelId=?";

	// ɾ����䣬ͬʱִ��
	private static final String sql5 = "delete from user where id=?";
	private static final String sql6 = "delete from userInfo where id=?";
	
	/**
	 * ��Ӵ������෽��
	 * @param ChuanGanModel����
	 * @return ִ����ӵĽ��
	 */
	public boolean addEquipmentBean(EquipmentBean equipmentBean) {
		
		String sql = "insert into equipmentBean (Type,Name,Value) values (?,?,?)";
		int result = 0;
		boolean flag = true;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil.getSQLSERVERConnection();
//			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(sql);
			System.out.println(sql);
		
			pstmt.setString(1, equipmentBean.getType());
			pstmt.setString(2, equipmentBean.getName());
			pstmt.setString(3, equipmentBean.getValue());

			result = pstmt.executeUpdate();
			
//			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			DBUtil.closeConn(pstmt, conn);
		}

		if (result == 0 ) {
			flag = false;
		}
		return flag;
	}
	
	
	
	/**
	 * ��Ӵ������෽��
	 * @param ChuanGanModel����
	 * @return ִ����ӵĽ��
	 */
	public void addEquipmentBeanList(ArrayList<EquipmentBean> arrayList) {
		String sql = "insert into equipmentBean (Type,Name,Value) values ";
		for(int i = 0 ;i < arrayList.size();i++){
			sql = sql + "("+"'"+arrayList.get(i).getType()+"'"+","+"'"+arrayList.get(i).getName()+"'"+","+"'"+arrayList.get(i).getValue()+"'"+"),";
			if(i==arrayList.size()-1){
				sql = sql + "("+"'"+arrayList.get(i).getType()+"'"+","+"'"+arrayList.get(i).getName()+"'"+","+"'"+arrayList.get(i).getValue()+"'"+")";
			}
		}
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil.getSQLSERVERConnection();
			
			pstmt = conn.prepareStatement(sql);
			System.out.println(sql);
		
			pstmt.executeUpdate();
            System.out.println("�ɹ���⣡");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(pstmt, conn);
			arrayList.clear();
		}
	}
	
	
	
	/**
	 * �޲�����ѯ,�������п����豸����
	 * 
	 * @return ���м�¼�����ֵ�list
	 */
//	public static List<String> getAllControlModelState() {
//
//		Connection conn = null;
//		Statement ps = null;
//		ResultSet rs = null;
//		List<String> info = new ArrayList<String>();
//
//		try {
//			conn = DBUtil.getSQLSERVERConnection();
//			ps = conn.createStatement();
//			rs = ps.executeQuery(sql2);
//
//			while (rs.next()) {
//				// ���ݾ������������ȡ�����������ͣ�
//				info.add(rs.getString("ControlModelState")); 
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			DBUtil.closeConn(rs, ps, conn);
//		}
//		return info;
//	}
	


	/**
	 * �в�����ѯ,�������;�����Լ��޸�
	 * 
	 * @param ModelType
	 * @param ModelWhere
	 * @return ����ָ���ֶεļ�¼list
	 */
//	public static List<String> getAllByMM(String ModelType, String ModelWhere) {
//
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		List<String> info = new ArrayList<String>();
//
//		try {
//			conn = DBUtil.getSQLSERVERConnection();
//			pstmt = conn.prepareStatement(sql1);
//			pstmt.setString(1, ModelType);
//			pstmt.setString(2, ModelWhere);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				info.add(rs.getString(1));
//				info.add(rs.getString(2));
//			}
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			DBUtil.closeConn(rs, pstmt, conn);
//		}
//
//		return info;
//	}

	

//	/**
//	 * �����෽��,ͬʱִ�����������
//	 * @param ModelType
//	 * @return ִ��ɾ���Ľ��
//	 */
//	public static boolean deleteByModelType(String ModelType) {
//		
//		int result1 = 0;
//		int result2 = 0;
//		boolean flag = true;
//
//		Connection conn = null;
//		PreparedStatement pstmt1 = null;
//		PreparedStatement pstmt2 = null;
//
//		try {
//			conn = DBUtil.getSQLSERVERConnection();
//			conn.setAutoCommit(false);
//
//			pstmt1 = conn.prepareStatement(sql4);
//			pstmt1.setString(1, ModelType);
//
//			pstmt2 = conn.prepareStatement(sql5);
//			pstmt2.setString(1, ModelType);
//
//			result1 = pstmt1.executeUpdate();
//			result2 = pstmt2.executeUpdate();
//
//			conn.commit();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			try {
//				conn.rollback();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		} finally {
//			DBUtil.closeConn(pstmt1, pstmt2, conn);
//		}
//
//		if (result1 == 0 || result2 == 0) {
//			flag = false;
//		}
//		return flag;
//	}
}
