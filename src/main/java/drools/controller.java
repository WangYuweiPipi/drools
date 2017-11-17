package drools;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Yuwei on 2017/11/15.
 */
@RestController
public class controller {
    @Autowired
    private KieSession kieSession;

    @RequestMapping("/test")
    public String test(){
        Message message = new Message();
        message.setMessage("Hello World!");
        message.setStatus(Message.HELLO);

        kieSession.insert(message);
        int ruleFileCount = kieSession.fireAllRules();
        System.out.println("触发了"+ruleFileCount+"条规则");
        kieSession.dispose();
        return message.getMessage();
    }
}
