-- +goose Up
-- +goose StatementBegin
CREATE TABLE url (
    original_url TEXT NOT NULL PRIMARY KEY,
    hash TEXT NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    used BIGINT NOT NULL DEFAULT 0
);

CREATE INDEX url_hash_idx ON url (hash);
-- +goose StatementEnd

-- +goose Down
-- +goose StatementBegin
DROP TABLE url;
DROP INDEX url_hash_idx;
-- +goose StatementEnd
