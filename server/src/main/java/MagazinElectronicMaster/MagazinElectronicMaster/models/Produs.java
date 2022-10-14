package MagazinElectronicMaster.MagazinElectronicMaster.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "produse")
@NoArgsConstructor
public class Produs {
    @Id
    @Generated
    private String codProdus;
    private String nume;
    private Integer pret;
    private String photo;
    private String cantitate;
    private ArrayList<Integer> note = new ArrayList<>();

    public Produs(String codProdus) {
        this.codProdus = codProdus;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Integer getPret() {
        return pret;
    }

    public void setPret(Integer pret) {
        this.pret = pret;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCodProdus() {
        return codProdus;
    }

    public void setCodProdus(String codProdus) {
        this.codProdus = codProdus;
    }

    public String getCantitate() {
        return cantitate;
    }

    public void setCantitate(String cantitate) {
        this.cantitate = cantitate;
    }

    public ArrayList<Integer> getNote() {
        return note;
    }

    public void adaugaNota(int nota) {
        note.add(nota);
    }

    public void setNote(ArrayList<Integer> note) {
        this.note = note;
    }
}
