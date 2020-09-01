package com.osg.projectsimplediary;

public class MemoItem {
    int no;
    String title, text;

    public MemoItem() {

    }

    public MemoItem(int no, String title, String text) {
        this.no = no;
        this.title = title;
        this.text = text;
    }

    public MemoItem(String title, String text) {
        this.title = title;
        this.text = text;
    }
}
