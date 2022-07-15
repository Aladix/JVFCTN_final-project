package sk.best.newtify.web.gui.component.widget.BitcoinWidgetComponent;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import lombok.val;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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
public class TickerWidgetComponent extends FlexLayout {

    private static final long              serialVersionUID    = 1414727226197842073L;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d LLLL uuuu", Locale.ENGLISH);

    private final RestTemplate restTemplate = new RestTemplate();


    public TickerWidgetComponent() {
    }

    @PostConstruct
    private void init() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        HttpEntity<String> entity = new HttpEntity<>(headers);
try{
    val ticker = restTemplate.getForObject("https://api.cryptonator.com/api/ticker/btc-usd", TickerResponseDTO.class);

    createIcon();
    createTickerPart(ticker);

}catch(Exception e){

}

        this.getStyle()
                .set("background", "var(--lumo-contrast-10pct)")
                .set("border-radius", "1em");
        this.setFlexDirection(FlexDirection.COLUMN);
        this.setAlignItems(Alignment.CENTER);
        this.setWidthFull();
    }

    private void createIcon() {
        Icon calendarIcon = VaadinIcon.COIN_PILES.create();
        calendarIcon.setSize("5em");
        calendarIcon.setColor("var(--lumo-contrast-color)");

        this.add(calendarIcon);
    }

    private void createTickerPart(TickerResponseDTO ticker) {
        H4 todayDateTitle = new H4(ticker.getTicker().getPrice());
        todayDateTitle.getStyle()
                .set("color", "var(--lumo-contrast-color)");
        H3 price = new H3(ticker.getTicker().getBase() + " - "+ ticker.getTicker().getTarget());
        price.getStyle()
                .set("color", "var(--lumo-contrast-color)")
                .set("align-text", "center");

        this.add(todayDateTitle, price);
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