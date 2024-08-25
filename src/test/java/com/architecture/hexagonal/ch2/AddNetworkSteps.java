package com.architecture.hexagonal.ch2;

import com.architecture.hexagonal.ch2.domain.entity.Router;
import com.architecture.hexagonal.ch2.domain.specification.CIDRSpecification;
import com.architecture.hexagonal.ch2.domain.specification.NetworkAvailableSpecification;
import com.architecture.hexagonal.ch2.domain.vo.IP;
import com.architecture.hexagonal.ch2.domain.vo.Network;
import com.architecture.hexagonal.ch2.domain.vo.RouterId;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddNetworkSteps {
    RouterId routerId;
    Router router;
    RouterNetworkFileAdapter routerNetworkFileAdapter = RouterNetworkFileAdapter.getInstance();
    Network network = new Network(new IP("20.0.0.0"), "Marketing", 8);

    @Given("라우터 ID와 네트워크 세부 사항을 제공한다")
    public void obtain_routerId() {
        this.routerId = RouterId.withId("ca23800e-9b5a-11eb-a8b3-0242ac130003");
    }

    @When("라우터를 발견했다")
    public void lookup_router() {
        // router Id 검색
        router = routerNetworkFileAdapter.fetchRouterById(routerId);
    }

    @And("네트워크 주소가 유효하며 기존에 존재하지 않는다")
    public void check_address_validity_and_existence() {
        // 네트워크 주소 유효성 검사
        var availabilitySpec = new NetworkAvailableSpecification(
                network.getAddress(), network.getName(), network.getCidr()
        );

        if(!availabilitySpec.isSatisfiedBy(router))
            throw new IllegalArgumentException("Address already exist");
    }

    @Given("CIDR이 유효하다")
    public void check_cidr() {
        // CIDR 유효성 검사
        var cidrSpec = new CIDRSpecification();
        if(cidrSpec.isSatisfiedBy(network.getCidr())) {
            throw new IllegalArgumentException("CIDR is below" + CIDRSpecification.MINIMUM_ALLOWED_CIDR);
        }
    }

    @Then("라우터에 네트워크를 추가한다")
    public void add_network() {
        // 네트워크 추가
        router.addNetworkToSwitch(network);
    }
}
