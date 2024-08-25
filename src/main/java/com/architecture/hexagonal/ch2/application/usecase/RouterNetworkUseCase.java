package com.architecture.hexagonal.ch2.application.usecase;

import com.architecture.hexagonal.ch2.domain.entity.Router;
import com.architecture.hexagonal.ch2.domain.vo.Network;
import com.architecture.hexagonal.ch2.domain.vo.RouterId;

public interface RouterNetworkUseCase {
    // 유스케이스의 역할은 입력 포트 구현을 허용 -> 애플리케이션이 지원해야 하는 동작을 명세, 입력 포트에서 구현
    Router addNetworkToRouter(RouterId routerId, Network network);
}
