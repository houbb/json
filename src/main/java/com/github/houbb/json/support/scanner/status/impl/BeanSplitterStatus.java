package com.github.houbb.json.support.scanner.status.impl;

import com.github.houbb.heaven.annotation.NotThreadSafe;
import com.github.houbb.heaven.constant.CharConst;
import com.github.houbb.json.constant.JsonBeanConst;
import com.github.houbb.json.exception.JsonRespCode;
import com.github.houbb.json.exception.JsonRuntimeException;
import com.github.houbb.json.support.scanner.status.IBeanSplitterStatus;
import com.github.houbb.json.support.scanner.status.IDoubleQuotesStatus;

import java.util.Stack;

/**
 * 状态存在共享，需要 new 实例
 * <p> project: json-DoubleQuotesStatus </p>
 * <p> create on 2019/11/9 20:40 </p>
 *
 * @author Administrator
 * @since 0.1.4
 */
@NotThreadSafe
public class BeanSplitterStatus implements IBeanSplitterStatus {

    /**
     * 状态内部枚举
     * @since 0.1.4
     */
    private StatusEnum statusEnum = StatusEnum.INIT;

    /**
     * 存放字符的堆栈
     * @since 0.1.4
     */
    private Stack<Character> stack = new Stack<>();

    @Override
    public boolean isBeanStart() {
        return StatusEnum.START.equals(statusEnum);
    }

    @Override
    public boolean isBeanEnd() {
        return StatusEnum.END.equals(statusEnum);
    }

    @Override
    public void currentChar(char c) {
        // 如果是 {，则进行入栈
        if(JsonBeanConst.C_START == c) {
            stack.push(c);

            // 处于初始化，则设置为开始
            if(StatusEnum.INIT == statusEnum) {
                this.statusEnum = StatusEnum.START;
            }
        } else if(JsonBeanConst.C_END == c) {
            if(stack.isEmpty()) {
                throw new JsonRuntimeException(JsonRespCode.DES_NOT_MATCH_BEAN_END);
            }
            char popChar = stack.pop();

            // 处理已经开始，且经过这次 pop，集合变为空。
            if(stack.isEmpty() && isBeanStart()) {
                this.statusEnum = StatusEnum.END;
            }
        } else {
            // 如果是其他情况
            if(StatusEnum.END == statusEnum) {
                statusEnum = StatusEnum.INIT;
            }
        }
    }

    /**
     * 状态枚举
     * @since 0.1.4
     */
    private enum StatusEnum {
        INIT,
        START,
        END,
        ;
    }

}
