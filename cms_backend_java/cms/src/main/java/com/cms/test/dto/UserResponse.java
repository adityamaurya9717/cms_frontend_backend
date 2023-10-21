package com.cms.test.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class UserResponse {

    private int pageNo;
    private int size;
    private int totalCount;
    private List<UserModel> users = new ArrayList<>();

    public UserResponse(int pageNo, int size, Integer totalCount,List<UserModel> users) {
        this.pageNo = pageNo;
        this.size = users.size();
        this.totalCount = totalCount;
        this.users = users;
    }
}
