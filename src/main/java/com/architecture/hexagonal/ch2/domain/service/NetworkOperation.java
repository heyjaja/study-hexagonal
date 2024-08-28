package com.architecture.hexagonal.ch2.domain.service;

import com.architecture.hexagonal.ch2.domain.entity.Router;
import com.architecture.hexagonal.ch2.domain.specification.CIDRSpecification;
import com.architecture.hexagonal.ch2.domain.specification.NetworkAmountSpecification;
import com.architecture.hexagonal.ch2.domain.specification.NetworkAvailableSpecification;
import com.architecture.hexagonal.ch2.domain.specification.RouterTypeSpecification;
import com.architecture.hexagonal.ch2.domain.vo.Network;

public class NetworkOperation {
    final static private int MINIMUM_ALLOWED_CIDR = 8;

    /**
     * 새로운 네트워크 객체를 생성하고 해당 객체를 라우터에 연결된 스위치에 추가
     * @param router
     * @param network
     */
    public static Router createNewNetwork(Router router, Network network) {
        var networkAvailableSpec =
                new NetworkAvailableSpecification(network.getAddress(), network.getName(), network.getCidr());
        var cidrSpec = new CIDRSpecification();
        var routerTypeSpec = new RouterTypeSpecification();
        var networkAmountSpec = new NetworkAmountSpecification();

        if(cidrSpec.isSatisfiedBy(network.getCidr()))
            throw new IllegalArgumentException("CIDR is below " + MINIMUM_ALLOWED_CIDR);


        if(!networkAvailableSpec.isSatisfiedBy(router))
            throw new IllegalArgumentException("address already exist");

        if(networkAmountSpec.and(routerTypeSpec).isSatisfiedBy(router)) {
            Network newNetwork = router.createNetwork(network.getAddress(), network.getName(), network.getCidr());
            router.addNetworkToSwitch(newNetwork);
        }
        return router;
    }
}
