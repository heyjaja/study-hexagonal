package com.architecture.hexagonal.ch2.domain.service;

import com.architecture.hexagonal.ch2.domain.entity.Router;
import com.architecture.hexagonal.ch2.domain.specification.CIDRSpecification;
import com.architecture.hexagonal.ch2.domain.specification.NetworkAmountSpecification;
import com.architecture.hexagonal.ch2.domain.specification.NetworkAvailableSpecification;
import com.architecture.hexagonal.ch2.domain.specification.RouterTypeSpecification;
import com.architecture.hexagonal.ch2.domain.vo.IP;
import com.architecture.hexagonal.ch2.domain.vo.Network;

public class NetworkOperation {
    final private int MINIMUM_ALLOWED_CIDR = 8;

    /**
     * 새로운 네트워크 객체를 생성하고 해당 객체를 라우터에 연결된 스위치에 추가
     * @param router
     * @param address
     * @param name
     * @param cidr
     */
    public void createNewNetwork(Router router, IP address, String name, int cidr) {
        // 최소 CIDR(Classless Inter-Domain Routing)이 위반되지 않는지 검사
        if(cidr < MINIMUM_ALLOWED_CIDR)
            throw new IllegalArgumentException("CIDR is below " + MINIMUM_ALLOWED_CIDR);

        // 네트워크 주소가 전체 네트워크에서 이미 사용되고 있는지 검사
        if(isNetworkAvailable(router, address, cidr))
            throw new IllegalArgumentException("address already exist");

        Network network = router.createNetwork(address, name, cidr);
        router.addNetworkToSwitch(network);

    }

    private boolean isNetworkAvailable(Router router, IP address, int cidr) {
        var availability = true;

        for (Network network : router.retrieveNetworks()) {
            if(network.getAddress().equals(address) && network.getCidr() == cidr) {
                availability = false;
                break;
            }
        }
        return availability;
    }

    public void newCreateNewNetwork(Router router, IP address, String name, int cidr) {
        var networkAvailableSpec = new NetworkAvailableSpecification(address, name, cidr);
        var cidrSpec = new CIDRSpecification();
        var routerTypeSpec = new RouterTypeSpecification();
        var networkAmountSpec = new NetworkAmountSpecification();

        if(cidrSpec.isSatisfiedBy(cidr))
            throw new IllegalArgumentException("CIDR is below " + MINIMUM_ALLOWED_CIDR);


        if(networkAvailableSpec.isSatisfiedBy(router))
            throw new IllegalArgumentException("address already exist");

        if(networkAmountSpec.and(routerTypeSpec).isSatisfiedBy(router)) {
            Network network = router.createNetwork(address, name, cidr);
            router.addNetworkToSwitch(network);
        }
    }
}
