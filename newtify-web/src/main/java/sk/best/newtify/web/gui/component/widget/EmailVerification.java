package sk.best.newtify.web.gui.component.widget;

import javax.annotation.PostConstruct;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Input;
import lombok.val;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class EmailVerification extends FormLayout {
    private static final long serialVersionUID = 1414727226194592861L;
    private final FormLayout formLayout = new FormLayout();
    public EmailVerification(){}

    @PostConstruct
    private void init(){
         createForm();
    }

    private void createForm(){
        formLayout.addFormItem(new Input(), "Revenue");
     }


}
