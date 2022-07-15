package sk.best.newtify.web.gui.component.widget.BitcoinWidgetComponent;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TickerResponseDTO {

    Ticker ticker;
    @JsonProperty("timestamp")
    int timestamp;
    @JsonProperty("success")
    boolean success;
    @JsonProperty("error")
    String error;

}

@Getter
@Setter
class Ticker {
    @JsonProperty("base")
    String base;
    @JsonProperty("target")
    String target;
    @JsonProperty("price")
    String price;
    @JsonProperty("volume")
    String volume;
    @JsonProperty("change   ")
    String change;
}