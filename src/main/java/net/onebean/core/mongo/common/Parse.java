package net.onebean.core.mongo.common;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 类型转换
 * 
 * @author Admin
 * 
 */
public class Parse {
	/**
	 * 将任意类型强制转换成double型, 如果值为空返回零
	 * 
	 * @param value
	 * @return
	 */
	public static double toDouble(Object value) {
		if (value == null)
			return 0;
		double d = 0;
		if (value != null) {
			String temp = value.toString();
			value = temp.replace(",", "");
		}
		try {
			d = Double.parseDouble(String.valueOf(value).toString());
		} catch (Exception e) {
		}
		return d;
	}

	/**
	 * 转换成double
	 * 
	 * @param value
	 * @param dig
	 *            指定小数位数
	 */
	public static double toDouble(Object value, int dig) {
		double d = toDouble(value);
		return toDouble(d, dig);
	}

	/**
	 * 四舍五入保留小数
	 * 
	 * @param d
	 * @param dig
	 * @return
	 */
	public static double toDouble(double d, int dig) {
		BigDecimal bd = new BigDecimal(d);
		BigDecimal bd1 = bd.setScale(dig, BigDecimal.ROUND_HALF_UP);
		d = bd1.doubleValue();
		return toBigDecimal(d, dig).doubleValue();
	}

	/**
	 * 四舍五入保留小数
	 * 
	 * @param d
	 * @param dig
	 * @return
	 */
	public static BigDecimal toBigDecimal(double d, int dig) {
		BigDecimal bd = new BigDecimal(d);
		return bd.setScale(dig, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 四舍五入保留小数
	 * 
	 * @param d
	 * @param dig
	 * @return
	 */
	public static BigDecimal toBigDecimal(BigDecimal d, int dig) {
		return toBigDecimal(d.doubleValue(), dig);
	}

	/**
	 * 将任意类型强制转换成int型, 如果值为空返回零
	 * 
	 * @param value
	 * @return
	 */
	public static int toInt(Object value) {
		if (value == null)
			return 0;
		if (value instanceof Double) {
			value = new BigDecimal((Double) value);
		}
		if (value instanceof BigDecimal)
			return ((BigDecimal) value).intValue();
		int d = 0;
		if (value != null) {
			String temp = value.toString();
			value = temp.replace(",", "");
		}
		try {
			d = Integer.parseInt(value + "");
		} catch (Exception e) {
			d = 0;
		}
		return d;
	}

	/**
	 * 字符串转布尔
	 * 
	 * @param param
	 * @return
	 */
	public static boolean toBoolean(Object value) {
		boolean b = false;
		try {
			b = Boolean.parseBoolean(String.valueOf(value).toString());
		} catch (Exception e) {
		}
		return b;
	}

	/**
	 * 转码
	 * 
	 * @param str
	 * @return
	 */
	public static String transCode(String str) {
		if (str == null)
			return null;
		try {
			str = new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str.replaceAll(".*([';]+|(--)+).*", " ");// 替换非法字符
	}

	/**
	 * URL参数 解码
	 * 
	 * @param keyword
	 * @return
	 */
	public static String URLDecode(String keyword) {
		try {
			keyword = URLDecoder.decode(keyword, "utf-8");
			return URLDecoder.decode(keyword, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			//
		}
		return null;
	}

	/**
	 * 验证是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(Object str) {
		try {
			Long.parseLong(str.toString());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 将任意类型强制转换成long型, 如果值为空返回零
	 * 
	 * @param value
	 * @return
	 */
	public static long toLong(Object value) {
		if (value == null)
			return 0l;
		long l = 0l;
		try {
			l = Long.parseLong(value.toString());
		} catch (Exception e) {
		}
		return l;
	}

	/**
	 * 字符串转换成日期 如果转换格式为空，则利用默认格式进行转换操作
	 * 
	 * @param str
	 *            字符串
	 * @param format
	 *            日期格式
	 * @return 日期
	 * @throws ParseException
	 */
	public static Timestamp toTimestamp(String str) {
		String format = "yyyy-MM-dd";
		if (null == str || "".equals(str)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sdf.parse(str);
			return new Timestamp(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 格式化为人民币最小单位分，舍弃多余小数位，小数位不进行四舍五入
	 * 
	 * @param price
	 * @return
	 */
	public static Float toMoney(Float price) {
		if (price == null)
			return 0.0f;
		if (price < 1) {
			price = Float.parseFloat(String.format("%.2f", price));
		} else {
			DecimalFormat df = new DecimalFormat();
			df.setMaximumFractionDigits(2);
			df.setGroupingSize(0);
			df.setRoundingMode(RoundingMode.FLOOR);
			price = Float.valueOf(df.format(price));
		}
		return price;
	}
	
	
	/**
	 * 数值格式化为 万单位
	 * @param num
	 * @return
	 */
	public static String formatNumber(Object value) {
		try {
			if (value == null){
				return null;
			}
			Double num=null;
			
			num = Double.parseDouble(value.toString());
			
			
			num = Math.floor(num);
			if (num < 10000d) {
				return Double.toString(num);
			}else{
				BigDecimal bd = new BigDecimal((num/10000d));
				double d=bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				return d+"万";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
