package com.github.houbb.json.support.scanner.status.impl;

import com.github.houbb.heaven.annotation.NotThreadSafe;
import com.github.houbb.heaven.constant.CharConst;
import com.github.houbb.json.support.scanner.status.IDoubleQuotesStatus;

/**
 * 状态存在共享，需要 new 实例
 * <p> project: json-DoubleQuotesStatus </p>
 * <p> create on 2019/11/9 20:40 </p>
 *
 * @author Administrator
 * @since 0.1.4
 */
@NotThreadSafe
public class DoubleQuotesStatus implements IDoubleQuotesStatus {

    /**
     * 状态内部枚举
     * @since 0.1.4
     */
    private StatusEnum statusEnum = StatusEnum.INIT;

    /**
     * 前一个字符
     * @since 0.1.4
     */
    private char preChar = CharConst.BLANK;

    @Override
    public boolean isInQuote() {
        return StatusEnum.START.equals(statusEnum);
    }

    @Override
    public void currentChar(char c) {
        //1. 获取上一个字符
        preChar = getPreChar(preChar, c);


        //2. 状态切换
        //上一个字符不是转义，且当前为 "。则进行状态的切换
        if (CharConst.BACK_SLASH != preChar
                && CharConst.DOUBLE_QUOTES == c) {
            this.statusEnum = StatusEnum.getNextStatus(this.statusEnum);
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

        /**
         * 获取下一个状态
         * @param statusEnum 状态美剧
         * @return 状态
         * @since 0.1.4
         */
        public static StatusEnum getNextStatus(final StatusEnum statusEnum) {
            if(INIT.equals(statusEnum)) {
                return START;
            }
            if(START.equals(statusEnum)) {
                return END;
            }
            if(END.equals(statusEnum)) {
                return START;
            }

            throw new UnsupportedOperationException();
        }
    }

    /**
     * 获取上一个字符
     *
     * 保证转义字符的两次抵消。
     *
     * @param preChar     上一个字符
     * @param currentChar 当前字符
     * @return 结果
     * @since 0.1.27
     */
    private static char getPreChar(final char preChar, final char currentChar) {
        // 判断前一个字符是什么
        if (CharConst.BACK_SLASH == preChar
                && CharConst.BACK_SLASH == currentChar) {
            return CharConst.BLANK;
        }
        return currentChar;
    }

}
