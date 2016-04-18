package com.bluefish.lru;

/**
 * Created by elaine on 2016/4/18.
 */
public class Record {

    public Record(Object key ,Object value){
        this.key = key;
        this.value = value;
    }
    public Record next;
    public  Record pre;
    public Object key;
    public Object value;

    public Record getNext() {
        return next;
    }

    public void setNext(Record next) {
        this.next = next;
    }

    public Record getPre() {
        return pre;
    }

    public void setPre(Record pre) {
        this.pre = pre;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
