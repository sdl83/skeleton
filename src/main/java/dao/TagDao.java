package dao;

import generated.tables.records.TagsRecord;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.util.List;

import static com.google.common.base.Preconditions.checkState;
import static generated.Tables.TAGS;


public class TagDao {

    // Configuring DSL connection
    DSLContext dsl;

    public TagDao(Configuration jooqConfig) {
        this.dsl = DSL.using(jooqConfig);
    }

    // insert new tag in table
    public int insert(String tagName, int receiptID) {
        TagsRecord tagsRecord = dsl
                .insertInto(TAGS, TAGS.TAGNAME, TAGS.RECEIPTID)
                .values(tagName, receiptID)
                .returning(TAGS.ID)
                .fetchOne();

        checkState(tagsRecord != null && tagsRecord.getId() != null, "Insert failed");

        return tagsRecord.getId();
    }

//    TODO: add remove...
//    TODO: Make list of all receipts to return

//    public List<ReceiptsRecord> getAllReceipts() {
//        return dsl.selectFrom(RECEIPTS).fetch();
//    }
}
