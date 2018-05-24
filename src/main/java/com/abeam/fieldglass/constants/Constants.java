package com.abeam.fieldglass.constants;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>
 * Description
 * Copyright:	Copyright (c) 2015  
 * Company:		拍拍贷
 * Author:		denghongbing
 * Version:		1.0  
 * Create at:	2015年8月31日 上午11:22:55  
 *  
 * 修改历史:
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------
 * 
 * </pre>
 */
public class Constants {

	/**
	 * 终端类型
	 */
	public static final class FORCAST_PERIOD_TYPE {

	}

	/**
	 * 预测时长
	 * 
	 */
	public static final class TERMINAL_TYPE_Ban {

		@ConstantTag(name = "App-Ios", type = "TERMINAL_TYPE_Ban")
		public static final String TERMINAL_TYPE_Ban_2 = "2";

		@ConstantTag(name = "App-Android", type = "TERMINAL_TYPE_Ban")
		public static final String TERMINAL_TYPE_Ban_3 = "3";

		@ConstantTag(name = "App公用", type = "TERMINAL_TYPE_Ban")
		public static final String TERMINAL_TYPE_Ban_4 = "4";
	}

	/**
	 * 预测来源
	 * 
	 */
	public static final class FORCAST_FROM_TYPE {

		@ConstantTag(name = "主动预测", type = "FORCAST_FROM_TYPE")
		public static final String FORCAST_FROM_TYPE_1 = "1";

		@ConstantTag(name = "pk预测", type = "FORCAST_FROM_TYPE")
		public static final String FORCAST_FROM_TYPE_2 = "2";

	}

	/**
	 * 预测走势
	 * 
	 */
	public static final class FORCAST_TYPE {

		@ConstantTag(name = "我猜涨", type = "FORCAST_TYPE")
		public static final String FORCAST_TYPE_1001 = "1001";

		@ConstantTag(name = "我猜跌", type = "FORCAST_TYPE")
		public static final String FORCAST_TYPE_1002 = "1002";

		@ConstantTag(name = "我猜平", type = "FORCAST_TYPE")
		public static final String FORCAST_TYPE_1003 = "1003";

	}

	/**
	 * 通用是否
	 * 
	 */
	public static final class IS_TRUE {

		@ConstantTag(name = "是", type = "IS_TRUE")
		public static final String IS_TRUE_1 = "1";

		@ConstantTag(name = "否", type = "IS_TRUE")
		public static final String IS_TRUE_0 = "0";

	}

	/**
	 * 用户注册终端
	 */
	public static final class TERMINAL_TYPE {
		@ConstantTag(name = "IOS", type = "TERMINAL_TYPE")
		public static final String TERMINAL_TYPE_IOS = "2";

		@ConstantTag(name = "Android", type = "TERMINAL_TYPE")
		public static final String TERMINAL_TYPE_ANDROID = "3";

	}

	/**
	 * 收支类型
	 */
	public static final class BALANCE_TYPE {

		@ConstantTag(name = "收入", type = "BALANCE_TYPE")
		public static final String BALANCE_TYPE_IN = "1";

		@ConstantTag(name = "支出", type = "BALANCE_TYPE")
		public static final String BALANCE_TYPE_OUT = "2";

	}

	/**
	 * 金币类型
	 */
	public static final class COIN_TYPE {

		@ConstantTag(name = "注册", type = "COIN_TYPE")
		public static final String COIN_TYPE_1001 = "1001";

		@ConstantTag(name = "签到", type = "COIN_TYPE")
		public static final String COIN_TYPE_1002 = "1002";

		@ConstantTag(name = "发布预测", type = "COIN_TYPE")
		public static final String COIN_TYPE_1003 = "1003";

		@ConstantTag(name = "预测结果", type = "COIN_TYPE")
		public static final String COIN_TYPE_1004 = "1004";

		@ConstantTag(name = "邀请好友PK", type = "COIN_TYPE")
		public static final String COIN_TYPE_1005 = "1005";

		@ConstantTag(name = "参与PK", type = "COIN_TYPE")
		public static final String COIN_TYPE_1006 = "1006";

	}

	/**
	 * 定时任务
	 */
	public static final class JOB_TYPE {

		@ConstantTag(name = "发布日收盘价", type = "JOB_TYPE")
		public static final String JOB_TYPE_1001 = "1001";

		@ConstantTag(name = "预测开奖", type = "JOB_TYPE")
		public static final String JOB_TYPE_1002 = "1002";

