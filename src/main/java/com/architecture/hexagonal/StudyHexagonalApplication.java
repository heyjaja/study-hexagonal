package com.architecture.hexagonal;


import com.architecture.hexagonal.ch2.application.ports.input.RouterNetworkInputPort;
import com.architecture.hexagonal.ch2.application.ports.output.RouterNetworkOutputPort;
import com.architecture.hexagonal.ch2.application.usecase.RouterNetworkUseCase;
import com.architecture.hexagonal.ch2.framework.adapters.input.RouterNetworkAdapter;
import com.architecture.hexagonal.ch2.framework.adapters.input.rest.RouterNetworkRestAdapter;
import com.architecture.hexagonal.ch2.framework.adapters.input.stdin.RouterNetworkCLIAdapter;
import com.architecture.hexagonal.ch2.framework.adapters.output.file.RouterNetworkFileAdapter;
import com.architecture.hexagonal.ch2.framework.adapters.output.h2.RouterNetworkH2Adapter;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class StudyHexagonalApplication {

	RouterNetworkAdapter inputAdapter;
	RouterNetworkUseCase useCase;
	RouterNetworkOutputPort outputPort;

	public static void main(String[] args) {
		var adapter = "cli";
		if(args.length>0) {
			adapter = args[0];
		}
		new StudyHexagonalApplication().setAdapter(adapter);
	}

	private void setAdapter(String adapter) {
		switch (adapter) {
			case "rest":
				outputPort = RouterNetworkH2Adapter.getInstance();
				useCase = new RouterNetworkInputPort(outputPort);
				inputAdapter = new RouterNetworkRestAdapter(useCase);
				rest();
				break;
			default:
				outputPort = RouterNetworkFileAdapter.getInstance();
				useCase = new RouterNetworkInputPort(outputPort);
				inputAdapter = new RouterNetworkCLIAdapter(useCase);
				cli();
		}
	}



	private void cli() {
		Scanner scanner = new Scanner(System.in);
		inputAdapter.processRequest(scanner);
	}

	private void rest() {
		try {
			System.out.println("REST endpoint listening on port 8080...");
			var httpserver = HttpServer.create(new InetSocketAddress(8080), 0);
			inputAdapter.processRequest(httpserver);
		} catch (IOException e){
			e.printStackTrace();
		}
	}
}
