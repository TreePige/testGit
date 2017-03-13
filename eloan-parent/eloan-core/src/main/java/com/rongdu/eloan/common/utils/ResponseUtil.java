package com.rongdu.eloan.common.utils;

import java.util.HashMap;
import java.util.Map;

import com.rongdu.eloan.common.context.Constant;

public class ResponseUtil {

	public static Map<String, Object> getResponseSuccess( String msg) {

		Map<String, Object> map = new HashMap<>();
		map.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		map.put(Constant.RESPONSE_CODE_MSG, msg);
		return map;
	}

	public static Map<String, Object> getResponseFail(int code,String msg) {

		Map<String, Object> map = new HashMap<>();
		map.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
		map.put(Constant.RESPONSE_CODE_MSG, msg);
		return map;
	}

	public static Map<String, Object> getPageListResponseSuccess(String data,
			String totalCount) {

		Map<String, Object> map = new HashMap<>();
		map.put(Constant.RESPONSE_DATA, data);
		map.put(Constant.RESPONSE_DATA_TOTALCOUNT, totalCount);
		map.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		map.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);

		return map;
	}
	
	public static Map<String, Object> getResponseFail(String msg) {

		Map<String, Object> map = new HashMap<>();
		map.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
		map.put(Constant.RESPONSE_CODE_MSG, msg);
		return map;
	}
}
