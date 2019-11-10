package com.github.houbb.json.support.scanner.status.impl;

import com.github.houbb.heaven.constant.CharConst;
import com.github.houbb.json.constant.JsonBeanConst;
import com.github.houbb.json.constant.JsonIterableConst;
import com.github.houbb.json.support.scanner.status.IBeanFieldStatus;

import java.util.Stack;

/**
 * bean field 状态管理
 *
 * @author binbin.hou
 * @since 0.1.4
 */
public class BeanFieldStatus implements IBeanFieldStatus {

    /**
     * 当前状态
     *
     * @since 0.1.4
     */
    private StatusEnum status = StatusEnum.KEY_INIT;

    /**
     * 特殊字符堆栈
     * （1）用来存储 [] {}
     * （）如果列表不为空，那么相关的状态转换就可以忽略。
     *
     * @since 0.1.4
     */
    private Stack<Character> valueSpecialStack = new Stack<>();

    @Override
    public boolean isKeyStart() {
        return StatusEnum.KEY_START.equals(status);
    }

    @Override
    public boolean isKeyEnd() {
        return StatusEnum.KEY_END.equals(status);
    }

    @Override
    public boolean isValueStart() {
        return StatusEnum.VALUE_START.equals(status);
    }

    @Override
    public boolean isValueEnd() {
        return StatusEnum.VALUE_END.equals(status);
    }

    @Override
    public void currentChar(char c) {
        //1. 如果是 key-init
        // 按照状态来处理
        switch (status) {
            // 开始和结束，就是一个循环
            case KEY_INIT:
            case VALUE_END:
                if (CharConst.DOUBLE_QUOTES == c) {
                    this.status = StatusEnum.KEY_START_PRE;
                }
                break;
            case KEY_START_PRE:
                if (CharConst.DOUBLE_QUOTES == c) {
                    this.status = StatusEnum.KEY_END;
                } else {
                    this.status = StatusEnum.KEY_START;
                }
                break;
            case KEY_START:
                if (CharConst.DOUBLE_QUOTES == c) {
                    this.status = StatusEnum.KEY_END;
                }
                break;
            case KEY_END:
                if (CharConst.COLON == c) {
                    this.status = StatusEnum.VALUE_START_PRE;
                }
                break;
            case VALUE_START_PRE:
                // 无特殊符号，且为逗号，说明遍历结束
                if (CharConst.COMMA == c
                    && this.valueSpecialStack.isEmpty()) {
                    this.status = StatusEnum.VALUE_END;
                } else {
                    this.setValueSpecialStack(c);
                    this.status = StatusEnum.VALUE_START;
                }
                break;
            case VALUE_START:
                // 无特殊符号，且为逗号，说明遍历结束
                if (CharConst.COMMA == c
                        && this.valueSpecialStack.isEmpty()) {
                    this.status = StatusEnum.VALUE_END;
                } else {
                    this.setValueSpecialStack(c);
                }
                break;
            default:
                //do nothing.
        }
    }

    /**
     * 添加值-特殊符号
     * @param c 当前符号
     * @since 0.1.4
     */
    private void setValueSpecialStack(final char c) {
        if (JsonIterableConst.C_START == c
                || JsonBeanConst.C_START == c) {
            this.valueSpecialStack.push(c);
        }
        if(JsonIterableConst.C_END == c
                || JsonBeanConst.C_END == c) {
            this.valueSpecialStack.pop();
        }
    }

    /**
     * value 状态
     *
     * @since 0.1.4
     */
    private enum StatusEnum {
        KEY_INIT,
        KEY_START_PRE,
        KEY_START,
        KEY_END,
        VALUE_START_PRE,
        VALUE_START,
        VALUE_END,
    }

}
