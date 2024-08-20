package com.architecture.hexagonal.ch2.domain.vo;

import java.util.UUID;

public class SwitchId {
    // 데이터베이스에 의존하지 않도록 하기 위해 UUID로 식별자 정의
    private final UUID id;

    private SwitchId(UUID id){
        this.id = id;
    }

    public static SwitchId withId(String id){
        return new SwitchId(UUID.fromString(id));
    }

    public static SwitchId withoutId() {
        return new SwitchId(UUID.randomUUID());
    }

    @Override
    public String toString() {
        return "RouterId{" +
                "id='" + id + '\'' +
                '}';
    }
}
