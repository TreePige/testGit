package com.rongdu.eloan.modules.common.utils.parser;

/**
 * Class类型解析器
 * @author FHJ
 *
 */
public interface ClassTypeParser {
	<T> T parse(String content, Class<T> valueType);
}
