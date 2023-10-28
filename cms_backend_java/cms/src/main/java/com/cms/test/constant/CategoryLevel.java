package com.cms.test.constant;

public enum CategoryLevel {
    LEVEL_ONE(1),
    LEVEL_TWO(2),
    LEVEL_THREE(3);
    int level;

    CategoryLevel(int level){
        this.level = level;
    }
    public int getLevel(){
        return this.level;
    }
}
