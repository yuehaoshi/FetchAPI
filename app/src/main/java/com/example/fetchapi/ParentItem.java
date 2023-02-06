package com.example.fetchapi;

import java.util.List;

public class ParentItem {
    private String ParentListId;
    private List<ChildItem> ChildItemList;
    public ParentItem(String parentListId, List<ChildItem> ChildItemList) {
        this.ParentListId = parentListId;
        this.ChildItemList = ChildItemList;
    }
    public String getParentListId() {
        return ParentListId;
    }
    public void setParentListId(String parentListId) {
        ParentListId = parentListId;
    }
    public List<ChildItem> getChildItemList() {return ChildItemList;}
    public void setChildItemList(List<ChildItem> childItemList) {ChildItemList = childItemList;}
}
