-- Insert sample data for testing

-- Insert dealers
INSERT INTO dealers (id, tenant_id, name, email, subscription_type) VALUES
                                                                        (UNHEX(REPLACE('11111111-1111-1111-1111-111111111111', '-', '')), 'tenant1', 'Premium Motors', 'premium@motors.com', 'PREMIUM'),
                                                                        (UNHEX(REPLACE('22222222-2222-2222-2222-222222222222', '-', '')), 'tenant1', 'Basic Auto', 'basic@auto.com', 'BASIC'),
                                                                        (UNHEX(REPLACE('33333333-3333-3333-3333-333333333333', '-', '')), 'tenant2', 'Luxury Cars', 'luxury@cars.com', 'PREMIUM');

-- Insert vehicles
-- Note: Replace the binary IDs with actual dealer IDs from above for proper relationships