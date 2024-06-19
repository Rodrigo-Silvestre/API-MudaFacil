CREATE TABLE vehicle (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    name TEXT NOT NULL,
    type_of_vehicle TEXT,
    load_in_kilograms INTEGER NOT NULL,
    active BOOLEAN NOT NULL
);