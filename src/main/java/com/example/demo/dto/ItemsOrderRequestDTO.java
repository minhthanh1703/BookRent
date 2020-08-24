package com.example.demo.dto;

import java.util.List;

public class ItemsOrderRequestDTO {

    private int userId;

    private List<ItemOrderDTO> listItem;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<ItemOrderDTO> getListItem() {
        return listItem;
    }

    public void setListItem(List<ItemOrderDTO> listItem) {
        this.listItem = listItem;
    }

    public ItemsOrderRequestDTO(int userId, List<ItemOrderDTO> listItem) {
        this.userId = userId;
        this.listItem = listItem;
    }
}
