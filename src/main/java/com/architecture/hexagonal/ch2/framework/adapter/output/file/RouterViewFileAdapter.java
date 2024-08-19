package com.architecture.hexagonal.ch2.framework.adapter.output.file;

import com.architecture.hexagonal.ch2.application.port.output.RouterViewOutputPort;
import com.architecture.hexagonal.ch2.domain.entity.Router;
import com.architecture.hexagonal.ch2.domain.vo.RouterId;
import com.architecture.hexagonal.ch2.domain.vo.RouterType;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class RouterViewFileAdapter implements RouterViewOutputPort {

    private static RouterViewFileAdapter instance;

    private RouterViewFileAdapter() {
    }

    public static RouterViewFileAdapter getInstance() {
        if (instance == null) {
            instance = new RouterViewFileAdapter();
        }
        return instance;
    }

    @Override
    public List<Router> fetchRouters() {
        // 출력 포트는 애플리케이션이 외부로부터 필요로 하는 데이터를 나타낸다.
        // 이 예제는 로컬 파일을 통해 데이터를 얻는 방법 제공
        return readFileAsString();
    }

    private static List<Router> readFileAsString() {
        List<Router> routers = new ArrayList<>();

        try(Stream<String> stream = new BufferedReader(
                new InputStreamReader(RouterViewFileAdapter.class.getClassLoader()
                        .getResourceAsStream("routers.txt"))).lines()) {
            stream.forEach(line -> {
                String[] routerEntry = line.split(";");
                var id = routerEntry[0];
                var type = routerEntry[1];
                Router router = new Router(RouterType.valueOf(type), RouterId.withId(id));

                routers.add(router);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return routers;
    }
}
