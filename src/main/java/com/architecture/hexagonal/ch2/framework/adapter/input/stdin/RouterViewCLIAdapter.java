package com.architecture.hexagonal.ch2.framework.adapter.input.stdin;

import com.architecture.hexagonal.ch2.framework.adapter.output.file.RouterViewFileAdapter;
import com.architecture.hexagonal.ch2.application.port.input.RouterViewInputPort;
import com.architecture.hexagonal.ch2.application.usecase.RouterViewUseCase;
import com.architecture.hexagonal.ch2.domain.entity.Router;
import com.architecture.hexagonal.ch2.domain.vo.RouterType;

import java.util.List;

public class RouterViewCLIAdapter {
    RouterViewUseCase routerViewUseCase;

    public RouterViewCLIAdapter() {
        setAdapters();
    }

    public List<Router> obtainRelatedRouters(String type) {
        return routerViewUseCase.getRouters(
                Router.filterRouterByType(RouterType.valueOf(type))
        );
    }

    private void setAdapters() {
        // 유스케이스 인터페이스를 통해 입력 포트를 사용
        this.routerViewUseCase =
                new RouterViewInputPort(RouterViewFileAdapter.getInstance());
    }
}
