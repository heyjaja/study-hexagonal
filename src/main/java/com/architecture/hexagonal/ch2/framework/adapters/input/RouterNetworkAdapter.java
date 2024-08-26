package com.architecture.hexagonal.ch2.framework.adapters.input;

import com.architecture.hexagonal.ch2.application.usecase.RouterNetworkUseCase;
import com.architecture.hexagonal.ch2.domain.entity.Router;
import com.architecture.hexagonal.ch2.domain.vo.IP;
import com.architecture.hexagonal.ch2.domain.vo.Network;
import com.architecture.hexagonal.ch2.domain.vo.RouterId;

import java.util.Map;

public abstract class RouterNetworkAdapter {
    protected Router router;
    protected RouterNetworkUseCase routerNetworkUseCase; // 입력 포트를 직접 참조하지 않고 유스케이스 인터페이스를 참조

    /**
     * 매개변수를 수신 받아 라우터에 네트워크를 추가하는 유스케이스 오퍼레이션 수행
     * @param params HTTP 요청 STDIN을 갖는 셸/콘솔과 같은 다양한 소스로부터 수신
     * @return
     */
    protected Router addNetworkToRouter(Map<String, String> params) {
        var routerId = RouterId.withId(params.get("routerId"));
        var network = new Network(IP.fromAddress(params.get("address")),
                params.get("name"), Integer.valueOf(params.get("cidr")));

        return routerNetworkUseCase.addNetworkToRouter(routerId, network);
    }

    public abstract Router ProcessRequest(Object requestParams);
}
