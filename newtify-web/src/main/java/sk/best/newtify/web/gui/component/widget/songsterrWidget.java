package sk.best.newtify.web.gui.component.widget;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import javax.annotation.PostConstruct;
import lombok.val;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class songsterrWidget extends VerticalLayout {
    private static final long serialVersionUID = 1414727226194592872L;
    private final RestTemplate restTemplate = new RestTemplate();
    public songsterrWidget(){

    }

    @PostConstruct
    private void init(){
        val songs = restTemplate.getForEntity("https://www.songsterr.com/a/wa/api/", String.class);
//        createSongsPart(songs.getBody().replace("{\"songs\":\"","").replace("\"}",""));
        createIcon();
        this.setWidthFull();

        this.getStyle()
                .set("backround", "var(--lumo-contrast-10pct)")
                .set("border-radius", "1em");
        this.setAlignItems(Alignment.AUTO);
        this.setWidthFull();
    }

    private void createIcon(){
        Icon kanyeIcon = VaadinIcon.GAMEPAD.create();
        kanyeIcon.setSize("5em");
        kanyeIcon.setColor("var(--lumo-contrast-color)");

        this.add(kanyeIcon);
    }

    private void createSongPart(String songs) {
        H4 todayDateTitle = new H4(songs);
        todayDateTitle.getStyle()
                .set("color", "var(--lumo-contrast-color)");


        this.add(todayDateTitle);
    }


}
