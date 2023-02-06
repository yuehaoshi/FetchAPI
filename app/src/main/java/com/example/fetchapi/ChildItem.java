package com.example.fetchapi;

public class ChildItem {
    int Id;
    String ListId,Name;

    public ChildItem(int id, String listId, String name) {
        this.Id = id;
        ListId = listId;
        Name = name;
    }
    public int getId() {
        return Id;
    }
    public void setId(int Id) {
        this.Id = Id;
    }
    public String getListId() {
        return ListId;
    }

    public void setListId(String listId) {
        ListId = listId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