		@ConstantTag(name = "金币奖励上限", type = "JOB_TYPE")
		public static final String JOB_TYPE_1003 = "1003";

	}

	private static Map<Object, String> typeData = new LinkedHashMap<Object, String>();

	private static Map<Object, List<Map<Object, String>>> listData = new LinkedHashMap<Object, List<Map<Object, String>>>();

	// 初始化
	static {
		Class<Constants> clazz = Constants.class;
		Class<?>[] clazzs = clazz.getDeclaredClasses();

		for (int i = 0; i < clazzs.length; i++) {

			Field[] fields = clazzs[i].getDeclaredFields();

			for (int j = 0; j < fields.length; j++) {
				Field field = fields[j];

				// 查看当前域是否存在常量定义标签
				ConstantTag constantsTag = field.getAnnotation(ConstantTag.class);
				if (constantsTag == null) {
					continue;
				}

				// 判断域是否为静态类型
				if (!Modifier.isStatic(field.getModifiers())) {
					throw new RuntimeException("类型[" + field.getDeclaringClass() + "]的域[" + field + "]不是静态类型,无法取得其值!");
				}

				// 取得域的值
				Object constantsValue;
				try {
					constantsValue = field.get(null);
				} catch (Exception e) {
					throw new RuntimeException("取得静态域[" + field + "]的值时发生异常:", e);
				}

				String type = constantsTag.type();
				String name = constantsTag.name();
				String value = String.valueOf(constantsValue);

				// 存值
				String key = generatorKey(type, value);
				String obj = typeData.get(key);
				if (null != obj) {
					throw new RuntimeException("KEY [" + key + "]之前已经存在");
				}
				typeData.put(key, name);

				// 存列表值
				List<Map<Object, String>> list = listData.get(type);

				if (null == list) {
					// 之前不存在则创建一个list
					list = new ArrayList<Map<Object, String>>();
					listData.put(type, list);
				}

				Map<Object, String> map = new HashMap<Object, String>();
				map.put(value, name);
				list.add(map);
			}
		}
	}

	// 生成key
	private static String generatorKey(String type, String value) {
		return type + "$" + value;
	}

	/**
	 * 翻译
	 * 
	 * @param type
	 * @param value
	 * @return
	 */
	public static String getConstantName(String type, String value) {
		return typeData.get(generatorKey(type, value));
	}

	/**
	 * 翻译
	 * 
	 * @param type
	 * @param value
	 * @return
	 */
	public static String getConstantName(String type, Long value) {
		return typeData.get(generatorKey(type, value.toString()));
	}

	/**
	 * 获取列表
	 * 
	 * @param type
	 * @return
	 */
	public static List<Map<Object, String>> getConstantList(String type) {
		return listData.get(type);
	}

	/**
	 * 获取列表
	 * 
	 * @param type
	 * @return
	 */
	public static Map<Object, String> getConstantMap(String type) {
		List<Map<Object, String>> list = listData.get(type);
		if (null == list || list.isEmpty()) {
			return null;
		}
		Map<Object, String> result = new LinkedHashMap<Object, String>();
		for (Map<Object, String> map : list) {
			result.putAll(map);
		}
		return result;
	}

	/**
	 * 转换优惠券使用范围
	 * 
	 * @param ranges
	 * @return
	 */
	public static String changeUseRange(String[] ranges) {
		String values = "";
		for (int j = 0; j < ranges.length; j++) {
			String a = ranges[j];
			values += Constants.getConstantName("PRODUCT_TYPE", a.trim()) + "|";
		}
		return values.substring(0, values.length() - 1);
	}

	public static void main(String[] args) {
		// String ss = "";
		// Test
		// String v = Constants.AA.STATUS_0;
		// System.out.println(v);
		// System.out.println("基金类型转码：" + getConstantName("FUND_TYPE_CODE",
		// Constants.FUND_TYPE_CODE.FUND_TYPE_1003));
		// System.out.println("中文" + getConstantName("UC_SMS_STATUS",
		// Constants.UC_SMS.STATUS_0002));
		// System.out.println(Arrays.toString(getConstantList("BUS_TRANS_TYPE").toArray()));
		// System.out.println(Arrays.toString(getConstantList("BUS_TRANS_TYPE").toArray()));
		//
		// System.out.println(getConstantMap("USER_REG_TERMINAL").toString());
	}
}
