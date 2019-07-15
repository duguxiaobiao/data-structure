package com.lonely.递归;

import org.junit.Test;

/**
 * @author ztkj-hzb
 * @Date 2019/6/24 16:37
 * @Description 测试sum求和使用递归来实现
 */
public class TestSum {

    @Test
    public void testSum() {
        System.out.println(sum(new int[]{1, 2, 3, 4, 5, 6}, 0));
    }

    private Integer sum(int[] arr, int index) {
        if (index == arr.length) {
            return 0;
        }
        return arr[index] + sum(arr, ++index) ;
    }
}
