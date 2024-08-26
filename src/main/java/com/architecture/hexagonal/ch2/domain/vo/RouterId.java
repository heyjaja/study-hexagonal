package com.architecture.hexagonal.ch2.domain.vo;

import java.util.UUID;

public class RouterId {

    // 데이터베이스에 의존하지 않도록 하기 위해 UUID로 식별자 정의
    private final UUID id;

    private RouterId(UUID id){
        this.id = id;
    }

    public static RouterId withId(String id){
        return new RouterId(UUID.fromString(id));
    }

    public static RouterId withoutId() {
        return new RouterId(UUID.randomUUID());
    }

    @Override
    public String toString() {
        return "RouterId{" +
                "id='" + id + '\'' +
                '}';
    }

    public UUID getUUID() {
        return id;
    }
}