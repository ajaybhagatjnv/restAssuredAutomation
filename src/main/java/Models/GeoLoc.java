package Models;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GeoLoc {
    private String status;
    private String place_id;
    private String scope;
    private String reference;
    private String id;
}
