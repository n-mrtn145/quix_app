-- 1. Tabelle für MatchEntity
CREATE TABLE match_entity
(
    id        UUID NOT NULL,
    status    VARCHAR(255), -- Enum MatchStatus
    timestamp TIMESTAMP,
    version   BIGINT DEFAULT 0,
    PRIMARY KEY (id)
);

-- 2. Tabelle für EntryEntity
-- Da dies eine 1:1 Beziehung zur Card ist und Entry keine eigenen FKs hat, erstellen wir sie zuerst.
CREATE TABLE entry_entity
(
    id          UUID NOT NULL PRIMARY KEY ,
    wrong_throw INTEGER,
    version     BIGINT DEFAULT 0,
    red         VARCHAR(255),
    yellow      VARCHAR(255),
    blue        VARCHAR(255),
    green       VARCHAR(255)

);

-- 3. Tabelle für CardEntity
CREATE TABLE card_entity
(
    id       UUID UNIQUE NOT NULL,
    user_id  UUID,
    version  BIGINT DEFAULT 0,

    -- Fremdschlüssel-Spalten
    match_id UUID        NOT NULL,
    entry_id UUID        NOT NULL,

    PRIMARY KEY (id)
);

ALTER TABLE card_entity
    ADD CONSTRAINT fk_card_match
        FOREIGN KEY (match_id)
            REFERENCES match_entity (id);

ALTER TABLE card_entity
    ADD CONSTRAINT fk_card_entry
        FOREIGN KEY (entry_id)
            REFERENCES entry_entity (id);