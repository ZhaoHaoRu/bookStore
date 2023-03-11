package org.sjtu.backend.utils;

import org.sjtu.backend.entity.SubTag;
import org.sjtu.backend.entity.Tag;
import org.sjtu.backend.repository.SubTagRepository;
import org.sjtu.backend.repository.TagRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

public class CreateGraph {
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
            cpp = subTagRepository.findByName(cpp.getName());
            programming.isParentOf(cpp);

            java.setParent(programming);
            subTagRepository.save(java);
            java = subTagRepository.findByName(java.getName());
            programming.isParentOf(java);

            system.setParent(programming);
            subTagRepository.save(system);
            system = subTagRepository.findByName(system.getName());
            programming.isParentOf(system);

            data.setParent(programming);
            subTagRepository.save(data);
            data = subTagRepository.findByName(data.getName());
            programming.isParentOf(data);

            tagRepository.save(programming);

            child = tagRepository.findByName(child.getName());
            famous.setParent(child);
            subTagRepository.save(famous);
            famous = subTagRepository.findByName(famous.getName());
            child.isParentOf(famous);

            science.setParent(child);
            subTagRepository.save(science);
            science = subTagRepository.findByName(science.getName());
            child.isParentOf(science);

            science.setParent(child);
            subTagRepository.save(science);
            science = subTagRepository.findByName(science.getName());
            child.isParentOf(science);

            tagRepository.save(child);


            fiction = tagRepository.findByName(fiction.getName());
            domestic.setParent(fiction);
            subTagRepository.save(domestic);
            domestic = subTagRepository.findByName(domestic.getName());
            fiction.isParentOf(domestic);

            suspense.setParent(fiction);
            subTagRepository.save(suspense);
            suspense = subTagRepository.findByName(suspense.getName());
            fiction.isParentOf(suspense);

            tagRepository.save(fiction);

        };
    }
}
