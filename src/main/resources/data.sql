INSERT INTO plant (id, name) VALUES
(1, 'leafy'),
(2, 'potted'),
(3, 'sunny');

INSERT INTO watering (id, plant_id, date, plantHealth, comment) VALUES
(1, 1, '2025-04-01', 3, NULL),
(2, 1, '2025-04-02', 4, NULL),
(3, 1, '2025-04-03', 5, 'Leafy is happy!'),
(4, 2, '2025-03-20', 2, NULL),
(5, 3, '2025-04-02', 5, 'Sunny likes the spring');