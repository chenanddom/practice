package com.itdom.linkedlist;

import org.apache.commons.lang3.StringUtils;

/**
 * 括号匹配
 * 实现步骤。
 * 1.遍历字符串中的每一个字符
 * 2. 如果是左括号加入栈中
 * 3. 如果是右括号从栈中取出一个元素
 * 4. 如果遍历完字符串中的每一个元素栈内的元素为空了就匹配，否则不匹配
 *
 *
 *
 *
 */
public class BracketsMatch {
    public static void main(String[] args) {
        String content = "(dadasd(adasdsa()))";
        System.out.println(isMatch(content));

    }

    public static boolean isMatch(String content){
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < content.length(); i++) {
            String str = content.charAt(i)+"";
            if ("(".equals(str)){
                //左括号进栈
                stack.push(str);
            }else if (")".equals(str)){
                String s = stack.pop();
                if (StringUtils.isEmpty(s)){
                    return false;
                }
            }
        }
        return stack.size()==0?true:false;
    }

}
