-- noinspection SqlResolveForFile

create table match_user
(
    user_id  UUID NOT NULL,
    match_id UUID NOT NULL,
    PRIMARY KEY (user_id, match_id)
);
alter table match_user
    add constraint fk_match foreign key (match_id)
        REFERENCES match_entity (id);
