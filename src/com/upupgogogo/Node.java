package com.upupgogogo;

/**
 * Created by upupgogogo on 2018/3/20.下午1:19
 */
public class Node<T> {

    public T data;

    public Node<T> next;

    public Node(T date, Node<T> next){
        this.data = date;
        this.next = next;
    }
    public Node(){
        this(null,null);
    }

}
