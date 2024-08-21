package com.architecture.hexagonal.ch2.domain.specification;

import com.architecture.hexagonal.ch2.domain.entity.Router;
import com.architecture.hexagonal.ch2.domain.specification.shared.AbstractSpecification;
import com.architecture.hexagonal.ch2.domain.vo.RouterType;

public class RouterTypeSpecification extends AbstractSpecification<Router> {
    @Override
    public boolean isSatisfiedBy(Router router) {
        return router.getRouterType().equals(RouterType.EDGE) || router.getRouterType().equals(RouterType.CORE);
    }
}
