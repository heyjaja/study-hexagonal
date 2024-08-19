package com.architecture.hexagonal.ch2.domain.entity;

import com.architecture.hexagonal.ch2.domain.vo.RouterId;
import com.architecture.hexagonal.ch2.domain.vo.RouterType;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Router {
    private final RouterType routerType; // 엔티티의 상태
    private final RouterId routerId; // 고유한 식별자

    public Router(RouterType routerType, RouterId routerId) {
        this.routerType = routerType;
        this.routerId = routerId;
    }

    public static Predicate<Router> filterRouterByType(RouterType routerType) {
        return routerType.equals(RouterType.CORE)
                ? isCore() :
                isEdge();
    }

    public static List<Router> retrieveRouter(List<Router> routers, Predicate<Router> predicate) {
        return routers.stream()
                .filter(predicate)
                .collect(Collectors.<Router>toList());
    }

    private static Predicate<Router> isEdge() {
        return p -> p.getRouterType() == RouterType.EDGE;
    }

    private static Predicate<Router> isCore() {
        return p -> p.getRouterType() == RouterType.CORE;
    }

    private RouterType getRouterType() {
        return routerType;
    }

    @Override
    public String toString(){
        return "Router{" +
                "routerType=" + routerType +
                ", routerId=" + routerId +
                '}';
    }
}
