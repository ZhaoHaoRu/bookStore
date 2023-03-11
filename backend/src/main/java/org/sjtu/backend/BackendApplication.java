package org.sjtu.backend;

import org.sjtu.backend.entity.SubTag;
import org.sjtu.backend.entity.Tag;
import org.sjtu.backend.repository.SubTagRepository;
import org.sjtu.backend.repository.TagRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories
public class BackendApplication {

    /**
     * http重定向到https
     * @return
     */
//    @Bean
//    public ServletWebServerFactory servletContainer() {
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
//            @Override
//            protected void postProcessContext(Context context) {
//                SecurityConstraint securityConstraint = new SecurityConstraint();
//                securityConstraint.setUserConstraint("CONFIDENTIAL");
//                SecurityCollection collection = new SecurityCollection();
//                collection.addPattern("/*");
//                securityConstraint.addCollection(collection);
//                context.addConstraint(securityConstraint);
//            }
//        };
//        tomcat.addAdditionalTomcatConnectors(httpConnector());
//        return tomcat;
//    }

//    @Bean
//    public Connector httpConnector() {
//        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        connector.setScheme("http");
//        //Connector监听的http的端口号
//        connector.setPort(8080);
//        connector.setSecure(false);
//        //监听到http的端口号后转向到的https的端口号
//        connector.setRedirectPort(8443);
//        return connector;
//    }

//    @Bean
//    public Connector connector(){
//        Connector connector=new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        connector.setScheme("http");
//        connector.setPort(8080);
//        connector.setSecure(false);
//        connector.setRedirectPort(8443);
//        return connector;
//    }
//
//    @Bean
//    public TomcatServletWebServerFactory tomcatServletWebServerFactory(Connector connector){
//        TomcatServletWebServerFactory tomcat=new TomcatServletWebServerFactory(){
//            @Override
//            protected void postProcessContext(Context context) {
//                SecurityConstraint securityConstraint=new SecurityConstraint();
//                securityConstraint.setUserConstraint("CONFIDENTIAL");
//                SecurityCollection collection=new SecurityCollection();
//                collection.addPattern("/*");
//                securityConstraint.addCollection(collection);
//                context.addConstraint(securityConstraint);
//            }
//        };
//        tomcat.addAdditionalTomcatConnectors(connector);
//        return tomcat;
//    }


    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(TagRepository tagRepository, SubTagRepository subTagRepository) {
        return args -> {

            tagRepository.deleteAll();
            subTagRepository.deleteAll();

            Tag programming = new Tag("编程");
            Tag child = new Tag("儿童文学");
            Tag fiction = new Tag("小说");


            SubTag cpp = new SubTag("C++编程");
            SubTag java = new SubTag("Java编程");
            SubTag system = new SubTag("计算机系统");
            SubTag data = new SubTag("数据库");

            SubTag domestic = new SubTag("中国儿童文学");
            SubTag abroad = new SubTag("外国儿童文学");

            SubTag famous = new SubTag("世界名著");
            SubTag science = new SubTag("科幻小说");
            SubTag suspense = new SubTag("悬疑推理");



            tagRepository.save(programming);
            tagRepository.save(child);
            tagRepository.save(fiction);


            programming = tagRepository.findByName(programming.getName());
            cpp.setParent(programming);
            subTagRepository.save(cpp);
//            System.out.println(cpp.getName());
            cpp = subTagRepository.findByName(cpp.getName());
            programming.isParentOf(cpp);

            java.setParent(programming);
            subTagRepository.save(java);
//            System.out.println(java.getName());
            java = subTagRepository.findByName(java.getName());
            programming.isParentOf(java);

            system.setParent(programming);
            subTagRepository.save(system);
//            System.out.println(system.getName());
            system = subTagRepository.findByName(system.getName());
            programming.isParentOf(system);

            data.setParent(programming);
            subTagRepository.save(data);
            data = subTagRepository.findByName(data.getName());
            programming.isParentOf(data);

            tagRepository.save(programming);

            child = tagRepository.findByName(child.getName());

            domestic.setParent(child);
            subTagRepository.save(domestic);
            domestic = subTagRepository.findByName(domestic.getName());
            child.isParentOf(domestic);

            abroad.setParent(child);
            subTagRepository.save(abroad);
            abroad = subTagRepository.findByName(abroad.getName());
            child.isParentOf(abroad);

            tagRepository.save(child);


            fiction = tagRepository.findByName(fiction.getName());

            famous.setParent(fiction);
            subTagRepository.save(famous);
            famous = subTagRepository.findByName(famous.getName());
            fiction.isParentOf(famous);

            science.setParent(fiction);
            subTagRepository.save(science);
            science = subTagRepository.findByName(science.getName());
            fiction.isParentOf(science);

            suspense.setParent(fiction);
            subTagRepository.save(suspense);
            science = subTagRepository.findByName(suspense.getName());
            fiction.isParentOf(science);

            tagRepository.save(fiction);

        };
    }

}
