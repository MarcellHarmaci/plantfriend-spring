INSERT INTO plant (name) VALUES
('leafy'),
('potted'),
('sunny');

INSERT INTO watering (plant_id, date, plantHealth, comment) VALUES
(1, '2025-04-01', 3, NULL),
(1, '2025-04-02', 4, NULL),
(1, '2025-04-03', 5, 'Leafy is happy!'),
(2, '2025-03-20', 2, NULL),
(3, '2025-04-02', 5, 'Sunny likes the spring');