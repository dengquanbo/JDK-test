package jdk8;

import com.google.common.collect.Lists;

import com.alibaba.fastjson.JSON;

import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.experimental.Accessors;

public class ListTest {


    /**
     * list 按某个属性分组
     */
    @Test
    public void group1() {
        Node node1 = new Node().setId(1).setType(1);
        Node node2 = new Node().setId(2).setType(2);
        Node node3 = new Node().setId(3).setType(2);
        List<Node> nodes = Lists.newArrayList(node1, node2, node3);

        Map<Integer, List<Node>> collect = nodes.stream().collect(Collectors.groupingBy(Node::getType));
        System.out.println(JSON.toJSONString(collect));
    }

    /**
     * list 按某个属性分组，分组后的Map的属性自定义
     */
    @Test
    public void group2() {
        Node node1 = new Node().setId(1).setType(1);
        Node node2 = new Node().setId(2).setType(2);
        Node node3 = new Node().setId(3).setType(2);
        List<Node> nodes = Lists.newArrayList(node1, node2, node3);

        Map<Integer, List<Integer>> collect = nodes.stream().collect(Collectors.groupingBy(Node::getType,
                Collectors.mapping(Node::getId, Collectors.toList())));
        System.out.println(JSON.toJSONString(collect));
    }


    @Test
    public void sss(){
        String s = StringEscapeUtils.escapeHtml4("<script> ss </script>");
        System.out.println(s);
    }

    @Data
    @Accessors(chain = true)
    static class Node {
        private Integer id;

        private Integer type;
    }
}
