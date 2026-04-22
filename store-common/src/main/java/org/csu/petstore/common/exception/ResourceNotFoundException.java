package org.csu.petstore.common.exception;

/**
 * 资源未找到异常
 */
public class ResourceNotFoundException extends BusinessException {

    public ResourceNotFoundException() {
        super(404, "资源不存在");
    }

    public ResourceNotFoundException(String message) {
        super(404, message);
    }

    public ResourceNotFoundException(String resourceName, Long id) {
        super(404, String.format("%s不存在，ID: %d", resourceName, id));
    }

    public ResourceNotFoundException(String resourceName, String identifier) {
        super(404, String.format("%s不存在，标识：%s", resourceName, identifier));
    }
}
