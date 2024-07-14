package com.architecture.hexagonal.ch1.application.usecase;

import com.architecture.hexagonal.ch1.domain.Router;

import java.util.List;
import java.util.function.Predicate;

public interface RouterViewUseCase {
    /**
     * 필터링 된 라우터 리스트를 가져온다.
     * @param filter
     * @return
     */
    List<Router> getRouters(Predicate<Router> filter);
}
