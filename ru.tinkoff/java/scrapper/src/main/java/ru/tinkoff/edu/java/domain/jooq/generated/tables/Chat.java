/*
 * This file is generated by jOOQ.
 */
package ru.tinkoff.edu.java.domain.jooq.generated.tables;


import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import javax.annotation.processing.Generated;

import org.jetbrains.annotations.NotNull;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function2;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row2;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import ru.tinkoff.edu.java.domain.jooq.generated.tables.records.ChatRecord;


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
public class Chat extends TableImpl<ChatRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.chat</code>
     */
    public static final Chat CHAT = new Chat();

    /**
     * The class holding records for this type
     */
    @Override
    @NotNull
    public Class<ChatRecord> getRecordType() {
        return ChatRecord.class;
    }

    /**
     * The column <code>public.chat.chat_id_pk</code>.
     */
    public final TableField<ChatRecord, Long> CHAT_ID_PK = createField(DSL.name("chat_id_pk"), SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('chat_pk_sequence'::regclass)", SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>public.chat.telegram_chat_id</code>.
     */
    public final TableField<ChatRecord, Long> TELEGRAM_CHAT_ID = createField(DSL.name("telegram_chat_id"), SQLDataType.BIGINT.nullable(false), this, "");

    private Chat(Name alias, Table<ChatRecord> aliased) {
        this(alias, aliased, null);
    }

    private Chat(Name alias, Table<ChatRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.chat</code> table reference
     */
    public Chat(String alias) {
        this(DSL.name(alias), CHAT);
    }

    /**
     * Create an aliased <code>public.chat</code> table reference
     */
    public Chat(Name alias) {
        this(alias, CHAT);
    }

    /**
     * Create a <code>public.chat</code> table reference
     */
    public Chat() {
        this(DSL.name("chat"), null);
    }

    public <O extends Record> Chat(Table<O> child, ForeignKey<O, ChatRecord> key) {
        super(child, key, CHAT);
    }

    @Override
    @NotNull
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    @NotNull
    public UniqueKey<ChatRecord> getPrimaryKey() {
        return Keys.CHAT_PKEY;
    }

    @Override
    @NotNull
    public List<UniqueKey<ChatRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.CHAT_TELEGRAM_CHAT_ID_KEY);
    }

    @Override
    @NotNull
    public Chat as(String alias) {
        return new Chat(DSL.name(alias), this);
    }

    @Override
    @NotNull
    public Chat as(Name alias) {
        return new Chat(alias, this);
    }

    @Override
    @NotNull
    public Chat as(Table<?> alias) {
        return new Chat(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public Chat rename(String name) {
        return new Chat(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public Chat rename(Name name) {
        return new Chat(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public Chat rename(Table<?> name) {
        return new Chat(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    @NotNull
    public Row2<Long, Long> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function2<? super Long, ? super Long, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function2<? super Long, ? super Long, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}