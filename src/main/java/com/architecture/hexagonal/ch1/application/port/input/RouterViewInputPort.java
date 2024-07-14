package com.architecture.hexagonal.ch1.application.port.input;

import com.architecture.hexagonal.ch1.application.port.output.RouterViewOutputPort;
import com.architecture.hexagonal.ch1.application.usecase.RouterViewUseCase;
import com.architecture.hexagonal.ch1.domain.Router;

import java.util.List;
import java.util.function.Predicate;

public class RouterViewInputPort implements RouterViewUseCase {
    private RouterViewOutputPort routerViewOutputPort;

    public RouterViewInputPort(RouterViewOutputPort routerViewOutputPort) {
        this.routerViewOutputPort = routerViewOutputPort;
    }

    @Override
    public List<Router> getRouters(Predicate<Router> filter) {
        // 도메인의 제약사항을 사용해 조회하고자 하는 라우터를 필터링
        var routers = routerViewOutputPort.fetchRouters();
        return Router.retrieveRouter(routers, filter);
    }


}
