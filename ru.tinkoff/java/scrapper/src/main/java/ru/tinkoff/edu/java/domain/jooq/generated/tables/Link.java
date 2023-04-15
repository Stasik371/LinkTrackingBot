/*
 * This file is generated by jOOQ.
 */
package ru.tinkoff.edu.java.domain.jooq.generated.tables;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import javax.annotation.processing.Generated;

import org.jetbrains.annotations.NotNull;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function7;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row7;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import ru.tinkoff.edu.java.domain.jooq.generated.tables.records.LinkRecord;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.17.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Link extends TableImpl<LinkRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.link</code>
     */
    public static final Link LINK = new Link();

    /**
     * The class holding records for this type
     */
    @Override
    @NotNull
    public Class<LinkRecord> getRecordType() {
        return LinkRecord.class;
    }

    /**
     * The column <code>public.link.link_id_pk</code>.
     */
    public final TableField<LinkRecord, Long> LINK_ID_PK = createField(DSL.name("link_id_pk"), SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('link_pk_sequence'::regclass)", SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>public.link.chat_id</code>.
     */
    public final TableField<LinkRecord, Long> CHAT_ID = createField(DSL.name("chat_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.link.uri</code>.
     */
    public final TableField<LinkRecord, String> URI = createField(DSL.name("uri"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.link.last_checked_at</code>.
     */
    public final TableField<LinkRecord, LocalDateTime> LAST_CHECKED_AT = createField(DSL.name("last_checked_at"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "");

    /**
     * The column <code>public.link.last_activity_at</code>.
     */
    public final TableField<LinkRecord, LocalDateTime> LAST_ACTIVITY_AT = createField(DSL.name("last_activity_at"), SQLDataType.LOCALDATETIME(6), this, "");

    /**
     * The column <code>public.link.issue_count</code>.
     */
    public final TableField<LinkRecord, Integer> ISSUE_COUNT = createField(DSL.name("issue_count"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.link.answer_count</code>.
     */
    public final TableField<LinkRecord, Integer> ANSWER_COUNT = createField(DSL.name("answer_count"), SQLDataType.INTEGER, this, "");

    private Link(Name alias, Table<LinkRecord> aliased) {
        this(alias, aliased, null);
    }

    private Link(Name alias, Table<LinkRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.link</code> table reference
     */
    public Link(String alias) {
        this(DSL.name(alias), LINK);
    }

    /**
     * Create an aliased <code>public.link</code> table reference
     */
    public Link(Name alias) {
        this(alias, LINK);
    }

    /**
     * Create a <code>public.link</code> table reference
     */
    public Link() {
        this(DSL.name("link"), null);
    }

    public <O extends Record> Link(Table<O> child, ForeignKey<O, LinkRecord> key) {
        super(child, key, LINK);
    }

    @Override
    @NotNull
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    @NotNull
    public UniqueKey<LinkRecord> getPrimaryKey() {
        return Keys.LINK_PKEY;
    }

    @Override
    @NotNull
    public List<ForeignKey<LinkRecord, ?>> getReferences() {
        return Arrays.asList(Keys.LINK__LINK_CHAT_ID_FKEY);
    }

    private transient Chat _chat;

    /**
     * Get the implicit join path to the <code>public.chat</code> table.
     */
    public Chat chat() {
        if (_chat == null)
            _chat = new Chat(this, Keys.LINK__LINK_CHAT_ID_FKEY);

        return _chat;
    }

    @Override
    @NotNull
    public Link as(String alias) {
        return new Link(DSL.name(alias), this);
    }

    @Override
    @NotNull
    public Link as(Name alias) {
        return new Link(alias, this);
    }

    @Override
    @NotNull
    public Link as(Table<?> alias) {
        return new Link(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public Link rename(String name) {
        return new Link(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public Link rename(Name name) {
        return new Link(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public Link rename(Table<?> name) {
        return new Link(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------

    @Override
    @NotNull
    public Row7<Long, Long, String, LocalDateTime, LocalDateTime, Integer, Integer> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function7<? super Long, ? super Long, ? super String, ? super LocalDateTime, ? super LocalDateTime, ? super Integer, ? super Integer, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function7<? super Long, ? super Long, ? super String, ? super LocalDateTime, ? super LocalDateTime, ? super Integer, ? super Integer, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
