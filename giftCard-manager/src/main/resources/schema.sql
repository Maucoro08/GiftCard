CREATE TABLE IF NOT EXISTS users_system (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(255) UNIQUE NOT NULL,
    amount DECIMAL(19, 2),
    date_creation DATE,
    date_expiration DATE
);