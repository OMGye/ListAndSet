package com.upupgogogo;

/**
 * Created by upupgogogo on 2018/3/20.下午1:23
 */
public class SinglyLinkedList<T> implements LList<T> {

    private Node<T> head;

    private int size;

    //初始化节点和保存数据的大小
    public SinglyLinkedList() {
        this.head = new Node<T>();
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int length() {
        return this.size;
    }

    public void add(int index, T x) {
        //1.判参数
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " +index+ "," +"Size: "+this.size);
        //2.找节点（插入点的前一个节点）
        Node<T> p = this.head;
        for (int j = 0; j < index; j++)
            p = p.next;
        //3.插入节点
        p.next = new Node<T>(x,p.next);
        //size加一
        this.size++;
    }

    public void add(T x) {
        add(this.size,x);
    }

    public T remove(int index) {

        //判断index是否有效
        checkElement(index);

        //得到头结点
        Node<T> p = this.head;

        //找到index节点的前一个节点
        for (int j = 0; j < index; j++)
            p = p.next;

        //得到保存的数据
        T x =(T)p.next.data;

        //让该节点的前节点的next指针指向该节点的下个节点
        p.next = p.next.next;

        //数据长度减一
        this.size--;

        return x;
    }

    public void set(int index, T x) {
        checkElement(index);
        Node<T> p = this.head;
        for (int j = 0; j < index; j++)
            p = p.next;
        p.next.data = x;
    }

    public T get(int index) {
        checkElement(index);
        Node<T> p = this.head;
        for (int j = 0; j < index; j++)
            p = p.next;
        return p.next.data;
    }
    private void checkElement(int index){
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " +index+ "," +"Size: "+this.size);
    }
    public int contain(T x){
        Node<T> p = head.next;
        int i = -1;
        while (p != null){
            i++;
            if (p.data .equals(x)){
                p.data = x;
                return i;
            }
            p = p.next;
        }
        return -1;
    }

    public String toString() {
        String str = "";
        Node<T> p = head;
        for (int j = 0; j < size; j++){
            p = p.next;
            str = str + p.data.toString() +",";
        }
        if (str.length() > 0)
            str = str.substring(0,str.length()-1);
        return "["+str+"]";
    }

    public static void main(String[] args) {
        LList lList = new SinglyLinkedList();
        lList.add(19);
        lList.add(20);
        lList.remove(0);
        System.out.println(lList);
    }
}
