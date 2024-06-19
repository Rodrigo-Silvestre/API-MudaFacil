CREATE TABLE  address (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    country TEXT NOT NULL,
    city TEXT NOT NULL,
    state TEXT NOT NULL,
    neighborhood TEXT NOT NULL,
    street TEXT NOT NULL,
    block TEXT,
    batch TEXT,
    active BOOLEAN NOT NULL
);