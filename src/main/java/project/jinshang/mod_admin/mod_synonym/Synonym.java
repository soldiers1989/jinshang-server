package project.jinshang.mod_admin.mod_synonym;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.List;

@Table(name = "synonym")
public class Synonym {
    @Id @GeneratedValue
    private int id;
    private List<String> words;

    private Timestamp createDt;

    public int getId() {
        return id;
    }

    public Synonym setId(int id) {
        this.id = id;
        return this;
    }

    public List<String> getWords() {
        return words;
    }

    public Synonym setWords(List<String> words) {
        this.words = words;
        return this;
    }

    public Timestamp getCreateDt() {
        return createDt;
    }

    public Synonym setCreateDt(Timestamp createDt) {
        this.createDt = createDt;
        return this;
    }
}
