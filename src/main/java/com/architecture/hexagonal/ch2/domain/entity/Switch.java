package com.architecture.hexagonal.ch2.domain.entity;

import com.architecture.hexagonal.ch2.domain.vo.IP;
import com.architecture.hexagonal.ch2.domain.vo.Network;
import com.architecture.hexagonal.ch2.domain.vo.SwitchId;
import com.architecture.hexagonal.ch2.domain.vo.SwitchType;

import java.util.ArrayList;
import java.util.List;

public class Switch {

    private SwitchType switchType;
    private SwitchId switchId;
    private List<Network> networks;
    private IP address;

    public Switch(SwitchType switchType, SwitchId switchId, List<Network> networks, IP address) {
        this.switchType = switchType;
        this.switchId = switchId;
        this.networks = networks;
        this.address = address;
    }

    public Switch addNetwork(Network network) {
        // 네트워크는 스위치에 직접 연결, 더 많은 네트워크를 추가하는 기능을 지원하는 메서드
        var networks = new ArrayList<Network>();
        networks.add(network);
        return new Switch(this.switchType, this.switchId, networks, this.address);
    }

    public List<Network> getNetworks() {
        return networks;
    }
}
