import com.springinaction.springidol.Contestant;
import com.springinaction.springidol.Performer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by tianjiyuan on 16/3/28.
 */
public class AopTester {
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-idol2.xml");
        Performer author = (Performer)context.getBean("kenny");
        author.perform();
        Contestant contestant = (Contestant) author;
        contestant.receiveAward();
//        Thinker thinker = (Thinker)context.getBean("thinker");
//        thinker.think("En ~ en ~ en", "ha ! ha ! ha");
//        MindReader reader = (MindReader)context.getBean("mindReader");
//        Bluefish.out.println(reader,reader.getThought());
    }
}
