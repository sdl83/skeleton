package dao;

import generated.tables.records.ReceiptsRecord;
import generated.tables.records.TagsRecord;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.util.List;

import static com.google.common.base.Preconditions.checkState;
import static generated.Tables.RECEIPTS;
import static generated.Tables.TAGS;


public class TagDao {

    // Configuring DSL connection
    DSLContext dsl;

    public TagDao(Configuration jooqConfig) {
        this.dsl = DSL.using(jooqConfig);
    }

    public boolean check(String tagName, int receipt) {
        return (getTagReceipts(tagName, receipt).size() >= 1);
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

    // insert new tag in table
    public void delete(String tagName, int receiptID) {
            dsl
                .delete(TAGS)
                .where(TAGS.TAGNAME.eq(tagName))
                .and(TAGS.RECEIPTID.eq(receiptID))
                .execute();
    }

    public List<TagsRecord> getTagReceipts(String tagName, int receipt) {
        return dsl
                .selectFrom(TAGS)
                .where(TAGS.TAGNAME.eq(tagName))
                .and(TAGS.RECEIPTID.eq(receipt))
                .fetch();
    }

    public List<Integer> getTagReceiptsIDs(String tagName) {
        return dsl
                .select()
                .from(TAGS)
                .where(TAGS.TAGNAME.eq(tagName))
                .fetch()
                .getValues(TAGS.RECEIPTID);
    }

    public List<ReceiptsRecord> getAllReceiptsbyIDs(List<Integer> tagged) {
        return dsl
                .selectFrom(RECEIPTS)
                .where(RECEIPTS.ID.in(tagged))
                .fetch();
    }

}
