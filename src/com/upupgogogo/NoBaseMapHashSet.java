package com.upupgogogo;

import java.util.HashSet;

/**
 * Created by upupgogogo on 2018/3/21.上午10:30
 */
public class NoBaseMapHashSet<T> {

    private SinglyLinkedList<T>[] table; //单链表数组来保存数据

    private static final int DEFAULT_VALUE = 16;

    private int size;  //容器所装载的数据大小

    private static final float LOAD_FACTOR = 0.75f;  //加载因子

    private int ciritical_vale;   //容器所装载的临界值

    public NoBaseMapHashSet(){
        //初始化数组
        this.table = new SinglyLinkedList[DEFAULT_VALUE];

        //初始化数组的每个单链表对象
        initSinglyLinkedList(table.length);
        this.size = 0;

        //初始容器所装载的临界值
        this.ciritical_vale = (int)(LOAD_FACTOR*DEFAULT_VALUE);
    }
    private void initSinglyLinkedList(int value){
        for (int i = 0; i < value; i++){
            table[i] = new SinglyLinkedList<T>();
        }
    }


    private int index(T key) {
        //根据key的hashcode和table长度取模计算key在table中的位置
        if (key == null)
            throw new NullPointerException("NO NULL");
        return key.hashCode() % table.length;
    }

    public void add(T key){
        //判断是否包含保存的数据
        if (contain(key) != -1)
            return;

        //判断是否需要扩容
        resize();

        //先通过hash值判断单链表所属数组位置，然后调用单链表的add方法添加数据
        table[index(key)].add(key);

        //数据长度加一
        size++;
    }

    //扩容
    private void resize(){

        //判断数据大小是否已达临界值
        if (this.size >= this.ciritical_vale){
            //申明一个临时变量保存原有的数据
            SinglyLinkedList[] temp = this.table;

            //初始化一个两倍长度的单链表数组
            this.table = new SinglyLinkedList[table.length*2];

            initSinglyLinkedList(table.length);
            for (int j =0; j < temp.length; j++)
                table[j] = temp[j];

            //重新初始化临界值
            ciritical_vale = (int) (table.length * LOAD_FACTOR);
        }
    }

    public void remove(T key){
        //判断是否包含此数据
        if (contain(key) != -1)
            return;
        //通过得到此数据所处的index值，然后调用单链表的remove删除数据
        table[index(key)].remove(contain(key));

        size--;
    }

    public String toString() {
     String str = "";
     for (int j = 0; j < table.length; j++){
         if (table[j] != null){
             for (int i = 0; i < table[j].length(); i++)
                 str = str + table[j].get(i).toString() + ",";
         }
     }
     if (str.length() > 0)
         str = str.substring(0,str.length()-1);
     return "["+str+"]";
    }

    public int contain(T key){
         //1.通过hashcode方法得到所属单链表下标
         //2.通过equals方法判断是否相同
        //3.返回该数据所处单链表的位置，若不存在则返回-1
        return table[index(key)].contain(key);
    }

    public static void main(String[] args) {
        NoBaseMapHashSet hashSet = new NoBaseMapHashSet();
        hashSet.add(19);
        hashSet.add(18);
        hashSet.add(19);
        System.out.println(hashSet);
    }

}
