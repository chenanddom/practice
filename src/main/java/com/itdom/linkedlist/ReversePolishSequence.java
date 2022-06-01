package com.itdom.linkedlist;

/**
 * 逆波兰序列
 */
public class ReversePolishSequence {
    public static void main(String[] args) {
        //中缀表达式3*（17-15）+18/6的逆波兰表达式如下
        String[] notation = {"3", "17", "15", "-", "*", "18", "6", "/", "+"};

        System.out.println(caculate(notation));
    }

    public static int caculate(String[] content) {
        Stack<Integer> stack = new Stack<>();
        for (String s : content) {
            Integer v1 = null;
            Integer v2 = null;
            switch (s) {
                case "+":
                    v1 = stack.pop();
                    v2 = stack.pop();
                    stack.push(v2+v1);
                    break;
                case "-":
                    v1 = stack.pop();
                    v2 = stack.pop();
                    stack.push(v2-v1);
                    break;
                case "*":
                    v1 = stack.pop();
                    v2 = stack.pop();
                    stack.push(v2*v1);
                    break;
                case "/":
                    v1 = stack.pop();
                    v2 = stack.pop();
                    stack.push(v2/v1);
                    break;
                default:
                    stack.push(Integer.valueOf(s));
                    break;
            }
        }
        //栈内最后剩下结果，弹出即可获得结果
        return stack.pop();
    }


}
