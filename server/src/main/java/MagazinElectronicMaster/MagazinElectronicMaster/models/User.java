package MagazinElectronicMaster.MagazinElectronicMaster.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "users")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;
}
