package com.company.Wiki.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@ComponentScan("com.company.Wiki")
//加入扫描包的位置
//也可以变成扫描多个包的列表
@SpringBootApplication
public class WikiApplication {

	private static final Logger LOG = LoggerFactory.getLogger(WikiApplication.class);
	public static void main(String[] args) {

		//SpringApplication.run(WikiApplication.class, args);
		SpringApplication app = new SpringApplication(WikiApplication.class);
		Environment env = app.run(args).getEnvironment();
		/**
		 * 打印显示启动成功，并且显示地址
		 */
		LOG.info("启动成功！！！");
		LOG.info("地址:\thttp://127.0.0.1:{}",env.getProperty("server.port"));
		/**
		 * 打印自定义配置信息，欢迎页，测试地址，提示性方案等
		 */
	}

}
