package com.architecture.hexagonal.ch2.domain.specification;

import com.architecture.hexagonal.ch2.domain.specification.shared.AbstractSpecification;

public class CIDRSpecification extends AbstractSpecification<Integer> {
    final static public int MINIMUM_ALLOWED_CIDR = 8;
    @Override
    public boolean isSatisfiedBy(Integer cidr) {
        return cidr < MINIMUM_ALLOWED_CIDR;
    }
}
