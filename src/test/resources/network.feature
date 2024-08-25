@addNetworkToRouter
  Feature: 라우터에 네트워크를 추가한다
    기존 라우터에 네트워크를 추가할 수 있기를 원한다
  Scenario: 기존 라우터에 네트워크 추가
    Given 라우터 ID와 네트워크 세부 사항을 제공한다
    When 라우터를 발견했다
    And 네트워크 주소가 유효하며 기존에 존재하지 않는다
    And CIDR이 유효하다
    Then 라우터에 네트워크를 추가한다