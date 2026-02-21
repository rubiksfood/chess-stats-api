package com.github.rubiksfood.chessstatsapi;

import org.springframework.boot.SpringApplication;

public class TestChessStatsApiApplication {

	public static void main(String[] args) {
		SpringApplication.from(ChessStatsApiApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
