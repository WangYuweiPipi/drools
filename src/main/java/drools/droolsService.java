package drools;


import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class droolsService {

    public String fireRule(){
        //构建KieServices
        KieServices ks = KieServices.Factory.get();
        KieContainer kieContainer = ks.getKieClasspathContainer();

        //获取kmodule.xml中的配置名称为ksession-rules的session，默认为有状态的
        KieSession kieSession = kieContainer.newKieSession("ksession-rules");

        Message message = new Message();
        message.setMessage("Hello World!");
        message.setStatus(Message.HELLO);
        kieSession.insert(message);
        kieSession.fireAllRules();
        kieSession.dispose();
        return message.getMessage();
    }


    public void create(){
        //创建kieservices
        KieServices kieServices = KieServices.Factory.get();
        //创建kieContainer
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieBase kieBase = kieContainer.getKieBase(); //获取kieBase
        KieSession kieSession = kieContainer.newKieSession(); //获取kiesession

        //创建kieRepository，作为一个单例对象，用来存放kieModule仓库
        KieRepository kieRepository = kieServices.getRepository();
    }
}
