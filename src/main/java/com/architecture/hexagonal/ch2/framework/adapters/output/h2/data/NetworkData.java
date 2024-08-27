package com.architecture.hexagonal.ch2.framework.adapters.output.h2.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "networks")
@MappedSuperclass
public class NetworkData implements Serializable {
    @Id
    @Column(name = "network_id")
    private int id;

    @Column(name = "switch_id")
    private UUID switchId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address", column = @Column(name = "network_address")),
            @AttributeOverride(name = "protocol", column = @Column(name = "network_protocol"))
    })
    private IPData ip;

    @Column(name = "network_name")
    private String name;

    @Column(name = "network_cidr")
    private Integer cidr;

    public NetworkData(UUID switchId, IPData ipData, String name, int cidr) {
        this.switchId = switchId;
        this.ip = ipData;
        this.name = name;
        this.cidr = cidr;
    }
}
