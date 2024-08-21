package com.architecture.hexagonal.ch2.domain.vo;

public class Network {
    private final IP address;
    private final String name;
    private final int cidr;

    public Network(IP address, String name, int cidr) {
        // IP에 대한 유효성 검사는 IP 객체에서 진행하기 때문에 cidr 속성만 검사하면 된다.
        if(cidr < 1 || cidr > 32) {
            throw new IllegalArgumentException("Invalid CIDR value");
        }
        this.address = address;
        this.name = name;
        this.cidr = cidr;
    }

    public IP getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public int getCidr() {
        return cidr;
    }
}
