alter table entry_entity
add column match_id UUID;

alter table entry_entity
add column user_id UUID not null ;

alter table entry_entity
    add constraint fk_entry_match
        foreign key (match_id)
            references match_entity(id);

drop table card_entity;

