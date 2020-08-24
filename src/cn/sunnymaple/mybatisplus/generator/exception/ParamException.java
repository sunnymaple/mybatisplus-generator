package cn.sunnymaple.mybatisplus.generator.exception;

/**
 * 参数输入异常
 * @author wangzb
 * @date 2019-01-24 13:44
 * @company 矽甲（上海）信息科技有限公司
 */
public class ParamException extends Exception {

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public ParamException(String message) {
        super(message);
    }
}
