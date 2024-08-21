package com.architecture.hexagonal.ch2.domain.specification;

import com.architecture.hexagonal.ch2.domain.entity.Router;
import com.architecture.hexagonal.ch2.domain.specification.shared.AbstractSpecification;
import com.architecture.hexagonal.ch2.domain.vo.IP;
import com.architecture.hexagonal.ch2.domain.vo.Network;

public class NetworkAvailableSpecification extends AbstractSpecification<Router> {
    private IP address;
    private String name;
    private int cidr;

    public NetworkAvailableSpecification(IP address, String name, int cidr) {
        this.address = address;
        this.name = name;
        this.cidr = cidr;
    }

    @Override
    public boolean isSatisfiedBy(Router router) {
        return router != null && isNetworkAvailable(router);
    }

    private boolean isNetworkAvailable(Router router) {
        var availability = true;
        for (Network network : router.retrieveNetworks()) {
            if(network.getAddress().equals(address) && network.getName().equals(name) && network.getCidr() == cidr) {
                availability = false;
            }
        }

        return availability;
    }
}
