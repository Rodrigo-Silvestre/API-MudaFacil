CREATE TABLE services (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    date DATE NOT NULL,
    value_in_cents INTEGER NOT NULL,
    user_id TEXT NOT NULL,
    vehicle_id TEXT NOT NULL,
    address_source_id TEXT NOT NULL,
    address_target_id TEXT NOT NULL,
    active BOOLEAN NOT NULL,
    FOREIGN KEY(user_id) REFERENCES users("id"),
    FOREIGN KEY(vehicle_id) REFERENCES vehicle("id"),
    FOREIGN KEY(address_source_id) REFERENCES address("id"),
    FOREIGN KEY(address_target_id) REFERENCES address("id")
);