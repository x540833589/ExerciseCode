package cn.com.shoppingmall.utils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import cn.com.shoppingmall.form.RegisterForm;

public class ReviseFormUtils {
	
	/**
	 * 注册表单信息校正方法
	 * @param registerForm 用户提交的注册表单
	 * @param errorMessages 错误信息提示列表，该列表的键名就是错误字段的名字
	 * */
	public static RegisterForm reviseRegisterForm(RegisterForm registerForm , Map<String , String> errorMessages) {
		Set<String> errorFieldSet = errorMessages.keySet();		//获取错误字段名的集合
		Class<RegisterForm> clz = RegisterForm.class;
		RegisterForm newRegisterForm = new RegisterForm();		//用于保存校正后的相关信息的新注册表单
		newRegisterForm = registerForm;							//接收用户输入的注册表单
		Field[] fields = clz.getDeclaredFields();				//获得注册表单声明的所有字段的数组
		for(String errorFieldName : errorFieldSet) {			//获取错误信息提示列表中的字段名
			for(Field field : fields) {							//遍历注册表单所有字段
				String cuurentFieldName = field.getName();		//保存注册表单元对象当前字段名
				if(cuurentFieldName.equals(errorFieldName)) {	//匹配到当前错误字段
					field.setAccessible(true);					//外界直接访问
					try {
						field.set(newRegisterForm , null);		//将错误字段清空
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				}
			}
		}
		//返回校正好的注册表单
		return newRegisterForm;
	}

}
