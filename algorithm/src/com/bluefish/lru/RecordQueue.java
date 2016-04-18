package com.bluefish.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by elaine on 2016/4/18.
 */
public class RecordQueue {

    Map<Object, Object> map = new HashMap<Object,Object>();
    Record head;
    Record tail;
    int size ;

    public RecordQueue(int size){
        this.size = size;
    }

    public Object get(Object key){
        Record node = (Record)map.get(key);
        if(node == null){
            return null;
        }
        MovetoHead(node);
        return node.value;

    }

    public void put(Object key ,Object value){
        Record node = (Record)map.get(key);
        if(node == null){
            if(map.size() >= size){
                map.remove(tail.key);
                removeTail();
            }
            node = new Record(key, value);

        }else{
            node.value = value;
        }
        MovetoHead(node);
        map.put(key,node);
    }

    public void MovetoHead(Record node){
        if(node == head){
            return;
        }

        if(node.next != null){
            node.next.pre = node.pre;
        }
        if(node.pre != null){
            node.pre.next = node.next;
        }
        if(head != null) {
            node.next = head;
            head.pre = node;
        }
        if(node == tail){
            tail = tail.pre;
        }
        head = node;
        node.pre = null;
        if(tail == null){
            tail = head;
        }
    }

    public Record ModetoTail(Record node){
        if(tail == node){
            return null;
        }

        if(tail != null){
            node.pre = tail;
            tail.next = node;
            node.next = null;
        }
        Record tmp = tail;
        tail = node;
        return tmp;
    }


    public void removeTail(){
        if(tail == null){
            return;
        }
        if(tail == head){
            tail = head = null;
        }
        if(tail.pre != null) {
            tail.pre.next = null;
            tail = tail.pre;
        }
    }


    public static void main(String [] args) {

        RecordQueue c = new RecordQueue(3);
        int array[] = {1,2,3,4,2,1,5,6,2,1,2,3,7,6,3,2,1,2,3,6};
        for(int i = 0 ; i < array.length ; ++ i){
            c.put(array[i] , array[i]);
            c.display();
        }

    }
    private int getSize() {
        return map.size();
    }

    private void display() {

        Record tmp = head;
        for (tmp = head; tmp != tail; tmp = tmp.next)
            System.out.print(tmp.getKey() + " : " + tmp.getValue() + " ");
        System.out.print(tmp.getKey() + " : " + tmp.getValue()+ " ");
        System.out.println();
    }
}



