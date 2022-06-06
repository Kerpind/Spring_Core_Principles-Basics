package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find protypeBean1");
        PrototypeBean protypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find protypeBean2");
        PrototypeBean protypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("protypeBean1 = " + protypeBean1);
        System.out.println("protypeBean2 = " + protypeBean2);
        assertThat(protypeBean1).isNotSameAs(protypeBean2);

        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }

}
