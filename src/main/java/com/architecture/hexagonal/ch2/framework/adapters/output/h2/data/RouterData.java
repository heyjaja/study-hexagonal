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
@Table(name = "routers")
@SecondaryTable(name = "switches")
@MappedSuperclass
public class RouterData implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "router_id", columnDefinition = "uuid", updatable = false)
    private UUID routerId;

    @Embedded
    @Enumerated(EnumType.STRING)
    @Column(name = "router_type")
    private RouterTypeData routerType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(table = "switches", name = "router_id", referencedColumnName = "router_id")
    private SwitchData networkSwitch;
}
