package beans;

import entity.EnumResponse;
import lombok.Data;

@Data
public class ResponseBean {

    private EnumResponse status;

    private Object content;

    private String message;

    public ResponseBean(EnumResponse status, Object content, String message) {
        this.status = status;
        this.content = content;
        this.message = message;
    }
}
