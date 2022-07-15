package sk.best.newtify.web.gui.component.widget;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import lombok.val;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sk.best.newtify.api.dto.NameDayDTO;

import javax.annotation.PostConstruct;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author Marek Urban
 * Copyright © 2022 BEST Technická univerzita Košice.
 * All rights reserved.
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class DogWidgetComponent extends FlexLayout {

    private static final long              serialVersionUID    = 4214727226197592073L;

    private final RestTemplate restTemplate = new RestTemplate();


    public DogWidgetComponent() {
    }

    @PostConstruct
    private void init() {
        val quote = restTemplate.getForEntity("https://dog.ceo/api/breeds/image/random", String.class);

        createIcon(quote.getBody());

        this.getStyle()
                .set("background", "var(--lumo-contrast-10pct)")
                .set("border-radius", "1em");
        this.setFlexDirection(FlexDirection.COLUMN);
        this.setAlignItems(Alignment.CENTER);
        this.setWidthFull();
    }

    private void createIcon(String body) {

       String link =  body.replace("{\"message\":\"","").replace("\",\"status\":\"success\"}","").replace("]","");
        Image image = new Image(link, "Have a dog");
        this.add(image);
    }

    private void createQuotePart(String quote) {
        H4 todayDateTitle = new H4(quote);
        todayDateTitle.getStyle()
                .set("color", "var(--lumo-contrast-color)");
        H3 ronSwanson = new H3("- Ron Swanson");
        ronSwanson.getStyle()
                .set("color", "var(--lumo-contrast-color)")
                .set("align-text", "center");

        this.add(todayDateTitle, ronSwanson);
    }

    private void createNameDayPart(NameDayDTO data) {
        Label nameDayLabel = new Label("Name day has");
        nameDayLabel.getStyle()
                .set("color", "var(--lumo-contrast-color)")
                .set("margin", "1em 0 0 0");

        H3 nameDayValue = new H3(data != null ? data.getName() : "Unknown");
        nameDayValue.getStyle()
                .set("color", "white")
                .set("font-style", "italic")
                .set("margin", "0 0 1em 0");

        this.add(nameDayLabel, nameDayValue);
    }
}