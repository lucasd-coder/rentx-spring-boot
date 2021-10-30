INSERT INTO tb_users (id, name, username, password, email, driver_license, created_at) VALUES ('420ee4ba-6eec-4f14-a237-14cc972d0494', 'zedasilva', 'ze123', '$2a$10$p98b46pCyGC4FM4bC/E.4eRB94SAD8bvvto0kewr1UGjjPMd7LfqG', 'zedasilva@gmail.com', '1010101099', NOW());

INSERT INTO tb_users (id, name, username, password, email, driver_license, created_at) VALUES ('3df97fad-161b-459b-b079-b2dc2cf026ac', 'testeAdmin', 'admin123', '$2a$10$h5UYog5PAw.GUW2SwhAnduf/9W7k48rwoBnX3Q7HIkxAQ81BiSgxW', 'admin@gmail.com', '1010176454', NOW());

INSERT INTO perfis (user_id, perfis) VALUES ('3df97fad-161b-459b-b079-b2dc2cf026ac', 1);

INSERT INTO tb_categories(id, name, description, created_at) VALUES ('cf4957be-8b59-40bd-8681-7dc2a50ee570', 'Ford', 'category test',NOW());

INSERT INTO tb_cars (id, name, description, daily_rate, avaiable, fine_amount, license_plate, brand, category_id, created_at) VALUES ('55ae7f39-0ac9-4386-a315-c07ae02ec567', 'Nome Car', 'Description Car', 100, true, 50, 'ABC-1234',  'Brand',  'cf4957be-8b59-40bd-8681-7dc2a50ee570',  NOW());

INSERT INTO tb_cars (id, name, description, daily_rate, avaiable, fine_amount, license_plate, brand, category_id, created_at) VALUES ('db155f29-1972-4e8b-adec-c8c1bc089e28', 'Nome Car2', 'Description Car2', 200, true, 90, 'ABC-593',  'Brand2',  'cf4957be-8b59-40bd-8681-7dc2a50ee570',  NOW());
