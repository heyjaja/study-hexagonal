package com.architecture.hexagonal.ch2.domain.specification.shared;

public class AndSpecification<T> extends AbstractSpecification<T> {
    private Specification<T> spec1;
    private Specification<T> spec2;
    public AndSpecification(AbstractSpecification<T> spec1, Specification<T> spec2) {
        this.spec1 = spec1;
        this.spec2 = spec2;
    }

    public boolean isSatisfiedBy(final T t) {
        return spec1.isSatisfiedBy(t) && spec2.isSatisfiedBy(t);
    }
}
