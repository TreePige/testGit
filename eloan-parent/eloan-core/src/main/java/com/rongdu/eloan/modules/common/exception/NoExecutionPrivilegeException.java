package com.rongdu.eloan.modules.common.exception;

import com.rongdu.eloan.common.exception.ErongBaseException;

/**
 * 没有执行权力 Exception
 * @author FHJ
 *
 */
public class NoExecutionPrivilegeException extends ErongBaseException{
	public NoExecutionPrivilegeException() {
	}

	public NoExecutionPrivilegeException(String message) {
		super(message);
	}

	public NoExecutionPrivilegeException(String message, Throwable cause) {
		super(message, cause);
	}
}
