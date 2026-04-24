CREATE TABLE users (
    id UUID PRIMARY KEY,
    create_date TIMESTAMP NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email_address VARCHAR(255),
    user_name VARCHAR(255),
    password VARCHAR(255),
    email_confirmed BOOLEAN NOT NULL
);

CREATE TABLE entry (
    id UUID PRIMARY KEY,
    create_date TIMESTAMP NOT NULL,
    subject VARCHAR(255),
    content VARCHAR(5000),
    created_by_id UUID,
    CONSTRAINT fk_entry_user FOREIGN KEY (created_by_id) REFERENCES users(id)
);

CREATE TABLE entry_comment (
    id UUID PRIMARY KEY,
    create_date TIMESTAMP NOT NULL,
    content VARCHAR(2000),
    entry_id UUID,
    created_by_id UUID,
    CONSTRAINT fk_comment_entry FOREIGN KEY (entry_id) REFERENCES entry(id),
    CONSTRAINT fk_comment_user FOREIGN KEY (created_by_id) REFERENCES users(id)
);

CREATE TABLE entry_vote (
    id UUID PRIMARY KEY,
    create_date TIMESTAMP NOT NULL,
    vote_type SMALLINT,
    entry_id UUID,
    created_by_id UUID,
    CONSTRAINT fk_vote_entry FOREIGN KEY (entry_id) REFERENCES entry(id),
    CONSTRAINT fk_vote_user FOREIGN KEY (created_by_id) REFERENCES users(id)
);

CREATE TABLE entry_comment_vote (
    id UUID PRIMARY KEY,
    create_date TIMESTAMP NOT NULL,
    vote_type SMALLINT,
    entry_comment_id UUID,
    created_by_id UUID,
    CONSTRAINT fk_comment_vote_comment FOREIGN KEY (entry_comment_id) REFERENCES entry_comment(id),
    CONSTRAINT fk_comment_vote_user FOREIGN KEY (created_by_id) REFERENCES users(id)
);

CREATE TABLE entry_favorite (
    id UUID PRIMARY KEY,
    create_date TIMESTAMP NOT NULL,
    entry_id UUID,
    created_user_id UUID,
    CONSTRAINT fk_favorite_entry FOREIGN KEY (entry_id) REFERENCES entry(id),
    CONSTRAINT fk_favorite_user FOREIGN KEY (created_user_id) REFERENCES users(id)
);

CREATE TABLE entry_comment_favorite (
    id UUID PRIMARY KEY,
    create_date TIMESTAMP NOT NULL,
    entry_comment_id UUID,
    created_user_id UUID,
    CONSTRAINT fk_comment_favorite_comment FOREIGN KEY (entry_comment_id) REFERENCES entry_comment(id),
    CONSTRAINT fk_comment_favorite_user FOREIGN KEY (created_user_id) REFERENCES users(id)
);

CREATE TABLE email_confirmation (
    id UUID PRIMARY KEY,
    create_date TIMESTAMP NOT NULL,
    old_email_address VARCHAR(255),
    new_email_address VARCHAR(255)
);
